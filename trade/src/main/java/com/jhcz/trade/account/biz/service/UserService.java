package com.jhcz.trade.account.biz.service;

import java.util.Map;

import com.jhcz.trade.account.biz.bean.UserInfo;
import com.jhcz.trade.common.base.DataRow;

/**
 * 描述:用户用关  
 * <br/>版权: Copyright (c) 2009
 * <br/>公司: 
 * <br/>作者: 
 * <br/>版本: 1.0
 * <br/>日期: 2016-3-15
 * <br/>时间: 上午10:14:53
 */
public interface UserService
{
	
	/**
	 * 验证手机号是否注册
	 * @return
	 * @throws Exception
	 */
	public boolean checkMobileIsRegist(String mobile,String custNo,String custType,boolean isPeoReg,boolean isActAndNoMod) throws Exception;
	
	/**
	 * 注册
	 * @param param
	 * @return
	 */
	public void register(Map param) throws Exception;
	
	
	/**
	 * 保存密码
	 * @param param
	 * @throws Exception
	 */
	public void savePassword(Map param) throws Exception;

	/**
	 * 重置密码
	 * @param param
	 * @throws Exception
	 */
	public void resetPassword(DataRow param) throws Exception;
	
	/**
	 * 生成客户号
	 * @return
	 */
	public String generateCustNo() throws Exception;
	
	
	/**
	 * 通过登录ID获取用户信息
	 * @param loginId
	 * @return
	 * @throws Exception
	 */
	public UserInfo getUserBaseInfoByLoginId(String loginId) throws Exception;
	
	
	/**
	 * 用户登录
	 * @param loginId
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public Map userLogin(UserInfo user,String loginId,String password,String pwdType) throws Exception;
}
