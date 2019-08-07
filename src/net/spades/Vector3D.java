package net.spades;

/**
 * Created by stanislav on 22.06.16.
 */
public class Vector3D {

    float i;
    float j;
    float k;

    public Vector3D(float i, float j, float k)
    {
        this.i = i;
        this.j = j;
        this.k = k;
    }

    public Vector3D(Point3D A, Point3D B)
    {
        this.i = B.x-A.x;
        this.j = B.y-A.y;
        this.k = B.z-A.z;
    }

    public float Dimension()
    {
        float x = (float) Math.sqrt(i*i+j*j+k*k);
        return x;
    }

    public double ScalarMultiply(Vector3D v)
    {
        double x = i*v.i+j*v.j+k*v.k;
        return x;
    }

    public Vector3D Multiply(Vector3D v)
    {
        return new Vector3D(j*v.k-k*v.j,
                k*v.i-i*v.k,
                i*v.j-j*v.i);
    }

    public Vector3D Direction()
    {
        return new Vector3D(i/Dimension(),j/Dimension(),k/Dimension());
    }

    public Vector3D MultiplyNum(float num)
    {
        return new Vector3D(i*num,j*num,k*num);
    }



}
