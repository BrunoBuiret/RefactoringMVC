package controllers;

import controllers.behaviors.FlockingBehavior;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Rectangle2D;
import model.turtles.AbstractTurtle;
import model.herds.Herd;
import utils.TurtleUtils;
import views.VivariumPanel;
import views.VivariumView;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public class VivariumController implements ActionListener, ComponentListener
{
    /**
     * An unknown mode for the vivarium.
     */
    public static final int MODE_UNKNOWN = -1;
    
    /**
     * A herd of turtles is going to be observed in the vivarium.
     */
    public static final int MODE_OBSERVATION = 0;

    /**
     * A single turtle is going to be controlled by the user.
     */
    public static final int MODE_CONTROL = 1;

    /**
     * A reference to the view being controlled.
     */
    protected VivariumView view;

    /**
     * A reference to the turtle currently in the vivarium.
     */
    protected AbstractTurtle theTurtle;

    /**
     * A reference to the herd of turtles currently in the vivarium.
     */
    protected Herd theHerd;

    /**
     * Creates a new controller for a vivarium.
     *
     * @param view The view to control.
     */
    public VivariumController(VivariumView view)
    {
        this(view, null);
    }

    /**
     * Creates a new controller for a vivarium.
     *
     * @param view The view to control.
     * @param theTurtle The turtle to control.
     */
    public VivariumController(VivariumView view, AbstractTurtle theTurtle)
    {
        // Initialize properties
        this.view = view;
        this.theTurtle = null;
        this.theHerd = new Herd();
    }

    /**
     * Sets the vivarium's mode.
     * Two modes are available: an observation one, {@code VivariumController.MODE_OBSERVATION},
     * to show a flocking behavior, and another, {@code VivariumController.MODE_CONTROL},
     * meant to control a single turtle.
     * 
     * @param mode The vivarium's mode.
     */
    public void setMode(int mode)
    {
        // Initialize vars
        Dimension vivariumDimension = this.view.getVivarium().getSize();

        switch(mode)
        {
            case VivariumController.MODE_OBSERVATION:
            case VivariumController.MODE_UNKNOWN:
                // Add turtles to the herd
                AbstractTurtle turtle;
                Random randomizer = new Random();
                VivariumPanel vivarium = this.view.getVivarium();
                FlockingBehavior behavior;
                int turtlesNumber = 5 + randomizer.nextInt(10);

                for(int i = 0; i < turtlesNumber; i++)
                {
                    // Create a random turtle
                    turtle = TurtleUtils.createRandomTurtle(vivariumDimension);
                    vivarium.addTurtle(turtle);
                    this.theHerd.addTurtle(turtle);

                    // And give it a behavior
                    behavior = new FlockingBehavior(this.theHerd, turtle, this.view.getVivarium());
                    behavior.start();
                }

                // Disable interface
                this.view.setInterfaceEnabled(false);
            break;

            case VivariumController.MODE_CONTROL:
                // Create a single turtle
                this.theTurtle = TurtleUtils.createRandomTurtle(vivariumDimension);
                this.view.getVivarium().addTurtle(this.theTurtle);
                this.theHerd.addTurtle(this.theTurtle);

                // Enable interface
                this.view.setInterfaceEnabled(true);
            break;

            default:
                throw new IllegalArgumentException("This mode doesn't exist.");
        }
    }

    /**
     * Called when an event happens to the view.
     * The event is triggered by the user clicking on a button.
     *
     * @param e The event that happened.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        switch(e.getActionCommand())
        {
            case "turn_left":
                if(null != this.theTurtle)
                {
                    this.theTurtle.turnLeft(this.view.getUserInput());
                }
            break;

            case "move_forward":
                if(null != this.theTurtle)
                {
                    this.theTurtle.moveForward(this.view.getUserInput());
                }
            break;

            case "turn_right":
                if(null != this.theTurtle)
                {
                    this.theTurtle.turnRight(this.view.getUserInput());
                }
            break;

            default:
        }
    }

    /**
     * Called when the vivarium is resized.
     * The turtles that could be out of the vivarium's bound should be moved to
     * the new bounds.
     *
     * @param e The event that happened.
     * @todo Implement collision detection so as to avoid turtles being on each other.
     */
    @Override
    public void componentResized(ComponentEvent e)
    {
        // Initialize vars
        Dimension vivariumDimension = this.view.getVivarium().getSize();
        Rectangle2D turtleBounds;

        for(AbstractTurtle turtle : this.theHerd)
        {
            // Further initialize vars
            turtleBounds = turtle.getShape().getBounds2D();

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
                    turtle.setPosition(
                        turtle.getX() - turtleBounds.getMinX(),
                        turtle.getY() - turtleBounds.getMinY()
                    );
                }
                // Top-right corner
                else if(turtleBounds.getMaxX() >= vivariumDimension.getWidth() && turtleBounds.getMinY() < 0)
                {
                    turtle.setPosition(
                        vivariumDimension.getWidth() - (turtleBounds.getMaxX() - turtle.getX()),
                        turtle.getY() - turtleBounds.getMinY()
                    );
                }
                // Bottom-right corner
                else if(turtleBounds.getMaxX() >= vivariumDimension.getWidth() && turtleBounds.getMaxY() >= vivariumDimension.getHeight())
                {
                    turtle.setPosition(
                        vivariumDimension.getWidth() - (turtleBounds.getMaxX() - turtle.getX()),
                        vivariumDimension.getHeight() - (turtleBounds.getMaxY() - turtle.getY())
                    );
                }
                // Bottom-left corner
                else if(turtleBounds.getMinX() < 0 && turtleBounds.getMaxY() >= vivariumDimension.getHeight())
                {
                    turtle.setPosition(
                        turtle.getX() - turtleBounds.getMinX(),
                        vivariumDimension.getHeight() - (turtleBounds.getMaxY() - turtle.getY())
                    );
                }
                // Left bound
                else if(turtleBounds.getMinX() < 0 && turtleBounds.getMinY() >= 0)
                {
                    turtle.setX(turtle.getX() - turtleBounds.getMinX());
                }
                // Top bound
                else if(turtleBounds.getMinX() >= 0 && turtleBounds.getMinY() < 0)
                {
                    // Make it appear at the bottom bound
                    turtle.setY(turtle.getY() - turtleBounds.getMinY());
                }
                // Right bound
                else if(turtleBounds.getMaxX() >= vivariumDimension.getWidth() && turtleBounds.getMinY() >= 0)
                {
                    turtle.setX(vivariumDimension.getWidth() - (turtleBounds.getMaxX() - turtle.getX()));
                }
                // Bottom bound
                else if(turtleBounds.getMinX() >= 0 && turtleBounds.getMaxY() >= vivariumDimension.getHeight())
                {
                    turtle.setY(vivariumDimension.getHeight() - (turtleBounds.getMaxY() - turtle.getY()));
                }
            }
        }
    }

    /**
     * Called when the vivarium is moved.
     * Nothing special happens if the vivarium is moved.
     *
     * @param e The event that happened.
     */
    @Override
    public void componentMoved(ComponentEvent e)
    {
    }

    /**
     * Called when the vivarium is shown.
     * Nothing special happens if the vivarium is shown.
     *
     * @param e The event that happened.
     */
    @Override
    public void componentShown(ComponentEvent e)
    {
    }

    /**
     * Called when the vivarium is hidden.
     * Nothing special happens if the vivarium is hidden.
     *
     * @param e The event that happened.
     */
    @Override
    public void componentHidden(ComponentEvent e)
    {
    }
}
