import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * Write a description of class WhackAMoleReference here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WhackAMoleReference implements Runnable {
    
    private JPanel gamePanel, controlPanel, boardPanel, mainPanel;
    private JButton start;
    private JSlider speedSlider;
    private int time, score, hiscore;
    private JLabel timer, playerScore, hiScore;
    
    // Colors used to show layers needed to properly display moles.
    private Color gameBack = new Color(0, 100, 0);
    private Color gameMid = new Color(0, 175, 0);
    private Color gameFront = new Color(0, 255, 0);
    
    @Override public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Mole Game Example");
        frame.setPreferredSize(new Dimension(500, 650));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mainPanel = new JPanel(new BorderLayout());
        
        controlPanel = new JPanel(); 
        controlPanel.setBackground(Color.RED);
        controlPanel.setPreferredSize(new Dimension(500, 25));
        
        boardPanel = new JPanel(); 
        boardPanel.setBackground(Color.BLUE);
        boardPanel.setPreferredSize(new Dimension(500, 150));
        
        gamePanel = new JPanel() {
            @Override public void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                g.setColor(gameBack);
                g.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight());
                
                g.setColor(gameMid);
                g.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight() / 2);
                
                g.setColor(gameFront);
                g.fillRect(0, 0, gamePanel.getWidth(), gamePanel.getHeight() / 4);
            }
        };
        
        mainPanel.add(boardPanel, BorderLayout.NORTH);
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);
        
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new WhackAMoleReference());
    }
    
}
