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
public class Componant {
    private final byte id;
	private Color color;
	public byte getId(){
		return id;
	}
	public Componant(byte id,Color color){
		this.id = id;
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public boolean equals(Componant c){
		return this.equalsType(c) && equalsColor(c);
	}
        public boolean equalsType(Componant c){
		return this.id == c.id;
	}
	public boolean equalsColor(Componant c){
            return this.color.equals(c.getColor());
        }
        
	public boolean canHold(Componant componant){
		return (this.id & componant.id) != 0;
	}
	
	public boolean canHold(Removable componant){
		return (this.id & ((Componant)componant).getId()) != 0;
	}
}
