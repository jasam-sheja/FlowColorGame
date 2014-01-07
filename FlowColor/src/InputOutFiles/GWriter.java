/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InputOutFiles;

import Componants.Level;
import Componants.Maze;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Wissam
 */
public class GWriter {

    public static void levelsWriter(Level level, String fileName) {

        ObjectOutputStream writeObject;
        try {
            ArrayList<Level> levels = new ArrayList<Level>();
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                levels = GReader.levelsReader(fileName);
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

    public static void gameWriter(Maze maze, String fileName) {

        ObjectOutputStream writeObject;
        try {
            ArrayList<Maze> mazes = new ArrayList<Maze>();
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            else{
                mazes = GReader.mazesReader(fileName);
            }
            writeObject = new ObjectOutputStream(new FileOutputStream(file));
            mazes.add(maze) ;
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
