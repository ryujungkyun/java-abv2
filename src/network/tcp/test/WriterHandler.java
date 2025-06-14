package network.tcp.test;

import javax.net.ssl.SSLSessionBindingEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static util.MyLogger.log;

public class WriterHandler implements Runnable {
    DataOutputStream output;

    public WriterHandler( DataOutputStream output) {
        this.output = output;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                String toSend = scanner.nextLine();

                // 서버에게 문자 보내기
                output.writeUTF(toSend);
                log("client -> sever: " + toSend);

                if (toSend.equals("exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            log(e);
        }
    }
}

