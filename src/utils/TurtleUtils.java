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
     * 
     * @param vivariumDimension
     * @return 
     */
    public static AbstractTurtle createRandomTurtle(Dimension vivariumDimension)
    {
        // Initialize vars
        Random randomizer = new Random();
        Color[] colors = new Color[]{
            Color.BLACK, Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN,
            Color.ORANGE, Color.PINK, Color.GRAY, Color.MAGENTA
        };
        AbstractTurtle turtle = null;
        
        switch(randomizer.nextInt(4))
        {
            case 0:
                // Create a polygonal turtle
                turtle = new PolygonalTurtle(
                    vivariumDimension.width * randomizer.nextDouble(),
                    vivariumDimension.height * randomizer.nextDouble(),
                    colors[randomizer.nextInt(colors.length)],
                    5 + randomizer.nextInt(10),
                    5 + randomizer.nextDouble() * 10
                );
            break;

            case 1:
                // Create a square turtle
                turtle = new SquareTurtle(
                    vivariumDimension.width * randomizer.nextDouble(),
                    vivariumDimension.height * randomizer.nextDouble(),
                    colors[randomizer.nextInt(colors.length)],
                    3 + randomizer.nextInt(12)
                );
            break;

            case 2:
                // Create a triangular turtle
                turtle = new TriangularTurtle(
                    vivariumDimension.width * randomizer.nextDouble(),
                    vivariumDimension.height * randomizer.nextDouble(),
                    colors[randomizer.nextInt(colors.length)],
                    6 + randomizer.nextInt(12)
                );
            break;

            case 3:
                // Create a circular turtle
                turtle = new CircularTurtle(
                    vivariumDimension.width * randomizer.nextDouble(),
                    vivariumDimension.height * randomizer.nextDouble(),
                    colors[randomizer.nextInt(colors.length)],
                    3 + 20 * randomizer.nextDouble()
                );
            break;
        }
        
        return turtle;
    }
}
