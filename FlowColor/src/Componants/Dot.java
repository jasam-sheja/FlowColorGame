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

    public Dot(Color color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }
    
}

class DotCompByPosition implements Comparator<Dot>{

    @Override
    public int compare(Dot o1, Dot o2) {
        if(o1.x < o2.x)
            return 1;
        else if(o1.x < o2.x)
            return -1;
        else if(o1.y < o2.y)
            return 1;
        else if(o1.y > o2.y)
            return -1;
        else
            return 0;            
    }    
}
