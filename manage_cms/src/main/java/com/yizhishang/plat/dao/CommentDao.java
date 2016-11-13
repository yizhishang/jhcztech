package com.yizhishang.plat.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhishang.base.dao.BaseDao;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.domain.Review;

@Repository
public class CommentDao extends BaseDao
{
    
    public void addComment(Review review)
    {
        String id = BaseService.getSequenceGenerator().getNextSequence("T_B_COMMENT");
        int pk = Integer.parseInt(id);
        review.setId(pk);
        DynaModel dr = new DynaModel();
        dr.putAll(review.toMap());
        this.getJdbcTemplateUtil().insert("T_B_COMMENT", dr);
    }
    
    public void deleteComment(int id)
    {
        this.getJdbcTemplateUtil().delete("T_B_COMMENT", "ID", new Integer(id));
    }
    
    /**
    * 按条件查询
    * @param Comment 查询条件
    * @return
    */
    public List<DynaModel> findComment(Review review)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        StringBuffer sqlBuffer = new StringBuffer();
        
        sqlBuffer.append("SELECT * FROM T_B_COMMENT WHERE 1=1");
        
        if (review.getElemType() != 0)
        {
            sqlBuffer.append(" and ELEM_TYPE = ? ");
            argList.add(review.getElemType());
        }
        if (review.getElemId() != 0)
        {
            sqlBuffer.append(" and ELEM_ID = ? ");
            argList.add(review.getElemId());
        }
        if (review.getClientId() != 0)
        {
            sqlBuffer.append(" and CLIENT_ID = ? ");
            argList.add(review.getClientId());
        }
        if (!StringHelper.isEmpty(review.getQuoteId()))
        {
            sqlBuffer.append(" and QUOTE_ID LIKE  ? ");
            argList.add("%" + review.getQuoteId() + "%");
        }
        if (review.getState() != 0)
        {
            sqlBuffer.append(" and STATE = ? ");
            argList.add(review.getState());
        }
        if (!StringHelper.isEmpty(review.getSource()))
        {
            sqlBuffer.append(" and SOURCE = ? ");
            argList.add("%" + review.getSource() + "%");
        }
        
        sqlBuffer.append(" ORDER BY NAME,ID DESC");
        return getJdbcTemplateUtil().queryForList(sqlBuffer.toString(), DynaModel.class, argList.toArray());
    }
    
    /**
    *
    * 通过ID查找评论
    * @param id 主键
    * @return
    */
    public Review findCommentById(int id)
    {
    	try
		{
			return getJdbcTemplateUtil().queryMap(" SELECT * FROM T_B_COMMENT WHERE ID = ? ", Review.class, new Object[] { new Integer(id) });
		}
		catch (SQLException e)
		{
			logger.error(e.getMessage());
			return null;
		}
    }
    
    /**
    * 分页查询
    * @param curPage
    * @param numPerPage
    * @param elemType 元素类型
    * @param elemId   元素ID
    * @param clientId 客户ID
    * @param quoteId  引用文章ID
    * @param state    状态
    * @param source   来源
    * @return
    */
    public DBPage<DynaModel> getPageData(int curPage, int numPerPage, int elemType, int elemId, int clientId, String quoteId, int state, String source)
    {
        DBPage<DynaModel> page = null;
        StringBuffer sqlBuffer = new StringBuffer();
        ArrayList<Object> argList = new ArrayList<Object>();
        sqlBuffer.append("select * from T_B_COMMENT where 1=1 ");
        if (elemType != 0)
        {
            sqlBuffer.append(" and ELEM_TYPE = ? ");
            argList.add(elemType);
        }
        if (elemId != 0)
        {
            sqlBuffer.append(" and ELEM_ID = ? ");
            argList.add(elemId);
        }
        if (clientId != 0)
        {
            sqlBuffer.append(" and CLIENT_ID = ? ");
            argList.add(clientId);
        }
        if (!StringHelper.isEmpty(quoteId))
        {
            sqlBuffer.append(" and QUOTE_ID LIKE  ? ");
            argList.add("%" + quoteId + "%");
        }
        if (state != 0)
        {
            sqlBuffer.append(" and STATE = ? ");
            argList.add(state);
        }
        if (!StringHelper.isEmpty(source))
        {
            sqlBuffer.append(" and SOURCE = ? ");
            argList.add("%" + source + "%");
        }
        
        sqlBuffer.append(" order by id desc ");
        page = getJdbcTemplateUtil().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);
        
        if (page != null)
        {
            List<DynaModel> dataList = page.getData();
            ArrayList<DynaModel> newDataList = new ArrayList<DynaModel>();
            for (Iterator<DynaModel> iter = dataList.iterator(); iter.hasNext();)
            {
                Review review = new Review();
                DynaModel row = (DynaModel) iter.next();
                review.fromMap(row);
                newDataList.add(review);
            }
            page.setData(newDataList);
        }
        return page;
    }
    
    public void updateComment(Review review)
    {
        DynaModel dr = new DynaModel();
        dr.putAll(review.toMap());
        this.getJdbcTemplateUtil().update("T_B_COMMENT", dr, "ID", review.getId());
    }
}
