package net.spades;

import java.util.ArrayList;

/**
 * Created by stanislav on 02.07.16.
 */
public class GObject2D {

    public ArrayList<Triangle> Structure;

    public GObject2D()
    {
        Structure = new ArrayList<>();
    }

    public void addTriangle(Triangle t)
    {
        if (!isContent(t))
        {Structure.add(t);}
    }

    public boolean isContent(Triangle t)
    {
        boolean isContent;
        isContent = false;
        if (Structure.size()>0)
        {
            for (int i = 0; i < Structure.size(); i++)
            {
                if (t.isEquals(Structure.get(i)))
                {
                    isContent = true;
                }
            }
        }
        return isContent;
    }
}
