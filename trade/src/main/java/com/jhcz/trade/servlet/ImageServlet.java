package com.jhcz.trade.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @描述: 
 * @版权: Copyright (c)
 * @版本：1.0
 */
public class ImageServlet extends HttpServlet
{
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private static final long serialVersionUID = 1L;
	
	//使用到Algerian字体，系统里没有的话需要安装字体，字体只显示大写，去掉了1,0,i,o几个容易混淆的字符  
	public static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
	
	private static SecureRandom random = new SecureRandom();
	
	/** 
	 * 使用系统默认字符源生成验证码 
	 * @param verifySize    验证码长度 
	 * @return 
	 */
	public static String generateVerifyCode(int verifySize)
	{
		return generateVerifyCode(verifySize, VERIFY_CODES);
	}
	
	/** 
	 * 使用指定源生成验证码 
	 * @param verifySize    验证码长度 
	 * @param sources   验证码字符源 
	 * @return 
	 */
	public static String generateVerifyCode(int verifySize, String sources)
	{
		if (sources == null || sources.length() == 0)
		{
			sources = VERIFY_CODES;
		}
		int codesLen = sources.length();
		Random rand = new Random(System.currentTimeMillis());
		StringBuilder verifyCode = new StringBuilder(verifySize);
		for (int i = 0; i < verifySize; i++)
		{
			verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
		}
		return verifyCode.toString();
	}
	
	/** 
	 * 输出随机验证码图片流,并返回验证码值 
	 * @param w 
	 * @param h 
	 * @param os 
	 * @param verifySize 
	 * @return 
	 * @throws IOException 
	 */
	public static BufferedImage outputVerifyImage(int w, int h, OutputStream os, String randomString) throws IOException
	{
		BufferedImage image = outputImage(w, h, os, randomString);
		return image;
		//        return verifyCode;  
	}
	
	/** 
	 * 输出指定验证码图片流 
	 * @param w 
	 * @param h 
	 * @param os 
	 * @param code 
	 * @throws IOException 
	 */
	public static BufferedImage outputImage(int w, int h, OutputStream os, String code) throws IOException
	{
		int verifySize = code.length();
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		SecureRandom rand = new SecureRandom();
		Graphics2D g2 = image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Color[] colors = new Color[5];
		Color[] colorSpaces = new Color[] { Color.WHITE, Color.CYAN, Color.GRAY, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW };
		float[] fractions = new float[colors.length];
		for (int i = 0; i < colors.length; i++)
		{
			colors[i] = colorSpaces[rand.nextInt(colorSpaces.length)];
			fractions[i] = rand.nextFloat();
		}
		Arrays.sort(fractions);
		
		g2.setColor(Color.GRAY);// 设置边框色  
		g2.fillRect(0, 0, w, h);
		
		Color c = getRandColor(200, 250);
		g2.setColor(c);// 设置背景色  
		g2.fillRect(0, 2, w, h - 4);
		
		//绘制干扰线  
		SecureRandom random = new SecureRandom();
		g2.setColor(getRandColor(160, 200));// 设置线条的颜色  
		for (int i = 0; i < 20; i++)
		{
			int x = random.nextInt(w - 1);
			int y = random.nextInt(h - 1);
			int xl = random.nextInt(6) + 1;
			int yl = random.nextInt(12) + 1;
			g2.drawLine(x, y, x + xl + 40, y + yl + 20);
		}
		
		// 添加噪点  
		float yawpRate = 0.05f;// 噪声率  
		int area = (int) (yawpRate * w * h);
		for (int i = 0; i < area; i++)
		{
			int x = random.nextInt(w);
			int y = random.nextInt(h);
			int rgb = getRandomIntColor();
			image.setRGB(x, y, rgb);
		}
		
		shear(g2, w, h, c);// 使图片扭曲  
		
		g2.setColor(getRandColor(100, 160));
		int fontSize = h - 4;
		Font font = new Font("Algerian", Font.ITALIC, fontSize);
		g2.setFont(font);
		char[] chars = code.toCharArray();
		for (int i = 0; i < verifySize; i++)
		{
			AffineTransform affine = new AffineTransform();
			affine.setToRotation(Math.PI / 4 * rand.nextDouble() * (rand.nextBoolean() ? 1 : -1), (w /(1.0*verifySize)) * i + fontSize / 2.0, h / 2.0);
			g2.setTransform(affine);
			g2.drawChars(chars, i, 1, ((w - 10) / verifySize) * i + 5, h / 2 + fontSize / 2 - 10);
		}
		
		g2.dispose();
		return image;
		//        ImageIO.write(image, "jpg", os);  
	}
	
