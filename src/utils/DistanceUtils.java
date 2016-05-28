package utils;

import java.awt.geom.Point2D;

/**
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 */
public abstract class DistanceUtils
{
    /**
     * Computes the distance between two 2D-points.
     * 
     * @param x1 The first point's abscissa.
     * @param y1 The first point's ordinate.
     * @param x2 The second point's abscissa.
     * @param y2 The second point's ordinate.
     * @return The distance between the two points.
     */
    public static double computeDistance(double x1, double y1, double x2, double y2)
    {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
    
    /**
     * Computes the distance between two 2D-points.
     *
     * @param p1 The first point.
     * @param p2 The second point.
     * @return The distance between the two points.
     */
    public static double computeDistance(Point2D.Double p1, Point2D.Double p2)
    {
        return DistanceUtils.computeDistance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }
}
