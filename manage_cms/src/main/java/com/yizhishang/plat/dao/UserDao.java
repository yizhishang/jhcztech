package com.yizhishang.plat.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhishang.base.dao.BaseDao;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.domain.User;

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
public class UserDao extends BaseDao
{
	
	private static String FIND_USER_BY_UID = "select * from T_USER where uid2 = ?";
	
	private static String FIND_USER_BY_ID = "select * from T_USER where id = ?";
	
	public void addUser(User user)
	{
		DynaModel dataRow = new DynaModel();
		dataRow.putAll(user.toMap());
		getJdbcTemplateUtil().insert("T_USER", dataRow);
	}
	
	public void deleteUser(int id)
	{
		getJdbcTemplateUtil().delete("T_USER", "id", new Integer(id));
	}
	
	public User findUserById(int id)
	{
		DynaModel dataRow = getJdbcTemplateUtil().queryMap(FIND_USER_BY_ID, new Object[] { new Integer(id) });
		if (dataRow == null)
			return null;
		User user = new User();
		user.fromMap(dataRow);
		return user;
	}
	
	public List<DynaModel> findUserBySiteNo(String siteNo)
	{
        List<DynaModel> dataList = getJdbcTemplateUtil().queryForList("select * from T_USER where siteno = ? ", new Object[] { siteNo });
		ArrayList<DynaModel> newDataList = new ArrayList<DynaModel>();
		if (dataList != null)
		{
            for (Iterator<DynaModel> iter = dataList.iterator(); iter.hasNext();)
			{
				User user = new User();
				DynaModel row = (DynaModel) iter.next();
				user.fromMap(row);
				newDataList.add(user);
			}
		}
		return newDataList;
	}
	
	public User findUserByUID(String uid)
	{
		return getJdbcTemplateUtil().queryMap(FIND_USER_BY_UID, User.class, new Object[] { uid });
	}
	
    public List<DynaModel> getAll()
	{
        List<DynaModel> dataList = getJdbcTemplateUtil().queryForList("select * from T_USER order by id desc ");
		ArrayList<DynaModel> newDataList = new ArrayList<DynaModel>();
        for (Iterator<DynaModel> iter = dataList.iterator(); iter.hasNext();)
		{
			User user = new User();
			DynaModel row = (DynaModel) iter.next();
			user.fromMap(row);
			newDataList.add(user);
		}
		return newDataList;
	}
	
	public DBPage getPageData(int curPage, int numPerPage, int group_id, String siteNo, String keyword, String loanNo, boolean isSystem)
	{
		DBPage page = null;
		
		StringBuffer sqlBuffer = new StringBuffer();
		ArrayList<Object> argList = new ArrayList<Object>();
		sqlBuffer.append("select * from T_USER where id not in (select user_id from t_group_user where group_id = ?) ");
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
			sqlBuffer.append(" order by id desc ");
		}
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
			sqlBuffer.append(" order by id desc ");
		}
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
	
	public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword, String branchno, String roleid)
	{
		DBPage page = null;
		StringBuffer sqlBuffer = new StringBuffer();
		ArrayList<Object> argList = new ArrayList<Object>();
		sqlBuffer.append("SELECT U.ID,U.SITENO,U.NAME,U.PASSWORD,U.STATE,U.LOGIN_TIMES,U.LAST_TIME,U.EMAIL,U.PHONE,U.MOBILE,U.UID2,U.BRANCHNO,B.BRANCHNAME");
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
			sqlBuffer.append(" AND U.ID IN (SELECT USER_ID FROM T_ROLE_USER WHERE ROLE_ID = ?)");
			argList.add(roleid);			
		}
		sqlBuffer.append(" AND U.IS_SYSTEM = 0");
		sqlBuffer.append(" ORDER BY ID DESC");
		page = getJdbcTemplateUtil().queryPage(sqlBuffer.toString(), argList.toArray(), curPage, numPerPage);
		
		//		if (page != null)
		//		{
        //			List<DynaModel> dataList = page.getData();
		//			ArrayList<DynaModel> newDataList = new ArrayList<DynaModel>();
        //			for (Iterator<DynaModel> iter = dataList.iterator(); iter.hasNext();)
		//			{
		//				User user = new User();
		//				DynaModel row = (DynaModel) iter.next();
		//				user.fromMap(row);
		//				newDataList.add(user);
		//			}
		//			page.setData(newDataList);
		//		}
		
		return page;
	}
	
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
		
		sqlBuffer.append(" and id not in (select user_id from T_ROLE_USER where role_id=?)");
		argList.add(new Integer(roleId));
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
	
    public void updateUser(User user)
	{
		DynaModel dataRow = new DynaModel();
		dataRow.fromMap(user);
		if (user.getId() > 0)
		{
			getJdbcTemplateUtil().update("T_USER", dataRow, "id", new Integer(user.getId()));
		}
		else
		{
			getJdbcTemplateUtil().update("T_USER", dataRow, "uid2", user.getUid());
		}
	}
}