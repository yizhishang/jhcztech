package com.jhcz.trade.account.controller;

import java.util.List;
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

import com.jhcz.trade.account.biz.bean.UserInfo;
import com.jhcz.trade.account.biz.service.MemberService;
import com.jhcz.trade.account.utils.WebConstants;
import com.jhcz.trade.common.base.DataRow;

/**
 * @类名: MemberController
 * @包名 com.jhcz.trade.account.controller
 * @描述: 会员中心控制器
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-4-19 下午3:40:36
 * @版本 V1.0
 */
@Controller
@RequestMapping("/member")
public class MemberController extends MultiActionController{
	private Logger logger = Logger.getLogger(MemberController.class);
	@Autowired
	public MemberService memberService;
	/**
	  * @方法名: index
	  * @描述: 跳转到首页
	  * @参数 @param request
	  * @参数 @param response
	  * @参数 @param model
	  * @参数 @return   
	  * @返回类型: ModelAndView 
	  * @throws
	  * @时间:2016-4-19下午3:44:56
	  * @作者: zhonghang
	 */
	@RequestMapping(params="method=index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response,Model model){
		logger.info("++++++++++++++++获取用户登录信息开始 start++++++++++++++++++++++++++");
		UserInfo userInfo = (UserInfo)request.getSession().getAttribute(WebConstants.LOGIN_USER);
		String cust_no = "";
		if (userInfo == null ) {
			cust_no = userInfo.getCustNo();
		}
		logger.info("++++++++++++++++获取用户登录信息开始 end+++++++++++++++++++++++++userInfo="+userInfo);
		DataRow params = new DataRow();
		params.put("BRANCH_CODE", "002001");
		params.put("CUST_NO", cust_no);
		Map<String, Object> result = null;
		try {
			logger.info("++++++++++++++++++调用客户资产接口开始 start+++++++++++++++++++++++++++++++++");
			result = memberService.queryCustAssets(params);
			logger.info("++++++++++++++++++调用客户资产接口结束 end+++++++++++++++++++++++++++++++++"+result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Object> message =(List<Object>)result.get("MESSAGE");
		Map msgResult = null;
		//返回信息
		String msg = "";
		//返回码
		String code = "";
		//昨日盈亏
		String yesterday_profit_loss = "";
		//历史累计盈亏
		String history_total_profit_loss = "";
		//基金资产
		String fund_assets = "";
		//专户资产
		String special_account_assets = "";
		//在途资产
		String onway_assets = "";
		//总资产
		String total_assets = "";
		if (message != null && message.size()>0) {
			msgResult = (Map)message.get(0);
			msg = msgResult.get("MSG")+"";
			code = msgResult.get("CODE")+"";
			yesterday_profit_loss = msgResult.get("YESTERDAY_PROFIT_LOSS")+"";
			history_total_profit_loss = msgResult.get("HISTORY_TOTAL_PROFIT_LOSS")+"";
			fund_assets = msgResult.get("FUND_ASSETS")+"";
			onway_assets = msgResult.get("ONWAY_ASSETS")+"";
			total_assets = msgResult.get("TOTAL_ASSETS")+"";
			
		}
		logger.info("message======"+message);
		ModelAndView mav = new ModelAndView("/memberIndex/index");
		mav.addObject("msg", msg+":"+code);
		mav.addObject("yesterday_profit_loss", "65.5");
		mav.addObject("history_total_profit_loss", "3000.32");
		mav.addObject("fund_assets", "9000");
		mav.addObject("special_account_assets", "3000");
		mav.addObject("onway_assets", "1500");
		mav.addObject("total_assets", "23569.32");
		return mav;
	}
}
