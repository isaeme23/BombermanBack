package edu.eci.arsw.bomberman.services;

import edu.eci.arsw.bomberman.model.Board;
import edu.eci.arsw.bomberman.model.Player;
import edu.eci.arsw.bomberman.model.Status;
import edu.eci.arsw.bomberman.persistence.BombermanPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BombermanServices {
    @Autowired
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

    public void setPlayerToPositionRight(String player){
        if (bp.getStatusPosition(bp.getPlayers().get(player).getX()+1, bp.getPlayers().get(player).getY()).equals(Status.EMPTY)){
            bp.setPlayerToPositionRight(player);
        } else if (bp.getStatusPosition(bp.getPlayers().get(player).getX()+1, bp.getPlayers().get(player).getY()).equals(Status.BOMB)){
            // c murio :(
        }

    }

    public void setPlayerToPositionLeft(String player){
        if (bp.getStatusPosition(bp.getPlayers().get(player).getX()-1, bp.getPlayers().get(player).getY()).equals(Status.EMPTY)){
            bp.setPlayerToPositionLeft(player);
        } else if (bp.getStatusPosition(bp.getPlayers().get(player).getX()-1, bp.getPlayers().get(player).getY()).equals(Status.BOMB)){
            // c murio :(
        }
    }

    public void setPlayerToPositionDown(String player){
        if (bp.getStatusPosition(bp.getPlayers().get(player).getX(), bp.getPlayers().get(player).getY()-1).equals(Status.EMPTY)){
            bp.setPlayerToPositionDown(player);
        } else if (bp.getStatusPosition(bp.getPlayers().get(player).getX(), bp.getPlayers().get(player).getY()-1).equals(Status.BOMB)){
            // c murio :(
        }
    }

    public void setPlayerToPositionUp(String player){
        if (bp.getStatusPosition(bp.getPlayers().get(player).getX(), bp.getPlayers().get(player).getY()+1).equals(Status.EMPTY)){
            bp.setPlayerToPositionUp(player);
        } else if (bp.getStatusPosition(bp.getPlayers().get(player).getX(), bp.getPlayers().get(player).getY()+1).equals(Status.BOMB)){
            // c murio :(
        }
    }

    public void setPlayerName(String player){
        if (bp.getPlayers().isEmpty()){
            bp.setPlayer1(player);
        } else{
            bp.setPlayer2(player);
        }

    }
}
