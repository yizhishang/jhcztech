package com.jhcz.plat.service;

import java.util.List;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.plat.domain.Article_Source;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-21
 * 创建时间: 10:36:35
 */
public interface Article_SourceService
{

    /**
     * 添加信息来源
     * @param article_source 组对象
     */
    public void addArticle_Source(Article_Source article_source);

    /**
     * 删除信息来源
     *
     * @param id 组的ID
     */
    public void deleteArticle_Source(int id);

    /**
     * 根据信息来源的ID，查找相应的信息来源
     *
     * @param id 信息来源的ID
     * @return 若没有发现信息来源，则返回null
     */
    public Article_Source findArticle_SourceById(int id);

    /**
     * 根据站点标识，查找该站点的所有信息来源
     * @param siteNo 站点编号
     * @return
     */
    public List<Object> findArticle_SourceBySiteNo(String siteNo);

    /**
     * 以分页方式返回某站点的信息来源
     *
     * @param curPage    当前第几页
     * @param numPerPage 每页多少条记录
     * @param siteNo     站点编号
     * @param keyword    关键词
     * @return
     */
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword);

    /**
     * 更新信息来源
     *
     * @param article_source 组对象
     */
    public void updateArticle_Source(Article_Source article_source);

}
