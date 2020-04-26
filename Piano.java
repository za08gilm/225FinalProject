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
