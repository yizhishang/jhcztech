package com.jhcz.trade.fundManage.contorller;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.jhcz.trade.account.biz.bean.UserInfo;
import com.jhcz.trade.account.biz.constants.Constants;
import com.jhcz.trade.account.utils.WebConstants;
import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.constant.fund.BusinessConstants;
import com.jhcz.trade.common.util.DateUtil;
import com.jhcz.trade.common.util.MD5Util;
import com.jhcz.trade.common.util.NumericHelper;
import com.jhcz.trade.common.util.RandomUtil;
import com.jhcz.trade.common.util.StringHelper;
import com.jhcz.trade.common.vo.PageView;
import com.jhcz.trade.common.wtc.CallWTC;
import com.jhcz.trade.fundManage.bean.FundInfo;
import com.jhcz.trade.fundManage.service.FundService;
import com.jhcz.trade.fundManage.service.FundTradeService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/fund")
public class FundController extends MultiActionController{
	
	Logger logger = Logger.getLogger(FundController.class);
	
	@Autowired
	private FundService fundService;
	
	@Autowired
	private FundTradeService fundTradeService;
	 
	@RequestMapping("/listAllFund")
	public ModelAndView listAllFund(HttpServletRequest request,HttpServletResponse response){

		ModelAndView mav = new ModelAndView("/fundmarket/fundmarket_jsp");
		//当前登录用户
		UserInfo user = (UserInfo)request.getSession().getAttribute(WebConstants.LOGIN_USER);
		if(user == null){
			//则跳转登录页面，重新登录——拦截器应该做了这件事
			
		}
		
		int hh = fundService.fundIsExist("708225");
		
		
		Map params = new HashMap();
		int pageSize;
		int pageIndex;
		try {
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
			pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		} catch (NumberFormatException e1) {
			pageSize = 30;
			pageIndex = 1;
		}
		
		// 开始分页
		PageView pageView = null;
        pageView = new PageView(pageSize,pageIndex);
        params.put("pageView", pageView);
		List<FundInfo> fundList = null;
		
		try {
			fundList =  fundService.listAllFund(params);
		} catch (Exception e) {
			mav.addObject("msg", "获取基金列表异常!");
			 return mav;
		}
		
		mav.addObject("fundList", fundList);
		mav.addObject("userInfo", user);
		 return mav;
	}
	
