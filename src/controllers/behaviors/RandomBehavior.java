package controllers.behaviors;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.herds.Herd;
import model.turtles.AbstractTurtle;
import views.VivariumPanel;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public class RandomBehavior extends AbstractBehavior
{
    /**
     * Creates a new random behavior for a single turtle.
     * 
     * @param herd The herd to which the turtle belongs.
     * @param turtle The turtle with this behavior.
     * @param vivarium
     */
    public RandomBehavior(Herd herd, AbstractTurtle turtle, VivariumPanel vivarium)
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
        
        while(true)
        {
            // Do a random action
            this.doSomethingRandom(randomizer);
            
            // Check if the turtle is going out of the vivarium, ie, the screen
            this.checkBounds();
            
            // Wait before the next action
            try
            {
                Thread.sleep((long) (300 + 500 * randomizer.nextDouble()));
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(RandomBehavior.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Makes the turtle do a random action: moving forward, turning left or
     * turning right.
     * 
     * @param randomizer A {@code Random} instance.
     */
    protected void doSomethingRandom(Random randomizer)
    {
        switch(randomizer.nextInt(3))
        {
            case 0:
                // Move forward
                this.turtle.moveForward(this.turtle.getSpeed());
            break;

            case 1:
                // Turn left
                this.turtle.turnLeft(360 * randomizer.nextDouble());
            break;

            case 2:
                // Turn right
                this.turtle.turnRight(360 * randomizer.nextDouble());
            break;
        }
    }
}
