package com.jhcz.trade.fundManage.service.imp;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.wtc.CallWTC;
import com.jhcz.trade.fundManage.service.IInvestmentRecordService;

/**
 * 
 * @Description：资金账户交易明细、基金投资记录、在途资金查询
 * @author 邱文豪
 * @created 2016年3月29日 下午1:46:30
 */
@Service("investmentRecordService")
public class InvestmentRecordServiceImpl implements IInvestmentRecordService{

	@Override
	/**
	 * 1.资金账户交易明细
	 */
	public Map selectCapitalAccountTradeDetail(DataRow dr) throws Exception {
		// TODO Auto-generated method stub
		return CallWTC.execute("800235", dr);
	}

	@Override
	/**
	 * 2.基金投资记录
	 */
	public Map selectFundInvestRecords(DataRow dr) throws Exception {
		// TODO Auto-generated method stub
		return CallWTC.execute("800235", dr);
	}

	@Override
	/**
	 * 3.在途资金查询
	 */
	public Map selectOnTheWayCapital(DataRow dr) throws Exception {
		// TODO Auto-generated method stub
		return CallWTC.execute("800220", dr);
	}
	
}
