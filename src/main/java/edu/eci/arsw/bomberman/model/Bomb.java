package edu.eci.arsw.bomberman.model;

public class Bomb {
    private int x;
    private int y;

    public Bomb(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void explode() {
        this.x = -1;
        this.y = -1;
    }
}
