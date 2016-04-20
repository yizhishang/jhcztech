package com.jhcz.trade.account.biz.dao;

import java.util.List;
import java.util.Map;

import com.jhcz.trade.account.biz.bean.UserInfo;
import com.jhcz.trade.common.base.DataRow;


public interface UserMapper {
	

	/**
	 * 注册登录用户
	 * @param params
	 */
	public void saveLoginUser(Map params);
	
	/**
	 * 保存密码
	 * @param params
	 */
	public void savePassword(Map params);
	
	/**
	 * 找回密码之重置密码
	 * @param params
	 */
	public void resetPassword(DataRow params);
	
	/**
	 * 生成客户号
	 * @return
	 */
	public String generateCustNo();
	
	/**
	 * 通过手机号查询用户
	 * @param mobile
	 * @return
	 */
	public List queryUserByMobile(String mobile);
	
    /**
     * 获取用户信息
     * @param loginId
     * @return
     */
    public UserInfo getUserBaseInfoByLoginId(String loginId);
    
    /**
     * 通过客户号获取密码
     * @param custNo
     * @return
     */
    public Map getPwdInfo(Map params);
    
    /**
     * 修改锁定时间
     */
    public void updateLockTime(Map params);
    
    /**
     * 锁定账户
     */
    public void lockAccount(String custNo);
    
    /**
     * 错误次数归零
     * @param custNo
     * @param times
     */
    public void updateErrorCountOfAccount(String custNo);
    
    /**
     * 注销登录账户
     * @param custNo
     */
    public void invalidUser(String custNo);
    
    /**
     * 注销登录账户之删除登录密码
     * @param custNo
     */
    public void deleteLoginPwd(String custNo);
}
