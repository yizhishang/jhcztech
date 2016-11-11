package com.yizhishang.plat.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.plat.dao.ArticleDao;
import com.yizhishang.plat.domain.Article;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-24
 * 创建时间: 12:30:44
 */
@Service
public class ArticleService extends BaseService
{
    
	@Autowired
	ArticleDao articleDao;
	
    /**
    * 添加文章
    * @param article
    */
    public int addArticle(Article article)
    {
        String id = getSeqValue("T_ARTICLE");
        article.setId(Integer.parseInt(id));
        article.setOrderline(Integer.parseInt(id));
        return articleDao.addArticle(article);
    }
    
    /**
    * 描述:  添加文章作者
    * 作者:	 285206405@qq.com
    * 创建日期: 2016-03-04
    * 创建时间: 上午11:11:13
    * @param data
    * @return
    */
    public void addAuthor(DynaModel data)
    {
        String id = getSeqValue("T_ARTICLE_AUTHOR");
        data.set("id", id);
        articleDao.addAuthor(data);
    }
    
    /**
    * 描述:  删除文章作者
    * 作者:	 285206405@qq.com
    * 创建日期: 2016-03-04
    * 创建时间: 下午03:20:51
    *
    * @param id
    * @return
    */
    public void delAuthor(String id)
    {
        articleDao.delAuthor(id);
    }
    
    /**
    * 删除文章
    * @param articleId
    */
    public void deleteArticle(int articleId)
    {
        articleDao.deleteArticle(articleId);
    }
    
    /**
    * 根据文章ID，查找文章
    * @param articleId
    * @return
    */
    public Article findArticleById(int articleId)
    {
        return articleDao.findArticleById(articleId);
    }
    
    /**
    * 描述:  根据站点编号查询所有作者
    * 作者:	 285206405@qq.com
    * 创建日期: 2016-03-04
    * 创建时间: 上午11:47:24
    * @param siteno
    * @return
    */
    public DBPage findAuthor(int curPage, int numPerPage, String siteno, String keyword)
    {
        return articleDao.findAuthor(curPage, numPerPage, siteno, keyword);
    }
    
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
    public List<DynaModel> findPublishArticleById(int catalogId, int rows, int sortType)
    {
        return articleDao.findPublishArticleById(catalogId, rows, sortType);
    }
    
    /**
    * 
    * @描述：查询当前栏目所有的子栏目，子栏目必须关联到文章
    * @作者：袁永君
    * @时间：2010-05-26 09:33:11
    * @param catalogId
    * @return
    */
    public List<DynaModel> findUnionArtilceByCatalog(int catalogId)
    {
        return articleDao.findUnionArtilceByCatalog(catalogId);
    }
    
    /**
    * 根据文章ID，查找文章的发布文件路径
    *
    * @param articleId
    * @return
    */
    public String findUrlById(int articleId)
    {
        ArrayList<Integer> argList = new ArrayList<Integer>();
        argList.add(new Integer(articleId));
        return getJdbcTemplateUtil().queryString("select url from T_ARTICLE where article_id=?", argList.toArray());
    }
    
    /**
    * 获得分页的数据
    *
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
            String endDate)
    {
        return articleDao.getPageData(curPage, numPerPage, siteNo, catalogId, title, state, author, startDate, endDate);
    }
    
    public DBPage getPageDataByEmail(int curPage, int numPerPage, String siteNo, int catalogId, String title, int state, String author, String branchno)
    {
        return articleDao.getPageDataByEmail(curPage, numPerPage, siteNo, catalogId, title, state, author, branchno);
    }
    
    /**
    * 描述:  根据作者名称查询作者信息
    * 作者:	 285206405@qq.com
    * 创建日期: 2016-03-04
    * 创建时间: 上午11:22:09
    * @param name
    * @return
    */
    public boolean isAuthorExist(String name)
    {
        return articleDao.isAuthorExist(name);
    }
    
    /**
    * 根据内容id，判断是否为最末节点
    * @param catalogId
    * @return
    */
    public boolean isEndCode(int catalogId)
    {
        return articleDao.isEndNode(catalogId);
    }
    
    /**
    * 根据标题，判断相同标题的文章是否存在
    * @param title
    * @return
    */
    public boolean isTitleExist(String title)
    {
        return articleDao.isTitleExist(title);
    }
    
    /**
    * 更新文章
    * @param article
    */
    public void updateArticle(Article article)
    {
        articleDao.updateArticle(article);
    }
    
    /**
     * 查询当前文章的上一条和下一条数据
     * @param article
     */
    public DynaModel findUpAndDown(String curArticleId, String catalog_id)
    {
    	return articleDao.findUpAndDown(curArticleId, catalog_id);
    }
}
