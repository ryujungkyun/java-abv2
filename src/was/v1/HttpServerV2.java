package was.v1;

import jdk.net.Sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.nio.charset.StandardCharsets.UTF_8;
import static util.MyLogger.log;

public class HttpServerV2 {

    private final int port;

    public HttpServerV2(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        log("서버 시작 port: " + port);

        while (true) {
            Socket socket = serverSocket.accept();
            process(socket); // 5초
        }
    }

    private void process(Socket socket) throws IOException {

            Thread thread = new Thread(new HttpRequestHandler(socket), "HttpHandler");
            thread.start();
    }

    private static void responseToClient(PrintWriter writer) {
        // 웹 브라우저에 전달하는 내용
        String body = "<h1>Hello world</h1>";
        int length = body.getBytes(UTF_8).length;

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Content-Type: text/html\r\n");
        sb.append("Content-Length: ").append(length).append("\r\n");
        sb.append("\r\n"); //header, body 구분 라인
        sb.append(body);

        log("HTTP 응답 정보 출력");
        System.out.println(sb);

        writer.println(sb);
        writer.flush();
    }

    private static String requestToString(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis); // 서버 처리 시간
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static class HttpRequestHandler implements Runnable {
        private final Socket socket;

        HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (socket;
                 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
                 PrintWriter writer = new PrintWriter(socket.getOutputStream(), false, UTF_8)){
                 String requestString = requestToString(reader);
                if (requestString.contains("/favicon.ico")) {
                    log("favicon 요청");
                    return;
                }
                log("HTTP 요청 정보 출력");
                System.out.println(requestString);
                log("HTTP 응답 생성중...");
                sleep(5000);
                responseToClient(writer);
                log("HTTP 응답 전달 완료");
            } catch (Exception e) {
                log(e);
            }
        }
    }
}
