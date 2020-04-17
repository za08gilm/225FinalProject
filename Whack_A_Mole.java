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
    
    protected void redraw(Graphics g) { // Redraws screen
        
    }
    
    @Override
    public void run() { // Runs the program
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) { 
        // Checks if mouse is clicked over a visible mole
    }
    
    public static void main(String[] args) {
        
        javax.swing.SwingUtilities.invokeLater(new Whack_A_Mole());
    }
}
