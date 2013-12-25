
package Componants;

import java.io.Serializable;

/**
 *
 * @author jasam + wissam
 */
public class Level implements Serializable{
    private Dot[] dots;
    private Bridge bridge;
    private Hall hall;
    private int length;

    public Level(Dot[] dots, Bridge bridge, Hall hall) {
        this.dots = dots;
        this.bridge = bridge;
        this.hall = hall;
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
    
}
