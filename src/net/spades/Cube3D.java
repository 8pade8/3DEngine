package net.spades;

/**
 * Created by ptmkpd on 17.06.16.
 */
public class Cube3D {

    private Rectangle3D A;
    private Rectangle3D B;
    private Rectangle3D C;
    private Rectangle3D D;
    private Rectangle3D E;
    private Rectangle3D F;

    public Cube3D(Point3D p, float a) {
        A = new Rectangle3D(p.copy(),50);
        B = new Rectangle3D(p.copy(),50);
        C = new Rectangle3D(p.copy(),50);
        D = new Rectangle3D(p.copy(),50);
        E = new Rectangle3D(p.copy(),50);
        F = new Rectangle3D(p.copy(),50);
        C.rotateD(p,90,Axises.X);
        E.rotateD(p,90,Axises.X);
        E.move(50,0,0);
        D.rotateD(p,90,Axises.Y);
        F.rotateD(p,90,Axises.Y);
        F.move(0,50,0);
        B.move(0,0,50);
    }

    public void rotateD(Point3D o, float angle, Axises axis) {
        A.rotateD(o,angle,axis);
        B.rotateD(o,angle,axis);
        C.rotateD(o,angle,axis);
        D.rotateD(o,angle,axis);
        E.rotateD(o,angle,axis);
        F.rotateD(o,angle,axis);
    }
    public void rotateOfCenter(float angle, Axises axis) {
        Point3D center = getCenter();
        A.rotateD(center,angle,axis);
        B.rotateD(center,angle,axis);
        C.rotateD(center,angle,axis);
        D.rotateD(center,angle,axis);
        E.rotateD(center,angle,axis);
        F.rotateD(center,angle,axis);
    }

    public void move(float dx, float dy, float dz) {
        A.move(dx,dy,dz);
        B.move(dx,dy,dz);
        C.move(dx,dy,dz);
        D.move(dx,dy,dz);
        E.move(dx,dy,dz);
        F.move(dx,dy,dz);
    }

    public Point3D getCenter() {

        Point3D ACenter = A.getCenter();
        Point3D BCenter = B.getCenter();
        Point3D CCenter = C.getCenter();
        Point3D DCenter = D.getCenter();
        Point3D ECenter = E.getCenter();
        Point3D FCenter = F.getCenter();

        Point3D Center = new Point3D((ACenter.getX()+BCenter.getX()+CCenter.getX()+DCenter.getX()+ECenter.getX()+FCenter.getX())/6,
        (ACenter.getY()+BCenter.getY()+CCenter.getY()+DCenter.getY()+ECenter.getY()+FCenter.getY())/6,
                (ACenter.getZ()+BCenter.getZ()+CCenter.getZ()+DCenter.getZ()+ECenter.getZ()+FCenter.getZ())/6);

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
        GOA.group(GOB);
        GOA.group(GOC);
        GOA.group(GOD);
        GOA.group(GOE);
        GOA.group(GOF);
        return GOA;
    }
}
