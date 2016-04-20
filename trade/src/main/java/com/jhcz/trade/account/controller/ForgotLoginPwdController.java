package com.jhcz.trade.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.jhcz.trade.account.biz.bean.UserInfo;
import com.jhcz.trade.account.biz.service.UserService;
import com.jhcz.trade.account.utils.WebConstants;
import com.jhcz.trade.common.base.DataRow;
import com.jhcz.trade.common.util.MD5Util;
import com.jhcz.trade.common.util.StringHelper;


@Controller
@RequestMapping("/findPassword")
public class ForgotLoginPwdController extends MultiActionController{
	
	@Autowired
	private UserService userService1;
	
	/**
	 * 跳转注册页面
	 * @return
	 */
	@RequestMapping("/forgetLoginPassword")
	public ModelAndView toRegister(){
		
		ModelAndView mav = new ModelAndView("/user/find_password01_jsp");
		
		return mav;
	}
	
	/**
	 * 校验手机验证码
	 * @param request
	 * @return
	 */
	@RequestMapping("/ckMobileTicket")
	public ModelAndView ckMobileTicket(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/user/find_password02_jsp");
		String ticket = StringHelper.getParameter(request,"ticket");
		String sessionTicket = (String)request.getSession().getAttribute(WebConstants.SESSION_TICKET_TOKEN);
		String mobile = request.getParameter("mobile");
		
		/*if(StringUtils.isBlank(ticket) || !ticket.equals(sessionTicket)){
			mav = new ModelAndView("/user/find_password01_jsp");
			mav.addObject("msg", "验证码错误！");
			
			return mav;
			
		}*/
		request.getSession().setAttribute("mobile", mobile);
		
		return mav;
	}
	

	/**
	 * 重置登录密码
	 * @param request
	 * @return
	 */
	@RequestMapping("/resetLoginPassword")
	public ModelAndView resetLoginPassword(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/user/find_password03_jsp");
		ModelAndView failMav = new ModelAndView("/user/find_password02_jsp");
		
		String password = StringHelper.getParameter(request,"password");
		String password2 = StringHelper.getParameter(request,"password2");
		String mobile = (String)request.getSession().getAttribute("mobile");
		
		if(StringUtils.isBlank(password)){
			failMav.addObject("msg","密码不能为空！");
			
			return failMav;
		}
		
		if(!password.equals(password2)){
			failMav.addObject("msg", "密码不一致！");
			
			return failMav;
		}

		UserInfo user = null;
		try {
			user = userService1.getUserBaseInfoByLoginId(mobile);
		} catch (Exception e) {
			mav.addObject("msg", "系统异常！");
		}
		
		DataRow params = new DataRow();
		if(user == null){
			try {
				String custNo = userService1.generateCustNo();
				params.put("custNo", custNo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			params.put("mobile", mobile);
			params.put("password", MD5Util.MD5(password));
			params.put("custType", "1");

			try {
				userService1.register(params);
				
				//添加用户登录密码
				params.put("pwdType", "0");
				params.put("errorInputCount", 0);
				userService1.savePassword(params);
				request.setAttribute("success", "1");
				
			} catch (Exception e) {
				request.setAttribute("success", "0");
				request.setAttribute("msg", "用户注册失败。");
			}
		}else{
			mav.addObject("success", "1");
			params.put("password", MD5Util.MD5(password));
			params.put("custNo", user.getCustNo());
			try {
				userService1.resetPassword(params);
			} catch (Exception e) {
				mav.addObject("success", "0");
				mav.addObject("msg", "找回密码失败！");
				return mav;
			}
			mav.addObject("msg", "操作成功！");
		}
		
		return mav;
	}
	
	
}
