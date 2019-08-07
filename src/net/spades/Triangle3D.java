package net.spades;

import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.Line2D;

/**
 * Created by ptmkpd on 17.06.16.
 */
public class Triangle3D {
    Point3D A;
    Point3D B;
    Point3D C;

    public Triangle3D(Point3D a,Point3D b, Point3D c)
    {
        A = a;
        B = b;
        C = c;
    }

    public  Point3D getCenter()
    {
        return new Point3D((A.x+B.x+C.x)/3,(A.y+B.y+C.y)/3,(A.z+B.z+C.z)/3);
    }

    public void Move(float dx, float dy, float dz)
    {
        A = A.move(dx,dy,dz);
        B = B.move(dx,dy,dz);
        C = C.move(dx,dy,dz);
    }

    public void RotateOfCenter(double a, String os)
    {
        Point3D center = getCenter();
        A.RotateD(center,a,os);
        B.RotateD(center,a,os);
        C.RotateD(center,a,os);
    }

    public void RotateD(Point3D o,double a, String os)
    {
        A.RotateD(o,a,os);
        B.RotateD(o,a,os);
        C.RotateD(o,a,os);
    }


    public GObject toGObject()
    {
        GObject GO = new GObject();
        GO.addTriangle(this);
        return GO;
    }

    public boolean isEquals (Triangle3D triangle3D) ///// ПЕРЕДЕЛАТЬ стороны вместо точек
    {
        if (A.isEquals(triangle3D.A)&B.isEquals(triangle3D.B)&C.isEquals(triangle3D.C)) return true;
        else if (A.isEquals(triangle3D.A)&B.isEquals(triangle3D.C)&C.isEquals(triangle3D.B)) return true;
        else if (A.isEquals(triangle3D.B)&B.isEquals(triangle3D.A)&C.isEquals(triangle3D.C)) return true;
        else if (A.isEquals(triangle3D.B)&B.isEquals(triangle3D.C)&C.isEquals(triangle3D.A)) return true;
        else if (A.isEquals(triangle3D.C)&B.isEquals(triangle3D.A)&C.isEquals(triangle3D.B)) return true;
        else if (A.isEquals(triangle3D.C)&B.isEquals(triangle3D.B)&C.isEquals(triangle3D.A)) return true;
        else return false;
    }

    public boolean isCross (Line3D line3D)
    {
        Plane3D plane = new Plane3D(A,B,C);
        if (plane.isCrossLine(line3D))
        {
            Point3D point3D = plane.CrossOfLine(line3D.getFunction());
            boolean tri = isContent(point3D);
            boolean lin = line3D.isContent(point3D);
            return tri & lin;
        }
        else return false;
    }

    public boolean isContent(Point3D p)
    {

            Triangle3D t1 = new Triangle3D(A,B,p);
            Triangle3D t2 = new Triangle3D(B,C,p);
            Triangle3D t3 = new Triangle3D(C,A,p);
            double S = t1.square()+t2.square()+t3.square();
            return S<=square()*1.0001;
    }

    public double square()
    {
        float p = (A.Dimension()+B.Dimension()+C.Dimension())/2;
        double S = Math.sqrt(p*(p-A.Dimension())*(p-B.Dimension())*(p-C.Dimension()));
        return S;
    }

    public Plane3D getPlane() //используется в lastFirst
    {
        Plane3D plane3D = new Plane3D(A.A,B.A,C.A);
        return plane3D;
    }
}
