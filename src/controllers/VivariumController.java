package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import model.AbstractTurtle;
import views.VivariumView;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public class VivariumController implements ActionListener
{
    /**
     * 
     */
    protected VivariumView view;
    
    /**
     * 
     * @param view 
     */
    public VivariumController(VivariumView view)
    {
        this(view, null);
    }
    
    /**
     * 
     * @param view
     * @param turtles 
     */
    public VivariumController(VivariumView view, Set<AbstractTurtle> turtles)
    {
        // Initialize properties
        this.view = view;
        
        if(null != turtles)
        {
            this.addTurtles(turtles);
        }
    }
    
    /**
     * 
     * @param t 
     */
    public void addTurtle(AbstractTurtle t)
    {
        this.view.getVivarium().addTurtle(t);
    }
    
    /**
     * 
     * @param turtles 
     */
    public void addTurtles(Set<AbstractTurtle> turtles)
    {
        this.view.getVivarium().addTurtles(turtles);
    }
    
    /**
     * 
     * @param t 
     */
    public void removeTurtle(AbstractTurtle t)
    {
        this.view.getVivarium().removeTurtle(t);
    }
    
    /**
     * 
     */
    public void removeTurtles()
    {
        this.view.getVivarium().removeTurtles();
    }
    
    /**
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("turn_left"))
        {
            
        }
        else if(e.getActionCommand().equals("move_forward"))
        {
            
        }
        else if(e.getActionCommand().equals("turn_right"))
        {
            
        }
    }
}
