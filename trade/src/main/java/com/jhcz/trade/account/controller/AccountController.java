package com.jhcz.trade.account.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.jhcz.trade.account.biz.service.AccountService;
import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.util.DateUtil;
import com.jhcz.trade.common.util.UuidUtil;

/**
 * @类名: AccountController
 * @包名 com.jhcz.trade.account.controller
 * @描述: 账户类控制器
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-4-20 下午2:10:21
 * @版本 V1.0
 */
@Controller
@RequestMapping("/account")
public class AccountController extends MultiActionController{
	private Logger logger = Logger.getLogger(AccountController.class);
	
	@Autowired
	public AccountService accountService;
	
	/**
	  * @方法名: cancelOrder
	  * @描述: 销户
	  * @参数 @param request
	  * @参数 @param response
	  * @参数 @param model
	  * @参数 @return   
	  * @返回类型: ModelAndView 
	  * @throws
	  * @时间:2016-4-20下午2:18:52
	  * @作者: zhonghang
	 */
	@RequestMapping(params="method=cancelAccount")
	public ModelAndView cancelAccount(HttpServletRequest request, HttpServletResponse response,Model model){
		String trade_acco = request.getParameter("trade_acco");
		String ecap_serialno = UuidUtil.createSerialNumber();
		String request_time = DateUtil.formatDate(new Date(), "yyyyMMddHHmmss");
		String branch_code = "002001";
		String trade_source = "0";
		DataRow params = new DataRow();
		params.set("TRADE_ACCO", trade_acco);
		params.set("ECAP_SERIALNO",ecap_serialno);
		params.set("REQUEST_TIME", request_time);
		params.set("BRANCH_CODE", branch_code);
		params.set("TRADE_SOURCE", trade_source);
		Map<String, Object> result = null;
		try {
			logger.info("++++++++++++++销户接口调用开始 start+++++++++++++++++++++");
			result = accountService.cancelAccount(params);
			logger.info("++++++++++++++销户接口调用开始 end++++++++++++++++++返回结果为："+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("/account/ok");
		return mav;
	}
}
