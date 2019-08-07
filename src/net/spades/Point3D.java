package net.spades;

/**
 * Created by Станислав Юшин on 17.06.16.
 */
public class Point3D {

    private float x;
    private float y;
    private float z;

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public Point3D() {
        x=0;y=0;z=0;
    }

    public Point3D(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    float dimensionOfPoint(Point3D S) {
        float s= (float) Math.sqrt((x-S.x)*(x-S.x)+
                (y-S.y)*(y-S.y)+(z-S.z)*(z-S.z));
     return s;
    }

    void move(float dx, float dy, float dz) {
        x+=dx;
        y+=dy;
        z+=dz;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null) {
            return false;
        }
        if (obj.getClass()!= Point3D.class) {
            return false;
        }
        Point3D point = (Point3D) obj;
        return x == point.x & y==point.y & z == point.z;
    }

    void moveForVector(Vector3D v) {
        move(v.getI(),v.getJ(),v.getK());
    }

    Point3D symmetryOfMiddleOfLine(Line3D l)
    {
        Vector3D v = new Vector3D(l.getX().x-x,l.getX().y-y,l.getX().z-z);
        Point3D p = l.getY().copy();
        p.moveForVector(v);
        return p;
    }

    void rotate0(float angle, Axises axis)
    {
        if (axis.equals(Axises.Z))
        {
            float nx = (float) (x*Math.cos(Math.toRadians(angle))-y*Math.sin(Math.toRadians(angle)));
            float ny = (float) (x*Math.sin(Math.toRadians(angle))+y*Math.cos(Math.toRadians(angle)));
            this.x = nx; this.y = ny;
        }
        if (axis.equals(Axises.Y))
        {
            float ny = (float) (y*Math.cos(Math.toRadians(angle))-z*Math.sin(Math.toRadians(angle)));
            float nz = (float) (y*Math.sin(Math.toRadians(angle))+z*Math.cos(Math.toRadians(angle)));
            this.y = ny; this.z = nz;
        }
        if (axis.equals(Axises.X))
        {
            float nx = (float) (x*Math.cos(Math.toRadians(angle))-z*Math.sin(Math.toRadians(angle)));
            float nz = (float) (x*Math.sin(Math.toRadians(angle))+z*Math.cos(Math.toRadians(angle)));
            this.x = nx; this.z = nz;}
    }

    void rotateD(Point3D o, float angle, Axises axis) {
        move(-o.x,-o.y,-o.z);
        rotate0(angle,axis);
        move(o.x,o.y,o.z);
    }

    Point3D copy() {
        return new Point3D(x, y, z);
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
