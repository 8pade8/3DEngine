package net.spades;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Content extends JPanel implements Runnable
{
    public static int width;
    public static int height;
    private Thread thread;
    private BufferedImage image;
    private Graphics2D g;
    public static BackGround backGround;


    public static Camera cam;
    public static Projector prj;
    public static GObject GO;
    public static Cube3D cube3D;
    double opp;






    public static boolean centerMode;


    public Content()
    {
        super();
        width = screenSize().width;
        height = screenSize().height;
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocus();
        addKeyListener(new Listeners());
    }

    public void stream()
    {

       cam = new Camera(new Point3D(0,0,-100),1000,1);
       prj = new Projector(this);

        GO = new GObject();
        Cube3D  cube3D = new Cube3D(new Point3D(),100);
//        Cube3D  cube3D2 = new Cube3D(new Point3D(0,0,200),100,200);
//        Cube3D  cube3D3 = new Cube3D(new Point3D(200,0,0),100,200);
//        Cube3D  cube3D4 = new Cube3D(new Point3D(200,0,200),100,200);
//        Cube3D  cube3D5 = new Cube3D(new Point3D(400,0,0),100,200);
//        Cube3D  cube3D6 = new Cube3D(new Point3D(0,0,400),100,200);
//        Cube3D  cube3D7 = new Cube3D(new Point3D(400,0,400),100,200);
//        Cube3D  cube3D8 = new Cube3D(new Point3D(200,0,400),100,200);
//        Cube3D  cube3D9 = new Cube3D(new Point3D(400,0,200),100,200);
        GO = cube3D.toGObject();
//        GO.group(cube3D2.toGObject());
//        GO.group(cube3D3.toGObject());
//        GO.group(cube3D4.toGObject());
//        GO.group(cube3D5.toGObject());
//        GO.group(cube3D6.toGObject());
//        GO.group(cube3D7.toGObject());
//        GO.group(cube3D8.toGObject());
//        GO.group(cube3D9.toGObject());

       Triangle3D t1 = new Triangle3D(new Point3D(),new Point3D(0,0,50),new Point3D(0,50,0));
       Triangle3D t1x = new Triangle3D(new Point3D(),new Point3D(50,0,0),new Point3D(0,50,0));


        Triangle3D t3 = new Triangle3D(new Point3D(0,50,50),new Point3D(0,0,50),new Point3D(0,50,0));


       Triangle3D t2 = new Triangle3D(new Point3D(),new Point3D(0,50,0),new Point3D(-50,0,0));
        Triangle3D t5 = new Triangle3D(new Point3D(-50,50,0),new Point3D(0,50,0),new Point3D(-50,0,0));


        Triangle3D t6 = new Triangle3D(new Point3D(0,0,0),new Point3D(0,0,50),new Point3D(-50,0,50));
        Triangle3D t7 = new Triangle3D(new Point3D(0,0,0),new Point3D(-50,0,0),new Point3D(-50,0,50));

        Triangle3D t8 = new Triangle3D(new Point3D(0,50,0),new Point3D(0,50,50),new Point3D(-50,50,50));
        Triangle3D t9 = new Triangle3D(new Point3D(0,50,0),new Point3D(-50,50,0),new Point3D(-50,50,50));

        Triangle3D t10 = new Triangle3D(new Point3D(0,0,50),new Point3D(-50,0,50),new Point3D(0,50,50));
        Triangle3D t11 = new Triangle3D(new Point3D(-50,50,50),new Point3D(-50,0,50),new Point3D(0,50,50));

        Triangle3D t12 = new Triangle3D(new Point3D(-50,0,0),new Point3D(-50,50,0),new Point3D(-50,0,50));
        Triangle3D t13 = new Triangle3D(new Point3D(-50,50,50),new Point3D(-50,50,0),new Point3D(-50,0,50));

        Triangle3D t21 = new Triangle3D(new Point3D(50,0,0),new Point3D(50,50,0),new Point3D(100,50,0));
        Triangle3D t22 = new Triangle3D(new Point3D(50,50,0),new Point3D(50,50,50),new Point3D(100,50,0));

        Triangle3D tex1 = new Triangle3D(new Point3D(0,0,0),new Point3D(0,0,100),new Point3D(0,100,0));
        Triangle3D tex2 = new Triangle3D(new Point3D(0,100,0),new Point3D(0,100,100),new Point3D(0,0,100));

        Triangle3D tex4 = new Triangle3D(new Point3D(0,100,0),new Point3D(100,100,0),new Point3D(100,100,100));
        Triangle3D tex3 = new Triangle3D(new Point3D(0,100,0),new Point3D(0,100,100),new Point3D(100,100,100));





        Rectangle3D r2 = new Rectangle3D(new Point3D(),100);
        r2.rotateD(new Point3D(),90,Axises.X);


 //    GO.addTriangle(t1);
 //    GO.addTriangle(t1x);
       // GO.addTriangle(t2);
//        GO.addTriangle(t3);
//     GO.addTriangle(t5);
//        GO.addTriangle(t6);
//        GO.addTriangle(t7);
//        GO.addTriangle(t8);
//        GO.addTriangle(t9);
//        GO.addTriangle(t10);
//       GO.addTriangle(t11);
//       GO.addTriangle(t12);
//        GO.addTriangle(t13);
        //GO = r1.toGObject();
       // GO.group(r2.toGObject());

        //GO.group(r4.toGObject());

       //GO.group(t21.toGObject());
       //GO.group(t22.toGObject());

//         GO.group(r5.toGObject()); /// ??????
//        GO.group(r3.toGObject()); ///??????
//        GO.group(tex1.toGObject());
//        GO.group(tex2.toGObject());
//        GO.group(tex3.toGObject());
//        GO.group(tex4.toGObject());

























        thread = new Thread(this);
        thread.start();
    }
    public void run()
    {
        image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        g =(Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        while (true)
        {


          GO.rotate(new Point3D(),-2,-2,-2);
            prj.DrawBG(g);

           prj.DrawGO(cam.transpose(GO),g);
//            System.out.println(GO.structure.get(0).name);
//            System.out.println(GO.structure.get(1).name);
//            System.out.println(GO.structure.get(2).name);
//            System.out.println(GO.structure.get(3).name);

            update();
            toScreen();
            try {
                thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void toScreen(){
        Graphics g2 = this.getGraphics();
        g2.drawImage(image,0,0,null);
        g2.dispose();
    }
    public Dimension screenSize()
    {
        Toolkit toolkit = this.getToolkit();
        return toolkit.getScreenSize();
    }
    public void update(){
        //enemy.update(g);
        //player.update(new Coordinate(mouseX,mouseY),g);



    }
}