package com.yizhishang.plat.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.jdbc.DataRow;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.dao.BaseDao;
import com.yizhishang.plat.dao.GroupDao;
import com.yizhishang.plat.domain.Group;
import com.yizhishang.plat.domain.User;

/**
 * 描述: GroupDaoImpl.java
 * 版权: Copyright (c) 2015
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2015-10-27
 * 创建时间: 上午10:18:16
 */
@Repository
public class GroupDaoImpl extends BaseDao implements GroupDao
{
    
    private static String FIND_GROUP_BY_ID = "select * from T_GROUP where group_id=?";
    
    private static String FIND_GROUP_USER_BY_NANE = "select * from T_GROUP where name=?";
    
    private static String INSERT_GROUP_USER = "insert into T_GROUP_USER values(?,?,?,?)";
    
    private static String FIND_GROUP_USER_ALL = "select * from T_USER where id in (select user_id from T_GROUP_USER where group_id=? ) order by id desc";
    
    private static String DELETE_GROUP_USER = "delete from T_GROUP_USER where GROUP_ID=? and USER_ID=?";
    
    private static String DELETE_GROUP_ALL_USER = "delete from T_GROUP_USER where GROUP_ID=?";
    
    private static String DELETE_GROUP_USER_BY_GROUP = "delete from T_GROUP_USER where GROUP_ID=?";
    
    @Override
    public void addGroup(Group group)
    {
        DataRow dateRow = new DataRow();
        dateRow.putAll(group.toMap());
        getJdbcTemplate().insert("T_GROUP", dateRow);
    }
    
    @Override
    public void addGroupUser(int groupId, int userId, String siteno)
    {
        String id = BaseService.getSequenceGenerator().getNextSequence("T_GROUP_USER");
        int id_group_user = Integer.parseInt(id);
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(new Integer(id_group_user));
        list.add(new Integer(groupId));
        list.add(new Integer(userId));
        list.add(siteno);
        getJdbcTemplate().update(INSERT_GROUP_USER, list.toArray());
    }
    
    /**
    * 删除组
    * @param groupId
    */
    @Override
    public void deleteGroup(int groupId)
    {
        getJdbcTemplate().delete("T_GROUP", "GROUP_ID", new Integer(groupId));
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(new Integer(groupId));
        getJdbcTemplate().update(DELETE_GROUP_USER_BY_GROUP, list.toArray());
    }
    
    @Override
    public void deleteGroupRight(int groupId)
    {
        
    }
    
    /**
    * 删除该组下所有用户
    * @param groupId
    */
    @Override
    public void deleteGroupUser(int groupId)
    {
        getJdbcTemplate().update(DELETE_GROUP_ALL_USER, new Object[] { new Integer(groupId) });
    }
    
    @Override
    public void deleteGroupUser(int groupId, int userId)
    {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(new Integer(groupId));
        list.add(new Integer(userId));
        getJdbcTemplate().update(DELETE_GROUP_USER, list.toArray());
    }
    
    /**
    * 根据组名查找组
    */
    @Override
    public Group findGroupBygroupNo(String groupNo)
    {
        DataRow datarow = getJdbcTemplate().queryMap(FIND_GROUP_USER_BY_NANE, new Object[] { groupNo });
        if (datarow == null)
        {
            return null;
        }
        Group group = new Group();
        group.fromMap(datarow);
        return group;
    }
    
    @Override
    public Group findGroupById(int id)
    {
        DataRow datarow = getJdbcTemplate().queryMap(FIND_GROUP_BY_ID, new Object[] { new Integer(id) });
        if (datarow == null)
        {
            return null;
        }
        Group group = new Group();
        group.fromMap(datarow);
        return group;
    }
    
    /**
    * 返回该组下的所有用户
    */
    @Override
    public List<Object> getGroupUser(int groupId)
    {
        List<Object> list_gruop_user = getJdbcTemplate().query(FIND_GROUP_USER_ALL, new Object[] { new Integer(groupId) });
        ArrayList<Object> newDataList = new ArrayList<Object>();
        if (list_gruop_user != null)
        {
            for (Iterator<Object> iter = list_gruop_user.iterator(); iter.hasNext();)
            {
                DataRow row = (DataRow) iter.next();
                User user = new User();
                user.fromMap(row);
                newDataList.add(user);
            }
        }
        return newDataList;
    }
    
    /**
    * 列出该组下所有用户信息
    */
    @Override
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, int groupId, String keyword)
    {
        DBPage page = null;
        StringBuffer sqlBuffer = new StringBuffer();
        ArrayList<Object> argList = new ArrayList<Object>();
        sqlBuffer.append("select * from T_USER where id in (select user_id from T_GROUP_USER where group_id=? )");
        argList.add(new Integer(groupId));
        if (!StringHelper.isEmpty(keyword))
        {
            sqlBuffer.append(" and (uid2 like ? or name like ?) ");
            argList.add("%" + keyword + "%");
            argList.add("%" + keyword + "%");
        }
        
        sqlBuffer.append(" order by id desc ");
        
        page = getJdbcTemplate().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);
        if (page != null)
        {
            List<Object> dataList = page.getData();
            ArrayList<Object> newDataList = new ArrayList<Object>();
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
            {
                User user = new User();
                DataRow row = (DataRow) iter.next();
                user.fromMap(row);
                newDataList.add(user);
            }
            page.setData(newDataList);
        }
        return page;
    }
    
    @Override
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword)
    {
        DBPage page = null;
        StringBuffer sqlBuffer = new StringBuffer();
        ArrayList<Object> argList = new ArrayList<Object>();
        sqlBuffer.append("select * from T_GROUP where 1=1 ");
        if (!StringHelper.isEmpty(siteNo))
        {
            sqlBuffer.append(" and siteno = ? ");
            argList.add(siteNo);
        }
        if (!StringHelper.isEmpty(keyword))
        {
            sqlBuffer.append(" and (description like ? or name like ?) ");
            argList.add("%" + keyword + "%");
            argList.add("%" + keyword + "%");
        }
        sqlBuffer.append(" order by group_id desc ");
        page = getJdbcTemplate().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);
        
        if (page != null)
        {
            List<Object> dataList = page.getData();
            ArrayList<Object> newDataList = new ArrayList<Object>();
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
            {
                Group group = new Group();
                DataRow row = (DataRow) iter.next();
                group.fromMap(row);
                newDataList.add(group);
            }
            page.setData(newDataList);
        }
        return page;
    }
    
    @Override
    public void updateGroup(Group group)
    {
        DataRow dataRow = new DataRow();
        dataRow.putAll(group.toMap());
        getJdbcTemplate().update("T_GROUP", dataRow, "group_Id", new Integer(group.getGroup_Id()));
    }
    
}
