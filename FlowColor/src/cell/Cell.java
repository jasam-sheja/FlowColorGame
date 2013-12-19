/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cell;

import cell.componants.*;

/**
 *
 * @author DigitalNet
 */
public class Cell{
	private Componant componant;
	private final Bridge bridge;
	
	public Cell(Componant componant,Bridge bridge){
		this.componant = componant;
		this.bridge = bridge;
		if((Bridge)componant != null || componant == null)
			throw new IllegalArgumentException();
	}
	
	public Cell(Componant componant){
		this(componant,null);
	}
	
	public void addComponant(Removable componant){
		if(componant == null)
			throw new IllegalArgumentException();
		if(!this.canHold(componant)) {
                } else if(this.bridge.canHold(componant))
			this.bridge.addPipe((Pipe)componant);
		else
			this.componant = (Componant)componant;
	}
	
	public void removeComponant(){
		if((Removable)this.componant == null)
			return;
		this.componant = new Nothing();
	}
	
	public void removeOnBridge(){
		this.bridge.removePipe();
	}
	
	public boolean containPipe(){
		boolean contain = false;
		if(this.containBridge())
			contain = bridge.containPipe();
		return Pipe.isPipe(componant) || contain;
	}
	
	public boolean containBridge(){
		return this.bridge!=null;
	}
	
	public boolean canHold(Removable componant){
		if((Nothing)componant != null)
			return true;
		if(this.containBridge())
			return this.bridge.canHold(componant) || this.bridge.canGoUnder(componant);
		else
			return this.componant.canHold(componant); 
	}
	
        public boolean isFull(){
            boolean full = (Nothing)this.componant == null;
            if(this.bridge != null){
                return this.bridge.containPipe() && full;
            }
            else
                return full;
        }
}
