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
    int x,y;
    public myImage(PipeImage image){
        this.image = image;
        x=10;
        y=10;
        this.setSize(100, 100);
        this.setVisible(true);
    }
    @Override
    public void paint(Graphics g){
        g.drawImage(image.Image(), x, y, this);
        this.getParent().repaint();
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
