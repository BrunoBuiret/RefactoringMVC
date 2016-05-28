package controllers.behaviors;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
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
        Dimension vivariumDimension;
        Rectangle2D turtleBounds;
        
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
                    this.turtle.turnLeft(360 * randomizer.nextDouble());
                break;
                    
                case 2:
                    // Turn right
                    this.turtle.turnRight(360 * randomizer.nextDouble());
                break;
            }
            
            // Check if the turtle is going out of the vivarium, ie, the screen
            vivariumDimension = this.vivarium.getSize();
            turtleBounds = this.turtle.getShape().getBounds2D();
            
            // Is the turtle getting out of the vivarium?
            if(
                turtleBounds.getMinX() < 0 ||
                turtleBounds.getMaxX() >= vivariumDimension.getWidth() ||
                turtleBounds.getMinY() < 0 ||
                turtleBounds.getMaxY() >= vivariumDimension.getHeight()
            )
            {
                // Top-left corner
                if(turtleBounds.getMinX() < 0 && turtleBounds.getMinY() < 0)
                {
                    // Make it appear at the bottom-right corner
                    this.turtle.setPosition(
                        vivariumDimension.getWidth() - (turtleBounds.getMaxX() - this.turtle.getX()),
                        vivariumDimension.getHeight() - (turtleBounds.getMaxY() - this.turtle.getY())
                    );
                }
                // Top-right corner
                else if(turtleBounds.getMaxX() >= vivariumDimension.getWidth() && turtleBounds.getMinY() < 0)
                {
                    // Make it appear at the bottom-left corner
                    this.turtle.setPosition(
                        this.turtle.getX() - turtleBounds.getMinX(),
                        vivariumDimension.getHeight() - (turtleBounds.getMaxY() - this.turtle.getY())
                    );
                }
                // Bottom-right corner
                else if(turtleBounds.getMaxX() >= vivariumDimension.getWidth() && turtleBounds.getMaxY() >= vivariumDimension.getHeight())
                {
                    // Make it appear at the top-left corner
                    this.turtle.setPosition(
                        this.turtle.getX() - turtleBounds.getMinX(),
                        this.turtle.getY() - turtleBounds.getMinY()
                    );
                }
                // Bottom-left corner
                else if(turtleBounds.getMinX() < 0 && turtleBounds.getMaxY() >= vivariumDimension.getHeight())
                {
                    // Make it appear at the top-right corner
                    this.turtle.setPosition(
                        vivariumDimension.getWidth() - (turtleBounds.getMaxX() - this.turtle.getX()),
                        this.turtle.getY() - turtleBounds.getMinY()
                    );
                }
                // Left bound
                else if(turtleBounds.getMinX() < 0 && turtleBounds.getMinY() >= 0)
                {
                    // Make it appear at the right bound
                    this.turtle.setX(vivariumDimension.getWidth() - (turtleBounds.getMaxX() - this.turtle.getX()));
                }
                // Top bound
                else if(turtleBounds.getMinX() >= 0 && turtleBounds.getMinY() < 0)
                {
                    // Make it appear at the bottom bound
                    this.turtle.setY(vivariumDimension.getHeight() - (turtleBounds.getMaxY() - this.turtle.getY()));
                }
                // Right bound
                else if(turtleBounds.getMaxX() >= vivariumDimension.getWidth() && turtleBounds.getMinY() >= 0)
                {
                    // Make it appear at the left bound
                    this.turtle.setX(this.turtle.getX() - turtleBounds.getMinX());
                }
                // Bottom bound
                else if(turtleBounds.getMinX() >= 0 && turtleBounds.getMaxY() >= vivariumDimension.getHeight())
                {
                    // Make it appear at the top bound
                    this.turtle.setY(this.turtle.getY() - turtleBounds.getMinY());
                }
            }
            
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
}
