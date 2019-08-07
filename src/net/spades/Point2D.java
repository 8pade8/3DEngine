package net.spades;

/**
 * Created by ptmkpd on 17.06.16.
 */
public class Point2D {
    private float x, y;

    Point2D(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != Point2D.class) {
            return false;
        }
        Point2D p = (Point2D) obj;
        return x == p.x & y == p.y;
    }
}