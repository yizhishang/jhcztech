package com.jhcz.web.service;

import java.util.List;

import com.jhcz.base.pojo.ManageCatalog;


public interface ManageCatalogService
{

	/**
	 * 描述: 根据父栏目id查询子栏目
	 * 作者: 袁永君
	 * 创建日期: 2016-3-1
	 * 创建时间: 下午3:17:00
	 * @param parentId
	 * @return
	 */
	public List<ManageCatalog> queryFunctionCatalogByParentId(String parentId);
	
}
