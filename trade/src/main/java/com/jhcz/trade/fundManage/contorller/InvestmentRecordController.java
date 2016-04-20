package com.jhcz.trade.fundManage.contorller;

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
import com.jhcz.trade.account.utils.WebConstants;
import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.exception.BusinessException;
import com.jhcz.trade.common.exception.ParameterException;
import com.jhcz.trade.fundManage.service.IInvestmentRecordService;

/**
 * 
 * @Description：投资记录Controller（资金账户交易明细、基金投资记录、在途资金查询）
 * @author 邱文豪
 * @created 2016年3月29日 下午1:36:49
 */
@Controller
@RequestMapping("/investmentRecord")
public class InvestmentRecordController extends MultiActionController {

	private Logger logger = Logger.getLogger(InvestmentRecordController.class);
	@Autowired 
	private IInvestmentRecordService investmentRecordService;

	/**
	 * 
	 * @Description：1.资金账户交易明细
	 * @author 邱文豪
	 * @created 2016年3月29日 下午2:21:05 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doSelectCapitalAccountTradeDetail")
	public ModelAndView doSelectCapitalAccountTradeDetail(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/fundManage/capitalAccountTradeDetail_jsp");
		//1.交易时间
		String trade_date = request.getParameter("trade_date") == null ? "" : request.getParameter("trade_date").toString();
		
		//2.交易类型
		String trade_type = request.getParameter("trade_type") == null ? "" : request.getParameter("trade_type").toString();
		
		//3.交易结果
		String trade_result = request.getParameter("trade_result") == null ? "" : request.getParameter("trade_result").toString();
		
		UserInfo user = (UserInfo)request.getSession().getAttribute(WebConstants.LOGIN_USER);
		//用户编号
		String user_no = user.getCustNo();
		//交易帐号
		String trade_account = user.getTradeAccount();
		
		/**
		 * 校验入参
		 */
		if(StringUtils.isBlank(user_no)){
			throw new ParameterException("9999", "用户编号不能为空！");
		}
		
		if(StringUtils.isBlank(trade_account)){
			throw new ParameterException("9999", "交易帐号不能为空！");
		}
		
		DataRow dr = new DataRow();
		dr.set("trade_date", trade_date);
		dr.set("trade_type", trade_type);
		dr.set("trade_result", trade_result);
		dr.set("user_no", user_no);
		dr.set("trade_account", trade_account);
		
		Map result = null;
		try {
			result = investmentRecordService.selectCapitalAccountTradeDetail(dr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("msg", result.get("MSG")+":"+result.get("DEBUGMSG"));
		
		if(result.get("MSG") != null && !result.get("MSG").toString().contains("成功")){
			mav.addObject("obj",dr);
		}else{
			throw new BusinessException("9999", "查询失败！");
		}
		
		return mav;
	}
	
	/**
	 * 
	 * @Description：2.基金投资记录
	 * @author 邱文豪
	 * @created 2016年3月29日 下午2:21:05 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doSelectFundInvestRecords")
	public ModelAndView doSelectFundInvestRecords(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/fundManage/fundInvestRecords_jsp");
		//1.交易时间
		String trade_date = request.getParameter("trade_date") == null ? "" : request.getParameter("trade_date").toString();
		
		//2.交易类型
		String trade_type = request.getParameter("trade_type") == null ? "" : request.getParameter("trade_type").toString();
		
		//3.交易结果
		String trade_result = request.getParameter("trade_result") == null ? "" : request.getParameter("trade_result").toString();
		
		//4.基金代码
		String fund_code = request.getParameter("fund_code") == null ? "" : request.getParameter("fund_code").toString();
		
		UserInfo user = (UserInfo)request.getSession().getAttribute(WebConstants.LOGIN_USER);
		//用户编号
		String user_no = user.getCustNo();
		//交易帐号
		String trade_account = user.getTradeAccount();
		
		/**
		 * 校验入参
		 */
		if(StringUtils.isBlank(user_no)){
			throw new ParameterException("9999", "用户编号不能为空！");
		}
		
		if(StringUtils.isBlank(trade_account)){
			throw new ParameterException("9999", "交易帐号不能为空！");
		}
		
		DataRow dr = new DataRow();
		dr.set("trade_date", trade_date);
		dr.set("trade_type", trade_type);
		dr.set("trade_result", trade_result);
		dr.set("fund_code", fund_code);
		dr.set("user_no", user_no);
		dr.set("trade_account", trade_account);
		
		Map result = null;
		try {
			result = investmentRecordService.selectFundInvestRecords(dr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("msg", result.get("MSG")+":"+result.get("DEBUGMSG"));
		
		if(result.get("MSG") != null && !result.get("MSG").toString().contains("成功")){
			mav.addObject("obj",dr);
		}else{
			throw new BusinessException("9999", "查询失败！");
		}
		
		return mav;
	}
	
	/**
	 * 
	 * @Description：3.在途资金查询
	 * @author 邱文豪
	 * @created 2016年3月29日 下午2:21:05 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doSelectOnTheWayCapital")
	public ModelAndView doSelectOnTheWayCapital(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/fundManage/onTheWayCapital_jsp");
		//1.交易申请时间
		String trade_application_date = request.getParameter("trade_application_date") == null ? "" : request.getParameter("trade_application_date").toString();
		
		//2.基金代码(页面显示为基金名称，后台传参为基金代码）
		String fund_code = request.getParameter("fund_code") == null ? "" : request.getParameter("fund_code").toString();
		
		//3.交易类型
		String trade_type = request.getParameter("trade_type") == null ? "" : request.getParameter("trade_type").toString();
		
		UserInfo user = (UserInfo)request.getSession().getAttribute(WebConstants.LOGIN_USER);
		//用户编号
		String user_no = user.getCustNo();
		//交易帐号
		String trade_account = user.getTradeAccount();
		
		/**
		 * 校验入参
		 */
		if(StringUtils.isBlank(user_no)){
			throw new ParameterException("9999", "用户编号不能为空！");
		}
		
		if(StringUtils.isBlank(trade_account)){
			throw new ParameterException("9999", "交易帐号不能为空！");
		}
		
		DataRow dr = new DataRow();
		dr.set("trade_application_date", trade_application_date);
		dr.set("fund_code", fund_code);
		dr.set("trade_type", trade_type);
		dr.set("CUST_NO", user_no);
		dr.set("TRADE_ACCO", trade_account);
		
		Map result = null;
		try {
			result = investmentRecordService.selectOnTheWayCapital(dr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("msg", result.get("MSG")+":"+result.get("DEBUGMSG"));
		
		if(result.get("MSG") != null && !result.get("MSG").toString().contains("成功")){
			mav.addObject("obj",dr);
		}else{
			throw new BusinessException("9999", "查询失败！");
		}
		
		return mav;
	}
	
}
