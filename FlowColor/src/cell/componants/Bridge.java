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
public final class Bridge extends Componant{
	public final static byte HORIZENTAL = 10;
	public final static byte VERTICAL = 17;
	
	private Pipe pipe;
	
	public Bridge(byte type,Color color,Pipe pipe){
		super(type,color);
                if(pipe != null)
                    this.addPipe(pipe);
		if(type != Bridge.HORIZENTAL && type != Bridge.VERTICAL)
			throw new IllegalArgumentException();
	}
	
	public Bridge(byte type,Color color){
		this(type,color,null);
	}
	
	public boolean canGoUnder(Removable componant){
		if((Nothing)componant != null)
			return true;
		return (this.getId()-((Componant)componant).getId())%2 != 0;
	}
	public boolean addPipe(Pipe pipe){
		if(this.canHold((Removable)pipe)){
			this.pipe = pipe;
			return true;
		}
		else
			return false;
	}
	
	public void removePipe(){
		this.pipe = null;
	}
	
	public boolean containPipe(){
		return this.pipe != null;
	}
}
