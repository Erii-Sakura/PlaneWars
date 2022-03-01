package com.neuedu;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class MyGameFrame extends Frame {
    Image bg = GameUtil.getImage("images/bg.jpg");
    Image planeImg = GameUtil.getImage("images/plane.png");
    int x = 200, y = 200;
    Plane plane = new Plane(planeImg, 200, 200, 7);
    Shell[] shell = new Shell[50];
    Explode explode;
    Date startTime = new Date();
    Date endTime;
    int period;//玩了多少秒
    //初始化窗口
    public void launchFrame() {
        this.setTitle("飞机大战");//设置窗口标题
        this.setVisible(true);//设置窗口可见
        this.setSize(GameUtil.FRAME_WIDTH, GameUtil.FRAME_HEIGHT);//设置窗口大小
        this.setLocation(300, 300);//设置窗口位置
        //添加窗口事件  关闭窗口
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);//退出窗口
            }
        });
        new PaintThread().start();//启动窗口绘制线程
        this.addKeyListener(new KeyMonitor());//启动键盘监听
        //初始化50发炮弹
        for (int i=0;i<shell.length;i++){
            shell[i] = new Shell();
        }
    }

    @Override
    public void paint(Graphics g) {
        //g为画笔
        /**
         Color oldcolor = g.getColor();
         g.setColor(Color.green);//设置画笔颜色
         g.setColor(new Color(123,4,63));//设置画笔颜色
         g.drawLine(100,50,400,400);//画直线
         g.drawRect(100,50,400,400);//画矩形
         g.drawOval(100,50,400,400);//画椭圆
         g.drawString("SXT",100,100);//画字符串
         g.setColor(oldcolor);
         */
        g.drawImage(bg, 0, 0, 500, 500, null);
        plane.drawSelf(g);
        for (Shell i:shell){
            i.drawSelf(g);
            boolean peng = i.getRec().intersects(plane.getRec());
            if (peng) {
                System.out.println("飞机死了");
                plane.live = false;
                endTime = new Date();
                period =(int)((endTime.getTime()-startTime.getTime())/1000);
                if (explode==null){
                    explode = new Explode(plane.x,plane.y);
                }
                explode.draw(g);
            }
        }
        if (!plane.live) {
            printInfo(g, "飞机死亡", 20, plane.x, plane.y, Color.WHITE);
            printInfo(g, "游戏时间"+period+"秒", 40, 200, 200, Color.WHITE);
        }
    }

    //创建重画线程
    class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //添加键盘监听内部类
    class KeyMonitor extends KeyAdapter {
        //按下
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            System.out.println("按下"+e.getKeyCode());
            plane.addDriection(e);
        }

        //抬起
        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            System.out.println("抬起"+e.getKeyCode());
            plane.minusDriection(e);
        }
    }

    public static void main(String[] args) {
        MyGameFrame myGameFrame = new MyGameFrame();
        myGameFrame.launchFrame();
    }

    //双缓冲解决图片闪烁问题
    private Image offScreenImage = null;

    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GameUtil.FRAME_WIDTH, GameUtil.FRAME_HEIGHT);//这里是游戏窗口的宽和高
        }
        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);
    }
    public void printInfo(Graphics g,String str,int size,int x,int y,Color color){
        Font oldFont = g.getFont();
        Color oldColor = g.getColor();
        Font f = new Font("宋体",Font.BOLD,size);
        g.setFont(f);
        g.setColor(color);
        g.drawString(str,x,y);
        g.setFont(oldFont);
        g.setColor(oldColor);
    }
}
