package net.spades;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by stanislav on 02.07.16.
 */
public class Projector {
    private int x;
    private int y;
    private int screenWidth;
    private int screenHeight;



    public Projector(Content content)
    {
        screenWidth = (int) content.screenSize().getWidth();
        screenHeight = (int) content.screenSize().getHeight();
        x = screenWidth/2;
        y = screenHeight/2;
    }

    private Point2D Transpose(Point2D point)
    {
        return new Point2D(point.x+x,y-point.y);
    }

    private Line2D Transpose(Line2D line)
    {
        return new Line2D(Transpose(line.A),Transpose(line.B));
    }

    private Triangle Transpose(Triangle tri)
    {
        return new Triangle(Transpose(tri.a),Transpose(tri.b),Transpose(tri.c));
    }

    private GObject2D Transpose(GObject2D GO)
    {
        GObject2D GOX = new GObject2D();
        for (int i = 0; i<GO.Structure.size();i++)
        {
            GOX.addTriangle(Transpose(GO.Structure.get(i)));
        }
        return GOX;
    }

    public void DrawGO (GObject2D GO, Graphics2D g)
    {

        GObject2D GODW = Transpose(GO);
        for (int i=0;i<GODW.Structure.size();i++)
        {

            Line2D l1=GODW.Structure.get(i).a;
            Line2D l2=GODW.Structure.get(i).b;
            Line2D l3=GODW.Structure.get(i).c;

            int[] xii = {(int)GODW.Structure.get(i).a.A.x,(int)GODW.Structure.get(i).b.A.x,(int)GODW.Structure.get(i).c.A.x};
            int[] yii = {(int)GODW.Structure.get(i).a.A.y,(int)GODW.Structure.get(i).b.A.y,(int)GODW.Structure.get(i).c.A.y};
            g.setColor(Color.BLUE);
            g.fillPolygon(xii,yii,3);
            g.setColor(Color.WHITE);
            g.drawLine((int)l1.A.x,(int)l1.A.y,(int)l1.B.x,(int)l1.B.y);
            g.drawLine((int)l2.A.x,(int)l2.A.y,(int)l2.B.x,(int)l2.B.y);
            g.drawLine((int)l3.A.x,(int)l3.A.y,(int)l3.B.x,(int)l3.B.y);

        }
    }

    public void DrawBG (Graphics2D g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0,0, screenWidth, screenHeight);
    }
}
