package com.jhcz.plat.service;

import java.util.List;

import com.jhcz.base.jdbc.DataRow;

/**
 * 文件标题：CatalogService
 * 描述: 用于查询前台页面所需要的栏目信息
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君  lanlei@jhcz.com
 * 版本:	 1.0
 * 创建日期: 2015-10-28
 * 创建时间: 下午04:39:03
 */
public interface WebCatalogService
{
	        
    /**
    *描述：根据栏目id获取栏目信息
    *作者：袁永君
    *时间：2016-02-21 下午01:59:05
    * @param catalogId 栏目id
    * @return
    */
    public DataRow findCatalogById(String catalogId);
	                                                    
    /**
    *描述：根据栏目id查询子栏目信息
    *作者：袁永君
    *时间：2016-02-21 下午01:56:52
    * @param catalogId 栏目id
    * @return
    */
    public List<Object> findChildrenById(String catalogId);
    
    /**
     * 描述: 根据栏目id获取栏目信息
     * 作者: 袁永君
     * 创建日期: 2016-1-3
     * 创建时间: 上午3:50:54
     * @param catalogNo
     * @return
     */
    public DataRow findCatalogByNo(String catalogNo);
	                            
    /**
    * 查询子栏目信息
    * @param route    线路
    * @return
    */
    public List<Object> findRouteCatalogById(String route);
	                                    
    /**
    *描述：可以根据一个栏目的ID查出该栏目的信息，包括子栏目，子栏目在DataRow里面的key是"children"，值类型是一个List。该方法依赖于方法findRouteCatalogById 
    *作者：袁永君
    *时间：2016-02-24 下午03:44:56
    * @param catalogId 栏目ID
    * @return
    */
    public DataRow findWholeCatalogById(String catalogId);
	                                        
    /**
    *描述：通过正则表达式找出data的子栏目在栏目链表中的位置
    *作者：袁永君
    *时间：2016-02-24 下午03:57:25
    * @param data 待查是否存在子栏目的栏目
    * @param catalogs 所有相关栏目的集合
    * @return 返回-1表示不存在子栏目
    */
    public int getChildCatalogIndex(DataRow data, List<Object> catalogs);
	 
}
