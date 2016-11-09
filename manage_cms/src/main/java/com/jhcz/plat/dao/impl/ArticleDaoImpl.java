package com.jhcz.plat.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jhcz.base.config.Configuration;
import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.jdbc.session.Session;
import com.jhcz.base.util.StringHelper;
import com.jhcz.plat.dao.ArticleDao;
import com.jhcz.plat.dao.BaseDao;
import com.jhcz.plat.domain.Article;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-24
 * 创建时间: 11:19:19
 */
@Service
public class ArticleDaoImpl extends BaseDao implements ArticleDao
{
    
    private static Logger logger = LoggerFactory.getLogger(ArticleDaoImpl.class);
    
    @Override
    public int addArticle(Article article)
    {
        Session session = null;
        try
        {
            session = getSession();
            session.beginTrans();
            
            //添加文章基本信息
            DataRow dataRow = new DataRow();
            dataRow.putAll(article.toMap());
            dataRow.remove("content");
            session.insert("T_ARTICLE", dataRow);
            
            //添加文章内容信息
            DataRow contentRow = new DataRow();
            if (1 == Configuration.getInt("system.isAutoIncrement"))
            {
                contentRow.set("article_id", session.getGeneratedKeys());
                article.setId(new Integer(session.getGeneratedKeys()).intValue());
            }
            else
            {
                contentRow.set("article_id", article.getId());
            }
            contentRow.set("content", article.getContent());
            session.insert("T_ARTICLE_CONTENT", contentRow);
            
            session.commitTrans();
            return article.getId();
        }
        catch (Exception ex)
        {
            if (session != null)
            {
                session.rollbackTrans();
            }
            logger.error(ex.getMessage());
            throw new RuntimeException(ex);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
    }
    
    /**
     * 
     * 描述:  添加文章作者  
     * 作者:	 
     * 创建日期: 2009-7-2
     * 创建时间: 上午11:08:54
     * @param data 
     * @return
     */
    @Override
    public void addAuthor(DataRow data)
    {
        try
        {
            getJdbcTemplate().insert("T_ARTICLE_AUTHOR", data);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
        }
    }
    
    /**
     * 
     * 描述:  删除文章作者  
     * 作者:	 
     * 创建日期: 2009-7-2
     * 创建时间: 下午03:19:23
     * @param id 
     * @return
     */
    @Override
    public void delAuthor(String id)
    {
        try
        {
            getJdbcTemplate().delete("T_ARTICLE_AUTHOR", "id", id);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
        }
    }
    
    @Override
    public void deleteArticle(int articleId)
    {
        Session session = null;
        try
        {
            session = getSession();
            session.beginTrans();
            session.delete("T_ARTICLE", "article_id", new Integer(articleId));
            session.delete("T_ARTICLE_CONTENT", "article_id", new Integer(articleId));
            session.delete("T_ARTICLE_EXTEND_FIELD", "article_id", new Integer(articleId));//删除扩展字段
            session.commitTrans();
        }
        catch (Exception ex)
        {
            if (session != null)
            {
                session.rollbackTrans();
            }
            throw new RuntimeException(ex);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
    }
    
    @Override
    public Article findArticleById(int articleId)
    {
        String sql = "select * from T_ARTICLE where article_id=?";
        DataRow dataRow = getJdbcTemplate().queryMap(sql, new Object[] { new Integer(articleId) });
        if (dataRow == null)
        {
            return null;
        }
        
        sql = "select content from T_ARTICLE_CONTENT where article_id=?";
        String content = getJdbcTemplate().queryString(sql, new Object[] { new Integer(articleId) });
        Article article = new Article();
        article.fromMap(dataRow);
        article.setContent(content);
        
        return article;
    }
    
    /**
     * 
     * 描述:  根据站点编号查询所有作者  
     * 作者:	 
     * 创建日期: 2009-7-2
     * 创建时间: 上午11:46:25
     * @param siteno
     * @return 
     * @return
     */
    @Override
    public DBPage findAuthor(int curPage, int numPerPage, String siteno, String keyword)
    {
        try
        {
            List<String> argList = new ArrayList<String>();
            StringBuffer sql = new StringBuffer("SELECT * FROM T_ARTICLE_AUTHOR WHERE SITENO = ? ");
            argList.add(siteno);
            if (StringHelper.isNotBlank(keyword))
            {
                sql.append(" AND NAME LIKE ?");
                argList.add("%" + keyword + "%");
            }
            sql.append(" ORDER BY NAME");
            return getJdbcTemplate().queryPage(sql.toString(), argList.toArray(), curPage, numPerPage);
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return null;
        }
    }
    
    /**
     * @描述：查询当前栏目下已发布的文章信息
     * @作者：袁永君
     * @时间：2010-05-18 18:02:08
     * @param catalogId 传0则视为查询所有已发布的文章
     * @return
     */
    @Override
    public List findPublishArticleById(int catalogId, int rows, int sortType)
    {
        try
        {
            List<Object> argList = new ArrayList<Object>();
            StringBuffer sql = new StringBuffer("SELECT T1.CATALOG_ID, T1.ARTICLE_ID,T1.TITLE,T1.BRIEF,T1.PUBLISH_DATE,T1.URL, T1.SOURCE,T2.CONTENT FROM T_ARTICLE T1 LEFT JOIN T_ARTICLE_CONTENT T2 ON T1.ARTICLE_ID = T2.ARTICLE_ID WHERE T1.STATE = 3");
            if(catalogId > 0)
            {
            	sql.append(" AND T1.CATALOG_ID = ?");
            	argList.add(new Integer(catalogId));
            }
            if(sortType == 0)
            {
            	sql.append(" ORDER BY T1.IS_HEAD DESC,T1.IS_NEW DESC,T1.IS_HOT DESC,T1.ARTICLE_ID DESC");
            }else if(sortType == 1){
            	sql.append(" ORDER BY T1.PUBLISH_DATE DESC");
            }
            return getJdbcTemplate().query(sql.toString(), argList.toArray(), rows);
        }
        catch (Exception ex)
        {
            logger.error("", ex);
            return null;
        }
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
        try
        {
            List<String> argList = new ArrayList<String>();
            String sql = "SELECT C.CATALOG_ID FROM T_ARTICLE A,T_CATALOG C WHERE A.CATALOG_ID = C.CATALOG_ID AND C.ROUTE LIKE ? GROUP BY C.CATALOG_ID";
            argList.add("%" + catalogId + "%");
            return getJdbcTemplate().query(sql, argList.toArray());
        }
        catch (Exception ex)
        {
            logger.error("", ex);
            return null;
        }
    }
    
    @Override
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, int catalogId, String title, int state, String author, String startDate,
            String endDate)
    {
        DBPage page = null;
        
        StringBuffer sqlBuffer = new StringBuffer();
        ArrayList<Object> argList = new ArrayList<Object>();
        sqlBuffer.append("select * from T_ARTICLE where 1=1 ");
        if (catalogId > 0)
        {
            sqlBuffer.append(" and catalog_id = ?");
            argList.add(new Integer(catalogId));
        }
        if (!StringHelper.isEmpty(siteNo))
        {
            sqlBuffer.append(" and siteno = ? ");
            argList.add(siteNo);
        }
        if (!StringHelper.isEmpty(title))
        {
            sqlBuffer.append(" and title like ? ");
            argList.add("%" + title + "%");
        }
        if (state >= 0)
        {
            sqlBuffer.append(" and state= ? ");
            argList.add(new Integer(state));
        }
        
        if (!StringHelper.isEmpty(author))
        {
            sqlBuffer.append(" and author like ? ");
            argList.add("%" + author + "%");
        }
        
        if (StringHelper.isNotEmpty(startDate))
        {
            sqlBuffer.append(" and create_date >= ?");
            argList.add(startDate);
        }
        
        if (StringHelper.isNotEmpty(endDate))
        {
            sqlBuffer.append(" and create_date <= ?");
            argList.add(endDate);
        }
        
        sqlBuffer.append(" order by is_head desc,create_date desc,article_id desc ");
        page = getJdbcTemplate().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);
        
        if (page != null)
        {
            List<Object> dataList = page.getData();
            List<Object> newDataList = new ArrayList<Object>();
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
            {
                Article article = new Article();
                DataRow row = (DataRow) iter.next();
                article.fromMap(row);
                newDataList.add(article);
            }
            page.setData(newDataList);
        }
        
        return page;
    }
    
