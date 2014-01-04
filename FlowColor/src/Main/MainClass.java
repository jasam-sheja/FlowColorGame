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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
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
public class MainClass extends JFrame {

    private TheGamePanel gameGrid;
    private LinkedList<Player> players;
    private Timer timer;
    private Level level;

    public MainClass(Level level) {
        initMyComponent(level);
        initComponents();
        loadPlayers();
        timer.start();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        this.setTitle("game");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 390);
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
        timer = new Timer(1000, new CountUpTimer());
        timerLabel = new JLabel("time " + timeStarted);

        JPanel southPanel = new JPanel(true);
        southPanel.add(this.homeButton);
        southPanel.add(this.undoButton);
        southPanel.add(this.timerLabel);
        southPanel.add(this.movesLabel);

        this.add(southPanel, BorderLayout.SOUTH);

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenu1.setText("File");

        jMenuItem1.setText("open");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("save");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Players");

        jMenuItem3.setText("show best players");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
    }// </editor-fold> 

    private void initMyComponent(Level level) {
        this.level = level;
        gameGrid = new TheGamePanel(level);
        gameGrid.addListener(new GirdGameListener());
    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int n = Integer.parseInt(JOptionPane.showInputDialog("enter game"));
        } catch (NumberFormatException e) {

        }
    }

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int n = Integer.parseInt(JOptionPane.showInputDialog("enter number to define the game"));
        } catch (NumberFormatException e) {

        }
    }

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {
        String str = "Name         Time    Moves";
        for (Player player : players) {
            str = player.getName() + "   \t" + player.getTime() + "   \t" + player.getMoves();
        }
        JOptionPane.showMessageDialog(this, str);
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        ObjectOutputStream writeObject;
        try {
            File file = new File("bestPlayers.pwaq");
            if (!file.exists()) {
                file.createNewFile();
            }
            writeObject = new ObjectOutputStream(new FileOutputStream(file, false));
            writeObject.writeObject(players);
            writeObject.flush();
            writeObject.close();

        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
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
                new MainClass(GReader.levelsReader(1, "LevelTest.bin")).setVisible(true);
            }
        });
    }

    private JLabel timerLabel;
    private long timeStarted;
    private JLabel movesLabel;
    private JButton undoButton;
    private JButton homeButton;

    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;

    private void loadPlayers() {
        ObjectInputStream readObject;
        try {
            readObject = new ObjectInputStream(new FileInputStream("bestPlayers.pwaq"));
            players = (LinkedList<Player>) readObject.readObject();
            readObject.close();
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            players = new LinkedList<>();
        }
    }

    private class CountUpTimer implements ActionListener {

        public CountUpTimer() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            timeStarted++;
            timerLabel.setText("time " + timeStarted);

        }
    }

    private class GirdGameListener implements PropertyChangeListener {

        private int moves;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt.getPropertyName().equalsIgnoreCase("moves")) {
                moves = (int) evt.getNewValue();
                movesLabel.setText("moves " + evt.getNewValue());
            } else if (evt.getPropertyName().equalsIgnoreCase("finished")) {
                JOptionPane.showMessageDialog(null, "finished \n moves " + moves + "\ntime " + timeStarted);
                timer.stop();
                boolean shouldAdd = players.size() < 10;
                for (Player player : players) {
                    if (timeStarted < player.getTime()
                            || timeStarted == player.getTime() && moves < player.getMoves()) {
                        shouldAdd = true;
                        break;
                    }
                }
                if (shouldAdd) {
                    String name = JOptionPane.showInputDialog("you are one of the best 10\n enter your name");
                    players.add(new Player(name, moves, timeStarted));
                    if (players.size() > 10) {
                        players.remove(players.getLast());
                    }
                }
            }
        }
    }
}
