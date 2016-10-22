package com.jhcz.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhcz.base.service.SequenceService;
import com.jhcz.web.dao.RegisterDao;
import com.jhcz.web.exception.BusinessException;
import com.jhcz.web.service.RegisterService;


/**
 * 描述: RegisterServiceImpl.java
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-4-15
 * 创建时间: 上午9:46:23
 */
@Service
public class RegisterServiceImpl implements RegisterService
{
	Logger logger = LoggerFactory.getLogger(RegisterServiceImpl.class);
	
	@Autowired
	RegisterDao registerDao;
	
	@Autowired
	SequenceService sequenceService;

	@Override
	public String insertMessage(String name, String mobileNo, String message) throws BusinessException
	{
		String id =  sequenceService.getNextSequence("T_WS_BUSINESS_REGISTER");
		try
		{
			registerDao.insertMessage(id, name, mobileNo, message);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		return id;
	}
	
}
