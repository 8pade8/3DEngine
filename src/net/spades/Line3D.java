package net.spades;

/**
 * Created by ptmkpd on 17.06.16.
 */
public class Line3D {
    Point3D A;
    Point3D B;

    public  Line3D(Point3D A, Point3D B)
    {
        this.A = A;
        this.B = B;
    }

    public double Dimension()
    {
        return  A.DimensionOfPoint(B);
    }

    public Line2D TranslateTo2D(Point3D F, Point3D O)
    {
        return new Line2D(this.A.TranslateTo2DonXYforF(F,O),this.B.TranslateTo2DonXYforF(F,O));
    }

    public void Move(double dx, double dy, double dz)
    {
        A = A.move(dx,dy,dz);
        B = B.move(dx,dy,dz);
    }

    public void Rotate0(double a, String os)
    {
        A.Rotate0(a,os);
        B.Rotate0(a,os);
    }

    public void RotateD(Point3D o,double a, String os)
    {
        A.RotateD(o,a,os);
        B.RotateD(o,a,os);
    }

    public boolean isEquals(Line3D line)
    {
        return line.A.isEquals(A) & line.B.isEquals(B) || line.A.isEquals(B) & line.B.isEquals(A);
    }

    public FunctionLine3D getFunction()
    {
        return new FunctionLine3D(A,B);
    }

    public boolean isContent(Point3D p)
    {
        if (getFunction().isContent(p))
        {
            Line3D l1 = new Line3D(p,A);
            Line3D l2 = new Line3D(p,B);
            double s = l1.Dimension()+l2.Dimension();
            return s<=Dimension();
        }
        else return false;
    }
}
