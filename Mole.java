import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * Write a description of class Mole here.
 *
 * @author Zack, Mark, Roeldi, Colin, Patrick
 * @version Spring 2020
 */
public class Mole extends Thread {
    
    public static final int DELAY_TIME = 33; // Delay time between animations.
    public static final int ABOVE_TIME = 3000; // Time spent above hole.
    public static final int MOLE = 200;
    
    protected boolean bonked; // Was mole hit?
    protected int score; // How many moles were hit?
    protected boolean escaped; // Did mole escape unharmed?
    protected boolean moveUp; // Mole move up
    protected boolean moveDown; // Mole move down
    
    protected JComponent container;
    
    public Mole(JComponent container) {
        this.container = container;
    }
    
    public void run() {
        
    }
    
    public void paint(Graphics g) {
        if (!bonked) {
            // Paint a mole that was not hit.
        } else {
            // Show that mole was hit (X's for eyes)
        }
    }
    
    public boolean bonked() {
        return bonked;
    }
    
    public boolean escaped() {
        return escaped;
    }
    
    public int score() {
        return score;
    }
    
}
