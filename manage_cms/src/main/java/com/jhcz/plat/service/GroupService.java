package com.jhcz.plat.service;

import java.util.List;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.plat.domain.Group;

/**
 * @author Administrator
 */
public interface GroupService
{
    
    /**
    * 添加组
    *
    * @param group 组对象
    */
    public void addGroup(Group group);
    
    /**
    * 增加组用户
    *
    * @param groupId 组ID
    * @param userId  用户ID
    */
    public void addGroupUser(int groupId, int userId, String siteno);
    
    /**
    * 删除组
    *
    * @param id 组的ID
    */
    public void deleteGroup(int id);
    
    /**
    * 删除组权限
    *
    * @param groupId 组ID
    */
    public void deleteGroupRight(int groupId);
    
    /**
    * 删除组用户
    *
    * @param groupId 组ID
    */
    public void deleteGroupUser(int groupId);
    
    /**
    * 删除组用户
    *
    * @param groupId 组ID
    * @param userId  用户ID
    */
    public void deleteGroupUser(int groupId, int userId);
    
    /**
    * 根据组groupNo，查找相应的组
    *
    * @param groupNo 组groupNo
    * @return 若没有发现组，则返回null
    */
    public Group findGroupBygroupNo(String groupNo);
    
    /**
    * 根据组的ID，查找相应的组
    *
    * @param id 组的ID
    * @return 若没有发现组，则返回null
    */
    public Group findGroupById(int id);
    
    /**
    * 返回某组所有用户，返回的list中的每一个元素是一个User对象
    *
    * @param groupId 组ID
    * @return
    */
    public List<Object> getGroupUser(int groupId);
    
    /**
    * 以分页方式返回某组的用户
    *
    * @param curPage    当前第几页
    * @param numPerPage 每页多少条记录
    * @param siteNo     站点编号
    * @param groupId    组ID
    * @return
    */
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, int groupId, String keyword);
    
    /**
    * 以分页方式返回某站点的组
    * @param curPage    当前第几页
    * @param numPerPage 每页多少条记录
    * @param siteNo     站点编号
    * @param keyword    关键词
    * @return
    */
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword);
    
    /**
    * 根据组groupNo，判断组是否已经存在
    *
    * @param groupNo
    * @return
    */
    public boolean isGroupExist(String groupNo);
    
    /**
    * 更新组
    *
    * @param group 组对象
    */
    public void updateGroup(Group group);

}
