package com.jhcz.plat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.service.BaseService;
import com.jhcz.plat.dao.ArticleDao;
import com.jhcz.plat.domain.Article;
import com.jhcz.plat.service.ArticleService;

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
public class ArticleServiceImpl extends BaseService implements ArticleService
{
    
    /**
    * 添加文章
    * @param article
    */
    @Override
    public int addArticle(Article article)
    {
        String id = getSeqValue("T_ARTICLE");
        article.setId(Integer.parseInt(id));
        article.setOrderline(Integer.parseInt(id));
        ArticleDao articleDao = (ArticleDao) getDao(ArticleDao.class);
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
    @Override
    public void addAuthor(DataRow data)
    {
        ArticleDao articleDao = (ArticleDao) getDao(ArticleDao.class);
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
    @Override
    public void delAuthor(String id)
    {
        ArticleDao articleDao = (ArticleDao) getDao(ArticleDao.class);
        articleDao.delAuthor(id);
    }
    
    /**
    * 删除文章
    * @param articleId
    */
    @Override
    public void deleteArticle(int articleId)
    {
        ArticleDao articleDao = (ArticleDao) getDao(ArticleDao.class);
        articleDao.deleteArticle(articleId);
    }
    
    /**
    * 根据文章ID，查找文章
    * @param articleId
    * @return
    */
    @Override
    public Article findArticleById(int articleId)
    {
        ArticleDao articleDao = (ArticleDao) getDao(ArticleDao.class);
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
    @Override
    public DBPage findAuthor(int curPage, int numPerPage, String siteno, String keyword)
    {
        ArticleDao articleDao = (ArticleDao) getDao(ArticleDao.class);
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
    @Override
    public List<DataRow> findPublishArticleById(int catalogId, int rows, int sortType)
    {
        ArticleDao articleDao = (ArticleDao) getDao(ArticleDao.class);
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
    @Override
    public List<Object> findUnionArtilceByCatalog(int catalogId)
    {
        ArticleDao articleDao = (ArticleDao) getDao(ArticleDao.class);
        return articleDao.findUnionArtilceByCatalog(catalogId);
    }
    
    /**
    * 根据文章ID，查找文章的发布文件路径
    *
    * @param articleId
    * @return
    */
    @Override
    public String findUrlById(int articleId)
    {
        ArrayList<Integer> argList = new ArrayList<Integer>();
        argList.add(new Integer(articleId));
        return getJdbcTemplate().queryString("select url from T_ARTICLE where article_id=?", argList.toArray());
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
    @Override
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, int catalogId, String title, int state, String author, String startDate,
            String endDate)
    {
        ArticleDao articleDao = (ArticleDao) getDao(ArticleDao.class);
        return articleDao.getPageData(curPage, numPerPage, siteNo, catalogId, title, state, author, startDate, endDate);
    }
    
    @Override
    public DBPage getPageDataByEmail(int curPage, int numPerPage, String siteNo, int catalogId, String title, int state, String author, String branchno)
    {
        ArticleDao articleDao = (ArticleDao) getDao(ArticleDao.class);
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
    @Override
    public boolean isAuthorExist(String name)
    {
        ArticleDao articleDao = (ArticleDao) getDao(ArticleDao.class);
        return articleDao.isAuthorExist(name);
    }
    
    /**
    * 根据内容id，判断是否为最末节点
    * @param catalogId
    * @return
    */
    @Override
    public boolean isEndCode(int catalogId)
    {
        ArticleDao articleDao = (ArticleDao) getDao(ArticleDao.class);
        return articleDao.isEndNode(catalogId);
    }
    
    /**
    * 根据标题，判断相同标题的文章是否存在
    * @param title
    * @return
    */
    @Override
    public boolean isTitleExist(String title)
    {
        ArticleDao articleDao = (ArticleDao) getDao(ArticleDao.class);
        return articleDao.isTitleExist(title);
    }
    
    /**
    * 更新文章
    * @param article
    */
    @Override
    public void updateArticle(Article article)
    {
        ArticleDao articleDao = (ArticleDao) getDao(ArticleDao.class);
        articleDao.updateArticle(article);
    }
    
    /**
     * 查询当前文章的上一条和下一条数据
     * @param article
     */
    @Override
    public DataRow findUpAndDown(String curArticleId, String catalog_id)
    {
    	ArticleDao articleDao = (ArticleDao) getDao(ArticleDao.class);
    	return articleDao.findUpAndDown(curArticleId, catalog_id);
    }
}
