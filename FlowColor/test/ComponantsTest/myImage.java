/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ComponantsTest;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author DigitalNet
 */
public class myImage extends Component{
    public enum PipeImage {

        HRBlue(Toolkit.getDefaultToolkit().getImage(myImage.class.getClass().getResource("/pipes/HRBlue.png"))),
        HLBlue(Toolkit.getDefaultToolkit().getImage(myImage.class.getClass().getResource("/pipes/HLBlue.png"))),
        VUBlue(Toolkit.getDefaultToolkit().getImage(myImage.class.getClass().getResource("/pipes/VUBlue.png"))),
        VDBlue(Toolkit.getDefaultToolkit().getImage(myImage.class.getClass().getResource("/pipes/VDBlue.png"))),
        CDLBlue(Toolkit.getDefaultToolkit().getImage(myImage.class.getClass().getResource("/pipes/CDLBlue.png"))),
        BlueDot(Toolkit.getDefaultToolkit().getImage(myImage.class.getClass().getResource("/pipes/BlueDot.png"))),
        NULL(Toolkit.getDefaultToolkit().getImage(myImage.class.getClass().getResource("/pipes/NULL.png")));
        private final Image image;

        private PipeImage(Image image) {
            this.image = image;
        }

        public Image Image() {
            return image;
        }
    }
    PipeImage image;
    public myImage(PipeImage image){
        this.image = image;
        this.setBounds(1, 1, 100, 100);
        this.setVisible(true);
    }
    
    public myImage setXY(int x,int y){
        this.setLocation(x, y);
        return this;
    }
    
    public myImage setDimension(int width,int height){
        super.setSize(width, height);
        return this;
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(image.Image(), this.getX(), this.getY(), this.getParent());
        
    }   

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof myImage){
            myImage temp = (myImage)obj;
            return this.image == temp.image;
        }
        else
            return false;
    }
    
}
