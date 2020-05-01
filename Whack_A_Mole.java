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
    
    private JPanel gamePanel, controlPanel, numbersPanel, actionPanel, mainPanel;
    protected java.util.List<Mole> moles = new java.util.ArrayList<Mole>();
    private JSlider speedSlider;
    private JButton startButton;
    private int hiscore = 10;
    protected Point p;
       
    protected static boolean isWithinMole = false;

    @Override public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Whack A Mole!");
        frame.setPreferredSize(new Dimension(750, 750));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //mainPanel = new JPanel();
        //controlPanel = new JPanel();

        start();

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
                
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Image image = toolkit.getImage("mallet.png");
                Cursor c = toolkit.createCustomCursor(image, 
                        new Point(gamePanel.getX(), gamePanel.getY()), "img");
                gamePanel.setCursor(c);

                int index = 0;
                while (index < moles.size()) {
                    Mole m = moles.get(index);
                    m.paintMole(g); index++;
                }
                redraw(g);
            }
        };

        //mainPanel.add(controlPanel);
        //mainPanel.add(gamePanel);

        gamePanel.addMouseListener(this);
        frame.add(gamePanel);
        frame.pack();
        frame.setVisible(true);
    }

    @Override public void mouseClicked(MouseEvent e) { 
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
        // Final product will construct 5 moles at diff. locations.
        // (Look at WhackAMoleReference)

        Mole mole1 = new Mole(35, 600, mainPanel); moles.add(mole1);
        // Mole mole2 = new Mole(300, 450, mainPanel); moles.add(mole2);
        // Mole mole3 = new Mole(550, 450, mainPanel); moles.add(mole3);

        for (Mole m : moles) {
            m.start();
        }
    }

    public static void main(String[] args) {       
        javax.swing.SwingUtilities.invokeLater(new Whack_A_Mole());
    }
}