/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flowcolor;

/**
 *
 * @author DigitalNet
 */
public interface PlayerGetable extends Comparable<PlayerGetable>{

    public String getName();

    public int getMoves();

    public long getTime();
}
