package com.jhcz.web.service;

import com.jhcz.base.domain.User;
import com.jhcz.web.exception.BusinessException;


public interface UserService
{
	public User getUserById(int userId);
	
	public User login(String uid, String password) throws BusinessException;
}
