package edu.eci.arsw.bomberman.model;

public class Position {

    int x;
    int y;
    Object placeHolder = null;
    Status status = Status.EMPTY;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void placeBomb(Bomb placeHolder){
        this.placeHolder = placeHolder;
        setStatus(Status.BOMBPLAYER);
    }

    public void placePlayer(Player placeHolder){
        this.placeHolder = placeHolder;
        setStatus(Status.PLAYER);
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public Object getPlaceHolder() {
        return placeHolder;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
