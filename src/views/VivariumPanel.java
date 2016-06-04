package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
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
     * Indicates if the vivarium should have debug data printed.
     */
    protected boolean isDebug;

    /**
     * Creates a new vivarium panel.
     */
    public VivariumPanel()
    {
        // Call super constructor
        super();

        // Initialize properties
        this.turtles = new HashSet<>();
        this.isDebug = false;
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
        // Start observing every turtle
        for(AbstractTurtle t : turtles)
        {
            t.addObserver(this);
        }

        // And remove them from the vivarium
        this.turtles.addAll(turtles);

        // Then, fire paint event
        this.repaint();
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
     * Sets the debug mode of the vivarium.
     * In debug mode, informations is written: sight areas, speed, direction,
     * and the turtles' actual position.
     * 
     * @param isDebug The debug mode.
     */
    public void setDebug(boolean isDebug)
    {
        this.isDebug = isDebug;
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
            // Initialize vars
            Graphics2D graphics = (Graphics2D) g;
            Dimension panelDimension = this.getSize();
            Rectangle2D turtleBounds;
            int fontHeight = g.getFontMetrics().getHeight();

            // Clear drawing sheet
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, (int) panelDimension.getWidth(), (int) panelDimension.getHeight());

            // Draw every turtle
            for(AbstractTurtle turtle : this.turtles)
            {
                // Draw the turtle's shape
                graphics.setColor(turtle.getColor());
                graphics.fill(turtle.getShape());

                // Debug mode
                if(this.isDebug)
                {
                    // Draw the turtle's sight
                    graphics.setColor(Color.BLACK);
                    graphics.draw(turtle.getSight());

                    // Draw the turtle's center
                    graphics.setColor(Color.CYAN);
                    graphics.drawLine(
                        (int) turtle.getX() - 10,
                        (int) turtle.getY(),
                        (int) turtle.getX() + 10,
                        (int) turtle.getY()
                    );
                    graphics.drawLine(
                        (int) turtle.getX(),
                        (int) turtle.getY() - 10,
                        (int) turtle.getX(),
                        (int) turtle.getY() + 10
                    );

                    // Write the turtle's speed
                    turtleBounds = turtle.getShape().getBounds2D();

                    graphics.setColor(Color.BLACK);
                    graphics.drawString(
                        "Vitesse : " + String.format("%.2f", turtle.getSpeed()),
                        (int) turtleBounds.getMaxX() + 5,
                        (int) turtleBounds.getMinY() - 5
                    );
                    graphics.drawString(
                        "Direction : " + String.format("%.2f", turtle.getDirection()),
                        (int) turtleBounds.getMaxX() + 5,
                        (int) turtleBounds.getMinY() - 5 + fontHeight
                    );
                }
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
