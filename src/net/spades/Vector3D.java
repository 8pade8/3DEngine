package net.spades;

/**
 * Created by stanislav on 22.06.16.
 */
public class Vector3D {

    private float i;
    private float j;
    private float k;

    public float getI() {
        return i;
    }

    public float getJ() {
        return j;
    }

    public float getK() {
        return k;
    }

    public Vector3D(float i, float j, float k) {
        this.i = i;
        this.j = j;
        this.k = k;
    }

    public Vector3D(Point3D A, Point3D B) {
        this.i = B.getX()-A.getX();
        this.j = B.getY()-A.getY();
        this.k = B.getZ()-A.getZ();
    }

    public float length() {
        float x = (float) Math.sqrt(i*i+j*j+k*k);
        return x;
    }

    public float scalarMultiply(Vector3D v) {
        float x = i*v.i+j*v.j+k*v.k;
        return x;
    }

    public Vector3D multiply(Vector3D v) {
        return new Vector3D(j*v.k-k*v.j,
                k*v.i-i*v.k,
                i*v.j-j*v.i);
    }

    public Vector3D direction() {
        return new Vector3D(i/ length(),j/ length(),k/ length());
    }

    public Vector3D multiplyNum(float num)
    {
        return new Vector3D(i*num,j*num,k*num);
    }



}
