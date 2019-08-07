package net.spades;


import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ptmkpd on 17.06.16.
 */
public class Triangle {

    Line2D a;
    Line2D b;
    Line2D c;


    public Triangle(Point2D A, Point2D B, Point2D C)
    {
        a = new Line2D(A,B);
        b = new Line2D(B,C);
        c = new Line2D(C,A);
    }

    public Triangle(Line2D a, Line2D b, Line2D c) {
        this.c = c;
        this.b = b;
        this.a = a;
    }

    public boolean isContent(Point2D p)
    {
        /* МЯГКОЕ НАХОЖДЕНИЕ точек ВНУТРИ ТРЕУГОЛЬНИКА */
      double p1 = (a.A.x-p.x)*(b.A.y-a.A.y)-(b.A.x-a.A.x)*(a.A.y-p.y);
      double p2 = (b.A.x-p.x)*(c.A.y-b.A.y)-(c.A.x-b.A.x)*(b.A.y-p.y);
      double p3 = (c.A.x-p.x)*(a.A.y-c.A.y)-(a.A.x-c.A.x)*(c.A.y-p.y);

        return (p1>=-0.02 & p2>=-0.02 & p3>=-0.02)||(p1<=0.02 & p2<=0.02 & p3<=0.02);
        //Точки на сторонах ВЫВОДЯТСЯ  (неравенство нестрогое)
    }

    public boolean isEquals(Triangle t)
    {
        if (a.isEquals(t.a)&b.isEquals(t.b)&c.isEquals(t.c)) return true;
        else if (a.isEquals(t.a)&b.isEquals(t.c)&c.isEquals(t.b)) return true;
        else if (a.isEquals(t.b)&b.isEquals(t.a)&c.isEquals(t.c)) return true;
        else if (a.isEquals(t.c)&b.isEquals(t.c)&c.isEquals(t.a)) return true;
        else if (a.isEquals(t.c)&b.isEquals(t.a)&c.isEquals(t.b)) return true;
        else if (a.isEquals(t.c)&b.isEquals(t.b)&c.isEquals(t.a)) return true;
        else return false;
    }

    public boolean isContentPointOfTriangle(Triangle t)
    {
        /*МЯГКОЕ ВЗАИМНОЕ (с учетом точек на линиях)*/
        boolean t1 = isContent(t.a.A);
        boolean t2 = isContent(t.b.A);
        boolean t3 = isContent(t.c.A);
        boolean rez1 = t1||t2||t3; //вхождение второго в первый
        boolean t12 = t.isContent(a.A);
        boolean t22 = t.isContent(b.A);
        boolean t32 = t.isContent(c.A);
        boolean rez12 = t12||t22||t32;//вхождение первого во второй
        return rez1||rez12;
    }

    public Point2D getCenter()
    {
       Point2D center = new Point2D((a.A.x+b.A.x+c.A.x)/3,(a.A.y+b.A.y+c.A.y)/3);
        return center;
    }

    public boolean isCross(Triangle t)
    {
        /*ТУПОЕ ПЕРЕСЕЧЕНИЕ Х без учета совпадения линий, исходящих линий, общих точек ЗВЕЗДА ДАВИДА*/
        boolean r1 = a.isCross(t.a);
        boolean r2 = a.isCross(t.b);
        boolean r3 = a.isCross(t.c);
        boolean r4 = b.isCross(t.a);
        boolean r5 = b.isCross(t.b);
        boolean r6 = b.isCross(t.c);
        boolean r7 = c.isCross(t.a);
        boolean r8 = c.isCross(t.b);
        boolean r9 = c.isCross(t.c);
        return r1||r2||r3||r4||r5||r6||r7||r8||r9;
    }

    public boolean isCrossOrSoftContent(Triangle t)
    {
        boolean b1 = isContentPointOfTriangle(t);
        boolean b2 = isCross(t);
        return  b1|b2;
    }

    public boolean isContentFullPointsOfTriangle(Triangle t)
        // Взаимное, мягкое, полное включение точек треугольника
    {
        boolean t1 = isContent(t.a.A);
        boolean t2 = isContent(t.b.A);
        boolean t3 = isContent(t.c.A);
        boolean rez1 = t1&t2&t3; //вхождение второго в первый
        boolean t12 = t.isContent(a.A);
        boolean t22 = t.isContent(b.A);
        boolean t32 = t.isContent(c.A);
        boolean rez12 = t12&t22&t32;//вхождение первого во второй
        return rez1||rez12;
    }

    public double perimeter()
    {
        return a.dimension()+b.dimension()+c.dimension();
    }

    public ArrayList<Point2D> hardCrossPoints(Triangle t)
    {
        /*НАХОДИТ ВСЕ ТОЧКИ "СТРОГОГО Х" ПЕРЕСЕЧЕНИЯ ТРЕУГОЛЬНИКОВ*/
        ArrayList<Point2D> pointsList = new ArrayList<>();
        if (a.isCross(t.a)) {pointsList.add(a.CrossPoint(t.a));}
        if (a.isCross(t.b)) {pointsList.add(a.CrossPoint(t.b));}
        if (a.isCross(t.c)) {pointsList.add(a.CrossPoint(t.c));}
        if (b.isCross(t.a)) {pointsList.add(b.CrossPoint(t.a));}
        if (b.isCross(t.b)) {pointsList.add(b.CrossPoint(t.b));}
        if (b.isCross(t.c)) {pointsList.add(b.CrossPoint(t.c));}
        if (c.isCross(t.a)) {pointsList.add(c.CrossPoint(t.a));}
        if (c.isCross(t.b)) {pointsList.add(c.CrossPoint(t.b));}
        if (c.isCross(t.c)) {pointsList.add(c.CrossPoint(t.c));}
        return pointsList;

    }
}
