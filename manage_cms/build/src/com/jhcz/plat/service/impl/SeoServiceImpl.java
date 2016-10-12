package com.jhcz.plat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.service.BaseService;
import com.jhcz.plat.service.SeoService;

/**
 * 描述: SeoServiceImpl.java
 * 版权: Copyright (c) 2015
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2015-12-3
 * 创建时间: 下午10:48:44
 */
@Service
public class SeoServiceImpl extends BaseService implements SeoService
{
	
	@Override
    public void addSeo(DataRow dataRow)
	{
		String id = getSeqValue("T_CATALOG_SEO");
		dataRow.set("id", id);
		getJdbcTemplate().insert("T_CATALOG_SEO", dataRow);
	}
	
	@Override
    public void editSeo(DataRow dataRow)
	{
		getJdbcTemplate().update("T_CATALOG_SEO", dataRow, "id", dataRow.getString("id"));
	}
	
	@Override
    public DataRow findSeoByCatalogid(int catalogId, String siteNo)
	{
        List<Object> argList = new ArrayList<Object>();
		String sql = "SELECT * FROM T_CATALOG_SEO WHERE CATALOG_ID = ? AND SITENO = ?";
		argList.add(new Integer(catalogId));
		argList.add(siteNo);
		return getJdbcTemplate().queryMap(sql, argList.toArray());
	}
}
