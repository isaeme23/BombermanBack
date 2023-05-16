package edu.eci.arsw.bomberman.persistence;

import edu.eci.arsw.bomberman.model.*;
import edu.eci.arsw.cache.CacheBomberman;
import jdk.jshell.Snippet;
import org.javatuples.Pair;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import java.util.concurrent.CopyOnWriteArrayList;
@Service
public class BombermanPersistence {

    ConcurrentHashMap<Pair<Integer, Integer>, Position> Positions = new ConcurrentHashMap<>();

    ConcurrentHashMap<String, Player> players = new ConcurrentHashMap<>();

    CacheBomberman cache = new CacheBomberman();

    public BombermanPersistence(){
        for (int x = 0; x < 21; x++){
            for (int y = 0; y < 13; y++){
                String key = "["+x+","+y+"]";
                if (!cache.getTemplate().hasKey(key)) {
                    cache.getOps().set(key, new Position(x,y).toString());
                }
                //System.out.println(cache.getOps().get(key));
                Positions.put(new Pair<>(x, y), new Position(x, y));
            }
        }
    }

    public Status getStatusPosition(int x, int y){
        Status status = null;
        if (cache.getOps().get("["+x+","+y+"]").contains("EMPTY")){
            status = Status.EMPTY;
        } else if (cache.getOps().get("["+x+","+y+"]").contains("BOMB")){
            status = Status.BOMB;
        } else if (cache.getOps().get("["+x+","+y+"]").contains("DEAD")) {
            status = Status.DEAD;
        } else if (cache.getOps().get("["+x+","+y+"]").contains("PLAYER")) {
            status = Status.PLAYER;
        } else if (cache.getOps().get("["+x+","+y+"]").contains("BOMBPLAYER")) {
            status = Status.BOMBPLAYER;
        }
        return Positions.get(new Pair<>(x,y)).getStatus();
    }

    public void setStatusAndBombPosition(int x, int y){
        Position p = new Position(x, y);
        p.placeBomb(new Bomb(x, y));
        cache.getOps().set("["+x+","+y+"]", p.toString());
        Positions.get(new Pair<>(x, y)).placeBomb(new Bomb(x, y));
    }

    public void setStatusAndPlayerPosition(int x, int y, Player player){
        Position p = new Position(x, y);
        p.placePlayer(player);
        cache.getOps().set("["+x+","+y+"]", p.toString());
        Positions.get(new Pair<>(x, y)).placePlayer(player);
        player.setXY(x, y);
    }

    public void setStatusAndPlayerPositionRight(Player player){
        Position p = new Position(player.getX()+1, player.getY());
        p.placePlayer(player);
        Position p1 = new Position(player.getX(), player.getY());
        cache.getOps().set("["+(player.getX()+1)+","+player.getY()+"]", p.toString());
        cache.getOps().set("["+player.getX()+","+player.getY()+"]", p1.toString());
        Positions.get(new Pair<>(player.getX()+1, player.getY())).placePlayer(player);
        player.moveRight();
    }

    public void setStatusAndPlayerPositionLeft(Player player){
        Position p = new Position(player.getX()-1, player.getY());
        p.placePlayer(player);
        Position p1 = new Position(player.getX(), player.getY());
        cache.getOps().set("["+(player.getX()-1)+","+player.getY()+"]", p.toString());
        cache.getOps().set("["+player.getX()+","+player.getY()+"]", p1.toString());
        Positions.get(new Pair<>(player.getX()-1, player.getY())).placePlayer(player);
        player.moveLeft();
    }

    public void setStatusAndPlayerPositionUp(Player player){
        Position p = new Position(player.getX(), player.getY()-1);
        p.placePlayer(player);
        Position p1 = new Position(player.getX(), player.getY());
        cache.getOps().set("["+player.getX()+","+(player.getY()-1)+"]", p.toString());
        cache.getOps().set("["+player.getX()+","+player.getY()+"]", p1.toString());
        Positions.get(new Pair<>(player.getX(), player.getY()-1)).placePlayer(player);
        player.moveUp();

    }

    public void setStatusAndPlayerPositionDown(Player player){
        Position p = new Position(player.getX(), player.getY()+1);
        p.placePlayer(player);
        Position p1 = new Position(player.getX(), player.getY());
        cache.getOps().set("["+player.getX()+","+(player.getY()+1)+"]", p.toString());
        cache.getOps().set("["+player.getX()+","+player.getY()+"]", p1.toString());
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
        Position p = new Position(x, y);
        p.setStatus(status);
        cache.getOps().set("["+x+","+y+"]", p.toString());
        Positions.get(new Pair<>(x, y)).setStatus(status);
    }

}
