import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * Write a description of class Whack_A_Mole here.
 *
 * @author Zack, Mark, Roeldi, Colin, Patrick
 * @version Spring 2020
 */
public class Whack_A_Mole extends MouseAdapter implements Runnable {
    // Instance variables go here

    private Color gameBack = new Color(0, 100, 0);
    private Color gameMid = new Color(0, 175, 0);
    private Color gameFront = new Color(0, 255, 0);
    private Color dirt = new Color(80, 50, 20);

    protected static final int SLOW = 3000;
    protected static final int REGULAR = 1500;
    protected static final int FAST = 500;

    private JPanel gamePanel, controlPanel, namePanel, numbersPanel, actionPanel, mainPanel;
    protected java.util.List<Mole> moles = new java.util.ArrayList<Mole>();
    private JSlider speedSlider;
    private JButton startButton;
    protected Point p;
    private int time = 120;
    private int score = 0, hiscore = 10;
    private JLabel nameTop, nameMid, nameBot, timeLeft, playerScore, hiScore;
    private static boolean start = false, gameover = false;
    private java.util.Timer countdown;
    private long startTime;

    @Override public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Whack A Mole!");
        frame.setPreferredSize(new Dimension(750, 750));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout());

        start();
        startTime = System.currentTimeMillis();

        gamePanel = new JPanel() {
            @Override public void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (gameover) {
                    JFrame gameOverFrame = new JFrame();
                    JOptionPane.showMessageDialog(gameOverFrame, "Game Over.");
                    gameover = false;
                    gamePanel.repaint();
                    System.exit(0);
                } else {
                    try {
                        g.drawImage(ImageIO.read(new File("sky.jpg")), 0, 0, null);
                    } catch (Exception e) {}

                    g.setColor(gameBack);
                    g.fillRect(0, 350, gamePanel.getWidth(), gamePanel.getHeight());                

                    if (start) {
                        for (int j = 0; j < 2; j++) {
                            Mole m = moles.get(j);
                            m.paintMole(g);
                        }
                    }

                    g.setColor(gameMid);
                    g.fillRect(0, 450, gamePanel.getWidth(), gamePanel.getHeight());

                    g.setColor(dirt);
                    g.fillOval(110, 450, 75, 35);
                    g.fillOval(300, 450, 75, 35);
                    g.setColor(Color.BLACK);
                    g.fillOval(110, 450, 75, 30);
                    g.fillOval(300, 450, 75, 30);
                    g.setColor(dirt);

                    if (start) {
                        for (int i = 2; i < 5; i++) {
                            Mole m = moles.get(i);
                            m.paintMole(g);
                        } 
                    }

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

                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    Image image = toolkit.getImage("mallet.png");
                    Cursor c = toolkit.createCustomCursor(image, 
                            new Point(gamePanel.getX(), gamePanel.getY()), "img");
                    gamePanel.setCursor(c);

                    redraw(g);

                }
            }
        };

        controlPanel = new JPanel(new BorderLayout()) {
            @Override public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D)g;
                g2.setStroke(new BasicStroke(3));
                g2.drawRect(0, 0, controlPanel.getWidth(), controlPanel.getHeight());

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
        controlPanel.setPreferredSize(new Dimension(250, mainPanel.getHeight()));

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

        timeLeft = new JLabel("Time: " + (time / 2), SwingConstants.CENTER);
        timeLeft.setFont(new Font("Comic Sans", Font.PLAIN, 20));

        playerScore = new JLabel("Score: " + score, SwingConstants.CENTER);
        playerScore.setFont(new Font("Comic Sans", Font.PLAIN, 20));

        hiScore = new JLabel("Hi-Score: " + hiscore, SwingConstants.CENTER);
        hiScore.setFont(new Font("Comic Sans", Font.PLAIN, 20));

        numbersPanel = new JPanel(new BorderLayout());
        numbersPanel.add(timeLeft, BorderLayout.NORTH);
        numbersPanel.add(playerScore, BorderLayout.CENTER);
        numbersPanel.add(hiScore, BorderLayout.SOUTH);

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

        controlPanel.add(namePanel, BorderLayout.NORTH);
        controlPanel.add(numbersPanel, BorderLayout.CENTER);
        controlPanel.add(actionPanel, BorderLayout.SOUTH);

        gamePanel.addMouseListener(this);

        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.EAST);

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);

        // if (gameover) {
        // JFrame gameOverFrame = new JFrame();
        // JOptionPane.showMessageDialog(gameOverFrame, "Game Over.");
        // }
    }

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
        p = null;
    }

    public void redraw(Graphics g) {
        for (Mole m : moles) {
            gamePanel.repaint();
        }
    }

    public void start() {
        Mole mole1 = new Mole(115, 500, mainPanel); moles.add(mole1);
        Mole mole2 = new Mole(300, 500, mainPanel); moles.add(mole2);
        Mole mole3 = new Mole( 35, 600, mainPanel); moles.add(mole3);
        Mole mole4 = new Mole(205, 600, mainPanel); moles.add(mole4);
        Mole mole5 = new Mole(375, 600, mainPanel); moles.add(mole5);

        for (Mole m : moles) {
            m.start();
        }

        // time = 120; 
        // score = 0; 
    }

    public static void main(String[] args) {       
        javax.swing.SwingUtilities.invokeLater(new Whack_A_Mole());
    }
}