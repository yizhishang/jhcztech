package com.jhcz.plat.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.util.StringHelper;
import com.jhcz.plat.dao.BaseDao;
import com.jhcz.plat.dao.UserDao;
import com.jhcz.plat.domain.User;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-11
 * 创建时间: 15:02:11
 */
@Repository
public class UserDaoImpl extends BaseDao implements UserDao
{
	
	private static String FIND_USER_BY_UID = "select * from T_USER where uid2 = ?";
	
	private static String FIND_USER_BY_ID = "select * from T_USER where user_id = ?";
	
	@Override
    public void addUser(User user)
	{
		DataRow dataRow = new DataRow();
		dataRow.putAll(user.toMap());
		getJdbcTemplate().insert("T_USER", dataRow);
	}
	
	@Override
    public void deleteUser(int id)
	{
		getJdbcTemplate().delete("T_USER", "user_id", new Integer(id));
	}
	
	@Override
    public User findUserById(int id)
	{
		DataRow dataRow = getJdbcTemplate().queryMap(FIND_USER_BY_ID, new Object[] { new Integer(id) });
		if (dataRow == null)
			return null;
		User user = new User();
		user.fromMap(dataRow);
		return user;
	}
	
	@Override
    public List<Object> findUserBySiteNo(String siteNo)
	{
        List<Object> dataList = getJdbcTemplate().query("select * from T_USER where siteno = ? ", new Object[] { siteNo });
		ArrayList<Object> newDataList = new ArrayList<Object>();
		if (dataList != null)
		{
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
			{
				User user = new User();
				DataRow row = (DataRow) iter.next();
				user.fromMap(row);
				newDataList.add(user);
			}
		}
		return newDataList;
	}
	
	@Override
    public User findUserByUID(String uid)
	{
		DataRow dataRow = getJdbcTemplate().queryMap(FIND_USER_BY_UID, new Object[] { uid });
		if (dataRow == null)
			return null;
		
		User user = new User();
		user.fromMap(dataRow);
		return user;
	}
	
    @Override
    public List<Object> getAll()
	{
        List<Object> dataList = getJdbcTemplate().query("select * from T_USER order by user_id desc ");
		ArrayList<Object> newDataList = new ArrayList<Object>();
        for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
		{
			User user = new User();
			DataRow row = (DataRow) iter.next();
			user.fromMap(row);
			newDataList.add(user);
		}
		return newDataList;
	}
	
