package Componants;

/**
 *
 * @author DigitalNet
 */
import InputOutFiles.GReader;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jasam sheja
 */
public class TheGamePanel extends javax.swing.JFrame {

    /**
     * Creates new form testJtable
     */
    public TheGamePanel() {
        initMyComponents();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setTitle("Calculator");
        setSize(300, 320);
        setLocationRelativeTo(null);

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("File");

        jMenuItem1.setText("jMenuItem1");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.DARK_GRAY);
        add(centerPanel, BorderLayout.CENTER);
        GridLayout gridLayout = new GridLayout(mybuttons.length, mybuttons.length, 0, 0);
        centerPanel.setLayout(gridLayout);
        for (CellPanel[] buttons : mybuttons) {
            for (CellPanel button : buttons) {
                centerPanel.add(button);
            }
        }

    }// </editor-fold>                        

    private void initMyComponents() {
//        Dot[] dots = new Dot[4];
//        dots[0] = new Dot(Color.BLUE, 0, 0, Color.BLUE, 0, 3);
//        dots[1] = dots[0].next;
//        dots[2] = new Dot(Color.yellow, 2, 2, Color.yellow, 1, 1);
//        dots[3] = dots[2].next;
//        level = new Level(dots, null, null, 5, 1);
        level = GReader.levelsReader(1, "LevelTest.bin");
        Dot[] dots =  level.getDots() ;
        gc = new GameControllar(level);

        for (int i = 0; i < gc.CellsPerRow(); i++) {
            final int fi = i;
            for (int j = 0; j < gc.CellsPerRow(); j++) {
                final int fj = j;
//                Cell cell = cells[i][j];
                gc.addPropertyChangeListner(new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        Cell.State state = (Cell.State) evt.getNewValue();
                        Cell thecell = (Cell) evt.getOldValue();
                        boolean hasPipe = state == Cell.State.CROSS_ENTERD
                                || state == Cell.State.ENTERD
                                || state == Cell.State.CROSS_LEAVED
                                || state == Cell.State.LEAVED;
                        Color color = null;
                        switch (evt.getPropertyName()) {
                            case "UP":
                                if (hasPipe) {
                                    color = thecell.getColor(Cell.Side.UP);
                                }
                                mybuttons[fi][fj].setDrawUp(hasPipe, color);
                                break;
                            case "DOWN":
                                if (hasPipe) {
                                    color = thecell.getColor(Cell.Side.DOWN);
                                }
                                mybuttons[fi][fj].setDrawDown(hasPipe, color);
                                break;
                            case "RIGHT":
                                if (hasPipe) {
                                    color = thecell.getColor(Cell.Side.RIGHT);
                                }
                                mybuttons[fi][fj].setDrawRight(hasPipe, color);
                                break;
                            case "LEFT":
                                if (hasPipe) {
                                    color = thecell.getColor(Cell.Side.LEFT);
                                }
                                mybuttons[fi][fj].setDrawLeft(hasPipe, color);
                                break;
                        }
                    }
                }, i, j);
            }
        }

        mybuttons = new CellPanel[level.getLength()][level.getLength()];
        int k = 0;
        for (int l = 0; l < mybuttons.length; l++) {
            final int fi = l;
            for (int m = 0; m < mybuttons.length; m++) {
                final int fj = m;
                if ((k < dots.length) && (dots[k].x == l && dots[k].y == m)) {
                    mybuttons[l][m] = new CellPanel(l, m, true, false, false, dots[k].color);
                    k++;
                } else if (level.getBridge() != null && level.getBridge().x == l && level.getBridge().y == m) {
                    mybuttons[l][m] = new CellPanel(l, m, false, true, false, null);
                } else if (level.getHall() != null && level.getHall().x == l && level.getHall().y == m) {
                    mybuttons[l][m] = new CellPanel(l, m, false, false, true, null);
                } else {
                    mybuttons[l][m] = new CellPanel(l, m, false, false, false, null);
                }
                mybuttons[l][m].addMouseListener(new myCellPanelListener(l, m) {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        if (ismousePressed) {
                            canAdd = (Math.abs(mybuttons[fi][fj].getRowIndex() - i0) + Math.abs(mybuttons[fi][fj].getColomunIndex() - j0)) == 1;
                            if(canAdd){
                                try {
                                    if (!gc.add(mybuttons[fi][fj].getRowIndex(), mybuttons[fi][fj].getColomunIndex(), i0, j0)) {
                                        canAdd = false;
                                    }
                                } catch (IllegalArgumentException e) {
                                    if ("moving horezentaly or verticaly only".equals(e.getMessage())) {
                                        canAdd = false;
                                    }
                                }
                            }
                        } 
                    }

                    @Override
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        if (ismousePressed && canAdd) {
                            i0 = mybuttons[fi][fj].getRowIndex();
                            j0 = mybuttons[fi][fj].getColomunIndex();
                        }
                    }

                    @Override
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        if (evt.getButton() == MouseEvent.BUTTON1) {
                            ismousePressed = true;
                            i0 = mybuttons[fi][fj].getRowIndex();
                            j0 = mybuttons[fi][fj].getColomunIndex();
                            canAdd = true;
                            color = mybuttons[fi][fj].getColor(true);
                            gc.clearPath(i0, j0);                            
                        }
                    }

                    @Override
                    public void mouseReleased(java.awt.event.MouseEvent evt) {
                        if (evt.getButton() == MouseEvent.BUTTON1) {
                            i0 = -10;
                            j0 = -10;
                            ismousePressed = false;
                            canAdd = false;
                            color = null;
                        }
                    }
                });

            }
        }
    }

    /**
     * @param args the command line arguments
     */
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
                new TheGamePanel().setVisible(true);
            }
        });
    }

    private int i, j, i0=-10, j0=-10;
    private boolean ismousePressed;
    private boolean canAdd;
    private Color color;
    // Variables declaration - do not modify                     
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration                   
    private GameControllar gc;
    CellPanel[][] mybuttons;
    Level level;

    private class myCellPanelListener extends MouseAdapter {

        private final int i, j;
        boolean ispressed;

        public myCellPanelListener(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public void setIsPressed(boolean bool) {
            ispressed = bool;
        }

        @Override
        public void mouseExited(MouseEvent e) {
            super.mouseExited(e); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            super.mouseEntered(e); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e); //To change body of generated methods, choose Tools | Templates.
        }

    }
}
