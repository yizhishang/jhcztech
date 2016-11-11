package com.yizhishang.base.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhishang.base.domain.Config;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.domain.Right_Url;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.util.StringHelper;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-2
 * 创建时间: 17:12:04
 */
@Repository
public class ConfigDao extends BaseDao
{
	
	private static String GET_ALL = "select * from T_SYS_CONFIG order by name";
	
	private static String GET_CONFIG_BY_ID = "select * from T_SYS_CONFIG where id = ?";
	
	private static String GET_CONFIG_BY_NAME = "select * from T_SYS_CONFIG where name = ?";
	
	private static String GET_RIGHT_URL = "select * from T_RIGHT_URL";
	
	public void addConfig(Config config)
	{
		DynaModel row = new DynaModel();
		row.putAll(config.toMap());
		getJdbcTemplateUtil().insert("T_SYS_CONFIG", row);
	}
	
	public List<Config> getAllSysConfig()
	{
        List<Config> configList = getJdbcTemplateUtil().queryForList(GET_ALL, Config.class);
		return configList;
	}
	
	public DBPage getPageData(int curPage, int numPerPage, String keyword, String siteNo)
	{
		
		DBPage page = null;
		
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from T_SYS_CONFIG where 1=1 ");
		ArrayList<Object> argList = new ArrayList<Object>();
		if (!StringHelper.isEmpty(keyword))
		{
			sqlBuffer.append(" and (name like ? or caption like ?) ");
			argList.add("%" + keyword + "%");
			argList.add("%" + keyword + "%");
		}
		if (StringHelper.isNotEmpty(siteNo))
		{
			sqlBuffer.append(" and siteno=?");
			argList.add(siteNo);
		}
		sqlBuffer.append(" order by name");
		page = getJdbcTemplateUtil().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);
		
		if (page != null)
		{
            List<DynaModel> dataList = page.getData();
			ArrayList<DynaModel> newDataList = new ArrayList<DynaModel>();
            for (Iterator<DynaModel> iter = dataList.iterator(); iter.hasNext();)
			{
				Config config = new Config();
				DynaModel row = iter.next();
				config.fromMap(row);
				newDataList.add(config);
			}
			page.setData(newDataList);
		}
		
		return page;
	}
	
	public List<DynaModel> loadRight(String siteno)
	{
		ArrayList<Object> argList = new ArrayList<Object>();
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("select * from T_RIGHT_URL where 1=1");
		if (StringHelper.isNotEmpty(siteno))
		{
			sqlBuf.append(" and siteno=?");
			argList.add(siteno);
		}
        List<DynaModel> list = this.getJdbcTemplateUtil().queryForList(sqlBuf.toString(), DynaModel.class, argList.toArray());
		return list;
	}
	
	public void updateConfig(Config config)
	{
		DynaModel row = new DynaModel();
		row.putAll(config.toMap());
		getJdbcTemplateUtil().update("T_SYS_CONFIG", row, "id", new Integer(config.getId()));
	}
	
    public void deleteConfig(int id)
	{
		getJdbcTemplateUtil().delete("T_SYS_CONFIG", "id", new Integer(id));
	}
	
    public Config findConfigById(int id)
	{
		return getJdbcTemplateUtil().queryMap(GET_CONFIG_BY_ID, Config.class, new Object[] { new Integer(id) });
	}
	
    public Config findConfigByName(String name)
	{
    	return getJdbcTemplateUtil().queryMap(GET_CONFIG_BY_NAME, Config.class, new Object[] { name });
	}
	
    public DBPage getPageData(int curPage, int numPerPage, String keyword)
	{
		
		DBPage page = null;
		
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from T_SYS_CONFIG where 1=1 ");
		ArrayList<Object> argList = new ArrayList<Object>();
		if (!StringHelper.isEmpty(keyword))
		{
			sqlBuffer.append(" and (name like ? or caption like ?) ");
			argList.add("%" + keyword + "%");
			argList.add("%" + keyword + "%");
		}
		sqlBuffer.append(" order by name");
		page = getJdbcTemplateUtil().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);
		
		if (page != null)
		{
            List<DynaModel> dataList = page.getData();
            ArrayList<DynaModel> newDataList = new ArrayList<DynaModel>();
            for (Iterator<DynaModel> iter = dataList.iterator(); iter.hasNext();)
			{
				Config config = new Config();
				DynaModel row = (DynaModel) iter.next();
				config.fromMap(row);
				newDataList.add(config);
			}
			page.setData(newDataList);
		}
		
		return page;
	}
	
    public List<Right_Url> loadRight()
	{
        List<Right_Url> list = this.getJdbcTemplateUtil().queryForList(GET_RIGHT_URL, Right_Url.class);
		return list;
	}
	
}
