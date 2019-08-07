package net.spades;


import java.util.ArrayList;

/**
 * Created by ptmkpd on 17.06.16.
 */
 class Triangle {

    Line2D a;
    Line2D b;
    Line2D c;


    Triangle(Point2D A, Point2D B, Point2D C)
    {
        a = new Line2D(A,B);
        b = new Line2D(B,C);
        c = new Line2D(C,A);
    }

    Triangle(Line2D a, Line2D b, Line2D c) {
        this.c = c;
        this.b = b;
        this.a = a;
    }

    private boolean isContent(Point2D p)
    {
        /* МЯГКОЕ НАХОЖДЕНИЕ точек ВНУТРИ ТРЕУГОЛЬНИКА */
      float p1 = (a.getX().getX()-p.getX())*(b.getX().getY()-a.getX().getY())-(b.getX().getX()-a.getX().getY())*
              (a.getX().getY()-p.getY());
      float p2 = (b.getX().getX()-p.getX())*(c.getX().getY()-b.getX().getY())-(c.getX().getX()-b.getX().getX())*
              (b.getX().getY()-p.getY());
      float p3 = (c.getX().getX()-p.getX())*(a.getX().getY()-c.getX().getY())-(a.getX().getX()-c.getX().getX())*
              (c.getX().getY()-p.getY());

        return (p1>=-0.02 & p2>=-0.02 & p3>=-0.02)||(p1<=0.02 & p2<=0.02 & p3<=0.02);
        //Точки на сторонах ВЫВОДЯТСЯ  (неравенство нестрогое)
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null) {
            return false;
        }
        if (obj.getClass()!= Triangle.class) {
            return false;
        }
        Triangle t = (Triangle) obj;
        return  (a.equals(t.a)&b.equals(t.b)&c.equals(t.c)) ||
          (a.equals(t.b)&b.equals(t.a)&c.equals(t.c)) ||
          (a.equals(t.c)&b.equals(t.c)&c.equals(t.a)) ||
          (a.equals(t.c)&b.equals(t.a)&c.equals(t.b)) ||
          (a.equals(t.c)&b.equals(t.b)&c.equals(t.a));
    }

    private boolean isContentPointOfTriangle(Triangle t) {
        /*МЯГКОЕ ВЗАИМНОЕ (с учетом точек на линиях)*/
        boolean t1 = isContent(t.a.getX());
        boolean t2 = isContent(t.b.getX());
        boolean t3 = isContent(t.c.getX());
        boolean rez1 = t1||t2||t3; //вхождение второго в первый
        boolean t12 = t.isContent(a.getX());
        boolean t22 = t.isContent(b.getX());
        boolean t32 = t.isContent(c.getX());
        boolean rez12 = t12||t22||t32;//вхождение первого во второй
        return rez1||rez12;
    }

    Point2D getCenter() {
        return new Point2D((a.getX().getX()+b.getX().getX()+c.getX().getX())/3,(a.getX().getY()
                +b.getX().getY()+c.getX().getY())/3);
    }

    boolean isCross(Triangle t) {
        /*ТУПОЕ ПЕРЕСЕЧЕНИЕ Х без учета совпадения линий, исходящих линий, общих точек ЗВЕЗДА ДАВИДА*/
        boolean r1 = a.isCross(t.a);
        boolean r2 = a.isCross(t.b);
        boolean r3 = a.isCross(t.c);
        boolean r4 = b.isCross(t.a);
        boolean r5 = b.isCross(t.b);
        boolean r6 = b.isCross(t.c);
        boolean r7 = c.isCross(t.a);
        boolean r8 = c.isCross(t.b);
        boolean r9 = c.isCross(t.c);
        return r1||r2||r3||r4||r5||r6||r7||r8||r9;
    }

    boolean isCrossOrSoftContent(Triangle t) {
        boolean b1 = isContentPointOfTriangle(t);
        boolean b2 = isCross(t);
        return  b1||b2;
    }

    boolean isContentFullPointsOfTriangle(Triangle t){
        // Взаимное, мягкое, полное включение точек треугольника
        boolean t1 = isContent(t.a.getX());
        boolean t2 = isContent(t.b.getX());
        boolean t3 = isContent(t.c.getX());
        boolean rez1 = t1&t2&t3; //вхождение второго в первый
        boolean t12 = t.isContent(a.getX());
        boolean t22 = t.isContent(b.getX());
        boolean t32 = t.isContent(c.getX());
        boolean rez12 = t12&t22&t32;//вхождение первого во второй
        return rez1||rez12;
    }

    double perimeter() {
        return a.length()+b.length()+c.length();
    }

    ArrayList<Point2D> hardCrossPoints(Triangle t)
    {
        /*НАХОДИТ ВСЕ ТОЧКИ "СТРОГОГО Х" ПЕРЕСЕЧЕНИЯ ТРЕУГОЛЬНИКОВ*/
        ArrayList<Point2D> pointsList = new ArrayList<>();
        if (a.isCross(t.a)) {pointsList.add(a.CrossPoint(t.a));}
        if (a.isCross(t.b)) {pointsList.add(a.CrossPoint(t.b));}
        if (a.isCross(t.c)) {pointsList.add(a.CrossPoint(t.c));}
        if (b.isCross(t.a)) {pointsList.add(b.CrossPoint(t.a));}
        if (b.isCross(t.b)) {pointsList.add(b.CrossPoint(t.b));}
        if (b.isCross(t.c)) {pointsList.add(b.CrossPoint(t.c));}
        if (c.isCross(t.a)) {pointsList.add(c.CrossPoint(t.a));}
        if (c.isCross(t.b)) {pointsList.add(c.CrossPoint(t.b));}
        if (c.isCross(t.c)) {pointsList.add(c.CrossPoint(t.c));}
        return pointsList;

    }
}
