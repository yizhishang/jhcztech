package com.jhcz.cms.base.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.jhcz.cms.base.util.PropHelper;
import com.jhcz.cms.base.util.StringHelper;

/**
 * 描述:	 读取configuration.xml文件
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-20
 * 创建时间: 13:06:42
 */
public class Configuration
{
    
    private static Logger logger = Logger.getLogger(Configuration.class);
    
    private static Map<String, String> items = new HashMap<String, String>();
    
    private static String CONFIG_FILE_NAME = "configuration.xml";
    
    static
    {
        loadConfig();
    }
    
    /**
    * 获得布尔型配置值
    * @param name
    * @return
    */
    public static boolean getBoolean(String name)
    {
        String value = getString(name);
        return Boolean.valueOf(value).booleanValue();
    }
    
    /**
    * 获得双精度浮点数配置值
    * @param name
    * @return
    */
    public static double getDouble(String name, double defaultValue)
    {
        String value = getString(name);
        try
        {
            return Double.parseDouble(value);
        }
        catch (NumberFormatException ex)
        {
        }
        return defaultValue;
    }
    
    /**
    * 获得整型配置值
    * @param name
    * @return
    */
    public static int getInt(String name)
    {
        String value = getString(name);
        try
        {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException ex)
        {
            return 0;
        }
    }
    
    /**
    * 获得整型配置值
    * @param name
    * @return
    */
    public static int getInt(String name, int defaultValue)
    {
        String value = getString(name);
        try
        {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException ex)
        {
        }
        return defaultValue;
    }
    
    /**
    * 获得字串配置值
    * @param name
    * @return
    */
    public static String getString(String name)
    {
        String value = items.get(name);
        return (value == null) ? "" : value;
    }
    
    /**
    * 获得字串配置值，若为空，则返回缺省值
    * @param name
    * @param defaultValue
    * @return
    */
    public static String getString(String name, String defaultValue)
    {
        String value = items.get(name);
        if (value != null && value.length() > 0)
            return value;
        else
            return defaultValue;
    }
    
    /**
    * 读入配置文件
    */
    private static void loadConfig()
    {
        File configFile = PropHelper.guessPropFile(Configuration.class, CONFIG_FILE_NAME);
        if (configFile != null && configFile.exists() && configFile.isFile())
        {
            InputStream is = null;
            try
            {
                is = new FileInputStream(configFile);
                SAXReader reader = new SAXReader();
                
                Document document = reader.read(is);
                Element systemElement = document.getRootElement();
                @SuppressWarnings("unchecked")
                List<Element> catList = systemElement.elements("category");
                for (Iterator<Element> catIter = catList.iterator(); catIter.hasNext();)
                {
                    Element catElement = catIter.next();
                    String catName = catElement.attributeValue("name");
                    if (StringHelper.isEmpty(catName))
                    {
                        continue;
                    }
                    
                    @SuppressWarnings("unchecked")
                    List<Element> itemList = catElement.elements("item");
                    for (Iterator<Element> itemIter = itemList.iterator(); itemIter.hasNext();)
                    {
                        Element itemElement = itemIter.next();
                        String itemName = itemElement.attributeValue("name");
                        String value = itemElement.attributeValue("value");
                        if (!StringHelper.isEmpty(itemName))
                        {
                            items.put(catName + "." + itemName, value);
                        }
                    }
                }
                
            }
            catch (Exception ex)
            {
                logger.error("", ex);
            }
            finally
            {
                if (is != null)
                {
                    try
                    {
                        is.close();
                        is = null;
                    }
                    catch (IOException ex)
                    {
                        logger.error("", ex);
                    }
                }
            }
        }
    }
    
    public Map<String, String> getItems()
    {
        return items;
    }
}
