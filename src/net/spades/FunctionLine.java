package net.spades;

/**
 * Created by stanislav on 17.07.16.
 */
public class FunctionLine {

    float A,B,C;

    public FunctionLine(Point2D p1, Point2D p2)
    {
        A = p1.y - p2.y;
        B = p2.x - p1.x;
        C = -(A*p1.x+B*p1.y);
    }

    public Point2D CrossPoint(FunctionLine f)
    {
        float x = -(C*f.B-f.C*B)/(A*f.B-f.A*B);
        float y = -(A*f.C-f.A*C)/(A*f.B-f.A*B);
        return new Point2D(x,y);
    }

}
