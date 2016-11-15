package com.yizhishang.base.dao;

import com.yizhishang.base.jdbc.JdbcTemplateUtil;
import com.yizhishang.base.util.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 返回数据操作对象
     *
     * @return 返回数据操作对象
     */
    public JdbcTemplateUtil getJdbcTemplateUtil()
    {
        return SpringContextHolder.getBean("jdbcTemplateUtil");
    }

}
