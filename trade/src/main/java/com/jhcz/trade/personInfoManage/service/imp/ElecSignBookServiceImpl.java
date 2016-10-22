package com.jhcz.trade.personInfoManage.service.imp;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.wtc.CallWTC;
import com.jhcz.trade.personInfoManage.service.IElecSignBookService;

/**
 * 
 * @Description：电子签名书实现类
 * @author 邱文豪
 * @created 2016年3月29日 下午1:46:30
 */
@Service("elecSignBookService")
public class ElecSignBookServiceImpl implements IElecSignBookService{
	
    /**
     * 同意签署
     */
	@Override
	public Map agreeSign(DataRow dr) throws Exception {
		// TODO Auto-generated method stub
		return CallWTC.execute("800050", dr);
	}

	/**
	 * 取消签署
	 */
	@Override
	public Map unAgreeSign(DataRow dr) throws Exception {
		// TODO Auto-generated method stub
		return CallWTC.execute("800050", dr);
	}
}
