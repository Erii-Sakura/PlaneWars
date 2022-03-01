package com.neuedu;

import java.awt.*;

//游戏物体的根类
public class GameObject {
    Image img;
    int x, y;
    int speed;
    int width, height;

    public GameObject() {
    }

    public GameObject(Image img, int x, int y, int speed) {
        this(img,x,y);
        this.speed = speed;
    }

    public GameObject(Image img, int x, int y) {
        this(img);
        this.x = x;
        this.y = y;
    }

    public GameObject(Image img) {
        this.img = img;
        if (this.img != null) {
            this.width = img.getWidth(null);
            this.height = img.getHeight(null);
        }
    }
    //画自己
    public void drawSelf(Graphics g){
        g.drawImage(this.img,this.x,this.y,this.width,this.height,null);
    }
    //返回出对应的矩形
    public Rectangle getRec(){
        return new Rectangle(x,y,width,height);
    }
}
