package Componants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;

/**
 *
 * @author jasam sheja
 */
public class CellPanel extends javax.swing.JPanel {

    public CellPanel(int rowIndex, int colomunIndex, boolean drawDot, boolean drawBridge, boolean drawHall, Color color) {
        this(rowIndex, colomunIndex,
                false, false, false, false,
                drawDot, drawBridge, drawHall,
                color, color);
    }

    public CellPanel(int rowIndex, int colomunIndex, boolean drawUp, boolean drawDown, boolean drawRight, boolean drawLeft, boolean drawDot, boolean drawBridge, boolean drawHall, Color horizentalColor, Color verticalColor) {
        initComponents();
        this.rowIndex = rowIndex;
        this.colomunIndex = colomunIndex;
        this.drawUp = drawUp;
        this.drawDown = drawDown;
        this.drawRight = drawRight;
        this.drawLeft = drawLeft;
        this.drawDot = drawDot;
        this.drawBridge = drawBridge;
        this.drawHall = drawHall;
        boolean shouldHaveColor = drawUp || drawDown || drawRight || drawLeft || drawDot;
        if (shouldHaveColor) {
            if (!drawBridge && !drawHall) {
                this.setColor(horizentalColor);
            } else if (drawBridge) {
                this.setColor(horizentalColor, true);
                this.setColor(verticalColor, false);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="Indexes getting">
    public int getRowIndex() {
        return rowIndex;
    }

    public int getColomunIndex() {
        return colomunIndex;
    }
    // </editor-fold>  

    // <editor-fold defaultstate="collapsed" desc="Set pipes">  
    public void setDrawUp(boolean drawUp, Color color) {
        this.drawUp = drawUp;
        if (drawUp) {
            if (drawBridge) {
                setColor(color, false);
            } else {
                setColor(color);
            }
        }
    }

    public void setDrawDown(boolean drawDown, Color color) {
        this.drawDown = drawDown;
        if (drawDown) {
            if (drawBridge) {
                setColor(color, false);
            } else {
                setColor(color);
            }
        }
    }

    public void setDrawRight(boolean drawRight, Color color) {
        this.drawRight = drawRight;
        if (drawRight) {
            if (drawBridge) {
                setColor(color, true);
            } else {
                setColor(color);
            }
        }
    }

    public void setDrawLeft(boolean drawLeft, Color color) {
        this.drawLeft = drawLeft;
        if (drawLeft) {
            if (drawBridge) {
                setColor(color, true);
            } else {
                setColor(color);
            }
        }
    }
    // </editor-fold>  

    // <editor-fold defaultstate="collapsed" desc="Set Size">  
    @Override
    public void setSize(Dimension d) {
        setSize(d.width);
    }

    @Override
    public void setSize(int width, int height) {
        setSize(width);
    }

    public void setSize(int width) {
        super.setSize(width, width);
        super.setPreferredSize(new Dimension(width, width));
        super.setMaximumSize(new Dimension(width, width));
        super.setMinimumSize(new Dimension(width, width));
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Set Colors">  
    public final void setColor(Color color, boolean horizental) {
        if (horizental) {
            colorHorizental = color;
        } else {
            colorVertical = color;
        }
    }

    public final void setColor(Color color) {
        if (drawBridge) {
            throw new IllegalArgumentException("unknow which color to assigne");
        }
        if (drawHall) {
            throw new IllegalArgumentException("this should have no color");
        }
        setColor(color, true);
        setColor(color, false);
    }
    // </editor-fold>  

    // <editor-fold defaultstate="collapsed" desc="Get Colors">  
    public final Color getColor(boolean horizental) {
        return horizental ? colorHorizental : colorVertical;
    }

    // </editor-fold>  
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);

        int n = 0, np = 0;

        g2d.setColor(Color.LIGHT_GRAY);   //bordor color     
        if (drawDot) {
            drawDot(g2d);
            n++;
        }

        if (drawHall) {
            drawHall(g2d);
            n++;
        }
        if (drawUp) {
            drawUpPipe(g2d);
            np++;
        }
        if (drawDown) {
            drawDownPipe(g2d);
            np++;
        }
        if (drawRight) {
            drawRightPipe(g2d);
            np++;
        }
        if (drawLeft) {
            drawLeftPipe(g2d);
            np++;
        }
        if (drawBridge) {
            drawCross(g2d);
            n++;
        }
        this.repaint();//show the result
        if (n > 1) {
            throw new IllegalArgumentException("in cell there should be either a dot or a hall or a bridge");
        }
        if (!drawBridge && np > 2) {
            throw new IllegalArgumentException("no more than 2 pipe in this cell");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="objects drawing methods">  
    private void drawHall(Graphics2D g2d) {
        int factor = Math.max(getSize().width, getSize().height);
        int width = 2 * factor / 3;
        int height = 2 * factor / 3;
        int x = factor / 2 - width / 2;
        int y = factor / 2 - height / 2;
        g2d.setColor(Color.GRAY);
        g2d.fillRoundRect(x, y, width, height, width, height);
        factor /= 2;
        width = 2 * factor / 3;
        height = 2 * factor / 3;
        x = factor - width / 2;
        y = factor - height / 2;
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRoundRect(x, y, width, height, width, height);
        factor /= 2;
        width = 2 * factor / 3;
        height = 2 * factor / 3;
        x = 2 * factor - width / 2;
        y = 2 * factor - height / 2;
        g2d.setColor(Color.BLACK);
        g2d.fillRoundRect(x, y, width, height, width, height);
    }

    private void drawDot(Graphics2D g2d) {
        int factor = Math.max(getSize().width, getSize().height);

        g2d.setColor(Color.LIGHT_GRAY);   //bordor color     
        int width = 2 * factor / 3;
        int height = 2 * factor / 3;
        int x = factor / 2 - width / 2;
        int y = factor / 2 - height / 2;
        g2d.drawRoundRect(x, y, width, height, width, height);
        g2d.setColor(colorVertical);
        g2d.fillRoundRect(x, y, width, height, width, height);
    }

    private void drawCross(Graphics2D g2d) {
        int x;
        int y;
        int width;
        int height;
        int factor = Math.max(getSize().width, getSize().height);
        Color horizentalColor = (drawRight || drawLeft) ? colorHorizental : Color.GRAY;
        Color verticalColor = (drawUp || drawDown) ? colorVertical : Color.GRAY;

        height = factor / 3;
        width = factor / 3;

        g2d.setColor(horizentalColor);
        x = 0;
        y = factor / 2 - height / 2;
        g2d.drawLine(x, y, factor / 2 - width / 2, y);
        y = factor / 2 + height / 2;
        g2d.drawLine(x, y, factor / 2 - width / 2, y);

        x = factor;
        y = factor / 2 - height / 2;
        g2d.drawLine(x, y, factor / 2 + width / 2, y);
        y = factor / 2 + height / 2;
        g2d.drawLine(x, y, factor / 2 + width / 2, y);

        g2d.setColor(verticalColor);
        y = 0;
        x = factor / 2 - height / 2;
        g2d.drawLine(x, y, x, factor / 2 - width / 2);
        x = factor / 2 + height / 2;
        g2d.drawLine(x, y, x, factor / 2 - width / 2);

        y = factor;
        x = factor / 2 - height / 2;
        g2d.drawLine(x, y, x, factor / 2 + width / 2);
        x = factor / 2 + height / 2;
        g2d.drawLine(x, y, x, factor / 2 + width / 2);

    }

    private void drawUpPipe(Graphics2D g2d) {
        int factor = Math.max(getSize().width, getSize().height);
        int width = factor / 3;
        int height = drawBridge ? factor : factor / 2 + width / 2;
        int x = factor / 2 - width / 2;
        int y = 0;
        g2d.drawRect(x, y, width, height);
        g2d.setColor(colorVertical);
        g2d.fillRect(x, y, width, height);
    }

    private void drawDownPipe(Graphics2D g2d) {
        int factor = Math.max(getSize().width, getSize().height);
        int width = factor / 3;
        int height = drawBridge ? factor : factor / 2 + width / 2;
        int x = factor / 2 - width / 2;
        int y = factor / 2 - width / 2;
        g2d.drawRect(x, y, width, height);
        g2d.setColor(colorVertical);
        g2d.fillRect(x, y, width, height);
    }

    private void drawRightPipe(Graphics2D g2d) {
        int factor = Math.max(getSize().width, getSize().height);
        int height = factor / 3;
        int width = drawBridge ? factor : factor / 2 + height / 2;
        int x = factor / 2 - height / 2;
        int y = factor / 2 - height / 2;
        g2d.drawRect(x, y, width, height);
        g2d.setColor(colorHorizental);
        g2d.fillRect(x, y, width, height);
    }

    private void drawLeftPipe(Graphics2D g2d) {
        int factor = Math.max(getSize().width, getSize().height);
        int height = factor / 3;
        int width = drawBridge ? factor : factor / 2 + height / 2;
        int x = 0;
        int y = factor / 2 - height / 2;
        g2d.drawRect(x, y, width, height);
        g2d.setColor(colorHorizental);
        g2d.fillRect(x, y, width, height);
    }
    // </editor-fold> 

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TheGamePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame j = new JFrame("test");
                j.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                j.setSize(300, 320);
                CellPanel cellPanel = new CellPanel(0, 0, false, true, false, null);
                cellPanel.setSize(50);

                cellPanel.setDrawUp(true, Color.BLUE);
                //cellPanel.setDrawDown(true, Color.BLUE);
                j.add(cellPanel);
                j.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
//indexes
    private final int rowIndex;
    private final int colomunIndex;

    //pipes to draw
    private boolean drawUp;
    private boolean drawDown;
    private boolean drawRight;
    private boolean drawLeft;

    //componants to drow
    private boolean drawDot;

    public boolean isDrawDot() {
        return drawDot;
    }

    public void setDrawDot(boolean drawDot) {
        this.drawDot = drawDot;
    }
    private boolean drawBridge;

    public boolean isDrawBridge() {
        return drawBridge;
    }

    public void setDrawBridge(boolean drawBridge) {
        this.drawBridge = drawBridge;
    }

    public boolean isDrawHall() {
        return drawHall;
    }

    public void setDrawHall(boolean drawHall) {
        this.drawHall = drawHall;
    }
    private boolean drawHall;

    //colors of the pipes
    Color colorHorizental;
    Color colorVertical;
}
