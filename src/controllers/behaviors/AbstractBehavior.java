package controllers.behaviors;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
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
     * Handles the turtle's behavior.
     */
    @Override
    public abstract void run();
    
    /**
     * Checks the turtle isn't going out of the vivarium's view and, if so, makes
     * it appear on the other side.
     */
    protected void checkBounds()
    {
        Dimension vivariumDimension = this.vivarium.getSize();
        Rectangle2D turtleBounds = this.turtle.getShape().getBounds2D();

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
    }
}
