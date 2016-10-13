package com.jhcz.plat.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jhcz.base.config.SysConfig;
import com.jhcz.base.jdbc.DBPage;
import com.jhcz.base.service.BaseService;
import com.jhcz.base.util.DateHelper;
import com.jhcz.base.util.StringHelper;
import com.jhcz.base.util.security.SecurityHelper;
import com.jhcz.plat.dao.UserDao;
import com.jhcz.plat.domain.User;
import com.jhcz.plat.service.UserService;
import com.jhcz.plat.service.exception.FistLoginModiPasswordException;
import com.jhcz.plat.service.exception.LoginFailedException;
import com.jhcz.plat.service.exception.PasswordErrorException;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-11
 * 创建时间: 14:54:24
 */
@Service
public class UserServiceImpl extends BaseService implements UserService
{
    
    @Resource
    private UserDao userDao;
    
    /**
    * 添加用户
    * @param user
    */
    @Override
    public void addUser(User user)
    {
        String id = getSequenceGenerator().getNextSequence("T_USER");
        user.setId(Integer.parseInt(id));
        userDao.addUser(user);
    }
    
    /**
    * 关闭用户，把用户状态置为关闭
    * @param id
    */
    @Override
    public void closeUser(int id)
    {
        User user = new User();
        user.setId(id);
        user.setState(User.STATE_CLOSE);
        userDao.updateUser(user);
    }
    
    /**
    * 
    * @描述：根据uid关闭用户，把用户状态设置为关闭
    * @作者：袁永君
    * @时间：2011-1-4 下午04:34:20
    * @param uid
    */
    @Override
    public void closeUser(String uid)
    {
        User user = new User();
        user.setUid(uid);
        user.setState(User.STATE_CLOSE);
        userDao.updateUser(user);
    }
    
    /**
    * 删除用户
    * @param id
    */
    @Override
    public void deleteUser(int id)
    {
        userDao.deleteUser(id);
    }
    
    /**
    * 根据用户编号查找用户
    *
    * @param id
    * @return
    */
    @Override
    public User findUserById(int id)
    {
        return userDao.findUserById(id);
    }
    
    /**
    * 根据站点标识，查找该站点的所有用户
    * @param siteNo 站点编号
    * @return
    */
    @Override
    public List<Object> findUserBySiteNo(String siteNo)
    {
        return userDao.findUserBySiteNo(siteNo);
    }
    
    /**
    * 根据用户UID查找用户
    * @param uid
    * @return
    */
    @Override
    public User findUserByUID(String uid)
    {
        return userDao.findUserByUID(uid);
    }
    
    /**
    * 得到所有的用户
    * @return
    */
    @Override
    public List<Object> getAll()
    {
        return userDao.getAll();
    }
    
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
    @Override
    public DBPage getPageData(int curPage, int numPerPage, int group_id, String siteNo, String keyword, String loanNo, boolean isSystem)
    {
        return userDao.getPageData(curPage, numPerPage, group_id, siteNo, keyword, loanNo, isSystem);
    }
    
    /**
    * 以分页方式返回某站点的用户
    * @param curPage
    * @param numPerPage
    * @param siteNo
    * @param keyword
    * @param loanNo
    * @return
    */
    @Override
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword, String loanNo, boolean isSystem)
    {
        return userDao.getPageData(curPage, numPerPage, siteNo, keyword, loanNo, isSystem);
    }
    
    /**
    * 以分页方式返回某站点的用户
    * @param curPage    当前第几页
    * @param numPerPage 每页多少条记录
    * @param siteNo     站点编号
    * @param keyword    关键词
    * @return
    */
    @Override
    public DBPage getPageData(int curPage, int numPerPage, String siteNo, String keyword, String branchno, String roleid)
    {
        return userDao.getPageData(curPage, numPerPage, siteNo, keyword, branchno, roleid);
    }
    
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
    @Override
    public DBPage getPageDataNotInRole(int curPage, int numPerPage, String siteNo, String keyword, int roleId)
    {
        return userDao.getPageDataNotInRole(curPage, numPerPage, siteNo, keyword, roleId);
    }
    
    /**
    * 根据用户的UID，判断用户是否已经存在
    * @param uid
    * @return
    */
    @Override
    public boolean isUserExist(String uid)
    {
        User user = userDao.findUserByUID(uid);
        return user != null;
    }
    
    /**
    * 用户登录
    *
    * @param uid
    * @param password
    * @return
    * @throws LoginFailedException
    */
    @Override
    public User login(String uid, String password) throws PasswordErrorException, FistLoginModiPasswordException, LoginFailedException
    {
        User user = userDao.findUserByUID(uid);
        if (user == null)
        {
            throw new LoginFailedException("用户名或密码错误！");
        }
        
        int state = user.getState();
        if (state == User.STATE_CLOSE)
        {
            throw new LoginFailedException("用户已经关闭！");
        }
        
        String pwd = user.getPassword();
        password = SecurityHelper.getMD5of32Str(password);
        
        if (!password.equals(pwd))
        {
            throw new PasswordErrorException("用户名或密码错误！");
        }
        
        int firstModiPwd = SysConfig.getInt("system.firstModiPwd");
        if (firstModiPwd == 1 && !"admin".equals(uid) && StringHelper.isEmpty(user.getLastTime()))
        {
            throw new FistLoginModiPasswordException("首次登录，需修改密码");
        }
        
        User partUser = new User();
        partUser.setId(user.getId());
        partUser.setLoginTimes(user.getLoginTimes() + 1);
        partUser.setLastTime(DateHelper.formatDate(new Date()));
        userDao.updateUser(partUser);
        
        return user;
    }
    
    /**
    * 开放用户，把用户状态置为开放
    * @param id
    */
    @Override
    public void openUser(int id)
    {
        User user = new User();
        user.setId(id);
        user.setState(User.STATE_OPEN);
        userDao.updateUser(user);
    }
    
    /**
    * 更新用户
    * @param user 用户对象
    */
    @Override
    public void updateUser(User user)
    {
        userDao.updateUser(user);
    }
}
