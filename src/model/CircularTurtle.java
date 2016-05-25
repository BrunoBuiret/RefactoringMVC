package model;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public class CircularTurtle extends AbstractTurtle
{
    /**
     * The turtle' shape.
     */
    protected Ellipse2D.Double shape;
    
    /**
     * The shape's radius.
     */
    protected double radius;
    
    /**
     * Creates a new polygonal turtle with the default values.
     */
    public CircularTurtle()
    {
        this(0., 0., Color.BLACK, 5.);
    }
    
    /**
     * Creates a custom new polygonal turtle.
     * 
     * @param x The turtle's abscissa.
     * @param y The turtle's ordinate.
     * @param color The turtle's color.
     * @param radius The shape's radius.
     */
    public CircularTurtle(double x, double y, Color color, double radius)
    {
        // Call super constructor
        super(x, y, color);
        
        // Perform some checks
        if(radius <= 0)
        {
            throw new IllegalArgumentException(
                "The radius can't be null or negative."
            );
        }
        
        // Initialize properties
        this.radius = radius;
        
        // Initialize shape
        this.shape = new Ellipse2D.Double(this.x - radius, this.y - this.radius, 2 * this.radius, 2 * this.radius);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Shape getShape()
    {
        return this.shape;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveForward(double distance)
    {
        // Initialize vars
        double deltaX = distance * Math.cos(Math.toRadians(this.direction));
        double deltaY = distance * Math.sin(Math.toRadians(this.direction));
        
        // Memorize new coordinates
        this.shape.x += deltaX;
        this.shape.y += deltaY;
        this.x += deltaX;
        this.y += deltaY;
        
        // Notify the observers
        this.notifyObservers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void turnLeft(double angle)
    {
        // Memorize new direction
        this.direction = (this.direction - angle) % 360;
        
        // Notify the observers
        this.notifyObservers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void turnRight(double angle)
    {
        // Make the turtle turn
        this.turnLeft(-angle);
    }
}
