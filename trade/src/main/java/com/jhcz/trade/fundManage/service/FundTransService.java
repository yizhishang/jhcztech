package com.jhcz.trade.fundManage.service;

import java.util.Map;

import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.exception.InterfaceCallException;

/**
 * @类名: FundTransService
 * @包名 com.jhcz.trade.fundManage.service
 * @描述: 基金转换接口服务类
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-4-5 下午5:13:59
 * @版本 V1.0
 */
public interface FundTransService {
	
	/**
	  * @方法名: queryFundShare
	  * @描述: 查询基金份额
	  * @参数 @param params
	  * @参数 @return   
	  * @返回类型: Map<String,Object> 
	  * @throws
	  * @时间:2016-4-5下午5:17:31
	  * @作者: zhonghang
	 */
	public Map<String, Object> queryFundShare(DataRow params) throws InterfaceCallException;
	
	/**
	  * @方法名: fundTrans
	  * @描述: 基金转换
	  * @参数 @param params
	  * @参数 @return
	  * @参数 @throws InterfaceCallException   
	  * @返回类型: Map<String,Object> 
	  * @throws
	  * @时间:2016-4-6上午9:20:22
	  * @作者: zhonghang
	 */
	public Map<String,Object> fundTrans(DataRow params) throws InterfaceCallException;
}
