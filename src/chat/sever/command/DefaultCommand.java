package chat.sever.command;

import chat.sever.Session;

import java.io.IOException;
import java.util.Arrays;

public class DefaultCommand implements Command{
    @Override
    public void execute(String[] args, Session session) throws IOException {
        session.send("처리할 수 없는 명령어 입니다: " + Arrays.toString(args));
    }
}
