package com.yizhishang.plat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.plat.dao.CommentDao;
import com.yizhishang.plat.domain.Review;

@Service
public class CommentService extends BaseService
{
    @Autowired
	CommentDao commentDao;
	
    public void addComment(Review review)
    {
        commentDao.addComment(review);
    }
    
    public void deleteComment(int id)
    {
        commentDao.deleteComment(id);
    }
    
    public Review findById(int id)
    {
        return commentDao.findCommentById(id);
    }
    
    public DBPage getPageData(int curPage, int numPerPage, int elemType, int elemId, int clientId, String quoteId, int state, String source)
    {
        return commentDao.getPageData(curPage, numPerPage, elemType, elemId, clientId, quoteId, state, source);
    }
    
    public void updateComment(Review review)
    {
        commentDao.updateComment(review);
    }
}
