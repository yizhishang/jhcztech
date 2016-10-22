package com.jhcz.trade.fundManage.contorller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import com.jhcz.trade.account.biz.bean.UserInfo;
import com.jhcz.trade.account.utils.WebConstants;
import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.exception.InterfaceCallException;
import com.jhcz.trade.common.util.UuidUtil;
import com.jhcz.trade.fundManage.service.FundTransService;

/**
 * @类名: FundTransContorller
 * @包名 com.jhcz.trade.fundManage.contorller
 * @描述: 基金转换
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-3-31 下午7:22:40
 * @版本 V1.0
 */
@Controller
@RequestMapping("/fundTrans.do")
public class FundTransController {
	private Logger logger = Logger.getLogger(FundTransController.class);

	@Autowired
	private FundTransService fundTransService;
	
	/**
	  * @方法名: queryTransFund
	  * @描述: 查询可转换的基金
	  * @参数 @return   
	  * @返回类型: ModelAndView 
	  * @throws
	  * @时间:2016-3-31下午7:23:51
	  * @作者: zhonghang
	 */
	@ResponseBody
	@RequestMapping(params="method=queryTransFund")
	public ModelAndView queryTransFund(HttpServletRequest request, HttpServletResponse response,Model model){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Object> dataList = new ArrayList<Object>();
		for (int i=0;i<3;i++) {
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("fund_code", "10698"+i);
			dataMap.put("fund_name", "钟航基金测试_"+i);//基金名称
			dataMap.put("share_class", "前收费_"+i);//收费方式
			dataMap.put("occur_share", "10_"+i);//发生份额
			dataMap.put("market_value", "1000000");//市值
			dataMap.put("confirm_share", "10_"+i);//可用份额
			dataMap.put("state", "0");//基金状态
			dataList.add(dataMap);
		}
		map.put("error_no", "0");
		map.put("message", "");
		map.put("data", dataList);
		ModelAndView mav = new ModelAndView(new MappingJacksonJsonView(),map);
		return mav;
	}
	
	/**
	  * @方法名: queryFundShare
	  * @描述: 查询基金份额
	  * @参数 @param request
	  * @参数 @param response
	  * @参数 @param model
	  * @参数 @return   
	  * @返回类型: ModelAndView 
	  * @throws
	  * @时间:2016-4-5下午4:27:04
	  * @作者: zhonghang
	 */
	@ResponseBody
	@RequestMapping(params="method=queryFundShare")
	public ModelAndView queryFundShare(HttpServletRequest request, HttpServletResponse response,Model model){
		//session的用户信息
		UserInfo user = (UserInfo)request.getSession().getAttribute(WebConstants.LOGIN_USER);
		//客户号
		//String cust_no = user.getCustNo();
		String cust_no = "10017";
		//基金代码
		String fund_code = request.getParameter("fund_code");
		//网点号
		String branch_code = request.getParameter("branch_code");
		DataRow params = new DataRow();
		params.set("fund_code", fund_code);
		params.set("branch_code", branch_code);
		params.set("cust_no", cust_no);
		try{
			//Map<String,Object> map = fundTransService.queryFundShare(params);
		}catch(InterfaceCallException e){
			throw new InterfaceCallException("-10000", "查询基金份额失败！");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		ModelAndView mav = new ModelAndView(new MappingJacksonJsonView(),map);
		return mav;
	}
	
	/**
	  * @方法名: fundTrans
	  * @描述: 基金转换
	  * @参数 @param request
	  * @参数 @param response
	  * @参数 @param model
	  * @参数 @return   
	  * @返回类型: ModelAndView 
	  * @throws
	  * @时间:2016-4-6上午9:17:52
	  * @作者: zhonghang
	 */
	@ResponseBody
	@RequestMapping(params="method=fundTrans")
	public ModelAndView fundTrans(HttpServletRequest request, HttpServletResponse response,Model model){
		//网点号码
		String branch_code = request.getParameter("branch_code");
		//流水号
		//String ecap_serialno = request.getParameter("ecap_serialno");
		String ecap_serialno = UuidUtil.createSerialNumber();
		//对方基金代码
		String target_fundcode = request.getParameter("target_fundcode");
		//基金账号
		String fund_code = request.getParameter("fund_code");
		//交易账号
		String trade_acco = request.getParameter("trade_acco");
		//发生份额  
		String occur_share = request.getParameter("occur_share");
		//交易来源  
		String trade_source = request.getParameter("trade_source");
		//发生时间
		/*String request_time = DateUtil.formatDate(new Date(), "yyyyMMddHHmmss");
		System.out.println("request_time==="+request_time);*/
		DataRow params = new DataRow();
		params.set("ECAP_SERIALNO", ecap_serialno);
		params.set("BRANCH_CODE", "002001");
		params.set("REQUEST_TIME", "20160301140202");
		params.set("SHARE_CLASS", "0");
		params.set("TRADE_ACCO", "710018");
		params.set("CASH_ACCO", "410016");
		params.set("DISCOUNT_RATE", "0.8");
		params.set("TARGET_FUNDCODE", "810014");
		params.set("LARGE_REDEEM_FLAG", "1");
		params.set("OCCUR_SHARE", "10000");
		params.set("FUND_CODE", "810017");
		params.set("TRADE_SOURCE", "0");
		params.set("TRADE_TYPE","036");
		/*params.set("BRANCH_CODE", branch_code);
		params.set("ECAP_SERIALNO", ecap_serialno);
		params.set("TARGET_FUNDCODE", target_fundcode);
		params.set("FUND_CODE", fund_code);
		params.set("TRADE_ACCO", trade_acco);
		params.set("OCCUR_SHARE", occur_share);
		params.set("TRADE_SOURCE", trade_source);*/
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			//调用基金转换接口
			logger.info("++++++++++++++++++++++基金转换接口 开始+++++++++++++++++++++");
			System.out.println("++++++++++++++++++++++基金转换接口 开始+++++++++++++++++++++");
			map = fundTransService.fundTrans(params);
			System.out.println("++++++++++++++++++++++基金转换接口 结束：返回信息为:"+map);
			logger.info("++++++++++++++++++++++基金转换接口 结束：返回信息为:"+map);
		}catch(InterfaceCallException ex){
			throw new InterfaceCallException("-10000", "基金转换失败！");
		}
		ModelAndView mav = new ModelAndView(new MappingJacksonJsonView(),map);
		return mav;
	}
	
}
