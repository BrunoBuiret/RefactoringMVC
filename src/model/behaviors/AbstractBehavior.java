package model.behaviors;

import model.herds.Herd;
import model.turtles.AbstractTurtle;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public abstract class AbstractBehavior extends Thread
{
    /**
     * The herd to which the turtle belongs.
     */
    protected Herd herd;
    
    /**
     * The turtle with this behavior.
     */
    protected AbstractTurtle turtle;
    
    /**
     * Creates a new abstract behavior for a single turtle.
     * 
     * @param herd The herd to which the turtle belongs.
     * @param turtle The turtle with this behavior.
     */
    public AbstractBehavior(Herd herd, AbstractTurtle turtle)
    {
        super();
        
        // Initialize properties
        this.herd = herd;
        this.turtle = turtle;
    }
    
    /**
     * 
     */
    @Override
    public abstract void run();
}
