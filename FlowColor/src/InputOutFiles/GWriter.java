/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InputOutFiles;

import Componants.Level;
import Componants.Maze;
import static java.awt.image.ImageObserver.ABORT;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Wissam
 */
public class GWriter {

    public static void levelsWriter(Level level, String fileName) {

        ObjectOutputStream writeObject;
        try {
            ArrayList<Level> levels = new ArrayList<>();
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                levels = GReader.levelsReader(fileName);
            }
            if (levels == null) {
                levels = new ArrayList<>();
            }
            writeObject = new ObjectOutputStream(new FileOutputStream(file));
            levels.add(level);
            writeObject.writeObject(levels);
            writeObject.flush();
            writeObject.close();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public static void levelsRemover(int levelnumber, String fileName) {

        ObjectOutputStream writeObject;
        try {
            Level level = null;
            ArrayList<Level> levels = new ArrayList<>();
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                level = GReader.levelReader(levelnumber, fileName);
            }
            if (level == null) {
                JOptionPane.showMessageDialog(null, "sry no such level");
                System.exit(ABORT);
            }

            writeObject = new ObjectOutputStream(new FileOutputStream(file));
            levels.remove(level);
            writeObject.writeObject(levels);
            writeObject.flush();
            writeObject.close();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public static void gameWriter(Maze maze, String fileName) {

        ObjectOutputStream writeObject;
        try {
            ArrayList<Maze> mazes = new ArrayList<>();
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                mazes = GReader.mazesReader(fileName);
            }
            writeObject = new ObjectOutputStream(new FileOutputStream(file));
            mazes.add(maze);
            writeObject.writeObject(mazes);

            writeObject.flush();
            writeObject.close();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public static void levelsNumberWriter(int number, String fileName) {

        ObjectOutputStream writeObject;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();

            }
            writeObject = new ObjectOutputStream(new FileOutputStream(file));
            writeObject.writeObject(number);
            writeObject.flush();
            writeObject.close();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

    }

}
