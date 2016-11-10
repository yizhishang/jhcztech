package com.yizhishang.plat.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.plat.dao.GroupDao;
import com.yizhishang.plat.domain.Group;
import com.yizhishang.plat.service.GroupService;

/**
 * 描述: GroupServiceImpl.java
 * 版权: Copyright (c) 2015
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2015-10-27
 * 创建时间: 上午10:18:50
 */
@Service
public class GroupServiceImpl extends BaseService implements GroupService
{
    
    @Resource
    GroupDao groupDao;
    
    @Override
    public void addGroup(Group group)
    {
        String id = getSequenceGenerator().getNextSequence("T_GROUP");
        group.setGroup_Id(Integer.parseInt(id));
        groupDao.addGroup(group);
    }
    
    @Override
    public void addGroupUser(int groupId, int userId, String siteno)
    {
        groupDao.addGroupUser(groupId, userId, siteno);
    }
    
    @Override
    public void deleteGroup(int id)
    {
        groupDao.deleteGroup(id);
    }
    
    @Override
    public void deleteGroupRight(int groupId)
    {
        
    }
    
    /**
    * 删除该组下所有用户
    *
    * @param groupId
    */
    @Override
    public void deleteGroupUser(int groupId)
    {
        groupDao.deleteGroupUser(groupId);
    }
    
    @Override
    public void deleteGroupUser(int groupId, int userId)
    {
        groupDao.deleteGroupUser(groupId, userId);
    }
    
    /**
    * 根据组名查找组
    */
    @Override
    public Group findGroupBygroupNo(String groupNo)
    {
        Group group = groupDao.findGroupBygroupNo(groupNo);
        if (group == null)
        {
            return null;
        }
        return group;
    }
    
    @Override
    public Group findGroupById(int id)
    {
        return groupDao.findGroupById(id);
    }
    
    /**
     * 获得该组下的所有用户
     */
    
    @Override
    public List<Object> getGroupUser(int groupId)
    {
        return groupDao.getGroupUser(groupId);
    }
    
    /**
    * 获取该组下的所有用户
    */
    @Override
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, int groupId, String keyword)
    {
        return groupDao.getPageData(curPage, numPerPage, siteNo, groupId, keyword);
    }
    
    @Override
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword)
    {
        return groupDao.getPageData(curPage, numPerPage, siteNo, keyword);
    }
    
    /**
     * 查看组是否存在
     */
    @Override
    public boolean isGroupExist(String groupNo)
    {
        Group group = findGroupBygroupNo(groupNo);
        if (group == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    @Override
    public void updateGroup(Group group)
    {
        groupDao.updateGroup(group);
    }
}
