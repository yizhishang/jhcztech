package com.jhcz.trade.fundManage.service;

import java.util.Map;

import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.exception.InterfaceCallException;

/**
 * @类名: TransitTradeService
 * @包名 com.jhcz.trade.fundManage.service
 * @描述: 交易查询接口类
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-4-5 上午8:25:16
 * @版本 V1.0
 */
public  interface TransitTradeService {
	
	/**
	  * @方法名: queryTransitTrade
	  * @描述: 在途交易查询
	  * @参数 @param params
	  * @参数 @return   
	  * @返回类型: Map 
	  * @throws
	  * @时间:2016-4-5上午8:45:30
	  * @作者: zhonghang
	 */
	public Map queryTransitTrade(DataRow params) throws InterfaceCallException;
}
