/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package InputOutFiles;

import Componants.*  ;      
import java.awt.Color;
        
        
public class MakeMaze {
    public MakeMaze(){
   
    }
    
    
    public Level buildLevel(){
        Dot redDot =  new Dot(Color.RED,0,0,Color.RED,4,1);
        Dot redDot2 = redDot.next ;
        Dot greenDot =  new Dot(Color.GREEN,0,2,Color.GREEN,3,1);
        Dot greenDot2 = greenDot.next ;
        Dot blueDot =  new Dot(Color.BLUE,1,2,Color.BLUE,4,2);
        Dot blueDot2= blueDot.next ;
        Dot yellowDot =  new Dot(Color.YELLOW,0,4,Color.YELLOW,3,3);
        Dot yellowDot2 = yellowDot.next ;
        Dot orangeDot =  new Dot(Color.ORANGE,1,4,Color.ORANGE,4,3);
        Dot orangeDot2 = orangeDot.next ;
        Dot [] dots = {redDot,redDot2,greenDot,greenDot2,blueDot,blueDot2,yellowDot,yellowDot2,orangeDot,orangeDot2} ;
        Level level = new Level(dots, null, null, 5, 1) ;
        return level ;
        
                
    
    }
    
    public static void main(String[] args) {
    
        MakeMaze maze = new MakeMaze() ;
        Level level = maze.buildLevel() ;
        GWriter.levelsWriter(level, "LevelTest.bin");
        Level levelt = GReader.levelsReader(1, "LevelTest.bin");
        System.out.println(levelt.getLength()); 
        System.out.println(levelt.getLevelNumber());
        
    }
    
}
