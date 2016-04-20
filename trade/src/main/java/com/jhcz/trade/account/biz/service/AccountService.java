package com.jhcz.trade.account.biz.service;

import java.util.Map;

import com.jhcz.trade.common.base.DataRow;

public interface AccountService {
	
	/**
	  * @方法名: cancelAccount
	  * @描述: 撤单
	  * @参数 @param params
	  * @参数 @return   
	  * @返回类型: Map 
	  * @throws
	  * @时间:2016-4-20下午2:35:57
	  * @作者: zhonghang
	 */
	public Map cancelAccount(DataRow params) throws Exception;
}
