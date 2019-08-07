package net.spades;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ptmkpd on 17.06.16.
 */
public class Cube3D {

    Rectangle3D A;
    Rectangle3D B;
    Rectangle3D C;
    Rectangle3D D;
    Rectangle3D E;
    Rectangle3D F;

    public Cube3D(Point3D o, double a, double b) {
        A = new Rectangle3D(o, a, b);
        Point3D o2 = new Point3D(o.x,o.y,o.z);
        B = new Rectangle3D(o2, a, b);
        B.RotateD(o2,-90,"y");
        Point3D o3 = o.move(a,0,0);
        C = new Rectangle3D(o3, a, b);
        C.RotateD(o3,-90,"y");
        Point3D o4 = o.move(0,0,-a);
        D = new Rectangle3D(o4, a, b);
        Point3D o5 = o.move(0,0,0);
        E = new Rectangle3D(o5,a);
        E.RotateD(o5,-90,"x");
        Point3D o6 = o.move(0,0,0);
        F = new Rectangle3D(o6,a);
        F.RotateD(o6,-90,"x");
        F.Move(0,b,0);

    }
    public Cube3D(Point3D o, double a) {
        A = new Rectangle3D(o, a, a);
        Point3D o2 = new Point3D(o.x,o.y,o.z);
        B = new Rectangle3D(o2, a, a);
        B.RotateD(o2,90,"y");
        Point3D o3 = o.move(a,0,0);
        C = new Rectangle3D(o3, a, a);
        C.RotateD(o3,90,"y");
        Point3D o4 = o.move(0,0,-a);
        D = new Rectangle3D(o4, a, a);
        Point3D o5 = o.move(0,0,0);
        E = new Rectangle3D(o5,a);
        E.RotateD(o5,-90,"x");
        Point3D o6 = o.move(0,0,0);
        F = new Rectangle3D(o6,a);
        F.RotateD(o6,-90,"x");
        F.Move(0,a,0);
    }


    public void RotateD (Point3D o, double alf, String os)
    {
        A.RotateD(o,alf,os);
        B.RotateD(o,alf,os);
        C.RotateD(o,alf,os);
        D.RotateD(o,alf,os);
        E.RotateD(o,alf,os);
        F.RotateD(o,alf,os);
    }
    public void RotateOfCenter (double alf, String os)
    {
        Point3D center = getCenter();

        A.RotateD(center,alf,os);
        B.RotateD(center,alf,os);
        C.RotateD(center,alf,os);
        D.RotateD(center,alf,os);
        E.RotateD(center,alf,os);
        F.RotateD(center,alf,os);
    }

    public void  Move(double dx, double dy, double dz)
    {
        A.Move(dx,dy,dz);
        B.Move(dx,dy,dz);
        C.Move(dx,dy,dz);
        D.Move(dx,dy,dz);
        E.Move(dx,dy,dz);
        F.Move(dx,dy,dz);
    }

    public Point3D getCenter()
    {

        Point3D ACenter = A.getCenter();
        Point3D BCenter = B.getCenter();
        Point3D CCenter = C.getCenter();
        Point3D DCenter = D.getCenter();
        Point3D ECenter = E.getCenter();
        Point3D FCenter = F.getCenter();

        Point3D Center = new Point3D((ACenter.x+BCenter.x+CCenter.x+DCenter.x+ECenter.x+FCenter.x)/6,
        (ACenter.y+BCenter.y+CCenter.y+DCenter.y+ECenter.y+FCenter.y)/6,
                (ACenter.z+BCenter.z+CCenter.z+DCenter.z+ECenter.z+FCenter.z)/6);

        return Center;
    }

    public GObject toGObject()
    {
        GObject GOA = A.toGObject();
        GObject GOB = B.toGObject();
        GObject GOC = C.toGObject();
        GObject GOD = D.toGObject();
        GObject GOE = E.toGObject();
        GObject GOF = F.toGObject();
        GOA.Group(GOB);
        GOA.Group(GOC);
        GOA.Group(GOD);
        GOA.Group(GOE);
        GOA.Group(GOF);
        return GOA;
    }
}
