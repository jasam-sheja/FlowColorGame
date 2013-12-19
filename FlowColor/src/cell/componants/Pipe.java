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
	public final static byte HORIZENTAL = 6;
	public final static byte VERTICAL = 5;
	public final static byte CORNOR = 4;
	
	public static boolean isPipe(Componant componant){
		return (componant.getId()&4)!=0;
	}
	
	public Pipe(byte type ,Color color){
		super(type,color);		
		if(type != Pipe.HORIZENTAL && type != Pipe.VERTICAL && type != Pipe.CORNOR)
			throw new IllegalArgumentException();
	}
}
