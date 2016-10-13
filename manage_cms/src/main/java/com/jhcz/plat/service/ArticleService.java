package com.jhcz.plat.service;

import java.util.List;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.plat.domain.Article;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-24
 * 创建时间: 12:30:23
 */
public interface ArticleService
{
    
    /**
    * 添加文章
    * @param article
    */
    public int addArticle(Article article);
    
    /**
    * 描述:  添加文章作者
    * 作者:	 285206405@qq.com
    * 创建日期: 2015-7-2
    * 创建时间: 上午11:11:54
    *
    * @param data
    * @return
    */
    public void addAuthor(DataRow data);
    
    /**
    * 描述:  删除文章作者
    * 作者:	 285206405@qq.com
    * 创建日期: 2015-7-2
    * 创建时间: 下午03:21:19
    *
    * @param id
    * @return
    */
    public void delAuthor(String id);
    
    /**
    * 删除文章
    *
    * @param articleId
    */
    public void deleteArticle(int articleId);
    
    /**
    * 根据文章ID，查找文章
    *
    * @param articleId
    * @return
    */
    public Article findArticleById(int articleId);
    
    /**
    * 描述:  根据站点编号查询所有作者
    * 作者:	 285206405@qq.com
    * 创建日期: 2015-7-2
    * 创建时间: 上午11:47:58
    *
    * @param siteno
    * @return
    */
    public DBPage findAuthor(int curPage, int numPerPage, String siteno, String keyword);
    
    /**
    * 
    * @描述：查询当前栏目下已发布的文章ID
    * @作者：袁永君
    * @时间：2010-05-18 18:12:45
    * @param catalogId 传0则视为查询所有已发布的文章
    * @param rows
    * @param sortType : 0--is_head + is_new + is_host + publish_date
    * 					1--publish_date
    * @return
    */
    public List<DataRow> findPublishArticleById(int catalogId, int rows, int sortType);
    
    /**
    * 
    * @描述：查询当前栏目所有的子栏目，子栏目必须关联到文章
    * @作者：袁永君
    * @时间：2010-05-26 09:33:11
    * @param catalogId
    * @return
    */
    public List<Object> findUnionArtilceByCatalog(int catalogId);
    
    /**
    * 根据文章ID，查找文章的发布文件路径
    * @param articleId
    * @return
    */
    public String findUrlById(int articleId);
    
    /**
    * 获得分页的数据
    * @param curPage    当前第几页
    * @param numPerPage 每页多少条记录
    * @param siteNo
    * @param catalogId
    * @param title
    * @param state
    * @param author
    * @return
    */
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, int catalogId, String title, int state, String author, String startDate,
            String endDate);
    
    public DBPage getPageDataByEmail(int curPage, int numPerPage, String siteNo, int catalogId, String title, int state, String author, String branchno);
    
    /**
    * 描述:  根据作者名称查询作者信息
    * 作者:	 285206405@qq.com
    * 创建日期: 2015-7-2
    * 创建时间: 上午11:22:09
    * @param name
    * @return
    */
    public boolean isAuthorExist(String name);
    
    /**
    * 根据内容id，判断是否是最末节点
    * @param catalogId
    * @return
    */
    public boolean isEndCode(int catalogId);
    
    /**
    * 根据标题，判断文章是否存在
    * @param title
    * @return
    */
    public boolean isTitleExist(String title);
    
    /**
    * 更新文章
    * @param article
    */
    public void updateArticle(Article article);

    /**
     * 描述: 查询当前文章的上一条和下一条数据
     * 作者: 袁永君
     * 创建日期: 2016-3-17
     * 创建时间: 下午5:09:09
     * @param curArticleId
     * @param catalog_id
     * @return
     */
	public DataRow findUpAndDown(String curArticleId, String catalog_id);
    
}
