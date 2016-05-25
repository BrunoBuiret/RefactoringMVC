package model;

import java.awt.Color;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public class TriangularTurtle extends PolygonalTurtle
{
    /**
     * Creates a new squarte turtle with the default values.
     */
    public TriangularTurtle()
    {
        this(0, 0, Color.BLACK, 10.);
    }
    
    /**
     * Creates a custom new square turtle.
     * 
     * @param x The turtle's abscissa.
     * @param y The turtle's ordinate.
     * @param color The turtle's color.
     * @param edgeSize The size of the square's edge.
     */
    public TriangularTurtle(double x, double y, Color color, double edgeSize)
    {
        super(x, y, color, 3, Math.sqrt(Math.pow(edgeSize, 2) - Math.pow(edgeSize / 2, 2)) / 2);
    }
}
