

package Componants;

import java.awt.Color;

/**
 *
 * @author jasam + wissam
 */
public class Cell {
    public enum Side{
        UP,
        DOWN,
        RIGHT,
        LEFT;        
    };
    public enum State{
        EMPTY,
        ENTERD,
        LEAVED,
        BLOCKED,
        CROSS_ENTERD,
        CROSS_LEAVED;        
    }
    /* Attributs */
    //<editor-fold defaultstate="collapsed" desc="Here the Private Attributs ">
    
    private State up;
    private State down;
    private State left;
    private State right;
    
    private boolean isCross;
    private boolean isFull;
    
    /**
     * if there is Cross this should represent the horizontal pipe color
     * else this is the pipe in this cell color
     */
    private Color color; 
    /**
     * if there is Cross this should represent the vertical pipe color
     * else this should be null
     */
    private Color crossColor;
    
    Dot dot;
    
    //</editor-fold>
    
    public Cell(boolean isHall,boolean isCross, Dot dot) {
        this.isCross = isCross;
        this.dot = dot;
        if(isHall)
        {
            this.up = State.BLOCKED;
            this.down = State.BLOCKED;
            this.right = State.BLOCKED;
            this.left = State.BLOCKED;
        }
    }
    public void add(Side side,Color color){
        throw new UnsupportedOperationException();
    }
    public Side remove(Side side){
        throw new UnsupportedOperationException();
    }
}
