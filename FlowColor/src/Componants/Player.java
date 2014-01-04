package Componants;

import java.io.Serializable;

/**
 *
 * @author jasam
 */
public class Player implements Serializable, Comparable<Player> {

    private final String name;
    private final int moves;
    private final long time;

    public Player(String name, int moves, long time) {
        this.name = name;
        this.moves = moves;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public int getMoves() {
        return moves;
    }

    public long getTime() {
        return time;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Player) {
            return this.name.equalsIgnoreCase(((Player) obj).name);
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Player o) {
        return this.time > o.time ? 2
                : this.time < o.time ? -2
                : this.moves > o.moves ? 1
                : this.moves < o.moves ? -1 : 0;
    }

}
