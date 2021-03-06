/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectN.component;

import ConnectN.server.GameHandler;
import java.io.Serializable;

/**
 *
 * @author phuy
 */
public class Session implements Serializable {

    private String sName;
    private int id, rows, cols, n, timeout;
    private Player creatorPlayer, joinerPlayer;
    
    public Session(String name) {
        
        this.sName = name;
    }

//    public Session(String name, int id, int rows, int cols, int n, int timeout) {
//        this.sName = name;
//        this.id = id;
//        this.rows = rows;
//        this.cols = cols;
//        this.n = n;
//        this.timeout = timeout;
//    }

    public Session(int id, Player creatorPlayer, String name, int rows, int cols, int n, int timeout) {
        this.sName = name;
        this.creatorPlayer = creatorPlayer;
        this.id = id;
        this.rows = rows;
        this.cols = cols;
        this.n = n;
        this.timeout = timeout;
        
    }

    public Session(String sName, int rows, int cols, int n, int timeout, Player creatorPlayer) {
        this.sName = sName;
        this.rows = rows;
        this.cols = cols;
        this.n = n;
        this.timeout = timeout;
        this.creatorPlayer = creatorPlayer;
        
    }   

    public String getName() {
        return sName;
    }

    public int getCols() {
        return cols;
    }

    public Player getCreator() {
        return creatorPlayer;
    }

    public int getId() {
        return id;
    }

    public int getN() {
        return n;
    }

    public int getRows() {
        return rows;
    }

    public int getTimeout() {
        return timeout;
    }

    public String getsName() {
        return sName;
    }

    public String displayTimeOut() {
        return (timeout == 0)
                ? "Unlimited"
                : timeout + " second(s)";
    }

    public Player getJoinerPlayer() {
        return joinerPlayer;
    }

    public Player getCreatorPlayer() {
        return creatorPlayer;
    }

    public void setJoinerPlayer(Player joinerPlayer) {
        this.joinerPlayer = joinerPlayer;
    }

    @Override
    public String toString() {
        return "\tGameID: "+id + "\n\tCreatorID: " + creatorPlayer.getId() + " Name: " + creatorPlayer.getName() + "\n\tGameName: " + sName + "\n\tRows: " + rows + "\n\tCols: " + cols + "\n\tN: " + n + "\n\tTimeout: " + timeout;
    }
    
    
}
