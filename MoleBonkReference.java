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
        };

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new MoleBonkReference());
    }
    
}
