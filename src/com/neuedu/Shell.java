package com.neuedu;

import java.awt.*;

//炮弹类
public class Shell extends GameObject {
    double degree;

    public Shell() {
        degree = Math.random() * Math.PI * 2;
        x = 100;
        y = 100;
        width = 10;
        height = 10;
        speed = 3;
    }

    @Override
    public void drawSelf(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.yellow);
        g.fillOval(x, y, width, height);
        //炮弹任意角度飞行
        x += speed * Math.cos(degree);
        y += speed * Math.sin(degree);
        //碰壁反弹
        if (y > GameUtil.FRAME_HEIGHT || y < 0) {
            degree = -degree;
        }
        if (x < 0 || x > GameUtil.FRAME_WIDTH) {
            degree = Math.PI - degree;
        }
        g.setColor(color);
    }
}
