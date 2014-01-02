package ComponantsTest;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author jasam + wissam
 */
public class Cell {

    public enum Side {

        UP,
        DOWN,
        RIGHT,
        LEFT;

        public Side Opposite() {
            switch (this) {
                case UP:
                    return DOWN;
                case DOWN:
                    return UP;
                case RIGHT:
                    return LEFT;
                case LEFT:
                    return RIGHT;
            }
            throw new IllegalArgumentException("this should not be thrown");
        }

        public Side Next() {
            switch (this) {
                case UP:
                    return RIGHT;
                case DOWN:
                    return LEFT;
                case RIGHT:
                    return DOWN;
                case LEFT:
                    return UP;
            }
            throw new IllegalArgumentException("this should not be thrown");
        }
    };

    public enum State {

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

    private final boolean isCross;
    private boolean isFull;

    /**
     * if there is Cross this should represent the horizontal pipe color else
     * this is the pipe in this cell color
     */
    private Color color;
    /**
     * if there is Cross this should represent the vertical pipe color else this
     * should be null
     */
    private Color crossColor;

    Dot dot;

    //</editor-fold>
    public Cell(boolean isHall, boolean isCross, Dot dot) {
        this(isHall, isCross, dot, false, false, false, false);
    }

    public Cell(boolean isHall, boolean isCross, Dot dot, boolean isUpBlocked, boolean isDownBlocked, boolean isRightBlocked, boolean isLeftBlocked) {
        this.isCross = isCross;
        this.dot = dot;
        if (isCross && dot != null) {
            throw new IllegalArgumentException();
        }
        if (dot != null) {
            this.color = dot.color;
            this.crossColor = dot.color;
        }
        if (isHall) {
            this.up = State.BLOCKED;
            this.down = State.BLOCKED;
            this.right = State.BLOCKED;
            this.left = State.BLOCKED;
        } else {
            this.up = isUpBlocked ? State.BLOCKED : State.EMPTY;
            this.down = isDownBlocked ? State.BLOCKED : State.EMPTY;
            this.right = isRightBlocked ? State.BLOCKED : State.EMPTY;
            this.left = isLeftBlocked ? State.BLOCKED : State.EMPTY;
        }
    }

    public void add(Side side, Color color) {
        if (isFull) {
            return;
        }
        if (side == null) {
            return;
        }
        State oldValue = up;
        State newValue = up;
        if (isCross) {
            switch (side) {
                case UP:
                    oldValue = up;
                    if (up == State.BLOCKED) {
                        return;
                    } else if (down == State.CROSS_ENTERD) {
                        up = State.CROSS_LEAVED;
                    } else {
                        up = State.CROSS_ENTERD;
                    }
                    newValue = up;
                    this.crossColor = color;
                    break;
                case DOWN:
                    oldValue = down;
                    if (down == State.BLOCKED) {
                        return;
                    } else if (up == State.CROSS_ENTERD) {
                        down = State.CROSS_LEAVED;
                    } else {
                        down = State.CROSS_ENTERD;
                    }
                    newValue = down;
                    this.crossColor = color;
                    break;
                case RIGHT:
                    oldValue = right;
                    if (right == State.BLOCKED) {
                        return;
                    } else if (left == State.CROSS_ENTERD) {
                        right = State.CROSS_LEAVED;
                    } else {
                        right = State.CROSS_ENTERD;
                    }
                    newValue = right;
                    this.color = color;
                    break;
                case LEFT:
                    oldValue = left;
                    if (left == State.BLOCKED) {
                        return;
                    } else if (right == State.CROSS_ENTERD) {
                        left = State.CROSS_LEAVED;
                    } else {
                        left = State.CROSS_ENTERD;
                    }
                    newValue = left;
                    this.color = color;
                    break;
            }
            isFull = up != State.EMPTY && down != State.EMPTY && right != State.EMPTY && left != State.EMPTY;
        } else {
            this.color = color;
            this.crossColor = color;
            switch (side) {
                case UP:
                    oldValue = up;
                    if (up == State.BLOCKED) {
                        return;
                    } else if (hasDot()) {
                        if (!dot.next.isStart) {
                            up = State.LEAVED;
                            dot.isStart = true;
                        } else {
                            up = State.ENTERD;
                        }
                    } else if (down == State.ENTERD
                            || right == State.ENTERD
                            || left == State.ENTERD) {
                        up = State.LEAVED;
                    } else {
                        up = State.ENTERD;
                    }
                    newValue = up;
                    break;
                case DOWN:
                    oldValue = down;
                    if (down == State.BLOCKED) {
                        return;
                    } else if (hasDot()) {
                        if (!dot.next.isStart) {
                            down = State.LEAVED;
                            dot.isStart = true;
                        } else {
                            down = State.ENTERD;
                        }
                    } else if (up == State.ENTERD
                            || right == State.ENTERD
                            || left == State.ENTERD) {
                        down = State.LEAVED;
                    } else {
                        down = State.ENTERD;
                    }
                    newValue = down;
                    break;
                case RIGHT:
                    oldValue = right;
                    if (right == State.BLOCKED) {
                        return;
                    } else if (hasDot()) {
                        if (!dot.next.isStart) {
                            right = State.LEAVED;

                            dot.isStart = true;
                        } else {
                            right = State.ENTERD;
                        }
                    } else if (down == State.ENTERD
                            || up == State.ENTERD
                            || left == State.ENTERD) {
                        right = State.LEAVED;
                    } else {
                        right = State.ENTERD;
                    }
                    newValue = right;
                    break;
                case LEFT:
                    oldValue = left;
                    if (left == State.BLOCKED) {
                        return;
                    } else if (hasDot()) {
                        if (!dot.next.isStart) {
                            left = State.LEAVED;
                            dot.isStart = true;
                        } else {
                            left = State.ENTERD;
                        }
                    } else if (down == State.ENTERD
                            || right == State.ENTERD
                            || up == State.ENTERD) {
                        left = State.LEAVED;
                    } else {
                        left = State.ENTERD;
                    }
                    newValue = left;
                    break;
            }
            boolean entered = false, leaved = false;
            int ne = 0,nl = 0;
            if (up == State.ENTERD) {
                ne++;
            } else if (up == State.LEAVED) {
                nl++;
            }
            if (down == State.ENTERD){
                ne++;
            }                
            else if(down == State.LEAVED) {
                nl++;
            }
            if (right == State.ENTERD){
                ne++;
            }
            else if(right == State.LEAVED) {
                nl++;
            }
            if (left == State.ENTERD){
                ne++;
            }
            else if(left == State.LEAVED) {
                nl++;
            }
            if (ne == 1 && nl == 1) {
                isFull = true;
            } else if (ne > 1 || nl > 1) {
                throw new IllegalArgumentException("there connot be more than one enter and one leave");
            } else {
                isFull = false;
            }
        }

//        changeSupport.firePropertyChange(side.toString(), oldValue, newValue);
        changeSupport.firePropertyChange(side.toString(), this, newValue);
    }

