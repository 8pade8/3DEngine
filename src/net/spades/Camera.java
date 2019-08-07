package net.spades;

import java.util.ArrayList;

/**
 * Created by stanislav on 02.07.16.
 */
public class Camera {

    private Point3D objective;
    private Point3D focus;
    private Point3D horizon;
    private Point3D vertical;
    private float optic;
    private float resolution;

    public Camera(Point3D objective, float optic, float resolution) {

        this.objective = objective;
        if (optic!=0) this.optic  = optic;
        else this.optic = 100;
        if (resolution!=0) this.resolution = resolution;
        else this.resolution = 1;
        focus = new Point3D(objective.getX(),objective.getY(),objective.getZ()-this.optic);
        horizon = new Point3D(objective.getX()+resolution,objective.getY(),objective.getZ());
        vertical = new Point3D(objective.getX(),objective.getY()+resolution,objective.getZ());
    }

    private Point2D transpose(Point3D point) {

        Plane3D screen = new Plane3D(new Vector3D(objective, focus), objective);
        Point3D XPoint = screen.crossOfLine(new Line3D(focus,point));
        Line3D fx=new Line3D(objective, horizon);
        Plane3D px=new Plane3D(new Vector3D(fx.getM(),fx.getN(),fx.getP()),XPoint);
        Point3D XPointX = px.crossOfLine(fx);
        Line3D fy=new Line3D(objective, vertical);
        Plane3D py=new Plane3D(new Vector3D(fy.getM(),fy.getN(),fy.getP()),XPoint);
        Point3D XPointY = py.crossOfLine(fy);
        float dX = objective.dimensionOfPoint(XPointX)/resolution;
        boolean znakX = true;
        if (dX >= 1 & horizon.dimensionOfPoint(XPointX)> objective.dimensionOfPoint(XPointX)){znakX = false;}
        float X;
        if (znakX) {X=dX;} else {X=-dX;}
        float dY = objective.dimensionOfPoint(XPointY)/resolution;
        boolean znakY = true;
        if (dY >= 1 & vertical.dimensionOfPoint(XPointY)> objective.dimensionOfPoint(XPointY)){znakY = false;}
        float Y;
        if (znakY) {Y=dY;} else {Y=-dY;}
        Point2D point2D = new Point2D(X,Y);
        return point2D;
    }

    private Triangle transpose(Triangle3D triangle3D) {
        Triangle t= new Triangle(transpose(triangle3D.getA()), transpose(triangle3D.getB()), transpose(triangle3D.getC()));
        return t;
    }

    public GObject2D transpose(GObject GO) {
        SortX(GO);
        GObject2D GO2D = new GObject2D();

        for (int i = 0; i<GO.structure.size(); i++) {
            GO2D.addTriangle(transpose(GO.structure.get(i)));
        }
        return GO2D;
    }

    private Line3D unTranspose(Point2D point) {
        Point3D xPoint;
        Vector3D vx = new Vector3D(objective, horizon);
        Vector3D vy = new Vector3D(objective, vertical);
        xPoint = objective.copy();
        xPoint.moveForVector(vx.multiplyNum(point.getX()*resolution));
        xPoint.moveForVector(vy.multiplyNum(point.getY()*resolution));
        return new Line3D(focus,xPoint);
    }

    public void move(float dX, float dY, float dZ) {
        Vector3D camX = new Vector3D(objective, horizon).direction();
        Vector3D camY = new Vector3D(objective, vertical).direction();
        Vector3D camZ = new Vector3D(focus, objective).direction();
        Vector3D DX = camX.multiplyNum(dX);
        Vector3D DY = camY.multiplyNum(dY);
        Vector3D DZ = camZ.multiplyNum(dZ);
        objective.moveForVector(DX);
        objective.moveForVector(DY);
        objective.moveForVector(DZ);
        focus.moveForVector(DX);
        focus.moveForVector(DY);
        focus.moveForVector(DZ);
        horizon.moveForVector(DX);
        horizon.moveForVector(DY);
        horizon.moveForVector(DZ);
        vertical.moveForVector(DX);
        vertical.moveForVector(DY);
        vertical.moveForVector(DZ);
    }

