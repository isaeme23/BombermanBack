package edu.eci.arsw.bomberman.persistence;

import edu.eci.arsw.bomberman.model.Player;
import java.util.*;
import java.util.Set;

public interface BombermanPersistence {

    public void movePlayer(Player p,int x, int y);

    public Set<Integer> getPlayer(Player p);

    public void putBomb(int x, int y);


}
