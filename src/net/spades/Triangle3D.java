package net.spades;

/**
 * Created by ptmkpd on 17.06.16.
 */
public class Triangle3D {
    private Point3D A;
    private Point3D B;
    private Point3D C;


    public Point3D getA() {
        return A;
    }

    public Point3D getB() {
        return B;
    }

    public Point3D getC() {
        return C;
    }

    public Triangle3D(Point3D a, Point3D b, Point3D c) {
        A = a;
        B = b;
        C = c;
    }

    public  Point3D getCenter() {
        return new Point3D((A.getX()+B.getX()+C.getX())/3,(A.getY()+B.getY()+C.getY())/3,(A.getZ()+B.getZ()+C.getZ())/3);
    }

    public void move(float dx, float dy, float dz) {
        A.move(dx,dy,dz);
        B.move(dx,dy,dz);
        C.move(dx,dy,dz);
    }

    public void rotateOfCenter(float angle, Axises axis)
    {
        Point3D center = getCenter();
        A.rotateD(center,angle,axis);
        B.rotateD(center,angle,axis);
        C.rotateD(center,angle,axis);
    }

    void RotateD(Point3D o, float angle, Axises axis)
    {
        A.rotateD(o,angle,axis);
        B.rotateD(o,angle,axis);
        C.rotateD(o,angle,axis);
    }


    public GObject toGObject()  //TO DO WTF??
    {
        GObject GO = new GObject();
        GO.addTriangle(this);
        return GO;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null) {
            return false;
        }
        if (obj.getClass()!= Triangle.class) {
            return false;
        }
        Triangle3D triangle3D = (Triangle3D) obj;

        Line3D K = new Line3D(A, B);
        Line3D L = new Line3D(B, C);
        Line3D M = new Line3D(C, A);
        Line3D K2 = new Line3D(triangle3D.A, triangle3D.B);
        Line3D L2 = new Line3D(triangle3D.B, triangle3D.C);
        Line3D M2 = new Line3D(triangle3D.C, triangle3D.A);
        return K.equals(K2)&&L.equals(L2)&&M.equals(M2)||
                K.equals(K2)&&L.equals(M2)&&M.equals(L2)||
                K.equals(L2)&&L.equals(K2)&&M.equals(M2)||
                K.equals(L2)&&L.equals(M2)&&M.equals(K2)||
                K.equals(M2)&&L.equals(L2)&&M.equals(K2)||
                K.equals(M2)&&L.equals(K2)&&M.equals(L2);
    }

    public boolean isCross (Line3D line3D)
    {
        Plane3D plane = new Plane3D(A,B,C);
        if (plane.isCrossLine(line3D))
        {
            Point3D point3D = plane.crossOfLine(line3D);
            boolean tri = isContent(point3D);
            boolean lin = line3D.isContent(point3D);
            return tri & lin;
        }
        else return false;
    }

    boolean isContent(Point3D p) {
            Triangle3D t1 = new Triangle3D(A,B,p);
            Triangle3D t2 = new Triangle3D(B,C,p);
            Triangle3D t3 = new Triangle3D(C,A,p);
            double S = t1.square()+t2.square()+t3.square();
            return S<=square()*1.0001;
    }

    private double square() {
        Line3D K = new Line3D(A, B);
        Line3D L = new Line3D(B, C);
        Line3D M = new Line3D(C, A);
        float p = (K.length()+L.length()+M.length())/2;
        float S = (float) Math.sqrt(p*(p-K.length())*(p-L.length())*(p-M.length()));
        return S;
    }

    public Plane3D getPlane() {//используется в lastFirst
        Plane3D plane3D = new Plane3D(A,B,C);
        return plane3D;
    }
}
