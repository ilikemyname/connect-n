package ConnectN.component;

import java.io.Serializable;

/**
 *
 * @author phuy
 */
public class Disk implements Serializable {

    private int x;
    private int y;

    public Disk(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return x + "," + y;
    }
}
