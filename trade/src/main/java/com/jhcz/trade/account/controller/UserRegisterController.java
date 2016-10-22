package com.jhcz.trade.account.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.jhcz.trade.account.biz.constants.Constants;
import com.jhcz.trade.account.biz.service.UserService;
import com.jhcz.trade.account.utils.ScriptHelper;
import com.jhcz.trade.common.util.MD5Util;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("/userRegister")
public class UserRegisterController extends MultiActionController{
	
	@Autowired
	private UserService userService1;
	
	/**
	 * 跳转注册页面
	 * @return
	 */
	@RequestMapping("/toRegister")
	public ModelAndView toRegister(){
		
		ModelAndView mav = new ModelAndView("/user/userRegister_jsp");
		mav.addObject("step", "1");
		
		return mav;
	}
	
	/**
	 * 校验手机验证码
	 * @param request
	 * @return
	 */
	@RequestMapping("/ckMobileTicket")
	public ModelAndView ckMobileTicket(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/user/userRegister_jsp");
		String mobileTicket = request.getParameter("mobileTicket");
		String mobile = request.getParameter("mobile");
		String callBack = request.getParameter("callBack");

		request.getSession(true).setAttribute("mobile", mobile);
		try {
			boolean isMobileRegisted = userService1.checkMobileIsRegist(mobile, "", Constants.CUST_TYPE_01, true,false);
			if (isMobileRegisted)
			{
				JSONObject json = new JSONObject();
				json.put("errorMsg", "该手机号码已被注册");
				ScriptHelper.callback(response, json, callBack);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("step", "2");
		return mav;
	}
	
	
	/**
	 * 输入密码，注册用户
	 * @param request
	 * @return
	 */
	@RequestMapping("/registerUser")
	public ModelAndView registerUser(HttpServletRequest request){
		String password = request.getParameter("password");
		String mobile = (String)request.getSession().getAttribute("mobile");
		Map params = new HashMap();
		
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
			//入参不为空时设置
			/*if(!"".equals(password)){
				pwddata.set("pwd_value", SecurityHelper.getMD5of32Str(password + salt));
				//			pwddata.set("pwd_value", SecurityUtil.encode(password + salt));
				pwddata.set("pwd_salt", salt);
			}*/
			params.put("errorInputCount", 0);
			userService1.savePassword(params);
			request.setAttribute("success", "1");
			
		} catch (Exception e) {
			request.setAttribute("success", "0");
			request.setAttribute("msg", "用户注册失败。");
		}
		
		
		
		ModelAndView mav = new ModelAndView("/user/userRegister_jsp");
		mav.addObject("step", "3");
		return mav;
	}
	
	
}
