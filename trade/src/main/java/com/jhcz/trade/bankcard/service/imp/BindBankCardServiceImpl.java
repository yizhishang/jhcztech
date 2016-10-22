package com.jhcz.trade.bankcard.service.imp;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jhcz.trade.bankcard.dao.BindBankCardMapper;
import com.jhcz.trade.bankcard.service.BindBankCardService;
import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.exception.InterfaceCallException;
import com.jhcz.trade.common.util.DateUtil;
import com.jhcz.trade.common.wtc.CallWTC;

/**
 * @类名: BindBankCardServiceImpl
 * @包名 com.jhcz.trade.bankcard.service.imp
 * @描述: 银行卡绑定业务实现类
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-3-16 下午5:43:18
 * @版本 V1.0
 */
@Service("bindBankCardService")
public class BindBankCardServiceImpl implements BindBankCardService{
	
	@Autowired
	private BindBankCardMapper bindBankCardMapper;
	
	@Override
	public String checkBankCardInfo(Map<String, String> param) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public Map openFundAccount(Map<String, String> param) throws InterfaceCallException {
		//开户
		try {
			return CallWTC.execute("1002410001", param);
		} catch (Exception e) {
			throw new InterfaceCallException("-1", "开户失败,"+e.toString());
		}
	}
	
	/**
	 * 保存绑卡信息
	 */
	@Override
	public void saveBankCardInfo(DataRow param) throws Exception{
		bindBankCardMapper.saveBankCardInfo(param);
	}
	
	/**
	 * 获取登陆用户的信息
	 */
	@Override
	public Map<String, String> getUserInfo(String login_id) throws Exception {
		return bindBankCardMapper.getUserInfo(login_id);
	}
	
	/**
	 * 插入日志
	 */
	@Override
	public void insertLogger(String modelName, String logger,String errorInfo,String login_id) {
		DataRow log = new DataRow();
		log.set("create_date", DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		log.set("model_name", modelName);
		log.set("logger_content", logger);
		log.set("error_info", errorInfo);
		log.set("login_id", login_id);
		bindBankCardMapper.insertLogger(log);
		
	}
	
	/**
	 * 删卡
	 */
	@Override
	public Map deleteBankCard(Map<String, String> param) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 加挂银行卡
	 */
	@Override
	public Map addBankCard(DataRow param) throws Exception {
		//加挂银行卡
		try {
			return CallWTC.execute("1002410015", param);
		} catch (Exception e) {
			throw new InterfaceCallException("-1", "加挂银行卡失败,"+e.getMessage());
		}
	}
	
	
	

}
