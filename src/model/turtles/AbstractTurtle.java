package model.turtles;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
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
     * The turtle's sight's radius.
     * This represents how far the turtle can see.
     */
    protected double sightRadius;
    
    /**
     * The turtle's sight's angle.
     * This represents the sight's width.
     */
    protected double sightAngle;
    
    /**
     * The turtle's speed.
     */
    protected double speed;
    
    /**
     * A set of objects observing this turtle.
     */
    protected Set<TurtleObserverInterface> observers;
    
    /**
     * Creates a new turtle with the default values.
     */
    public AbstractTurtle()
    {
        this(0., 0., Color.BLACK, 20., 70., 30.);
    }
    
    /**
     * Creates a new custom turtle.
     * 
     * @param x The turtle's abscissa.
     * @param y The turtle's ordinate.
     * @param color The turtle's color.
     * @param sightRadius The turtle's sight's radius.
     * @param sightAngle The turtle's sight's angle.
     * @param speed The turtle's speed.
     */
    public AbstractTurtle(double x, double y, Color color, double sightRadius, double sightAngle, double speed)
    {
        // Initialize properties;
        this.x = x;
        this.y = y;
        this.direction = -90.;
        this.color = color;
        this.sightRadius = sightRadius;
        this.sightAngle = sightAngle;
        this.speed = speed;
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
        this.notifyObservers();
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
        this.notifyObservers();
    }
    
    /**
     * Gets the turtle's position.
     * 
     * @return The turtle's position.
     * @todo Optimize this method, maybe, replace the x and y properties with
     * this point.
     */
    public Point2D.Double getPosition()
    {
        return new Point2D.Double(this.x, this.y);
    }
    
    /**
     * Sets the turtle's position.
     * 
     * @param position The turtle's position.
     */
    public void setPosition(Point2D.Double position)
    {
        this.setPosition(position.x, position.y);
    }
    
    /**
     * Sets the turtle's position.
     * 
     * @param x The turtle's abscissa.
     * @param y The turtle's ordinate.
     */
    public void setPosition(double x, double y)
    {
        this.x = x;
        this.y = y;
        this.notifyObservers();
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
        this.notifyObservers();
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
     * Gets the point at which the turtle's sight begins.
     * 
     * @return The sight's beginning point.
     */
    protected abstract Point2D.Double getSightPoint();
    
    /**
     * Gets the turtle's sight area.
     * 
     * @return The turtle's sight area.
     * @todo Optimize this method by memorizing the sight as a property and
     * making it move as the turtle does.
     */
    public Shape getSight()
    {
        // Initialize vars
        Path2D.Double sight = new Path2D.Double();
        Point2D.Double sightPoint = this.getSightPoint();
        
        sight.moveTo(
            sightPoint.x,
            sightPoint.y
        );
        sight.lineTo(
            sightPoint.x + this.sightRadius * Math.cos(Math.toRadians((this.direction - (this.sightAngle / 2)) % 360)),
            sightPoint.y + this.sightRadius * Math.sin(Math.toRadians((this.direction - (this.sightAngle / 2)) % 360))
        );
        sight.curveTo(
            sightPoint.x + this.sightRadius * Math.cos(Math.toRadians((this.direction - (this.sightAngle / 2)) % 360)),
            sightPoint.y + this.sightRadius * Math.sin(Math.toRadians((this.direction - (this.sightAngle / 2)) % 360)),
            sightPoint.x + this.sightRadius * Math.cos(Math.toRadians(this.direction)),
            sightPoint.y + this.sightRadius * Math.sin(Math.toRadians(this.direction)),
            sightPoint.x + this.sightRadius * Math.cos(Math.toRadians((this.direction + (this.sightAngle / 2)) % 360)),
            sightPoint.y + this.sightRadius * Math.sin(Math.toRadians((this.direction + (this.sightAngle / 2)) % 360))
        );
        sight.lineTo(
            sightPoint.x,
            sightPoint.y
        );
        
        return sight;
    }
    
    /**
     * Gets the turtle's speed.
     * 
     * @return The turtle's speed.
     */
    public double getSpeed()
    {
        return this.speed;
    }
    
    /**
     * Sets the turtle's speed.
     * 
     * @param speed The turtle's speed.
     */
    public void setSpeed(double speed)
    {
        this.speed = speed;
    }
    
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
    public void turnLeft(double angle)
    {
        // Memorize new direction
        this.direction = (this.direction - angle) % 360;
        
        // Notify the observers
        this.notifyObservers();
    }
    
    /**
     * Makes the turtle turn to the right.
     * 
     * @param angle The angle to turn.
     */
    public void turnRight(double angle)
    {
        // Make the turtle turn
        this.turnLeft(-angle);
    }
    
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
     * Gets the list of this turtle's observers.
     * 
     * @return The list of observers.
     */
    public Set<TurtleObserverInterface> getObservers()
    {
        return this.observers;
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
