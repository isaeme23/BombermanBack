package edu.eci.arsw.bomberman.model;

import org.javatuples.Pair;

import java.util.concurrent.ConcurrentHashMap;

public class Board {

    ConcurrentHashMap<Pair<Integer, Integer>, Position> Positions = new ConcurrentHashMap<>();

    public Board(){
        for (int x = 0; x < 10; x++){
            for (int y = 0; y < 10; y++){
                Positions.put(new Pair<>(x, y), new Position(x, y));
            }
        }
    }

    public Status getStatusPosition(int x, int y){
        return Positions.get(new Pair<>(x,y)).getStatus();
    }

    public void setStatusAndBombPosition(int x, int y){
        if (getStatusPosition(x, y).equals(Status.EMPTY)) {
            Positions.get(new Pair<>(x, y)).placeBomb(new Bomb(x, y));
        }
    }

    public void setStatusAndPlayerPosition(int x, int y, Player player){
        if (getStatusPosition(x, y).equals(Status.EMPTY)){
            Positions.get(new Pair<>(x, y)).placePlayer(player);
        }
    }
}