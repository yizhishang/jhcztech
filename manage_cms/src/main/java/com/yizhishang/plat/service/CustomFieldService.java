package com.yizhishang.plat.service;

import java.util.List;

import com.yizhishang.base.jdbc.DataRow;


/**
 * 描述: CustomFieldService.java
 * 版权: Copyright (c) 2015
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2015-12-3
 * 创建时间: 下午11:07:41
 */
public interface CustomFieldService
{
	
	                            /**
    * 
    * @描述：插入扩展字段信息
    * @作者：袁永君
    * @时间：2015-05-17 15:03:08
    * @param data
    */
	public boolean addExtendField(DataRow data);
	
	                            /**
    * 
    * @描述：添加扩展字段的详细值
    * @作者：袁永君
    * @时间：2015-05-17 17:33:44
    * @param data
    */
	public void addExtendFieldContent(DataRow data);
	
	                            /**
    * 根据栏目ID，查找当前栏目下的扩展字段信息，如果扩展字段为空，则向上找父栏目的扩展信息
    * @param catalogId
    * @return
    */
    public List<Object> cycFindExtendFieldInfo(int catalogId);
	
	                            /**
    * 
    * @描述：根据字段代码删除扩展字段
    * @作者：袁永君
    * @时间：2015-05-17 15:54:20
    * @param code
    */
	public boolean delExtendFieldById(int id);
	
	                            /**
    * 
    * @描述：修改扩展信息的详细值
    * @作者：袁永君
    * @时间：2015-05-17 19:20:01
    * @param data
    */
	public void editExtendFieldContent(DataRow data);
	
	                                /**
    * 
    * @描述：修改字段信息
    * @作者：袁永君
    * @时间：2015-05-17 16:35:57
    * @param data
    */
	public void editFieldInfo(DataRow data);
	
	                            /**
    * 
    * @描述：根据栏目ID查询当前栏目所有的代码
    * @作者：袁永君
    * @时间：2015-05-17 18:00:14
    * @param catalogId
    * @return
    */
	public String[] findExtendFieldCodeById(int catalogId);
	
	                            /**
    * 
    * @描述：根据文章ID查询扩展文章信息
    * @作者：袁永君
    * @时间：2011-3-21 下午09:55:39
    * @param articleId
    * @return
    */
	public DataRow findExtendFieldContent(int articleId);
	
	                            /**
    * 
    * @描述：查询扩展文章的内容
    * @作者：袁永君
    * @时间：2015-05-17 18:18:06
    * @param articleId
    * @param fileCodes 查询的字段，每个字段间用逗号分隔
    * @return
    */
	public DataRow findExtendFieldContent(int articleId, String fileCodes);
	
    /**
    * @描述：查询所有的扩展字段信息
    * @作者：袁永君
    * @时间：2015-05-17 14:53:32
    * @param catalogId
    * @return
    */
    public List<Object> findExtendFieldInfo(int catalogId);
	
    /**
    * 
    * @描述：根据字段代码查询字段信息
    * @作者：袁永君
    * @时间：2015-05-17 15:01:08
    * @param code
    * @return
    */
	public DataRow findExtendFieldInfoByCode(String code);
	
	                            /**
    * 
    * @描述：根据ID查询字段信息
    * @作者：袁永君
    * @时间：2015-05-17 16:34:02
    * @param id
    * @return
    */
	public DataRow findExtendFieldInfoById(int id);
	
	                            /**
    * 
    * @描述：判断文章扩展信息是否存在
    * @作者：袁永君
    * @时间：2015-05-17 19:17:51
    * @param articleId
    * @return
    */
	public boolean isExistsExtendFieldById(int articleId);
}
