package com.jhcz.base.jdbc.session;

import java.sql.Connection;

import com.jhcz.base.jdbc.connection.ConnManager;
import com.jhcz.base.jdbc.session.Impl.SessionImpl;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-5
 * 创建时间: 16:24:45
 */
public final class SessionFactory
{
    
    /**
     * 根据datasource.xml文件中配置数据源ID，得到对应的会话对象
     * @param id 数据源ID
     * @return
     */
    public static Session getSession(String id)
    {
        Connection conn = ConnManager.getConnection(id);
        return new SessionImpl(conn);
    }
    
    /**
     * 获得缺省的数据源会话对象
     * @return
     */
    public static Session getSession()
    {
        Connection conn = ConnManager.getConnection();
        return new SessionImpl(conn);
    }
}
