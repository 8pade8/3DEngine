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
        return new Point2D(point.getX()+x,y-point.getY());
    }

    private Line2D Transpose(Line2D line)
    {
        return new Line2D(Transpose(line.getX()),Transpose(line.getY()));
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

            int[] xii = {(int)GODW.Structure.get(i).a.getX().getX(),(int)GODW.Structure.get(i).b.getX().getX(),(int)GODW.Structure.get(i).c.getX().getX()};
            int[] yii = {(int)GODW.Structure.get(i).a.getX().getY(),(int)GODW.Structure.get(i).b.getX().getY(),(int)GODW.Structure.get(i).c.getX().getY()};
            g.setColor(Color.BLUE);
            g.fillPolygon(xii,yii,3);
            g.setColor(Color.WHITE);
            g.drawLine((int)l1.getX().getX(),(int)l1.getX().getY(),(int)l1.getY().getX(),(int)l1.getY().getY());
            g.drawLine((int)l2.getX().getX(),(int)l2.getX().getY(),(int)l2.getY().getX(),(int)l2.getY().getY());
            g.drawLine((int)l3.getX().getX(),(int)l3.getX().getY(),(int)l3.getY().getX(),(int)l3.getY().getY());

        }
    }

    public void DrawBG (Graphics2D g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0,0, screenWidth, screenHeight);
    }
}
