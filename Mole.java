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
    public static final int ABOVE_TIME = 1500; // Time spent above hole.
    public static final int MOLE = 200; // Size of the mole

    protected boolean bonked; // Was mole hit?
    protected boolean escaped; // Did mole escape unharmed? (may remove)
    protected boolean moveUp; // Mole move up (may remove)
    protected boolean moveDown; // Mole move down (may remove)
    protected int x, y; // Coordinates where the mole will be drawn.

    protected JComponent container;
    protected Graphics graphic;

    public Mole(int x, int y, JComponent container) {
        this.x = x; this.y = y;
        this.container = container;
    }

    public void run() {
        Random rand = new Random();
        int waitTime = rand.nextInt(1500) + 1000;

        try {
            sleep(waitTime);
        } catch (InterruptedException e) {}

        for (int i = 0; i < 16; i++) {
            try {
                sleep(DELAY_TIME);
            } catch (InterruptedException e) {}

            y = y + 2;
            paint(graphic);
        }
        
        try {
            sleep(ABOVE_TIME);
        } catch (InterruptedException e) {}
        
        for (int i = 0; i < 16; i++) {
            try {
                sleep(DELAY_TIME);
            } catch (InterruptedException e) {}

            y = y - 2;
            paint(graphic);
        }
    }

    public void paint(Graphics g) {
        if (!bonked) {
            // Paint a mole that was not hit.
            // Body
            g.setColor(new Color(160, 82, 45));
            g.fillOval(x, y, MOLE, MOLE + 100); // Change x&y later
            g.setColor(Color.BLACK);
            g.drawOval(x, y, MOLE, MOLE + 100);

            // Eyes
            g.setColor(Color.WHITE);
            g.fillOval(x + 41, y + 55, 30, 30); // Left eye
            g.fillOval(x + 126, y + 55, 30, 30); // Right eye
            g.setColor(Color.BLACK);
            g.drawOval(x + 41, y + 55, 30, 30); // Left Outline
            g.drawOval(x + 126, y + 55, 30, 30); // Right Outline
            g.fillOval(x + 46, y + 60, 20, 20); // Left Pupil
            g.fillOval(x + 131, y + 60, 20, 20); // Right Pupil

            // Nose
            g.setColor(Color.PINK);
            g.fillOval(x + 52, y + 110, 95, 75);
            g.setColor(Color.BLACK);
            g.drawOval(x + 52, y + 110, 95, 75);
            g.fillOval(x + 73, y + 115, 50, 40);
        } else {
            // Show that mole was hit (X's for eyes)
        }
    }

    public boolean bonked() {
        return bonked;
    }

    public boolean escaped() {
        return escaped;
    }

    public void setBonked(boolean b) {
        bonked = b;
    }

}
