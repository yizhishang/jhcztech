package com.jhcz.trade.bankcard.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;

import com.jhcz.trade.bankcard.bean.UserBankCardBean;
import com.jhcz.trade.common.base.DataRow;

/**
 * @类名: BindBankCardMapper
 * @包名 com.jhcz.trade.bankcard.dao
 * @描述: 银行卡绑定
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-3-16 下午5:36:34
 * @版本 V1.0
 */
public interface BindBankCardMapper {
	
	/**
	  * @方法名: saveBankCardInfo
	  * @描述: 落地开户信息
	  * @参数 @param dataRow   
	  * @返回类型: void 
	  * @throws
	  * @时间 2016-3-18 下午2:17:52
	  * @作者: zhonghang
	 */
	public void saveBankCardInfo(DataRow param) throws Exception;
	
	/**
	  * @方法名: getUserInfo
	  * @描述: 获取登陆用户信息
	  * @参数 @param login_id
	  * @参数 @return   
	  * @返回类型: Map<String,String> 
	  * @throws
	  * @时间:2016-3-26上午10:52:17
	  * @作者: zhonghang
	 */
	public Map<String,String> getUserInfo(String login_id) throws Exception;
	
	
	/**
	  * @方法名: insertLogger
	  * @描述: 保存日志
	  * @参数 @param params   
	  * @返回类型: void 
	  * @throws
	  * @时间:2016-3-26上午11:10:44
	  * @作者: zhonghang
	 */
	public void insertLogger(DataRow params);

}
