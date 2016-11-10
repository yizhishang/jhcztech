package com.yizhishang.base.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhishang.base.dao.BaseDao;
import com.yizhishang.base.dao.ConfigDao;
import com.yizhishang.base.domain.Config;
import com.yizhishang.base.domain.Right_Url;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.jdbc.DataRow;
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
public class ConfigDaoImpl extends BaseDao implements ConfigDao
{
	
	private static String GET_ALL = "select * from T_SYS_CONFIG order by name";
	
	private static String GET_CONFIG_BY_ID = "select * from T_SYS_CONFIG where id = ?";
	
	private static String GET_CONFIG_BY_NAME = "select * from T_SYS_CONFIG where name = ?";
	
	private static String GET_RIGHT_URL = "select * from T_RIGHT_URL";
	
    @Override
    public void addConfig(Config config)
	{
		DataRow row = new DataRow();
		row.putAll(config.toMap());
		getJdbcTemplate().insert("T_SYS_CONFIG", row);
	}
	
	@Override
    public void deleteConfig(int id)
	{
		getJdbcTemplate().delete("T_SYS_CONFIG", "id", new Integer(id));
	}
	
	@Override
    public Config findConfigById(int id)
	{
		DataRow row = getJdbcTemplate().queryMap(GET_CONFIG_BY_ID, new Object[] { new Integer(id) });
		if (row != null)
		{
			Config config = new Config();
			config.fromMap(row);
			return config;
		}
		return null;
	}
	
	@Override
    public Config findConfigByName(String name)
	{
		try
		{
			DataRow row = getJdbcTemplate().queryMap(GET_CONFIG_BY_NAME, new Object[] { name });
			if (row != null)
			{
				Config config = new Config();
				config.fromMap(row);
				return config;
			}
		}
		catch (Exception ex)
		{
			
		}
		return null;
	}
	
	@Override
    public List<Config> getAllSysConfig()
	{
        List<Object> configList = getJdbcTemplate().query(GET_ALL);
        ArrayList<Config> newConfigList = new ArrayList<Config>();
        for (Iterator<Object> iter = configList.iterator(); iter.hasNext();)
		{
			Config config = new Config();
			DataRow row = (DataRow) iter.next();
			config.fromMap(row);
			newConfigList.add(config);
		}
		return newConfigList;
	}
	
	@Override
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
		page = getJdbcTemplate().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);
		
		if (page != null)
		{
            List<Object> dataList = page.getData();
            ArrayList<Object> newDataList = new ArrayList<Object>();
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
			{
				Config config = new Config();
				DataRow row = (DataRow) iter.next();
				config.fromMap(row);
				newDataList.add(config);
			}
			page.setData(newDataList);
		}
		
		return page;
	}
	
	@Override
    public List<Right_Url> loadRight()
	{
        List<Object> list = this.getJdbcTemplate().query(GET_RIGHT_URL);
        ArrayList<Right_Url> dateList = new ArrayList<Right_Url>();
        for (Iterator<Object> iter = list.iterator(); iter.hasNext();)
		{
			Right_Url rightUrl = new Right_Url();
			DataRow row = (DataRow) iter.next();
			rightUrl.fromMap(row);
			dateList.add(rightUrl);
		}
		return dateList;
	}
	
	@Override
    public void updateConfig(Config config)
	{
		DataRow row = new DataRow();
		row.putAll(config.toMap());
		getJdbcTemplate().update("T_SYS_CONFIG", row, "id", new Integer(config.getId()));
	}
}
