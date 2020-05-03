// Swing imports.
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

// Imports for the background image of the sky.
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
Class that plays a game of Whack-A-Mole. The player will have 60 seconds to click on
as many moles as possible. If the player clicks on more moles than the current hi-score,
a special message will appear. Once the game is over and the player clicks off the message
box, the program will close.

@author Zack Gilman, Mark Smith, Roeldi Staro, Colin Palladino, Patrick Baumgardner
@version Spring 2020
 */
public class Whack_A_Mole extends MouseAdapter implements Runnable {
    // Instance variables go here
    /** Colors used to paint the grass on the gamePanel. */
    private Color gameBack = new Color(0, 100, 0);
    private Color gameMid = new Color(0, 175, 0);
    private Color gameFront = new Color(0, 255, 0);
    private Color dirt = new Color(80, 50, 20);

    /** Static variables. */
    protected static final int SLOW = 3000;
    protected static final int REGULAR = 1500;
    protected static final int FAST = 500;

    /** Instance variables. */
    // Different panels used in the program.
    private JPanel gamePanel, controlPanel, namePanel, numbersPanel, actionPanel, mainPanel;
    
    // List of threaded Moles.
    protected java.util.List<Mole> moles = new java.util.ArrayList<Mole>();
    
    private JSlider speedSlider; // Adjusts the speed of the moles.
    private JButton startButton; // Starts the game.
    protected Point p; // The point the player clicks on screen.
    
    // Integers used to keep track of the time and scores.
    private int time = 120, score = 0, hiscore = 25;
    
    // Labels used in the controlPanel.
    private JLabel nameTop, nameMid, nameBot, timeLeft, playerScore, hiScore;
    
    // Booleans used to check if the game has started/ended.
    private static boolean start = false, gameover = false;
    
    // Used to keep track of how much time is left until the game ends.
    private java.util.Timer countdown; 

    @Override public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Whack A Mole!");
        frame.setPreferredSize(new Dimension(750, 750));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel to hold all other panels.
        mainPanel = new JPanel(new BorderLayout());

        start(); // Starts the game.

