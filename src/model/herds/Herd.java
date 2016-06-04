package model.herds;

import java.awt.Shape;
import java.awt.geom.Area;
import model.turtles.AbstractTurtle;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public class Herd implements Iterable<AbstractTurtle>
{
    /**
     * The turtles composing the herd.
     */
    protected Set<AbstractTurtle> turtles;
    
    /**
     * Creates a new empty herd of turtles.
     */
    public Herd()
    {
        this(new HashSet<>());
    }
    
    /**
     * Creates a new herd with existing turtles.
     * 
     * @param turtles The turtles composing the herd.
     */
    public Herd(Set<AbstractTurtle> turtles)
    {
        // Initialize properties
        this.turtles = turtles;
    }
    
    /**
     * Adds a single turtle to the herd.
     * 
     * @param turtle The turtle to add.
     */
    public void addTurtle(AbstractTurtle turtle)
    {
        this.turtles.add(turtle);
    }
    
    /**
     * Adds a collection of turtles to the herd.
     *
     * @param turtles The turtles to add.
     */
    public void addTurtles(Set<AbstractTurtle> turtles)
    {
        this.turtles.addAll(turtles);
    }
    
    /**
     * Removes a single turtle from the herd.
     * 
     * @param turtle The turtle to remove.
     */
    public void removeTurtle(AbstractTurtle turtle)
    {
        this.turtles.remove(turtle);
    }
    
    /**
     * Removes every turtle from the herd.
     */
    public void removeTurtles()
    {
        this.turtles.clear();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<AbstractTurtle> iterator()
    {
        return this.turtles.iterator();
    }
    
    /**
     * Looks for turtles in a specific zone.
     * 
     * @param zone The zone to look in.
     * @param exception A turtle not to take into account.
     * @return The turtles in this zone.
     */
    public Set<AbstractTurtle> getTurtlesIn(Shape zone, AbstractTurtle exception)
    {
        // Initialize vars
        Set<AbstractTurtle> turtlesInZone = new HashSet<>();
        Area zoneArea = new Area(zone), turtleArea;
        
        for(AbstractTurtle turtle : this.turtles)
        {
            if(null == exception || !exception.equals(turtle))
            {
                turtleArea = new Area(turtle.getShape());
                turtleArea.intersect(zoneArea);

                if(!turtleArea.isEmpty())
                {
                    turtlesInZone.add(turtle);
                }
            }
        }
        
        return turtlesInZone;
    }
}