    public void rotate(float dX, float dY, float dZ) {
        objective.rotateD(focus,dX,Axises.X);
        horizon.rotateD(focus,dX,Axises.X);
        vertical.rotateD(focus,dX,Axises.X);
        objective.rotateD(focus,dY,Axises.Y);
        horizon.rotateD(focus,dY,Axises.Y);
        vertical.rotateD(focus,dY,Axises.Y);
        objective.rotateD(focus,dZ,Axises.Z);
        horizon.rotateD(focus,dZ,Axises.Z);
        vertical.rotateD(focus,dZ,Axises.Z);
    }

    public void setOptic(float op) {
        optic = op;
        Vector3D v = new Vector3D(objective, focus).direction().multiplyNum(op);
        focus = objective.copy();
        focus.moveForVector(v);
    }

    public void setResolution(float res)
    {
        resolution = res;
        Vector3D vx = new Vector3D(objective, horizon).direction().multiplyNum(res);
        Vector3D vy = new Vector3D(objective, vertical).direction().multiplyNum(res);
        vertical = objective.copy();
        vertical.moveForVector(vy);
        horizon = objective.copy();
        horizon.moveForVector(vx);
    }

    public void opticZoom(float dO) {
        float kO = optic+dO;
        setOptic(kO);
    }

    public void cyberZoom(float dO) {
        float kO = resolution+dO;
        setResolution(kO);
    }

