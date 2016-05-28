package controllers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import model.behaviors.RandomBehavior;
import model.turtles.AbstractTurtle;
import model.herds.Herd;
import utils.TurtleUtils;
import views.VivariumPanel;
import views.VivariumView;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public class VivariumController implements ActionListener
{
    /**
     * 
     */
    public static final int MODE_OBSERVATION = 0;
    
    /**
     * 
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
     * 
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
        this.theHerd = null;
    }
    
    /**
     * 
     * @param mode 
     */
    public void setMode(int mode)
    {
        // Initialize vars
        Dimension vivariumDimension = this.view.getVivarium().getSize();
        
        switch(mode)
        {
            case VivariumController.MODE_OBSERVATION:
                // Initialize properties
                this.theHerd = new Herd();
                
                // Add turtles to the herd
                AbstractTurtle turtle;
                Random randomizer = new Random();
                VivariumPanel vivarium = this.view.getVivarium();
                RandomBehavior behavior;
                int turtlesNumber = 5 + randomizer.nextInt(5);
                
                for(int i = 0; i < turtlesNumber; i++)
                {
                    // Create a random turtle
                    turtle = TurtleUtils.createRandomTurtle(vivariumDimension);
                    vivarium.addTurtle(turtle);
                    this.theHerd.addTurtle(turtle);
                    
                    // And give it a behavior
                    behavior = new RandomBehavior(this.theHerd, turtle);
                    behavior.start();
                }
                
                // Disables interface
                this.view.setInterfaceEnabled(false);
            break;
                
            case VivariumController.MODE_CONTROL:
                // Create a single turtle
                this.theTurtle = TurtleUtils.createRandomTurtle(vivariumDimension);
                this.view.getVivarium().addTurtle(this.theTurtle);
                
                // Enables interface
                this.view.setInterfaceEnabled(true);
            break;
            
            default:
                throw new IllegalArgumentException("This mode doesn't exist.");
        }
    }
    
    /**
     * Called when an event happens to the view.
     * 
     * @param e The event that happened.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("turn_left"))
        {
            if(null != this.theTurtle)
            {
                this.theTurtle.turnLeft(this.view.getUserInput());
            }
        }
        else if(e.getActionCommand().equals("move_forward"))
        {
            if(null != this.theTurtle)
            {
                this.theTurtle.moveForward(this.view.getUserInput());
            }
        }
        else if(e.getActionCommand().equals("turn_right"))
        {
            if(null != this.theTurtle)
            {
                this.theTurtle.turnRight(this.view.getUserInput());
            }
        }
    }
}
