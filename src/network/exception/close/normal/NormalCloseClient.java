package network.exception.close.normal;

import java.io.*;
import java.net.Socket;

import static util.MyLogger.log;

public class NormalCloseClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        log("소켓 연결: " + socket);
        InputStream input = socket.getInputStream();

        readByInputStream(input, socket);
        readByBufferRead(input, socket);
        readByDataInputStream(input, socket);

        log("연결 종료: " + socket.isClosed());
    }

    private static void readByDataInputStream(InputStream input, Socket socket) throws IOException {
        DataInputStream dis = new DataInputStream(input);

        //null로 받을 수 있지만 dis.readInt는 -1을 반환함으로 함꺼번에 처리할 수 있는 EOF를 사용
        //close를 중복으로 써도 괜찮은 이유는 중복 호출에 대한 방지가 있기 때문이다.
        try {
            dis.readUTF();
        } catch (EOFException e) {
            log(e);
        }finally {
            dis.close();
            socket.close();
        }
    }

    private static void readByBufferRead(InputStream input, Socket socket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(input));
        String readString = br.readLine();
        log("readString = " + readString);
        if (readString == null) {
            br.close();
            socket.close();
        }
    }

    private static void readByInputStream(InputStream input, Socket socket) throws IOException {
        int read = input.read();
        log("read" + read);
        if (read == -1) {
            input.close();
            socket.close();
        }
    }
}
