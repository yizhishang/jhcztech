package com.yizhishang.plat.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.plat.domain.User;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-11
 * 创建时间: 15:01:50
 */
@Repository
public interface UserDao
{
	
	public void addUser(User user);
	
	public void deleteUser(int id);
	
	public User findUserById(int id);
	
	public List<Object> findUserBySiteNo(String siteNo);
	
	public User findUserByUID(String uid);
	
    public List<Object> getAll();
	
	public DBPage getPageData(int curPage, int numPerPage, int group_id, String siteNo, String keyword, String loanNo, boolean isSystem);
	
	public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword, String loanNo, boolean isSystem);

	public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword, String branchno, String roleid);
	
	public DBPage getPageDataNotInRole(int curPage, int numPerPage, String siteNo, String keyword, int roleId);
	
    public void updateUser(User user);
}
