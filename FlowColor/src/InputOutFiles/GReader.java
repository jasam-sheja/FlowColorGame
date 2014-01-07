/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InputOutFiles;


import Componants.Level ;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import Componants.Maze ;
/**
 *
 * @author Wissam
 */
public class GReader {
    
    public static Level levelsReader (int levelNumber, String fileName){
        Level level ;
        ObjectInputStream readObjectS ;
        try{        
         readObjectS = new ObjectInputStream(new FileInputStream(fileName)) ;
        while (true){
            level = (Level) readObjectS.readObject() ;
            if (level==null)
            System.out.println(level.getLevelNumber());
            
            if ((level == null)||(level.getLevelNumber() == levelNumber)){
                break ;
            }           
        }
        readObjectS.close();
        
        return level ;
        
        }
        catch (ClassNotFoundException ex){
            System.err.println(ex.getMessage());
            return null ;
        }
        catch(IOException ex){
            System.err.println(ex.getMessage());
            return null ;
        }
        
        
        
    }
    public static Maze gameReader (int gameNumber, String fileName){
        Maze maze ;
        ObjectInputStream readObject ;
        try{        
         readObject = new ObjectInputStream(new FileInputStream(fileName)) ;
        while (true){
            maze = (Maze) readObject.readObject() ;
            if ((maze == null)||(maze.getGameNumber()== gameNumber))
                break ;
            
        }
        readObject.close();
        return maze ;
        
        }
        catch (ClassNotFoundException ex){
            System.err.println(ex.getMessage());
            return null ;
        }
        catch(IOException ex){
            System.err.println(ex.getMessage());
            return null ;
        }
        
        
        
    }
    
    public static int levelsReader (String fileName){
        Level level ;
        ObjectInputStream readObjectS ;
        try{        
         readObjectS = new ObjectInputStream(new FileInputStream(fileName)) ;
         int number = (int) readObjectS.readObject() ;
        readObjectS.close();
        
        return number ;
        
        }
        catch (ClassNotFoundException ex){
            System.err.println(ex.getMessage());
            return  1 ;
            
        }
        catch(IOException ex){
            System.err.println(ex.getMessage());
            return 1 ;
            
        }
        
        
        
    }
}
