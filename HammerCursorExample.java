import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * Creates a custom cursor that can be used for the Whack-A-Mole class.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HammerCursorExample implements Runnable {
    
    private JPanel panel;
    
    @Override public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Mole");
        frame.setPreferredSize(new Dimension(500, 500));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel() {
            @Override public void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                setBackground(Color.RED);
                
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Image image = toolkit.getImage("mallet.png");
                Cursor c = toolkit.createCustomCursor(image, 
                new Point(panel.getX(), panel.getY()), "img");
                panel.setCursor(c);
            }
        };

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new HammerCursorExample());
    }
}
