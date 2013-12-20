/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cell;

import cell.componants.Bridge;
import cell.componants.Componant;
import cell.componants.Pipe;
import java.awt.Color;

/**
 *
 * @author DigitalNet
 */
public class Net {
    private final Cell[][] net;
    /**
     * 
     * @param control \
     * least 32 bit to control componant 
     * *least 24 bit for color
     * *most 8 bit for type of componant 
     * **note that we need for componant 7 bit only
     * most 32 bit to control bridge
     * *least 24 bit for color
     * *most 8 bit for type of bridge
     * **also note that we need for bridge 7 bit only
     */
    public Net(long [][]control){
        for(int i=0;i<control.length-1;i++){
            if(control[i].length != control[i+1].length)
                throw new IllegalArgumentException();
        }
        net = new Cell[control.length][control[0].length];
        for(int i=0;i<control.length;i++){
            for(int j=0;j<control[i].length;i++){ 
                net[i][j] = new Cell(new Componant((byte)(control[i][j] & 0xFF000000), new Color((int)(control[i][j] & 0xFFFFFF)))
                                     ,new Bridge((byte)((control[i][j]>>32) & 0xFF000000),  new Color((int)((control[i][j]>>32) & 0xFFFFFF))));
            }
        }
    }
    /**
     * @param componants
     * @param Colors
     * @param bridges
     * @param BridgesColors 
     */
    public Net(byte [][]componants,Color [][]Colors,byte [][]bridges,Color [][]BridgesColors){
        for(int i=0;i<componants.length-1;i++){
            if(componants[i].length != componants[i+1].length
			|| Colors[i].length != Colors[i+1].length
			|| bridges[i].length != bridges[i+1].length
			|| BridgesColors[i].length != BridgesColors[i+1].length
			|| componants[i].length != Colors[i].length
			|| componants[i].length != bridges[i].length
			|| componants[i].length != BridgesColors[i].length)
                throw new IllegalArgumentException();
        }
                
        net = new Cell[componants.length][componants[0].length];
        for(int i=0;i<componants.length;i++){
            for(int j=0;j<componants[i].length;i++){ 
                net[i][j] = new Cell(new Componant(componants[i][j], Colors[i][j])
                                     ,new Bridge(bridges[i][j], BridgesColors[i][j]));
            }
        }        
    }
    /**
     * @deprecated check again
     * @param componants
     * @param bridges 
     */
    public Net(Componant [][]componants,Bridge [][]bridges){
        for(int i=0;i<componants.length-1;i++){
            if(componants[i].length != componants[i+1].length
			|| bridges[i].length != bridges[i+1].length
			|| componants[i].length != bridges[i].length)
                throw new IllegalArgumentException();
        }

        net = new Cell[componants.length][componants[0].length];
        for(int i=0;i<componants.length;i++){
            for(int j=0;j<componants[i].length;i++){ 
                net[i][j] = new Cell(componants[i][j], bridges[i][j]);
            }
        }
    }
    
    public Cell getCellAt(int i,int j){
        return net[i][j];
    }
	
	public void addPipeAt(int i,int j,Pipe pipe){
		net[i][j].addComponant(pipe);
	}
	
	public void removePipeAt(int i,int j,boolean ofBridge){
		if(ofBridge)
			net[i][j].removeFromBridge();
		else
			net[i][j].removeComponant();
	}
}
