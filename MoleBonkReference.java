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

        JFrame frame = new JFrame("ExampleMole");
        frame.setPreferredSize(new Dimension(500, 500));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel() {
            @Override public void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw mole here
                // Body
                g.setColor(new Color(160, 82, 45));
                g.fillOval(100, 100, MOLE, MOLE + 100); // Change x&y later
                g.setColor(Color.BLACK);
                g.drawOval(100, 100, MOLE, MOLE + 100);

                // Eyes (Fix later)
                g.fillArc(120, 150, 20, 10, 50, 50);
                g.fillArc(240, 150, 20, 10, 50, 50);
                
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