	/***
	 * 购买基金产品，输入购买金额
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/doFundBuy")
	public ModelAndView doFundBuy(HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("/fundmarket/fundbuy_jsp");
		//当前登录用户
		UserInfo user = (UserInfo)request.getSession().getAttribute(WebConstants.LOGIN_USER);
		logger.info("检查是否登录：");
		if(user == null){
			//则跳转登录页面，重新登录——拦截器应该做了这件事
			
		}
		user = new UserInfo();
		String isTrade = user.getIsTrade();
		
		//未开户
		if ("0".equalsIgnoreCase(isTrade) )
		{
			return new ModelAndView("");  //跳转到开户页面——绑卡页面
		}

		String fundCode = request.getParameter("fundCode");
		if(StringUtils.isBlank(fundCode)){
//			return listAllFund(request);
		}
		
		FundInfo fundInfo = fundService.getFundByCode(fundCode);
		if(fundInfo == null){
			mav.addObject("errorMsg", "产品已不存在！");
			return mav;
		}
		
		//判断交易账号、资金账号状态
		
		// 判断基金状态 是 认购还是申购
		String fundstatus = fundInfo.getFundStatus();
		String businesscode = "";
		if ("1".equalsIgnoreCase(fundstatus) || "2".equalsIgnoreCase(fundstatus))
		{
			mav.addObject("buytype", "1");
			mav.addObject("businessCode", "01");
			mav.addObject("businessCode1", BusinessConstants.G_FUND_BUSINESSCODE_020);
			businesscode = BusinessConstants.G_FUND_BUSINESSCODE_020;
		}
		else
		{
			mav.addObject("buytype", "0");
			mav.addObject("businessCode", "02");
			mav.addObject("businessCode1", BusinessConstants.G_FUND_BUSINESSCODE_022);
			businesscode = BusinessConstants.G_FUND_BUSINESSCODE_022;
		}
		request.getSession().setAttribute("businessCode", businesscode);
		
		//查询客户所有的银行卡信息
		user.setCustNo("23");
		List<DataRow> bankCardList = fundService.getDepositBankCard(user.getCustNo());
		for (DataRow dataRow : bankCardList) {
			String bankAcct = dataRow.getString("BANKACCT");
			if(StringUtils.isNotBlank(bankAcct) && bankAcct.length() >4){
				dataRow.set("shortBankAcct", bankAcct.substring(bankAcct.length()-4));
			}
		}
		
		mav.addObject("fundInfo", fundInfo);
		mav.addObject("bankCardList", bankCardList);
		
		return mav;
	}

	
	/**
	 * 交易确认
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doFundBuyConfirm")
	public ModelAndView doFundBuyConfirm(HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("/fundmarket/fundbuy_sure_jsp");
		try
		{
			String chargetype = StringHelper.getParameter(request, "chargetype");
			String fundName = StringHelper.getParameter(request, "fundname");
			String fundCode = StringHelper.getParameter(request, "fundcode");
			String apply_money = StringHelper.getParameter(request, "apply_money");//申购金额
			NumberFormat nf = new DecimalFormat("#,##0.00");
			String moneyFormat = nf.format(new Double(apply_money));
			String moneyStr = NumericHelper.number2CNMontrayUnit(new BigDecimal(apply_money));
			String tradeAccount = StringHelper.getParameter(request, "tradeAccount");
			String cashAccount = StringHelper.getParameter(request, "cashAccount");
			
			String discount_rate = StringHelper.getParameter(request,"discount_rate");//折扣率
			String shareclass = StringHelper.getParameter(request,"shareclass");//收费方式
			
			String original_value = StringHelper.getParameter(request,"original_value_1");//折扣率方式时的基金费率
			String specifyratefee = StringHelper.getParameter(request,"specifyratefee");//指定费率时的申购费率
			String specifyfee = StringHelper.getParameter(request,"specifyfee");//指定费用时的手续费
			
			DataRow data = new DataRow();
			
			data.set("chargeType", chargetype);
			data.set("fundName", fundName);
			data.set("fundCode", fundCode);
			data.set("moneyFormat", moneyFormat);
			data.set("applyMoney", apply_money);
			data.set("moneyStr", moneyStr);
			data.set("specifyratefee", specifyratefee);
			data.set("specifyfee", specifyfee);
			data.set("discount_rate", discount_rate);
			data.set("shareclass", shareclass);
			data.set("original_value", original_value);
			data.set("tradeAccount", tradeAccount);
			data.set("cashAccount", cashAccount);
			
			
			FundInfo fundInfo = fundService.getFundByCode(fundCode);
			if (fundInfo == null)
			{
				mav.addObject("success", "0");
				mav.addObject("message", "基金信息查询失败");
				mav.setViewName("/fundmarket/fundbuy_result_jsp");
				return mav;
			}
			// 银行卡申购
			if (StringUtils.isEmpty(cashAccount))
			{
				mav.addObject("success", "0");
				mav.addObject("message", "银行卡信息查询失败！");
				mav.setViewName("/fundmarket/fundbuy_result_jsp");
				return mav;
				//new ActionResult("/WEB-INF/views/fund_management/fund_applybuy_result.ftl");
			}
			else
			{
				DataRow selectBank = new DataRow();//BankCardService.getBankCardByCashaccountid(request, tradeAccount, cashAccount);
				/*if (selectBank == null)
				{
					mav.addObject("success", "0");
					mav.addObject("message", "银行卡信息查询失败！");
					mav.setViewName("/fundmarket/fundbuy_result_jsp");
					return mav;
				}*/
				data.set("bank", selectBank);
			}
			request.getSession().setAttribute(WebConstants.SESSION_FORM_DATA, data);
			mav.addObject("data", data);
			return mav;
		}
	
		catch (Exception e)
		{
			logger.error("购买基金错误，", e);
			mav.addObject("success", "0");
			mav.addObject("message", "服务器忙,请稍后重试！");
			mav.setViewName("/fundmarket/fundbuy_result_jsp");
			return mav;
		}
	}
	
	/**
	 * 购买确认
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/doFundBuySubmit")
	public ModelAndView doFundBuySubmit(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		DataRow data = (DataRow)request.getSession().getAttribute(WebConstants.SESSION_FORM_DATA);
		String businessCode = (String)request.getSession().getAttribute("businessCode");
		String tradePwd = StringHelper.getParameter(request, "password");
		//验证交易密码
		
		if(BusinessConstants.G_FUND_BUSINESSCODE_020.equals(businessCode)){  //认购
			return doFundSubscription(request, response);
		}
		else if(BusinessConstants.G_FUND_BUSINESSCODE_022.equals(businessCode)){  //申购

			return doFundPurchase(request, response);
		}
		
		return null;
	}
	
	
	/**
	 * 基金申购
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView doFundPurchase(HttpServletRequest request, HttpServletResponse response){
		//申请单号
		String requestNo = RandomUtil.randNum(20);
		//网点号
		String branch_code = StringHelper.getParameter(request, "brach_code", "002001");
		//交易发起时间
		String request_time = DateUtil.formatDate(new Date(), "yyyyMMddHHmmss");
		//交易密码
		String password = StringHelper.getParameter(request, "password");
		
		ModelAndView mav = new ModelAndView("/fundmarket/fundbuy_result_jsp");
		DataRow dr = new DataRow();
		DataRow data = (DataRow)request.getSession().getAttribute(WebConstants.SESSION_FORM_DATA);
		data.set("shareclass", "0");
		dr.set("ECAP_SERIALNO", requestNo);
		dr.set("BRANCH_CODE", branch_code);
		dr.set("REQUEST_TIME", "20160301140202");
		dr.set("SHARE_CLASS", data.getString("shareclass"));
		dr.set("TRADE_ACCO", data.getString("tradeAccount"));
		dr.set("CASH_ACCO", data.getString("cashAccount"));
		dr.set("OCCUT_AMOUNT", data.getString("applyMoney"));
		dr.set("FUND_CODE", "810015");
		dr.set("CURRENCY_TYPE", "156");
		dr.set("password", MD5Util.MD5(password));
		dr.set("TRADE_TYPE", BusinessConstants.G_FUND_BUSINESSCODE_022);  //申购
		dr.set("TRADE_SOURCE", BusinessConstants.G_OP_SOURCE_ONLINE);  //网上交易
		
		DataRow result = null;
		try {
			result = fundTradeService.fundPurchase(dr);
		} catch (Exception e) {
			mav.addObject("success", "0");
			mav.addObject("message", "基金申购失败！"+e.getMessage());
			return mav;
		}
		
		if("0000".equals(result.get("CODE"))){  //成功
			String state = result.getString("ORDER_STATE");
			
			if (Constants.ORDER_STATE_00.equalsIgnoreCase(state))
			{
				mav.addObject("success", "2");
				DataRow messageData = new DataRow();
				//基金状态为1表示发行期，认购
				/*if("1".equals(fundStatus))
				{
					messageData.set("tag1", DateHelper.formatDate(new Date(), "MM月dd日 HH:mm"));
					messageData.set("tag2", data.getString("fundname") + "(代码" + target_fundcode + ")");
					messageData.set("tag3", df.format(Double.parseDouble(apply_money)));
					MessageService.sendMobileMessage(tradeAccount, mobile, MessageConstants.T072, "0", messageData);
				}else{
					messageData.set("tag1", Constants.SMS_TAG1);
					messageData.set("tag2", DateHelper.formatDate(new Date(), "MM月dd日 HH:mm"));
					messageData.set("tag3", data.getString("fundname") + "(代码" + target_fundcode + ")" + df.format(Double.parseDouble(apply_money)) + "元");
					MessageService.sendMobileMessage(tradeAccount, mobile, MessageConstants.T033, "0", messageData);
				}*/
				mav.addObject("message", "订单待确认");
			}
			else if (Constants.ORDER_STATE_01.equalsIgnoreCase(state))
			{
				mav.addObject("success", "2");
				DataRow messageData = new DataRow();
				//基金状态为1表示发行期，认购
				/*if("1".equals(fundStatus))
				{
					messageData.set("tag1", DateHelper.formatDate(new Date(), "MM月dd日 HH:mm"));
					messageData.set("tag2", data.getString("fundname") + "(代码" + target_fundcode + ")");
					messageData.set("tag3", df.format(Double.parseDouble(apply_money)));
					MessageService.sendMobileMessage(tradeAccount, mobile, MessageConstants.T072, "0", messageData);
				}else
				{
					messageData.set("tag1", Constants.SMS_TAG1);
					messageData.set("tag2", DateHelper.formatDate(new Date(), "MM月dd日 HH:mm"));
					messageData.set("tag3", data.getString("fundname") + "(代码" + target_fundcode + ")" + df.format(Double.parseDouble(apply_money)));
					MessageService.sendMobileMessage(tradeAccount, mobile, MessageConstants.T033, "0", messageData);
				}*/
				mav.addObject("message", "订单处理中");
			}
			else if (Constants.ORDER_STATE_02.equalsIgnoreCase(state))
			{
				mav.addObject("success", "1");
				mav.addObject("message", "成功");
			}
			else if (Constants.ORDER_STATE_03.equalsIgnoreCase(state))
			{
				mav.addObject("success", "0");
				mav.addObject("message", "支付失败");
			}
		}else{
			mav.addObject("success", "0");
			mav.addObject("message", "支付失败,"+((DataRow)result.getList("MESSAGE").get(0)).getString("MSG"));
		}
		
		return mav;
	}
	
	/**
	 * 基金认购
	 * @param request
	 * @param response
	 * @return
	 */
	public ModelAndView doFundSubscription(HttpServletRequest request, HttpServletResponse response){
		//当前登录用户
		/*UserInfo user = (UserInfo)request.getSession().getAttribute(WebConstants.LOGIN_USER);
		logger.info("检查是否登录：");
		if(user == null){
			//则跳转登录页面，重新登录——拦截器应该做了这件事
			
		}
		String isTrade = user.getIsTrade();
		String tradeAccount = user.getTradeAccount();
		
		//
		if ("0".equalsIgnoreCase(isTrade) || StringUtils.isEmpty(tradeAccount))
		{
			return new ModelAndView("");  //跳转到开户页面——绑卡页面
		}

		String fundCode = request.getParameter("fundCode");
		if(StringUtils.isBlank(fundCode)){
			response.sendRedirect("fundMarket.do?method=listAllFund");
			return null;
		}*/
		DataRow data = (DataRow)request.getSession().getAttribute(WebConstants.SESSION_FORM_DATA);
		data.set("shareclass", "0");
		
		ModelAndView mav = new ModelAndView("/fundmarket/fundbuy_result_jsp");
		//申请单号
		String requestNo = RandomUtil.randNum(20);
		//网点号
		String branch_code = StringHelper.getParameter(request, "brach_code", "002001");
		//交易发起时间
		String request_time = DateUtil.formatDate(new Date(), "yyyyMMddHHmmss");
		//交易密码
		String password = StringHelper.getParameter(request, "password");
		
		DataRow dr = new DataRow();
		dr.set("ECAP_SERIALNO", requestNo);
		dr.set("BRANCH_CODE", branch_code);
		dr.set("REQUEST_TIME", "20160301140202");
		dr.set("SHARE_CLASS", data.getString("shareclass"));
		dr.set("TRADE_ACCO", data.getString("tradeAccount"));
		dr.set("CASH_ACCO", data.getString("cashAccount"));
		dr.set("OCCUT_AMOUNT", data.getString("applyMoney"));
		dr.set("FUND_CODE", "810015");
		dr.set("CURRENCY_TYPE", "156");
		dr.set("password", MD5Util.MD5(password));
		dr.set("TRADE_TYPE", BusinessConstants.G_FUND_BUSINESSCODE_020);  //认购
		dr.set("TRADE_SOURCE", BusinessConstants.G_OP_SOURCE_CENTER);  //网上交易
		
		DataRow result = null;
		try {
			result = fundTradeService.fundSubscription(dr);
		} catch (Exception e) {
			mav.addObject("success", "0");
			mav.addObject("message", "基金认购失败！"+e.getMessage());
			return mav;
		}
		
		if("0000".equals(result.get("CODE"))){  //成功
			String state = result.getString("ORDER_STATE");
			
			if (Constants.ORDER_STATE_00.equalsIgnoreCase(state))
			{
				mav.addObject("success", "2");
				DataRow messageData = new DataRow();
				//基金状态为1表示发行期，认购
				/*if("1".equals(fundStatus))
				{
					messageData.set("tag1", DateHelper.formatDate(new Date(), "MM月dd日 HH:mm"));
					messageData.set("tag2", data.getString("fundname") + "(代码" + target_fundcode + ")");
					messageData.set("tag3", df.format(Double.parseDouble(apply_money)));
					MessageService.sendMobileMessage(tradeAccount, mobile, MessageConstants.T072, "0", messageData);
				}else{
					messageData.set("tag1", Constants.SMS_TAG1);
					messageData.set("tag2", DateHelper.formatDate(new Date(), "MM月dd日 HH:mm"));
					messageData.set("tag3", data.getString("fundname") + "(代码" + target_fundcode + ")" + df.format(Double.parseDouble(apply_money)) + "元");
					MessageService.sendMobileMessage(tradeAccount, mobile, MessageConstants.T033, "0", messageData);
				}*/
				mav.addObject("message", "订单待确认");
			}
			else if (Constants.ORDER_STATE_01.equalsIgnoreCase(state))
			{
				mav.addObject("success", "2");
				DataRow messageData = new DataRow();
				//基金状态为1表示发行期，认购
				/*if("1".equals(fundStatus))
				{
					messageData.set("tag1", DateHelper.formatDate(new Date(), "MM月dd日 HH:mm"));
					messageData.set("tag2", data.getString("fundname") + "(代码" + target_fundcode + ")");
					messageData.set("tag3", df.format(Double.parseDouble(apply_money)));
					MessageService.sendMobileMessage(tradeAccount, mobile, MessageConstants.T072, "0", messageData);
				}else
				{
					messageData.set("tag1", Constants.SMS_TAG1);
					messageData.set("tag2", DateHelper.formatDate(new Date(), "MM月dd日 HH:mm"));
					messageData.set("tag3", data.getString("fundname") + "(代码" + target_fundcode + ")" + df.format(Double.parseDouble(apply_money)));
					MessageService.sendMobileMessage(tradeAccount, mobile, MessageConstants.T033, "0", messageData);
				}*/
				mav.addObject("message", "订单处理中");
			}
			else if (Constants.ORDER_STATE_02.equalsIgnoreCase(state))
			{
				mav.addObject("success", "1");
				mav.addObject("message", "成功");
			}
			else if (Constants.ORDER_STATE_03.equalsIgnoreCase(state))
			{
				mav.addObject("success", "0");
				mav.addObject("message", "支付失败");
			}
		}else{
			
			mav.addObject("success", "0");
			mav.addObject("message", "支付失败,"+((DataRow)result.getList("MESSAGE").get(0)).getString("MSG"));
		}
		
		return mav;
	}
	
	/**
	  * @throws Exception 
	 * @方法名: 处理http请求，
	  * @参数 @param request
	  * @参数 @param response
	  * @参数 @return   
	  * @返回类型: ModelAndView 
	  * @throws
	 */
	@RequestMapping("/doHttpReq")
	public void doHttpReq(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//商户流水号
		String serviceName = request.getParameter("serviceName") == null ? "" : request.getParameter("serviceName").toString();
		String json = request.getParameter("json") == null ? "" : request.getParameter("json").toString();

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		Map params = null;
		try {
			params = toHashMap(json);
		} catch (Exception e) {
			out.write("请传入json格式数据！");

			out.flush();
			out.close();
			
			return;
			
		}
		ModelAndView mav = new ModelAndView(request.getRequestURI());
		Map result = CallWTC.execute(serviceName, params);
		
		
		out.write(result.get("MSG")+":"+result.get("DEBUGMSG"));
		out.flush();
		out.close();
	}
	
    private static Map<String, Object> toHashMap(Object object)  
    {  
    	Map<String, Object> map = new HashMap<String, Object>();
        //最外层解析
        JSONObject json = JSONObject.fromObject(object);
        for(Object k : json.keySet()){
          Object v = json.get(k); 
          //如果内层还是数组的话，继续解析
          if(v instanceof JSONArray){
            List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
            Iterator<JSONObject> it = ((JSONArray)v).iterator();
            while(it.hasNext()){
              JSONObject json2 = it.next();
              list.add(toHashMap(json2.toString()));
            }
            map.put(k.toString(), list);
          } else {
            map.put(k.toString(), v);
          }
        }
        return map;
    }  
	
	
}
