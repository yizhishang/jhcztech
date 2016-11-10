package com.yizhishang.base.dao;

import java.util.List;

import com.yizhishang.base.domain.Config;
import com.yizhishang.base.domain.Right_Url;
import com.yizhishang.base.jdbc.DBPage;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-2
 * 创建时间: 17:11:17
 */
public interface ConfigDao
{
	
	public void addConfig(Config config);
	
	public void deleteConfig(int id);
	
	public Config findConfigById(int id);
	
	public Config findConfigByName(String name);
	
    public List<Config> getAllSysConfig();
	
	public DBPage getPageData(int curPage, int numPerPage, String keyword);
	
	public List<Right_Url> loadRight();
	
    public void updateConfig(Config config);
}
