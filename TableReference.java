import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * Shows how to draw pool table.
 *
 * @author Zack
 * @version Spring 2020
 */
public class TableReference implements Runnable {
    
    private static final Color TABLE = new Color(76, 153, 0);
    private static final Color WOOD = new Color(108, 66, 29);
    private static final Color HOLE_COLOR = Color.BLACK;
    private static final int HOLESIZE = 40;
    private JPanel panel;
        
    @Override public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Table Reference");
        frame.setPreferredSize(new Dimension(800, 500));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel() {
            @Override public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(TABLE);
                g.fillRect(25, 30, 725, 400);
                
                g.setColor(WOOD);
                g.fillRect(25, 30, 725, 15); // Top
                g.fillRect(25, 30, 15, 400); // Left
                g.fillRect(25, 415, 725, 15); // Bottom
                g.fillRect(740, 30, 15, 400); // Right
                
                g.setColor(HOLE_COLOR);
                g.fillOval(25, 30, HOLESIZE, HOLESIZE); // Top-right
                g.fillOval(25, 390, HOLESIZE, HOLESIZE); // Bottom-right
                g.fillOval(715, 30, HOLESIZE, HOLESIZE); // Top-left
                g.fillOval(715, 390, HOLESIZE, HOLESIZE); // Bottom-left
                g.fillOval(365, 30, HOLESIZE, HOLESIZE); // Top-middle
                g.fillOval(365, 390, HOLESIZE, HOLESIZE); // Bottom-middle
            }
        };

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] srgs) {
        javax.swing.SwingUtilities.invokeLater(new TableReference());
    }
    
}
