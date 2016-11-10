package com.yizhishang.plat.service;

import com.yizhishang.base.jdbc.DataRow;

/**
 * 描述: SeoService.java
 * 版权: Copyright (c) 2015
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2015-12-3
 * 创建时间: 下午11:07:08
 */
public interface SeoService
{
	
	/**
	 * 描述: 添加seo
	 * 作者: 袁永君
	 * 创建日期: 2015-12-3
	 * 创建时间: 下午11:07:17
	 * @param dataRow
	 */
	public void addSeo(DataRow dataRow);
	
	        /**
    * 
    * @描述：修改seo
    * @作者：袁永君
    * @时间：2015-11-14 下午02:13:46
    * @param dataRow
    */
	public void editSeo(DataRow dataRow);
	
	        /**
    * 
    * @描述：根据栏目ID获取seo信息
    * @作者：袁永君
    * @时间：2015-11-14 下午02:14:07
    * @param catalogId
    * @return
    */
	public DataRow findSeoByCatalogid(int catalogId, String siteNo);
	
}
