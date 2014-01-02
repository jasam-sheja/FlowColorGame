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
    }

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
    private byte pipeCount;

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

    private Dot dot;

    //</editor-fold>
    public Cell(boolean isHall, boolean isCross, Dot dot) {
        this(isHall, isCross, dot, false, false, false, false);
    }

    public Cell(boolean isHall, boolean isCross, Dot dot, boolean isUpBlocked, boolean isDownBlocked, boolean isRightBlocked, boolean isLeftBlocked) {
        this.isCross = isCross;
        this.dot = dot;
        if ((isCross && dot != null)
                || (isCross && isHall)
                || (dot != null && isHall)) {
            throw new IllegalArgumentException("there should be either a dot or a bridge or a hall");
        }
        if (isCross && (isUpBlocked || isDownBlocked || isRightBlocked || isLeftBlocked)) {
            throw new IllegalArgumentException("there connot be a bridge with one of his side blocked");
        }
        if (this.hasDot()) {
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

    public boolean add(Side side, Color color) {
        if (side == null) {
            throw new NullPointerException("there should be a side to add to");
        }
        if (color == null) {
            throw new NullPointerException("there should be a color to add");
        }

        if (isFull()) {
            return false;
        }

        State newValue = up;
        switch (side) {
            case UP:
                if (up != State.EMPTY) {
                    return false;
                } else if (isCross) {
                    if (down == State.CROSS_ENTERD) {
                        if(!getColor(false).equals(color))
                            return false;
                        up = State.CROSS_LEAVED;
                    } else {
                        up = State.CROSS_ENTERD;
                        this.crossColor = color;
                    }
                } else {
                    if (hasDot()) {
                        if(!dot.color.equals(color))
                            return false;
                        if (!dot.next.isStart) {
                            up = State.LEAVED;
                            dot.isStart = true;
                        } else {
                            up = State.ENTERD;
                        }
                    } else if (down == State.ENTERD
                            || right == State.ENTERD
                            || left == State.ENTERD) {
                        if(!getColor(false).equals(color))
                                return false;
                        up = State.LEAVED;
                    } else {
                        up = State.ENTERD;
                        this.color = color;
                        this.crossColor = color;
                    }
                }
                newValue = up;
                break;

            case DOWN:
                if (down != State.EMPTY) {
                    return false;
                } else if (isCross) {
                    if (up == State.CROSS_ENTERD) {
                        if(!getColor(false).equals(color))
                                return false;
                        down = State.CROSS_LEAVED;
                    } else {
                        down = State.CROSS_ENTERD;
                        this.crossColor = color;
                    }                    
                } else {
                    if (hasDot()) {
                        if(!dot.color.equals(color))
                            return false;
                        if (!dot.next.isStart) {
                            down = State.LEAVED;
                            dot.isStart = true;
                        } else {
                            down = State.ENTERD;
                        }
                    } else if (up == State.ENTERD
                            || right == State.ENTERD
                            || left == State.ENTERD) {
                        if(!getColor(false).equals(color))
                                return false;
                        down = State.LEAVED;
                    } else {
                        down = State.ENTERD;
                        this.color = color;
                        this.crossColor = color;
                    }
                }
                newValue = down;
                break;

            case RIGHT:
                if (right != State.EMPTY) {
                    return false;
                } else if (isCross) {
                    if (left == State.CROSS_ENTERD) {
                        if(!getColor(false).equals(color))
                                return false;
                        right = State.CROSS_LEAVED;
                    } else {
                        right = State.CROSS_ENTERD;
                        this.color = color;
                    }                    
                } else {
                    if (hasDot()) {
                        if(!dot.color.equals(color))
                            return false;
                        if (!dot.next.isStart) {
                            right = State.LEAVED;
                            dot.isStart = true;
                        } else {
                            right = State.ENTERD;
                        }
                    } else if (down == State.ENTERD
                            || up == State.ENTERD
                            || left == State.ENTERD) {
                        if(!getColor(false).equals(color))
                                return false;
                        right = State.LEAVED;
                    } else {
                        right = State.ENTERD;
                        this.color = color;
                        this.crossColor = color;
                    }
                }
                newValue = right;
                break;

            case LEFT:
                if (left != State.EMPTY) {
                    return false;
                } else if (isCross) {
                    if (right == State.CROSS_ENTERD) {
                        if(!getColor(false).equals(color))
                                return false;
                        left = State.CROSS_LEAVED;
                    } else {
                        left = State.CROSS_ENTERD;
                        this.color = color;
                    }
                } else {
                    if (hasDot()) {
                        if(!dot.color.equals(color))
                            return false;
                        if (!dot.next.isStart) {
                            left = State.LEAVED;
                            dot.isStart = true;
                        } else {
                            left = State.ENTERD;
                        }
                    } else if (down == State.ENTERD
                            || right == State.ENTERD
                            || up == State.ENTERD) {
                        if(!getColor(false).equals(color))
                                return false;
                        left = State.LEAVED;
                    } else {
                        left = State.ENTERD;
                        this.color = color;
                        this.crossColor = color;
                    }
                }
                newValue = left;
                break;

            default:
                throw new IllegalArgumentException("the side " + side + " is unknown");
        }
        pipeCount++;
        changeSupport.firePropertyChange(side.toString(), this, newValue);
        return true;
    }

    public boolean remove(Side side) {
        if (side == null) {
            throw new NullPointerException("there should be a side to add to");
        }

        State newValue = up;
        switch (side) {
            case UP:
                if (up == State.EMPTY) {
                    return false;
                } else if (up != State.BLOCKED) {
                    up = State.EMPTY;
                    pipeCount--;
                } else {
                    return false;
                }
                newValue = up;
                break;
            case DOWN:
                if (down == State.EMPTY) {
                    return false;
                } else if (down != State.BLOCKED) {
                    down = State.EMPTY;
                    pipeCount--;
                } else {
                    return false;
                }
                newValue = down;
                break;
            case RIGHT:
                if (right == State.EMPTY) {
                    return false;
                } else if (right != State.BLOCKED) {
                    right = State.EMPTY;
                    pipeCount--;
                } else {
                    return false;
                }
                newValue = right;
                break;
            case LEFT:
                if (left == State.EMPTY) {
                    return false;
                } else if (left != State.BLOCKED) {
                    left = State.EMPTY;
                    pipeCount--;
                } else {
                    return false;
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
        changeSupport.firePropertyChange(side.toString(), this, newValue);
        return true;
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
        if (pipeCount < 0) {
            throw new InternalError("pipes count is negative");
        }
        int max = isCross ? 4
                : hasDot() ? 1
                : isHall() ? 0 : 2;
        if (pipeCount > max) {
            throw new InternalError("we have more than " + max + " pipes");
        }
        return pipeCount == max;
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
    public Color getColor(boolean horizental) {
        return horizental?color:crossColor;
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

    public final boolean hasDot() {
        return this.dot != null;
    }

    public Dot getDot() {
        return dot;
    }

    public boolean isHall() {
        return up == State.BLOCKED && down == State.BLOCKED && right == State.BLOCKED && left == State.BLOCKED;
    }
}
