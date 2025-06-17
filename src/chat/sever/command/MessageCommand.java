package chat.sever.command;

import chat.sever.Session;
import chat.sever.SessionManager;

import java.io.IOException;

public class MessageCommand implements Command{
    private final SessionManager sessionManager;

    public MessageCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String[] args, Session session)  {
        String message = args[1];
        sessionManager.sendAll("[" + session.getUsername() + "] " + message);
    }
}
