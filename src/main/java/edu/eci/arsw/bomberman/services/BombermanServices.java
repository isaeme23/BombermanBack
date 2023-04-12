package edu.eci.arsw.bomberman.services;

import edu.eci.arsw.bomberman.model.*;
import edu.eci.arsw.bomberman.persistence.BombermanPersistence;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BombermanServices {
    @Autowired
    BombermanPersistence bp;

    public ConcurrentHashMap<Pair<Integer, Integer>, Position> getBoard() {
        return bp.getBoard();
    }

    public ConcurrentHashMap<String, Player> getplayers() {
        return bp.getPlayers();
    }

    public void movement(String player, String movement){
        if (movement.equals("Right")){
            setPlayerToPositionRight(player);
        } else if (movement.equals("Up")){
            setPlayerToPositionUp(player);
        } else if (movement.equals("Down")){
            setPlayerToPositionDown(player);
        } else if (movement.equals("Left")){
            setPlayerToPositionLeft(player);
        } else if (movement.equals("Bomb")){
            setBombToPosition(getplayers().get(player).getX(), getplayers().get(player).getY());
        }
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
