package Componants;

import java.awt.Color;
import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author jasam + wissam
 */
public class Dot implements Serializable{
    public Color color;
    public int x;
    public int y;
    public boolean isStart;
    public Dot next;
    public Dot(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.isStart = false;
    }
    public Dot(Color color, int x, int y,Color color2, int x2, int y2) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.isStart = false;
        this.next = new Dot(color2, x2, y2);
        this.next.next = this;
    }
}

class DotCompByPosition implements Comparator<Dot>{

    @Override
    public int compare(Dot o1, Dot o2) {
        if(o1.x > o2.x)
            return 1;
        else if(o1.x < o2.x)
            return -1;
        else if(o1.y > o2.y)
            return 1;
        else if(o1.y < o2.y)
            return -1;
        else
            return 0;            
    }    
}
