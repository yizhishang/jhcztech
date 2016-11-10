package com.yizhishang.plat.dao;

import java.util.List;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.plat.domain.Group;

/**
 * @author Administrator
 */
public interface GroupDao
{
    
    public void addGroup(Group group);
    
    public void addGroupUser(int groupId, int userId, String siteno);
    
    public void deleteGroup(int id);
    
    public void deleteGroupRight(int groupId);
    
    public void deleteGroupUser(int groupId);
    
    public void deleteGroupUser(int groupId, int userId);
    
    public Group findGroupBygroupNo(String groupNo);
    
    public Group findGroupById(int id);
    
    public List<Object> getGroupUser(int groupId);
    
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, int groupId, String keyword);
    
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword);
    
    public void updateGroup(Group group);
    
}
