package com.neuedu;

import java.awt.*;
import java.awt.event.KeyEvent;

//飞机类
public class Plane extends GameObject{
    boolean left,right,up,down;
    boolean live = true;
    @Override
    public void drawSelf(Graphics g) {
        if (!live){
            return;
        }
        super.drawSelf(g);

        if (left){
            x-=speed;
        }
        if (right){
            x+=speed;
        }
        if (up){
            y-=speed;
        }
        if (down){
            y+=speed;
        }
    }
    public void addDriection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
        }
    }
    public void minusDriection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
        }
    }
    public Plane(Image img, int x, int y, int speed) {
        super(img, x, y, speed);
    }
}
