package net.spades;

/**
 * Created by stanislav on 02.07.16.
 */
public class FunctionLine3D {
    double x0;
    double m;
    double y0;
    double n;
    double z0;
    double p;

    public FunctionLine3D(Point3D p1, Point3D p2)
    {
        x0 = p1.x;
        m = p2.x-p1.x;
        y0 = p1.y;
        n = p2.y-p1.y;
        z0 = p1.z;
        p = p2.z-p1.z;
    }

    public double DimensionOfPoint(Point3D point)
    {
        Vector3D MM = new Vector3D(point,new Point3D(x0,y0,z0));
        Vector3D s = new Vector3D(m,n,p);
        double d = MM.Multiply(s).Dimension()/s.Dimension();
        return d;
    }

    public boolean isContent(Point3D p)
    {
        double t1 = (p.x-x0)/m;
        double t2 = (p.y-y0)/n;
        double t3 = (p.z-z0)/this.p;
        return t1==t2 & t2==t3;
    }
}
