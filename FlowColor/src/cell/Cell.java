/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cell;

import cell.componants.*;
import java.awt.Color;

/**
 *
 * @author DigitalNet
 */
public class Cell {

    private Componant componant;
    private final Bridge bridge;

    public final static byte NORTH = 0;
    public final static byte SOUTH = 3;
    public final static byte EAST = 1;
    public final static byte WEST = 2;

    public Cell(Componant componant, Bridge bridge) {
        this.componant = componant;
        this.bridge = bridge;
        if ((Bridge) componant != null || componant == null) {
            throw new IllegalArgumentException();
        }
    }

    public Cell(Componant componant) {
        this(componant, null);
    }

    public void addComponant(Removable componant) {
        if (componant == null) {
            throw new IllegalArgumentException();
        }
        if (this.containBridge()) {
            if (this.bridge.canHold(componant)) {
                this.bridge.addPipe((Pipe) componant);
            } else if (this.bridge.canGoUnder(componant)) {
                this.componant = (Componant) componant;
            }
        } else {
            this.componant = (Componant) componant;
        }
    }

    public void removeComponant() {
        if ((Removable) this.componant == null) {
            return;
        }
        this.componant = new Nothing();
    }

    public void removeFromBridge() {
        this.bridge.removePipe();
    }

    public void removePipe(Pipe pipe) {
        if (this.containBridge()) {
            if (this.componant.equalsType(pipe)) {
                this.removeComponant();
            } else if (this.bridge.getPipe().equalsType(pipe)) {
                this.bridge.removePipe();
            }
        } else {
            this.removeComponant();
        }
    }

    public boolean containPipe() {
        boolean contain = false;
        if (this.containBridge()) {
            contain = bridge.containPipe();
        }
        return Pipe.isPipe(componant) || contain;
    }

    public boolean containBridge() {
        return this.bridge != null;
    }

    public boolean containDot() {
        return Dot.isDot(componant);
    }
    
    public boolean canHold(Removable componant) {
        if ((Nothing) componant != null) {
            return true;
        }
        if (this.containBridge()) {
            return this.bridge.canHold(componant) || this.bridge.canGoUnder(componant);
        } else {
            return this.componant.canHold(componant);
        }
    }

    public boolean isFull() {
        boolean full = (Nothing) this.componant == null;
        if (this.bridge != null) {
            return this.bridge.containPipe() && full;
        } else {
            return full;
        }
    }

    public Color getComponantColor() {
        return this.componant.getColor();
    }

    public Color getBridgeColor() {
        if (this.containBridge()) {
            return this.bridge.getColor();
        } else {
            return Color.WHITE;
        }
    }

    public byte getComponantId(){
        return this.componant.getId();
    }
    
    public byte getBridgeId(){
        return this.bridge.getId();
    }
    /**
     * get the pipe that go form north ,west ,east or south
     *
     * @param direction
     * @return
     */
    public Pipe getPipe(byte direction) {
        if (!this.containPipe()) {
            return null;
        }
        Pipe temp ;
        if (direction == NORTH || direction == SOUTH) {
            if (Pipe.isPipe(this.componant)) {
                temp = (Pipe) this.componant;
                if (temp.isVertical()) {
                    return temp;
                } else if ((temp.getId() & 1) == (direction >> 1)) {
                    return temp; //here return cornor
                }
            }
            if (this.containBridge()) {
                if (this.bridge.containPipe()) {
                    temp = this.bridge.getPipe();
                    if (temp.isVertical()) {
                        return temp;    
                    } else if ((temp.getId() & 1) == (direction >> 1)) {
                        return temp; //here return cornor
                    }
                }
            }
        } else if (direction == EAST || direction == WEST) {
            if (Pipe.isPipe(this.componant)) {
                temp = (Pipe) this.componant;
                if (temp.isVertical()) {
                    return temp;
                } else if ((temp.getId() & 1)>>1 == (direction >> 1)) {
                    return temp; //here return cornor
                }
            }
            if (this.containBridge()) {
                if (this.bridge.containPipe()) {
                    temp = this.bridge.getPipe();
                    if (temp.isVertical()) {
                        return temp;    
                    } else if ((temp.getId() & 1)>>1 == (direction >> 1)) {
                        return temp; //here return cornor
                    }
                }
            }
        }
        return null;
    }
}
