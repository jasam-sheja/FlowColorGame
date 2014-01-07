package Componants;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 *
 * @author jasam + wissam
 */
public class Cell implements Serializable {

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
    private Color horizontalColor;
    /**
     * if there is Cross this should represent the vertical pipe color else this
     * should be null
     */
    private Color verticalColor;

    private Dot dot;

    /**
     * fire the state of the cell
     */
    private PropertyChangeSupport changeSupport;

    //</editor-fold>
    public Cell(boolean isHall, boolean isCross, Dot dot) {
        this(isHall, isCross, dot, false, false, false, false);
    }

    /**
     *
     * @param isHall boolean tell whether the cell is a hall
     * @param isCross boolean tell whether the cell contain bridge
     * @param dot the dot that the cell should contain , can be null
     * @param isUpBlocked boolean tell whether this side cannot be edited
     * @param isDownBlocked boolean tell whether this side cannot be edited
     * @param isRightBlocked boolean tell whether this side cannot be edited
     * @param isLeftBlocked boolean tell whether this side cannot be edited
     * @throws IllegalArgumentException if there is two of hall ,cross or dot
     * @throws IllegalArgumentException if there is a bridge and one or more of
     * the sides is blocked
     */
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
            this.horizontalColor = dot.color;
            this.verticalColor = dot.color;
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
        pipeCount = 0;
    }

    /**
     *
     * @param copy the cell we want to get a copy of it
     * @throws IllegalArgumentException if the <code>copy<\code> is null
     */
    public Cell(Cell copy) {
        if (copy == null) {
            throw new NullPointerException(" the copy cell is null");
        }
        this.isCross = copy.isCross;
        this.changeSupport = copy.changeSupport;
        this.horizontalColor = copy.horizontalColor != null ? new Color(copy.horizontalColor.getRGB()) : null;
        this.verticalColor = copy.verticalColor != null ? new Color(copy.verticalColor.getRGB()) : null;
        this.dot = copy.dot;
        this.down = copy.down;
        this.left = copy.left;
        this.right = copy.right;
        this.up = copy.up;
        if (changeSupport != null) {
            changeSupport.firePropertyChange(up.toString(), this, up);//i know
            changeSupport.firePropertyChange(right.toString(), this, right);//i know
            changeSupport.firePropertyChange(left.toString(), this, left);//i know
            changeSupport.firePropertyChange(down.toString(), this, down);//i know
        }
        this.pipeCount = copy.pipeCount;
    }

    /**
     * add a pipe at the side and a color
     *
     * @param side the side we want to add pipe to
     * @param color the color of the pipe
     * @return true if the adding is a success and false if the adding is not a
     * success
     *
     * @throws NullPointerException is either one of the side or the color is
     * null
     */
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
                        if (!getColor(false).equals(color)) {
                            return false;
                        }
                        up = State.CROSS_LEAVED;
                    } else {
                        up = State.CROSS_ENTERD;
                        this.verticalColor = color;
                    }
                } else {
                    if (hasDot()) {
                        if (!dot.color.equals(color)) {
                            return false;
                        }
                        if (!dot.next.isStart) {
                            up = State.LEAVED;
                            dot.isStart = true;
                        } else {
                            up = State.ENTERD;
                        }
                    } else if (down == State.ENTERD
                            || right == State.ENTERD
                            || left == State.ENTERD) {
                        if (!getColor(false).equals(color)) {
                            return false;
                        }
                        up = State.LEAVED;
                    } else {
                        up = State.ENTERD;
                        this.horizontalColor = color;
                        this.verticalColor = color;
                    }
                }
                newValue = up;
                break;

            case DOWN:
                if (down != State.EMPTY) {
                    return false;
                } else if (isCross) {
                    if (up == State.CROSS_ENTERD) {
                        if (!getColor(false).equals(color)) {
                            return false;
                        }
                        down = State.CROSS_LEAVED;
                    } else {
                        down = State.CROSS_ENTERD;
                        this.verticalColor = color;
                    }
                } else {
                    if (hasDot()) {
                        if (!dot.color.equals(color)) {
                            return false;
                        }
                        if (!dot.next.isStart) {
                            down = State.LEAVED;
                            dot.isStart = true;
                        } else {
                            down = State.ENTERD;
                        }
                    } else if (up == State.ENTERD
                            || right == State.ENTERD
                            || left == State.ENTERD) {
                        if (!getColor(false).equals(color)) {
                            return false;
                        }
                        down = State.LEAVED;
                    } else {
                        down = State.ENTERD;
                        this.horizontalColor = color;
                        this.verticalColor = color;
                    }
                }
                newValue = down;
                break;

            case RIGHT:
                if (right != State.EMPTY) {
                    return false;
                } else if (isCross) {
                    if (left == State.CROSS_ENTERD) {
                        if (!getColor(true).equals(color)) {
                            return false;
                        }
                        right = State.CROSS_LEAVED;
                    } else {
                        right = State.CROSS_ENTERD;
                        this.horizontalColor = color;
                    }
                } else {
                    if (hasDot()) {
                        if (!dot.color.equals(color)) {
                            return false;
                        }
                        if (!dot.next.isStart) {
                            right = State.LEAVED;
                            dot.isStart = true;
                        } else {
                            right = State.ENTERD;
                        }
                    } else if (down == State.ENTERD
                            || up == State.ENTERD
                            || left == State.ENTERD) {
                        if (!getColor(true).equals(color)) {
                            return false;
                        }
                        right = State.LEAVED;
                    } else {
                        right = State.ENTERD;
                        this.horizontalColor = color;
                        this.verticalColor = color;
                    }
                }
                newValue = right;
                break;

            case LEFT:
                if (left != State.EMPTY) {
                    return false;
                } else if (isCross) {
                    if (right == State.CROSS_ENTERD) {
                        if (!getColor(true).equals(color)) {
                            return false;
                        }
                        left = State.CROSS_LEAVED;
                    } else {
                        left = State.CROSS_ENTERD;
                        this.horizontalColor = color;
                    }
                } else {
                    if (hasDot()) {
                        if (!dot.color.equals(color)) {
                            return false;
                        }
                        if (!dot.next.isStart) {
                            left = State.LEAVED;
                            dot.isStart = true;
                        } else {
                            left = State.ENTERD;
                        }
                    } else if (down == State.ENTERD
                            || right == State.ENTERD
                            || up == State.ENTERD) {
                        if (!getColor(true).equals(color)) {
                            return false;
                        }
                        left = State.LEAVED;
                    } else {
                        left = State.ENTERD;
                        this.horizontalColor = color;
                        this.verticalColor = color;
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

    /**
     * remove the pipe at side
     *
     * @param side the side we want to remove the pipe from
     * @return true if the removing is a success and false if else
     * @throws NullPointerException if the <code>side<\code> is null
     */
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
        if (isEmpty() && !hasDot() && !isCross) {
            this.horizontalColor = this.verticalColor = null;
        } else if (isCross) {
            if (emptyLine(true)) {
                horizontalColor = null;
            } else if (emptyLine(false)) {
                verticalColor = null;
            }
        }

        changeSupport.firePropertyChange(side.toString(), this, newValue);
        return true;
    }

    /**
     * this method should be used only with none bridged cells
     *
     * @return the side of the entering pipe and if there isn't any returns null
     * @throws InternalError if the cell is a bridge
     */
    public Side getEntered() {
        if (isCross) {
            throw new InternalError("connot use with cell with bridge");
        }
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

    /**
     * this method should be used only with none bridged cells
     *
     * @return the side of the leaving pipe and if there isn't any returns null
     * @throws InternalError if the cell is a bridge
     */
    public Side getLeaved() {
        if (isCross) {
            throw new InternalError("connot use with cell with bridge");
        }
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

    /**
     * this method can be used with any cell
     *
     * @param horizontal
     * @return the side of the entering pipe depending on <code>horizontal<\code>
     * and if there isn't any returns null
     */
    public Side getEntered(boolean horizontal) {
        if (horizontal) {
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

    /**
     * this method can be used with any cell
     *
     * @param horizontal
     * @return the side of the leaving pipe depending on <code>horizontal<\code>
     * and if there isn't any returns null
     */
    public Side getLeaved(boolean horizontal) {
        if (horizontal) {
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

    /**
     *
     * @return true if the cell is a bridge
     */
    public boolean isCross() {
        return isCross;
    }

    /**
     *
     * @return true if the cell full of pipes
     */
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

    /**
     *
     * @return true if the cell has no pipes
     */
    public boolean isEmpty() {
        return (up == State.EMPTY || up == State.BLOCKED)
                && (down == State.EMPTY || down == State.BLOCKED)
                && (right == State.EMPTY || right == State.BLOCKED)
                && (left == State.EMPTY || left == State.BLOCKED);
    }

    /**
     *
     * @param horizontal
     * @return true if the line which depend on <code>horizontal<\code> is empty
     */
    public boolean emptyLine(boolean horizontal) {
        if (isCross) {
            if (!horizontal) {
                return (up == State.EMPTY || up == State.BLOCKED)
                        && (down == State.EMPTY || down == State.BLOCKED);
            } else {
                return (right == State.EMPTY || right == State.BLOCKED)
                        && (left == State.EMPTY || left == State.BLOCKED);
            }
        } else {
            return isEmpty();
        }
    }

    /**
     *
     * @param side
     * @return the color of the pipe at the side or the opposite side of <code>side<\code>
     */
    public Color getColor(Side side) {
        switch (side) {
            case LEFT:
            case RIGHT:
                return horizontalColor;
            case UP:
            case DOWN:
                return verticalColor;
            default:
                return null;
        }
    }

    /**
     *
     * @param horizontal
     * @return the color of the pipe depending on <code>horizontal<\code>
     */
    public Color getColor(boolean horizontal) {
        return horizontal ? horizontalColor : verticalColor;
    }

    /**
     * Add a PropertyChangeListener to the listener list. The listener is
     * registered for all properties. The same listener object may be added more
     * than once, and will be called as many times as it is added. If
     * <code>listener</code> is null, no exception is thrown and no action is
     * taken.
     *
     * @param listener The PropertyChangeListener to be added
     */
    public void addPropertyChangeListner(PropertyChangeListener listener) {
        if (listener == null) {
            return;
        }
        if (changeSupport == null) {
            changeSupport = new PropertyChangeSupport(this);
        }
        changeSupport.addPropertyChangeListener(listener);
    }

    /**
     *
     * @return whether this cell has a dot or not
     */
    public final boolean hasDot() {
        return this.dot != null;
    }

    /**
     *
     * @return the dot of this cell and can return null if the cell has no dot
     */
    public Dot getDot() {
        return dot;
    }

    /**
     *
     * @return whether this cell is a hall or not
     */
    public boolean isHall() {
        return up == State.BLOCKED && down == State.BLOCKED && right == State.BLOCKED && left == State.BLOCKED;
    }

    /**
     * fire the state of pipes at this cell
     */
    public void updateUI() {
        changeSupport.firePropertyChange("UP", this, up);
        changeSupport.firePropertyChange("Down", this, down);
        changeSupport.firePropertyChange("RIGHT", this, right);
        changeSupport.firePropertyChange("LEFT", this, left);
    }

    /**
     *
     * @return the number of pipes in this cell
     */
    public int getPipeCount() {
        return pipeCount;
    }
}
