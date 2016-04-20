package com.jhcz.trade.fundManage.service.imp;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.fundManage.service.TransitTradeService;

/**
 * @类名: TransitTradeServiceImpl
 * @包名 com.jhcz.trade.fundManage.service.imp
 * @描述: 在途交易业务实现类
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-4-5 上午8:24:39
 * @版本 V1.0
 */
@Service("transitTradeService")
public class TransitTradeServiceImpl implements TransitTradeService{
	
	/**
	 * 在途交易查询
	 */
	@Override
	public Map queryTransitTrade(DataRow params) {
		Map map = null;
		return map;
	}

}
