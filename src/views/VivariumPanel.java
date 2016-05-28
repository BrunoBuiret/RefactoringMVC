package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JPanel;
import model.turtles.AbstractTurtle;
import model.turtles.TurtleObserverInterface;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public class VivariumPanel extends JPanel implements TurtleObserverInterface
{
    /**
     * The set of turtles inside of the vivarium.
     */
    protected Set<AbstractTurtle> turtles;
    
    /**
     * Creates a new vivarium panel.
     */
    public VivariumPanel()
    {
        // Call super constructor
        super();
        
        // Initialize properties
        this.turtles = new HashSet<>();
    }
    
    /**
     * Adds a turtle to the vivarium.
     * 
     * @param t The turtle to add.
     */
    public void addTurtle(AbstractTurtle t)
    {
        // Add the turtle to the vivarium and observe it
        this.turtles.add(t);
        t.addObserver(this);
        
        // Then, fire paint event
        this.repaint();
    }
    
    /**
     * Adds a set of turtles to the vivarium.
     * 
     * @param turtles The set of turtles.
     */
    public void addTurtles(Set<AbstractTurtle> turtles)
    {
        this.turtles.addAll(turtles);
    }
    
    /**
     * Removes a turtle from the vivarium.
     * 
     * @param t The turtle to remove.
     */
    public void removeTurtle(AbstractTurtle t)
    {
        // Remove the turtle from the vivarium and stop observing it
        this.turtles.remove(t);
        t.removeObserver(this);
        
        // Then, fire paint event
        this.repaint();
    }
    
    /**
     * Removes every turtle from the vivarium.
     */
    public void removeTurtles()
    {
        // Stop observing every turtle
        for(AbstractTurtle t : this.turtles)
        {
            t.removeObserver(this);
        }
        
        // And remove them from the vivarium
        this.turtles.clear();
        
        // Then, fire paint event
        this.repaint();
    }
    
    /**
     * Paints this panel to represent the vivarium.
     * 
     * @param g The graphics manager.
     */
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        
        if(g instanceof Graphics2D)
        {
            // Get 2D graphics manager
            Graphics2D graphics = (Graphics2D) g;
            
            // Clear drawing sheet
            Dimension panelDimension = this.getSize();
            
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, (int) panelDimension.getWidth(), (int) panelDimension.getHeight());
            
            // Draw every turtle
            for(AbstractTurtle turtle : this.turtles)
            {
                graphics.setColor(turtle.getColor());
                graphics.fill(turtle.getShape());
            }
        }
    }

    /**
     * Called when a turtle is updated, be it its position or its color.
     * 
     * @param t The updated turtle.
     */
    @Override
    public void onTurtleUpdated(AbstractTurtle t)
    {
        // Fire paint event
        this.repaint();
    }
}
