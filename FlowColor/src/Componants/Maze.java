package Componants;

import java.awt.Color;
import java.util.Arrays;

/**
 *
 * @author jasam + wissam
 */
public class Maze {

    private Cell[][] maze;
    private Dot[] dots;

    public Maze(Dot[] dots, Bridge bridge, Hall hall, int lenght) {
        if (dots == null) {
            throw new IllegalArgumentException();
        } else if (dots.length < 1) {
            throw new IllegalArgumentException();
        }
        
        Arrays.sort(dots, new DotCompByPosition());
        this.dots = dots;
        this.maze = new Cell[lenght][lenght];
        int k = 0;
        for (int i = 0; i < lenght; i++) {
            for (int j = 0; j < lenght; j++) {
                if (dots[k].x == i && dots[k].y == j) {
                    this.maze[i][j] = new Cell(false, false, dots[k]);
                    k++;
                    if (k > dots.length) {
                        k = 0;
                    }
                } else if (bridge != null && bridge.x == i && bridge.y == j) {
                    this.maze[i][j] = new Cell(false, true, null);
                } else if (hall != null && hall.x == i && hall.y == j) {
                    this.maze[i][j] = new Cell(true, false, null);
                } else
                    this.maze[i][j] = new Cell(false, false, null);
            }
        }
    }

    public Cell getCellAt(int i,int j) {
        return maze[i][j];
    }

    public Dot getFirstDotOfColor(Color color) {
        throw new UnsupportedOperationException();
    }

    
}
