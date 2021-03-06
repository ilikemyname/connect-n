package ConnectN.component;

import java.io.Serializable;

/**
 *
 * @author phuy
 */
public class GameBoard {

    private int[][] board;
    private int numWin;

    public GameBoard(int[][] board, int numWin) {
        this.board = board;
        this.numWin = numWin;
    }

    public Status diskDrop(int diskColor, Disk disk) {
        board[disk.getX()][disk.getY()] = diskColor;
        return getStatus(diskColor);
    }

    public int dropDiscAtColumn(int x, int diskColor) {
        for (int i = board.length - 1; i >= 0; i--) {
            if (board[i][x] == 0) {
                board[i][x] = diskColor;
                return i;
            }
        }
        return -1;
    }

    public Status getStatus(int diskColor) {
        synchronized (this) {
            return isWin(diskColor)
                    ? Status.WIN
                    : isFull() ? Status.TIE
                    : Status.CONTINUE;
        }
    }

    public boolean isFull() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }

        }
        return true;
    }

    public boolean isWin(int diskColor) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                if (i <= board.length - numWin && j <= board[i].length - numWin) {
                    int count = 0;
                    for (int k = 0; k < numWin; k++) {
                        if (board[i + k][j + k] == diskColor) {
                            count++;
                        }
                    }
                    if (count == numWin) {
                        return true;
                    }
                }

                if (i >= numWin - 1 && j <= board[i].length - numWin) {
                    int count = 0;
                    for (int k = 0; k < numWin; k++) {
                        if (board[i - k][j + k] == diskColor) {
                            count++;
                        }
                    }
                    if (count == numWin) {
                        return true;
                    }
                }

                if (j <= board[i].length - numWin) {
                    int count = 0;
                    for (int k = 0; k < numWin; k++) {
                        if (board[i][j + k] == diskColor) {
                            count++;
                        }
                    }
                    if (count == numWin) {
                        return true;
                    }
                }

                if (i < board.length + 1 - numWin) {
                    int count = 0;
                    for (int k = 0; k < numWin; k++) {
                        if (board[i + k][j] == diskColor) {
                            count++;
                        }
                    }
                    if (count == numWin) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isColFull(int x) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][x] == 0) {
                return false;
            }
        }
        return true;
    }

    public int[][] getBoard() {
        return board;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                str += " " + board[i][j] + " ";

            }
            str += "\n";
        }
        return str;
    }
}
