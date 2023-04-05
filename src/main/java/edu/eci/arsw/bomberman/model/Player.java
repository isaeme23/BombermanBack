package edu.eci.arsw.bomberman.model;

public class Player {

    private int x;
    private int y;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setXY(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void putBomb() {
        Bomb b1 = new Bomb(x,y);
    }

    public void moveUp() {
        this.x = x;
        this.y = y + 1;
    }

    public void moveDown() {
        this.x = x;
        this.y = y - 1;
    }

    public void moveLeft() {
        this.x = x - 1;
        this.y = y;
    }

    public void moveRight() {
        this.x = x + 1;
        this.y = y;
    }

}
