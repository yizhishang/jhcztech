package com.jhcz.trade.personInfoManage.service;

import java.util.Map;

import com.jhcz.trade.common.base.DataRow;

/**
 * 
 * @Description：风险评估接口（1.通讯地址修改 2.电子邮箱验证 3.修改账单寄送方式 4.预留验证信息  通用）
 * @author 邱文豪
 * @created 2016年3月29日 下午1:44:44
 */
public interface IRiskAsessService {
	/**
	 * 
	 * @Description：风险承受能力测评
	 * @author 邱文豪
	 * @created 2016年3月29日 下午2:03:36 
	 * @param dr
	 * @return
	 * @throws Exception
	 */
	public Map calcRisk(DataRow dr ) throws Exception;	
	
}
