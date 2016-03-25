package com.jhcz.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jhcz.cms.dao.SiteDao;
import com.jhcz.cms.model.Site;
import com.jhcz.cms.service.SiteService;

/**
 * 描述: SiteServiceImpl.java
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-3-25
 * 创建时间: 下午3:52:10
 */
@Service
public class SiteServiceImpl implements SiteService
{
	
	@Resource
	SiteDao siteDao;
	
	/**
	* 返回所有的站点列表，
	* @return 结果为一个List,其中每一个元素为一个Site对象
	*/
	@Override
	public List<Site> getAllSite()
	{
		return siteDao.getAllSite();
	}
	
}
