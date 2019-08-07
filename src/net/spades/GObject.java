package net.spades;

import java.util.ArrayList;

/**
 * Created by stanislav on 30.06.16.
 */
public class GObject {

    public ArrayList<Triangle3D> structure;
    public boolean isSelected;
    static int count;

    public GObject()
    {
        structure = new ArrayList<Triangle3D>();
    }

    public void addTriangle(Triangle3D triangle3D)
    {
        if (!isContent(triangle3D))
        {
            structure.add(triangle3D);}
    }

    public void removeTriangle(Triangle3D triangle3D)
    {
        for (int i = 0; i < structure.size(); i++)
        {
         if (structure.get(i).equals(triangle3D))
         {
             structure.remove(i);
         }
        }
    }

    public boolean isContent(Triangle3D triangle3D)
    {
        boolean isContent;
        isContent = false;
        if (structure.size()>0)
        {
            for (int i = 0; i < structure.size(); i++)
            {
                if (triangle3D.equals(structure.get(i)))
                {
                    isContent = true;
                }
            }
        }
            return isContent;
    }

    public void select(Line3D line3D)
    {
        for (int i = 0; i< structure.size(); i++)
        {
            if (structure.get(i).isCross(line3D))
            {isSelected = true;}
        }
    }

    public void group(GObject go)
    {
        for (int i = 0; i<go.structure.size(); i++)
        {
           addTriangle(go.structure.get(i));
        }
    }

    public ArrayList<GObject> deGroup()
    {
        ArrayList<GObject> list = new ArrayList<>();
        for (int i = 0; i< structure.size(); i++)
        {
            GObject obj = new GObject();
            obj.addTriangle(structure.get(i));
            list.add(obj);
        }
        return  list;
    }

    public void rotate(Point3D o, float alfX, float alfY, float alfZ)
    {
        for (int i = 0; i< structure.size(); i++)
        {
            structure.get(i).RotateD(o,alfX,Axises.X);
            structure.get(i).RotateD(o,alfY,Axises.Y);
            structure.get(i).RotateD(o,alfZ,Axises.Z);
        }
    }

    public void move (float dx, float dy, float dz)
    {
        for (int i = 0; i< structure.size(); i++)
        {
            structure.get(i).move(dx,dy,dz);
        }
    }
}
