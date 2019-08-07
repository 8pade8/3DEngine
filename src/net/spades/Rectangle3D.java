package net.spades;

import java.awt.*;

/**
 * Created by ptmkpd on 22.06.16.
 */
public class Rectangle3D {
    Triangle3D A;
    Triangle3D B;
    String name;

    public Rectangle3D(Point3D a90, Point3D b, Point3D c)
    {
        A = new Triangle3D(a90,b,c);
        Point3D as = a90.SymmetryOfMiddleOfLine(new Line3D(b,c));
        Point3D b2 = new Point3D(b.x,b.y,b.z);
        Point3D c2 = new Point3D(c.x,c.y,c.z);
        B = new Triangle3D(as,b2,c2);
    }

    public Rectangle3D(Point3D a90, double a, double b)
    {
        A = new Triangle3D(a90, a, b);
        Point3D as = a90.SymmetryOfMiddleOfLine(new Line3D(A.A.B,A.C.A));
        B = new Triangle3D(as, -a, -b);
    }

    public Rectangle3D(Point3D o,double a, String name)
    {
        this.name = name;
        A = new Triangle3D(o, a);
        A.name = name + "_1";
        Point3D o2 = new Point3D(o.x,o.y,o.z);
        B = new Triangle3D(o2,a);
        B.RotateD(o2,180,"z");
        B.Move(a,a,0);
        B.name = name + "_2";
    }
    public Rectangle3D(Point3D o,double a)
    {
        A = new Triangle3D(o, a);
        Point3D o2 = new Point3D(o.x,o.y,o.z);
        B = new Triangle3D(o2,a);
        B.RotateD(o2,180,"z");
        B.Move(a,a,0);

    }

    public Point3D getCenter()
    {
        Point3D x1 = A.getCenter();
        Point3D x2 = B.getCenter();
        Point3D x = new Point3D((x1.x+x2.x)/2,(x1.y+x2.y)/2,(x1.z+x2.z)/2);
        return x;
    }

    public void RotateD(Point3D o,double alf,String os)
    {
        A.RotateD(o,alf,os);
        B.RotateD(o,alf,os);
    }
    public void RotateOfCenter(double alf, String os)
    {
        RotateD(getCenter(),alf,os);
    }

    public void Move(double dx,double dy, double dz)
    {
        A.Move(dx,dy,dz);
        B.Move(dx,dy,dz);
    }

    public GObject toGObject()
    {
        GObject GO = new GObject();
        GO.addTriangle(A);
        GO.addTriangle(B);
        return GO;
    }

}
