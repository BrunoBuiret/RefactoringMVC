package model.turtles;

import java.awt.Color;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public class SquareTurtle extends PolygonalTurtle
{
    /**
     * Creates a new square turtle with the default values.
     */
    public SquareTurtle()
    {
        this(0, 0, Color.BLACK, 20., 70., 30., 10.);
    }
    
    /**
     * Creates a custom new square turtle.
     * 
     * @param x The turtle's abscissa.
     * @param y The turtle's ordinate.
     * @param color The turtle's color.
     * @param sightRadius The turtle's sight's radius.
     * @param sightAngle The turtle's sight's angle.
     * @param speed The turtle's speed.
     * @param edgeSize The size of the square's edge.
     */
    public SquareTurtle(double x, double y, Color color, double sightRadius, double sightAngle, double speed, double edgeSize)
    {
        super(x, y, color, sightRadius, sightAngle, speed, 4, Math.sqrt(2 * Math.pow(edgeSize / 2, 2)));
    }
}
