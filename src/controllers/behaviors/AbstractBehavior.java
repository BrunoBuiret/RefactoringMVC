package controllers.behaviors;

import model.herds.Herd;
import model.turtles.AbstractTurtle;
import views.VivariumPanel;

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
     * The turtle's vivarium.
     */
    protected VivariumPanel vivarium;
    
    /**
     * Creates a new abstract behavior for a single turtle.
     * 
     * @param herd The herd to which the turtle belongs.
     * @param turtle The turtle with this behavior.
     * @param vivarium
     */
    public AbstractBehavior(Herd herd, AbstractTurtle turtle, VivariumPanel vivarium)
    {
        super();
        
        // Initialize properties
        this.herd = herd;
        this.turtle = turtle;
        this.vivarium = vivarium;
    }
    
    /**
     * 
     */
    @Override
    public abstract void run();
}
