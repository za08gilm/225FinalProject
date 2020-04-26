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
    private static final int STARTX_BASE = 25;
    private static final int STARTY_BASE = 30;
    private static final int STARTX_SHARP = 83;
    private static final int STARTY_SHARP = 30;
    private static final int BASE_WIDTH = 75;
    private static final int BASE_HEIGHT = 300;
    private static final int SHARP_WIDTH = 40;
    private static final int SHARP_HEIGHT = 120;
    private static final Color PRESSED = new Color(185, 185, 185);
    private static final Color SHARPRGB = new Color(5, 5, 5);

    /** Instance variables. */
    private JPanel panel;
    private boolean c, d, e, f, g, a, b = false;
    private boolean csharp, dsharp, fsharp, gsharp, asharp = false;
    private int xBase, yBase, xSharp, ySharp;
    private java.util.List<Boolean> base1, base2, sharps1, sharps2;
    private JComboBox octives;
    
    /** Arrays to store note Strrings and keys to press. */
    private String[] notes = {" C ", " D ", " E ", " F ", " G ", " A ", " B ",
                              "C#", "D#", "F#", "G#", "A#"};
                              
    private String[] keys = {"(Z)", "(X)", "(C)", "(V)", "(B)", "(N)", "(M)",
                             "(S)", "(D)", "(G)", "(H)", "(J)"};
    
    @Override public void run() {
        
    }
    
    @Override public void keyPressed(KeyEvent e) {
        
    }
    
    @Override public void keyReleased(KeyEvent e) {
        
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Piano());
    }
}
