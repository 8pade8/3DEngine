package net.spades;

import java.util.ArrayList;

/**
 * Created by stanislav on 02.07.16.
 */
public class Camera {

    private Point3D Objective;
    private Point3D Focus;
    private Point3D Horizon;
    private Point3D Vertical;
    private double optic;
    private double resolution;

    public Camera(Point3D objective, double optic, double resolution)
    {
        Objective = objective;
        if (optic!=0) this.optic  = optic;
        else this.optic = 100;
        if (resolution!=0) this.resolution = resolution;
        else this.resolution = 1;
        Focus = new Point3D(objective.x,objective.y,objective.z-this.optic);
        Horizon = new Point3D(objective.x+resolution,objective.y,objective.z);
        Vertical = new Point3D(objective.x,objective.y+resolution,objective.z);
    }

    private Point2D Transpose(Point3D point)
    {
        Plane3D screen = new Plane3D(new Vector3D(Objective,Focus),Objective);
        Point3D XPoint = screen.CrossOfLine(new FunctionLine3D(Focus,point));
        FunctionLine3D fx=new FunctionLine3D(Objective,Horizon);
        Plane3D px=new Plane3D(new Vector3D(fx.m,fx.n,fx.p),XPoint);
        Point3D XPointX = px.CrossOfLine(fx);
        FunctionLine3D fy=new FunctionLine3D(Objective,Vertical);
        Plane3D py=new Plane3D(new Vector3D(fy.m,fy.n,fy.p),XPoint);
        Point3D XPointY = py.CrossOfLine(fy);
        double dX = Objective.DimensionOfPoint(XPointX)/resolution;
        boolean znakX = true;
        if (dX >= 1 & Horizon.DimensionOfPoint(XPointX)> Objective.DimensionOfPoint(XPointX)){znakX = false;}
        double X;
        if (znakX) {X=dX;} else {X=-dX;}
        double dY = Objective.DimensionOfPoint(XPointY)/resolution;
        boolean znakY = true;
        if (dY >= 1 & Vertical.DimensionOfPoint(XPointY)> Objective.DimensionOfPoint(XPointY)){znakY = false;}
        double Y;
        if (znakY) {Y=dY;} else {Y=-dY;}
        Point2D point2D = new Point2D(X,Y);
        return point2D;
    }

    private Line2D Transpose(Line3D line)
    {
        return new Line2D(Transpose(line.A),Transpose(line.B));
    }

    private Triangle Transpose(Triangle3D triangle3D)
    {
        Triangle t= new Triangle(Transpose(triangle3D.A),Transpose(triangle3D.B),Transpose(triangle3D.C));
        return t;
    }

    public GObject2D Transpose(GObject GO)
    {
        SortX(GO);
        GObject2D GO2D = new GObject2D();

        for (int i=0;i<GO.Structure.size();i++)
        {
            GO2D.addTriangle(Transpose(GO.Structure.get(i)));
        }
        return GO2D;
    }

    private Line3D UnTranspose(Point2D point)
    {
        Point3D xPoint;
        Vector3D vx = new Vector3D(Objective,Horizon);
        Vector3D vy = new Vector3D(Objective,Vertical);
        xPoint = new Point3D(Objective.x,Objective.y,Objective.z);
        xPoint = xPoint.PointAsVector(vx.MultiplyNum(point.x*resolution));
        xPoint = xPoint.PointAsVector(vy.MultiplyNum(point.y*resolution));
        return new Line3D(Focus,xPoint);
    }

    public void Move (double dX, double dY, double dZ)
    {
        Vector3D camX = new Vector3D(Objective,Horizon).Direction();
        Vector3D camY = new Vector3D(Objective,Vertical).Direction();
        Vector3D camZ = new Vector3D(Focus,Objective).Direction();
        Vector3D DX = camX.MultiplyNum(dX);
        Vector3D DY = camY.MultiplyNum(dY);
        Vector3D DZ = camZ.MultiplyNum(dZ);
        Objective = Objective.PointAsVector(DX);
        Objective = Objective.PointAsVector(DY);
        Objective = Objective.PointAsVector(DZ);
        Focus = Focus.PointAsVector(DX);
        Focus = Focus.PointAsVector(DY);
        Focus = Focus.PointAsVector(DZ);
        Horizon = Horizon.PointAsVector(DX);
        Horizon = Horizon.PointAsVector(DY);
        Horizon = Horizon.PointAsVector(DZ);
        Vertical = Vertical.PointAsVector(DX);
        Vertical = Vertical.PointAsVector(DY);
        Vertical = Vertical.PointAsVector(DZ);
    }

    public void Rotate (double dX, double dY, double dZ)
    {
        Objective.RotateD(Focus,dX,"x");
        Horizon.RotateD(Focus,dX,"x");
        Vertical.RotateD(Focus,dX,"x");
        Objective.RotateD(Focus,dY,"y");
        Horizon.RotateD(Focus,dY,"y");
        Vertical.RotateD(Focus,dY,"y");
        Objective.RotateD(Focus,dZ,"z");
        Horizon.RotateD(Focus,dZ,"z");
        Vertical.RotateD(Focus,dZ,"z");
    }

    public void setOptic(double op)
    {
        optic = op;
        Vector3D v = new Vector3D(Objective,Focus).Direction().MultiplyNum(op);
        Focus = Objective.PointAsVector(v);
    }

    public void setResolution(double res)
    {
        resolution = res;
        Vector3D vx = new Vector3D(Objective,Horizon).Direction().MultiplyNum(res);
        Vector3D vy = new Vector3D(Objective, Vertical).Direction().MultiplyNum(res);
        Vertical = Objective.PointAsVector(vy);
        Horizon = Objective.PointAsVector(vx);
    }

