package net.spades;

import java.util.Map;

/**
 * Created by stanislav on 22.06.16.
 */
public class Vector2D {
    private float i;
    private float j;

    Vector2D(float i, float j)
    {
        this.i = i;
        this.j = j;
    }

    public float getI() {
        return i;
    }

    public float getJ() {
        return j;
    }

    Vector2D(Point2D A, Point2D B) {
        this.i = B.getX()-A.getX();
        this.j = B.getY()-A.getY();
    }

    float Dimension() {
        return (float) Math.sqrt(i*i+j*j);
    }

    float ScalarMultiply(Vector2D v)
    {
        return i*v.i+j*v.j;
    }

    float cosAngle(Vector2D v) {
        return ScalarMultiply(v)/(Dimension()*v.Dimension());
    }

    Vector2D addVector(Vector2D v)
    {
        return new Vector2D(i+v.i,j+v.j);
    }

    float Multiply(Vector2D v)
    {
        return i*v.j-j*v.i;
    }

    Vector2D Multiply(float d)
    {
        return new Vector2D(i*d,j*d);
    }
}
