package com.jhcz.trade.fundManage.service;

import com.jhcz.trade.common.base.DataRow;

/**
 * @类名: FundTradeService
 * @包名 com.jhcz.trade.fundtrade.service
 * @描述: 基金交易业务层
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-3-21 上午10:33:23
 * @版本 V1.0
 */
public interface FundTradeService {
	
	/**
	  * @方法名: fundSubscription
	  * @描述: 基金认购
	  * @参数 @param dr   
	  * @返回类型: void 
	  * @throws
	  * @时间 2016-3-21 上午10:39:42
	  * @作者: zhonghang
	 */
	public DataRow fundSubscription(DataRow dr ) throws Exception;	
	/**
	  * @方法名: fundPurchase
	  * @描述: 基金申购
	  * @参数 @param dr   
	  * @返回类型: void 
	  * @throws
	  * @时间:2016-3-21下午3:23:04
	  * @作者: zhonghang
	 */
	public DataRow fundPurchase(DataRow dr ) throws Exception;
	
	/**
	  * @方法名: fundRedemption
	  * @描述: 基金赎回
	  * @参数 @param dr   
	  * @返回类型: void 
	  * @throws
	  * @时间:2016-3-21下午3:23:22
	  * @作者: zhonghang
	 */
	public DataRow fundRedemption(DataRow dr ) throws Exception;
	
	/**
	 * 同步基金信息
	 * @return
	 * @throws Exception
	 */
	public DataRow synchronizeFundInfo() throws Exception;
}
