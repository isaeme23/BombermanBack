package edu.eci.arsw.bomberman.persistence;

import edu.eci.arsw.bomberman.model.*;
import org.javatuples.Pair;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
@Service
public class BombermanPersistence {

    ConcurrentHashMap<Pair<Integer, Integer>, Position> Positions = new ConcurrentHashMap<>();

    ConcurrentHashMap<String, Player> players = new ConcurrentHashMap<>();

    public BombermanPersistence(){
        for (int x = 0; x < 21; x++){
            for (int y = 0; y < 13; y++){
                Positions.put(new Pair<>(x, y), new Position(x, y));
            }
        }
    }

    public Status getStatusPosition(int x, int y){
        return Positions.get(new Pair<>(x,y)).getStatus();
    }

    public void setStatusAndBombPosition(int x, int y){
        Positions.get(new Pair<>(x, y)).placeBomb(new Bomb(x, y));
    }

    public void setStatusAndPlayerPosition(int x, int y, Player player){
        Positions.get(new Pair<>(x, y)).placePlayer(player);
        player.setXY(x, y);
    }

    public void setStatusAndPlayerPositionRight(Player player){
        Positions.get(new Pair<>(player.getX()+1, player.getY())).placePlayer(player);
        player.moveRight();
    }

    public void setStatusAndPlayerPositionLeft(Player player){
        Positions.get(new Pair<>(player.getX()-1, player.getY())).placePlayer(player);
        player.moveLeft();
    }

    public void setStatusAndPlayerPositionUp(Player player){
        Positions.get(new Pair<>(player.getX(), player.getY()-1)).placePlayer(player);
        player.moveUp();

    }

    public void setStatusAndPlayerPositionDown(Player player){
        Positions.get(new Pair<>(player.getX(), player.getY()+1)).placePlayer(player);
        player.moveDown();
    }

    public ConcurrentHashMap<Pair<Integer, Integer>, Position> getBoard() {
        return Positions;
    }


    public void setBombToPosition(int x, int y) {
        setStatusAndBombPosition(x, y);
    }

    public void setPlayerToPositionRight(String player) {
        setStatusAndPlayerPositionRight(players.get(player));
    }
    public void setPlayerToPositionLeft(String player) {
        setStatusAndPlayerPositionLeft(players.get(player));
    }

    public void setPlayerToPositionUp(String player) {
        setStatusAndPlayerPositionUp(players.get(player));
    }

    public void setPlayerToPositionDown(String player) {
        setStatusAndPlayerPositionDown(players.get(player));
    }

    public ConcurrentHashMap<String, Player> getPlayers() {
        return players;
    }
    public void arrangePlayer(String name, int x, int y, String color){
        players.put(name, new Player(x, y));
        players.get(name).setColor(color);
        setStatusAndPlayerPosition(x, y, players.get(name));
    }

    public void setStatusPosition(int x, int y, Status status){
        Positions.get(new Pair<>(x, y)).setStatus(status);
    }

}
