package com.lzh.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
/**
 * @Description 生成不同的验证码 
 * @author 林泽鸿
 * @time 2019年4月22日 上午2:06:19
 */
public class VerifyCode {
    private int w = 70;
    private int h = 35;
    
    /**
     * 随机数 
     */
    private Random r = new Random();
    /**
     * 设置的字体 {"宋体", "华文楷体", "黑体", "华文新魏", "华文隶书", "微软雅黑", "楷体_GB2312"}
     */
    private String[] fontNames  = {"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};
    /**
     *  可选字符
     */
    private String codes  = "0123456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
    /**
     *  验证码的背景色
     */
    private Color bgColor  = new Color(255, 255, 255);
    /**
     *  验证码上的文本----正确答案
     */
    private String text ;

    /**
     * @Description 生成随机的颜色
     * @return
     */
    private Color randomColor () {
        int red = r.nextInt(150);
        int green = r.nextInt(150);
        int blue = r.nextInt(150);
        return new Color(red, green, blue);
    }

    /**
     *  @Description 生成随机的字体
     * @return
     */
    private Font randomFont () {
        int index = r.nextInt(fontNames.length);
        String fontName = fontNames[index];//生成随机的字体名称
        int style = r.nextInt(4);//生成随机的样式, 0(无样式), 1(粗体), 2(斜体), 3(粗体+斜体)
        int size = r.nextInt(5) + 24; //生成随机字号, 24 ~ 28
        return new Font(fontName, style, size);
    }

    /**
     * @Description  画干扰线 
     * @param image
     */
    private void drawLine (BufferedImage image) {
    	//一共画3条
        int num  = 3;
        Graphics2D g2 = (Graphics2D)image.getGraphics();
      //生成两个点的坐标，即4个值
        for(int i = 0; i < num; i++) {
            int x1 = r.nextInt(w);
            int y1 = r.nextInt(h);
            int x2 = r.nextInt(w);
            int y2 = r.nextInt(h); 
            g2.setStroke(new BasicStroke(1.5F)); 
            g2.setColor(Color.black); //干扰线是蓝色
            g2.drawLine(x1, y1, x2, y2);//画线
        }
    }

    /**
     * @Description  随机生成一个字符 
     * @return
     */
    private char randomChar () {
        int index = r.nextInt(codes.length());
        return codes.charAt(index);
    }

    /**
     * @Description 创建BufferedImage 
     * @return
     */
    private BufferedImage createImage () {
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB); 
        Graphics2D g2 = (Graphics2D)image.getGraphics(); 
        g2.setColor(this.bgColor);
        g2.fillRect(0, 0, w, h);
        return image;
    }

    /**
     * @Description 调用这个方法得到验证码 
     * @return BufferedImage
     */
    public BufferedImage getImage () {
    	//创建图片缓冲区 
        BufferedImage image = createImage();
      //得到绘制环境
        Graphics2D g2 = (Graphics2D)image.getGraphics();
      //用来装载生成的验证码文本
        StringBuilder sb = new StringBuilder();
        
      // 向图片中画4个字符--循环四次，每次生成一个字符
        for(int i = 0; i < 4; i++)  {
        	//随机生成一个字母 
            String s = randomChar() + "";
          //把字母添加到sb中 即StringBuilder中
            sb.append(s); 
          //设置当前字符的x轴坐标
            float x = i * 1.0F * w / 4; 
          //设置随机字体
            g2.setFont(randomFont()); 
          //设置随机颜色
            g2.setColor(randomColor()); 
          //画图
            g2.drawString(s, x, h-5); 
        }
      /**
       * 把StringBuilder类型的验证码赋给了this.text
       */
        this.text = sb.toString(); 
        //添加干扰线
        drawLine(image);
        //得到BufferedImage
        return image;       
    }

    /**
     * @Description 
     * @return 验证码图片上的文本
     */
    public String getText () {
        return text;
    }

    /**
     * @Description  保存图片到指定的输出流 
     * @param image BufferedImage
     * @param out  输出流
     * @throws IOException
     */
    public static void output (BufferedImage image, OutputStream out) 
                throws IOException {
        ImageIO.write(image, "JPEG", out);
    }
}

