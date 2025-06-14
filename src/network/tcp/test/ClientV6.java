package network.tcp.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import static util.MyLogger.log;

public class ClientV6 {

    private static final int PORT = 12345;
    private static final String IP_ADRESS = "localhost";

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");
        Socket socket = new Socket(IP_ADRESS, PORT);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        // finally 블록에서 변수에 접근해야 한다. 따라서 try 블록 안에서 선언할 수 없다.

        Thread readThread = new Thread(new ReadHandler(input),"ReadHandler");
        Thread writeThread = new Thread(new WriterHandler(output), "WriteHandler");
        readThread.start();
        writeThread.start();
    }
}
