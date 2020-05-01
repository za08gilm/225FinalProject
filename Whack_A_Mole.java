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
    
    @Override public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        JFrame frame = new JFrame("Whack A Mole!");
        frame.setPreferredSize(new Dimension(500, 500));
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //mainPanel = new JPanel();
        //controlPanel = new JPanel();
        
        start();
        
        mainPanel = new JPanel() {
            @Override public void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Image image = toolkit.getImage("mallet.png");
                Cursor c = toolkit.createCustomCursor(image, 
                new Point(mainPanel.getX(), mainPanel.getY()), "img");
                mainPanel.setCursor(c);
                
                int index = 0;
                while (index < moles.size()) {
                    Mole m = moles.get(index);
                    m.paintMole(g); index++;
                }
                
                mainPanel.repaint();
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
        if (e.getButton() == MouseEvent.BUTTON1) {
            Point p = e.getPoint();
            for (Mole m : moles) {
                // Check if isUp is true and if p coordinates are within 
                // mole dimensions.
            }
        }
    }
    
    public void start() {
        // Final product will construct 5 moles at diff. locations.
        // (Look at WhackAMoleReference)
        
        Mole newMole = new Mole(150, 450, mainPanel);
        moles.add(newMole);
        newMole.start();
    }
    
    public static void main(String[] args) {       
        javax.swing.SwingUtilities.invokeLater(new Whack_A_Mole());
    }
}
