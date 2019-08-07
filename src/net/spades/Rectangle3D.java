package net.spades;

/**
 * Created by ptmkpd on 22.06.16.
 */
public class Rectangle3D {
    private Triangle3D A;
    private Triangle3D B;
    private String name;

    public Triangle3D getA() {
        return A;
    }

    public Triangle3D getB() {
        return B;
    }

    public String getName() {
        return name;
    }

    public Rectangle3D(Point3D a90, Point3D b, Point3D c)
    {
        A = new Triangle3D(a90,b,c);
        Point3D as = a90.symmetryOfMiddleOfLine(new Line3D(b,c));
        Point3D b2 = b.copy();
        Point3D c2 = c.copy();
        B = new Triangle3D(as,b2,c2);
    }

    public Rectangle3D(Point3D a90, float size)
    {
        Point3D a91 = a90.copy();
        a91.move(size,0,0);
        Point3D a92 = a90.copy();
        a92.move(0,size,0);
        A = new Triangle3D(a90,a91,a92);

        Point3D as = a90.symmetryOfMiddleOfLine(new Line3D(a91,a92));
        Point3D b2 = a91.copy();
        Point3D c2 = a92.copy();
        B = new Triangle3D(as,b2,c2);
    }

    public Point3D getCenter() {
        Point3D x1 = A.getCenter();
        Point3D x2 = B.getCenter();
        Point3D x = new Point3D((x1.getX()+x2.getX())/2,(x1.getY()+x2.getY())/2,(x1.getZ()+x2.getZ())/2);
        return x;
    }

    public void rotateD(Point3D o, float angle, Axises axis) {
        A.RotateD(o,angle,axis);
        B.RotateD(o,angle,axis);
    }

    public void rotateOfCenter(float angle, Axises axsis)
    {
        rotateD(getCenter(),angle,axsis);
    }

    public void move(float dx, float dy, float dz)
    {
        A.move(dx,dy,dz);
        B.move(dx,dy,dz);
    }

    public GObject toGObject()
    {
        GObject GO = new GObject();
        GO.addTriangle(A);
        GO.addTriangle(B);
        return GO;
    }

}
