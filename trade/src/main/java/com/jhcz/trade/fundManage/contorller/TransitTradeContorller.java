package com.jhcz.trade.fundManage.contorller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.jhcz.trade.common.base.DataRow;

/**
 * @类名: TransitTradeContorller
 * @包名 com.jhcz.trade.fundManage.contorller
 * @描述: 在途交易
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-4-5 上午8:18:43
 * @版本 V1.0
 */
@Controller
@RequestMapping("/transitTrade")
public class TransitTradeContorller {
	
	/**
	  * @方法名: queryTransitTrade
	  * @描述: 在途交易查询
	  * @参数 @param request
	  * @参数 @param response
	  * @参数 @param model
	  * @参数 @return   
	  * @返回类型: ModelAndView 
	  * @throws
	  * @时间:2016-4-5上午8:20:21
	  * @作者: zhonghang
	 */
	@ResponseBody
	@RequestMapping("/queryTransitTrade")
	public ModelAndView queryTransitTrade(HttpServletRequest request, HttpServletResponse response,Model model){
		//交易账号
		String trade_acco = request.getParameter("trade_acco");
		//客户号
		String cust_no = request.getParameter("cust_no");
		//网点号
		String branch_code = request.getParameter("branch_code");
		DataRow param = new DataRow();
		param.set("trade_acco", trade_acco);
		param.set("cust_no", cust_no);
		param.set("branch_code", branch_code);
		/*************************模拟测试数据 start 2016年4月8日11:24:41**************************************/
		List<Object> dataList = new ArrayList<Object>();
		for (int i=0;i<3;i++) {
			Map<String,Object> dataMap = new HashMap<String,Object>();
			dataMap.put("fund_name", "平安大华睿享文娱灵活配置混合C_"+i);//基金名称
			dataMap.put("trade_type", "申购_"+i);//交易类型
			dataMap.put("trade_state", "1_"+i);//交易状态
			dataMap.put("occur_amount", "100");//发生金额
			dataMap.put("trade_time", "2016-04-08 12:15:22");//发生金额
			dataList.add(dataMap);
		}
		/*************************模拟测试数据 end 2016年4月8日11:24:41***************************************/
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("data", dataList);
		ModelAndView mav = new ModelAndView(new MappingJacksonJsonView(),map);
		return mav;
	}
	
	/**
	  * @方法名: queryKillOrderFundInfo
	  * @描述: 查询撤单基金的信息
	  * @参数 @param request
	  * @参数 @param response
	  * @参数 @param model
	  * @参数 @return   
	  * @返回类型: ModelAndView 
	  * @throws
	  * @时间:2016-4-8下午2:57:48
	  * @作者: zhonghang
	 */
	@ResponseBody
	@RequestMapping("/queryKillOrderFundInfo")
	public ModelAndView queryKillOrderFundInfo(HttpServletRequest request, HttpServletResponse response,Model model){
		//基金代码
		String fund_code = request.getParameter("fund_code");
		/************************模拟数据   2016年4月8日15:18:33 start**************************************/
		Map<String,String> dataMap = new HashMap<String,String>();
		List<Object> dataList = new ArrayList<Object>();
		dataMap.put("occur_amount", "1000");//发生金额
		dataMap.put("trade_type", "申购");//交易类型
		dataMap.put("trade_time", "2015-06-08 20:23:1");//原申请时间
		dataMap.put("request_no", "456646978945645113");//申请流水号
		dataList.add(dataMap);
		/************************模拟数据   2016年4月8日15:18:33 end****************************************/
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("data", dataList);
		ModelAndView mav = new ModelAndView(new MappingJacksonJsonView(),map);
		return mav;
	}
	
	
	/**
	  * @方法名: killOrder
	  * @描述: 交易撤单
	  * @参数 @param request
	  * @参数 @param response
	  * @参数 @param model
	  * @参数 @return   
	  * @返回类型: ModelAndView 
	  * @throws
	  * @时间:2016-4-8上午11:00:03
	  * @作者: zhonghang
	 */
	@ResponseBody
	@RequestMapping("/killOrder")
	public ModelAndView killOrder(HttpServletRequest request, HttpServletResponse response,Model model){
		Map<String,Object> map = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView(new MappingJacksonJsonView(),map);
		return mav;
	}
}
