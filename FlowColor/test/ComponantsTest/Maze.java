package ComponantsTest;

import java.awt.Color;
import java.util.Arrays;

/**
 *
 * @author jasam + wissam
 */
public class Maze {

    private static  int counter = 0 ;
    private final Cell[][] maze;
    private final Dot[] dots;
    private final int lenght;
    private final int gameNumber ;

    public int getGameNumber() {
        return gameNumber;
    }

    public Maze(Dot[] dots, Bridge bridge, Hall hall, int lenght) {
        counter ++ ; 
        this.gameNumber = counter ;

        this.lenght = lenght;
        if (dots == null) {
            throw new IllegalArgumentException();
        } else if ((dots.length % 2) != 0 || dots.length < 2) {
            throw new IllegalArgumentException();
        }

        Arrays.sort(dots, new DotCompByPosition());
        this.dots = dots;
        this.maze = new Cell[lenght][lenght];
        int k = 0;
        for (int i = 0; i < lenght; i++) {
            for (int j = 0; j < lenght; j++) {
                boolean bup = i == 0, bdown = i == lenght, bright = j == lenght, bleft = j == 0;
                if ((k < dots.length) && (dots[k].x == i && dots[k].y == j)) {
                    this.maze[i][j] = new Cell(false, false, dots[k], bup, bdown, bright, bleft);
                    k++;
                } else if (bridge != null && bridge.x == i && bridge.y == j) {
                    this.maze[i][j] = new Cell(false, true, null, bup, bdown, bright, bleft);
                } else if (hall != null && hall.x == i && hall.y == j) {
                    this.maze[i][j] = new Cell(true, false, null, bup, bdown, bright, bleft);
                } else {
                    this.maze[i][j] = new Cell(false, false, null, bup, bdown, bright, bleft);
                }
            }
        }
    }

    public Cell getCellAt(int i, int j) {
        return maze[i][j];
    }

    public Dot getFirstDotOfColor(Color color) {
        throw new UnsupportedOperationException();
    }

    public int getLength() {
        return lenght;
    }
}
