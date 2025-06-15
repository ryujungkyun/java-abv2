package chat.sever;

import network.tcp.v6.SessionManagerV6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class Server {

    private final int port;
    private final CommandManger commandManger;
    private final SessionManager sessionManager;

    private ServerSocket serverSocket;

    public Server(int port, CommandManger commandManger, SessionManager sessionManager) {
        this.port = port;
        this.commandManger = commandManger;
        this.sessionManager = sessionManager;
    }

    public void start() throws IOException {
        log("서버 시작: " + commandManger.getClass());
        serverSocket = new ServerSocket(port);
        log("서버 소켓 시작 - 리스닝 포트: " + port);

        addShutdownHook();
        running();
    }

    private void running() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                log("소켓 연결: " + socket);

                Session session = new Session(socket, commandManger, sessionManager);
                Thread thread = new Thread(session);
                thread.start();

            }
        } catch (IOException e) {
            log(e);
        }
    }

    private void addShutdownHook() {
        ShutDownHook target = new ShutDownHook(serverSocket, sessionManager);
        Runtime.getRuntime().addShutdownHook(new Thread(target, "shutdown"));
    }

    static class ShutDownHook implements Runnable {

        private final ServerSocket serverSocket;
        private final SessionManager sessionManager;

        public ShutDownHook(ServerSocket serverSocket, SessionManager sessionManager) {
            this.serverSocket = serverSocket;
            this.sessionManager = sessionManager;
        }

        @Override
        public void run() {
            log("shutdownHook 실행");
            try {
                sessionManager.closeAll();
                serverSocket.close();

                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("e = " + e);
            }
        }
    }
}