	private static Color getRandColor(int fc, int bc)
	{
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	
	private static int getRandomIntColor()
	{
		int[] rgb = getRandomRgb();
		int color = 0;
		for (int c : rgb)
		{
			color = color << 8;
			color = color | c;
		}
		return color;
	}
	
	private static int[] getRandomRgb()
	{
		int[] rgb = new int[3];
		for (int i = 0; i < 3; i++)
		{
			rgb[i] = random.nextInt(255);
		}
		return rgb;
	}
	
	private static void shear(Graphics g, int w1, int h1, Color color)
	{
		shearX(g, w1, h1, color);
		shearY(g, w1, h1, color);
	}
	
	private static void shearX(Graphics g, int w1, int h1, Color color)
	{
		
		int period = random.nextInt(2);
		
		boolean borderGap = true;
		int frames = 1;
		int phase = random.nextInt(2);
		
		for (int i = 0; i < h1; i++)
		{
			double d = (double) (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			g.copyArea(0, i, w1, 1, (int) d, 0);
			if (borderGap)
			{
				g.setColor(color);
				g.drawLine((int) d, i, 0, i);
				g.drawLine((int) d + w1, i, w1, i);
			}
		}
	}
	
	private static void shearY(Graphics g, int w1, int h1, Color color)
	{
		
		int period = random.nextInt(40) + 10; // 50;  
		
		boolean borderGap = true;
		int frames = 20;
		int phase = 7;
		for (int i = 0; i < w1; i++)
		{
			double d = (double) (period >> 1) * Math.sin((double) i / (double) period + (6.2831853071795862D * (double) phase) / (double) frames);
			g.copyArea(i, 0, 1, h1, 0, (int) d);
			if (borderGap)
			{
				g.setColor(color);
				g.drawLine(i, (int) d, i, 0);
				g.drawLine(i, (int) d + h1, i, h1);
			}
		}
	}
	
	// 增加或减少天数  
	private static Date addDay(Date date, int num)
	{
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}
	
	//启动定时器
	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
	
	/*public static void timerTask()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, Configuration.getInt("task.hour")); //凌晨4点  
		calendar.set(Calendar.MINUTE, Configuration.getInt("task.minute"));
		calendar.set(Calendar.SECOND, 0);
		Date date = calendar.getTime(); //第一次执行定时任务的时间  
		//如果第一次执行定时任务的时间 小于当前的时间  
		//此时要在 第一次执行定时任务的时间加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。  
		if (date.before(new Date()))
		{
			date = addDay(date, 1);
		}
		Timer timer = new Timer();
		timer.schedule(new TimerTask()
		{
			
			public void run()
			{
				SysLogUtils.info("重置图片验证码的队列！");
				vcodeMap = new DataRow();
			}
		}, date, PERIOD_DAY);
	}*/
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		long start = System.currentTimeMillis();
		//SysLogUtils.info("生成图片验证码开始：" + start);
		response.setContentType("image/jpeg");// 设置输出的类型
		String randomString = generateVerifyCode(4);// 得到返回的字符集
		request.getSession(true).setAttribute("randomString", randomString);
		
		ServletOutputStream out = response.getOutputStream();
		
		BufferedImage bi = outputVerifyImage(200, 80, out, randomString);
		// 转换图片格式
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(bi);// 对图片进行编码
		HttpSession session = request.getSession();
//		SessionHelper.setString(AccesserConstants.TICKET, String.valueOf(randomString), session);
//		String key = "12345621";//request.getParameter("mobileKey");
//		ImageServlet.saveVcode(key, randomString);
		out.flush();// 输出
		long end = System.currentTimeMillis();
		logger.info("生成图片验证码结束：" + (end-start) + "毫秒");
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
