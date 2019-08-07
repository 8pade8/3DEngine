package net.spades;

import java.awt.*;

/**
 * Created by Станислав Юшин on 17.06.16.
 */
public class Line2D {

    private Point2D X;
    private Point2D Y;

    private float A,B,C;

    public Point2D getX() {
        return X;
    }

    public Point2D getY() {
        return Y;
    }

     Line2D(Point2D x, Point2D y) {
        X = x;
        Y = y;
        A = x.getY() - y.getY();
        B = y.getX() - x.getX();
        C = -(A*x.getX()+B*x.getY());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != Line2D.class) {
            return false;
        }
        Line2D line2D = (Line2D) obj;
        return X.equals(line2D.X) & Y.equals(line2D.Y) || X.equals(line2D.Y) & Y.equals(line2D.X);
    }

     float length(){
        return (float) Math.sqrt((X.getX()-Y.getX())*(X.getX()-Y.getX())+(X.getY()-Y.getY())*(X.getY()-Y.getY()));
    }

     boolean isCross(Line2D l){ //используется в lastFirst

        /* ТУПОЕ ПЕРЕСЕЧЕНИЕ Х */
        Vector2D p3p4 = new Vector2D(l.X,l.Y);
        Vector2D p3p1 = new Vector2D(l.X,X);
        Vector2D p3p2 = new Vector2D(l.X,Y);
        Vector2D p1p2 = new Vector2D(X,Y);
        Vector2D p1p3 = new Vector2D(X,l.X);
        Vector2D p1p4 = new Vector2D(X,l.Y);
        float v1 = p3p4.Multiply(p3p1);
        float v2 = p3p4.Multiply(p3p2);
        float v3 = p1p2.Multiply(p1p3);
        float v4 = p1p2.Multiply(p1p4);
        return (v1*v2<-0.01) & (v3*v4<-0.01); //не выводит совпадающие линии (строгое неравенство)
        //не выводит линии из одной точки, не выводит линии исходящие из середины другой линии
    }

     Point2D CrossPoint(Line2D f)
    {
        float x = -(C*f.B-f.C*B)/(A*f.B-f.A*B);
        float y = -(A*f.C-f.A*C)/(A*f.B-f.A*B);
        return new Point2D(x,y);
    }


}
