package com.yizhishang.plat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.jdbc.DataRow;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.StringHelper;

/**
 * 描述:
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2010-1-19
 * 创建时间: 10:23:26
 */
@Service
public class TemplateVarService extends BaseService
{
    
    /**
    * 
    * 描述:  添加模板变量  
    * 作者:	 
    * 创建日期: 2010-1-19
    * 创建时间: 下午03:39:56
    * @return void
    * @throws
    */
    public void addTemplateVar(DataRow data)
    {
        String id = getSeqValue("T_TEMPLATE_VAR");
        data.set("id", id);
        getJdbcTemplate().insert("T_TEMPLATE_VAR", data);
    }
    
    /**
    * 
    * 描述:  删除模板变量  
    * 作者:	 
    * 创建日期: 2010-1-19
    * 创建时间: 下午04:24:17
    * @return void
    * @throws
    */
    public void deleteTemplateVar(int id)
    {
        getJdbcTemplate().delete("T_TEMPLATE_VAR", "id", new Integer(id));
    }
    
    /**
    * 
    * 描述:  编辑模板变量  
    * 作者:	 
    * 创建日期: 2010-1-19
    * 创建时间: 下午04:13:18
    * @return void
    * @throws
    */
    public void editTemplateVar(DataRow data)
    {
        getJdbcTemplate().update("T_TEMPLATE_VAR", data, "id", data.getString("id"));
    }
    
    /**
    * 查找所有可用的模板变量
    * @return
    */
    public List<Object> findAllUsableItem()
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(new Integer(1));
        return getJdbcTemplate().query("select * from T_TEMPLATE_VAR where state=?", argList.toArray());
    }
    
    /**
    * 描述:  分页查询模板变量  
    * 作者:	 
    * 创建日期: 2010-1-19
    * 创建时间: 下午03:56:22
    * @return DBPage
    * @throws
    */
    public DBPage findTemplateVar(int curPage, int numPerPage, String keyword, String siteNo)
    {
        List<Object> argList = new ArrayList<Object>();
        StringBuffer strBuf = new StringBuffer("SELECT * FROM T_TEMPLATE_VAR WHERE 1 = 1");
        if (StringHelper.isNotEmpty(keyword))
        {
            strBuf.append(" AND ITEM_NAME LIKE ?");
            argList.add("%" + keyword + "%");
        }
        if (StringHelper.isNotEmpty(siteNo))
        {
            strBuf.append(" AND SITENO = ?");
            argList.add(siteNo);
        }
        strBuf.append(" ORDER BY ID DESC");
        return getJdbcTemplate().queryPage(strBuf.toString(), argList.toArray(), curPage, numPerPage);
    }
    
    /**
    * 
    * 描述:  根据ID查询模板变量  
    * 作者:	 
    * 创建日期: 2010-1-19
    * 创建时间: 下午04:06:55
    * @return DataRow
    * @throws
    */
    public DataRow findTemplateVarById(int id, String siteNo)
    {
        List<Object> argList = new ArrayList<Object>();
        String sql = "SELECT * FROM T_TEMPLATE_VAR WHERE ID = ? AND SITENO = ?";
        argList.add(new Integer(id));
        argList.add(siteNo);
        return getJdbcTemplate().queryMap(sql, argList.toArray());
    }
}
