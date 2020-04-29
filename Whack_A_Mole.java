import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * Write a description of class Whack_A_Mole here.
 *
 * @author Zack, Mark, Roeldi, Colin, Patrick
 * @version Spring 2020
 */
public class Whack_A_Mole extends MouseAdapter implements Runnable  
{
    // Instance variables go here
    private JPanel mainPanel; //gamePanel, controlPanel;
    protected java.util.List<Mole> moles = new java.util.ArrayList<Mole>();
    private JSlider speedSlider;
    private JButton startButton;
    private int hiscore = 10;
        
    protected void redraw(Graphics g) { // Redraws screen (may remove)
        for (Mole m : moles) {
            m.paintMole(g);
        }
    }
    
    @Override public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        JFrame frame = new JFrame("Whack A Mole!");
        frame.setPreferredSize(new Dimension(500, 500));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //mainPanel = new JPanel();
        //controlPanel = new JPanel();
        
        mainPanel = new JPanel() {
            @Override public void paintComponent(Graphics g) {
                super.paintComponent(g);
               
                for (int i = 0; i < 1; i++) {
                    Mole mole = new Mole(100, 100, false, mainPanel);
                    moles.add(mole);
                    mole.start();
                }
                
                for (Mole m : moles) {
                    m.paintMole(g);
                }
            }
        };
        
        //mainPanel.add(controlPanel);
        //mainPanel.add(gamePanel);
        
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
    
    @Override public void mouseClicked(MouseEvent e) { 
        // Checks if mouse is clicked over a visible mole
    }
    
    public static void main(String[] args) {       
        javax.swing.SwingUtilities.invokeLater(new Whack_A_Mole());
    }
}
