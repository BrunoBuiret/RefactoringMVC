package model.turtles;

import java.awt.Color;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public class TestSquareTurtle
{
    /**
     * Tests the initialization of a circular turtle with the default values.
     */
    @Test
    public void testDefaultTurtle()
    {
        // Initialize vars
        SquareTurtle turtle = new SquareTurtle();
        
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
     * Tests the initialization of a circular turtle with custom values.
     */
    @Test
    public void testCustomTurtle()
    {
        // Initialize vars
        SquareTurtle turtle = new SquareTurtle(
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
            "The speed should have been 50.",
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
}
