import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * A reference to how to draw a mole when it has not been clocked on.
 *
 * @author Zack
 * @version Spring 2020
 */
public class MoleReference implements Runnable {
    
    private JPanel panel;
    private static final int MOLE = 200;

    @Override public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Mole");
        frame.setPreferredSize(new Dimension(500, 500));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel() {
            @Override public void paintComponent(Graphics g) {
                super.paintComponent(g);
                
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
            }
        };

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new MoleReference());
    }
}