    @Override
    public DBPage getPageDataByEmail(int curPage, int numPerPage, String siteNo, int catalogId, String title, int state, String author, String branchno)
    {
        DBPage page = null;
        
        StringBuffer sqlBuffer = new StringBuffer();
        ArrayList<Object> argList = new ArrayList<Object>();
        sqlBuffer.append("select a.*,c.branchname from T_ARTICLE A,T_USER B,T_B_BRANCH C WHERE A.CREATE_BY = B.UID2 AND B.BRANCHNO = C.BRANCHNO");
        if (!StringHelper.isEmpty(siteNo))
        {
            sqlBuffer.append(" and a.siteno = ? ");
            argList.add(siteNo);
        }
        if (catalogId > 0)
        {
            sqlBuffer.append(" and a.catalog_id =?");
            argList.add(new Integer(catalogId));
        }
        if (!StringHelper.isEmpty(title))
        {
            sqlBuffer.append(" and a.title like ? ");
            argList.add("%" + title + "%");
        }
        if (state >= 0)
        {
            sqlBuffer.append(" and a.state= ? ");
            argList.add(new Integer(state));
        }
        
        if (!StringHelper.isEmpty(author))
        {
            sqlBuffer.append(" and a.author like ? ");
            argList.add("%" + author + "%");
        }
        
        sqlBuffer.append(" and c.branchno = ?");
        argList.add(branchno);
        sqlBuffer.append(" order by a.article_id desc ");
        page = getJdbcTemplate().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);
        
