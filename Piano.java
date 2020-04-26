// Imports for swing.
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

// Imports for files.
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// Imports for sound.
import javax.sound.sampled.*;
/**
 * Write a description of class Piano here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Piano extends KeyAdapter implements Runnable {
    
    /** Static variables */
    private static final int STARTX_NATURAL = 25;
    private static final int STARTY_NATURAL = 30;
    private static final int STARTX_SHARP = 83;
    private static final int STARTY_SHARP = 30;
    private static final int NATURAL_WIDTH = 75;
    private static final int NATURAL_HEIGHT = 300;
    private static final int SHARP_WIDTH = 40;
    private static final int SHARP_HEIGHT = 120;
    private static final Color PRESSED = new Color(185, 185, 185);
    private static final Color SHARPRGB = new Color(5, 5, 5);

    /** Instance variables. */
    private JPanel panel;
    private boolean c, d, e, f, g, a, b = false;
    private boolean csharp, dsharp, fsharp, gsharp, asharp = false;
    private int xNatural, yNatural, xSharp, ySharp;
    private java.util.List<Boolean> nat1, nat2, sharps1, sharps2;
    private JComboBox octives;
    
    /** Arrays to store note Strrings and keys to press. */
    private String[] notes = {" C ", " D ", " E ", " F ", " G ", " A ", " B ",
                              "C#", "D#", "F#", "G#", "A#"};
                              
    private String[] keys = {"(Z)", "(X)", "(C)", "(V)", "(B)", "(N)", "(M)",
                             "(S)", "(D)", "(G)", "(H)", "(J)"};

    
    @Override public void run() {
        
    }
    
    @Override public void keyPressed(KeyEvent ke) {
        // Natural Keys
        if (ke.getKeyCode() == KeyEvent.VK_Z) {
            c = true; nat1.set(0, c); panel.repaint();
        }
        if (ke.getKeyCode() == KeyEvent.VK_X) {
            d = true; nat1.set(1, d); panel.repaint();
        }
        if (ke.getKeyCode() == KeyEvent.VK_C) {
            e = true; nat1.set(2, e); panel.repaint();
        }
        if (ke.getKeyCode() == KeyEvent.VK_V) {
            f = true; nat1.set(3, f); panel.repaint();
        }
        if (ke.getKeyCode() == KeyEvent.VK_B) {
            g = true; nat2.set(0, g); panel.repaint();
        }
        if (ke.getKeyCode() == KeyEvent.VK_N) {
            a = true; nat2.set(1, a); panel.repaint();
        }
        if (ke.getKeyCode() == KeyEvent.VK_M) {
            b = true; nat2.set(2, b); panel.repaint();
        }                
        
        // Sharp keys
        if (ke.getKeyCode() == KeyEvent.VK_S) {
            csharp = true; sharps1.set(0, csharp); panel.repaint();
        }
        if (ke.getKeyCode() == KeyEvent.VK_D) {
            dsharp = true; sharps1.set(1, dsharp); panel.repaint();
        }
        if (ke.getKeyCode() == KeyEvent.VK_G) {
            fsharp = true; sharps2.set(0, fsharp); panel.repaint();
        }
        if (ke.getKeyCode() == KeyEvent.VK_H) {
            gsharp = true; sharps2.set(1, gsharp); panel.repaint();
        }
        if (ke.getKeyCode() == KeyEvent.VK_J) {
            asharp = true; sharps2.set(2, asharp); panel.repaint();
        }
    }
    
    @Override public void keyReleased(KeyEvent ke) {
        
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Piano());
    }
}
