package com.jhcz.trade.account.biz.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jhcz.trade.account.biz.bean.UserInfo;
import com.jhcz.trade.account.biz.constants.Constants;
import com.jhcz.trade.account.biz.dao.UserMapper;
import com.jhcz.trade.account.biz.service.UserService;
import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.util.MD5Util;

@Service("userService1")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public void register(Map param) throws Exception {
		
		List<UserInfo> users = userMapper.queryUserByMobile((String)param.get("mobile"));
		if(users != null && !users.isEmpty()){
			
			//将原来的用户注销、并删除登录密码
			userMapper.invalidUser(users.get(0).getCustNo());
			userMapper.deleteLoginPwd(users.get(0).getCustNo());
			
		}
		
		userMapper.saveLoginUser(param);
	}
	
	@Override
	public void savePassword(Map param) throws Exception {
		userMapper.savePassword(param);
	}
	
	@Override
	public String generateCustNo() {
		
		return userMapper.generateCustNo();
	}
	
	@Override
	public UserInfo getUserBaseInfoByLoginId(String loginId) throws Exception {
		return userMapper.getUserBaseInfoByLoginId(loginId);
	}
	
	@Override
	public Map userLogin(UserInfo user,String loginId, String password,String pwdType) throws Exception {
		
		Map result = new HashMap();
		if(user == null){
			
			result.put("loginState", "-1");
			result.put("logiIinfo", "用户不存在！");
			
			return result;
		}
		

		/**
		 * 清空客户近一天登录的错误次数（判断客户最后一次登录时间与当前系统时间如果相差一天则清空错误次数）
		 */
		Map params = new HashMap();
		params.put("custNo", user.getCustNo());
		params.put("pwdType", pwdType);
		Map passWordInfo = userMapper.getPwdInfo(params);
		if(passWordInfo == null)
		{
			result.put("loginState", "-1");
			result.put("logiIinfo", "密码信息不存在！");
		}
		else
		{
			String lock_date = (String)passWordInfo.get("FD_LOCK_DATE");
			Date lockDate = new Date();
			boolean checkPwd = true;
			String state = user.getState();
			//匹配账户密码
			//String password = SecurityUtil.encode(data.getString("password") + passWordInfo.getString("pwd_salt"));
			if (MD5Util.MD5(password).equals((String)passWordInfo.get("FC_PWD_VALUE")))
			{
				// 成功 清除锁定 且 清空用户登录密码错误次数
				userMapper.updateLockTime(passWordInfo);
				
				if (Constants.USER_INFO_STATE_NORMAL.equalsIgnoreCase(state))
				{
					result.put("loginState", "0");
					result.put("logiIinfo", "用户名密码正确，登陆成功！");
				}
				else if (Constants.USER_INFO_STATE_ACTIVATE.equalsIgnoreCase(state))
				{
					result.put("loginState", "2");
					result.put("logiIinfo", "账户未激活！");
				}
				else if (Constants.USER_INFO_STATE_NUSER.equalsIgnoreCase(state))
				{
					result.put("loginState", "3");
					result.put("logiIinfo", "账户已注销！");
				}
			}
			else
			{
				if(Constants.USER_INFO_STATE_ACTIVATE.equalsIgnoreCase(state))
				{
					result.put("error_times", 0);
					result.put("loginState", "4");
					result.put("logiIinfo", "密码错误！");
				}
				else
				{
					int error_input_count = 0;
					try {
						error_input_count = (Integer) passWordInfo.get("FI_ERROR_INPUT_COUNT");
					} catch (NumberFormatException e) {
						error_input_count = 0;
					}catch (ClassCastException e) {
						error_input_count = 0;
					}
					if (error_input_count >= 5)
					{
						result.put("loginState", "1");
						result.put("logiIinfo", "账户已锁定！！");
					}
					else
					{
						userMapper.updateErrorCountOfAccount(user.getCustNo());
						
						if(error_input_count == 4){
							userMapper.lockAccount(user.getCustNo());
						}
						
					}
				}
			}
		}
		
		return result;
	}
	
	public boolean checkMobileIsRegist(String mobile,String custNo,String custType,boolean isPeoReg,boolean isActAndNoMod){
		List list = userMapper.queryUserByMobile(mobile);
		if(list!=null && !list.isEmpty()){
			for(int i=0;i<list.size();i++){
				Map user = (Map)list.get(i);
				String userCustNo = (String)user.get("cust_no");
				String userCustType = (String)user.get("cust_type");
				String state = (String)user.get("state");
				String identity_num = (String)user.get("identity_num");
				/**
				 * 存在实名客户使用本手机号码
				 */
				if(((!"".equals(custNo) && !custNo.equals(userCustNo)) || "".equals(custNo)) && !"".equals(identity_num)){
					/**
					 * 用户状态为正常/锁定
					 */
					if(Constants.USER_INFO_STATE_NORMAL.equals(state) || Constants.USER_INFO_STATE_LOCK.equals(state)){
						/**
						 * 同为机构户情况下，允许重复
						 */
						if(custType.equals(userCustType) && custType.equals(Constants.CUST_TYPE_00)){
							//继续
						}else{
							/**
							 * 手机号码冲突客户同属机构户或者个人户   
							 */
							if(custType.equals(userCustType) || isPeoReg){
								/**
								 * 激活流程且未修改号码 如果是则允许通过  允许通过
								 */
								if(!isActAndNoMod){
									return true;
								}
								
							}
						}
						
					}
				}
			}
		}
		return false;
	}
	
	@Override
	public void resetPassword(DataRow param) throws Exception {
		userMapper.resetPassword(param);
	}
	
}
