package com.jhcz.trade.fundManage.contorller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jhcz.trade.account.biz.bean.UserInfo;
import com.jhcz.trade.account.utils.WebConstants;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.jhcz.trade.common.base.BaseHttpServletRequest;
import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.exception.InterfaceCallException;
import com.jhcz.trade.common.util.DateUtil;
import com.jhcz.trade.common.util.UuidUtil;
import com.jhcz.trade.fundManage.service.FundDividendService;
/**
 * @类名: FundDividendController
 * @包名 com.jhcz.trade.fundManage.contorller
 * @描述: 基金分红方式
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-4-1 下午5:03:57
 * @版本 V1.0
 */
@Controller
@RequestMapping("/fundDividend.do")
public class FundDividendController {
	private Logger logger = Logger.getLogger(Logger.class);
	@Autowired
	private FundDividendService fundDividendService;
	/**
	  * @方法名: queryFundDividend
	  * @描述: 查询可以修改分红的基金
	  * @参数 @param request
	  * @参数 @param response
	  * @参数 @param model
	  * @参数 @return   
	  * @返回类型: ModelAndView 
	  * @throws
	  * @时间:2016-4-6下午2:41:54
	  * @作者: zhonghang
	 */
	@ResponseBody
	@RequestMapping(params="method=queryFundDividend")
	public ModelAndView queryFundDividend(HttpServletRequest request, HttpServletResponse response,Model model){
		//客户号
		//String cust_no = request.getParameter("cust_no");
		UserInfo userInfo = (UserInfo)request.getSession().getAttribute(WebConstants.LOGIN_USER);
		//String cust_no = userInfo.getCustNo();
		//网点号
		String branch_code = request.getParameter("branch_code");
		Map<String,Object> map = new HashMap<String,Object>();
		List<Object> dataList = new ArrayList<Object>();
		for (int i=0;i<4;i++) {
			Map<String, String> dataMap = new HashMap<String,String>();
			dataMap.put("fund_code", "962301");
			dataMap.put("fund_name", "平安大华安享保本混合_"+i);
			dataMap.put("share_class", "1");
			dataMap.put("market_value", "99.4");
			dataMap.put("occur_share", "99.4");
			dataMap.put("dividend_method", "红利再投");
			dataList.add(dataMap);
 		}
		map.put("data", dataList);
		ModelAndView mav = new ModelAndView(new MappingJacksonJsonView(),map);
		return mav;
	}
	
	
	/**
	  * @方法名: modifyFundDividend
	  * @描述: 修改分红方式
	  * @参数 @param request
	  * @参数 @param response
	  * @参数 @param model
	  * @参数 @return   
	  * @返回类型: ModelAndView 
	  * @throws
	  * @时间:2016-4-1下午5:06:59
	  * @作者: zhonghang
	 */
	@ResponseBody
	@RequestMapping(params="method=modifyFundDividend")
	 public ModelAndView modifyFundDividend(HttpServletRequest request, HttpServletResponse response,Model model){
		 //交易账号
		 String trade_acco = request.getParameter("trade_acco");
		 //客户号
		 //String cust_no = request.getParameter("cust_no");
		UserInfo userInfo =  (UserInfo)request.getSession().getAttribute(WebConstants.LOGIN_USER);
		String cust_no = "";
		if (userInfo != null) {
			 cust_no = userInfo.getCustNo();
		}
		 //流水号
		 //String ecap_serialno = request.getParameter("ecap_serialno");
		String ecap_serialno = UuidUtil.createSerialNumber();
		 //交易发起时间
		 //String request_time = request.getParameter("request_time");
		// String request_time = DateUtil.formatDate(new Date(), "yyyyMMddHHmiss");
		 //网点号
		 //String branch_code = request.getParameter("branch_code");
		 String branch_code = "002001";
		 //基金代码
		 String fund_code = request.getParameter("fund_code");
		 //分红方式
		 String dividend_method = request.getParameter("dividend_method");
		 DataRow params = new DataRow();
		 params.set("ECAP_SERIALNO", ecap_serialno);
		 params.set("BRANCH_CODE", "002001");
		 params.set("DIVIDEND_METHOD", "0");
		 params.set("REQUEST_TIME", "20160301140202");
		 params.set("TRADE_ACCO", "1067");
		 params.set("FUND_CODE", "810015");
		 params.set("TRADE_SOURCE", "0");
		 params.set("TRADE_TYPE", "036");
		 logger.info("++++++++++++基金分红方式调用开始 +++++++++++++++++++++");
		 try{
			 Map resultMap = fundDividendService.modifyFundDividend(params);
			 logger.info("++++++++++++基金分红方式调用结束.返回结果为： "+resultMap);
		 }catch(InterfaceCallException e){
			 throw new InterfaceCallException("-10000", "基金分红方式修改出错");
		 }
		 
		/* dr.set("trade_acco", trade_acco);
		 dr.set("cust_no", cust_no);
		 dr.set("ecap_serialno", ecap_serialno);
		 dr.set("request_time", request_time);
		 dr.set("branch_code", branch_code);
		 dr.set("fund_code", fund_code);
		 dr.set("dividend_method", dividend_method);*/
		 Map<String,Object> map = new HashMap<String, Object>();
		 ModelAndView mav = new ModelAndView(new MappingJacksonJsonView(),map);
		 return mav;
	 }
	
	/**
	  * @方法名: queryFundInfo
	  * @描述: 查询基金信息
	  * @参数 @param request
	  * @参数 @param response
	  * @参数 @param model
	  * @参数 @return   
	  * @返回类型: ModelAndView 
	  * @throws
	  * @时间:2016-4-7上午10:30:22
	  * @作者: zhonghang
	 */
	@ResponseBody
	@RequestMapping(params="method=queryFundInfo")
	public ModelAndView queryFundInfo(HttpServletRequest request, HttpServletResponse response,Model model){
		String fund_code = request.getParameter("fund_code");
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> dataMap = new HashMap<String, Object>();
		List<Object> dataList = new ArrayList<Object>();
		dataMap.put("fund_code", "0008");
		dataMap.put("fund_name", "平安大华安享保本混合");
		dataMap.put("dividend_method", "1");
		dataList.add(dataMap);
		map.put("data", dataList);
		ModelAndView mav = new ModelAndView(new MappingJacksonJsonView(),map);
		return mav;
	}
}
