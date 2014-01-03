/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InputOutFiles;

import ComponantsTest.Level ;
import ComponantsTest.Maze ;
import java.io.* ;
/**
 *
 * @author Wissam
 */
public class Writer {
    
    public static void levelsWriter (Level  level , String fileName){
    
        ObjectOutputStream writeObject  ;
        try{
            writeObject = new ObjectOutputStream(new FileOutputStream(fileName) ) ;
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
            writeObject = new ObjectOutputStream(new FileOutputStream(fileName) ) ;
            writeObject.writeObject(maze);
            writeObject.flush();
            writeObject.close();
        
        }
        catch (IOException ex){
            System.err.println(ex.getMessage());
        }
        
    }
    
}
