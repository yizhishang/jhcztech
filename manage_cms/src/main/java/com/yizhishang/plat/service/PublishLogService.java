package com.yizhishang.plat.service;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.DateHelper;
import com.yizhishang.base.util.StringHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 描述:
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2010-1-18
 * 创建时间: 10:12:55
 */
@Service
public class PublishLogService extends BaseService
{

    /**
     * 添加一条日志信息
     *
     * @param log
     */
    public void add(DynaModel log)
    {
        log.set("id", getSeqValue("T_PUBLISH_LOG"));
        getJdbcTemplateUtil().insert("T_PUBLISH_LOG", log);
    }

    /**
     * 添加一条日志信息
     *
     * @param queueId
     * @param description
     */
    public void add(int queueId, String description)
    {
        DynaModel data = new DynaModel();
        data.set("id", getSeqValue("T_PUBLISH_LOG"));
        data.set("queue_id", queueId);
        data.set("description", description);
        data.set("create_by", "system");
        data.set("create_date", DateHelper.formatDate(new Date()));
        getJdbcTemplateUtil().insert("T_PUBLISH_LOG", data);
    }

    /**
     * 描述:  删除日志信息
     * 作者:
     * 创建日期: 2010-1-19
     * 创建时间: 上午09:45:59
     *
     * @return void
     * @throws
     */
    public void delete(int id)
    {
        getJdbcTemplateUtil().delete("T_PUBLISH_LOG", "id", new Integer(id));
    }

    /**
     * 删除所有日志
     */
    public void deleteAllLog()
    {
        getJdbcTemplateUtil().update("delete from T_PUBLISH_LOG");
    }

    /**
     * 查询日志信息
     */
    public DBPage<DynaModel> findPublishLog(int curPage, int numPerPage, String startDate, String endDate)
    {
        List<Object> argList = new ArrayList<Object>();
        StringBuffer strBuf = new StringBuffer("SELECT * FROM T_PUBLISH_LOG WHERE 1 = 1");
        if (StringHelper.isNotEmpty(startDate)) {
            strBuf.append(" AND CREATE_DATE >= ?");
            argList.add(startDate);
        }

        if (StringHelper.isNotEmpty(endDate)) {
            strBuf.append(" AND CREATE_DATE <= ?");
            argList.add(endDate);
        }
        strBuf.append(" ORDER BY ID DESC");
        return getJdbcTemplateUtil().queryPage(strBuf.toString(), argList.toArray(), curPage, numPerPage);
    }
}
