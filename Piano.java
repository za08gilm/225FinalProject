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
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
/**
A program that creates an interactive piano. Due to limitations with space and to
make the program more efficient, only one octave can be played at a time.

@author Zack Gilman, Patrick Baumgardner, Colin Palladino, Mark Smath, Roeldi Staro
@version Spring 2020
 */
public class Piano extends KeyAdapter implements Runnable, ActionListener {

    /** Static variables */
    private static final int STARTX_NATURAL = 25; // Starting x-value for the natural keys.
    private static final int STARTY_NATURAL = 30; // Starting y-value for the natural keys.
    private static final int STARTX_SHARP = 83; // Starting x-value for the sharp/flat keys.
    private static final int STARTY_SHARP = 30; // Starting y-value for the sharp/flat keys.
    private static final int NATURAL_WIDTH = 75; // Width of the natural keys.
    private static final int NATURAL_HEIGHT = 300; // Height of the natural keys.
    private static final int SHARP_WIDTH = 40; // Width of the sharp/flat kays.
    private static final int SHARP_HEIGHT = 120; // Height of the sharp/flat keys.
    private static final Color PRESSED = new Color(185, 185, 185); // Color of key when pressed (grey).
    private static final Color SHARPRGB = new Color(5, 5, 5); // Color of sharo/flat keys when not pressed (black).
    private static final int NOTE_NUM = 12; // Number of notes per octave.

    /** Instance variables. */
    private JFrame frame; // Frame
    private JPanel pianoPanel, octavePanel, mainPanel; // Panels
    private boolean c, d, e, f, g, a, b = false; // Booleans stored in isPressed[]
    private boolean csharp, dsharp, fsharp, gsharp, asharp = false; // Booleans stored in isPressed[]
    private int xNatural, yNatural, xSharp, ySharp; // Values used to help draw piano.
    private java.util.List<Boolean> nat1, nat2, sharps1, sharps2; // Lists of 3-4 notes each (makes it more reliable to draw).
    private JComboBox octaves; // Allows user to switch between different octaves.
    private AudioInputStream ais; // Used when creating sound clips.
    private Clip clip; // Used to play a note sound when a key is pressed.

    /** Array of Strings for each note name. */
    private String[] notes = {" C ", " D ", " E ", " F ", " G ", " A ", " B ",
            "C#", "D#", "F#", "G#", "A#"};

    /** Array of Strings for what key to press for each note. */
    private String[] keys = {"(Z)", "(X)", "(C)", "(V)", "(B)", "(N)", "(M)",
            "(S)", "(D)", "(G)", "(H)", "(J)"};

    /** Array that holds the names of the files for each note of the current octave. */
    private String[] noteFiles = {"C3.aif", "D3.aif", "E3.aif", "F3.aif", "G3.aif",
            "A3.aif", "B3.aif", "C#3.aif", "D#3.aif", "F#3.aif","G#3.aif", "A#3.aif"};
    
    /** Array of the sound clips of the notes in the current octave. */        
    private Clip[] noteClips = new Clip[NOTE_NUM];
    
    /** Array that checks each note to see if it is pressed. (used  */
    private boolean[] isPressed = {c, d, e, f, g, a, b, csharp, dsharp, fsharp, 
            gsharp, asharp};

