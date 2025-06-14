package network.tcp.test;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import static util.MyLogger.log;

public class ReadHandler implements Runnable {
    DataInputStream input;

    public ReadHandler(DataInputStream input) {
        this.input = input;
    }

    @Override
    public void run() {
        String received = null;

        try {
        received = input.readUTF();
        } catch (IOException e) {
            log(e);
        }
        log("client <- server: " + received);
    }
}
