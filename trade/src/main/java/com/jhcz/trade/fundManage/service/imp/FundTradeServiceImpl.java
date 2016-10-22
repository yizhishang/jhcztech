package com.jhcz.trade.fundManage.service.imp;

import org.springframework.stereotype.Service;

import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.wtc.CallWTC;
import com.jhcz.trade.fundManage.service.FundTradeService;

/**
 * @类名: FundTradeServiceImpl
 * @描述: 基金交易业务实现层
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-3-21 上午10:33:58
 * @版本 V1.0
 */
@Service("fundTradeService")
public class FundTradeServiceImpl implements FundTradeService{
	//数据库层
	 
//	@Autowired FundTradeMapper fundTradeMapper;
	/**
	 * 认购
	 */
	@Override
	public DataRow fundSubscription(DataRow dr) throws Exception {
		
		return CallWTC.execute("1002420001", dr);
		
	}
	/**
	 * 申购
	 * @throws Exception 
	 */
	@Override
	public DataRow fundPurchase(DataRow dr) throws Exception {
		return CallWTC.execute("1002420002", dr);
	}
	
	/**
	 * 赎回
	 * @throws Exception 
	 */
	@Override
	public DataRow fundRedemption(DataRow dr) throws Exception {
		return CallWTC.execute("1002420003", dr);
	}
	
	/**
	 * 同步基金信息
	 * @throws Exception
	 */
	public DataRow synchronizeFundInfo() throws Exception {
		return CallWTC.execute("1002420007", null);
	}
	

}
