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
    protected boolean isUp;
    protected int x, y; // Coordinates where the mole will be drawn.

    protected JComponent container;
    protected Graphics graphic;

    public Mole(int x, int y, boolean bonked, JComponent container) {
        this.x = x; this.y = y; this.bonked = bonked;
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

            y = y + 5;
            paintMole(graphic);
            
        }

        try {
            sleep(ABOVE_TIME);
        } catch (InterruptedException e) {}

        for (int i = 0; i < 16; i++) {
            try {
                sleep(DELAY_TIME);
            } catch (InterruptedException e) {}

            y = y - 5;
            paintMole(graphic);
            //container.repaint();
        }
    }

    public void paintMole(Graphics g) {
        if (!bonked) {
            // Paint a mole that was not hit.
            // Body
            g.setColor(new Color(160, 82, 45));
            g.fillOval(100, 100, MOLE, MOLE + 100); // Change x&y later
            g.setColor(Color.BLACK);
            g.drawOval(100, 100, MOLE, MOLE + 100);

            // Eyes
            g.setColor(Color.BLACK);
            g.fillOval(141, 155, 30, 30); // Left eye
            g.fillOval(226, 155, 30, 30); // Right eye
            g.drawOval(141, 155, 30, 30); // Left Outline
            g.drawOval(226, 155, 30, 30); // Right Outline

            g.setColor(Color.WHITE);
            g.fillOval(141, 160, 15, 15); // Left Pupil
            g.fillOval(226, 160, 15, 15); // Right Pupil

            // Nose
            g.setColor(Color.PINK);
            g.fillOval(152, 210, 95, 75);
            g.setColor(Color.BLACK);
            g.drawOval(152, 210, 95, 75);
        } else {
            // Show that mole was hit (X's for eyes)
            // Body
            g.setColor(new Color(160, 82, 45));
            g.fillOval(100, 100, MOLE, MOLE + 100); // Change x&y later
            g.setColor(Color.BLACK);
            g.drawOval(100, 100, MOLE, MOLE + 100);

            // Nose
            g.setColor(Color.PINK);
            g.fillOval(152, 210, 95, 75);
            g.setColor(Color.BLACK);
            g.drawOval(152, 210, 95, 75);

            Graphics2D g2 = (Graphics2D)g;
            g2.setStroke(new BasicStroke(4));

            // Left Eye
            g2.drawLine(150, 150, 180, 200);                
            g2.drawLine(150, 200, 180, 150);

            // Right Eye
            g2.drawLine(220, 150, 250, 200);                
            g2.drawLine(220, 200, 250, 150);
        }
        container.repaint();
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
