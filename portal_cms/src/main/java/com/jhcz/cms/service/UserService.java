package com.jhcz.cms.service;

import com.jhcz.cms.exception.BusinessException;
import com.jhcz.cms.model.User;


public interface UserService
{
	public User getUserById(int userId);
	
	public User login(String uid, String password) throws BusinessException;
}
