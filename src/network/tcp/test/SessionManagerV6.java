package network.tcp.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SessionManagerV6 {

    private List<SessionV6> sessions = new ArrayList<>();

    public synchronized void add(SessionV6 session) {
        sessions.add(session);
    }

    public synchronized void remove(SessionV6 session) {
        sessions.remove(session);
    }

    public synchronized void closeAll() {
        for (SessionV6 session : sessions) {
            session.close();
        }
        sessions.clear();
    }

    public synchronized void sendAll(String received) throws IOException {
        for (SessionV6 session : sessions) {
            session.send(received);
        }
    }
}
