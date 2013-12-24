/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cell.componants;

import java.awt.Color;

/**
 *
 * @author DigitalNet
 */
public class Pipe extends Componant implements Removable{
	public final static byte HORIZENTAL = 8;
	public final static byte VERTICAL = 9;
        public final static byte CORNOR_NORTH_EAST = 12;
	public final static byte CORNOR_NORTH_WEST = 13;
        public final static byte CORNOR_SHOUTH_EAST = 14;
        public final static byte CORNOR_SHOUTH_WEST = 15;

	protected final static byte CORNOR = 20;
        
	public static boolean isPipe(Componant componant){
		return (componant.getId()&4)!=0;
	}
	
	public Pipe(byte type ,Color color){
		super(type,color);		
		if(type != Pipe.HORIZENTAL && type != Pipe.VERTICAL && (type & Pipe.CORNOR)==0)
			throw new IllegalArgumentException();
	}
        
        public boolean isHorizental(){
            return this.getId() == HORIZENTAL;
        }
        
        public boolean isVertical(){
            return this.getId() == VERTICAL;
        }
        
        public boolean isCORNOR_NORTH_WEST(){
            return this.getId() == CORNOR_NORTH_WEST;
        }        
        public boolean isCORNOR_NORTH_EAST(){
            return this.getId() == CORNOR_NORTH_EAST;
        }  
        public boolean isCORNOR_SHOUTH_WEST(){
            return this.getId() == CORNOR_SHOUTH_WEST;
        }  
        public boolean isCORNOR_SHOUTH_EAST(){
            return this.getId() == CORNOR_SHOUTH_EAST;
        }  
        public boolean isACornor(){
            return (this.getId() & Pipe.CORNOR)==0;
        }
}
