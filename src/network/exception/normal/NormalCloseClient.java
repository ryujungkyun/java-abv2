package network.exception.normal;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import static util.MyLogger.log;

public class NormalCloseClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);
        log("소켓 연결: " + socket);
        InputStream input = socket.getInputStream();

        readByInputStream(input, socket);
    }

    private static void readByInputStream(InputStream input, Socket socket) throws IOException {
        int read = input.read();
    }

}
