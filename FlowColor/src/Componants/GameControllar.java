package Componants;

import InputOutFiles.GWriter;
import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author jasam + wissam
 */
public class GameControllar {

    /* Attributs */
    //<editor-fold defaultstate="collapsed" desc="Here the Private Attributs ">
    private Maze maze;
    private Maze maze2;

    private PropertyChangeSupport changeSupport;

    /**
     * tell the cell is full if it is zero
     */
    private int unfilled;
    private int unfilled2;

    //</editor-fold>
    /**
     * construct the game controller with level
     *
     * @param level
     */
    public GameControllar(Level level) {
        maze = new Maze(level);
        maze2 = new Maze(maze);
        unfilled = 2 * (int) Math.pow(level.getLength(), 2) - level.getDots().length;
        if (level.getBridge() != null) {
            unfilled += 2;
        }
        if (level.getHall() != null) {
            unfilled -= 2;
        }
        unfilled2 = unfilled;
    }

    /**
     * construct the game with maze
     *
     * @param newMaze
     */
    public GameControllar(Maze newMaze) {
        maze = newMaze;
        maze2 = new Maze(maze);
        unfilled = 2 * (int) Math.pow(newMaze.getLength(), 2) - newMaze.getLevel().getDots().length;
        if (newMaze.getLevel().getBridge() != null) {
            unfilled += 2;
        }
        if (newMaze.getLevel().getHall() != null) {
            unfilled -= 2;
        }
        for (int i = 0; i < maze.getLength(); i++) {
            for (int j = 0; j < maze.getLength(); j++) {
                unfilled -= maze.getCellAt(i, j).getPipeCount();
            }
        }
        unfilled2 = unfilled;
    }

    /**
     * add pipe from the cell at <code>i0,j0</code> to the cell at
     * <code>i,j</code>
     *
     * @param i
     * @param j
     * @param i0
     * @param j0
     * @return true if the adding is a success
     * @throws IllegalArgumentException if the cells aren't next to each other
     * vertically or horizontally
     */
    public boolean add(int i, int j, int i0, int j0) {
        Cell.Side side = null;//for the cell to
        int n = 0;
        if (i == i0 + 1) {
            side = Cell.Side.UP;
            n++;
        }
        if (i == i0 - 1) {
            side = Cell.Side.DOWN;
            n++;
        }
        if (j == j0 + 1) {
            side = Cell.Side.LEFT;
            n++;
        }
        if (j == j0 - 1) {
            side = Cell.Side.RIGHT;
            n++;
        }
        if (n == 2) {
            throw new IllegalArgumentException("moving horezentaly or verticaly only");
        } else if (n == 0) {
            throw new IllegalArgumentException("moveing one cell at a time only");
        } else if (n != 1) {
            throw new IllegalArgumentException("unknown exception there should be only one side but here there is " + n);
        }

        Cell from = this.maze.getCellAt(i0, j0);
        if (from.hasDot() && from.getDot().next.isStart && from.getEntered() == null) {
            removeLeaveLine(from.getDot().next.x, from.getDot().next.y);
        }
        if ((from.isEmpty() && !from.hasDot())
                || from.isFull()
                || from.isHall()) {
            return false;
        }
        if (from.isCross() && from.getEntered(side == Cell.Side.RIGHT || side == Cell.Side.LEFT) == null) {
            return false;
        }

        Cell to = this.maze.getCellAt(i, j);
        if (to.isHall()) {
            return false;
        }

        Color color = from.getColor(side);
        if (color == null) {
            throw new InternalError("color is null");
        }
        if (to.hasDot() && !to.getColor(side).equals(color)) {
            return false;
        } else if (!to.isEmpty() && !to.hasDot()) {
            if (!(to.getColor(side) == null) && !to.getColor(side).equals(color)) {

                int toIsFromI = i;
                int toIsFromJ = j;
                Cell.Side toSide = (!to.isCross() ? to.getEntered() : to.getEntered(side == Cell.Side.LEFT || side == Cell.Side.RIGHT)).Opposite();
                switch (toSide.Opposite()) {
                    case UP:
                        toIsFromI--;
                        break;
                    case DOWN:
                        toIsFromI++;
                        break;
                    case RIGHT:
                        toIsFromJ++;
                        break;
                    case LEFT:
                        toIsFromJ--;
                        break;
                }
                Cell toIsFrom = maze.getCellAt(toIsFromI, toIsFromJ);
                if (toIsFrom.isCross() && to.getEntered(toSide == Cell.Side.RIGHT || toSide == Cell.Side.LEFT) != null && !to.getColor(toSide).equals(color)) {
                    removeLeaveLine(toIsFromI, toIsFromJ, toSide == Cell.Side.RIGHT || toSide == Cell.Side.LEFT);
                } else if (!to.getColor(toSide).equals(color)) {
                    removeLeaveLine(toIsFromI, toIsFromJ);
                }
            }
        }

        if (from.add(side.Opposite(), color)) {
            unfilled--;
        }
        if (!to.add(side, color)) {
            Cell.Side entered;
            if (!to.isCross()) {
                removeLeaveLine(i, j);
                entered = to.getEntered();
            } else {
                removeLeaveLine(i, j, side == Cell.Side.LEFT || side == Cell.Side.RIGHT);
                entered = to.getEntered(side == Cell.Side.LEFT || side == Cell.Side.RIGHT);
            }
            if (entered != null) {
                if (to.getColor(side) != color) {
                    to.remove(entered);
                }
            }
        } else {
            unfilled--;
        }
        if (unfilled == 0) {
            changeSupport.firePropertyChange("unfilled", unfilled + 1, unfilled);
        } else if (unfilled < 0) {
            throw new InternalError("unfilled are negative");
        }
        return true;
    }

