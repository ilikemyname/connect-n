/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectN.client.view.test;

import ConnectN.client.model.ClientModel;
import ConnectN.client.view.ConnectNView;
import ConnectN.client.view.components.MetroStyle;
import ConnectN.component.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phuy
 */
public class ClientTest implements Runnable{

    private Communicator server;
    private Player[][] board;
    private boolean win;
    private String name;
    private int clientID;
    private ArrayList<Session> sessionList;
    private ClientModel client;

    public ClientTest() throws UnknownHostException, IOException {
        MetroStyle.setLookAndFeel("dark");
        
        
        client = new ClientModel("abc");
        new Thread(client).start();
        new ConnectNView(client,client.getPlayer());
        
        new Thread(this).start();

    }

    public static void main(String[] args) {
        try {
            new ClientTest();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
         Object message = server.read();
        while(true){
            
        }
    }
    
    public void createDisk(int x, int y) {
        server.write(new Disk(x, y));
    }

    public void createGame() {
        server.write(Status.CREATE);

    }

    public void joinGame(int roomName) {
        server.write(Status.JOIN + ":" + roomName);
    }

    public boolean isWin(Status obj) {
        if (obj == Status.WIN) {
            return true;
        } else {
            return false;
        }

    }

    public void setBoard(Player[][] board) {
        this.board = board;
    }

    public int getPlayerID() {
        return clientID;
    }

    public ArrayList<Session> getSessionList() {
        return this.sessionList;
    }

    public Communicator getServer() {
        return server;
    }

    public String getClientName() {
        return name;
    }
}
