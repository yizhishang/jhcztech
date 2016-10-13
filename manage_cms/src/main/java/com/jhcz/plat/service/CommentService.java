package com.jhcz.plat.service;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.plat.dao.CommentDao;
import com.jhcz.plat.domain.Review;

public interface CommentService
{
    
    public void addComment(Review review);
    
    public void deleteComment(int id);
    
    public Review findById(int id);
    
    public CommentDao getCommentDao();
    
    public DBPage getPageData(int curPage, int numPerPage, int elemType, int elemId, int clientId, String quoteId, int state, String source);
    
    public void updateComment(Review review);
    
}
