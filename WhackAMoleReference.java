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
 * Write a description of class WhackAMoleReference here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class WhackAMoleReference implements Runnable {

    private JPanel gamePanel, controlPanel, numbersPanel, actionPanel, mainPanel;
    private JButton start;
    private JSlider speedSlider;
    private int time = 60, score = 0, hiscore = 10;
    private JLabel name, timer, playerScore, hiScore;

    // Colors used to show layers needed to properly display moles.
    private Color gameBack = new Color(0, 100, 0);
    private Color gameMid = new Color(0, 175, 0);
    private Color gameFront = new Color(0, 255, 0);
    private Color dirt = new Color(80, 50, 20);
    @Override public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Mole Game Example");
        frame.setPreferredSize(new Dimension(750, 750));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel(new BorderLayout());

        gamePanel = new JPanel() {
            @Override public void paintComponent(Graphics g) {
                super.paintComponent(g);

                try {
                    g.drawImage(ImageIO.read(new File("sky.jpg")), 0, 0, null);
                } catch (Exception e) {}

                g.setColor(gameBack);
                g.fillRect(0, 350, gamePanel.getWidth(), gamePanel.getHeight());                

                g.setColor(gameMid);
                g.fillRect(0, 450, gamePanel.getWidth(), gamePanel.getHeight());

                g.setColor(dirt);
                g.fillOval(110, 450, 75, 35);
                g.fillOval(300, 450, 75, 35);
                g.setColor(Color.BLACK);
                g.fillOval(110, 450, 75, 30);
                g.fillOval(300, 450, 75, 30);
                g.setColor(dirt);

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
            }
        };

        controlPanel = new JPanel(new BorderLayout()) {
            @Override public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D)g;
                g2.setStroke(new BasicStroke(3));
                g2.drawRect(0, 0, controlPanel.getWidth(), controlPanel.getHeight());
            }
        };
        controlPanel.setPreferredSize(new Dimension(250, mainPanel.getHeight()));

        name = new JLabel("Whack-A-Mole!");
        name.setFont(new Font("Comic Sans", Font.PLAIN, 30));

        timer = new JLabel("Time: " + time);
        timer.setFont(new Font("Comic Sans", Font.PLAIN, 20));

        playerScore = new JLabel("Score: " + score);
        playerScore.setFont(new Font("Comic Sans", Font.PLAIN, 20));

        hiScore = new JLabel("Hi-Score: " + hiscore);
        hiScore.setFont(new Font("Comic Sans", Font.PLAIN, 20));

        numbersPanel = new JPanel(new BorderLayout());
        numbersPanel.setPreferredSize(new Dimension(150, 300));
        numbersPanel.add(timer, BorderLayout.NORTH);
        numbersPanel.add(playerScore, BorderLayout.CENTER);
        numbersPanel.add(hiScore, BorderLayout.SOUTH);
        

        speedSlider = new JSlider(0, 2);
        //speedSlider.addChangeListener(this);

        start = new JButton("START");
        // start.addActionListener(new ActionListener()) {
        // @Override public void actionPerformed(ActionEvent ae) {

        // }
        // };

        actionPanel = new JPanel(new BorderLayout());
        actionPanel.add(speedSlider, BorderLayout.NORTH);
        actionPanel.add(start, BorderLayout.SOUTH);

        controlPanel.add(name, BorderLayout.NORTH);
        controlPanel.add(numbersPanel, BorderLayout.CENTER);
        controlPanel.add(actionPanel, BorderLayout.SOUTH);

        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.EAST);

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new WhackAMoleReference());
    }

}
