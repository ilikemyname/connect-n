package ConnectN.server;

import ConnectN.component.Communicator;
import ConnectN.component.Disk;
import ConnectN.component.GameBoard;
import ConnectN.component.Status;
import java.util.Timer;

public class GameHandler {

    private Communicator client1;
    private Communicator client2;
    private GameBoard gameBoard;
    private int timeout;
    private int currentTurn = 0;
    private Timer timer;
    private boolean isFinish;

    public GameHandler(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        isFinish = false;
    }

    public void add(Communicator client) {
        if (client1 == null) {
            client1 = client;
        } else {
            client2 = client;
        }
    }

    public void remove() {
        client1.write(Status.QUIT);
        client2.write(Status.QUIT);

    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void autoRun() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new Timer();
        timer.schedule(new AutoDrop(this, client1, client2, gameBoard, currentTurn), timeout * 1000, timeout * 1000);

    }

    public void run(Disk disk) {

        this.cancelAutorun();
        Communicator current = currentTurn == 0 ? client1 : client2;
        Communicator other = current == client1 ? client2 : client1;
        int diskColor = currentTurn == 0 ? 10 : 20;
        Status status = gameBoard.diskDrop(diskColor, disk);
        other.write(disk);
        other.write(20);
        switch (status) {
            case CONTINUE:
                other.write(Status.CONTINUE);
                currentTurn = currentTurn == 0 ? 1 : 0;
                if (timeout > 0) {
                    this.autoRun();
                }
                break;
            case WIN:
                current.write(Status.WIN);
                other.write(Status.LOSE);
                this.cancelAutorun();
                setIsFinish(true);
                return;
            case TIE:
                current.write(Status.TIE);
                other.write(Status.TIE);
                this.cancelAutorun();
                setIsFinish(true);
                return;
            default:
                throw new RuntimeException("Illegal state");
        }

    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public void cancelAutorun() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    public boolean isIsFinish() {
        return isFinish;
    }

    public void setIsFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }
}
