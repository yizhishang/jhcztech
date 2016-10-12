/*
 * Copyright (c) 2010 Your Corporation. All Rights Reserved.
 */

package com.jhcz.plat.service;

import java.util.List;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.jdbc.DataRow;

/**
 * 描述:
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2010-1-19
 * 创建时间: 10:19:30
 */
public interface TemplateVarService
{
    
    /**
    * 描述:  添加模板变量  
    * 作者:	 
    * 创建日期: 2010-1-19
    * 创建时间: 下午03:41:23
    * @return void
    * @throws
    */
    public void addTemplateVar(DataRow data);
    
    /**
    * 
    * 描述:  删除模板变量
    * 作者:	 
    * 创建日期: 2010-1-19
    * 创建时间: 下午04:24:34
    * @return void
    * @throws
    */
    public void deleteTemplateVar(int id);
    
    /**
    * 
    * 描述:  编辑模板变量
    * 作者:	 
    * 创建日期: 2010-1-19
    * 创建时间: 下午04:13:41
    * @return void
    * @throws
    */
    public void editTemplateVar(DataRow data);
    
    /**
    * 查找所有可用的模板变量
    * @return
    */
    public List<Object> findAllUsableItem();
    
    /**
    * 描述:  分页查询模板变量  
    * 作者:	 
    * 创建日期: 2010-1-19
    * 创建时间: 下午03:57:31
    * @return DBPage
    * @throws
    */
    public DBPage findTemplateVar(int curPage, int numPerPage, String keyword, String siteNo);
    
    /**
    * 描述:  根据ID查询模板变量  
    * 作者:	 
    * 创建日期: 2010-1-19
    * 创建时间: 下午04:07:17
    * @return DataRow
    * @throws
    */
    public DataRow findTemplateVarById(int id, String siteNo);
}
