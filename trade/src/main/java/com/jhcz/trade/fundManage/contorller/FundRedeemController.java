package com.jhcz.trade.fundManage.contorller;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.jhcz.trade.account.biz.bean.UserInfo;
import com.jhcz.trade.account.utils.WebConstants;
import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.constant.fund.BusinessConstants;
import com.jhcz.trade.common.util.DateUtil;
import com.jhcz.trade.common.util.MD5Util;
import com.jhcz.trade.common.util.StringHelper;
import com.jhcz.trade.common.util.UuidUtil;
import com.jhcz.trade.fundManage.bean.FundInfo;
import com.jhcz.trade.fundManage.service.FundService;
import com.jhcz.trade.fundManage.service.FundTradeService;

@Controller
@RequestMapping("/fundRedeem")
public class FundRedeemController extends MultiActionController{
	
	Logger logger = Logger.getLogger(FundRedeemController.class);
	
	@Autowired
	private FundService fundService;
	
	@Autowired
	private FundTradeService fundTradeService;
	
	/**
	 * 可以赎回的基金列表（在途交易）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/redeemFund")
	public ModelAndView redeemFund(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mav = new ModelAndView("/fund");
		
		try
		{
			HttpSession session = request.getSession();
			UserInfo user = (UserInfo)session.getAttribute(WebConstants.LOGIN_USER);
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
			DecimalFormat df = new DecimalFormat("#0.00");
			df.setRoundingMode(RoundingMode.DOWN);
			
			//交易账号
			//基金份额
			//基金投资记录查询
			DataRow params = new DataRow();
			params.set("custNo", user.getCustNoOfSale());
			params.set("brachCode", WebConstants.FUND_ONLINE_BRACH_CODE);

			List<DataRow> invests = fundService.queryFundInvest(params);
			
			
//			return new ActionResult("/WEB-INF/views/fund_management/fund_redeem.ftl");
		}
		catch (Exception e)
		{
			logger.error("", e);
		}
		return null;
	}
	
	
	/**
	 * 点击赎回按钮，进入赎回详情页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/doFundRedeem")
	public ModelAndView doFundRedeem(HttpServletRequest request,HttpServletResponse response){
		

		try
		{
			if ("POST".equalsIgnoreCase(request.getMethod()))   //赎回确认
			{
				String fundname = StringHelper.getParameter(request, "fundname"); //基金名称
				String fundcode = StringHelper.getParameter(request, "fundcode"); //基金代码
				String redeem_copys = StringHelper.getParameter(request, "redeem_copys");
				String capital = StringHelper.getParameter(request, "capital");
				String share_type = StringHelper.getParameter(request, "share_type");//份额类别
				String largeredemptionflag = StringHelper.getParameter(request, "largeredemptionflag");//巨额赎回标志
				String redeem_copysStr = StringHelper.milliFormatStr(redeem_copys);
				String tradeAccount = StringHelper.getParameter(request, "tradeAccount");
				String cashAccount = StringHelper.getParameter(request, "cashAccount");
				
				DataRow redeemInfo = new DataRow();  //赎回信息
				redeemInfo.set("fundname", fundname);
				redeemInfo.set("fundcode", fundcode);
				redeemInfo.set("redeem_copys", redeem_copys);
				redeemInfo.set("redeem_copysStr", redeem_copysStr);
				redeemInfo.set("capital", capital);
				redeemInfo.set("share_type", share_type);
				redeemInfo.set("largeredemptionflag", largeredemptionflag);
				redeemInfo.set("tradeAccount", tradeAccount);
				redeemInfo.set("cashAccount", cashAccount);
				request.setAttribute("fundInfo", redeemInfo);
				request.getSession().setAttribute(WebConstants.SESSION_FORM_DATA, redeemInfo);
				return null;
				//new ActionResult("/WEB-INF/views/fund_management/fund_redeem_2.ftl");
			}else{
				//查询基金信息
				String fund_code = StringHelper.getParameter(request, "fund_code");
				FundInfo fundInfo = fundService.getFundByCode(fund_code);
				if (fundInfo == null)
				{
					return redeemFund(request,response);
				}
				//查询基金可用份额
				/*List<DataRow> fund_share = FundService.getFundShare(tradeAccount, fund_code);
				if (fund_share == null)
				{
					return redeemFund(request,response);
				}*/
				// 查询交易限制——赎回限制
				/*DataRow limit = FundService.getFundTradeLimit(tradeAccount, fund_code, null, null, BusinessConstants.G_FUND_BUSINESSCODE_024);
				if (limit != null)
				{
					this.setAttribute("max_value", limit.getString("maxTransAmt"));
					this.setAttribute("min_value", limit.getString("minTransAmt"));
				}
				else
				{
					this.setAttribute("max_value", "0");
					this.setAttribute("min_value", "0");
				}
				
				//检查账号状态
				AccountService.checkCustAcctState(getRequest(), tradeAccount, fund_code);
				
				this.setAttribute("fundInfo", fundInfo);
				this.setAttribute("fund_share", fund_share.get(0));
				return new ActionResult("/WEB-INF/views/fund_management/fund_redeem_1.ftl");*/
			}
		}
		catch (Exception e)
		{
			logger.error("", e);
		}
		return null;
		
	}
	
	/**
	 * 确认赎回
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/doFundRedeemCofirm")
	public ModelAndView doFundRedeemConfirm(HttpServletRequest request,HttpServletResponse response){

		DataRow data = (DataRow)request.getSession().getAttribute(WebConstants.SESSION_FORM_DATA);
		if (data == null)
		{
			return redeemFund(request,response);
		}
		UserInfo user = (UserInfo)request.getSession().getAttribute(WebConstants.LOGIN_USER);
		String custNo = user.getCustNo();
		
		String password = StringHelper.getParameter(request, "password");
		//申请单号
		String requestNo = UuidUtil.createSerialNumber();
		//交易发起时间
		String request_time = DateUtil.formatDate(new Date(), "yyyyMMddHHmmss");
		//币种
		String currency_type = request.getParameter("currency_type") == null ? "156" : request.getParameter("currency_type").toString();
		
		DataRow dr = new DataRow();
		dr.set("ECAP_SERIALNO", requestNo);
		dr.set("REQUEST_TIME", request_time);
		dr.set("BRANCH_CODE", WebConstants.FUND_ONLINE_BRACH_CODE);
		dr.set("OCCUR_SHARE", data.getString("capital"));
		dr.set("CURRENCY_TYPE", currency_type);
		dr.set("LARGE_REDEEM_FLAG", data.getString("largeredemptionflag"));
		dr.set("SHARE_CLASS", data.getString("shareclass"));
		dr.set("TRADE_ACCO", data.getString("tradeAccount"));
		dr.set("CASH_ACCO", data.getString("cashAccount"));
		dr.set("FUND_CODE", data.getString("fundCode"));
		dr.set("CURRENCY_TYPE", "156");
		dr.set("password", MD5Util.MD5(password));
		dr.set("TRADE_TYPE", BusinessConstants.G_FUND_BUSINESSCODE_024);  //赎回
		dr.set("TRADE_SOURCE", BusinessConstants.G_OP_SOURCE_ONLINE);  //网上交易
		
		ModelAndView mav = new ModelAndView("/fundmarket/fundredeem_result_jsp");
		Map result = null;
		try {
			result = fundTradeService.fundRedemption(dr);
		} catch (Exception e) {
			mav.addObject("success", "0");
			mav.addObject("message", "基金认购失败！"+e.getMessage());
			return mav;
		}
		
		mav.addObject("msg", result.get("MSG")+":"+result.get("DEBUGMSG"));
		if(result.get("MSG") != null && !result.get("MSG").toString().contains("成功")){
			mav.addObject("obj",dr);
		}
		return mav;
		
		/*Result result = FundService.fundRedeem(tradeAccount, custno, fundcode, redeem_copys, share_type, largeredemptionflag, password,PropertiesUtil.getString("ots.pc"));
		if (result != null && result.getErr_no() == 0)
		{
			//赎回申请成功，发送短信。
			DecimalFormat df = new DecimalFormat(",##0.00");
			df.setRoundingMode(RoundingMode.DOWN);
			DataRow messageData = new DataRow();
			messageData.set("tag1", Constants.SMS_TAG1);
			messageData.set("tag2", DateHelper.formatDate(new Date(), "MM月dd日 HH:mm"));
			messageData.set("tag3", data.getString("fundname") + "(代码" + fundcode + ")" + df.format(Double.parseDouble(redeem_copys)) + "份");
			messageData.set("tag4", "1");
			MessageService.sendMobileMessage(tradeAccount, mobile, MessageConstants.T037, "0", messageData);
			
			this.setAttribute("success", "1");
			
			DataRow dr = result.getData();
			// 查询下一个交易日
			DataRow tDay = SystemParamService.getWorkDay();
			if (dr != null && com.thinkive.base.util.StringHelper.isNotEmpty(dr.getString("predict_condate")))
			{
				String next_workday = dr.getString("predict_condate");
				next_workday = DateHelper.formatDate(DateHelper.parseString(next_workday, "yyyyMMdd"), "yyyy年MM月dd日");
				this.setAttribute("next_workday", next_workday);
			}
			else if (tDay != null)
			{
				String next_workday = tDay.getString("next_workday");
				next_workday = DateHelper.formatDate(DateHelper.parseString(next_workday, "yyyyMMdd"), "yyyy年MM月dd日");
				this.setAttribute("next_workday", next_workday);
			}
			if (dr != null && com.thinkive.base.util.StringHelper.isNotEmpty(dr.getString("trans_date")) && com.thinkive.base.util.StringHelper.isNotEmpty(dr.getString("submit_time")))
			{
				String trans_date = dr.getString("trans_date");
				String submit_time = dr.getString("submit_time");
				String workday = trans_date + submit_time;
				workday = DateHelper.formatDate(DateHelper.parseString(workday, "yyyyMMddHHmmss"), "yyyy年MM月dd日 HH:mm");
				this.setAttribute("workday", workday);
			}
			else if (tDay != null)
			{
				String workday = tDay.getString("workday");
				workday = DateHelper.formatDate(DateHelper.parseString(workday, "yyyyMMdd"), "yyyy年MM月dd日 15:00");
				this.setAttribute("workday", workday);
			}
		}
		else
		{
			this.setAttribute("success", "0");
			this.setAttribute("message", ErrorCodeManage.getErrorMessage(result, "基金赎回失败,请稍后重试！"));
		}*/

	
	}
}
