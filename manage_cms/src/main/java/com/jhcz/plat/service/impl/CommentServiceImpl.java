package com.jhcz.plat.service.impl;

import org.springframework.stereotype.Service;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.service.BaseService;
import com.jhcz.plat.dao.CommentDao;
import com.jhcz.plat.domain.Review;
import com.jhcz.plat.service.CommentService;

@Service
public class CommentServiceImpl extends BaseService implements CommentService
{
    
    @Override
    public void addComment(Review review)
    {
        getCommentDao().addComment(review);
    }
    
    @Override
    public void deleteComment(int id)
    {
        getCommentDao().deleteComment(id);
    }
    
    @Override
    public Review findById(int id)
    {
        return getCommentDao().findCommentById(id);
    }
    
    @Override
    public CommentDao getCommentDao()
    {
        return (CommentDao) getDao(CommentDao.class);
    }
    
    @Override
    public DBPage getPageData(int curPage, int numPerPage, int elemType, int elemId, int clientId, String quoteId, int state, String source)
    {
        return getCommentDao().getPageData(curPage, numPerPage, elemType, elemId, clientId, quoteId, state, source);
    }
    
    @Override
    public void updateComment(Review review)
    {
        getCommentDao().updateComment(review);
    }
}
