package InputOutFiles;

import Componants.Level;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import Componants.Maze;
import static java.awt.image.ImageObserver.ABORT;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Wissam
 */
public class GReader {

    public static ArrayList<Level> levelsReader(String fileName) {
        ArrayList<Level> levels;
        ObjectInputStream readObjectS;
        try {
            readObjectS = new ObjectInputStream(new FileInputStream(fileName));
            levels = (ArrayList<Level>) readObjectS.readObject();
            readObjectS.close();

            return levels;

        } catch (ClassNotFoundException | IOException ex) {
            System.err.println(ex.getMessage());
            return null;
        }

    }

    public static Level levelReader(int leverNumber, String fileName) {
        ArrayList<Level> levels = levelsReader(fileName);
        for (Level level : levels) {
            if (level.getLevelNumber() == leverNumber) {
                return level;

            }
        }
        JOptionPane.showMessageDialog(null, "sry no such level");
        System.exit(ABORT);
        return null;
    }

    public static ArrayList<Maze> mazesReader(String fileName) {
        ArrayList<Maze> mazes;
        ObjectInputStream readObjectS;
        try {
            readObjectS = new ObjectInputStream(new FileInputStream(fileName));
            mazes = (ArrayList<Maze>) readObjectS.readObject();
            readObjectS.close();

            return mazes;

        } catch (ClassNotFoundException | IOException ex) {
            System.err.println(ex.getMessage());
            return null;
        }

    }

    public static Maze mazeReader(int mazeNumber, String fileName) {
        ArrayList<Maze> mazes = mazesReader(fileName);
        for (Maze maze : mazes) {
            if (maze.getGameNumber() == mazeNumber) {
                return maze;

            }
        }
        JOptionPane.showMessageDialog(null, "sry no such level");
        System.exit(ABORT);
        return null;
    }

    public static int levelsNumberReader(String fileName) {
        Level level;
        ObjectInputStream readObjectS;
        try {
            readObjectS = new ObjectInputStream(new FileInputStream(fileName));
            int number = (int) readObjectS.readObject();
            readObjectS.close();

            return number;

        } catch (ClassNotFoundException | IOException ex) {
            System.err.println(ex.getMessage());
            return 1;

        }

    }
}
