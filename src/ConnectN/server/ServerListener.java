/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectN.server;

import ConnectN.component.*;
import java.util.List;

/**
 *
 * @author phuy
 */
public class ServerListener implements Runnable {

    private final int CREATE_MESSAGE_LENGTH = 7;
    private Server server;
    private Communicator communicator;
    private Player player;
    private GameHandler gameHandler;

    public ServerListener(Server server, Communicator communicator, Player player) {
        this.server = server;
        this.communicator = communicator;
        this.player = player;
    }

    @Override
    public void run() {
        while (true) {
            Object obj = communicator.read();

            if (obj instanceof Status) {
                Status status = (Status) obj;
                switch (status) {
                    case CREATE:
                        String mesDetails = (String) communicator.read();
                        int sID = server.getNumSession();
                        String[] sessionDetails = mesDetails.split(":");
                        if (sessionDetails.length != CREATE_MESSAGE_LENGTH) {
                            System.out.println("ERROR invalid statement for session");
                            writeError(Status.ERROR);
                        } else {
                            int cID = Integer.parseInt(sessionDetails[0]);
                            String cName = sessionDetails[1];
                            String sName = sessionDetails[2];
                            int n = Integer.parseInt(sessionDetails[3]);
                            int row = Integer.parseInt(sessionDetails[4]);
                            int col = Integer.parseInt(sessionDetails[5]);
                            int timeout = Integer.parseInt(sessionDetails[6]);
                            Session newSession = new Session(sID, new Player(cName, cID), sName, row, col, n, timeout);

                            if (server.addSession(newSession)) {
                                server.notifyAllGame(newSession, Status.NEW);
                            }
                            
                            System.out.println("New Game Create: ");
                            System.out.println(newSession);
                        }
                        break;
                    case JOIN:

                        Player player = (Player) communicator.read();
                        int sessionID = (int) communicator.read();
                        System.out.println("Player ID: "+player.getId()+" Name: " + player.getName() + " is joining");
                        Session session = server.getSessionByID(sessionID);
                        if (session != null) {
                            if (session.getJoinerPlayer() != null) {
                            } else {
                                session.setJoinerPlayer(player);
                                if (server.removeSession(session)) {
                                    server.notifyAllGame(session, Status.REMOVE);
                                    ServerListener listener = server.getListener(session.getCreator());

                                    gameHandler = new GameHandler(new GameBoard(new int[session.getRows()][session.getCols()], session.getN()));
                                    gameHandler.add(listener.communicator);
                                    gameHandler.add(this.communicator);
                                    gameHandler.setTimeout(session.getTimeout());
                                    if (session.getTimeout() > 0) {
                                        gameHandler.autoRun();
                                    }
                                    listener.setGameHandler(gameHandler);
                                    listener.communicator.write(Status.GAME_IS_JOINED);
                                    listener.communicator.write(player);
                                    listener.communicator.write(Status.CONTINUE);
                                }
                                System.out.println("Joint to Session: "+session.getName());
                            }
                        }
                        break;
                    case QUIT:
                        System.out.println(this.player+" send server request Quit");
                        Session sess = server.getSessionByCreatorPlayer(this.player);
                        if (sess != null) {
                            server.removeSession(sess);
                            server.notifyAllGame(sess, Status.REMOVE);
                        } 
                        sess = server.getSessinByJoinedPlayer(this.player);
                        if (sess != null) {
                            server.removeSession(sess);
                            server.notifyAllGame(sess, Status.REMOVE);
                        } 

                        if (gameHandler != null) {
                            gameHandler.remove();
                        } else {
                            communicator.write(Status.QUIT);
                        }
                        System.out.println(this.player+ " quited");
                        break;
                    default:
                        break;
                }
            } else if (obj instanceof Disk) {
                if (gameHandler != null) {
                    if (!gameHandler.isIsFinish()) {
                        gameHandler.run((Disk) obj);
                    }
                } else {
                    System.out.println("NULL");
                }
            }

        }
    }

    public void start() {
        Thread t = new Thread(this);
        t.start();
    }

    public void write(Object obj) {
        communicator.write(obj);
    }

    public void writeError(Status status) {
        communicator.write(status);
    }

    public Player getPlayer() {
        return player;
    }

    public void setGameHandler(GameHandler gameHandler) {
        this.gameHandler = gameHandler;
    }
}
