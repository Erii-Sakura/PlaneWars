package com.neuedu;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

//设置游戏工具类
public class GameUtil {
    public static final int FRAME_WIDTH=500;
    public static final int FRAME_HEIGHT=500;
    //构造器私有  防止外部创建对象
    private GameUtil(){}
    //定义获取图片的方法
    public static Image getImage(String path){
        Image img = null;
        URL url = GameUtil.class.getClassLoader().getResource(path);
        try {
             img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
