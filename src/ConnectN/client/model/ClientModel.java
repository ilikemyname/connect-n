/*
 * Copyright (c) 2010, 2012 Dang Hong Thanh. All rights reserved.
 * 
 * This code is written by Luan Nguyen Thanh, with any partners involved.
 * The unauthorized use of this code should not be permitted!
 */
package ConnectN.client.model;

import ConnectN.client.view.ConnectNView;
import ConnectN.component.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Dang Hong Thanh
 */
public class ClientModel implements Runnable {
    
    final int PORT = 1616;
    private Player[][] board;
    private boolean win;
    private List<Session> gameList;
    private Communicator server;
    private Player player;
    private Player joinPlayer;
    private ConnectNView view;
    private Thread t;
    
    public ClientModel(String clientName) throws IOException {
        System.out.println("Name is " + clientName);
        Socket socket = new Socket("localhost", PORT);
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        server = new Communicator(socket, outputStream, inputStream);
        player = new Player(clientName);
        System.out.println("Connected to server");
        System.out.println("Write playername to server");
        
        server.write(player.getName());
        
        System.out.println("Read Server ID");
        int clientID = (Integer) server.read();
        player.setId(clientID);
        List<Session> gameList = (List<Session>) server.read();
        if (gameList != null) {
            this.gameList = gameList;
            for (Session session : gameList) {
                System.out.println(session);
            }
        }
        
    }
    
    public void createDisk(int x, int y) {
        System.out.println(new Disk(x, y));
        server.write(new Disk(x, y));
    }
    
    public void createGame(String gameDetails) {
        server.write(Status.CREATE);
        gameDetails = player.getId() + ":" + player.getName() + ":" + gameDetails;
        server.write(gameDetails);
    }
    
    public void joinGame(int roomID) {
        server.write(Status.JOIN);
        server.write(player);
        server.write(roomID);
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
        return player.getId();
    }
    
    public List<Session> getSessionList() {
        return this.gameList;
    }
    
    public Communicator getServer() {
        return server;
    }
    
    public String getClientName() {
        return player.getName();
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public void quitGame() {
        System.out.println("Request Quit Game");
        server.write(Status.QUIT);
        
    }
    
    @Override
    public void run() {
        //read clientID
        while (true) {
            
            System.out.println("Get mess from server");
            Object obj = server.read();
            if (obj instanceof Status) {
                Status msg = (Status) obj;
                switch (msg) {
                    case NEW:
                        Session newSession = (Session) server.read();
                        gameList.add(newSession);
                        view.repaint();
                        break;
                    case ERROR:
                        System.out.println("ERROR");
                        break;
                    case CONTINUE:
                        System.out.println("My Turn");
                        player.setMyTurn(true);
                        break;
                    case WIN:
                        System.out.println("Win!!!!");
                        JOptionPane.showMessageDialog(null, "YOU WIN!!!", null, JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case LOSE:
                        System.out.println("Lose!!!");
                        JOptionPane.showMessageDialog(null, "YOU LOSE :(", null, JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case TIE:
                        System.out.println("Tie");
                        JOptionPane.showMessageDialog(null, "YOU TIE -_-", null, JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case REMOVE:
                        Session removeSession = (Session) server.read();
                        gameList.remove(removeSession);
                        view.repaint();
                        break;
                    case GAME_IS_JOINED:
                        Player joinedPlayer = (Player) server.read();
                        if (view != null) {
                            view.updateRoomInfo(joinedPlayer);
                        }
                        System.out.println("JoinedPlayer" + joinedPlayer);
                        break;
                    case QUIT:
                        view.goBackToList();
                        break;
                    default:
                        break;
                }
            } else if (obj instanceof Disk) {
                Disk disk = (Disk) obj;
                int diskColor = (int) server.read();
                view.updateGamePanel(disk, diskColor);
                System.out.println(obj);
            }
            
            
        }
    }
    
    public void start() {
        t = new Thread(this);
        t.start();
        
    }
    
    public Player getJoinPlayer() {
        return joinPlayer;
    }
    
    public void setView(ConnectNView view) {
        this.view = view;
    }
    
    public void stop() {
        t.stop();
    }
}
