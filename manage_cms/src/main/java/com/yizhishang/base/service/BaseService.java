package com.yizhishang.base.service;

import com.yizhishang.base.jdbc.JdbcTemplateUtil;
import com.yizhishang.base.util.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获得数据表SEQUENCE产生器
     *
     * @return
     */
    public static SequenceGenerator getSequenceGenerator()
    {
        return SequenceGenerator.getInstance();
    }

    /**
     * 返回缺省数据源的数据操作对象
     *
     * @return
     */
    public JdbcTemplateUtil getJdbcTemplateUtil()
    {
        return SpringContextHolder.getBean("jdbcTemplateUtil");
    }

    /**
     * 根据Sequence的名称，得到sequence的值
     *
     * @param seqName sequence的名称，对应到T_SEQUENCE表中的某一项的名称
     * @return
     */
    public String getSeqValue(String seqName)
    {
        return SequenceGenerator.getInstance().getNextSequence(seqName);
    }

    /**
     * 根据Sequence的名称，得到sequence的值
     *
     * @param seqName sequence的名称，对应到T_SEQUENCE表中的某一项的名称
     * @return
     */
    public String getSeqValue(String id, String seqName)
    {
        return SequenceGenerator.getInstance().getNextSequence(id, seqName);
    }

}
