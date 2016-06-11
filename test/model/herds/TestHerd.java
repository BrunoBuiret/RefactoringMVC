package model.herds;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;
import java.util.Set;
import model.turtles.AbstractTurtle;
import model.turtles.CircularTurtle;
import model.turtles.SquareTurtle;
import model.turtles.TriangularTurtle;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public class TestHerd
{
    /**
     * Tests the initialization of herds with and without existing turtles.
     */
    @Test
    public void testInitialization()
    {
        // Initialize vars
        Herd emptyHerd = new Herd();
        
        Set<AbstractTurtle> turtles = new HashSet<>();
        turtles.add(new CircularTurtle(
            0, 0, Color.BLACK, 0, 0, 0, 10
        ));
        turtles.add(new TriangularTurtle(
            0, 0, Color.BLACK, 0, 0, 0, 25
        ));
        turtles.add(new SquareTurtle(
            0, 0, Color.BLACK, 0, 0, 0, 25
        ));
        Herd notEmptyHerd = new Herd(turtles);
        
        // Perform tests
        Assert.assertEquals(
            "There shouldn't have been any turtle in the herd.",
            0,
            emptyHerd.getTurtles().size()
        );
        Assert.assertEquals(
            "There should have been 3 turtles in the herd.",
            3,
            notEmptyHerd.getTurtles().size()
        );
    }
    
    /**
     * Tests a herd's manipulation methods
     */
    @Test
    public void testSetManipulation()
    {
        // Initialize vars
        Herd herd = new Herd();
        AbstractTurtle turtle1 = new CircularTurtle(
            0, 0, Color.BLACK, 0, 0, 0, 10
        );
        AbstractTurtle turtle2 = new TriangularTurtle(
            200., 200., Color.BLACK, 0, 0, 0, 25
        );
        AbstractTurtle turtle3 = new SquareTurtle(
            10., 10., Color.BLACK, 0, 0, 0, 25
        );
        Set<AbstractTurtle> set = new HashSet<>();
        
        // Perform tests
        // Herd.addTurtle()
        herd.addTurtle(turtle1);
        Assert.assertEquals(
            "There should have been 1 turtle in the herd.",
            1,
            herd.getTurtles().size()
        );
        Assert.assertTrue(
            "Turtle #1 isn't in the herd like it's supposed to.",
            herd.getTurtles().contains(turtle1)
        );
        
        // Herd.addTurtles()
        set.add(turtle2);
        set.add(turtle3);
        herd.addTurtles(set);
        Assert.assertEquals(
            "There should have been 3 turtles in the herd.",
            3,
            herd.getTurtles().size()
        );
        Assert.assertTrue(
            "Turtle #1 isn't in the herd like it's supposed to.",
            herd.getTurtles().contains(turtle1)
        );
        Assert.assertTrue(
            "Turtle #2 isn't in the herd like it's supposed to.",
            herd.getTurtles().contains(turtle2)
        );
        Assert.assertTrue(
            "Turtle #3 isn't in the herd like it's supposed to.",
            herd.getTurtles().contains(turtle3)
        );
        
        // Herd.removeTurtle()
        herd.removeTurtle(turtle3);
        Assert.assertEquals(
            "There should have been 2 turtles in the herd.",
            2,
            herd.getTurtles().size()
        );
        Assert.assertTrue(
            "Turtle #1 isn't in the herd like it's supposed to.",
            herd.getTurtles().contains(turtle1)
        );
        Assert.assertTrue(
            "Turtle #2 isn't in the herd like it's supposed to.",
            herd.getTurtles().contains(turtle2)
        );
        
        // Herd.removeTurtles()
        herd.removeTurtles();
        Assert.assertEquals(
            "There should not have been in the herd.",
            0,
            herd.getTurtles().size()
        );
    }
    
    /**
     * Tests a herd's extra methods.
     */
    public void testExtraMethods()
    {
        // Initialize vars
        Herd herd = new Herd();
        AbstractTurtle turtle1 = new CircularTurtle(
            0, 0, Color.BLACK, 0, 0, 0, 10
        );
        AbstractTurtle turtle2 = new SquareTurtle(
            10., 10., Color.BLACK, 0, 0, 0, 25
        );
        Set<AbstractTurtle> set;
        Rectangle2D.Double zone;
        
        // Two turtles in a zone
        zone = new Rectangle2D.Double(-50., -50., 100., 100.);
        set = herd.getTurtlesIn(zone, null);
        Assert.assertEquals(
            "There should have been 2 turtles in the zone.",
            2,
            set.size()
        );
        Assert.assertTrue(
            "Turtle #1 isn't in the herd like it's supposed to.",
            set.contains(turtle1)
        );
        Assert.assertTrue(
            "Turtle #2 isn't in the herd like it's supposed to.",
            set.contains(turtle2)
        );
        
        // Two turtles in a zone but with an exception
        set = herd.getTurtlesIn(zone, turtle1);
        Assert.assertEquals(
            "There should have been 1 turtles in the zone, apart from Turtle #2.",
            1,
            set.size()
        );
        Assert.assertTrue(
            "Turtle #2 isn't in the herd like it's supposed to.",
            set.contains(turtle2)
        );
        
        // Empty zone
        zone.x = zone.y = -100.;
        set = herd.getTurtlesIn(zone, turtle1);
        Assert.assertEquals(
            "There shouldn't have been any turtle in the herd.",
            0,
            set.size()
        );
    }
}
