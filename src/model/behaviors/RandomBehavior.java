package model.behaviors;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.herds.Herd;
import model.turtles.AbstractTurtle;

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
     */
    public RandomBehavior(Herd herd, AbstractTurtle turtle)
    {
        super(herd, turtle);
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
            switch(randomizer.nextInt(3))
            {
                case 0:
                    // Move forward
                    this.turtle.moveForward(50 * randomizer.nextDouble());
                break;
                
                case 1:
                    // Turn left
                    this.turtle.turnRight(randomizer.nextInt(360));
                break;
                    
                case 2:
                    // Turn right
                    this.turtle.turnRight(randomizer.nextInt(360));
                break;
            }
            
            // Wait before the next action
            try
            {
                Thread.sleep((long) (500 + 500 * randomizer.nextDouble()));
            }
            catch(InterruptedException ex)
            {
                Logger.getLogger(RandomBehavior.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
