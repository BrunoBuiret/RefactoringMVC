package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.AbstractTurtle;
import views.VivariumView;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public class VivariumController implements ActionListener
{
    /**
     * A reference to the view being controlled.
     */
    protected VivariumView view;
    
    /**
     * A reference to the turtle currently in the vivarium.
     */
    protected AbstractTurtle theTurtle;
    
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
        this.theTurtle = theTurtle;
        
        // Add the turtle to the vivarium
        if(null != this.theTurtle)
        {
            this.view.getVivarium().addTurtle(this.theTurtle);
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
