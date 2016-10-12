package com.jhcz.plat.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jhcz.base.domain.Config;
import com.jhcz.base.domain.Right_Url;
import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.util.StringHelper;
import com.jhcz.plat.dao.BaseDao;
import com.jhcz.plat.dao.ConfigDao;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-2
 * 创建时间: 17:12:04
 */
@Repository(value = "configServiceDaoImpl")
public class ConfigDaoImpl extends BaseDao implements ConfigDao
{
	
	private static String GET_ALL = "select * from T_SYS_CONFIG order by name";
	
	private static String GET_CONFIG_BY_ID = "select * from T_SYS_CONFIG where id = ?";
	
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
    public List<Object> loadRight()
	{
        List<Object> list = this.getJdbcTemplate().query(GET_RIGHT_URL);
		ArrayList<Object> dateList = new ArrayList<Object>();
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
    public List<Object> loadRight(String siteno)
	{
		ArrayList<Object> argList = new ArrayList<Object>();
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("select * from T_RIGHT_URL where 1=1");
		if (StringHelper.isNotEmpty(siteno))
		{
			sqlBuf.append(" and siteno=?");
			argList.add(siteno);
		}
        List<Object> list = this.getJdbcTemplate().query(sqlBuf.toString(), argList.toArray());
		ArrayList<Object> dateList = new ArrayList<Object>();
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
