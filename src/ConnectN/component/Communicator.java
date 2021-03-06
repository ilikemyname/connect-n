/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectN.component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phuy
 */
public class Communicator {

    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;

    public Communicator(Socket socket, ObjectOutputStream out, ObjectInputStream in) {
        this.socket = socket;
        this.in = in;
        this.out = out;
    }

    public void write(Object obj) {
        try {
            out.writeObject(obj);
        } catch (IOException ex) {
            Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Object read() {
        try {
            return in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
