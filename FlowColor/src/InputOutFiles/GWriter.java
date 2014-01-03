/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InputOutFiles;
import Componants.Level ;
import Componants.Maze ;
import java.io.* ;
/**
 *
 * @author Wissam
 */
public class GWriter {
    
    public static void levelsWriter (Level  level , String fileName){
    
        ObjectOutputStream writeObject  ;
        try{
            File file = new File(fileName);
            if(!file.exists()) {
               file.createNewFile();
            }
            writeObject = new ObjectOutputStream(new FileOutputStream(file,true) ) ;
            writeObject.writeObject(level);
            writeObject.flush();
            writeObject.close();
        
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
        }
        
    }
    public static void gameWriter (Maze maze , String fileName){
    
        ObjectOutputStream writeObject  ;
        try{
            File file = new File(fileName);
            if(!file.exists()) {
               file.createNewFile();
            }
            writeObject = new ObjectOutputStream(new FileOutputStream(file,true) ) ;
            writeObject.writeObject(maze);
            writeObject.flush();
            writeObject.close();
        
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
        }
        
    }
    
}
