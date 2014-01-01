
import ComponantsTest.GameControllar;
import ComponantsTest.Cell;
import ComponantsTest.Dot;
import ComponantsTest.Level;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ImageIcon;
import javax.swing.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DigitalNet
 */
public class testJtable extends javax.swing.JFrame {

    public enum PipeImage {

        HRBlue(Toolkit.getDefaultToolkit().getImage(testJtable.class.getClass().getResource("/pipes/HRBlue.png"))),
        HLBlue(Toolkit.getDefaultToolkit().getImage(testJtable.class.getClass().getResource("/pipes/HLBlue.png"))),
        VUBlue(Toolkit.getDefaultToolkit().getImage(testJtable.class.getClass().getResource("/pipes/VUBlue.png"))),
        VDBlue(Toolkit.getDefaultToolkit().getImage(testJtable.class.getClass().getResource("/pipes/VDBlue.png"))),
        CDLBlue(Toolkit.getDefaultToolkit().getImage(testJtable.class.getClass().getResource("/pipes/CDLBlue.png"))),
        BlueDot(Toolkit.getDefaultToolkit().getImage(testJtable.class.getClass().getResource("/pipes/BlueDot.png"))),
        NULL(Toolkit.getDefaultToolkit().getImage(testJtable.class.getClass().getResource("/pipes/NULL.png")));
        private final Image image;

        private PipeImage(Image image) {
            this.image = image;
        }

        public Image Image() {
            return image;
        }
    }

