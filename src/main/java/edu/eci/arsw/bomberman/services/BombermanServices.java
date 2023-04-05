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
        if (bp.getStatusPosition(x, y).equals(Status.EMPTY)) {
            bp.setBombToPosition(x, y);
        }
    }

    public void setPlayerToPositionRight(Player player){
        if (bp.getStatusPosition(player.getX()+1, player.getY()).equals(Status.EMPTY)){
            bp.setPlayerToPositionRight(player);
        } else if (bp.getStatusPosition(player.getX()+1, player.getY()).equals(Status.BOMB)){
            // c murio :(
        }

    }

    public void setPlayerToPositionLeft(Player player){
        if (bp.getStatusPosition(player.getX()-1, player.getY()).equals(Status.EMPTY)){
            bp.setPlayerToPositionLeft(player);
        } else if (bp.getStatusPosition(player.getX()-1, player.getY()).equals(Status.BOMB)){
            // c murio :(
        }
    }

    public void setPlayerToPositionDown(Player player){
        if (bp.getStatusPosition(player.getX(), player.getY()-1).equals(Status.EMPTY)){
            bp.setPlayerToPositionDown(player);
        } else if (bp.getStatusPosition(player.getX(), player.getY()-1).equals(Status.BOMB)){
            // c murio :(
        }
    }

    public void setPlayerToPositionUp(Player player){
        if (bp.getStatusPosition(player.getX(), player.getY()+1).equals(Status.EMPTY)){
            bp.setPlayerToPositionUp(player);
        } else if (bp.getStatusPosition(player.getX(), player.getY()+1).equals(Status.BOMB)){
            // c murio :(
        }
    }
}
