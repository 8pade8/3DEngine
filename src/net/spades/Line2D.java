package net.spades;

import java.awt.*;

/**
 * Created by Станислав Юшин on 17.06.16.
 */
public class Line2D {

    Point2D A;
    Point2D B;

    public Line2D(Point2D a, Point2D b)
    {
        B = b;
        A = a;
    }

    public boolean isEquals(Line2D line2D)
    {
        return A.isEquals(line2D.A) & B.isEquals(line2D.B) || A.isEquals(line2D.B) & B.isEquals(line2D.A);
    }

    public double dimension()
    {
        return Math.sqrt((A.x-B.x)*(A.x-B.x)+(A.y-B.y)*(A.y-B.y));
    }

    public boolean isCross(Line2D l) //используется в lastFirst
    {
        /* ТУПОЕ ПЕРЕСЕЧЕНИЕ Х */
        Vector2D p3p4 = new Vector2D(l.A,l.B);
        Vector2D p3p1 = new Vector2D(l.A,A);
        Vector2D p3p2 = new Vector2D(l.A,B);
        Vector2D p1p2 = new Vector2D(A,B);
        Vector2D p1p3 = new Vector2D(A,l.A);
        Vector2D p1p4 = new Vector2D(A,l.B);
        double v1 = p3p4.Multiply(p3p1);
        double v2 = p3p4.Multiply(p3p2);
        double v3 = p1p2.Multiply(p1p3);
        double v4 = p1p2.Multiply(p1p4);
        return (v1*v2<-0.01) & (v3*v4<-0.01); //не выводит совпадающие линии (строгое неравенство)
        //не выводит линии из одной точки, не выводит линии исходящие из середины другой линии
    }

    public Point2D CrossPoint(Line2D l)
    {
        FunctionLine f1 = getFunction();
        FunctionLine f2 = l.getFunction();
        return f1.CrossPoint(f2);
    }

    public FunctionLine getFunction()
    {
        return new FunctionLine(A,B);
    }
}
