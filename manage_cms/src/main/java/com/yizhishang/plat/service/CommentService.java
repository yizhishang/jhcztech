package com.yizhishang.plat.service;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.plat.dao.CommentDao;
import com.yizhishang.plat.domain.Review;

public interface CommentService
{
    
    public void addComment(Review review);
    
    public void deleteComment(int id);
    
    public Review findById(int id);
    
    public CommentDao getCommentDao();
    
    public DBPage getPageData(int curPage, int numPerPage, int elemType, int elemId, int clientId, String quoteId, int state, String source);
    
    public void updateComment(Review review);
    
}