    public boolean lastFirst (Triangle3D T1, Triangle3D T2) {
        // Метод опредления ближнего к камере треугольника
        Plane3D T1Plane = T1.getPlane(); // Созадаем плоскость первого треугольника
        Plane3D ObjectivePlane = new Plane3D(objective, horizon, vertical); // Создаем плоскость объектива
        if (T1Plane.isContent(T2.getA()) & T1Plane.isContent(T2.getB()) & !T1Plane.isPerpendicular(ObjectivePlane))
        // Проверяем содержит ли плоскость первого треугольника две стороны второго,
        //и перпендикулярна ли плоскость треугольника плоскости объектива
        {
           // System.out.println("Triangles in the same plane (and not perpendicular to the plane of the screen)");
            return true; //треугольники в одной плоскости (НЕ ПЕРПЕНДИКУЛЯРНОЙ ПЛОСКОСТИ ЭКРАНА), их порядок не важен - меняем местами (проброс сортировки)
        }

        Triangle prT1 = transpose(T1); // Создаем проекцию первого треугольника
        Triangle prT2 = transpose(T2); // Создаем проекцию второго треугольника

        if (!prT1.isCrossOrSoftContent(prT2)) // Проверяем имеют ли проекции треугольников общие точки (пересечения, включения)
        {
          //  System.out.println("Projections are disjoint");
            return true; //проекции не имеют общих точек, их порядок не важен - меняем местами (проброс сортировки)
        }

        if (prT1.isContentFullPointsOfTriangle(prT2)) // Проверяем содержит ли проекция одного
        // треугольника все точки проекции другого и наоборот
        {
            double perimeter1 = prT1.perimeter(); // Определяем периметр проекции первого треугольника
            double perimeter2 = prT2.perimeter(); // Определеяем периметр проекции второго треугольника
            Point2D aim;
            if (perimeter1<=perimeter2) {aim = prT1.getCenter();} else {aim = prT2.getCenter();}
            // Создаем и задаем точку депроецирования как центр включаемого (меньшего) треугольника
            Line3D aimLine = unTranspose(aim); // Депроецируем точку в линию
            Point3D aimLineCrossT1 = T1Plane.crossOfLine(aimLine); // Определяем точку пересечения
            // депроекции с плоскостью первого треугольника
            Plane3D T2Plane = T2.getPlane(); // Созадаем плоскость второго треугольника
            Point3D aimLineCrossT2 = T2Plane.crossOfLine(aimLine); // Определяем точку пересечения
            // депроекции с плоскостью второго треугольника
            double s1 = focus.dimensionOfPoint(aimLineCrossT1); // Определяем расстояние от фокуса до центра первого треугольника
            double s2 = focus.dimensionOfPoint(aimLineCrossT2); // Определяем расстояние от фокуса до центра второго треугольника
           // System.out.println("One triangle to another"+" s1 = "+s1+" s2 = "+s2);
           // System.out.println(s1-s2<0);
            return s1-s2<0; // Знак разности расстояний от фокуса до центров треугольников определяет их близость к камере
        }
        if (prT1.isCross(prT2)) // Определяем есть ли "строгое Х" пересечение сторон проекций треугольников
        {
            ArrayList<Point2D> listCrossPoints = prT1.hardCrossPoints(prT2); // Определяем все точки "строгих Х"
            // пересечений сторон проекций треугольков
            double d = 0; // Вводим переменную расстояния до треугольников
            for (int i = 0; i < listCrossPoints.size(); i++) // Проходим по всем точкам пересечений
            {
                Line3D aimLine = unTranspose(listCrossPoints.get(i)); // Депроецируем точку в линию
                Point3D aimLineCrossT1 = T1Plane.crossOfLine(aimLine); // Определяем точку пересечения
                // депроекции с плоскостью первого треугольника
                Plane3D T2Plane = T2.getPlane(); // Созадаем плоскость второго треугольника
                Point3D aimLineCrossT2 = T2Plane.crossOfLine(aimLine); // Определяем точку пересечения
                // депроекции с плоскостью второго треугольника
                double d1 = focus.dimensionOfPoint(aimLineCrossT1); // Определяем расстояние от фокуса до первого треугольника
                double d2 = focus.dimensionOfPoint(aimLineCrossT2); // Определяем расстояние от фокуса до второго треугольника
                d+=d1-d2; // Определяем разость расстояний до треугольников и накапливаем (некоторые разности могут быть близки к нулю)
            }
            if ( d<-0.01 || d>0.01) // Проверяем равна ли накопленая разность нулю, учитывая погрешность вычислений
            {
             //   System.out.println("Triangles strictly intersect"+" d= "+d);
                return d < 0; // Знак суммарной разности растояний до треугольников определяет их близость к камере
            }
            ArrayList<Point2D> listVertex = new ArrayList<>(); // Создаем список вершин
            listVertex.add(prT1.a.getX()); // Помещаем вершину  проецкии первого треугольника в список вершин
            listVertex.add(prT1.b.getX()); // Помещаем вершину  проецкии первого треугольника в список вершин
            listVertex.add(prT1.c.getX()); // Помещаем вершину  проецкии первого треугольника в список вершин
            listVertex.add(prT2.a.getX()); // Помещаем вершину  проецкии второго треугольника в список вершин
            listVertex.add(prT2.b.getX()); // Помещаем вершину  проецкии второго треугольника в список вершин
            listVertex.add(prT2.c.getX()); // Помещаем вершину  проецкии второго треугольника в список вершин
            double l = 0; // Вводим переменную расстояния до треугольников
            for (int i = 0; i<listVertex.size();i++) // Перебираем все вершины из списка
            {
                Line3D aimLine = unTranspose(listVertex.get(i)); // Депроецируем точку в линию
                Point3D aimLineCrossT1 = T1Plane.crossOfLine(aimLine); // Определяем точку пересечения
                // депроекции с плоскостью первого треугольника
                Plane3D T2Plane = T2.getPlane(); // Созадаем плоскость второго треугольника
                Point3D aimLineCrossT2 = T2Plane.crossOfLine(aimLine); // Определяем точку пересечения
                // депроекции с плоскостью второго треугольника
                if (T1.isContent(aimLineCrossT1)&T2.isContent(aimLineCrossT2)) // Проверяем содержат ли треугольники
                // соответсвующие полученые точки пересечения депроекций с плоскостями треугольников
                {
                    double l1 = focus.dimensionOfPoint(aimLineCrossT1); // Определяем растояние от фокуса до первого треугольника
                    double l2 = focus.dimensionOfPoint(aimLineCrossT2); // Определяем растояние от фокуса до второго треугольника
                    l = l1 - l2; // Определяем разность растояний до треугольников и накапливаем (некоторые разности могут быть близки к нулю)
                }
            }
            if (l<-0.01 || l>0.01) // Проверяем равна ли накопленая разность нулю (с учетом погрешности вычислений)
            {
             //   System.out.println("At the apex of the triangle distance"+" l = "+l);
                return l<0; // // Знак суммарной разности растояний до треугольников определяет их близость
            }
        }
        //System.out.println("The triangles do not intersect");
        return true; //меняем местами (проброс сортировки)
    }

    private void SortX(GObject GO) {
       // System.out.println("SORT:");
        ArrayList<Triangle3D> SortedList = new ArrayList<>();
        SortedList.add(GO.structure.get(0));
        for (int i = 1; i < GO.structure.size(); i++) {
            int j = 0;
            while (j < SortedList.size()) {
                if (lastFirst(GO.structure.get(i), SortedList.get(j))) {
                    j++;
                }
                else {break;}
            }
            SortedList.add(j, GO.structure.get(i));
        }
        GO.structure = SortedList;
    }

}
