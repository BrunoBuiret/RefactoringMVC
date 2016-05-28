package controllers.behaviors;

import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.herds.Herd;
import model.turtles.AbstractTurtle;
import views.VivariumPanel;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public class FlockingBehavior extends RandomBehavior
{
    /**
     * Turtles won't go closer than this distance.
     */
    protected static final double MINIMUM_DISTANCE = 40.;
    
    /**
     * Creates a new flocking behavior for a single turtle.
     * 
     * @param herd
     * @param turtle
     * @param vivarium 
     */
    public FlockingBehavior(Herd herd, AbstractTurtle turtle, VivariumPanel vivarium)
    {
        super(herd, turtle, vivarium);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void run()
    {
        // Initialize vars
        Random randomizer = new Random();
        Set<AbstractTurtle> turtlesInSight;
        double direction, speed;
        
        while(true)
        {
            // Check if there are turtles in sight
            turtlesInSight = this.herd.getTurtlesIn(this.turtle.getSight(), this.turtle);
            
            if(!turtlesInSight.isEmpty())
            {
                // Modify speed and direction
                speed = 0.;
                direction = 0.;
                
                for(AbstractTurtle aTurtle : turtlesInSight)
                {
                    // speed += aTurtle.getSpeed();
                    direction += aTurtle.getDirection();
                }
                
                // this.turtle.setSpeed(speed / turtlesInSight.size());
                this.turtle.setDirection(direction / turtlesInSight.size());
            }
            else
            {
                // Fall back to random behavior
                this.doSomethingRandom(randomizer);
            }
            
            // Check if the turtle is going out of the vivarium, ie, the screen
            this.checkBounds();
            
            // Wait before the next action
            try
            {
                Thread.sleep((long) (300 + 500 * randomizer.nextDouble()));
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(FlockingBehavior.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
