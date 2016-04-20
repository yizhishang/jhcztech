package com.jhcz.web.dao;

import org.springframework.stereotype.Repository;

import com.jhcz.base.pojo.User;

@Repository
public interface UserDao
{
	public User getUserById(int userId);

	public User findUserByUID(String uid2);

	public void updateUser(User user);
}
