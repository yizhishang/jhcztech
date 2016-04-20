package com.jhcz.web.service;

import com.jhcz.base.pojo.User;
import com.jhcz.web.exception.BusinessException;


public interface UserService
{
	public User getUserById(int userId);
	
	public User login(String uid, String password) throws BusinessException;
}
