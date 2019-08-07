package net.spades;

/**
 * Created by stanislav on 02.07.16.
 */
public class Plane3D {
    double A;
    double B;
    double C;
    double D;

    public Plane3D(double a, double b, double c, double d)
    {
        A = a;
        B = b;
        C = c;
        D = d;
    }

    public Plane3D(Vector3D vector, Point3D point)
    {
        A = vector.i;
        B = vector.j;
        C = vector.k;
        D = -vector.i*point.x-vector.j*point.y-vector.k*point.z;
    }

    public Plane3D(Point3D p1, Point3D p2, Point3D p3)
    {
        Vector3D v1 = new Vector3D(p1,p2);
        Vector3D v2 = new Vector3D(p1,p3);
        Vector3D n = v1.Multiply(v2);
        A = n.i;
        B = n.j;
        C = n.k;
        D = -n.i*p1.x-n.j*p1.y-n.k*p1.z;
    }

    public Point3D CrossOfLine(FunctionLine3D line)
    {
        double t = -(D+A*line.x0+B*line.y0+C*line.z0)/(A*line.m+B*line.n+C*line.p);
        double x = line.x0+line.m*t;
        double y = line.y0+line.n*t;
        double z = line.z0+line.p*t;
        return new Point3D(x,y,z);
    }

    public boolean isCrossLine(Line3D line3D)
    {
        FunctionLine3D line = line3D.getFunction();
        Vector3D v1 = new Vector3D(line.m,line.n,line.p);
        Vector3D v2 = new Vector3D(A,B,C);
        return v1.ScalarMultiply(v2)!=0;
    }

    public boolean isContent(Point3D p)
    {
        return A*p.x+B*p.y+C*p.z+D<0.01 & A*p.x+B*p.y+C*p.z+D>-0.01;
    }

    public boolean isContent(Line3D l) //используется в lastFirst
    {
        return isContent(l.A)&isContent(l.B);
    }

    public double DimensionOfPoint(Point3D M)
    {
        return Math.abs(A*M.x+B*M.y+C*M.z+D)/Math.sqrt(A*A+B*B+C*C);
    }

    public double DimensionOfPlane(Plane3D P)
    {
        return (Math.abs(P.D-D))/(Math.sqrt(A*A+B*B+C*C));
    }

    public boolean isPerpendicular(Plane3D P) //используется в lastFirst
    {
        Vector3D p1 = new Vector3D(A,B,C);
        Vector3D p2 = new Vector3D(P.A,P.B,P.C);
        double rez = p1.ScalarMultiply(p2);
        return rez<0.01 & rez>-0.01;
    }
}
