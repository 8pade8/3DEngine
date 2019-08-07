package net.spades;

import javax.swing.*;


/**
 * Создал Станислав 13.10.2015.
 */
public class Start
{
    public static void main(String[] args)
    {
        JFrame jFrame;//Создаём фрейм(окно)
        jFrame = new JFrame();//Инициализируем фрейм
        jFrame.setTitle("3D Cube");//Устанавливаем заголовок фрейма
        jFrame.setVisible(true);//Делаем фрейм видимым
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//Останавливаем программу при
                                                                        // нажатии крестика

        Content content;//Создаем объект класса Контент (созданный нами как наследник JPanel)
        content = new Content();//Инициализируем Контент

        jFrame.setContentPane(content);//Заполняем фрейм контентом
        jFrame.pack();//Устанавливаем размеры фрейма по размеру контента
        content.stream();//Запускаем основной метод приложения
    }
}