        // Panel used to play the game.
        gamePanel = new JPanel() {
            @Override public void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (gameover) { // If the game is over.
                    JFrame gameOverFrame = new JFrame();
                    if (score == hiscore) { // Print a message for the player.
                        JOptionPane.showMessageDialog(gameOverFrame, "Congratulations. New Hi-Score!");
                    } else {
                        JOptionPane.showMessageDialog(gameOverFrame, "Game Over. Better Luck Next Time.");
                    }
                    gameover = false;
                    gamePanel.repaint();
                    System.exit(0); // Close the program.
                    
                } else { // If the game is not over.
                     
                    try { // Draw the sky.
                        g.drawImage(ImageIO.read(new File("sky.jpg")), 0, 0, null);
                    } catch (Exception e) {}

                    // Draw the background.
                    g.setColor(gameBack);
                    g.fillRect(0, 350, gamePanel.getWidth(), gamePanel.getHeight());                

                    // Start the moles in the top two holes.
                    if (start) {
                        for (int j = 0; j < 2; j++) {
                            Mole m = moles.get(j);
                            m.paintMole(g);
                        }
                    }

                    // Draw the middleground.
                    g.setColor(gameMid);
                    g.fillRect(0, 450, gamePanel.getWidth(), gamePanel.getHeight());

                    g.setColor(dirt);
                    g.fillOval(110, 450, 75, 35);
                    g.fillOval(300, 450, 75, 35);
                    g.setColor(Color.BLACK);
                    g.fillOval(110, 450, 75, 30);
                    g.fillOval(300, 450, 75, 30);
                    g.setColor(dirt);

                    // Start the moles in the bottom three holes.
                    if (start) {
                        for (int i = 2; i < 5; i++) {
                            Mole m = moles.get(i);
                            m.paintMole(g);
                        } 
                    }

                    // Draw the foreground.
                    g.setColor(gameFront);
                    g.fillRect(0, 550, gamePanel.getWidth(), gamePanel.getHeight());

                    g.setColor(dirt);
                    g.fillOval(35, 550, 75, 35);
                    g.fillOval(205, 550, 75, 35);
                    g.fillOval(375, 550, 75, 35);
                    g.setColor(Color.BLACK);
                    g.fillOval(35, 550, 75, 30);
                    g.fillOval(205, 550, 75, 30);
                    g.fillOval(375, 550, 75, 30);

                    // Create the custom cursor.
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    Image image = toolkit.getImage("mallet.png");
                    Cursor c = toolkit.createCustomCursor(image, 
                            new Point(gamePanel.getX(), gamePanel.getY()), "img");
                    gamePanel.setCursor(c);

                    redraw(g); // Repaints the gamePanel.
                }
            }
        };

        // Panel used to have title, timer, scores, slider and start button.
        controlPanel = new JPanel(new BorderLayout()) {
            @Override public void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Timer that goes from 60-0.
                countdown = new java.util.Timer();
                countdown.schedule(new TimerTask() {
                        @Override public void run() {
                            if (start) {
                                if ((int)Math.ceil(time / 2) == 0) {
                                    start = false;
                                    gameover = true;
                                    return;
                                } else {
                                    time--; 
                                    timeLeft.setText("Time: " + (int)Math.ceil(time / 2));
                                }
                            }
                        }
                    },  1000, 1000);

            }
        };
        // Make controlPanel 1/3 size of the frame.
        controlPanel.setPreferredSize(new Dimension(250, mainPanel.getHeight()));

        // Title of the game.
        nameTop = new JLabel("Whack", SwingConstants.CENTER);
        nameTop.setFont(new Font("Comic Sans", Font.PLAIN, 30));

        nameMid = new JLabel("-A-", SwingConstants.CENTER);
        nameMid.setFont(new Font("Comic Sans", Font.PLAIN, 30));

        nameBot = new JLabel("Mole!", SwingConstants.CENTER);
        nameBot.setFont(new Font("Comic Sans", Font.PLAIN, 30));

        namePanel = new JPanel(new BorderLayout());
        namePanel.add(nameTop, BorderLayout.NORTH);
        namePanel.add(nameMid, BorderLayout.CENTER);
        namePanel.add(nameBot, BorderLayout.SOUTH);

        // Timer.
        timeLeft = new JLabel("Time: " + (time / 2), SwingConstants.CENTER);
        timeLeft.setFont(new Font("Comic Sans", Font.PLAIN, 20));

        // Player's current score.
        playerScore = new JLabel("Score: " + score, SwingConstants.CENTER);
        playerScore.setFont(new Font("Comic Sans", Font.PLAIN, 20));

        // Hi-score.
        hiScore = new JLabel("Hi-Score: " + hiscore, SwingConstants.CENTER);
        hiScore.setFont(new Font("Comic Sans", Font.PLAIN, 20));

        numbersPanel = new JPanel(new BorderLayout());
        numbersPanel.add(timeLeft, BorderLayout.NORTH);
        numbersPanel.add(playerScore, BorderLayout.CENTER);
        numbersPanel.add(hiScore, BorderLayout.SOUTH);

        // Slider that adjusts the speed of the moles.
        speedSlider = new JSlider(0, 2);
        speedSlider.addChangeListener(new ChangeListener() {
                @Override public void stateChanged(ChangeEvent e) {
                    if (e.getSource().equals(speedSlider)) {
                        if (speedSlider.getValue() == 0) {
                            for (Mole m : moles) {
                                m.setTimeUp(SLOW);
                            }
                        } else if (speedSlider.getValue() == 1) {
                            for (Mole m : moles) {
                                m.setTimeUp(REGULAR);
                            }
                        } else {
                            for (Mole m : moles) {
                                m.setTimeUp(FAST);
                            }
                        }
                    }
                }
            });

        // Button that starts the game.
        startButton = new JButton("START");
        startButton.addActionListener(new ActionListener() {
                @Override public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton)e.getSource();
                    String str = button.getText();
                    if (str.equals("START")) {
                        start = true;
                        gameover = false;
                    }
                }
            });

        actionPanel = new JPanel(new BorderLayout());
        actionPanel.add(speedSlider, BorderLayout.NORTH);
        actionPanel.add(startButton, BorderLayout.SOUTH);

        // Add title, timer, scores, slider and start button to controlPanel.
        controlPanel.add(namePanel, BorderLayout.NORTH);
        controlPanel.add(numbersPanel, BorderLayout.CENTER);
        controlPanel.add(actionPanel, BorderLayout.SOUTH);

        // Add a MouseListener for the gamePanel only.
        gamePanel.addMouseListener(this);

        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.EAST);

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    /**
    Method to be called when the mouse is pressed on screen.
    
    Satisfies the MouseAdapter interface.
    
    @param e The MouseEvent that triggered the method call.
     */
    @Override public void mousePressed(MouseEvent e) { 
        // Checks if mouse is clicked over a visible mole
        if (e.getButton() == MouseEvent.BUTTON1) {
            p = e.getPoint();
            for (Mole m : moles) {
                // Check if isUp is true and if p coordinates are within 
                // mole dimensions.
                if (m.isUp()) {
                    if ((p.x >= m.getX() && p.x <= m.getX() + 75) 
                    && (p.y >= m.getY() && p.y <= m.getY() + 175)) {
                        m.setBonked(true);
                        score++; playerScore.setText("Score: " + score);
                        if (score > hiscore) {
                            hiscore++; hiScore.setText("Hi-Score: " + hiscore);
                        }
                    }
                }
            }
        }
        p = null; // Reset the point.
    }

    /**
    Redraws the gamePanel based on the movement of each mole.
     */
    public void redraw(Graphics g) {
        for (Mole m : moles) {
            gamePanel.repaint();
        }
    }

    /**
    Adds all moles to a list and starts each thread.
     */
    public void start() {
        Mole mole1 = new Mole(115, 500, mainPanel); moles.add(mole1);
        Mole mole2 = new Mole(300, 500, mainPanel); moles.add(mole2);
        Mole mole3 = new Mole( 35, 600, mainPanel); moles.add(mole3);
        Mole mole4 = new Mole(205, 600, mainPanel); moles.add(mole4);
        Mole mole5 = new Mole(375, 600, mainPanel); moles.add(mole5);

        for (Mole m : moles) {
            m.start();
        }
    }

    /**
    Main method to construct and play the game.
    
    @param args No input.
     */
    public static void main(String[] args) {       
        javax.swing.SwingUtilities.invokeLater(new Whack_A_Mole());
    }
}