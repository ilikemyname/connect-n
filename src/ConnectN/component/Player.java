/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectN.component;

import java.io.Serializable;

/**
 *
 * @author phuy
 */
public class Player implements Serializable, Comparable<Player> {

    private String name;
    private int id;
    private boolean myTurn;

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return id + " " + name;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    @Override
    public int compareTo(Player o) {
        return ((Integer)this.getId()).compareTo(o.getId());
    }
}
