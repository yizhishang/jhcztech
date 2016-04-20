package com.jhcz.trade.fundManage.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.exception.InterfaceCallException;

/**
 * @类名: FundDividendService
 * @包名 com.jhcz.trade.fundManage.service
 * @描述: 基金分红方式业务实现接口类
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-4-5 上午8:26:18
 * @版本 V1.0
 */
public interface FundDividendService {
	
	/**
	  * @方法名: queryFundDividend
	  * @描述:  查询分红的基金
	  * @参数 @param params
	  * @参数 @return   
	  * @返回类型: Map 
	  * @throws
	  * @时间:2016-4-6下午2:51:02
	  * @作者: zhonghang
	 */
	public Map<String,Object> queryFundDividend(DataRow params) throws InterfaceCallException;
	
	/**
	  * @方法名: modifyFundDividend
	  * @描述: 修改基金分红方式
	  * @参数 @param params
	  * @参数 @return   
	  * @返回类型: Map 
	  * @throws
	  * @时间:2016-4-5上午8:41:45
	  * @作者: zhonghang
	 */
	public Map<String,Object> modifyFundDividend(DataRow params) throws InterfaceCallException;
}
