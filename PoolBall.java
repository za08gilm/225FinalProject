import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
/**
An abstract class that will be used to help create red colored balls for player
1, blue colored balls for player 2, and a cue ball which will be used to play
the game.

@author Zack, Mark, Roeldi, Colin, Patrick
@version Spring 2020

Simple outline, add aditional functionality later.
 */
public abstract class PoolBall extends Thread {
    /** Size of the pool balls. */
    protected static final int BALL_SIZE = 10; // Change later.
    
    /** Color of the ball. */
    private Color color;
    
    /** The container the ball will be drawn on. */
    private JComponent 
    container;
    public PoolBall(int size, Color color, JComponent container) {
        
        size = BALL_SIZE;
        this.color = color;
        this.container = container;
    }
}

class RedBall extends PoolBall {
    
    public RedBall(int size, Color color, JComponent container) {
        
        super(size, Color.RED, container);
    }
}

class BlueBall extends PoolBall {
    
    public BlueBall(int size, Color color, JComponent container) {
        
        super(size, Color.BLUE, container);
    }
}

class CueBall extends PoolBall{
    
    public CueBall(int size, Color color, JComponent container) {
        
        super(size, Color.WHITE, container);
    }
}