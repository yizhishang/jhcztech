package com.yizhishang.base.service;

import java.sql.Connection;

import org.springframework.stereotype.Service;

import com.yizhishang.base.dao.factory.DAOFactory;
import com.yizhishang.base.jdbc.JdbcTemplate;
import com.yizhishang.base.jdbc.connection.ConnManager;
import com.yizhishang.base.jdbc.session.Session;
import com.yizhishang.base.jdbc.session.SessionFactory;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-28
 * 创建时间: 15:01:02
 */
@Service
public class BaseService
{
    
    /**
     * 根据datasource.xml文件中配置的缺省数据源，得到对应的连接对象
     * @param id 数据源ID
     * @return
     */
    public Connection getConnection()
    {
        return ConnManager.getConnection();
    }
    
    /**
     * 根据datasource.xml文件中配置的数据源ID，得到对应的连接对象
     * @param id 数据源ID
     * @return
     */
    public Connection getConnection(String id)
    {
        return ConnManager.getConnection(id);
    }
    
    /**
     * 获得相应的DAO对象
     * @param interfaceClass
     * @return
     */
    @SuppressWarnings("rawtypes")
    public Object getDao(Class interfaceClass)
    {
        return DAOFactory.getDao(interfaceClass);
    }
    
    /**
    * 返回缺省数据源的数据操作对象
    * @return
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
    * 获得数据表SEQUENCE产生器
    * @return
    */
    public static SequenceGenerator getSequenceGenerator()
    {
        return SequenceGenerator.getInstance();
    }
    
    /**
    * 根据Sequence的名称，得到sequence的值
    * @param seqName sequence的名称，对应到T_SEQUENCE表中的某一项的名称
    * @return
    */
    public String getSeqValue(String seqName)
    {
        return SequenceGenerator.getInstance().getNextSequence(seqName);
    }
    
    /**
    * 根据Sequence的名称，得到sequence的值
    * @param seqName sequence的名称，对应到T_SEQUENCE表中的某一项的名称
    * @return
    */
    public String getSeqValue(String id, String seqName)
    {
        return SequenceGenerator.getInstance().getNextSequence(id, seqName);
    }
    
    /**
     * 根据datasource.xml文件中配置的缺省数据源，得到对应的会话对象
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
}
