package com.jhcz.web.service;

import com.jhcz.web.exception.BusinessException;


/**
 * 描述: RegisterService.java
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-4-15
 * 创建时间: 上午9:33:45
 */
public interface RegisterService
{
	
	/**
	 * 描述: insertMessage
	 * 作者: 袁永君
	 * 创建日期: 2016-4-15
	 * 创建时间: 上午9:33:50
	 * @param name
	 * @param mobileNo
	 * @param message
	 * @return
	 * @throws BusinessException 
	 */
	public String insertMessage(String name, String mobileNo, String message) throws BusinessException;
	
	
}
