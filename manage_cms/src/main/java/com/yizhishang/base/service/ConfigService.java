package com.yizhishang.base.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yizhishang.base.dao.ConfigDao;
import com.yizhishang.base.domain.Config;
import com.yizhishang.base.domain.Right_Url;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.service.BaseService;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-2
 * 创建时间: 17:09:14
 */
@Service
public class ConfigService extends BaseService
{
	
	@Resource
	ConfigDao configDao;
	
	/**
	* 根据name，得到某一个配置的详细信息
	* @param id 配置的ID值
	* @return
	*/
	public Config findConfigByName(String name)
	{
		return configDao.findConfigByName(name);
	}
	
	/**
	* 获得分页数据
	*
	* @return
	*/
	public DBPage getPageData(int curPage, int numPerPage, String keyword)
	{
		
		return configDao.getPageData(curPage, numPerPage, keyword);
	}
	
	/**
	 * 添加新的配置信息
	 * @param config
	 * @return
	 */
	public void addConfig(Config config)
	{
		String id = getSequenceGenerator().getNextSequence("T_SYS_CONFIG");
		config.setId(Integer.parseInt(id));
		configDao.addConfig(config);
	}
	
	/**
	 * 删除配置信息
	 * @param id
	 */
	public void deleteConfig(int id)
	{
		configDao.deleteConfig(id);
	}
	
	/**
	 * 根据ID，得到某一个配置的详细信息
	 * @param id 配置的ID值
	 * @return
	 */
	public Config findConfigById(int id)
	{
		return configDao.findConfigById(id);
	}
	
	/**
	 * 获得所有的配置信息，返回列表中的每一项都是一个config
	 * @return
	 */
	public List<Config> getAllSysConfig()
	{
		return configDao.getAllSysConfig();
	}
	
	/**
	 * 获得分页数据
	 * @return
	 */
	public DBPage getPageData(int curPage, int numPerPage, String keyword, String siteNo)
	{
		return configDao.getPageData(curPage, numPerPage, keyword, siteNo);
	}
	
	/**
	 * 读取权限URL
	 * @return
	 */
	public List<Right_Url> loadRight()
	{
		return configDao.loadRight();
	}
	
	public List<Object> loadRight(String siteno)
	{
		return configDao.loadRight(siteno);
	}
	
	/**
	 * 更新配置信息
	 * @param config
	 * @return
	 */
	public void updateConfig(Config config)
	{
		configDao.updateConfig(config);
	}
}
