package net.spades;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by stanislav on 30.06.16.
 */
public class GObject {

    public ArrayList<Triangle3D> Structure;
    public boolean isSelected;
    static int count;

    public GObject()
    {
        Structure = new ArrayList<Triangle3D>();
    }

    public void addTriangle(Triangle3D triangle3D)
    {
        if (!isContent(triangle3D))
        {Structure.add(triangle3D);}
    }

    public void removeTriangle(Triangle3D triangle3D)
    {
        for (int i =0;i <Structure.size();i++)
        {
         if (Structure.get(i).isEquals(triangle3D))
         {
             Structure.remove(i);
         }
        }
    }

    public boolean isContent(Triangle3D triangle3D)
    {
        boolean isContent;
        isContent = false;
        if (Structure.size()>0)
        {
            for (int i = 0; i < Structure.size(); i++)
            {
                if (triangle3D.isEquals(Structure.get(i)))
                {
                    isContent = true;
                }
            }
        }
            return isContent;
    }

    public void Select(Line3D line3D)
    {
        for (int i=0;i<Structure.size();i++)
        {
            if (Structure.get(i).isCross(line3D))
            {isSelected = true;}
        }
    }

    public void Group(GObject go)
    {
        for (int i = 0; i<go.Structure.size();i++)
        {
           addTriangle(go.Structure.get(i));
        }
    }

    public ArrayList<GObject> DeGroup()
    {
        ArrayList<GObject> list = new ArrayList<>();
        for (int i=0;i<Structure.size();i++)
        {
            GObject obj = new GObject();
            obj.addTriangle(Structure.get(i));
            list.add(obj);
        }
        return  list;
    }

    public void Rotate (Point3D o, double alfX, double alfY, double alfZ)
    {
        for (int i=0;i<Structure.size();i++)
        {
            Structure.get(i).RotateD(o,alfX,"x");
            Structure.get(i).RotateD(o,alfY,"y");
            Structure.get(i).RotateD(o,alfZ,"z");
        }
    }

    public void Move (double dx, double dy, double dz)
    {
        for (int i=0;i<Structure.size();i++)
        {
            Structure.get(i).Move(dx,dy,dz);
        }
    }
}
