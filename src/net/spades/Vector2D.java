package net.spades;

import java.util.Map;

/**
 * Created by stanislav on 22.06.16.
 */
public class Vector2D {
    double i;
    double j;

    public Vector2D(double i, double j)
    {
        this.i = i;
        this.j = j;
    }

    public Vector2D(Point2D A, Point2D B) {
        this.i = B.x-A.x;
        this.j = B.y-A.y;
    }

    public double Dimension()
    {   double x = Math.sqrt(i*i+j*j);
        return x;
    }

    public double ScalarMultiply(Vector2D v)
    {
        double x = i*v.i+j*v.j;
        return x;
    }

    public Double cosAngle(Vector2D v) {
        return ScalarMultiply(v)/(Dimension()*v.Dimension());
    }

    public Vector2D addVector(Vector2D v)
    {
        return new Vector2D(i+v.i,j+v.j);
    }

    public double Multiply(Vector2D v)
    {
        return i*v.j-j*v.i;
    }

    public Vector2D Multiply(double d)
    {
        return new Vector2D(i*d,j*d);
    }
}