    /**
     * this method should not be used with bridged cells or it will do nothing
     * this method remove all pipes starting from the cell with the indexes      <code>i,j<\code> toward the leaving pipes keeping the pipe that enters 
     * this cell
     *
     * @param i
     * @param j
     */
    public void removeLeaveLine(int i, int j) {
        Cell cell = this.maze.getCellAt(i, j);
        if (cell.isEmpty()) {
            return;
        }
        if (!cell.isCross()) {
            Cell.Side leaveSide = cell.getLeaved();
            if (leaveSide == null) {
                return;
            }
            if (cell.remove(leaveSide)) {
                unfilled++;
            }
            switch (leaveSide) {
                case UP:
                    i--;
                    break;
                case DOWN:
                    i++;
                    break;
                case RIGHT:
                    j++;
                    break;
                case LEFT:
                    j--;
                    break;
            }
            cell = this.maze.getCellAt(i, j);
            if (cell.remove(leaveSide.Opposite())) {
                unfilled++;
            }
            if (!cell.isCross()) {
                removeLeaveLine(i, j);
            } else {
                removeLeaveLine(i, j, leaveSide == Cell.Side.RIGHT || leaveSide == Cell.Side.LEFT);
            }
        }
    }

    /**
     * used for bridge cell this method remove all pipes starting from the cell
     * with the indexes      <code>i,j<\code> toward the leaving pipes keeping the pipe that enters 
     * this cell
     *
     * @param i
     * @param j
     * @param horizontal
     */
    private void removeLeaveLine(int i, int j, boolean horizontal) {
        Cell cell = this.maze.getCellAt(i, j);
        if (cell.isEmpty()) {
            return;
        }
        Cell.Side leaveSide = cell.getLeaved(horizontal);
        if (leaveSide == null) {
            return;
        }
        if (cell.remove(leaveSide)) {
            unfilled++;
        }
        switch (leaveSide) {
            case UP:
                i--;
                break;
            case DOWN:
                i++;
                break;
            case RIGHT:
                j++;
                break;
            case LEFT:
                j--;
                break;
        }
        cell = this.maze.getCellAt(i, j);
        if (cell.remove(leaveSide.Opposite())) {
            unfilled++;
        }
        if (!cell.isCross()) {
            removeLeaveLine(i, j);
        } else {
            removeLeaveLine(i, j, leaveSide == Cell.Side.RIGHT || leaveSide == Cell.Side.LEFT);
        }
    }

    /**
     * this method can be used with bridged cells if they are not full this
     * method remove all pipes starting from the cell with the indexes      <code>i,j<\code> toward the leaving pipes keeping the pipe that enters 
     * this cell
     *
     * @param i
     * @param j
     */
    public void clearPath(int i, int j) {
        Cell theCell = maze.getCellAt(i, j);
        if (theCell.isHall() || (theCell.isEmpty() && !theCell.hasDot())) {
            return;
        }
        if (theCell.isCross() && !theCell.isFull()) {
            removeLeaveLine(i, j, theCell.getLeaved(true) == Cell.Side.RIGHT || theCell.getLeaved(true) == Cell.Side.LEFT);
        } else if (theCell.hasDot()) {
            removeLeaveLine(theCell.getDot().next.x, theCell.getDot().next.y);
        }
        removeLeaveLine(i, j);
    }

    public void addPropertyChangeListner(PropertyChangeListener listener, int i, int j) {
        this.maze.getCellAt(i, j).addPropertyChangeListner(listener);
        this.maze2.getCellAt(i, j).addPropertyChangeListner(listener);
    }

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
     * @return count of rows
     */
    public int CellsPerRow() {
        return this.maze.getLength();
    }

    /**
     * save the state of the maze in other attributes
     */
    public void saveState() {
        maze2 = new Maze(maze);
        unfilled2 = unfilled;
    }

    /**
     * go back on step back
     *
     * @return if the undoing is done
     */
    public boolean undo() {
        if (unfilled == unfilled2) {
            return false;
        }
        Maze tempMaze = maze;
        maze = maze2;
        maze2 = tempMaze;
        int tempI = unfilled;
        unfilled = unfilled2;
        unfilled2 = tempI;
        changeSupport.firePropertyChange("MAZE", maze2, maze);
        return true;
    }

    /**
     * save the current maze in a file
     *
     * @param gamenumber unique number
     * @param filename
     */
    public void saveMaze(int gamenumber, String filename) {
        this.maze.setGameNumber(gamenumber);
        GWriter.gameWriter(maze, filename);
    }

    /**
     * fire the state of pipes in all cells
     */
    public void updateUI() {
        maze.updateUI();
    }
}
