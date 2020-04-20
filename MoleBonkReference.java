import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * A reference to how to draw a mole when it has been clocked on.
 *
 * @author Zack
 * @version Spring 2020
 */
public class MoleBonkReference implements Runnable {
            
    private static final int MOLE = 200;
    private JPanel panel;
    
    @Override public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Bonked Mole");
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

                // Left Eye
                g.drawLine(150, 150, 180, 200);
                g.drawLine(151, 150, 181, 200);
                g.drawLine(152, 150, 182, 200);
                g.drawLine(153, 150, 183, 200);
                
                g.drawLine(150, 200, 180, 150);
                g.drawLine(151, 200, 180, 151);
                g.drawLine(152, 200, 180, 152);
                g.drawLine(153, 200, 180, 153);
                
                // Right Eye
                g.drawLine(220, 150, 250, 200);
                g.drawLine(221, 150, 251, 200);
                g.drawLine(222, 150, 252, 200);
                g.drawLine(223, 150, 253, 200);
                
                g.drawLine(220, 200, 250, 150);
                g.drawLine(221, 200, 250, 151);
                g.drawLine(222, 200, 250, 152);
                g.drawLine(223, 200, 250, 153);
                
                // Nose
                g.setColor(Color.PINK);
                g.fillOval(152, 210, 95, 75);
                g.setColor(Color.BLACK);
                g.drawOval(152, 210, 95, 75);
                g.fillOval(173, 215, 50, 40);
            }
        };

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new MoleBonkReference());
    }
    
}
