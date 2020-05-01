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
    public static final int ABOVE_TIME = 2000; // Time spent above hole.
    public static final int MOLE = 75; // Size of the mole
    protected boolean isRunning = true;

    protected boolean bonked; // Was mole hit?
    protected boolean moveUp; // Mole move up (may remove)
    protected boolean moveDown; // Mole move down (may remove)
    protected boolean isUp;
    protected int x, y; // Coordinates where the mole will be drawn.

    protected JComponent container;
    protected Graphics graphic;
    protected MouseEvent e;

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
        int waitTime = rand.nextInt(7500) + 1000;

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
        isUp = false;

        for (int i = 0; i < 16; i++) {
            try {
                sleep(DELAY_TIME);
            } catch (InterruptedException e) {}

            y = y + 10;
        }
        bonked = false;
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
            g.fillOval(x + 15, y + 30, MOLE / 6, MOLE / 6); // Left eye
            g.fillOval(x + 50, y +  30, MOLE / 6, MOLE / 6); // Right eye
            g.drawOval(x + 15, y + 30, MOLE / 6, MOLE / 6); // Left Outline
            g.drawOval(x + 50, y + 30, MOLE / 6, MOLE / 6); // Right Outline

            g.setColor(Color.WHITE);
            g.fillOval(x + 15, y + 30, MOLE / 12, MOLE / 12); // Left Pupil
            g.fillOval(x + 50, y + 30, MOLE / 12, MOLE / 12); // Right Pupil

            // Nose
            g.setColor(Color.PINK);
            g.fillOval(x + 20, y + 50, MOLE / 2, MOLE / 3);
            g.setColor(Color.BLACK);
            g.drawOval(x + 20, y + 50, MOLE / 2, MOLE / 3);
        } else {
            // Show that mole was hit (X's for eyes)
            // Body
            g.setColor(new Color(160, 82, 45));
            g.fillOval(x, y, MOLE, MOLE + 100); // Change x&y later
            g.setColor(Color.BLACK);
            g.drawOval(x, y, MOLE, MOLE + 100);

            // Nose
            g.setColor(Color.PINK);
            g.fillOval(x + 20, y + 50, MOLE / 2, MOLE / 3);
            g.setColor(Color.BLACK);
            g.drawOval(x + 20, y + 50, MOLE / 2, MOLE / 3);

            Graphics2D g2 = (Graphics2D)g;
            g2.setStroke(new BasicStroke(4));

            // Left Eye
            g2.drawLine(x + 15, y + 30, x + 25, y + 40);                
            g2.drawLine(x + 15, y + 40, x + 25, y + 30);

            // Right Eye
            g2.drawLine(x + 50, y + 30, x + 60, y + 40);                
            g2.drawLine(x + 50, y + 40, x + 60, y + 30);

            g2.setStroke(new BasicStroke(1));
        }
        //container.repaint();
    }

    public void setBonked(boolean b) {
        bonked = b;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isUp() {
        return isUp;
    }
}
