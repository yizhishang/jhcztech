package com.jhcz.trade.personInfoManage.service;

import java.util.Map;

import com.jhcz.trade.common.base.DataRow;

/**
 * 
 * @Description：账单寄送方式接口
 * @author 邱文豪
 * @created 2016年3月29日 下午1:44:44
 */
public interface IBillSendWayService {
	/**
	 * 
	 * @Description：修改账单寄送方式
	 * @author 邱文豪
	 * @created 2016年3月29日 下午2:03:36 
	 * @param dr
	 * @return
	 * @throws Exception
	 */
	public Map updateBillSendWay(DataRow params ) throws Exception;	
	
	/**
	  * @方法名: modifyCustInfo
	  * @描述: 客户资料修改
	  * @参数 @param params
	  * @参数 @return   
	  * @返回类型: Map 
	  * @throws
	  * @时间:2016-4-13下午4:05:41
	  * @作者: zhonghang
	 */
	public Map modifyCustInfo(DataRow params) throws Exception;
}