    public void OpticZoom(double dO)
    {
        double kO = optic+dO;
        setOptic(kO);
    }

    public void CyberZoom(double dO)
    {
        double kO = resolution+dO;
        setResolution(kO);
    }

    public boolean lastFirst (Triangle3D T1, Triangle3D T2) // Метод опредления ближнего к камере треугольника
    {
        Plane3D T1Plane = T1.getPlane(); // Созадаем плоскость первого треугольника
        Plane3D ObjectivePlane = new Plane3D(Objective,Horizon,Vertical); // Создаем плоскость объектива
        if (T1Plane.isContent(T2.A) & T1Plane.isContent(T2.B) & !T1Plane.isPerpendicular(ObjectivePlane))
        // Проверяем содержит ли плоскость первого треугольника две стороны второго,
        //и перпендикулярна ли плоскость треугольника плоскости объектива
        {
           // System.out.println("Triangles in the same plane (and not perpendicular to the plane of the screen)");
            return true; //треугольники в одной плоскости (НЕ ПЕРПЕНДИКУЛЯРНОЙ ПЛОСКОСТИ ЭКРАНА), их порядок не важен - меняем местами (проброс сортировки)
        }

        Triangle prT1 = Transpose(T1); // Создаем проекцию первого треугольника
        Triangle prT2 = Transpose(T2); // Создаем проекцию второго треугольника

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
            Line3D aimLine = UnTranspose(aim); // Депроецируем точку в линию
            Point3D aimLineCrossT1 = T1Plane.CrossOfLine(aimLine.getFunction()); // Определяем точку пересечения
            // депроекции с плоскостью первого треугольника
            Plane3D T2Plane = T2.getPlane(); // Созадаем плоскость второго треугольника
            Point3D aimLineCrossT2 = T2Plane.CrossOfLine(aimLine.getFunction()); // Определяем точку пересечения
            // депроекции с плоскостью второго треугольника
            double s1 = Focus.DimensionOfPoint(aimLineCrossT1); // Определяем расстояние от фокуса до центра первого треугольника
            double s2 = Focus.DimensionOfPoint(aimLineCrossT2); // Определяем расстояние от фокуса до центра второго треугольника
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
                Line3D aimLine = UnTranspose(listCrossPoints.get(i)); // Депроецируем точку в линию
                Point3D aimLineCrossT1 = T1Plane.CrossOfLine(aimLine.getFunction()); // Определяем точку пересечения
                // депроекции с плоскостью первого треугольника
                Plane3D T2Plane = T2.getPlane(); // Созадаем плоскость второго треугольника
                Point3D aimLineCrossT2 = T2Plane.CrossOfLine(aimLine.getFunction()); // Определяем точку пересечения
                // депроекции с плоскостью второго треугольника
                double d1 = Focus.DimensionOfPoint(aimLineCrossT1); // Определяем расстояние от фокуса до первого треугольника
                double d2 = Focus.DimensionOfPoint(aimLineCrossT2); // Определяем расстояние от фокуса до второго треугольника
                d+=d1-d2; // Определяем разость расстояний до треугольников и накапливаем (некоторые разности могут быть близки к нулю)
            }
            if ( d<-0.01 || d>0.01) // Проверяем равна ли накопленая разность нулю, учитывая погрешность вычислений
            {
             //   System.out.println("Triangles strictly intersect"+" d= "+d);
                return d < 0; // Знак суммарной разности растояний до треугольников определяет их близость к камере
            }
            ArrayList<Point2D> listVertex = new ArrayList<>(); // Создаем список вершин
            listVertex.add(prT1.a.A); // Помещаем вершину  проецкии первого треугольника в список вершин
            listVertex.add(prT1.b.A); // Помещаем вершину  проецкии первого треугольника в список вершин
            listVertex.add(prT1.c.A); // Помещаем вершину  проецкии первого треугольника в список вершин
            listVertex.add(prT2.a.A); // Помещаем вершину  проецкии второго треугольника в список вершин
            listVertex.add(prT2.b.A); // Помещаем вершину  проецкии второго треугольника в список вершин
            listVertex.add(prT2.c.A); // Помещаем вершину  проецкии второго треугольника в список вершин
            double l = 0; // Вводим переменную расстояния до треугольников
            for (int i = 0; i<listVertex.size();i++) // Перебираем все вершины из списка
            {
                Line3D aimLine = UnTranspose(listVertex.get(i)); // Депроецируем точку в линию
                Point3D aimLineCrossT1 = T1Plane.CrossOfLine(aimLine.getFunction()); // Определяем точку пересечения
                // депроекции с плоскостью первого треугольника
                Plane3D T2Plane = T2.getPlane(); // Созадаем плоскость второго треугольника
                Point3D aimLineCrossT2 = T2Plane.CrossOfLine(aimLine.getFunction()); // Определяем точку пересечения
                // депроекции с плоскостью второго треугольника
                if (T1.isContent(aimLineCrossT1)&T2.isContent(aimLineCrossT2)) // Проверяем содержат ли треугольники
                // соответсвующие полученые точки пересечения депроекций с плоскостями треугольников
                {
                    double l1 = Focus.DimensionOfPoint(aimLineCrossT1); // Определяем растояние от фокуса до первого треугольника
                    double l2 = Focus.DimensionOfPoint(aimLineCrossT2); // Определяем растояние от фокуса до второго треугольника
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
        SortedList.add(GO.Structure.get(0));
        for (int i = 1; i < GO.Structure.size(); i++) {
            int j = 0;
            while (j < SortedList.size()) {
                if (lastFirst(GO.Structure.get(i), SortedList.get(j))) {
                    j++;
                }
                else {break;}
            }
            SortedList.add(j, GO.Structure.get(i));
        }
        GO.Structure = SortedList;
    }

}
