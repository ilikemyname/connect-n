package ConnectN.server;

import ConnectN.component.Communicator;
import ConnectN.component.Disk;
import ConnectN.component.GameBoard;
import ConnectN.component.Status;
import java.util.Random;
import java.util.TimerTask;

/**
 *
 * @author phuy
 */
public class AutoDrop extends TimerTask {

    Communicator client1, client2;
    GameBoard gameBoard;
    int diskColor, currentTurn;
    GameHandler gameHandler;

    public AutoDrop(GameHandler gameHandler, Communicator client1, Communicator client2, GameBoard gameBoard, int currentTurn) {
        this.gameHandler = gameHandler;
        this.client1 = client1;
        this.client2 = client2;
        this.gameBoard = gameBoard;
        this.currentTurn = currentTurn;
    }

    @Override
    public void run() {
        Random r = new Random();
        int randomNum = r.nextInt(r.nextInt(1000));
        randomNum = randomNum % gameBoard.getBoard()[0].length;
        while (gameBoard.isColFull(randomNum)) {
            randomNum = r.nextInt(gameBoard.getBoard()[0].length);
        }
        diskColor = this.currentTurn == 0 ? 10 : 20;
        int y = gameBoard.dropDiscAtColumn(randomNum, diskColor);
        Communicator current = currentTurn == 0 ? client1 : client2;
        Communicator other = current == client1 ? client2 : client1;

        
        gameHandler.setCurrentTurn((currentTurn == 0) ? 1 : 0);
        other.write(new Disk(randomNum, y));
        other.write(20);
        current.write(new Disk(randomNum, y));
        current.write(10);
        Status status = gameBoard.getStatus(diskColor);
        switch (status) {
            case CONTINUE:
                other.write(Status.CONTINUE);
                currentTurn = currentTurn == 0 ? 1 : 0;
                break;
            case WIN:
                current.write(Status.WIN);
                other.write(Status.LOSE);
                gameHandler.cancelAutorun();
                gameHandler.setIsFinish(true);
                return;
            case TIE:
                current.write(Status.TIE);
                other.write(Status.TIE);
                gameHandler.cancelAutorun();
                gameHandler.setIsFinish(true);
                return;
            default:
                break;
        }        
    }
}
