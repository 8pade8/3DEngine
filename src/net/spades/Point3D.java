package net.spades;

import javafx.scene.effect.Light;
import javafx.scene.transform.Rotate;

/**
 * Created by Станислав Юшин on 17.06.16.
 */
public class Point3D {

    float x;
    float y;
    float z;

    public Point3D() {
        x=0;y=0;z=0;
    }

    public Point3D(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float DimensionOfPoint(Point3D S)
    {
        float s= (float) Math.sqrt((x-S.x)*(x-S.x)+
                (y-S.y)*(y-S.y)+(z-S.z)*(z-S.z));
     return s;
    }

    public Point3D move(float dx, float dy, float dz)
    {
        return new Point3D(x+dx,y+dy,z+dz);
    }

    public boolean isEquals(Point3D point)
    {
        return x == point.x & y==point.y & z == point.z;
    }

    public Point3D PointAsVector(Vector3D v)
    {
        return new Point3D((v.i + x),(v.j + y),(v.k + z));
    }

    public Point3D SymmetryOfMiddleOfLine(Line3D l)
    {
        Vector3D v = new Vector3D(l.A.x-x,l.A.y-y,l.A.z-z);
        return l.B.PointAsVector(v);
    }

    public void Rotate0(double alf, String os)
    {
        if (os.equals("z"))
        {
            float nx = (float) (x*Math.cos(Math.toRadians(alf))-y*Math.sin(Math.toRadians(alf)));
            float ny = (float) (x*Math.sin(Math.toRadians(alf))+y*Math.cos(Math.toRadians(alf)));
            this.x = nx; this.y = ny;
        }
        if (os.equals("x"))
        {
            float ny = (float) (y*Math.cos(Math.toRadians(alf))-z*Math.sin(Math.toRadians(alf)));
            float nz = (float) (y*Math.sin(Math.toRadians(alf))+z*Math.cos(Math.toRadians(alf)));
            this.y = ny; this.z = nz;
        }
        if (os.equals("y"))
        {
            float nx = (float) (x*Math.cos(Math.toRadians(alf))-z*Math.sin(Math.toRadians(alf)));
            float nz = (float) (x*Math.sin(Math.toRadians(alf))+z*Math.cos(Math.toRadians(alf)));
            this.x = nx; this.z = nz;}
    }

    public void RotateD(Point3D o, double alf, String os)
    {
        Point3D XR = new Point3D(x-o.x,y-o.y,z-o.z);
        XR.Rotate0(alf,os);
        x = XR.move(o.x,o.y,o.z).x;
        y = XR.move(o.x,o.y,o.z).y;
        z =XR.move(o.x,o.y,o.z).z;
    }
}
