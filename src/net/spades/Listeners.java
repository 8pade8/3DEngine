package net.spades;

import java.awt.event.*;

/**
 * Created by ��������� on 11.10.2015.
 */
public class Listeners implements KeyListener, MouseMotionListener, MouseListener {


    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_O) {Content.centerMode=!Content.centerMode; }
        if (key == KeyEvent.VK_UP) {Content.cam.Move(0,5,0);}
        if (key == KeyEvent.VK_DOWN) {Content.cam.Move(0,-5,0);}
        if (key == KeyEvent.VK_LEFT) {Content.cam.Move(-5,0,0);}
        if (key == KeyEvent.VK_RIGHT) {Content.cam.Move(5,0,0);}
        if (key == KeyEvent.VK_PAGE_UP) {Content.cam.Move(0,0,5);}
        if (key == KeyEvent.VK_PAGE_DOWN) {Content.cam.Move(0,0,-5);}
        if (key == KeyEvent.VK_Q & !Content.centerMode) {Content.cam.Rotate(1,0,0);}
        if (key == KeyEvent.VK_A & !Content.centerMode)  {Content.cam.Rotate(-1,0,0);}
        if (key == KeyEvent.VK_W & !Content.centerMode) {Content.cam.Rotate(0,1,0);}
        if (key == KeyEvent.VK_S & !Content.centerMode) {Content.cam.Rotate(0,-1,0);}
        if (key == KeyEvent.VK_E & !Content.centerMode) {Content.cam.Rotate(0,0,1);}
        if (key == KeyEvent.VK_D & !Content.centerMode) {Content.cam.Rotate(0,0,-1);}
        if (key == KeyEvent.VK_O & !Content.centerMode) {Content.cam.OpticZoom(1);}
        if (key == KeyEvent.VK_L & !Content.centerMode) {Content.cam.OpticZoom(-1);}
        if (key == KeyEvent.VK_I & !Content.centerMode) {Content.cam.CyberZoom(1);}
        if (key == KeyEvent.VK_K & !Content.centerMode) {Content.cam.CyberZoom(-1);}
        if (key == KeyEvent.VK_SPACE & !Content.centerMode) {Content.GO.Rotate(new Point3D(),2,2,2);}
        if (key == KeyEvent.VK_BACK_SPACE & !Content.centerMode) {Content.GO.Rotate(new Point3D(),-2,-2,-2);}

    }


    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        //if (key == KeyEvent.VK_W) Player.up = false;
        //if (key == KeyEvent.VK_S) Player.down = false;
        //if (key == KeyEvent.VK_A) Player.left = false;
        //if (key == KeyEvent.VK_D) Player.right = false;
        //if (key == KeyEvent.VK_SPACE) Player.isfiring = false;

    }


    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }


    public void mousePressed(MouseEvent e) {
    if (e.getButton()== MouseEvent.BUTTON1){
        //GamePanel.player.isfiring = true;
        //GamePanel.leftMouse = true;
    }
    }


    public void mouseReleased(MouseEvent e) {
        if (e.getButton()== MouseEvent.BUTTON1){
           // GamePanel.player.isfiring = false; GamePanel.leftMouse = false;
    }}


    public void mouseEntered(MouseEvent e) {

    }


    public void mouseExited(MouseEvent e) {

    }


    public void mouseDragged(MouseEvent e) {
    //GamePanel.mouseX = e.getX();
    //GamePanel.mouseY = e.getY();

    }


    public void mouseMoved(MouseEvent e) {
       // GamePanel.mouseX = e.getX();
       // GamePanel.mouseY = e.getY();

    }
}