    @Override public void run() {
        // Create the frame.
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Piano");
        frame.setPreferredSize(new Dimension(600,450));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initializes the 4 ArrayLists of notes that will be used
        // to help paint the pressed keys more efficiently.
        nat1 = new java.util.ArrayList(); sharps1 = new java.util.ArrayList();
        nat2 = new java.util.ArrayList(); sharps2 = new java.util.ArrayList();

        // Add each of the notes into one of the ArrayLists.
        nat1.add(c); nat1.add(d); nat1.add(e); nat1.add(f); 
        nat2.add(g); nat2.add(a); nat2.add(b);
        sharps1.add(csharp); sharps1.add(dsharp);
        sharps2.add(fsharp); sharps2.add(gsharp); sharps2.add(asharp);

        // Create the panel to hold both the piano and the JComboBox.
        mainPanel = new JPanel(new BorderLayout());

        // Create the initial sound clips for the starting octave (3).
        for (int i = 0; i < NOTE_NUM; i++) {
            try {
                ais = AudioSystem.getAudioInputStream
                    (new File(noteFiles[i]).getAbsoluteFile());
                clip = AudioSystem.getClip();
                clip.open(ais);
                noteClips[i] = clip;
            } catch(Exception e) {
                System.err.println(e);
            }
        }

        // Create the panel to hold the piano.
        pianoPanel = new JPanel() {
            @Override public void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Variables used to help draw each key.
                xNatural = STARTX_NATURAL; yNatural = STARTY_NATURAL;
                xSharp = STARTX_SHARP; ySharp = STARTY_SHARP;
                
                // Used as an index to check lists.
                int i = 0;

                // Check the natural keys in the first list.
                for (Boolean b : nat1) {
                    if (b == true) { // If the key is pressed.
                        
                        // Show that the key is pressed.
                        g.setColor(PRESSED);
                        g.fillRect(xNatural, yNatural, NATURAL_WIDTH, NATURAL_HEIGHT);
                        g.setColor(Color.BLACK);
                        g.drawRect(xNatural, yNatural, NATURAL_WIDTH, NATURAL_HEIGHT);
                        g.setColor(Color.RED);
                        g.drawString(notes[i], xNatural + 30, NATURAL_HEIGHT + 5);
                        g.drawString(keys[i], xNatural + 30, NATURAL_HEIGHT + 25);

                        // Play the sound clip.
                        try { 
                            playNote(i);
                        } catch(Exception e) {
                            System.err.println(e);
                        }
                        
                    } else { // If the key is not pressed.
                        
                        // Show that the key is not pressed.
                        g.setColor(Color.WHITE);
                        g.fillRect(xNatural, yNatural, NATURAL_WIDTH, NATURAL_HEIGHT);
                        g.setColor(Color.BLACK);
                        g.drawRect(xNatural, yNatural, NATURAL_WIDTH, NATURAL_HEIGHT);
                        g.setColor(Color.RED);
                        g.drawString(notes[i], xNatural + 30, NATURAL_HEIGHT + 5);
                        g.drawString(keys[i], xNatural + 30, NATURAL_HEIGHT + 25);

                        // Do not play the sound clip.
                        try {
                            playNote(i);
                        } catch(Exception e) {
                            System.err.println(e);
                        }
                    }
                    
                    // Increment the index and change the point to draw the next note.
                    xNatural += NATURAL_WIDTH; i++;
                }

                // Check the natural keys in the second list.
                for (Boolean b : nat2) {
                    if (b == true) { // If the key is pressed.
                        
                        // Show that the key is pressed.
                        g.setColor(PRESSED);
                        g.fillRect(xNatural, yNatural, NATURAL_WIDTH, NATURAL_HEIGHT);
                        g.setColor(Color.BLACK);
                        g.drawRect(xNatural, yNatural, NATURAL_WIDTH, NATURAL_HEIGHT);
                        g.setColor(Color.RED);
                        g.drawString(notes[i], xNatural + 30, NATURAL_HEIGHT + 5);
                        g.drawString(keys[i], xNatural + 30, NATURAL_HEIGHT + 25);

                        // Play the sound clip.
                        try {
                            playNote(i);
                        } catch(Exception e) {
                            System.err.println(e);
                        }
                        
                    } else { // If the key is not pressed.
                        
                        // Show that the key is not pressed.
                        g.setColor(Color.WHITE);
                        g.fillRect(xNatural, yNatural, NATURAL_WIDTH, NATURAL_HEIGHT);
                        g.setColor(Color.BLACK);
                        g.drawRect(xNatural, yNatural, NATURAL_WIDTH, NATURAL_HEIGHT);
                        g.setColor(Color.RED);
                        g.drawString(notes[i], xNatural + 30, NATURAL_HEIGHT + 5);
                        g.drawString(keys[i], xNatural + 30, NATURAL_HEIGHT + 25);

                        // Do not play the sound clip.
                        try {
                            playNote(i);
                        } catch(Exception e) {
                            System.err.println(e);
                        }
                    }
                    
                    // Increment the index and change the point to draw the next note.
                    xNatural += NATURAL_WIDTH; i++;
                }
                xNatural = STARTX_NATURAL; // Reset the point to draw the next note.
                pianoPanel.repaint(); // Repaint the panel.

                // Check the sharp keys in the first list.
                for (Boolean s : sharps1) {
                    if (s == true) { // If the key is pressed.
                        
                        // Show that the key is pressed.
                        g.setColor(PRESSED);
                        g.fillRect(xSharp, ySharp, SHARP_WIDTH, SHARP_HEIGHT);
                        g.setColor(Color.BLACK);
                        g.drawRect(xSharp, ySharp, SHARP_WIDTH, SHARP_HEIGHT);
                        g.setColor(Color.RED);
                        g.drawString(notes[i], xSharp + 15, SHARP_HEIGHT + 5);
                        g.drawString(keys[i], xSharp + 15, SHARP_HEIGHT + 25);

                        // Play the note.
                        try {
                            playNote(i);
                        } catch(Exception e) {
                            System.err.println(e);
                        }
                        
                    } else { // If the key is not pressed.
                        
                        // Show that the key is not pressed.
                        g.setColor(SHARPRGB);
                        g.fillRect(xSharp, ySharp, SHARP_WIDTH, SHARP_HEIGHT);
                        g.setColor(Color.BLACK);
                        g.drawRect(xSharp, ySharp, SHARP_WIDTH, SHARP_HEIGHT);
                        g.setColor(Color.RED);
                        g.drawString(notes[i], xSharp + 15, SHARP_HEIGHT + 5);
                        g.drawString(keys[i], xSharp + 15, SHARP_HEIGHT + 25);

                        // Do not play the note.
                        try {
                            playNote(i);
                        } catch(Exception e) {
                            System.err.println(e);
                        }
                        
                    }
                    
                    // Increment the index and change the point to draw the next note.
                    xSharp += NATURAL_WIDTH; i++;
                }

                // Skip over the section between E and F, since there is no 
                // E# key.
                xSharp += NATURAL_WIDTH;

                // Check the sharp keys in the second list.
                for (Boolean s : sharps2) {
                    if (s == true) { // If the key is pressed.
                        
                        // Show that the key is pressed.
                        g.setColor(PRESSED);
                        g.fillRect(xSharp, ySharp, SHARP_WIDTH, SHARP_HEIGHT);
                        g.setColor(Color.BLACK);
                        g.drawRect(xSharp, ySharp, SHARP_WIDTH, SHARP_HEIGHT);
                        g.setColor(Color.RED);
                        g.drawString(notes[i], xSharp + 15, SHARP_HEIGHT + 5);
                        g.drawString(keys[i], xSharp + 15, SHARP_HEIGHT + 25);

                        // Play the note.
                        try {
                            playNote(i);
                        } catch(Exception e) {
                            System.err.println(e);
                        }
                        
                    } else { // If the key is not pressed.
                        
                        // Show that the key is not pressed.
                        g.setColor(SHARPRGB);
                        g.fillRect(xSharp, ySharp, SHARP_WIDTH, SHARP_HEIGHT);
                        g.setColor(Color.BLACK);
                        g.drawRect(xSharp, ySharp, SHARP_WIDTH, SHARP_HEIGHT);
                        g.setColor(Color.RED);
                        g.drawString(notes[i], xSharp + 15, SHARP_HEIGHT + 5);
                        g.drawString(keys[i], xSharp + 15, SHARP_HEIGHT + 25);

                        // Do not play the note.
                        try {
                            playNote(i);
                        } catch(Exception e) {
                            System.err.println(e);
                        }
                        
                    }
                    
                    // Increment the index and change the point to draw the next note.
                    xSharp += NATURAL_WIDTH; i++;
                }
                
                xSharp = STARTX_SHARP; i = 0; // Reset the point to draw the next note and the index.
                pianoPanel.repaint(); // Repaint the panel.
            }
        };

