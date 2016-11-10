package com.yizhishang.plat.service;

import java.util.List;

import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.plat.domain.User;
import com.yizhishang.plat.service.exception.FistLoginModiPasswordException;
import com.yizhishang.plat.service.exception.LoginFailedException;
import com.yizhishang.plat.service.exception.PasswordErrorException;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-11
 * 创建时间: 14:54:07
 */
public interface UserService
{
    
    /**
    * 添加用户
    *
    * @param user 用户对象
    */
    public void addUser(User user);
    
    /**
    * 关闭用户，把用户状态置为关闭
    *
    * @param id
    */
    public void closeUser(int id);
    
    /**
    * 
    * @描述：根据uid关闭用户，把用户状态设置为关闭
    * @作者：袁永君
    * @时间：2011-1-4 下午04:34:54
    * @param uid
    */
    public void closeUser(String uid);
    
    /**
    * 删除用户
    *
    * @param id 用户的ID
    */
    public void deleteUser(int id);
    
    /**
    * 根据用户的ID，查找相应的用户
    * 描述: findUserById
    * 作者: 袁永君
    * 创建日期: 2015-10-30
    * 创建时间: 下午3:46:53
    * @param id
    * @return
    */
    public User findUserById(int id);
    
    /**
    * 根据站点标识，查找该站点的所有用户
    *
    * @param siteNo 站点编号
    * @return
    */
    public List<Object> findUserBySiteNo(String siteNo);
    
    /**
    * 根据用户的UID，查找相应的用户
    *
    * @param uid 用户的UID
    * @return 若没有发现用户，则返回null
    */
    public User findUserByUID(String uid);
    
    /**
    * 得到所有的用户
    * @return
    */
    public List<Object> getAll();
    
    /**
    * 以分页方式返回某站点而不是某用户组的用户
    * @param curPage
    * @param numPerPage
    * @param group_id
    * @param siteNo
    * @param keyword
    * @param loanNo
    * @return
    */
    public DBPage getPageData(int curPage, int numPerPage, int group_id, String siteNo, String keyword, String loanNo, boolean isSystem);
    
    /**
    * 以分页方式返回某站点的用户
    * @param curPage
    * @param numPerPage
    * @param siteNo
    * @param keyword
    * @param loanNo
    * @return
    */
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword, String loanNo, boolean isSystem);
    
    /**
    * 以分页方式返回某站点的用户
    *
    * @param curPage    当前第几页
    * @param numPerPage 每页多少条记录
    * @param siteNo     站点编号
    * @param keyword    关键词
    * @return
    */
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword, String branchno, String roleid);
    
    /**
    * 以分页方式返回某站点且不是某角色的用户的用户
    *
    * @param curPage    当前第几页
    * @param numPerPage 每页多少条记录
    * @param siteNo     站点编号
    * @param keyword    关键词
    * @param roleId     角色ID
    * @return
    */
    public DBPage getPageDataNotInRole(int curPage, int numPerPage, String siteNo, String keyword, int roleId);
    
    /**
    * 根据用户的UID，判断用户是否已经存在
    * @param uid
    * @return
    */
    public boolean isUserExist(String uid);
    
    /**
    * 使用户登录系统
    *
    * @param uid      用户的UID
    * @param password 用户的密码
    * @return 当前登录的用户对象
    * @throws LoginFailedException 登录失败的异常，其会带上失败的信息
    */
    public User login(String uid, String password) throws PasswordErrorException, FistLoginModiPasswordException, LoginFailedException;
    
    /**
    * 开放用户，把用户状态置为开放
    *
    * @param id
    */
    public void openUser(int id);
    
    /**
    * 更新用户
    *
    * @param user 用户对象
    */
    public void updateUser(User user);

}
