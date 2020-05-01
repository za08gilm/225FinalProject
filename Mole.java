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
    public static final int ABOVE_TIME = 5000; // Time spent above hole.
    public static final int MOLE = 200; // Size of the mole
    protected boolean isRunning = true;

    protected boolean bonked; // Was mole hit?
    protected boolean moveUp; // Mole move up (may remove)
    protected boolean moveDown; // Mole move down (may remove)
    protected boolean isUp;
    protected int x = 100, y = 100; // Coordinates where the mole will be drawn.

    protected JComponent container;
    protected Graphics graphic;

    public Mole(int x, int y, JComponent container) {
        this.x = x; 
        this.y = y; 
        bonked = false;
        isUp = false;
        this.container = container;
    }

    public void run() {
        while (isRunning) {
            moveMole();
        }
    }
    
    public void moveMole() {
        Random rand = new Random();
        int waitTime = rand.nextInt(1500) + 1000;

        try {
            sleep(waitTime);
        } catch (InterruptedException e) {}

        for (int i = 0; i < 16; i++) {
            try {
                sleep(DELAY_TIME);
            } catch (InterruptedException e) {}

            y = y - 10;
        }
        
        isUp = true;
        try {
            sleep(ABOVE_TIME);
        } catch (InterruptedException e) {}

        for (int i = 0; i < 16; i++) {
            try {
                sleep(DELAY_TIME);
            } catch (InterruptedException e) {}

            y = y + 10;
        }
    }

    public void paintMole(Graphics g) {
        if (!bonked) {
            // Paint a mole that was not hit.
            // Body
            g.setColor(new Color(160, 82, 45));
            g.fillOval(x, y, MOLE, MOLE + 100); // Change x&y later
            g.setColor(Color.BLACK);
            g.drawOval(x, y, MOLE, MOLE + 100);

            // Eyes
            g.setColor(Color.BLACK);
            g.fillOval(x + 41, y + 55, 30, 30); // Left eye
            g.fillOval(x + 126, y +  55, 30, 30); // Right eye
            g.drawOval(x + 41, y + 55, 30, 30); // Left Outline
            g.drawOval(x + 126, y + 55, 30, 30); // Right Outline

            g.setColor(Color.WHITE);
            g.fillOval(x + 41, y + 60, 15, 15); // Left Pupil
            g.fillOval(x + 126, y + 60, 15, 15); // Right Pupil

            // Nose
            g.setColor(Color.PINK);
            g.fillOval(x + 52, y + 110, 95, 75);
            g.setColor(Color.BLACK);
            g.drawOval(x + 52, y + 110, 95, 75);
        } else {
            // Show that mole was hit (X's for eyes)
            // Body
            g.setColor(new Color(160, 82, 45));
            g.fillOval(x, y, MOLE, MOLE + 100); // Change x&y later
            g.setColor(Color.BLACK);
            g.drawOval(x, y, MOLE, MOLE + 100);

            // Nose
            g.setColor(Color.PINK);
            g.fillOval(x + 52, y + 110, 95, 75);
            g.setColor(Color.BLACK);
            g.drawOval(152, 210, 95, 75);

            Graphics2D g2 = (Graphics2D)g;
            g2.setStroke(new BasicStroke(4));

            // Left Eye
            g2.drawLine(x + 50, y + 50, x + 80, y + 100);                
            g2.drawLine(x + 50, y + 100, x + 80, y + 50);

            // Right Eye
            g2.drawLine(x + 120, y + 50, x + 250, y + 100);                
            g2.drawLine(x + 120, y + 100, x + 150, y + 50);
        }
        //container.repaint();
    }

    public boolean bonked() {
        return bonked;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

    public void setBonked(boolean b) {
        bonked = b;
    }

}