        // Create a JComboBox with values [1-7].
        octaves = new JComboBox();
        for (int i = 1; i < 8; i++) {
            octaves.addItem("" + i);
        }
        octaves.setSelectedItem("3"); // Set the default octave to 3.

        // Create a panel to hold the JComboBox and give it
        // an ActionListener.
        octavePanel = new JPanel();
        octavePanel.add(new JLabel("Octave:"));
        octaves.addActionListener(this);
        octavePanel.add(octaves);        

        // Add the two panels to a main panel.
        mainPanel.add(octavePanel, BorderLayout.SOUTH);
        mainPanel.add(pianoPanel, BorderLayout.CENTER);

        
        frame.add(mainPanel); // Add main panel to frame.
        frame.addKeyListener(this); // Give the frame a KeyListener.
        frame.setFocusable(true); // Can change focus between piano and JComboBox.

        frame.pack();
        frame.setVisible(true);
    }

    /**
    Method to be called when a key is pressed and/or held.
    
    Satisfies the KeyAdapter interface.
    
    @param ke The KeyEvent that triggered the method call.
     */
    @Override public void keyPressed(KeyEvent ke) {
        // Checks each of the natural keys.
        if (ke.getKeyCode() == KeyEvent.VK_Z) { // C
            c = true; nat1.set(0, c);
            isPressed[0] = true; 
        }
        if (ke.getKeyCode() == KeyEvent.VK_X) { // D
            d = true; nat1.set(1, d); 
            isPressed[1] = true; 
        }
        if (ke.getKeyCode() == KeyEvent.VK_C) { // E
            e = true; nat1.set(2, e); 
            isPressed[2] = true; 
        }
        if (ke.getKeyCode() == KeyEvent.VK_V) { // F
            f = true; nat1.set(3, f); 
            isPressed[3] = true; 
        }
        if (ke.getKeyCode() == KeyEvent.VK_B) { // G
            g = true; nat2.set(0, g); 
            isPressed[4] = true; 
        }
        if (ke.getKeyCode() == KeyEvent.VK_N) { // A
            a = true; nat2.set(1, a); 
            isPressed[5] = true; 
        }
        if (ke.getKeyCode() == KeyEvent.VK_M) { // B
            b = true; nat2.set(2, b); 
            isPressed[6] = true; 
        }                

        // Checks each of the sharp/flat keys.
        if (ke.getKeyCode() == KeyEvent.VK_S) { // C#
            csharp = true; sharps1.set(0, csharp); 
            isPressed[7] = true; 
        }
        if (ke.getKeyCode() == KeyEvent.VK_D) { // D#
            dsharp = true; sharps1.set(1, dsharp); 
            isPressed[8] = true; 
        }
        if (ke.getKeyCode() == KeyEvent.VK_G) { // F#
            fsharp = true; sharps2.set(0, fsharp); 
            isPressed[9] = true; 
        }
        if (ke.getKeyCode() == KeyEvent.VK_H) { // G#
            gsharp = true; sharps2.set(1, gsharp); 
            isPressed[10] = true; 
        }
        if (ke.getKeyCode() == KeyEvent.VK_J) { // A#
            asharp = true; sharps2.set(2, asharp); 
            isPressed[11] = true; 
        }
    }

    /**
    Method to be called when a key is released.
    
    Satisfies the KeyAdapter interface.
    
    @param ke The KeyEvent that triggered the method call.
     */
    @Override public void keyReleased(KeyEvent ke) {
        // Checks each of the natural keys.
        if (ke.getKeyCode() == KeyEvent.VK_Z) { // C
            c = false; nat1.set(0, c); 
            isPressed[0] = false; 
        }
        if (ke.getKeyCode() == KeyEvent.VK_X) { // D
            d = false; nat1.set(1, d); 
            isPressed[1] = false; 
        }
        if (ke.getKeyCode() == KeyEvent.VK_C) { // E
            e = false; nat1.set(2, e); 
            isPressed[2] = false; 
        }
        if (ke.getKeyCode() == KeyEvent.VK_V) { // F
            f = false; nat1.set(3, f); 
            isPressed[3] = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_B) { // G
            g = false; nat2.set(0, g); 
            isPressed[4] = false; 
        }
        if (ke.getKeyCode() == KeyEvent.VK_N) { // A
            a = false; nat2.set(1, a); 
            isPressed[5] = false; 
        }
        if (ke.getKeyCode() == KeyEvent.VK_M) { // B
            b = false; nat2.set(2, b); 
            isPressed[6] = false; 
        }                

        // Checks each of the sharp/flat keys.
        if (ke.getKeyCode() == KeyEvent.VK_S) { // C#
            csharp = false; sharps1.set(0, csharp); 
            isPressed[7] = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_D) { // D#
            dsharp = false; sharps1.set(1, dsharp); 
            isPressed[8] = false; 
        }
        if (ke.getKeyCode() == KeyEvent.VK_G) { // F#
            fsharp = false; sharps2.set(0, fsharp);
            isPressed[9] = false; 
        }
        if (ke.getKeyCode() == KeyEvent.VK_H) { // G#
            gsharp = false; sharps2.set(1, gsharp);
            isPressed[10] = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_J) { // A#
            asharp = false; sharps2.set(2, asharp);
            isPressed[11] = false;
        }
    }

    /**
    Method to be called when a selection is made in the JComboBox.
    
    Satisfies the ActionListener interface.
    
    @param ae The ActionEvent that triggered the method call.
     */
    @Override public void actionPerformed(ActionEvent ae) {
        // This method changes all of the Strings in the noteFiles[]
        // so that those files can have clips made and allows the old
        // clips to be discarded.
        
        if (ae.getSource() == octaves) {
            if (octaves.getSelectedItem().equals("1")) {
                noteFiles[0] = "C1.aif"; noteFiles[1] = "D1.aif"; noteFiles[2] = "E1.aif";
                noteFiles[3] = "F1.aif"; noteFiles[4] = "G1.aif"; noteFiles[5] = "A1.aif";
                noteFiles[6] = "B1.aif"; noteFiles[7] = "C#1.aif"; noteFiles[8] = "D#1.aif";
                noteFiles[9] = "F#1.aif"; noteFiles[10] = "G#1.aif"; noteFiles[11] = "A#1.aif";
            } else if (octaves.getSelectedItem().equals("2")) {
                noteFiles[0] = "C2.aif"; noteFiles[1] = "D2.aif"; noteFiles[2] = "E2.aif";
                noteFiles[3] = "F2.aif"; noteFiles[4] = "G2.aif"; noteFiles[5] = "A2.aif";
                noteFiles[6] = "B2.aif"; noteFiles[7] = "C#2.aif"; noteFiles[8] = "D#2.aif";
                noteFiles[9] = "F#2.aif"; noteFiles[10] = "G#2.aif"; noteFiles[11] = "A#2.aif";
            } else if (octaves.getSelectedItem().equals("3")) {
                noteFiles[0] = "C3.aif"; noteFiles[1] = "D3.aif"; noteFiles[2] = "E3.aif";
                noteFiles[3] = "F3.aif"; noteFiles[4] = "G3.aif"; noteFiles[5] = "A3.aif";
                noteFiles[6] = "B3.aif"; noteFiles[7] = "C#3.aif"; noteFiles[8] = "D#3.aif";
                noteFiles[9] = "F#3.aif"; noteFiles[10] = "G#3.aif"; noteFiles[11] = "A#3.aif";
            } else if (octaves.getSelectedItem().equals("4")) {
                noteFiles[0] = "C4.aif"; noteFiles[1] = "D4.aif"; noteFiles[2] = "E4.aif";
                noteFiles[3] = "F4.aif"; noteFiles[4] = "G4.aif"; noteFiles[5] = "A4.aif";
                noteFiles[6] = "B4.aif"; noteFiles[7] = "C#4.aif"; noteFiles[8] = "D#4.aif";
                noteFiles[9] = "F#4.aif"; noteFiles[10] = "G#4.aif"; noteFiles[11] = "A#4.aif";
            } else if (octaves.getSelectedItem().equals("5")) {
                noteFiles[0] = "C5.aif"; noteFiles[1] = "D5.aif"; noteFiles[2] = "E5.aif";
                noteFiles[3] = "F5.aif"; noteFiles[4] = "G5.aif"; noteFiles[5] = "A5.aif";
                noteFiles[6] = "B5.aif"; noteFiles[7] = "C#5.aif"; noteFiles[8] = "D#5.aif";
                noteFiles[9] = "F#5.aif"; noteFiles[10] = "G#5.aif"; noteFiles[11] = "A#5.aif";
            } else if (octaves.getSelectedItem().equals("6")) {
                noteFiles[0] = "C6.aif"; noteFiles[1] = "D6.aif"; noteFiles[2] = "E6.aif";
                noteFiles[3] = "F6.aif"; noteFiles[4] = "G6.aif"; noteFiles[5] = "A6.aif";
                noteFiles[6] = "B6.aif"; noteFiles[7] = "C#6.aif"; noteFiles[8] = "D#6.aif";
                noteFiles[9] = "F#6.aif"; noteFiles[10] = "G#6.aif"; noteFiles[11] = "A#6.aif";
            } else { // (octaves.getSelectedItem().equals("7"))
                noteFiles[0] = "C7.aif"; noteFiles[1] = "D7.aif"; noteFiles[2] = "E7.aif";
                noteFiles[3] = "F7.aif"; noteFiles[4] = "G7.aif"; noteFiles[5] = "A7.aif";
                noteFiles[6] = "B7.aif"; noteFiles[7] = "C#7.aif"; noteFiles[8] = "D#7.aif";
                noteFiles[9] = "F#7.aif"; noteFiles[10] = "G#7.aif"; noteFiles[11] = "A#7.aif";
            } 

            // Returns focts to the piano.
            frame.transferFocusDownCycle();
            return;
        }
    }

    /**
    This method plays the clip of the note at the specified index within the 
    noteClips[].
    
    @param index The index of the clip to be played.
     */
    public void playNote(int index) throws IOException,
    UnsupportedAudioFileException, LineUnavailableException {
        if (isPressed[index] == true) { // If note is pressed.
            noteClips[index].start();
        } else { // If note is not pressed.
            // Stop playing the clip and reset it.
            noteClips[index].stop();
            noteClips[index].close();
            ais = AudioSystem.getAudioInputStream
                (new File(noteFiles[index]).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(ais);
            noteClips[index] = clip;
        }
    }

    /**
    Main method to construct and display the piano.
    
    @param args No input.
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Piano());
    }
}