	@Override
    public DBPage getPageData(int curPage, int numPerPage, int group_id, String siteNo, String keyword, String loanNo, boolean isSystem)
	{
		DBPage page = null;
		
		StringBuffer sqlBuffer = new StringBuffer();
		ArrayList<Object> argList = new ArrayList<Object>();
		sqlBuffer.append("select * from T_USER where user_id not in (select user_id from t_group_user where group_id = ?) ");
		argList.add(group_id);
		if (!StringHelper.isEmpty(siteNo))
		{
			sqlBuffer.append(" and siteno = ? ");
			argList.add(siteNo);
		}
		if (!StringHelper.isEmpty(keyword))
		{
			sqlBuffer.append(" and (uid2 like ? or name like ?) ");
			argList.add("%" + keyword + "%");
			argList.add("%" + keyword + "%");
		}
		if (!isSystem)
		{
			sqlBuffer.append(" and is_system = 0");
		}
		if (!StringHelper.isEmpty(loanNo))
		{
			sqlBuffer.append(" order by login_times desc ");
		}
		else
		{
			sqlBuffer.append(" order by user_id desc ");
		}
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
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword, String loanNo, boolean isSystem)
	{
		DBPage page = null;
		
		StringBuffer sqlBuffer = new StringBuffer();
		ArrayList<Object> argList = new ArrayList<Object>();
		sqlBuffer.append("select * from T_USER where 1=1 ");
		if (!StringHelper.isEmpty(siteNo))
		{
			sqlBuffer.append(" and siteno = ? ");
			argList.add(siteNo);
		}
		if (!StringHelper.isEmpty(keyword))
		{
			sqlBuffer.append(" and (uid2 like ? or name like ?) ");
			argList.add("%" + keyword + "%");
			argList.add("%" + keyword + "%");
		}
		if (!isSystem)
		{
			sqlBuffer.append(" and is_system = 0");
		}
		if (!StringHelper.isEmpty(loanNo))
		{
			sqlBuffer.append(" order by login_times desc ");
		}
		else
		{
			sqlBuffer.append(" order by user_id desc ");
		}
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
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword, String branchno, String roleid)
	{
		DBPage page = null;
		StringBuffer sqlBuffer = new StringBuffer();
		ArrayList<Object> argList = new ArrayList<Object>();
		sqlBuffer.append("SELECT U.USER_ID,U.SITENO,U.NAME,U.PASSWORD,U.STATE,U.LOGIN_TIMES,U.LAST_TIME,U.EMAIL,U.PHONE,U.MOBILE,U.UID2,U.BRANCHNO,B.BRANCHNAME");
		sqlBuffer.append(" FROM T_USER U LEFT JOIN T_B_BRANCH B ON U.BRANCHNO = B.BRANCHNO WHERE 1 = 1 ");
		if (!StringHelper.isEmpty(siteNo))
		{
			if (!siteNo.equals("all"))
			{
				sqlBuffer.append(" AND U.SITENO LIKE ? ");
				argList.add("%" + siteNo + "%");
			}
		}
		if (!StringHelper.isEmpty(keyword))
		{
			sqlBuffer.append(" AND (U.UID2 LIKE ? OR U.NAME LIKE ?)");
			argList.add("%" + keyword + "%");
			argList.add("%" + keyword + "%");
		}
		if (StringHelper.isNotEmpty(branchno))
		{
			sqlBuffer.append(" AND U.BRANCHNO = ?");
			argList.add(branchno);
		}
		if (StringHelper.isNotEmpty(roleid))
		{
			sqlBuffer.append(" AND U.USER_ID IN (SELECT USER_ID FROM T_ROLE_USER WHERE ROLE_ID = ?)");
			argList.add(roleid);			
		}
		sqlBuffer.append(" AND U.IS_SYSTEM = 0");
		sqlBuffer.append(" ORDER BY USER_ID DESC");
		page = getJdbcTemplate().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);
		
		//		if (page != null)
		//		{
        //			List<Object> dataList = page.getData();
		//			ArrayList<Object> newDataList = new ArrayList<Object>();
        //			for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
		//			{
		//				User user = new User();
		//				DataRow row = (DataRow) iter.next();
		//				user.fromMap(row);
		//				newDataList.add(user);
		//			}
		//			page.setData(newDataList);
		//		}
		
		return page;
	}
	
	@Override
    public DBPage getPageDataNotInRole(int curPage, int numPerPage, String siteNo, String keyword, int roleId)
	{
		DBPage page = null;
		
		StringBuffer sqlBuffer = new StringBuffer();
		ArrayList<Object> argList = new ArrayList<Object>();
		sqlBuffer.append("select * from T_USER where 1=1 ");
		if (!StringHelper.isEmpty(siteNo))
		{
			sqlBuffer.append(" and siteno = ? ");
			argList.add(siteNo);
		}
		if (!StringHelper.isEmpty(keyword))
		{
			sqlBuffer.append(" and (uid2 like ? or name like ?) ");
			argList.add("%" + keyword + "%");
			argList.add("%" + keyword + "%");
		}
		
		sqlBuffer.append(" and user_id not in (select user_id from T_ROLE_USER where role_id=?)");
		argList.add(new Integer(roleId));
		sqlBuffer.append(" order by user_id desc ");
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
    public void updateUser(User user)
	{
		DataRow dataRow = new DataRow();
		dataRow.putAll(user.toMap());
		if (user.getId() > 0)
		{
			getJdbcTemplate().update("T_USER", dataRow, "user_id", new Integer(user.getId()));
		}
		else
		{
			getJdbcTemplate().update("T_USER", dataRow, "uid2", user.getUid());
		}
	}
}