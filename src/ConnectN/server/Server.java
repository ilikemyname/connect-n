/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectN.server;

import ConnectN.component.Communicator;
import ConnectN.component.Player;
import ConnectN.component.Session;
import ConnectN.component.Status;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phuy
 */
public class Server implements Runnable {

    final int PORT = 1616;
    int numPlayer, numSession;
    List<Session> sessionList;
    List<ServerListener> listenerList;
    Communicator communicator;

    public Server() {
        sessionList = new ArrayList<>();
        listenerList = new ArrayList<>();
        numPlayer = 0;
        numSession = 0;
    }

    public static void main(String args[]) {
        Server server = new Server();
        new Thread(server, "ServerConnect-n").start();
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Generate server using port " + PORT);
            System.out.println("Server is running ...");
            while (true) {
                System.out.println("Waiting for new client ...");
                Socket socket = server.accept();
                numPlayer++;
                communicator = new Communicator(socket, new ObjectOutputStream(socket.getOutputStream()), new ObjectInputStream(socket.getInputStream()));

                String clientName = (String) communicator.read();

                System.out.println("Player ID: " + numPlayer + " name: " + clientName + " connected ...");
                communicator.write(numPlayer);
                communicator.write(sessionList);
                ServerListener listener = new ServerListener(this, communicator, new Player(clientName, numPlayer));
                if (listenerList.add(listener)) {
                    listener.start();
                }
            }
        } catch (IOException ex) {
        }
    }

    public List<Session> getSessionList() {
        return sessionList;
    }

    public int getNumSession() {
        return numSession;
    }

    public void increateNumSession() {
        numSession++;
    }

    public boolean addSession(Session newSession) {
        synchronized (sessionList) {
            return this.sessionList.add(newSession);
        }
    }

    public void notifyAllGame(Object obj, Status status) {
        for (ServerListener serverListener : listenerList) {
            serverListener.write(status);
            serverListener.write(obj);
        }
    }

    public Session getSessionByID(int id) {
        for (Session session : sessionList) {
            if (session.getId() == id) {
                return session;
            }
        }
        return null;
    }

    public boolean removeSession(Session session) {
        synchronized (sessionList) {
            return sessionList.remove(session);
        }
    }

    public ServerListener getListener(Player player) {
        for (ServerListener serverListener : listenerList) {
            if (serverListener.getPlayer().compareTo(player) == 0) {
                return serverListener;
            }
        }
        return null;
    }

    public Session getSessionByCreatorPlayer(Player p) {
        for (Session session : sessionList) {
            if (session.getCreatorPlayer().compareTo(p) == 0) {
                return session;
            }
        }
        return null;
    }

    public Session getSessinByJoinedPlayer(Player p) {
        for (Session session : sessionList) {
            if (session.getJoinerPlayer() != null && session.getJoinerPlayer().compareTo(p) == 0) {
                return session;
            }
        }
        return null;
    }
}
