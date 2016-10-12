package com.jhcz.base.config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.jhcz.base.domain.Config;
import com.jhcz.base.domain.Right_Url;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.service.ConfigService;
import com.jhcz.base.service.ServiceLocator;
import com.jhcz.base.util.ReflectHelper;
import com.jhcz.base.util.StringHelper;

/**
 * 描述: 保存系统配置信息类
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-20
 * 创建时间: 14:29:06
 */
public class SysConfig
{
    
    private static SysConfig instance = new SysConfig();
    
    private static DataRow itemMap = new DataRow();
    
    public static ArrayList<Right_Url> rightUrl = new ArrayList<Right_Url>();
    
    static
    {
        loadConfig();
    }
    
    public static SysConfig getInstance()
    {
        return instance;
    }
    
    /**
    * 返回整型配置值
    * @param name 键值名称
    * @return 若不存在，或转换失败，则返回0
    */
    public static int getInt(String name)
    {
        return itemMap.getInt(name);
    }
    
    /**
    * 获得最大上传文件大小,缺省为5M
    * @return
    */
    public static int getMaxUploadSize()
    {
        int maxUploadSize = SysConfig.getInt("system.maxUploadSize");
        return maxUploadSize == 0 ? 5242880 : maxUploadSize;
    }
    
    /**
    * 返回每页显示的行数,缺省为20
    * @return
    */
    public static int getRowOfPage()
    {
        int rowOfPage = SysConfig.getInt("system.rowOfPage");
        return rowOfPage == 0 ? 20 : rowOfPage;
    }
    
    /**
    * 获得字串配置信息
    * @param name 键值名称
    * @return 若不存在，则返回空字串
    */
    public static String getString(String name)
    {
        return itemMap.getString(name);
    }
    
    /**
    * 从数据库中读入配置文件
    */
    public static void loadConfig()
    {
        DataRow data = new DataRow();
        List<Config> configList = null;
        /**
        * 考虑到很多项目不能直接操作jdbc，这里增加一个数据来源的实现类
        *  add by 20131012
        */
        String configDataImpl = Configuration.getString("system.configDataImpl");
        if (StringHelper.isNotEmpty(configDataImpl))
        {
            Object object = ReflectHelper.objectForName(configDataImpl);
            if (object instanceof ConfigDataSource)
            {
                configList = ((ConfigDataSource) object).getAllSysConfig();
            }
        }
        else
        {
            ConfigService service = (ConfigService) ServiceLocator.getService(ConfigService.class);
            configList = service.getAllSysConfig();
        }
        if (configList != null)
        {
            for (Iterator<Config> iter = configList.iterator(); iter.hasNext();)
            {
                Config item = iter.next();
                String name = item.getName();
                String value = item.getValue();
                data.set(name, value);
            }
        }
        DataRow tempMap = itemMap;
        itemMap = data;
        tempMap.clear();
    }
    
    /**
    *   读取权限URL
    */
    public static void loadRight()
    {
        ConfigService service = (ConfigService) ServiceLocator.getService(ConfigService.class);
        rightUrl = (ArrayList<Right_Url>) service.loadRight();
    }
    
    /**
    * 设定配置信息
    * @param name  键值名称
    * @param value 具体的值
    */
    public static void set(String name, int value)
    {
        itemMap.set(name, value);
    }
    
    /**
    * 设定配置信息
    * @param name  键值名称
    * @param value 具体的值
    */
    public static void set(String name, String value)
    {
        itemMap.set(name, value);
    }
    
    private SysConfig()
    {
    }
}
