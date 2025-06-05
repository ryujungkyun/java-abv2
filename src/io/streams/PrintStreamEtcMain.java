package io.streams;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class PrintStreamEtcMain {

    public static void main(String[] args) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream("temp/print.txt");
        PrintStream printStream = new PrintStream(fos);

        //시스템 안에서 기본 인코딩을 통하여 인코딩을 진행함
        printStream.println("hello java");
        printStream.println(10);
        printStream.println(true);
        printStream.printf("hello %s", "world");
        printStream.close();
    }
}
