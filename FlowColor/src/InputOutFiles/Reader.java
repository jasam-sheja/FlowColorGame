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
public class Reader {
    
    public static Level levelsReader (int levelNumber, String fileName){
        Level level ;
        ObjectInputStream readObject ;
        try{        
         readObject = new ObjectInputStream(new FileInputStream(fileName)) ;
        while (true){
            level = (Level) readObject.readObject() ;
            if ((level == null)||(level.getLevelNumber() == levelNumber))
                break ;
            
        }
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
    
}
