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
public class Nothing extends Componant implements Removable{
	public Nothing(){
		super((byte)63,Color.GRAY);//63=111111 in binary
	}
}
