package Componants;

import java.io.Serializable;

/**
 *
 * @author jasam + wissam
 */
public class Level implements Serializable {

    private Dot[] dots;
    private Bridge bridge;
    private Hall hall;
    private int length;
    private int levelNumber;

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

}
