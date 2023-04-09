package edu.eci.arsw.bomberman.persistence;

import edu.eci.arsw.bomberman.model.Board;
import edu.eci.arsw.bomberman.model.Player;
import edu.eci.arsw.bomberman.model.Status;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
@Service
public class BombermanPersistence {

    Board board;

    ConcurrentHashMap<String, Player> players = new ConcurrentHashMap<>();

    Player player1;
    Player player2;

    public BombermanPersistence() {
        board = newBoard();

        player1 = new Player(0, 0);
        board.setStatusAndPlayerPosition(0, 0, player1);

        player2 = new Player(0, 0);
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

    public void setPlayerToPositionRight(String player) {
        board.setStatusAndPlayerPositionRight(players.get(player));
    }
    public void setPlayerToPositionLeft(String player) {
        board.setStatusAndPlayerPositionLeft(players.get(player));
    }

    public void setPlayerToPositionUp(String player) {
        board.setStatusAndPlayerPositionUp(players.get(player));
    }

    public void setPlayerToPositionDown(String player) {
        board.setStatusAndPlayerPositionDown(players.get(player));
    }

    public ConcurrentHashMap<String, Player> getPlayers() {
        return players;
    }

    public void setPlayer1(String namePlayer1){
        players.put(namePlayer1, player1);
    }

    public void setPlayer2(String namePlayer2){
        players.put(namePlayer2, player2);
    }
}
