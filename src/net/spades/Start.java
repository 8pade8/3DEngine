package net.spades;

import javax.swing.*;


/**
 * ������ ��������� 13.10.2015.
 */
public class Start
{
    public static void main(String[] args)
    {
        JFrame jFrame;//������ �����(����)
        jFrame = new JFrame();//�������������� �����
        jFrame.setTitle("3D Cube");//������������� ��������� ������
        jFrame.setVisible(true);//������ ����� �������
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//������������� ��������� ���
                                                                        // ������� ��������

        Content content;//������� ������ ������ ������� (��������� ���� ��� ��������� JPanel)
        content = new Content();//�������������� �������

        jFrame.setContentPane(content);//��������� ����� ���������
        jFrame.pack();//������������� ������� ������ �� ������� ��������
        content.stream();//��������� �������� ����� ����������
    }
}
