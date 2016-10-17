package com.jhcz.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jhcz.base.pojo.ManageCatalog;
import com.jhcz.base.util.StringHelper;
import com.jhcz.web.dao.ManageCatalogDao;
import com.jhcz.web.service.ManageCatalogService;

@Service
public class ManageCatalogServiceImpl implements ManageCatalogService
{
	
	@Resource
	ManageCatalogDao manageCatalogDao;
	
	@Override
	public List<ManageCatalog> queryFunctionCatalogByParentId(String parentId)
	{
		if(StringHelper.isEmpty(parentId))
		{
			parentId = "1";
		}
		List<ManageCatalog> catalogs = manageCatalogDao.queryFunctionCatalogByParentId(parentId);
		
		return catalogs;
	}
	
}
