package com.jhcz.trade.account.biz.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.jhcz.trade.account.biz.service.AccountService;
import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.wtc.CallWTC;
@Service("accountService")
public class AccountServiceImpl implements AccountService{
	
	/**
	 * 销户
	 */
	@Override
	public Map cancelAccount(DataRow params) throws Exception {
		return CallWTC.execute("1002420011", params);
	}

}
