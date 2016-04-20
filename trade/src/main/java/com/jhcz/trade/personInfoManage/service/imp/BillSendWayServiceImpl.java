package com.jhcz.trade.personInfoManage.service.imp;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.wtc.CallWTC;
import com.jhcz.trade.personInfoManage.service.IBillSendWayService;

/**
 * 
 * @Description：对账单寄送方式实现类（1.通讯地址修改 2.电子邮箱验证 3.修改账单寄送方式 4.预留验证信息  通用）
 * @author 邱文豪
 * @created 2016年3月29日 下午1:46:30
 */
@Service("billSendWayService")
public class BillSendWayServiceImpl implements IBillSendWayService{
	
	@Override
    /**
     * 修改账单寄送方式
     */
	public Map updateBillSendWay(DataRow dr) throws Exception {
		// TODO Auto-generated method stub
		return CallWTC.execute("1002410003", dr);
	}
	
	/**
	 * 客户资料修改
	 */
	@Override
	public Map modifyCustInfo(DataRow params) throws Exception{
		return CallWTC.execute("1002410003", params);
	}
	
	
	
	
}
