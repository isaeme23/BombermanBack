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

import java.io.*;
import java.sql.SQLOutput;
import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import java.util.concurrent.CopyOnWriteArrayList;
@Service
public class BombermanPersistence {

    ConcurrentHashMap<Pair<Integer, Integer>, Position> Positions = new ConcurrentHashMap<>();

    ConcurrentHashMap<String, Player> players = new ConcurrentHashMap<>();

    CacheBomberman cache = new CacheBomberman();
    Board b = new Board();

    public BombermanPersistence(){
        for (int x = 0; x < 21; x++){
            for (int y = 0; y < 13; y++){
                /*String key = "["+x+","+y+"]";
                if (!cache.getTemplate().hasKey(key)) {
                    cache.getOps().set(key, new Position(x,y).toString());
                }*/
                Positions.put(new Pair<>(x, y), new Position(x, y));
            }
        }
        cache.getOps().set("tablero", toString(Positions));
    }

    public Status getStatusPosition(int x, int y){
        Positions = fromString(cache.getOps().get("tablero"));
        return Positions.get(new Pair<>(x,y)).getStatus();
    }

    public void setStatusAndBombPosition(int x, int y){
        Positions = fromString(cache.getOps().get("tablero"));
        Positions.get(new Pair<>(x, y)).placeBomb(new Bomb(x, y));
        cache.getOps().set("tablero", toString(Positions));
    }

    public void setStatusAndPlayerPosition(int x, int y, Player player){
        ConcurrentHashMap<Pair<Integer, Integer>, Position> positionsplayers = new ConcurrentHashMap<>();
        positionsplayers = fromString(cache.getOps().get("tablero"));
        positionsplayers.get(new Pair<>(x, y)).placePlayer(player);
        System.out.println(toString(positionsplayers));
        System.out.println(positionsplayers.get(new Pair<>(1,1)).getStatus());
        cache.getOps().set("tablero", toString(positionsplayers));
        player.setXY(x, y);
    }

    public void setStatusAndPlayerPositionRight(Player player){
        Positions = fromString(cache.getOps().get("tablero"));
        Positions.get(new Pair<>(player.getX()+1, player.getY())).placePlayer(player);
        cache.getOps().set("tablero", toString(Positions));
        player.moveRight();
    }

    public void setStatusAndPlayerPositionLeft(Player player){
        Positions = fromString(cache.getOps().get("tablero"));
        Positions.get(new Pair<>(player.getX()-1, player.getY())).placePlayer(player);
        cache.getOps().set("tablero", toString(Positions));
        player.moveLeft();
    }

    public void setStatusAndPlayerPositionUp(Player player){
        Positions = fromString(cache.getOps().get("tablero"));
        Positions.get(new Pair<>(player.getX(), player.getY()-1)).placePlayer(player);
        cache.getOps().set("tablero", toString(Positions));
        player.moveUp();

    }

    public void setStatusAndPlayerPositionDown(Player player){
        Positions = fromString(cache.getOps().get("tablero"));
        Positions.get(new Pair<>(player.getX(), player.getY()+1)).placePlayer(player);
        cache.getOps().set("tablero", toString(Positions));
        player.moveDown();
    }

    public ConcurrentHashMap<Pair<Integer, Integer>, Position> getBoard() {
        return fromString(cache.getOps().get("tablero"));
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
        Positions = fromString(cache.getOps().get("tablero"));
        Positions.get(new Pair<>(x, y)).setStatus(status);
        cache.getOps().set("tablero", toString(Positions));
    }
    private static ConcurrentHashMap<Pair<Integer, Integer>, Position> fromString( String s ){
        try { byte [] data = Base64.getDecoder().decode( s );
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream( data ) );
            ConcurrentHashMap<Pair<Integer, Integer>, Position> o = (ConcurrentHashMap<Pair<Integer, Integer>, Position>) ois.readObject();
            ois.close();
            return o;
        } catch (IOException | ClassNotFoundException e){
            return null;
        }
    }
    private static String toString( Serializable o ) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream( baos );
            oos.writeObject( o );
            oos.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException e){
            return null;
        }
    }
}
