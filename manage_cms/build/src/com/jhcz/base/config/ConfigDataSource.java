package com.jhcz.base.config;

import java.util.List;

import com.jhcz.base.domain.Config;

/**
 * 描述: 
 * 版权: Copyright (c) 2012 
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0 
 * 创建日期: Oct 12, 2013 
 * 创建时间: 5:43:49 PM
 */
public interface ConfigDataSource
{
	
	public List<Config> getAllSysConfig();
}
