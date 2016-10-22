package com.jhcz.trade.personInfoManage.contorller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.jhcz.trade.account.biz.bean.UserInfo;
import com.jhcz.trade.account.utils.WebConstants;
import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.exception.BusinessException;
import com.jhcz.trade.common.exception.ParameterException;
import com.jhcz.trade.personInfoManage.service.IBillSendWayService;
import com.jhcz.trade.personInfoManage.service.IElecSignBookService;
import com.jhcz.trade.personInfoManage.service.IRiskAsessService;

/**
 * 
 * @Description：用户信息管理Controller
 * @author 邱文豪
 * @created 2016年3月29日 下午1:36:49
 */
@Controller
@RequestMapping("/custInfo.do")
public class CustInfoController extends MultiActionController {

	private Logger logger = Logger.getLogger(CustInfoController.class);
	@Autowired 
	private IBillSendWayService billSendWayService;
	@Autowired 
	private IRiskAsessService riskAsessService;
	@Autowired 
	private IElecSignBookService elecSignBookService;

	/**
	  * @方法名: doCustInfoUpdate
	  * @描述: 更新客户信息：1.通讯地址修改 2.电子邮箱验证 3.修改账单寄送方式 4.预留验证信息
	  * @参数 @param request
	  * @参数 @param response
	  * @参数 @param model
	  * @参数 @return   
	  * @返回类型: ModelAndView 
	  * @throws
	  * @时间:2016-4-13下午3:18:15
	  * @作者: zhonghang
	 */
	@RequestMapping(params="method=doCustInfoUpdate")
	public ModelAndView doCustInfoUpdate(HttpServletRequest request, HttpServletResponse response,Model model) {
		//1.通讯地址
		String address = request.getParameter("address");
		//2.电子邮箱
		String mail = request.getParameter("mail");
		//3.对账单寄送方式
		String deliver_type = request.getParameter("deliver_type");
		//4.预留验证信息
		String reserve_info = request.getParameter("reserve_info") ;
		//用户编号
		UserInfo user = (UserInfo)request.getSession().getAttribute(WebConstants.LOGIN_USER);
		String cust_no = null;
		if (user != null) {
			cust_no = user.getCustNo();
		}
		
		DataRow params = new DataRow();
		params.set("BRANCH_CODE", "002001");
		if (address != null) {
			params.set("ADDRESS", address);
		}else{
			params.set("ADDRESS", "NULL");
		} 
		if (mail != null ) {
			params.set("MAIL", mail);
		}else{
			params.set("MAIL", "NULL");
		}
		if (deliver_type != null ) {
			params.set("DELIVER_TYPE", deliver_type);
		}else{
			params.set("DELIVER_TYPE", "NULL");
		}
		if (reserve_info != null ) {
			params.set("RESERVE_INFO", reserve_info);
		}else{
			params.set("RESERVE_INFO", "NULL");
		}
		if (cust_no != null ) {
			params.set("CUST_NO", cust_no);
		}
		Map<String, Object> map = null;
		try {
			logger.info("+++++++++++++++++++++客户资料修改 start+++++++++++++++++++++++++");
			map = billSendWayService.modifyCustInfo(params);
			logger.info("+++++++++++++++++++++客户资料修改 end+返回结果为:"+map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("/personInfoManage/billSendWay_jsp");
		return mav;
	}
	
	/**
	 * 
	 * @Description：1.通讯地址修改 2.电子邮箱验证 3.修改账单寄送方式 4.预留验证信息
	 * @author 邱文豪 
	 * @created 2016年3月29日 下午2:21:05 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*@RequestMapping(params="method=doCustInfoUpdate")
	public ModelAndView doCustInfoUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/personInfoManage/billSendWay_jsp");
		//1.通讯地址
		String address = request.getParameter("address") == null ? "" : request.getParameter("address").toString();
		//2.电子邮箱
		String email = request.getParameter("email") == null ? "" : request.getParameter("email").toString();
		//3.对账单寄送方式
		String send_way = request.getParameter("send_way") == null ? "" : request.getParameter("send_way").toString();
		//4.预留验证信息
		String reserve_info = request.getParameter("reserve_info") == null ? "" : request.getParameter("reserve_info").toString();
		//用户编号
		UserInfo user = (UserInfo)request.getSession().getAttribute(WebConstants.LOGIN_USER);
		String user_no = user.getCustNo();
		
		*//**
		 * 校验入参
		 *//*
		if(StringUtils.isBlank(address)){
			throw new ParameterException("9999", "通讯地址不能为空！");
		}
		if(StringUtils.isBlank(email)){
			throw new ParameterException("9999", "电子邮箱不能为空！");
		}
		if(StringUtils.isBlank(send_way)){
			throw new ParameterException("9999", "寄送方式不能为空！");
		}
		if(StringUtils.isBlank(reserve_info)){
			throw new ParameterException("9999", "预留验证信息不能为空！");
		}
		
		if(StringUtils.isBlank(user_no)){
			throw new ParameterException("9999", "用户编号不能为空！");
		}
		
		DataRow dr = new DataRow();
		dr.set("CUST_NO", user_no);
		if(!StringUtils.isBlank(address)){
			dr.set("ADDRESS", address);
		}
		if(!StringUtils.isBlank(email)){
			dr.set("MAIL", email);
		}
		if(!StringUtils.isBlank(email)){
			dr.set("DELIVER_TYPE", send_way);
		}
		if(!StringUtils.isBlank(email)){
			dr.set("RESERVE_INFO", reserve_info);
		}
		Map result = null;
		try {
			result = billSendWayService.updateBillSendWay(dr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("msg", result.get("MSG")+":"+result.get("DEBUGMSG"));
		
		if(result.get("MSG") != null && !result.get("MSG").toString().contains("成功")){
			mav.addObject("obj",dr);
		}else{
			throw new BusinessException("9999", "修改失败！");
		}
		
		return mav;
	}*/
	
	/**
	 * 
	 * @Description：风险测评
	 * @author 邱文豪
	 * @created 2016年3月29日 下午2:21:05 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=doCalcRisk")
	public ModelAndView doCalcRisk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/personInfoManage/riskAsess_jsp");
		
		Map questionAndAnswerNo = request.getParameterMap();//问题和答案编号
		String risk_level = request.getParameter("risk_level") == null ? "" : request.getParameter("risk_level").toString();//风险能力承受级别
		//用户编号
		UserInfo user = (UserInfo)request.getSession().getAttribute(WebConstants.LOGIN_USER);
		String user_no = user.getCustNo();
		
		/**
		 * 校验入参
		 */
		if(questionAndAnswerNo.size()==0){
			throw new ParameterException("9999", "问题和答案编号不能为空！");
		}
		
		if(StringUtils.isBlank(user_no)){
			throw new ParameterException("9999", "用户编号不能为空！");
		}
		
		if(StringUtils.isBlank(risk_level)){
			throw new ParameterException("9999", "风险能力承受级别不能为空！");
		}
		
		DataRow dr = new DataRow();
		dr.putAll(questionAndAnswerNo);
		dr.set("user_no", user_no);
		dr.set("CUST_RISK_LEVEL", risk_level);
		
		Map result = null;
		try {
			result = riskAsessService.calcRisk(dr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("msg", result.get("MSG")+":"+result.get("DEBUGMSG"));
		
		if(result.get("MSG") != null && !result.get("MSG").toString().contains("成功")){
			mav.addObject("obj",dr);
		}else{
			throw new BusinessException("9999", "风险测评失败！");
		}
		
		return mav;
	}
	
	/**
	 * 
	 * @Description：签署电子签名书
	 * @author 邱文豪
	 * @created 2016年3月30日 上午10:40:00 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params="method=doAgreeSign")
	public ModelAndView doAgreeSign(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/personInfoManage/agreeSign_jsp");
		
		
		UserInfo user = (UserInfo)request.getSession().getAttribute(WebConstants.LOGIN_USER);
		String user_name = user.getName();//用户姓名
		String id_code = user.getIdentityNum();//证件号码
		String sign_user = user_name+"|"+id_code;//签订人
		String trade_password = request.getParameter("trade_password") == null ? "" : request.getParameter("trade_password").toString();//风险能力承受级别
	
		
		/**
		 * 校验入参
		 */
		if(StringUtils.isBlank(sign_user)){
			throw new ParameterException("9999", "签订人不能为空！");
		}
		
		if(StringUtils.isBlank(trade_password)){
			throw new ParameterException("9999", "交易密码不能为空！");
		}
		
		DataRow dr = new DataRow();
		dr.set("trade_password", trade_password);
		dr.set("sign_user", sign_user);
		
		Map result = null;
		try {
			result = elecSignBookService.agreeSign(dr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("msg", result.get("MSG")+":"+result.get("DEBUGMSG"));
		
		if(result.get("MSG") != null && !result.get("MSG").toString().contains("成功")){
			mav.addObject("obj",dr);
		}else{
			throw new BusinessException("9999", "签订失败！");
		}
		
		return mav;
	}
	
	/**
	 * 
	 * @Description：取消签署电子签名书
	 * @author 邱文豪
	 * @created 2016年3月30日 上午10:40:00 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params="method=doUnAgreeSign")
	public ModelAndView doUnAgreeSign(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/personInfoManage/unAgreeSign_jsp");
		
		
		UserInfo user = (UserInfo)request.getSession().getAttribute(WebConstants.LOGIN_USER);
		String user_name = user.getName();//用户姓名
		String id_code = user.getIdentityNum();//证件号码
		String sign_user = user_name+"|"+id_code;//签订人
		String trade_password = request.getParameter("trade_password") == null ? "" : request.getParameter("trade_password").toString();//风险能力承受级别
	
		
		/**
		 * 校验入参
		 */
		if(StringUtils.isBlank(sign_user)){
			throw new ParameterException("9999", "签订人不能为空！");
		}
		
		if(StringUtils.isBlank(trade_password)){
			throw new ParameterException("9999", "交易密码不能为空！");
		}
		
		DataRow dr = new DataRow();
		dr.set("trade_password", trade_password);
		dr.set("sign_user", sign_user);
		
		Map result = null;
		try {
			result = elecSignBookService.unAgreeSign(dr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("msg", result.get("MSG")+":"+result.get("DEBUGMSG"));
		
		if(result.get("MSG") != null && !result.get("MSG").toString().contains("成功")){
			mav.addObject("obj",dr);
		}else{
			throw new BusinessException("9999", "签订失败！");
		}
		
		return mav;
	}
}
