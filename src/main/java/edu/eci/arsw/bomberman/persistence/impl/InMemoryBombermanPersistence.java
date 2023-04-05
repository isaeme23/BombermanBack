package edu.eci.arsw.bomberman.persistence.impl;

import edu.eci.arsw.bomberman.model.Player;
import edu.eci.arsw.bomberman.persistence.BombermanPersistence;

import java.util.Set;

public class InMemoryBombermanPersistence implements BombermanPersistence {
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
