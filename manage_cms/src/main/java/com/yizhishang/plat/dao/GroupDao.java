package com.yizhishang.plat.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhishang.base.dao.BaseDao;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.StringHelper;
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
public class GroupDao extends BaseDao
{
    
    private static String FIND_GROUP_BY_ID = "select * from T_GROUP where group_id=?";
    
    private static String FIND_GROUP_USER_BY_NANE = "select * from T_GROUP where name=?";
    
    private static String INSERT_GROUP_USER = "insert into T_GROUP_USER values(?,?,?,?)";
    
    private static String FIND_GROUP_USER_ALL = "select * from T_USER where id in (select user_id from T_GROUP_USER where group_id=? ) order by id desc";
    
    private static String DELETE_GROUP_USER = "delete from T_GROUP_USER where GROUP_ID=? and USER_ID=?";
    
    private static String DELETE_GROUP_ALL_USER = "delete from T_GROUP_USER where GROUP_ID=?";
    
    private static String DELETE_GROUP_USER_BY_GROUP = "delete from T_GROUP_USER where GROUP_ID=?";
    
    public void addGroup(Group group)
    {
        DynaModel dateRow = new DynaModel();
        dateRow.putAll(group.toMap());
        getJdbcTemplateUtil().insert("T_GROUP", dateRow);
    }
    
    public void addGroupUser(int groupId, int userId, String siteno)
    {
        String id = BaseService.getSequenceGenerator().getNextSequence("T_GROUP_USER");
        int id_group_user = Integer.parseInt(id);
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(new Integer(id_group_user));
        list.add(new Integer(groupId));
        list.add(new Integer(userId));
        list.add(siteno);
        getJdbcTemplateUtil().update(INSERT_GROUP_USER, list.toArray());
    }
    
    /**
    * 删除组
    * @param groupId
    */
    public void deleteGroup(int groupId)
    {
        getJdbcTemplateUtil().delete("T_GROUP", "GROUP_ID", new Integer(groupId));
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(new Integer(groupId));
        getJdbcTemplateUtil().update(DELETE_GROUP_USER_BY_GROUP, list.toArray());
    }
    
    public void deleteGroupRight(int groupId)
    {
        
    }
    
    /**
    * 删除该组下所有用户
    * @param groupId
    */
    public void deleteGroupUser(int groupId)
    {
        getJdbcTemplateUtil().update(DELETE_GROUP_ALL_USER, new Object[] { new Integer(groupId) });
    }
    
    public void deleteGroupUser(int groupId, int userId)
    {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(new Integer(groupId));
        list.add(new Integer(userId));
        getJdbcTemplateUtil().update(DELETE_GROUP_USER, list.toArray());
    }
    
    /**
    * 根据组名查找组
    */
    public Group findGroupBygroupNo(String groupNo)
    {
        DynaModel datarow = getJdbcTemplateUtil().queryMap(FIND_GROUP_USER_BY_NANE, new Object[] { groupNo });
        if (datarow == null)
        {
            return null;
        }
        Group group = new Group();
        group.fromMap(datarow);
        return group;
    }
    
    public Group findGroupById(int id)
    {
        DynaModel datarow = getJdbcTemplateUtil().queryMap(FIND_GROUP_BY_ID, new Object[] { new Integer(id) });
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
    public List<User> getGroupUser(int groupId)
    {
        List<User> list_gruop_user = getJdbcTemplateUtil().queryForList(FIND_GROUP_USER_ALL, User.class, new Object[] { new Integer(groupId) });
        return list_gruop_user;
    }
    
    /**
    * 列出该组下所有用户信息
    */
    public DBPage<DynaModel> getPageData(int curPage, int numPerPage, String siteNo, int groupId, String keyword)
    {
        DBPage<DynaModel> page = null;
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
        
        page = getJdbcTemplateUtil().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);
        if (page != null)
        {
            List<DynaModel> dataList = page.getData();
            ArrayList<DynaModel> newDataList = new ArrayList<DynaModel>();
            for (Iterator<DynaModel> iter = dataList.iterator(); iter.hasNext();)
            {
                User user = new User();
                DynaModel row = (DynaModel) iter.next();
                user.fromMap(row);
                newDataList.add(user);
            }
            page.setData(newDataList);
        }
        return page;
    }
    
    public DBPage<DynaModel> getPageData(int curPage, int numPerPage, String siteNo, String keyword)
    {
        DBPage<DynaModel> page = null;
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
        page = getJdbcTemplateUtil().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);
        
        if (page != null)
        {
            List<DynaModel> dataList = page.getData();
            ArrayList<DynaModel> newDataList = new ArrayList<DynaModel>();
            for (Iterator<DynaModel> iter = dataList.iterator(); iter.hasNext();)
            {
                Group group = new Group();
                DynaModel row = (DynaModel) iter.next();
                group.fromMap(row);
                newDataList.add(group);
            }
            page.setData(newDataList);
        }
        return page;
    }
    
    public void updateGroup(Group group)
    {
        DynaModel dataRow = new DynaModel();
        dataRow.putAll(group.toMap());
        getJdbcTemplateUtil().update("T_GROUP", dataRow, "group_Id", new Integer(group.getGroup_Id()));
    }
    
}
