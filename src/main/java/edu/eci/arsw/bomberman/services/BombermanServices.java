package edu.eci.arsw.bomberman.services;

import edu.eci.arsw.bomberman.model.Board;
import edu.eci.arsw.bomberman.model.Player;
import edu.eci.arsw.bomberman.model.Status;
import edu.eci.arsw.bomberman.persistence.BombermanPersistence;

public class BombermanServices {

    BombermanPersistence bp;

    public Board newBoard(){
        return bp.newBoard();
    }

    public Board getBoard() {
        return bp.getBoard();
    }

    public void setBombToPosition(int x, int y){
        bp.setBombToPosition(x, y);
    }

    public void setPlayerToPosition(int x, int y, Player player){
        bp.setPlayerToPosition(x, y, player);
    }

}
