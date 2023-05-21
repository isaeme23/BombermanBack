package edu.eci.arsw.bomberman.model;

import java.io.Serializable;

public class Bomb implements Serializable {
    private int x;
    private int y;

    public Bomb(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void explode() {
        this.x = -1;
        this.y = -1;
    }

    @Override
    public String toString() {
        return "Bomb{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