    public void remove(Side side) {
        if (side == null) {
            return;
        }
        State oldValue = up;
        State newValue = up;
        switch (side) {
            case UP:
                oldValue = up;
                if (up != State.BLOCKED) {
                    up = State.EMPTY;
                    isFull = false;
                }
                newValue = up;
                break;
            case DOWN:
                oldValue = down;
                if (down != State.BLOCKED) {
                    down = State.EMPTY;
                    isFull = false;
                }
                newValue = down;
                break;
            case RIGHT:
                oldValue = right;
                if (right != State.BLOCKED) {
                    right = State.EMPTY;
                    isFull = false;
                }
                newValue = right;
                break;
            case LEFT:
                oldValue = left;
                if (left != State.BLOCKED) {
                    left = State.EMPTY;
                    isFull = false;
                }
                newValue = left;
                break;
        }
        if (hasDot()) {
            dot.isStart = false;
        }
        if (isEmpty() && !hasDot()) {
            this.color = this.crossColor = null;
        }
//        changeSupport.firePropertyChange(side.toString(), oldValue, newValue);
        changeSupport.firePropertyChange(side.toString(), this, newValue);
    }

    public Side getEntered() {
        if (up == State.ENTERD) {
            return Side.UP;
        } else if (down == State.ENTERD) {
            return Side.DOWN;
        } else if (right == State.ENTERD) {
            return Side.RIGHT;
        } else if (left == State.ENTERD) {
            return Side.LEFT;
        } else {
            return null;
        }
    }

    public Side getLeaved() {
        if (up == State.LEAVED) {
            return Side.UP;
        } else if (down == State.LEAVED) {
            return Side.DOWN;
        } else if (right == State.LEAVED) {
            return Side.RIGHT;
        } else if (left == State.LEAVED) {
            return Side.LEFT;
        } else {
            return null;
        }
    }

    public Side getEntered(boolean Horizontal) {
        if (Horizontal) {
            if (right == State.ENTERD
                    || right == State.CROSS_ENTERD) {
                return Side.RIGHT;
            } else if (left == State.ENTERD
                    || left == State.CROSS_ENTERD) {
                return Side.LEFT;
            }
        } else {
            if (up == State.ENTERD
                    || up == State.CROSS_ENTERD) {
                return Side.UP;
            } else if (down == State.ENTERD
                    || down == State.CROSS_ENTERD) {
                return Side.DOWN;
            }
        }
        return null;
    }

    public Side getLeaved(boolean Horizontal) {
        if (Horizontal) {
            if (right == State.LEAVED
                    || right == State.CROSS_LEAVED) {
                return Side.RIGHT;
            } else if (left == State.LEAVED
                    || left == State.CROSS_LEAVED) {
                return Side.LEFT;
            }
        } else {
            if (up == State.LEAVED
                    || up == State.CROSS_LEAVED) {
                return Side.UP;
            } else if (down == State.LEAVED
                    || down == State.CROSS_LEAVED) {
                return Side.DOWN;
            }
        }
        return null;
    }

    public boolean isCross() {
        return isCross;
    }

    public boolean isFull() {
        return isFull;
    }

    public boolean isEmpty() {
        return (up == State.EMPTY || up == State.BLOCKED)
                && (down == State.EMPTY || down == State.BLOCKED)
                && (right == State.EMPTY || right == State.BLOCKED)
                && (left == State.EMPTY || left == State.BLOCKED);
    }

    public Color getColor(Side side) {
        switch (side) {
            case LEFT:
            case RIGHT:
                return color;
            case UP:
            case DOWN:
                return crossColor;
            default:
                return null;
        }
    }

    private PropertyChangeSupport changeSupport;

    public void addPropertyChangeListner(PropertyChangeListener listener) {
        if (listener == null) {
            return;
        }
        if (changeSupport == null) {
            changeSupport = new PropertyChangeSupport(this);
        }
        changeSupport.addPropertyChangeListener(listener);
    }

    public boolean hasDot() {
        return this.dot != null;
    }

    public Dot getDot() {
        return dot;
    }
    
    public boolean isHall(){
        return up == State.BLOCKED && down == State.BLOCKED && right == State.BLOCKED && left == State.BLOCKED;
    }
}
