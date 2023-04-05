package edu.eci.arsw.bomberman.persistence.impl;

import edu.eci.arsw.bomberman.model.Player;
import edu.eci.arsw.bomberman.model.block;
import edu.eci.arsw.bomberman.model.elem;
import edu.eci.arsw.bomberman.persistence.BombermanPersistence;

import java.util.Set;

public class InMemoryBombermanPersistence implements BombermanPersistence {

    private final elem[][] gameboard = new elem[30][30];

    public InMemoryBombermanPersistence(){
        for (int i = 0; i != 30; i++) {
            for (int j = 0; j != 30; j++) {
                gameboard[i][j] = new block(i, j);
            }
        }
        gameboard[0][0] = new Player(0,0);
        gameboard[30][30] = new Player(30,30);
    }
    @Override
    public void movePlayer(Player p, int x, int y) {

    }

    @Override
    public Set<Integer> getPlayer(Player p) {
        return null;
    }

    @Override
    public void putBomb(int x, int y) {

    }
}
