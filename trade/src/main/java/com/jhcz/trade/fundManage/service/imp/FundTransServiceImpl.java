package com.jhcz.trade.fundManage.service.imp;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.exception.InterfaceCallException;
import com.jhcz.trade.common.wtc.CallWTC;
import com.jhcz.trade.fundManage.service.FundTransService;

/**
 * @类名: FundTransServiceImpl
 * @包名 com.jhcz.trade.fundManage.service.imp
 * @描述: 基金转换服务类
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-4-5 下午5:14:22
 * @版本 V1.0
 */
@Service("fundTransService")
public class FundTransServiceImpl implements  FundTransService{
	
	/**
	 * 查询基金份额
	 */
	@Override
	public Map<String, Object> queryFundShare(DataRow params) throws InterfaceCallException{
		Map map = null;
		try {
			map =  CallWTC.execute("1002420004", params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 基金转换
	 */
	@Override
	public Map<String, Object> fundTrans(DataRow params) throws InterfaceCallException {
		Map map = null;
		
		try {
			CallWTC.execute("1002420005", params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
