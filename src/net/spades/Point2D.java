package net.spades;

/**
 * Created by ptmkpd on 17.06.16.
 */
public class Point2D {
    float x, y;

    public Point2D(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public boolean isEquals(Point2D p)
    {
        return x == p.x & y == p.y;
    }
}