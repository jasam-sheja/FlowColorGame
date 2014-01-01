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
    
    public void add(int i,int j,int i0,int j0){
        Cell from = this.maze.getCellAt(i0, j0);
        Cell to = this.maze.getCellAt(i, j);
        if(from.isEmpty() && from.dot==null)
            return ;
        Cell.Side side;//for the cell to
        if(i==i0+1)
            side = Cell.Side.UP;
        else if(i==i0-1)
            side = Cell.Side.DOWN;
        else if(j==j0+1)
            side = Cell.Side.LEFT;
        else if(j==j0-1)
            side = Cell.Side.RIGHT;
        else
            throw new IllegalArgumentException();
        Color color = from.getColor(side);
		Cell.Side entered;
		if(!to.isCross()){
			removeLeaveLine(i, j);
			entered = to.getEntered();
		}else{
			removeLeaveLine(i,j,side == Cell.Side.LEFT||side == Cell.Side.RIGHT);
			entered = to.getEntered(side == Cell.Side.LEFT||side == Cell.Side.RIGHT);
		}
		if(entered != null){
			if(to.getColor(side) != color){
				to.remove(entered);
			}
		}   
        from.add(side.Opposite(), color);
        to.add(side, color);        
    }
    
    public void removeLeaveLine(int i,int j){
        Cell cell = this.maze.getCellAt(i, j);
        if(cell.isEmpty())
                return ;
        if(!cell.isCross()){            
            Cell.Side leaveSide = cell.getLeaved();
            if(leaveSide == null)
                return ;
            cell.remove(leaveSide);
            switch(leaveSide){
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
            if(!cell.isCross())
                removeLeaveLine(i, j);
            else
                removeLeaveLine(i, j, leaveSide == Cell.Side.RIGHT || leaveSide == Cell.Side.LEFT);
        }
    }
    /**
     * used for cross cell
     * @param i
     * @param j
     * @param horizontal 
     */
    private void removeLeaveLine(int i,int j,boolean horizontal){
        Cell cell = this.maze.getCellAt(i, j);
        if(cell.isEmpty())
                return ;
        Cell.Side leaveSide = cell.getLeaved(horizontal);
        if(leaveSide == null)
            return ;
        cell.remove(leaveSide);
        switch(leaveSide){
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
        if(!cell.isCross())
            removeLeaveLine(i, j);
        else
            removeLeaveLine(i, j, leaveSide == Cell.Side.RIGHT || leaveSide == Cell.Side.LEFT);
    }
    
    public void addPropertyChangeListner(PropertyChangeListener listener,int i,int j) {
        this.maze.getCellAt(i, j).addPropertyChangeListner(listener);
    }
    
    public int CellsPerRow(){
        return this.maze.getLength();
    }
}
