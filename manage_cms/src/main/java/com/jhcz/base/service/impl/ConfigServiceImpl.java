package com.jhcz.base.service.impl;

import java.util.List;

import com.jhcz.base.dao.ConfigDao;
import com.jhcz.base.domain.Config;
import com.jhcz.base.domain.Right_Url;
import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.service.BaseService;
import com.jhcz.base.service.ConfigService;
import com.jhcz.base.service.ServiceLocator;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-2
 * 创建时间: 17:09:14
 */
public class ConfigServiceImpl extends BaseService implements ConfigService
{
	
    /**
    * 添加新的配置信息
    *
    * @param config
    * @return
    */
	@Override
    public void addConfig(Config config)
	{
		
		String id = getSeqValue("T_SYS_CONFIG");
		config.setId(Integer.parseInt(id));
		getConfigDao().addConfig(config);
	}
	
    /**
    * 删除配置信息
    *
    * @param id
    */
	@Override
    public void deleteConfig(int id)
	{
		
		getConfigDao().deleteConfig(id);
	}
	
    /**
    * 根据ID，得到某一个配置的详细信息
    *
    * @param id 配置的ID值
    * @return
    */
	@Override
    public Config findConfigById(int id)
	{
		
		return getConfigDao().findConfigById(id);
	}
	
    /**
    * 根据name，得到某一个配置的详细信息
    *
    * @param id 配置的ID值
    * @return
    */
	@Override
    public Config findConfigByName(String name)
	{
		
		return getConfigDao().findConfigByName(name);
	}
	
    /**
    * 获得所有的配置信息，返回列表中的每一项都是一个config
    *
    * @return
    */
	@Override
    public List<Config> getAllSysConfig()
	{
		
		return getConfigDao().getAllSysConfig();
	}
	
	public ConfigDao getConfigDao()
	{
		return (ConfigDao) ServiceLocator.getService(ConfigDao.class);
	}
	
    /**
    * 获得分页数据
    *
    * @return
    */
	@Override
    public DBPage getPageData(int curPage, int numPerPage, String keyword)
	{
		
		return getConfigDao().getPageData(curPage, numPerPage, keyword);
	}
	
    /**
    *
    * 读取权限URL
    * @return
    */
	@Override
    public List<Right_Url> loadRight()
	{
		
		return getConfigDao().loadRight();
	}
	
    /**
    * 更新配置信息
    * @param config
    * @return
    */
	@Override
    public void updateConfig(Config config)
	{
		
		getConfigDao().updateConfig(config);
	}
}
