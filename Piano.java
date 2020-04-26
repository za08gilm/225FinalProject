// Imports for swing.
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

// Imports for files.
import java.io.File;
import java.io.IOException;

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
    private JPanel panel, octavePanel;
    private boolean c, d, e, f, g, a, b = false;
    private boolean csharp, dsharp, fsharp, gsharp, asharp = false;
    private int xNatural, yNatural, xSharp, ySharp;
    private java.util.List<Boolean> nat1, nat2, sharps1, sharps2;
    private JComboBox octaves;
    
    /** Arrays to store note Strrings and keys to press. */
    private String[] notes = {" C ", " D ", " E ", " F ", " G ", " A ", " B ",
                              "C#", "D#", "F#", "G#", "A#"};
                              
    private String[] keys = {"(Z)", "(X)", "(C)", "(V)", "(B)", "(N)", "(M)",
                             "(S)", "(D)", "(G)", "(H)", "(J)"};

    
    @Override public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Piano");
        frame.setPreferredSize(new Dimension(700,500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        nat1 = new java.util.ArrayList(); sharps1 = new java.util.ArrayList();
        nat2 = new java.util.ArrayList(); sharps2 = new java.util.ArrayList();
        
        nat1.add(c); nat1.add(d); nat1.add(e); nat1.add(f); 
        nat2.add(g); nat2.add(a); nat2.add(b);
        sharps1.add(csharp); sharps1.add(dsharp);
        sharps2.add(fsharp); sharps2.add(gsharp); sharps2.add(asharp);
        
        panel = new JPanel(new BorderLayout()) {
            @Override public void paintComponent(Graphics g) {
                super.paintComponent(g);

                xNatural = STARTX_NATURAL; yNatural = STARTY_NATURAL;
                xSharp = STARTX_SHARP; ySharp = STARTY_SHARP;
                int i = 0;

                // Check the natural keys.
                for (Boolean b : nat1) {
                    if (b == true) {
                        g.setColor(PRESSED);
                        g.fillRect(xNatural, yNatural, NATURAL_WIDTH, NATURAL_HEIGHT);
                        g.setColor(Color.BLACK);
                        g.drawRect(xNatural, yNatural, NATURAL_WIDTH, NATURAL_HEIGHT);
                        g.setColor(Color.RED);
                        g.drawString(notes[i], xNatural + 30, NATURAL_HEIGHT + 5);
                        g.drawString(keys[i], xNatural + 30, NATURAL_HEIGHT + 25);
                    } else {
                        g.setColor(Color.WHITE);
                        g.fillRect(xNatural, yNatural, NATURAL_WIDTH, NATURAL_HEIGHT);
                        g.setColor(Color.BLACK);
                        g.drawRect(xNatural, yNatural, NATURAL_WIDTH, NATURAL_HEIGHT);
                        g.setColor(Color.RED);
                        g.drawString(notes[i], xNatural + 30, NATURAL_HEIGHT + 5);
                        g.drawString(keys[i], xNatural + 30, NATURAL_HEIGHT + 25);
                    }
                    xNatural += NATURAL_WIDTH; i++;
                }
                
                for (Boolean b : nat2) {
                    if (b == true) {
                        g.setColor(PRESSED);
                        g.fillRect(xNatural, yNatural, NATURAL_WIDTH, NATURAL_HEIGHT);
                        g.setColor(Color.BLACK);
                        g.drawRect(xNatural, yNatural, NATURAL_WIDTH, NATURAL_HEIGHT);
                        g.setColor(Color.RED);
                        g.drawString(notes[i], xNatural + 30, NATURAL_HEIGHT + 5);
                        g.drawString(keys[i], xNatural + 30, NATURAL_HEIGHT + 25);
                    } else {
                        g.setColor(Color.WHITE);
                        g.fillRect(xNatural, yNatural, NATURAL_WIDTH, NATURAL_HEIGHT);
                        g.setColor(Color.BLACK);
                        g.drawRect(xNatural, yNatural, NATURAL_WIDTH, NATURAL_HEIGHT);
                        g.setColor(Color.RED);
                        g.drawString(notes[i], xNatural + 30, NATURAL_HEIGHT + 5);
                        g.drawString(keys[i], xNatural + 30, NATURAL_HEIGHT + 25);
                    }
                    xNatural += NATURAL_WIDTH; i++;
                }
                xNatural = STARTX_NATURAL;
                panel.repaint();

                // Check the sharp keys.
                for (Boolean s : sharps1) {
                    if (s == true) {
                        g.setColor(PRESSED);
                        g.fillRect(xSharp, ySharp, SHARP_WIDTH, SHARP_HEIGHT);
                        g.setColor(Color.BLACK);
                        g.drawRect(xSharp, ySharp, SHARP_WIDTH, SHARP_HEIGHT);
                        g.setColor(Color.RED);
                        g.drawString(notes[i], xSharp + 15, SHARP_HEIGHT + 5);
                        g.drawString(keys[i], xSharp + 15, SHARP_HEIGHT + 25);
                    } else {
                        g.setColor(SHARPRGB);
                        g.fillRect(xSharp, ySharp, SHARP_WIDTH, SHARP_HEIGHT);
                        g.setColor(Color.BLACK);
                        g.drawRect(xSharp, ySharp, SHARP_WIDTH, SHARP_HEIGHT);
                        g.setColor(Color.RED);
                        g.drawString(notes[i], xSharp + 15, SHARP_HEIGHT + 5);
                        g.drawString(keys[i], xSharp + 15, SHARP_HEIGHT + 25);
                    }
                    xSharp += NATURAL_WIDTH; i++;
                }
                
                xSharp += NATURAL_WIDTH;
                
                for (Boolean s : sharps2) {
                    if (s == true) {
                        g.setColor(PRESSED);
                        g.fillRect(xSharp, ySharp, SHARP_WIDTH, SHARP_HEIGHT);
                        g.setColor(Color.BLACK);
                        g.drawRect(xSharp, ySharp, SHARP_WIDTH, SHARP_HEIGHT);
                        g.setColor(Color.RED);
                        g.drawString(notes[i], xSharp + 15, SHARP_HEIGHT + 5);
                        g.drawString(keys[i], xSharp + 15, SHARP_HEIGHT + 25);
                    } else {
                        g.setColor(SHARPRGB);
                        g.fillRect(xSharp, ySharp, SHARP_WIDTH, SHARP_HEIGHT);
                        g.setColor(Color.BLACK);
                        g.drawRect(xSharp, ySharp, SHARP_WIDTH, SHARP_HEIGHT);
                        g.setColor(Color.RED);
                        g.drawString(notes[i], xSharp + 15, SHARP_HEIGHT + 5);
                        g.drawString(keys[i], xSharp + 15, SHARP_HEIGHT + 25);
                    }
                    xSharp += NATURAL_WIDTH; i++;
                }
                xSharp = STARTX_SHARP; i = 0;
                panel.repaint();
            }
        };
        
        octaves = new JComboBox();
        octaves.addItem("1"); octaves.addItem("2"); octaves.addItem("3");
        octaves.addItem("4"); octaves.addItem("5"); octaves.addItem("6");
        octaves.addItem("7");
        
        octavePanel = new JPanel();
        octavePanel.add(new JLabel("Octave:"));
        octavePanel.add(octaves);
        panel.add(octavePanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.addKeyListener(this);
        frame.pack();
        frame.setVisible(true);
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
        // Natural Keys
        if (ke.getKeyCode() == KeyEvent.VK_Z) {
            c = false; nat1.set(0, c); panel.repaint();
        }
        if (ke.getKeyCode() == KeyEvent.VK_X) {
            d = false; nat1.set(1, d); panel.repaint();
        }
        if (ke.getKeyCode() == KeyEvent.VK_C) {
            e = false; nat1.set(2, e); panel.repaint();
        }
        if (ke.getKeyCode() == KeyEvent.VK_V) {
            f = false; nat1.set(3, f); panel.repaint();
        }
        if (ke.getKeyCode() == KeyEvent.VK_B) {
            g = false; nat2.set(0, g); panel.repaint();
        }
        if (ke.getKeyCode() == KeyEvent.VK_N) {
            a = false; nat2.set(1, a); panel.repaint();
        }
        if (ke.getKeyCode() == KeyEvent.VK_M) {
            b = false; nat2.set(2, b); panel.repaint();
        }                
        
        // Sharp keys
        if (ke.getKeyCode() == KeyEvent.VK_S) {
            csharp = false; sharps1.set(0, csharp); panel.repaint();
        }
        if (ke.getKeyCode() == KeyEvent.VK_D) {
            dsharp = false; sharps1.set(1, dsharp); panel.repaint();
        }
        if (ke.getKeyCode() == KeyEvent.VK_G) {
            fsharp = false; sharps2.set(0, fsharp); panel.repaint();
        }
        if (ke.getKeyCode() == KeyEvent.VK_H) {
            gsharp = false; sharps2.set(1, gsharp); panel.repaint();
        }
        if (ke.getKeyCode() == KeyEvent.VK_J) {
            asharp = false; sharps2.set(2, asharp); panel.repaint();
        }
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Piano());
    }
}
