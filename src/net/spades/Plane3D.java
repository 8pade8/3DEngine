package net.spades;

/**
 * Created by stanislav on 02.07.16.
 */
public class Plane3D {
    float A;
    float B;
    float C;
    float D;

    public Plane3D(float a, float b, float c, float d) {
        A = a;
        B = b;
        C = c;
        D = d;
    }

    public Plane3D(Vector3D vector, Point3D point) {
        A = vector.getI();
        B = vector.getJ();
        C = vector.getK();
        D = -vector.getI()*point.getX()-vector.getJ()*point.getY()-vector.getK()*point.getZ();
    }

    public Plane3D(Point3D p1, Point3D p2, Point3D p3)
    {
        Vector3D v1 = new Vector3D(p1,p2);
        Vector3D v2 = new Vector3D(p1,p3);
        Vector3D n = v1.multiply(v2);
        A = n.getI();
        B = n.getJ();
        C = n.getK();
        D = -n.getI()*p1.getX()-n.getJ()*p1.getY()-n.getK()*p1.getZ();
    }

    public Point3D crossOfLine(Line3D line) {
        float t = -(D+A*line.getX0()+B*line.getY0()+C*line.getZ0())/(A*line.getM()+B*line.getN()+C*line.getP());
        float x = line.getX0()+line.getM()*t;
        float y = line.getY0()+line.getN()*t;
        float z = line.getZ0()+line.getP()*t;
        return new Point3D(x,y,z);
    }

    public boolean isCrossLine(Line3D line3D) {
        Vector3D v1 = new Vector3D(line3D.getM(),line3D.getN(),line3D.getP());
        Vector3D v2 = new Vector3D(A,B,C);
        return v1.scalarMultiply(v2)!=0;
    }

    public boolean isContent(Point3D p) {
        return A*p.getX()+B*p.getY()+C*p.getZ()+D<0.01 & A*p.getX()+B*p.getY()+C*p.getZ()+D>-0.01;
    }

    public boolean isContent(Line3D l) {
        //используется в lastFirst
        return isContent(l.getX())&isContent(l.getY());
    }

    public float dimensionOfPoint(Point3D M) {
        return (float) (Math.abs(A*M.getX()+B*M.getY()+C*M.getZ()+D)/Math.sqrt(A*A+B*B+C*C));
    }

    public float dimensionOfPlane(Plane3D P)
    {
        return (float) ((Math.abs(P.D-D))/(Math.sqrt(A*A+B*B+C*C)));
    }

    public boolean isPerpendicular(Plane3D P) //используется в lastFirst
    {
        Vector3D p1 = new Vector3D(A,B,C);
        Vector3D p2 = new Vector3D(P.A,P.B,P.C);
        double rez = p1.scalarMultiply(p2);
        return rez<0.01 & rez>-0.01;
    }
}
