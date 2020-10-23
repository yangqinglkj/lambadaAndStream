package com.watermark;

import sun.font.FontDesignMetrics;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @Author yq
 * @Date 2020/10/20 10:27
 */

public class WatermarkTest {

    /**
     * @description
     * @param sourceImgPath 源图片路径
     * @param tarImgPath 保存的图片路径
     * @param waterMarkContent 水印内容
     * @param fileExt 图片格式
     * @return void
     */
    public void addWatermark(String sourceImgPath, String tarImgPath, String waterMarkContent,String fileExt){



        Font font = new Font("宋体", Font.BOLD, 20);//水印字体，大小
        Color markContentColor = Color.red;//水印颜色
        Integer degree = 45;//设置水印文字的旋转角度
        float alpha = 0.5f;//设置水印透明度
        OutputStream outImgStream = null;
        try {
            File srcImgFile = new File(sourceImgPath);//得到文件
            Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = bufImg.createGraphics();
            g2.dispose();

            Graphics2D g = bufImg.createGraphics();//得到画笔
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            g.setColor(markContentColor); //设置水印颜色
            g.setFont(font);              //设置字体
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));//设置水印文字透明度
            if (null != degree) {
                g.rotate(Math.toRadians(degree));//设置水印旋转
            }
            JLabel label = new JLabel(waterMarkContent);
            FontMetrics metrics = label.getFontMetrics(font);
            int width = metrics.stringWidth(label.getText());//文字水印的宽
            int rowsNumber = srcImgHeight/width;// 图片的高  除以  文字水印的宽    ——> 打印的行数(以文字水印的宽为间隔)
            int columnsNumber = srcImgWidth/width;//图片的宽 除以 文字水印的宽   ——> 每行打印的列数(以文字水印的宽为间隔)
            //防止图片太小而文字水印太长，所以至少打印一次
            if(rowsNumber < 1){
                rowsNumber = 1;
            }
            if(columnsNumber < 1){
                columnsNumber = 1;
            }
            for(int j=0;j<rowsNumber;j++){
                for(int i=0;i<columnsNumber;i++){
                    g.drawString(waterMarkContent, i*width + j*width, -i*width + j*width);//画出水印,并设置水印位置
                }
            }
            g.dispose();// 释放资源
            // 输出图片
            outImgStream = new FileOutputStream(tarImgPath);
            ImageIO.write(bufImg, fileExt, outImgStream);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        } finally{
            try {
                if(outImgStream != null){
                    outImgStream.flush();
                    outImgStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getMessage();
            }
        }
    }

    public static void main(String[] args)throws Exception {
//        System.out.println("开始水印：");
//        new WatermarkTest().addWatermark("D:/qq.png", "D:/qqtest.png", "admin", "png");
//        System.out.println("水印完成。");
        new WatermarkTest().stringToPng("admin",new File("D://test/test.png"));
    }

    private void stringToPng(String text,File outFile) throws IOException,FontFormatException {
        //BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);

        //Font font = new Font("STSong-Light", Font.PLAIN, 90);
        InputStream is = this.getClass().getResourceAsStream("/font/simhei.ttf");
        Font fontTemp = Font.createFont(Font.TRUETYPE_FONT,is);//返回一个指定字体类型和输入数据的font

        Font font = fontTemp.deriveFont(Font.BOLD,30);

        // 获取font的样式应用在输出内容上整个的宽高
        int[] arr = getWidthAndHeight(text, font);
        int width = arr[0];
        int height = arr[1];

        BufferedImage image = createImageWithText(text,width,height,font,Color.gray,0.8f);

        // 输出png图片，formatName 对应图片的格式
        ImageIO.write(image, "png", outFile);
    }
    /**
     * 创建背景透明的文字图片
     *
     * @param str       文本字符串
     * @param width     图片宽度
     * @param height    图片高度
     * @param font      设置字体
     * @param fontColor 字体颜色
     * @param alpha     文字透明度，值从0.0f-1.0f，依次变得不透明
     */
    private BufferedImage createImageWithText(String str, int width, int height, Font font, Color fontColor, float alpha) {
        BufferedImage textImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = textImage.createGraphics();

        //设置背景透明
        textImage = g2.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g2.dispose();
        g2 = textImage.createGraphics();

        //开启文字抗锯齿
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        //设置字体
        g2.setFont(font);
        //设置字体颜色
        g2.setColor(fontColor);
        //设置透明度:1.0f为透明度 ，值从0-1.0，依次变得不透明
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        //计算字体位置：上下左右居中
        FontRenderContext context = g2.getFontRenderContext();
        LineMetrics lineMetrics = font.getLineMetrics(str, context);
        FontMetrics fontMetrics = FontDesignMetrics.getMetrics(font);
        float offset = (width - fontMetrics.stringWidth(str)) / 2;
        float y = (height + lineMetrics.getAscent() - lineMetrics.getDescent() - lineMetrics.getLeading()) / 2;
        //绘图
        g2.drawString(str, (int) offset, (int) y);
        //释放资源
        g2.dispose();
        return textImage;
    }


    private  int[] getWidthAndHeight(String text, Font font) {
        Rectangle2D r = font.getStringBounds(text, new FontRenderContext(
                AffineTransform.getScaleInstance(1, 1), false, false));
        int unitHeight = (int) Math.floor(r.getHeight());//
        // 获取整个str用了font样式的宽度这里用四舍五入后+1保证宽度绝对能容纳这个字符串作为图片的宽度
        int width = (int) Math.round(r.getWidth()) + 1;
        // 把单个字符的高度+3保证高度绝对能容纳字符串作为图片的高度
        int height = unitHeight + 3;
        return new int[]{width, height};
    }

    public int getPercent(float h,float w) {
        int p = 0;
        float p2 = 0.0f;
        if (h > w) {
            p2 = 1160 / h * 100;
        } else {
            p2 = 842 / w * 100;
        }
        p = Math.round(p2);
        return p;
    }
}
