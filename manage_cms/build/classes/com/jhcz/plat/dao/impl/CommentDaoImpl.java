package com.jhcz.plat.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.service.BaseService;
import com.jhcz.base.util.StringHelper;
import com.jhcz.plat.dao.BaseDao;
import com.jhcz.plat.dao.CommentDao;
import com.jhcz.plat.domain.Review;

@Service
public class CommentDaoImpl extends BaseDao implements CommentDao
{
    
    @Override
    public void addComment(Review review)
    {
        String id = BaseService.getSequenceGenerator().getNextSequence("T_B_COMMENT");
        int pk = Integer.parseInt(id);
        review.setId(pk);
        DataRow dr = new DataRow();
        dr.putAll(review.toMap());
        this.getJdbcTemplate().insert("T_B_COMMENT", dr);
    }
    
    @Override
    public void deleteComment(int id)
    {
        this.getJdbcTemplate().delete("T_B_COMMENT", "ID", new Integer(id));
    }
    
    /**
    * 按条件查询
    * @param Comment 查询条件
    * @return
    */
    @Override
    public List<Object> findComment(Review review)
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
        return getJdbcTemplate().query(sqlBuffer.toString(), argList.toArray());
    }
    
    /**
    *
    * 通过ID查找评论
    * @param id 主键
    * @return
    */
    @Override
    public Review findCommentById(int id)
    {
        DataRow dw = this.getJdbcTemplate().queryMap(" SELECT * FROM T_B_COMMENT WHERE ID = ? ", new Object[] { new Integer(id) });
        if (dw != null)
        {
            Review review = new Review();
            review.fromMap(dw);
            
            return review;
        }
        return null;
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
    @Override
    public DBPage getPageData(int curPage, int numPerPage, int elemType, int elemId, int clientId, String quoteId, int state, String source)
    {
        DBPage page = null;
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
        page = getJdbcTemplate().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);
        
        if (page != null)
        {
            List<Object> dataList = page.getData();
            ArrayList<Object> newDataList = new ArrayList<Object>();
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
            {
                Review review = new Review();
                DataRow row = (DataRow) iter.next();
                review.fromMap(row);
                newDataList.add(review);
            }
            page.setData(newDataList);
        }
        return page;
    }
    
    @Override
    public void updateComment(Review review)
    {
        DataRow dr = new DataRow();
        dr.putAll(review.toMap());
        this.getJdbcTemplate().update("T_B_COMMENT", dr, "ID", review.getId());
    }
}
