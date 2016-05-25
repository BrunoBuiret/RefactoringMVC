package model;

import java.awt.Color;
import java.awt.Shape;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public abstract class AbstractTurtle
{
    /**
     * The turtle's abscissa.
     */
    protected double x;
    
    /**
     * The turtle's ordinate.
     */
    protected double y;
    
    /**
     * The turtle's direction.
     */
    protected double direction;
    
    /**
     * The turtle's color.
     */
    protected Color color;
    
    /**
     * A set of objects observing this turtle.
     */
    protected Set<TurtleObserverInterface> observers;
    
    /**
     * Creates a new turtle with the default values.
     */
    public AbstractTurtle()
    {
        this(0., 0., Color.BLACK);
    }
    
    /**
     * Creates a new custom turtle.
     * 
     * @param x The turtle's abscissa.
     * @param y The turtle's ordinate.
     * @param color The turtle's color.
     */
    public AbstractTurtle(double x, double y, Color color)
    {
        // Initialize properties;
        this.x = x;
        this.y = y;
        this.color = color;
        this.direction = -90.;
        this.observers = new HashSet<>();
    }
    
    /**
     * Gets the turtle's abscissa.
     * 
     * @return The turtle's abscissa.
     */
    public double getX()
    {
        return this.x;
    }

    /**
     * Sets the turtle's abscissa.
     * 
     * @param x The turtle's abscissa.
     */
    public void setX(double x)
    {
        this.x = x;
    }

    /**
     * Gets the turtle's ordinate.
     * 
     * @return The turtle's ordinate.
     */
    public double getY()
    {
        return this.y;
    }

    /**
     * Sets the turtle's ordinate.
     * 
     * @param y The turtle's ordinate;
     */
    public void setY(double y)
    {
        this.y = y;
    }

    /**
     * Gets the turtle's direction.
     * 
     * @return The turtle's direction.
     */
    public double getDirection()
    {
        return this.direction;
    }

    /**
     * Sets the turtle's direction.
     * 
     * @param direction The turtle's direction.
     */
    public void setDirection(double direction)
    {
        this.direction = direction;
    }

    /**
     * Gets the turtle's color.
     * 
     * @return The turtle's color.
     */
    public Color getColor()
    {
        return this.color;
    }
    
    /**
     * Gets the turtle' shape.
     * 
     * @return The turtle' shape.
     */
    public abstract Shape getShape();
    
    /**
     * Makes the turtle move forward.
     * 
     * @param distance The distance to travel.
     */
    public abstract void moveForward(double distance);
    
    /**
     * Makes the turtle turn to the left.
     * 
     * @param angle The angle to turn.
     */
    public abstract void turnLeft(double angle);
    
    /**
     * Makes the turtle turn to the right.
     * 
     * @param angle The angle to turn.
     */
    public abstract void turnRight(double angle);
    
    /**
     * Adds an observer of this turtle.
     * 
     * @param o The observer.
     */
    public void addObserver(TurtleObserverInterface o)
    {
        this.observers.add(o);
    }
    
    /**
     * Removes an observer of this turtle.
     * 
     * @param o The observer.
     */
    public void removeObserver(TurtleObserverInterface o)
    {
        this.observers.remove(o);
    }
    
    /**
     * Removes every observers this turtle has.
     */
    public void removeObservers()
    {
        this.observers.clear();
    }
    
    /**
     * Notify every observers this turtle has that something has been updated.
     */
    public void notifyObservers()
    {
        for(TurtleObserverInterface o : this.observers)
        {
            o.onTurtleUpdated(this);
        }
    }
}
