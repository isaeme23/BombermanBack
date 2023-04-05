package edu.eci.arsw.bomberman.services;

import edu.eci.arsw.bomberman.model.Player;
import edu.eci.arsw.bomberman.persistence.BombermanPersistence;

import java.util.Set;

public class BombermanServices {

    BombermanPersistence bpp;

    public void movePlayer(Player p, int x, int y) {
        bpp.movePlayer(p,x,y);
    }

    public Set<Integer> getPlayer(Player p) {
        return bpp.getPlayer(p);
    }

    public void putBomb(int x, int y) {
        bpp.putBomb(x,y);

    }

}
