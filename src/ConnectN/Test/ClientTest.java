/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectN.Test;

import ConnectN.client.view.components.GamePanel;
import ConnectN.client.view.components.MetroStyle;
import ConnectN.component.Communicator;
import ConnectN.component.Session;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phuy
 */
public class ClientTest implements Runnable {

    private String clientName;
    private Communicator server;
    private boolean myTurn;
    private int clientID;
    private ArrayList<Session> sessionList;
    
    public static void main(String[] args){
        try {
            new ClientTest("abc");
        } catch (IOException ex) {
            Logger.getLogger(ClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ClientTest(String clientName) throws IOException {

        MetroStyle.setLookAndFeel("dark");
        this.clientName = clientName;
        initializeConnection();
        System.out.println("Waiting to play...");
        new Thread(this).start();
    }

    private void initializeConnection() throws IOException {
        Socket socket = new Socket("localhost", 1616);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        server = new Communicator(socket, out, in);
    }

    @Override
    public void run() {
        System.out.println("Connecting ...");
        while (true) {
            //GamePanel panel = new GamePanel();
            clientID = (Integer) server.read();
            server.write(clientName);
            System.out.println(clientID + " " + clientName);
            ArrayList<Session> gameList = (ArrayList<Session>) server.read();
            if (gameList != null) {
                this.sessionList = gameList;
            }
        }
    }

    public void start() {
        Thread t = new Thread(this);
        t.start();
    }

    public int getPlayerID() {
        return clientID;
    }

    public ArrayList<Session> getSessionList() {
        return sessionList;
    }
}
