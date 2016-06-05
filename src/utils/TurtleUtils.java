package utils;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;
import model.turtles.AbstractTurtle;
import model.turtles.CircularTurtle;
import model.turtles.PolygonalTurtle;
import model.turtles.SquareTurtle;
import model.turtles.TriangularTurtle;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public abstract class TurtleUtils
{
    /**
     * Creates a random turtle.
     * 
     * @param vivariumDimension The vivarium's dimension so as not to place it
     * out of it.
     * @return A turtle.
     */
    public static AbstractTurtle createRandomTurtle(Dimension vivariumDimension)
    {
        // Initialize vars
        Random randomizer = new Random();
        Color[] colors = new Color[]{
            Color.BLACK, Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN,
            Color.ORANGE, Color.PINK, Color.GRAY, Color.MAGENTA, Color.DARK_GRAY
        };
        AbstractTurtle turtle = null;
        
        switch(randomizer.nextInt(4))
        {
            case 0:
                // Create a polygonal turtle
                turtle = new PolygonalTurtle(
                    // Abscissa
                    vivariumDimension.width * randomizer.nextDouble(),
                    // Ordinate
                    vivariumDimension.height * randomizer.nextDouble(),
                    // Color
                    colors[randomizer.nextInt(colors.length)],
                    // Sight radius
                    100 + 40 * randomizer.nextDouble(),
                    // Sight angle
                    100 + 55 * randomizer.nextDouble(),
                    // Speed
                    20 + 50 * randomizer.nextDouble(),
                    // Edges number
                    5 + randomizer.nextInt(10),
                    // Radius
                    5 + 10 * randomizer.nextDouble()
                );
            break;

            case 1:
                // Create a square turtle
                turtle = new SquareTurtle(
                    // Abscissa
                    vivariumDimension.width * randomizer.nextDouble(),
                    // Ordinate
                    vivariumDimension.height * randomizer.nextDouble(),
                    // Color
                    colors[randomizer.nextInt(colors.length)],
                    // Sight radius
                    100 + 40 * randomizer.nextDouble(),
                    // Sight angle
                    100 + 55 * randomizer.nextDouble(),
                    // Speed
                    20 + 50 * randomizer.nextDouble(),
                    // Edge size
                    20 + randomizer.nextInt(12)
                );
            break;

            case 2:
                // Create a triangular turtle
                turtle = new TriangularTurtle(
                    // Abscissa
                    vivariumDimension.width * randomizer.nextDouble(),
                    // Ordinate
                    vivariumDimension.height * randomizer.nextDouble(),
                    // Color
                    colors[randomizer.nextInt(colors.length)],
                    // Sight radius
                    100 + 40 * randomizer.nextDouble(),
                    // Sight angle
                    100 + 55 * randomizer.nextDouble(),
                    // Speed
                    20 + 50 * randomizer.nextDouble(),
                    // Edge size
                    20 + randomizer.nextInt(12)
                );
            break;

            case 3:
                // Create a circular turtle
                turtle = new CircularTurtle(
                    // Abscissa
                    vivariumDimension.width * randomizer.nextDouble(),
                    // Ordinate
                    vivariumDimension.height * randomizer.nextDouble(),
                    // Color
                    colors[randomizer.nextInt(colors.length)],
                    // Sight radius
                    100 + 40 * randomizer.nextDouble(),
                    // Sight angle
                    100 + 55 * randomizer.nextDouble(),
                    // Speed
                    20 + 50 * randomizer.nextDouble(),
                    // Radius
                    10 + 20 * randomizer.nextDouble()
                );
            break;
        }
        
        return turtle;
    }
}