        return page;
    }
    
    /**
     * 
     * 描述:  判断文章ID是否存在于文章内容表中  
     * 作者:	 
     * 创建日期: 2009-6-1
     * 创建时间: 下午07:06:16
     * @return
     */
    public boolean isArticleContent(int articleId)
    {
        try
        {
            List<Integer> argList = new ArrayList<Integer>();
            String sql = "SELECT ARTICLE_ID FROM T_ARTICLE_CONTENT WHERE ARTICLE_ID = ?";
            argList.add(new Integer(articleId));
            DataRow data = getJdbcTemplate().queryMap(sql, argList.toArray());
            if (data != null && StringHelper.isNotEmpty(data.getString("article_id")))
            {
                return true;
            }
            
            return false;
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return false;
        }
    }
    
    /**
     * 
     * 描述:  根据作者名称查询作者信息  
     * 作者:	 
     * 创建日期: 2009-7-2
     * 创建时间: 上午11:20:35
     * @param name
     * @return 
     * @return
     */
    @Override
    public boolean isAuthorExist(String name)
    {
        try
        {
            List<String> argList = new ArrayList<String>();
            String sql = "SELECT ID FROM T_ARTICLE_AUTHOR WHERE NAME = ?";
            argList.add(name);
            DataRow data = getJdbcTemplate().queryMap(sql, argList.toArray());
            return (data == null) ? false : true;
        }
        catch (Exception ex)
        {
            logger.error(ex.getMessage());
            return false;
        }
    }
    
    @Override
    public boolean isEndNode(int catalogId)
    {
        String sql = "select * from T_Catalog where parent_id=?";
        DataRow dataRow = getJdbcTemplate().queryMap(sql, new Object[] { new Integer(catalogId) });
        return (dataRow == null);
    }
    
    @Override
    public boolean isTitleExist(String title)
    {
        String sql = "select * from T_ARTICLE where title=?";
        DataRow dataRow = getJdbcTemplate().queryMap(sql, new Object[] { title });
        return (dataRow != null);
    }
    
    @Override
    public void updateArticle(Article article)
    {
        Session session = null;
        try
        {
            session = getSession();
            session.beginTrans();
            
            DataRow dataRow = new DataRow();
            dataRow.putAll(article.toMap());
            
            //若设了文章的内容字段，则需要先更新内容字段
            if (dataRow.containsKey("content"))
            {
                DataRow contentRow = new DataRow();
                contentRow.set("content", article.getContent());
                //判断文章ID是否存在于文章内容表中，如果有则修改，没有就增加  2009-6-1 modify
                if (isArticleContent(dataRow.getInt("article_id")))
                {
                    session.update("T_ARTICLE_CONTENT", contentRow, "article_id", new Integer(article.getId()));
                }
                else
                {
                    contentRow.set("article_id", dataRow.getInt("article_id"));
                    session.insert("T_ARTICLE_CONTENT", contentRow);
                }
            }
            
            dataRow.remove("content");
            session.update("T_ARTICLE", dataRow, "article_id", new Integer(article.getId()));
            session.commitTrans();
        }
        catch (Exception ex)
        {
            if (session != null)
            {
                session.rollbackTrans();
            }
            throw new RuntimeException(ex);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
    }
    
	/**
	 * 查询当前文章的上一条和下一条数据
	 * @return
	 */
    @Override
	public DataRow findUpAndDown(String curArticleId,String catalog_id)
	{
		String sql ="SELECT * FROM (SELECT ARTICLE_ID, LEAD(ARTICLE_ID, 1, 0) OVER(ORDER BY PUBLISH_DATE DESC) AS DOWN_ID, LAG(ARTICLE_ID, 1, 0) OVER(ORDER BY PUBLISH_DATE DESC) AS UP_ID FROM T_ARTICLE WHERE CATALOG_ID = ? AND STATE = 3) WHERE ARTICLE_ID = ?";
		DataRow row = getJdbcTemplate().queryMap(sql, new Object[]{catalog_id,curArticleId});
		DataRow temp = new DataRow();
		sql = "select title,url from t_article where article_id = ?";
		DataRow down  = getJdbcTemplate().queryMap(sql, new Object[]{row.getString("down_id")});
		temp.set("downTitle", row.getString("down_id").equals("0")?"没有了":down.getString("title"));
		temp.set("downUrl", row.getString("down_id").equals("0")?"javascript:":down.getString("url"));
		DataRow up  = getJdbcTemplate().queryMap(sql, new Object[]{row.getString("up_id")});
		temp.set("upTitle", row.getString("up_id").equals("0")?"没有了":up.getString("title"));
		temp.set("upUrl", row.getString("up_id").equals("0")?"javascript:":up.getString("url"));
		return temp;
	}
}
