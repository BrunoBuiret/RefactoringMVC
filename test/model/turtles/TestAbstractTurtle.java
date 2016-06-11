package model.turtles;

import java.awt.Color;
import java.awt.geom.Point2D;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public class TestAbstractTurtle
{
    /**
     * Tests the initialization of a turtle with the default values.
     */
    @Test
    public void testDefaultTurtle()
    {
        // Initialize vars
        AbstractTurtle turtle = new CircularTurtle();
        
        // Perform tests
        Assert.assertEquals(
            "The abscissa should have been 0.",
            0,
            turtle.getX(),
            0.5
        );
        Assert.assertEquals(
            "The ordinate should have been 0.",
            0,
            turtle.getY(),
            0.5
        );
        Assert.assertEquals(
            "The color should have been black.",
            Color.BLACK,
            turtle.getColor()
        );
        Assert.assertEquals(
            "The speed should have been 30.",
            30.,
            turtle.getSpeed(),
            0.5
        );
        Assert.assertEquals(
            "The direction should have been -90.",
            -90.,
            turtle.getDirection(),
            0.5
        );
        Assert.assertEquals(
            "There should have been no observers.",
            0,
            turtle.getObservers().size()
        );
    }
    
    /**
     * Tests the initialization of a turtle with custom values.
     */
    @Test
    public void testCustomTurtle()
    {
        // Initialize vars
        AbstractTurtle turtle = new CircularTurtle(
            100.,
            200.,
            Color.RED,
            1.,
            1.,
            50.,
            1
        );
        
        // Perform tests
        Assert.assertEquals(
            "The abscissa should have been 0.",
            100.,
            turtle.getX(),
            0.5
        );
        Assert.assertEquals(
            "The ordinate should have been 0.",
            200.,
            turtle.getY(),
            0.5
        );
        Assert.assertEquals(
            "The color should have been black.",
            Color.RED,
            turtle.getColor()
        );
        Assert.assertEquals(
            "The speed should have been 30.",
            50.,
            turtle.getSpeed(),
            0.5
        );
        Assert.assertEquals(
            "The direction should have been -90.",
            -90.,
            turtle.getDirection(),
            0.5
        );
        Assert.assertEquals(
            "There should have been no observers.",
            0,
            turtle.getObservers().size()
        );
    }
    
    /**
     * Tests the different methods to modify a turtle's position.
     */
    @Test
    public void testPosition()
    {
        // Initialize vars
        AbstractTurtle turtle = new CircularTurtle();
        
        // Perform tests
        turtle.setX(50.);
        Assert.assertEquals(
            "The abscissa should have been 50.",
            50.,
            turtle.getX(),
            0.5
        );
        
        turtle.setY(60.);
        Assert.assertEquals(
            "The ordinate should have been 60.",
            60.,
            turtle.getY(),
            0.5
        );
        
        Point2D.Double position = turtle.getPosition();
        Assert.assertEquals(
            "The abscissa should have been 50.",
            50.,
            position.x,
            0.5
        );
        Assert.assertEquals(
            "The ordinate should have been 60.",
            60.,
            position.y,
            0.5
        );
        
        position.x = 100.;
        position.y = 150.;
        turtle.setPosition(position);
        Assert.assertEquals(
            "The abscissa should have been 100.",
            100.,
            turtle.getX(),
            0.5
        );
        Assert.assertEquals(
            "The ordinate should have been 150.",
            150.,
            turtle.getY(),
            0.5
        );
        
        
        turtle.setPosition(200., 250.);
        Assert.assertEquals(
            "The abscissa should have been 200.",
            200.,
            turtle.getX(),
            0.5
        );
        Assert.assertEquals(
            "The ordinate should have been 250.",
            250.,
            turtle.getY(),
            0.5
        );
        
        turtle.setDirection(180.);
        Assert.assertEquals(
            "The direction should have been 180.",
            180.,
            turtle.getDirection(),
            0.5
        );
        
        turtle.setSpeed(90.);
        Assert.assertEquals(
            "The speed should have been 90.",
            90.,
            turtle.getSpeed(),
            0.5
        );
    }
    
    /**
     * Tests the observers design pattern.
     */
    @Test
    public void testObservers()
    {
        // Initialize vars
        AbstractTurtle turtle = new CircularTurtle();
        TurtleObserverInterface observer = Mockito.mock(TurtleObserverInterface.class);
        
        // Perform tests
        turtle.addObserver(observer);
        Assert.assertEquals(
            "The observers' list's size should have been 1.",
            1,
            turtle.getObservers().size()
        );
        Assert.assertTrue(
            "The observer should have been in the turtle's observers' list.",
            turtle.getObservers().contains(observer)
        );
        
        turtle.removeObserver(observer);
        Assert.assertEquals(
            "The observers' list's size should have been 0.",
            0,
            turtle.getObservers().size()
        );
        Assert.assertFalse(
            "The observer should not have been in the turtle's observers' list.",
            turtle.getObservers().contains(observer)
        );
        
        turtle.addObserver(observer);
        turtle.removeObservers();
        Assert.assertEquals(
            "The observers' list's size should have been 0.",
            0,
            turtle.getObservers().size()
        );
        Assert.assertFalse(
            "The observer should not have been in the turtle's observers' list.",
            turtle.getObservers().contains(observer)
        );
        
        turtle.addObserver(observer);
        turtle.notifyObservers();
        Mockito.verify(observer, Mockito.times(1)).onTurtleUpdated(Mockito.any(AbstractTurtle.class));
    }
    
    /**
     * Tests a turtle's movements.
     */
    @Test
    public void testMovements()
    {
        // Initialize vars
        AbstractTurtle turtle = new CircularTurtle();
        Point2D.Double position;
        double direction;
        
        // Perform tests
        turtle.setDirection(45);
        position = turtle.getPosition();
        position.x += 50 * Math.cos(Math.toRadians(45));
        position.y += 50 * Math.sin(Math.toRadians(45));
        turtle.moveForward(50);
        Assert.assertEquals(
            String.format("The abscissa should have been %.2f.", position.x),
            position.x,
            turtle.getX(),
            0.5
        );
        Assert.assertEquals(
            String.format("The ordinate should have been %.2f.", position.y),
            position.y,
            turtle.getY(),
            0.5
        );
        
        direction = (turtle.getDirection() - 45) % 360;
        turtle.turnLeft(45);
        Assert.assertEquals(
            String.format("The direction should have been %.2f.", direction),
            direction,
            turtle.getDirection(),
            0.5
        );
        
        direction = (turtle.getDirection() + 90) % 360;
        turtle.turnRight(90);
        Assert.assertEquals(
            String.format("The direction should have been %.2f.", direction),
            direction,
            turtle.getDirection(),
            0.5
        );
    }
}
