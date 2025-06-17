package was.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

    private String method;
    private String path;
    // ?q=hello&key2=value2
    private final Map<String, String> queryParameters = new HashMap<>();
    private final Map<String, String> headers = new HashMap<>();

    public HttpRequest(BufferedReader reader) throws IOException {
        parseRequestLine(reader);
        parseHeader(reader);
        // 메시지 바디는 이후에 처리
    }

    // GET /search?q=hello HTTP/1.1
    // Host: localhost:12345
    private void parseRequestLine(BufferedReader reader) throws IOException {
        String requestLine = reader.readLine();
        if (requestLine == null) {
            throw new IOException("EOF: No request line received");
        }
        String[] parts = requestLine.split(" ");
        if (parts.length != 3) {
            throw new IOException("Invalid request line: " + requestLine);
        }

        method = parts[0];
        //레귤러 익스프레션으로 인하여 //? 이렇게 처리해야함
        String[] pathParts = parts[1].split("\\?");
        path = pathParts[0];

        // q = hello
        //
    }

    private void parseHeader(BufferedReader reader) {

    }
}
