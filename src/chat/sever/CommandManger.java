package chat.sever;

import java.io.IOException;

public interface CommandManger {
    void execute(String totalMessage, Session session) throws IOException;
}
