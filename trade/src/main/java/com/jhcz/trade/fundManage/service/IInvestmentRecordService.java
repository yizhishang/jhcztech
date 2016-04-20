package com.jhcz.trade.fundManage.service;

import java.util.Map;

import com.jhcz.trade.common.base.DataRow;

/**
 * 
 * @Description：资金账户交易明细、基金投资记录、在途资金查询
 * @author 邱文豪
 * @created 2016年3月29日 下午1:44:44
 */
public interface IInvestmentRecordService {
	
	/**
	 * 
	 * @Description：资金账户交易明细
	 * @author 邱文豪
	 * @created 2016年3月29日 下午2:03:36 
	 * @param dr
	 * @return
	 * @throws Exception
	 */
	public Map selectCapitalAccountTradeDetail(DataRow dr ) throws Exception;	
	
	/**
	 * 
	 * @Description：基金投资记录
	 * @author 邱文豪
	 * @created 2016年3月29日 下午2:03:36 
	 * @param dr
	 * @return
	 * @throws Exception
	 */
	public Map selectFundInvestRecords(DataRow dr ) throws Exception;	
	
	/**
	 * 
	 * @Description：在途资金查询
	 * @author 邱文豪
	 * @created 2016年3月29日 下午2:03:36 
	 * @param dr
	 * @return
	 * @throws Exception
	 */
	public Map selectOnTheWayCapital(DataRow dr ) throws Exception;
	
}
