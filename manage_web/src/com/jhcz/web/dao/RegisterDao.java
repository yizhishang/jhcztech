package com.jhcz.web.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;


/**
 * 描述: RegisterDao.java
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-4-15
 * 创建时间: 上午9:35:41
 */
@Repository
public interface RegisterDao
{
	@Insert("insert into T_WS_BUSINESS_REGISTER (fn_id, fc_name, fc_mobileno, fc_message) values(#{0},#{1},#{2},#{3})")
	public void insertMessage(String id, String name, String mobileNo, String message);
}
