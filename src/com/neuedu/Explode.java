package com.neuedu;

import java.awt.*;

//爆炸类
public class Explode {
    int x,y;
    static Image[] imgs = new Image[16];
    static {
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = GameUtil.getImage("images/explode/e"+(i+1)+".gif");
            imgs[i].getWidth(null);//懒加载
        }
    }
    int count;
    boolean live = true;
    public void draw(Graphics g){
        if (!live){
            return;
        }
        if (count<16){
            g.drawImage(imgs[count],x,y,null);
            count++;
        }else {
            live = false;
        }
    }

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
