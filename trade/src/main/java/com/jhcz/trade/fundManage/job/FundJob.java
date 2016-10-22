package com.jhcz.trade.fundManage.job;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.util.DateUtil;
import com.jhcz.trade.fundManage.service.FundService;

public class FundJob {

	@Autowired
	private FundService fundService;
	
	private Logger logger = Logger.getLogger(FundJob.class);
	
	public void execute()
	{
		System.out.println(DateUtil.formatDate(new Date())+",Spring quartz Job!");
		DataRow result = new DataRow();
		/*try {
			result = new FundTradeServiceImpl().synchronizeFundInfo();
		} catch (Exception e) {
			logger.error("同步基金信息失败", e);
//			mav.addObject("msg", "同步基金信息异常，请联系管理员！详情如下：\n"+e.toString());
//			 return mav;
		}*/
//		if(result == null || result.size() < 1){
//			logger.error("同步基金信息列表为空");
////			 return mav;
//		}
		
		List funds = result.getList("DATA0"); //批量操作
		for (int i = 0; funds != null && i < funds.size(); i++) {
//			int sc = fundService.fundIsExist(((DataRow)funds.get(i)).getString("FC_FUND_CODE"));
//			if(sc < 1)	fundService.saveFundInfo((DataRow)funds.get(i));
		}
		
	}
	
}
