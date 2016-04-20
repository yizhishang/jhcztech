package com.jhcz.trade.fundManage.service.imp;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.exception.InterfaceCallException;
import com.jhcz.trade.common.wtc.CallWTC;
import com.jhcz.trade.fundManage.service.FundDividendService;

/**
 * @类名: FundDividendServiceImpl
 * @包名 com.jhcz.trade.fundManage.service.imp
 * @描述: 基金分红方式业务实现类
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-4-5 上午8:26:52
 * @版本 V1.0
 */
@Service("fundDividendService")
public class FundDividendServiceImpl implements FundDividendService{
	
	/**
	 * 修改基金分红方式
	 */
	@Override
	public Map modifyFundDividend(DataRow params) throws InterfaceCallException{
		Map map = null;
		try {
			map = CallWTC.execute("1002420009", params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 *  查询分红的基金
	 */
	@Override
	public Map<String, Object> queryFundDividend(DataRow params) throws InterfaceCallException{
		Map map = null;
		try {
			map = CallWTC.execute("1002420006", params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
