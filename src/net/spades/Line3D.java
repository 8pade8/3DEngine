package net.spades;

/**
 * Created by ptmkpd on 17.06.16.
 */
public class Line3D {
    private Point3D X;
    private Point3D Y;

    private float x0;
    private float m;
    private float y0;
    private float n;
    private float z0;
    private float p;

    public Line3D(Point3D p1, Point3D p2) {
        X = p1;
        Y = p2;

        x0 = p1.getX();
        m = p2.getX()-p1.getX();
        y0 = p1.getY();
        n = p2.getY()-p1.getY();
        z0 = p1.getZ();
        p = p2.getZ()-p1.getZ();
    }

    public float getX0() {
        return x0;
    }

    public float getM() {
        return m;
    }

    public float getY0() {
        return y0;
    }

    public float getN() {
        return n;
    }

    public float getZ0() {
        return z0;
    }

    public float getP() {
        return p;
    }

    public Point3D getX() {
        return X;
    }

    public Point3D getY() {
        return Y;
    }

    public float length() {
        return  X.dimensionOfPoint(Y);
    }

//    public Line2D TranslateTo2D(Point3D F, Point3D O) че кого??
//    {
////        return new Line2D(X.TranslateTo2DonXYforF(F,O),Y.TranslateTo2DonXYforF(F,O));
//    }

    public void move(float dx, float dy, float dz) {
        X.move(dx,dy,dz);
        Y.move(dx,dy,dz);
    }

    public void rotate0(float angle, Axises axis) {
        X.rotate0(angle,axis);
        Y.rotate0(angle,axis);
    }

    public void rotateD(Point3D o, float angle, Axises axis) {
        X.rotateD(o,angle,axis);
        Y.rotateD(o,angle,axis);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null) {
            return false;
        }
        if (obj.getClass()!= Line3D.class) {
            return false;
        }
        Line3D line = (Line3D) obj;
        return line.X.equals(X) & line.Y.equals(Y) || line.X.equals(Y) & line.Y.equals(X);    }

    public double dimensionOfPoint(Point3D point)
    {
        Vector3D MM = new Vector3D(point,new Point3D(x0,y0,z0));
        Vector3D s = new Vector3D(m,n,p);
        double d = MM.multiply(s).length()/s.length();
        return d;
    }

    public boolean isContent(Point3D point3D) {
        float t1 = (point3D.getX()-x0)/m;
        float t2 = (point3D.getY()-y0)/n;
        float t3 = (point3D.getZ()-z0)/p;
        return t1==t2 & t2==t3;
    }
}
