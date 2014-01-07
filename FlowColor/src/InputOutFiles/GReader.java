/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InputOutFiles;

import Componants.Dot;
import Componants.Level;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import Componants.Maze;
import java.io.BufferedInputStream;
import java.util.ArrayList;

/**
 *
 * @author Wissam
 */
public class GReader {

    public static ArrayList<Level> levelsReader( String fileName) {
        ArrayList<Level> levels = new ArrayList<Level>();
        ObjectInputStream readObjectS;
        try {
            readObjectS = new ObjectInputStream(new FileInputStream(fileName));
            levels = (ArrayList<Level>) readObjectS.readObject();
            readObjectS.close();

            return levels;

        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
            return null;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return null;
        }

    }
    public static Level levelReader(int leverNumber , String fileName){
        ArrayList<Level> levels = levelsReader(fileName) ;
        for (Level level :levels){
            if (level.getLevelNumber()==leverNumber){
                return level ;
                
            }
        }
        return null ;
    }

    public static ArrayList<Maze> mazesReader( String fileName) {
        ArrayList<Maze> mazes = new ArrayList<Maze>();
        ObjectInputStream readObjectS;
        try {
            readObjectS = new ObjectInputStream(new FileInputStream(fileName));
            mazes = (ArrayList<Maze>) readObjectS.readObject();
            readObjectS.close();

            return mazes;

        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
            return null;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return null;
        }

    }
    public static Maze mazeReader(int mazeNumber , String fileName){
        ArrayList<Maze> mazes = mazesReader(fileName) ;
        for (Maze maze :mazes){
            if (maze.getGameNumber()==mazeNumber){
                return maze ;
                
            }
        }
        return null ;
    }
    
    public static int levelsNumberReader(String fileName) {
        Level level;
        ObjectInputStream readObjectS;
        try {
            readObjectS = new ObjectInputStream(new FileInputStream(fileName));
            int number = (int) readObjectS.readObject();
            readObjectS.close();

            return number;

        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
            return 1;

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return 1;

        }

    }
}
