package com.jhcz.trade.bankcard.service;

import java.util.Map;

import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.exception.InterfaceCallException;

/**
 * @类名: BindBankCardService
 * @包名 com.jhcz.trade.bankcard.service
 * @描述: 银行卡绑定业务接口类
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-3-16 下午5:43:11
 * @版本 V1.0
 */
public interface BindBankCardService {
	
	/**
	  * @方法名: checkBankCardInfo
	  * @描述: 银卡卡信息校验
	  * @参数 @param param
	  * @参数 @return   
	  * @返回类型: String 
	  * @throws
	  * @时间 2016-3-18 下午2:01:40
	  * @作者: zhonghang
	 */
	public String checkBankCardInfo(Map<String, String> param);
	
	/**
	  * @方法名: openFundAccount
	  * @描述: 基金开户
	  * @参数 @param param
	  * @参数 @return   
	  * @返回类型: String 
	  * @throws
	  * @时间 2016-3-18 下午2:15:52
	  * @作者: zhonghang
	 */
	public Map openFundAccount(Map<String, String> param) throws InterfaceCallException;
	
	/**
	  * @方法名: deleteBankCard
	  * @描述: 删除卡
	  * @参数 @param param
	  * @参数 @return
	  * @参数 @throws Exception   
	  * @返回类型: Map 
	  * @throws
	  * @时间:2016-3-30下午5:41:43
	  * @作者: zhonghang
	 */
	public Map deleteBankCard(Map<String,String> param) throws Exception;
	
	/**
	  * @方法名: saveBankCardInfo
	  * @描述: 保存银行卡信息
	  * @参数 @param param
	  * @参数 @return   
	  * @返回类型: String 
	  * @throws
	  * @时间 2016-3-18 下午2:18:40
	  * @作者: zhonghang
	 */
	public void saveBankCardInfo(DataRow param) throws Exception; 
	
	
	/**
	  * @方法名: getUserInfo
	  * @描述: 获得用户信息
	  * @参数 @param login_id
	  * @参数 @return   
	  * @返回类型: Map<String,String> 
	  * @throws
	  * @时间:2016-3-26上午10:55:46
	  * @作者: zhonghang
	 */
	public Map<String,String> getUserInfo(String login_id) throws Exception;
	
	/**
	  * @方法名: addBankCard
	  * @描述: 加挂银行卡
	  * @参数 @param param
	  * @参数 @return
	  * @参数 @throws Exception   
	  * @返回类型: Map<String,String> 
	  * @throws
	  * @时间:2016-4-15下午5:21:25
	  * @作者: zhonghang
	 */
	public Map<String,String> addBankCard(DataRow param) throws Exception;
	
	/**
	  * @方法名: insertLogger
	  * @描述: 保存日志
	  * @参数 @param modelName  :模块名称
	  * @参数 @param logger：日志信息
	  * @参数 @param errorInfo ：错误信息 
	  * @返回类型: void 
	  * @throws
	  * @时间:2016-3-26上午11:18:52
	  * @作者: zhonghang
	 */
	public void insertLogger(String modelName, String logger,String errorInfo,String login_id);
	
}
