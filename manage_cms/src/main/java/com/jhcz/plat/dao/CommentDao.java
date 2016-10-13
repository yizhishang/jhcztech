package com.jhcz.plat.dao;

import java.util.List;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.plat.domain.Review;

public interface CommentDao
{

    public void addComment(Review review);

    public void deleteComment(int id);
    
    /**
    * 按条件查询
    * @param review 查询条件
    * @return
    */
    public List<Object> findComment(Review review);
    
    /**
    *
    * 通过ID查找评论
    * @param id 主键
    * @return
    */
    public Review findCommentById(int id);
    
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
    public DBPage getPageData(int curPage, int numPerPage, int elemType, int elemId, int clientId, String quoteId, int state, String source);
    
    public void updateComment(Review review);

}
