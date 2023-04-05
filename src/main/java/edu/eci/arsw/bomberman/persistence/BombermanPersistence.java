package edu.eci.arsw.bomberman.persistence;

import edu.eci.arsw.bomberman.model.Board;
import edu.eci.arsw.bomberman.model.Player;
import edu.eci.arsw.bomberman.model.Status;

public class BombermanPersistence {

    Board board;

    public BombermanPersistence() {
        board = newBoard();

        Player player1 = new Player(0, 0);
        board.setStatusAndPlayerPosition(0, 0, player1);

        Player player2 = new Player(0, 0);
        board.setStatusAndPlayerPosition(9, 9, player2);
    }

    public Board newBoard() {
        board = new Board();
        return board;
    }

    public Board getBoard() {
        return board;
    }

    public Status getStatusPosition(int x, int y) {
        return board.getStatusPosition(x, y);
    }

    public void setBombToPosition(int x, int y) {
        board.setStatusAndBombPosition(x, y);
    }

    public void setPlayerToPositionRight(Player player) {
        board.setStatusAndPlayerPositionRight(player);
    }
    public void setPlayerToPositionLeft(Player player) {
        board.setStatusAndPlayerPositionLeft(player);
    }

    public void setPlayerToPositionUp(Player player) {
        board.setStatusAndPlayerPositionUp(player);
    }

    public void setPlayerToPositionDown(Player player) {
        board.setStatusAndPlayerPositionDown(player);
    }
}
