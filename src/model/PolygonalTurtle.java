package model;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public class PolygonalTurtle extends AbstractTurtle
{
    /**
     * The turtle' shape.
     */
    protected Path2D.Double shape;
    
    /**
     * The shape's number of edges.
     */
    protected int edgesNumber;
    
    /**
     * The shape's radius.
     */
    protected double radius;
    
    /**
     * Creates a new polygonal turtle with the default values.
     */
    public PolygonalTurtle()
    {
        this(0., 0., Color.BLACK, 8, 5.);
    }
    
    /**
     * Creates a custom new polygonal turtle.
     * 
     * @param x The turtle's abscissa.
     * @param y The turtle's ordinate.
     * @param color The turtle's color.
     * @param edgesNumber The shape's number of edges.
     * @param radius The shape's radius.
     */
    public PolygonalTurtle(double x, double y, Color color, int edgesNumber, double radius)
    {
        // Call super constructor
        super(x, y, color);
        
        // Perform some checks
        if(edgesNumber <= 0)
        {
            throw new IllegalArgumentException(
                "The number of edges can't be null or negative."
            );
        }
        
        if(radius <= 0)
        {
            throw new IllegalArgumentException(
                "The radius can't be null or negative."
            );
        }
        
        // Initialize properties
        this.edgesNumber = edgesNumber;
        this.radius = radius;
        
        // Initialize shape
        double angle = 360 / edgesNumber;
        this.shape = new Path2D.Double();
        this.shape.moveTo(
                x + this.radius,
                y
            );
        
        for(int i = 1; i <= this.edgesNumber; i++)
        {
            this.shape.lineTo(
                x + this.radius * Math.cos(angle * i),
                y + this.radius * Math.sin(angle * i)
            );
        }
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
        // Initialize affine transform
        AffineTransform transform = new AffineTransform();
        transform.setToTranslation(
            this.x + distance * Math.cos(Math.toRadians(this.direction)),
            this.y + distance * Math.sin(Math.toRadians(this.direction))
        );
        
        // Perform affine transform
        this.shape.transform(transform);
        
        // Notify the observers
        this.notifyObservers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void turnLeft(double angle)
    {
        // Initialize affine transform
        AffineTransform transform = new AffineTransform();
        transform.setToRotation(Math.toRadians(angle));
        
        // Perform affine transform
        this.shape.transform(transform);
        
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
        
        // Notify the observers
        this.notifyObservers();
    }
}
