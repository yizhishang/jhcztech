package com.jhcz.cms.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhcz.cms.dao.UserDao;
import com.jhcz.cms.exception.BusinessException;
import com.jhcz.cms.model.User;
import com.jhcz.cms.service.UserService;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDao userDao;

	public User getUserById(int userId)
	{
		return userDao.getUserById(userId);
	}

	public User login(String uid, String password) throws BusinessException
	{
		User user = userDao.findUserByUID(uid);
		
		return user;
	}
	
}
