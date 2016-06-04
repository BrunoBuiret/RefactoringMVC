package controllers.behaviors;

import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.herds.Herd;
import model.turtles.AbstractTurtle;
import utils.DistanceUtils;
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
        double direction, speed, x, y;
        boolean tooClose;
        
        while(true)
        {
            // Check if there are turtles in sight
            turtlesInSight = this.herd.getTurtlesIn(this.turtle.getSight(), this.turtle);
            
            if(!turtlesInSight.isEmpty())
            {
                // Reset vars
                speed = 0.;
                direction = 0.;
                x = 0.;
                y = 0.;
                tooClose = false;
                
                // Analyse the herd
                for(AbstractTurtle aTurtle : turtlesInSight)
                {
                    speed += aTurtle.getSpeed();
                    direction += aTurtle.getDirection();
                    x += aTurtle.getX();
                    y += aTurtle.getY();
                    
                    if(DistanceUtils.computeDistance(this.turtle.getPosition(), aTurtle.getPosition()) <= FlockingBehavior.MINIMUM_DISTANCE)
                    {
                        tooClose = true;
                    }
                }
                
                // Make the turtle do something
                if(!tooClose)
                {
                    // The turtle isn't too close to the herd so it can go closer
                    this.turtle.setSpeed(speed / turtlesInSight.size());
                    this.turtle.setDirection(direction / turtlesInSight.size());
                }
                else
                {
                    // The turtle is too close to the herd, make it go back
                    this.turtle.setDirection((this.turtle.getDirection() + 180) % 360);
                    this.turtle.moveForward(this.turtle.getSpeed());
                }
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
