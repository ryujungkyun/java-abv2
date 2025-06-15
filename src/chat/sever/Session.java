package chat.sever;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class Session implements Runnable{

    private final Socket socket;
    private final DataInputStream input;
    private final DataOutputStream output;
    private final CommandManger commandManger;
    private final SessionManager sessionManager;

    private boolean closed = false;
    private String username;

    public Session(Socket socket, CommandManger commandManger, SessionManager sessionManager) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        this.commandManger = commandManger;
        this.sessionManager = sessionManager;
        sessionManager.add(this);
    }

    @Override
    public void run() {
        try {
            while (true) {
                //클라이언트로부터 문자 받기
                String received = input.readUTF();
                log("client -> server: " + received);

                // 메세지를 전체에게 보내주기
                commandManger.execute(received,this); //IOException
            }
        } catch (IOException e) {
            log(e);
        } finally {
            sessionManager.remove(this);
            sessionManager.sendAll(username + "님이 퇴장했습니다.");
            close();
        }
    }

    public void send(String message) throws IOException {
        log("server -> client: " + message);
        output.writeUTF(message);
    }

    public void close() {
        if (closed) {
            return;
        }
        closeAll(socket, input, output);
        closed = true;
        log("연결 종료: " + socket);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
