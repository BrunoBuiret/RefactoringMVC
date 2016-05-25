package model;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public interface TurtleObserverInterface
{
    /**
     * Called when a turtle has been updated, whether it is its position or anything
     * else.
     * 
     * @param t The updated turtle.
     */
    public void onTurtleUpdated(AbstractTurtle t);
}
