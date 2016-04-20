package com.jhcz.web.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jhcz.base.pojo.User;
import com.jhcz.base.util.DateHelper;
import com.jhcz.base.util.security.MD5;
import com.jhcz.web.dao.UserDao;
import com.jhcz.web.exception.BusinessException;
import com.jhcz.web.service.UserService;

@Service
public class UserServiceImpl implements UserService
{
	@Resource
	private UserDao userDao;

	@Override
	public User getUserById(int userId)
	{
		return userDao.getUserById(userId);
	}

	@Override
	public User login(String uid, String password) throws BusinessException
	{
		User user = userDao.findUserByUID(uid);
		if (user == null)
        {
            throw new BusinessException("用户名或密码错误！");
        }
		
		String state = user.getState();
        if (state.equals(User.STATE_CLOSE) )
        {
            throw new BusinessException("用户已经关闭！");
        }
        
        String pwd = user.getPassword();
        password = MD5.getMD5ofStr(password);
        
        if (!password.equals(pwd))
        {
            throw new BusinessException("用户名或密码错误！");
        }
        
        user.setLogin_times(user.getLogin_times() + 1);
        user.setLast_time(DateHelper.formatDate(new Date()));
        userDao.updateUser(user);
		return user;
	}
	
	
}
