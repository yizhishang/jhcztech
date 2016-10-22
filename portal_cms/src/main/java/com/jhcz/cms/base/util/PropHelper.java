package com.jhcz.cms.base.util;

import java.io.File;
import java.net.URL;

public class PropHelper
{
	
	/**
	*guessPropFile:
	*@param cls:和要寻找的属性文件处于相同的包中的任意的类
	*@param propFile:要寻找的属性文件名
	*/
	@SuppressWarnings("rawtypes")
	public static File guessPropFile(Class cls, String propFile)
	{
		File f = null;
		try
		{
			//得到类的类装载器
			ClassLoader loader = cls.getClassLoader();
			//先从当前类所处路径的根目录中寻找属性文件
			URL url = loader.getResource(propFile);
			if (url != null)
			{
				f = new File(url.getPath());
				if (f != null && f.exists() && f.isFile())
				{
					return f;
				}
			}
			//没有找到，就从该类所处的包目录中查找属性文件
			Package pack = cls.getPackage();
			if (pack != null)
			{
				String packName = pack.getName();
				String path = "";
				if (packName.indexOf(".") < 0)
				{
					path = packName + "/";
				}
				else
				{
					int start = 0, end = 0;
					end = packName.indexOf(".");
					while (end != -1)
					{
						path = path + packName.substring(start, end) + "/";
						start = end + 1;
						end = packName.indexOf(".", start);
					}
					path = path + packName.substring(start) + "/";
				}
				url = loader.getResource(path + propFile);
				if (url != null)
				{
					f = new File(url.getPath());
					if (f != null && f.exists() && f.isFile())
					{
						return f;
					}
				}
			}
			//如果没有找到，再从当前系统的用户目录中进行查找
			String curDir = System.getProperty("user.dir");
			f = new File(curDir, propFile);
			if (f != null && f.exists() && f.isFile())
			{
				return f;
			}
			//如果还是没有找到，则从系统所有的类路径中查找
			String classpath = System.getProperty("java.class.path");
			String[] cps = classpath.split(System.getProperty("path.separator"));
			for (int i = 0; i < cps.length; i++)
			{
				f = new java.io.File(cps[i], propFile);
				if (f != null && f.exists() && f.isFile())
				{
					break;
				}
				f = null;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			f = null;
		}
		return f;
	}
}
