import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
Creates a Mole object that will randomly appear that can be clicked on.

@author Zack Gilman, Mark Smith, Roeldi Staro, Colin Palladino, Patrick Baumgardner
@version Spring 2020
 */
public class Mole extends Thread {

    /** Static variables. */
    public static final int DELAY_TIME = 33; // Delay time between animations.
    public static final int MOLE = 75; // Size of the mole
    
    /** Instance variables. */
    protected boolean isRunning = true; // Is the mole running?
    protected boolean bonked; // Was mole hit?
    protected boolean isUp; // Is the mole completely up?
    protected int x, y; // Coordinates where the mole will be drawn.
    protected JComponent container; // Panel mole will be drawn on.
    protected int timeUp; // How long the mole is above ground for.

    /**
    Constructs a new Mole object that is not hit(bonked), not on the surface,
    and with a set starting speed.
    
    @param x The x-coordinate where the Mole will be drawn.
    @param y The y-coordinate where the Mole will be drawn.
    @param container The panel where the Mole will be drawn.
     */
    public Mole(int x, int y, JComponent container) {
        this.x = x; 
        this.y = y; 
        bonked = false;
        isUp = false;
        timeUp = Whack_A_Mole.REGULAR;
        this.container = container;
    }

    /**
    Allows the Mole to translate up and down.
     */
    public void run() {
        while (isRunning) {
            moveMole();
        }
    }

    /**
    Draws the Mole moving up and down, and redraws the Mole
    based on whether it has been clicked on or not.
     */
    public void moveMole() {
        Random rand = new Random();
        // How long the mole will wait before popping up.
        int waitTime = rand.nextInt(7500) + 1000;

        try {
            sleep(waitTime);
        } catch (InterruptedException e) {}

        // Will move up for approximately 500ms.
        for (int i = 0; i < 16; i++) {
            try {
                sleep(DELAY_TIME);
            } catch (InterruptedException e) {}

            y = y - 10;
        }

        isUp = true; // Mole can be clicked on.
        try { // Stay up for amount of time based on JSlider in Whack_A_Mole class.
            sleep(timeUp);
        } catch (InterruptedException e) {}
        isUp = false; // Mole cannit be clicked on anymore.

        // Will move down for approximately 500ms.
        for (int i = 0; i < 16; i++) {
            try {
                sleep(DELAY_TIME);
            } catch (InterruptedException e) {}

            y = y + 10;
        }
        bonked = false; // Redraws Mole if it was clicked on while up.
    }

    /**
    
     */
    public void paintMole(Graphics g) {
        if (!bonked) { // Paint a mole that was not hit.
          
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
            
        } else { // Paint a mole that was hit.

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

            // Reset the stroke.
            g2.setStroke(new BasicStroke(1));
        }
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
    
    public void setTimeUp(int time) {
        timeUp = time;
    }
}
