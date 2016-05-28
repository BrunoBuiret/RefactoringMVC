package model.herds;

import model.turtles.AbstractTurtle;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public class Herd
{
    /**
     * 
     */
    protected Set<AbstractTurtle> turtles;
    
    /**
     * 
     */
    public Herd()
    {
        this(new HashSet<>());
    }
    
    /**
     * 
     * @param turtles 
     */
    public Herd(Set<AbstractTurtle> turtles)
    {
        // Initialize properties
        this.turtles = turtles;
    }
    
    /**
     * 
     * @param turtle 
     */
    public void addTurtle(AbstractTurtle turtle)
    {
        this.turtles.add(turtle);
    }
    
    /**
     * 
     * @param turtles 
     */
    public void addTurtles(List<AbstractTurtle> turtles)
    {
        this.turtles.addAll(turtles);
    }
    
    /**
     * 
     * @param turtle 
     */
    public void removeTurtle(AbstractTurtle turtle)
    {
        this.turtles.remove(turtle);
    }
    
    /**
     * 
     */
    public void removeTurtles()
    {
        this.turtles.clear();
    }
}
