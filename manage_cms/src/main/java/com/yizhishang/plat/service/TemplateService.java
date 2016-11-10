package com.yizhishang.plat.service;

import java.util.List;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.plat.domain.Template;
import com.yizhishang.plat.template.TemplatePreview;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2010-1-18
 * 创建时间: 下午02:44:16
 */
public interface TemplateService
{
	
	public int addTemplate(Template template);
	
	                /**
    * @描述：发布任务到发布队列
    * @作者：袁永君
    * @时间：2010-04-02 21:01:22
    * @param id
    * @param flag A:发布文章 T:发布模板 C:发布栏目 CR:发布栏目及其所有子栏目(递归发布)
    * @param loginSiteNo 用户所登陆的站点
    * @param uid 当前后台登录用户的UID
    */
    void addToPublishQueue(int id, String flag, String loginSiteNo, String uid);
	
	                /**
    * 根据栏目ID，查找有效的详细细览模板，若当前栏目找不到，则需要往父目录继续寻找
    * @param catalogId
    * @return
    */
    List<Object> cycFindDetailedTemplateByCatalogId(int catalogId, int type);
	
    public void deleteTemplate(int id);
	
    public void editTemplate(Template template);
	
    /**
    * 
    * @描述：批量更新模板
    * @作者：袁永君
    * @时间：2011-3-3 下午05:15:22
    * @param dataList
    * @throws Exception
    */
    public void editTemplateByList(List<Object> dataList) throws Exception;
	
	                /**
    * 描述: findTemplate
    * 作者: 袁永君
    * 创建日期: 2015-12-6
    * 创建时间: 下午11:59:11
    * @param curPage
    * @param numPerPage
    * @param catalogId
    * @param keyword
    * @param state
    * @param startDate
    * @param endDate
    * @param siteNo
    * @return
    */
    public DBPage findTemplate(int curPage, int numPerPage, int catalogId, String keyword, int state, String startDate, String endDate, String siteNo);
	
	                /**
    * 
    * @描述：查询栏目下的所有模板
    * @作者：袁永君
    * @时间：2010-07-01 12:44:01
    * @param catalogId 
    * @return
    */
    public List<Object> findTemplate(int catalogId, String siteNo);
	
    public Template findTemplateById(int id);
	
    public Template findTemplateById(int id, String siteNo);
	
    /**
    * 
    * @描述：查询当前栏目和所有子栏目模板
    * @作者：袁永君
    * @时间：2011-3-3 下午01:34:09
    * @param route
    * @return
    */
    public List<Template> findTemplateByRoute(int route);
    
    /**
    * 查找某栏目下可用的某类型的模板,若有多个，则按建立时间的降序进行排序
    *
    * @param catalogId 栏目ID
    * @param type      模板类型  1:首页模板 2:信息列表 3:信息细览 4：其它模版
    * @return
    */
    public List<Object> findUsableTemplate(int catalogId, int type);
    
    /**
    * 查找某栏目下可用的某类型的模板,若有多个，则按建立时间的降序进行排序
    *
    * @param catalogId 栏目ID
    * @param type      模板类型  1:首页模板 2:信息列表 3:信息细览 4：其它模版
    * @param siteNo 	站点编号
    * @return
    */
    public List<Object> findUsableTemplate(int catalogId, int type, String siteNo);
    
    /**
    * 解析模板内容
    * @param type 0:根据文章ID预览模板
    *             1:根据栏目ID预览模板
    *             2:根据模板ID预览模板
    * @return
    */
    String parseTemplate(TemplatePreview templatePreview) throws Exception;
	
}
