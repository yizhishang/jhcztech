package com.jhcz.trade.personInfoManage.service.imp;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.wtc.CallWTC;
import com.jhcz.trade.personInfoManage.service.IRiskAsessService;

/**
 * 
 * @Description：风险评估实现类
 * @author 邱文豪
 * @created 2016年3月29日 下午1:46:30
 */
@Service("riskAsessService")
public class RiskAsessServiceImpl implements IRiskAsessService{
	
	@Override
    /**
     * 风险测评
     */
	public Map calcRisk(DataRow dr) throws Exception {
		// TODO Auto-generated method stub
		return CallWTC.execute("1002410007", dr);
	}
}
