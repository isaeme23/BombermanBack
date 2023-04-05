package edu.eci.arsw.bomberman.model;

public class block implements elem{

    private int x;
    private int y;

    public block(int x, int y){
        place(x,y);
    }

    @Override
    public void place(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
