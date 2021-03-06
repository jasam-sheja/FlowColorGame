/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Componants.Bridge;
import Componants.Hall;
import Componants.CellPanel;
import Componants.Dot;
import Componants.Level;
import Componants.TheBuilderPanel;
import InputOutFiles.GReader;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import InputOutFiles.GWriter;

/**
 *
 * @author Wissam
 */
public class LevelBuilder extends javax.swing.JFrame {

    /**
     * Creates new form LevelBuilder
     */
    private TheBuilderPanel panel;
    private JButton color;
    private JButton build;
    private JButton hole;
    private JButton bridge;
    private JButton remover;
    static private JColorChooser colorChooser;
    private JPanel colorGUI;
    private static int level;
    private final ArrayList<Dot> dots = new ArrayList<>();
    private int levelNumber = 0;
    private boolean holePrased;
    private boolean bridgePrased;

    public LevelBuilder() {
        //initComponents();
        initMyComponents();
        initComponentsN();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initComponentsN() {
        this.setTitle("GameBuilder");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 400);
        this.setResizable(false);
        this.add(panel, BorderLayout.CENTER);
        JPanel southPanel = new JPanel(true);
        JPanel northPanel = new JPanel(true);
        southPanel.add(color);
        northPanel.add(build);
        southPanel.add(hole);
        northPanel.add(remover);
        southPanel.add(bridge);
        this.add(northPanel, BorderLayout.NORTH);
        this.add(southPanel, BorderLayout.SOUTH);
//        JPanel eastPanel =  new JPanel(true) ;
//        eastPanel.add(hole) ;
//        eastPanel.add(bridge) ;
//        this.add(eastPanel,BorderLayout.NORTH);

    }

    private void initMyComponents() {
        panel = new TheBuilderPanel(level);
        panel.addListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                CellPanel butten;
                if ("MatrixAndColor".equalsIgnoreCase(evt.getPropertyName())) {
                    CellPanel[][] mybuttons;
                    butten = (CellPanel) evt.getNewValue();
                    Color colors;
                    mybuttons = (CellPanel[][]) evt.getOldValue();
                    colors = colorChooser.getColor();
                    if ((!hole.isEnabled()) && (!butten.isDrawDot()) && (!butten.isDrawBridge())) {
                        if (butten.isDrawHall()) {
                            butten.setDrawHall(false);
                            hole.setEnabled(true);
                            holePrased = false;
                        } else if (holePrased) {
                            butten.setDrawHall(true);
                            hole.setEnabled(false);
                            holePrased = false;
                        }
                    } else if ((!bridge.isEnabled()) && (!butten.isDrawDot()) && (!butten.isDrawHall())) {
                        if (butten.isDrawBridge()) {
                            butten.setDrawBridge(false);
                            bridge.setEnabled(true);
                            bridgePrased = false;

                        } else if (bridgePrased) {
                            butten.setDrawBridge(true);
                            bridge.setEnabled(false);
                            bridgePrased = false;
                        }

                    } else if ((!bridgePrased) && (!holePrased) && (!butten.isDrawBridge()) && (!butten.isDrawHall())) {
                        if (getColorNumber(mybuttons, colors) == 0) {
                            if (!butten.isDrawDot()) {
                                butten.setDrawDot(true);
                                butten.setColor(colors);
                                color.setEnabled(false);
                            }
                        } else if (getColorNumber(mybuttons, colors) == 2) {
                            if (butten.isDrawDot() && (colors == butten.getColor(true))) {
                                butten.setDrawDot(false);
                                butten.setColor(null);
                                color.setEnabled(false);
                            } else {
                                color.setEnabled(true);
                            }
                        } else if (getColorNumber(mybuttons, colors) == 1) {
                            if ((butten.isDrawDot()) && (colors == butten.getColor(true))) {
                                butten.setDrawDot(false);
                                butten.setColor(null);
                                color.setEnabled(true);
                            } else {
                                butten.setDrawDot(true);
                                butten.setColor(colors);
                                color.setEnabled(true);
                            }
                        }
                    }
                }

            }

            private int getColorNumber(CellPanel[][] mybuttons, Color color) {
                int number = 0;
                for (CellPanel[] mybutton : mybuttons) {
                    for (int j = 0; j < mybuttons[0].length; j++) {
                        if (mybutton[j].getColor(true) == color) {
                            number++;
                        }
                    }
                }
                return number;
            }
        });
        colorChooser = new JColorChooser();
        AbstractColorChooserPanel[] p = colorChooser.getChooserPanels();
        p[0].setBorder(new TitledBorder(p[0].getDisplayName()));
        JPanel tempP = new JPanel();
        tempP.add(p[0]);
        colorGUI = new JPanel(new BorderLayout(2, 2));
        colorGUI.add(tempP, BorderLayout.CENTER);
        build = new JButton("Build");

        build.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CellPanel[][] mybuttons = panel.getMybuttons();
                Bridge bridgeS = null;
                Hall holeS = null;
                for (int i = 0; i < mybuttons.length; i++) {
                    for (int j = 0; j < mybuttons[0].length; j++) {
                        if (mybuttons[i][j].isDrawDot()) {
                            dots.add(new Dot(mybuttons[i][j].getColor(true), i, j));
                            mybuttons[i][j].setDrawDot(false);
                            mybuttons[i][j].setColor(null);
                        } else if (mybuttons[i][j].isDrawBridge()) {
                            bridgeS = new Bridge(i, j);
                            mybuttons[i][j].setDrawBridge(false);

                        } else if (mybuttons[i][j].isDrawHall()) {
                            holeS = new Hall(i, j);
                            mybuttons[i][j].setDrawHall(false);
                        }
                    }

                }

                connectDots(dots);
                Dot dotsA[] = new Dot[dots.size()];
                dots.toArray(dotsA);
                levelNumber = GReader.levelsNumberReader("LevelNumber.bin");
                System.out.println(levelNumber);
                GWriter.levelsWriter(new Level(dotsA, bridgeS, holeS, level, levelNumber), "LevelFile.bin");
                System.out.println(levelNumber);
                ++levelNumber;
                GWriter.levelsNumberWriter(levelNumber, "LevelNumber.bin");
                dots.removeAll(dots);

            }

            private void connectDots(ArrayList<Dot> dots) {
                for (Dot dot : dots) {
                    for (Dot dot2 : dots) {
                        if ((dot.color == dot2.color) && (dot != dot2)) {
                            dot.connectDots(dot2);
                        }
                    }

                }
            }
        });

        color = new JButton("Color");

        color.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JOptionPane.showMessageDialog(null, colorGUI);
            }
        });

        hole = new JButton("Hole");
        hole.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                holePrased = true;
                hole.setEnabled(false);
            }
        });

        bridge = new JButton("Bridge");
        bridge.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                bridgePrased = true;
                bridge.setEnabled(false);
            }
        });
        remover = new JButton("Remove");
        remover.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int levelo = Integer.parseInt(JOptionPane.showInputDialog("plz enter the level"));
                GWriter.levelsRemover(levelo, "LevelFile.bin");
            }
        });

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
            java.util.logging.Logger.getLogger(LevelBuilder.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    level = Integer.parseInt(JOptionPane.showInputDialog("plz enter the level"));
                    try {
                        if ((level < 5)) {
                            throw new IllegalArgumentException();
                        }
                    } catch (IllegalArgumentException ex) {
                        System.err.println("wrong in level input number");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    System.err.println("wrong in level input");
                    return;
                }

                new LevelBuilder().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
