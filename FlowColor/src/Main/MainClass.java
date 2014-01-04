

package Main;

import Componants.*;
import InputOutFiles.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author DigitalNet
 */
public class MainClass extends JFrame{
    
    private TheGamePanel gameGrid;
    private Timer timer;
    private Level level;

    public MainClass(Level level) {
        this.level = level;
        gameGrid = new TheGamePanel(level);
        gameGrid.addListener(new GirdGameListener());
        initComponents();
        
        timer.start();
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {        
        this.setTitle("game");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 320);
        this.setResizable(false);
        
        this.add(gameGrid, BorderLayout.CENTER);
        
        undoButton = new JButton("undo");
        undoButton.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 gameGrid.getGameControllar().undo();
                 gameGrid.increaseMoves();
                 gameGrid.repaint();
             } 
});
        homeButton = new JButton("home");
        movesLabel = new JLabel("moves 0");
        timer =  new Timer(1000, new CountUpTimer()) ; 
        timerLabel = new JLabel("time "+timeStarted);    
        
        
        JPanel southPanel = new JPanel(true);
        southPanel.add(this.homeButton);        
        southPanel.add(this.undoButton);
        southPanel.add(this.timerLabel);
        southPanel.add(this.movesLabel);
        
        this.add(southPanel,BorderLayout.SOUTH);
    }// </editor-fold> 
    
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
                new MainClass(GReader.levelsReader(1, "LevelTest.bin")).setVisible(true);
            }
        });
    }
    
    JLabel timerLabel;
    long timeStarted;
    JLabel movesLabel;
    JButton undoButton;
    JButton homeButton;
    
    
    
    
    
    private class CountUpTimer implements ActionListener {

        public CountUpTimer() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
             timeStarted++   ;
             timerLabel.setText("time "+timeStarted);
             
            
        }
    }
    
    private class GirdGameListener implements PropertyChangeListener {
        private int moves;
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if(evt.getPropertyName().equalsIgnoreCase("moves")){
                moves = (int)evt.getNewValue();
                movesLabel.setText("moves "+evt.getNewValue());
            } else if(evt.getPropertyName().equalsIgnoreCase("finished")){
                JOptionPane.showMessageDialog(null, "finished \n moves "+ moves + "\ntime " +timeStarted);
                timer.stop();
            }
        }       
    }
}



