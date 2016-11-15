package com.yizhishang.plat.web.servlet;

import com.yizhishang.plat.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;

/**
 * 描述: BuildImageServlet.java
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-2-23
 * 创建时间: 下午4:15:22
 */
@Controller
@RequestMapping("/ticketAdmin")
public class BuildImageServlet extends HttpServlet implements Serializable
{

    private static final long serialVersionUID = 1L;

    public BuildImageServlet()
    {
    }

    private Color getRandColor(int fc, int bc)
    {
        //给定范围获得随机颜色
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    //private Font mFont=new Font("宋体", Font.PLAIN,12);//设置字体
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    @Override
    @RequestMapping("/getTicket.action")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //设置页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 在内存中创建图象
        int width = 90, height = 35;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取图形上下文
        Graphics g = image.getGraphics();

        //生成随机类
        Random random = new Random();

        // 设定背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);

        //设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 33));

        //画边框
        //g.setColor(new Color());
        //g.drawRect(0,0,width-1,height-1);

        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160, 200));

        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        // 取随机产生的认证码(4位数字)
        String sRand = "";
        int temp = 15;
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            //调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand, 13 * i + 15, 16 + temp);
        }
        // 将认证码存入SESSION
        request.getSession().setAttribute(Constants.TICKET, sRand);

        // 图象生效
        g.dispose();

        /**
         * 20130520 by
         * 将ImageIO改为JPEGCodec输出到页面
         * 解决1：tomcat兼容性问题
         *     2：ImageIO耗时太长  2007-01-18 by zhanxb
         */
        // 输出图象到页面
        //	        try
        //	        {
        //	            ImageIO.write(image, "JPEG", response.getOutputStream());
        //	        }
        //	        catch (Exception ex)
        //	        {
        //	        }

        //        ServletOutputStream out = response.getOutputStream();
        //        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        //        encoder.encode(image);
        //        out.close();

        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

}
