package ComponantsTest;

import java.awt.Color;
import java.beans.PropertyChangeListener;

/**
 *
 * @author jasam + wissam
 */
public class GameControllar {

    private Maze maze;

    public GameControllar(Level level) {
        maze = new Maze(level.getDots(), level.getBridge(), level.getHall(), level.getLength());
    }

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
        if ((from.hasDot() && from.getDot().next.isStart)
                || (from.isEmpty() && !from.hasDot())
                || from.isFull()) {
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
        } else if (!to.isEmpty()&& !to.getColor(side).equals(color)) {
            int toIsFromI = i;
            int toIsFromJ = j;
            switch (to.getEntered()) {
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
            if (to.isCross()) {
                removeLeaveLine(toIsFromI, toIsFromJ, side == Cell.Side.RIGHT || side == Cell.Side.LEFT);
            } else {
                removeLeaveLine(toIsFromI, toIsFromJ);
            }
        }

        from.add(side.Opposite(), color);
        if (!to.add(side, color)) {
            System.out.println("connot add " + side + " at " + i + "|" + j);

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
        }

        return true;
    }

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
            cell.remove(leaveSide);
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
            cell.remove(leaveSide.Opposite());
            if (!cell.isCross()) {
                removeLeaveLine(i, j);
            } else {
                removeLeaveLine(i, j, leaveSide == Cell.Side.RIGHT || leaveSide == Cell.Side.LEFT);
            }
        } else {
            removeLeaveLine(i, j, true);
        }
    }

    /**
     * used for cross cell
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
        cell.remove(leaveSide);
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
        cell.remove(leaveSide.Opposite());
        if (!cell.isCross()) {
            removeLeaveLine(i, j);
        } else {
            removeLeaveLine(i, j, leaveSide == Cell.Side.RIGHT || leaveSide == Cell.Side.LEFT);
        }
    }

    public void addPropertyChangeListner(PropertyChangeListener listener, int i, int j) {
        this.maze.getCellAt(i, j).addPropertyChangeListner(listener);
    }

    public int CellsPerRow() {
        return this.maze.getLength();
    }

}
