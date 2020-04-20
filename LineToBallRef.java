import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * Write a description of class LineToBallRef here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class LineToBallRef extends MouseAdapter implements Runnable {
    
    /** Figure out how to draw line to circle without using exact numbers. */
    
    private JPanel panel;
    private boolean released;
    private Point point = null;
    private int rVal;
       
    @Override public void run() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("Line Reference");
        frame.setPreferredSize(new Dimension(500, 500));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel() {
            @Override public void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(new Color(76, 153, 0));
                g.setColor(Color.WHITE);
                g.fillOval(440, 225, 50, 50);
                g.setColor(Color.BLACK);
                g.drawOval(440, 225, 50, 50);
                
                if (point != null) {
                    if (440 - point.x > 255) {
                        g.setColor(Color.RED);
                        g.drawLine(point.x, point.y, 440, 250);
                    } else {
                        g.setColor(new Color((440 - point.x), 0, 0));
                        g.drawLine(point.x, point.y, 440, 250);
                    }
                    
                }
            }
        };

        frame.add(panel);
        
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        
        frame.pack();
        frame.setVisible(true);
    }
    
    @Override public void mousePressed(MouseEvent e) {
        point = new Point(e.getPoint());
        panel.repaint();
    }
    
    @Override public void mouseDragged(MouseEvent e) {
        point = e.getPoint();
        panel.repaint();
    }
    
    @Override public void mouseReleased(MouseEvent e) {
        point = null;
        panel.repaint();
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new LineToBallRef());
    }
}
