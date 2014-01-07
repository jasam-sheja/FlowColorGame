package Componants;

import java.io.Serializable;

/**
 *
 * @author jasam + wissam
 */
public class Level implements Serializable {

    private final Dot[] dots;
    private final Bridge bridge;
    private final Hall hall;
    private final int length;
    private final int levelNumber;

    public Level(Dot[] dots, Bridge bridge, Hall hall, int length, int levelNumber) {
        this.dots = dots;
        this.bridge = bridge;
        this.hall = hall;
        this.length = length;
        this.levelNumber = levelNumber;
    }

    public Dot[] getDots() {
        return dots;
    }

    public Bridge getBridge() {
        return bridge;
    }

    public Hall getHall() {
        return hall;
    }

    public int getLength() {
        return length;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Level other = (Level) obj;
        return this.levelNumber == other.levelNumber;
    }

}
