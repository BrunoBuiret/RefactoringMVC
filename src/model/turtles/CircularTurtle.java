package model.turtles;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

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
        this(0., 0., Color.BLACK, 5., 20., 30., 70.);
    }
    
    /**
     * Creates a custom new polygonal turtle.
     * 
     * @param x The turtle's abscissa.
     * @param y The turtle's ordinate.
     * @param color The turtle's color.
     * @param sightRadius The turtle's sight's radius.
     * @param sightAngle The turtle's sight's angle.
     * @param speed The turtle's speed.
     * @param radius The shape's radius.
     */
    public CircularTurtle(double x, double y, Color color, double sightRadius, double sightAngle, double speed, double radius)
    {
        // Call super constructor
        super(x, y, color, sightRadius, sightAngle, speed);
        
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
        this.shape = new Ellipse2D.Double(
            this.x - this.radius,
            this.y - this.radius,
            2 * this.radius,
            2 * this.radius
        );
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setX(double x)
    {
        // Memorize new shifted abscissa
        this.shape.x = x - this.radius;
        
        // Memorize new abscissa
        super.setX(x);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setY(double y)
    {
        // Memorize new shifted ordinate
        this.shape.y = y - this.radius;
        
        // Memorize new ordinate
        super.setY(y);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(double x, double y)
    {
        // Memorize new shifted position
        this.shape.x = x - this.radius;
        this.shape.y = y - this.radius;
        
        // Memorize new position
        super.setPosition(x, y);
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
    protected Point2D.Double getSightPoint()
    {
        return new Point2D.Double(
            this.x + this.radius * Math.cos(Math.toRadians(this.direction)),
            this.y + this.radius * Math.sin(Math.toRadians(this.direction))
        );
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
}
