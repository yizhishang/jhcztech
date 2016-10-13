package com.jhcz.plat.template.webpart;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.log4j.Logger;

import com.jhcz.base.util.MapHelper;
import com.jhcz.base.util.ReflectHelper;
import com.jhcz.base.util.StringHelper;
import com.jhcz.plat.template.Context;
import com.jhcz.plat.template.WebpartParser;

/**
 * 描述:
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2009-12-28
 * 创建时间: 17:17:11
 */
public class CommonWebpart implements WebpartParser
{
	
	private final Logger logger = Logger.getLogger(CommonWebpart.class);
	
	private String getDefaultView(String provider)
	{
		StringBuffer buffer = new StringBuffer();
		InputStream inStream = null;
		try
		{
			String path = "/" + provider.replace(".", "/");
			path = path + ".view";
			inStream = CommonWebpart.class.getResourceAsStream(path);
			if (inStream != null)
			{
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
				String lineStr = "";
				while ((lineStr = reader.readLine()) != null)
				{
					buffer.append(lineStr + "\r\n");
				}
			}
		}
		catch (Exception ex)
		{
			logger.error("", ex);
		}
		finally
		{
			if (inStream != null)
			{
				try
				{
					inStream.close();
				}
				catch (Exception ex)
				{
				}
			}
		}
		
		return buffer.toString();
	}
	
	        /**
    * 对部件进行解析，得到对应部件的HTML
    * @param context     解析上下文参数
    * @param webpartProp 部件的属性
    * @param viewStr     模板中的部件视图字串
    * @return
    */
	@Override
    public String parse(Context context, Map<String, Object> webpartProp, String viewStr)
	{
		String provider = MapHelper.getString(webpartProp, "provider");
		
        //若页面中视图为空，则根据provider实现类，寻找相应的缺省视图，缺省视图为(类名.view)
		if (StringHelper.isEmpty(viewStr))
		{
			viewStr = getDefaultView(provider);
		}
		
		WebpartParser parser = null;
		parser = (WebpartParser) ReflectHelper.objectForName(provider);
		return parser.parse(context, webpartProp, viewStr);
	}
}