    /**
     * Creates new form testJtable
     */
    public testJtable() {
        initComponents();
        initMyComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton00 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton01 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton02 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton03 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton23MouseReleased(evt);
            }
        });

        jButton00.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton00MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton00MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton23MouseReleased(evt);
            }
        });
        jButton00.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton00ActionPerformed(evt);
            }
        });

        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton10MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton23MouseReleased(evt);
            }
        });

        jButton11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton11MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton23MouseReleased(evt);
            }
        });

        jButton01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton01MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton01MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton23MouseReleased(evt);
            }
        });
        jButton01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton01ActionPerformed(evt);
            }
        });

        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton23MouseReleased(evt);
            }
        });

        jButton02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton02MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton02MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton23MouseReleased(evt);
            }
        });

        jButton21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton23MouseReleased(evt);
            }
        });

        jButton20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton23MouseReleased(evt);
            }
        });

        jButton22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton23MouseReleased(evt);
            }
        });

        jButton03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton23MouseReleased(evt);
            }
        });

        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton23MouseReleased(evt);
            }
        });

        jButton23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton23MouseReleased(evt);
            }
        });

        jButton33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton23MouseReleased(evt);
            }
        });

        jButton32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton23MouseReleased(evt);
            }
        });

        jButton31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton23MouseReleased(evt);
            }
        });

        jButton30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton23MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton23MouseReleased(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pipes/HBlue.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setText("jMenuItem1");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton00, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton01, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton02, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton03, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton00, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton01, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton02, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton03, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void initMyComponents() {
        Level level = new Level(new Dot[]{new Dot(Color.BLUE, 0, 0), new Dot(Color.BLUE, 3, 3)}, null, null, 4);
        gc = new GameControllar(level);
        buttons = new JButton[4][4];
        buttons[0][0] = jButton00;
        buttons[0][1] = jButton01;
        buttons[0][2] = jButton02;
        buttons[0][3] = jButton03;
        buttons[1][0] = jButton10;
        buttons[1][1] = jButton11;
        buttons[1][2] = jButton12;
        buttons[1][3] = jButton13;
        buttons[2][0] = jButton20;
        buttons[2][1] = jButton21;
        buttons[2][2] = jButton22;
        buttons[2][3] = jButton23;
        buttons[3][0] = jButton30;
        buttons[3][1] = jButton31;
        buttons[3][2] = jButton32;
        buttons[3][3] = jButton33;

        for (JButton[] btns : buttons) {
            for (JButton btn : btns) {
                btn.setIcon(new ImageIcon(PipeImage.NULL.Image()));
            }
        }

//        cells = new Cell[4][4];
//        for (Cell[] cellrow : cells) {
//            for (Cell cell : cellrow) {
//                cell = new Cell(false, false, null);
//            }
//        }
//        cells[0][0] = new Cell(false, false, new Dot(Color.BLUE, 0, 0));
//        cells[3][3] = new Cell(false, false, new Dot(Color.BLUE, 0, 0));
        for (int i = 0; i < gc.CellsPerRow(); i++) {
            final int fi = i;
            for (int j = 0; j < gc.CellsPerRow(); j++) {
                final int fj = j;
//                Cell cell = cells[i][j];
                gc.addPropertyChangeListner(new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        Cell.State state = (Cell.State) evt.getNewValue();
                        switch (evt.getPropertyName()) {
                            case "UP":
                                if (state == Cell.State.CROSS_ENTERD
                                        || state == Cell.State.ENTERD
                                        || state == Cell.State.CROSS_LEAVED
                                        || state == Cell.State.LEAVED) {
                                    buttons[fi][fj].setIcon(new ImageIcon(PipeImage.VUBlue.Image()));
                                } else {
                                    buttons[fi][fj].setIcon(new ImageIcon(PipeImage.NULL.Image()));
                                }
                                break;
                            case "DOWN":
                                if (state == Cell.State.CROSS_ENTERD
                                        || state == Cell.State.ENTERD
                                        || state == Cell.State.CROSS_LEAVED
                                        || state == Cell.State.LEAVED) {
                                    buttons[fi][fj].setIcon(new ImageIcon(PipeImage.VDBlue.Image()));
                                } else {
                                    buttons[fi][fj].setIcon(new ImageIcon(PipeImage.NULL.Image()));
                                }
                                break;
                            case "RIGHT":
                                if (state == Cell.State.CROSS_ENTERD
                                        || state == Cell.State.ENTERD
                                        || state == Cell.State.CROSS_LEAVED
                                        || state == Cell.State.LEAVED) {
                                    buttons[fi][fj].setIcon(new ImageIcon(PipeImage.HRBlue.Image()));
                                } else {
                                    buttons[fi][fj].setIcon(new ImageIcon(PipeImage.NULL.Image()));
                                }
                                break;
                            case "LEFT":
                                if (state == Cell.State.CROSS_ENTERD
                                        || state == Cell.State.ENTERD
                                        || state == Cell.State.CROSS_LEAVED
                                        || state == Cell.State.LEAVED) {
                                    buttons[fi][fj].setIcon(new ImageIcon(PipeImage.HLBlue.Image()));
                                } else {
                                    buttons[fi][fj].setIcon(new ImageIcon(PipeImage.NULL.Image()));
                                }
                                break;
                        }
                    }
                }, i, j);
            }
        }
    }
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton00ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton00ActionPerformed
        //i0 = 0;
        //j0 = 0;
        //jButton01.setIcon(new ImageIcon(getClass().getResource("blue.png")));
        //JOptionPane.showMessageDialog(rootPane, "work");
    }//GEN-LAST:event_jButton00ActionPerformed

    private void jButton23MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton23MousePressed
        if (evt.getButton() == MouseEvent.BUTTON1) {
            ismousePressed = true;

        }
    }//GEN-LAST:event_jButton23MousePressed

    private void jButton23MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton23MouseReleased
        if (evt.getButton() == MouseEvent.BUTTON1) {
            ismousePressed = false;
            i0 = -10;
            j0 = -10;
        }
    }//GEN-LAST:event_jButton23MouseReleased

    private void jButton00MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton00MouseEntered
        if (ismousePressed) {
            i = 0;
            j = 0;
            gc.add(i, j, i0, j0);
        }
    }//GEN-LAST:event_jButton00MouseEntered

    private void jButton01MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton01MouseExited
        if (ismousePressed) {
            i0 = 0;
            j0 = 1;
        }
    }//GEN-LAST:event_jButton01MouseExited

    private void jButton10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseExited
        if (ismousePressed) {
            i0 = 1;
            j0 = 0;
        }
    }//GEN-LAST:event_jButton10MouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JOptionPane.showMessageDialog(this, Cell.Side.DOWN.toString());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton01ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton01ActionPerformed

    private void jButton00MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton00MouseExited
        if (ismousePressed) {
            i0=0;        
        j0=0;
        }
    }//GEN-LAST:event_jButton00MouseExited

    private void jButton01MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton01MouseEntered
        if (ismousePressed) {
            i=0;
            j=1;
            gc.add(i, j, i0, j0);
        }       
    }//GEN-LAST:event_jButton01MouseEntered

    private void jButton02MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton02MouseExited
         if (ismousePressed) {
             i0=0;
             j0=2;
         }
    }//GEN-LAST:event_jButton02MouseExited

    private void jButton02MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton02MouseEntered
        if (ismousePressed) {
            i=0;
            j=2;
            gc.add(i, j, i0, j0);
        }    
    }//GEN-LAST:event_jButton02MouseEntered

    private void jButton10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseEntered
        if (ismousePressed) {
            i=1;
            j=0;
            gc.add(i, j, i0, j0);
        }   
    }//GEN-LAST:event_jButton10MouseEntered

    private void jButton11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseEntered
        if (ismousePressed) {
            i=1;
            j=1;
            gc.add(i, j, i0, j0);
        }   
    }//GEN-LAST:event_jButton11MouseEntered

    private void jButton11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton11MouseExited
        if (ismousePressed) {
             i0=1;
             j0=1;
        }           
    }//GEN-LAST:event_jButton11MouseExited

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(testJtable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(testJtable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(testJtable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(testJtable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new testJtable().setVisible(true);
            }
        });
    }

    private int i, j, i0, j0;
    private boolean ismousePressed;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton00;
    private javax.swing.JButton jButton01;
    private javax.swing.JButton jButton02;
    private javax.swing.JButton jButton03;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables
    private GameControllar gc;
    JButton[][] buttons;
    Cell[][] cells;
}