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
public class Dot extends Componant{
	public Dot(Color color){
		super((byte)0,color);
	}
        public static boolean isDot(Componant c){
            return (Dot)c != null;
        } 
}
