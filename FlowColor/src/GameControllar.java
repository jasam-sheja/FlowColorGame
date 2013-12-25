
import Componants.*;


/**
 *
 * @author jasam + wissam
 */
public class GameControllar {
    private Maze maze;

    public GameControllar(Level level) {
        maze = new Maze(level.getDots(), level.getBridge(), level.getHall(), level.getLength());
    }
    
    
}
