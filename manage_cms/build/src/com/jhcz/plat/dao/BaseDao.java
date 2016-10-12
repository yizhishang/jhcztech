package com.jhcz.plat.dao;

import com.jhcz.base.jdbc.JdbcTemplate;
import com.jhcz.base.jdbc.session.Session;
import com.jhcz.base.jdbc.session.SessionFactory;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-11
 * 创建时间: 13:47:25
 */
public class BaseDao
{
    
    /**
     * 获得数据库访问Session
     * @return
     */
    public Session getSession()
    {
        return SessionFactory.getSession();
    }
    
    /**
     * 返回根据datasource.xml文件中配置数据源ID，得到的会话对象
     * @param id 数据源ID
     * @return
     */
    public Session getSession(String id)
    {
        return SessionFactory.getSession(id);
    }
    
    /**
     * @return 返回数据操作对象
     */
    public JdbcTemplate getJdbcTemplate()
    {
        return new JdbcTemplate();
    }
    
    /**
     * 返回根据datasource.xml文件中配置的数据源ID，构造的数据操作对象
     * @param id 数据源的ID
     */
    public JdbcTemplate getJdbcTemplate(String id)
    {
        return new JdbcTemplate(id);
    }
    
    /**
     * 根据一个整型数组，返回带括号的以","分隔的sql语句字串
     * @param intArray
     * @return
     */
    public String getBracketSqlStr(int[] intArray)
    {
        int length = intArray.length;
        if (length == 0)
        {
            return "";
        }
        
        StringBuffer buffer = new StringBuffer();
        buffer.append("(");
        for (int i = 0; i < intArray.length; i++)
        {
            if (i == length - 1)
                buffer.append(String.valueOf(intArray[i]));
            else
                buffer.append(String.valueOf(intArray[i] + ","));
        }
        buffer.append(")");
        return buffer.toString();
    }
}
