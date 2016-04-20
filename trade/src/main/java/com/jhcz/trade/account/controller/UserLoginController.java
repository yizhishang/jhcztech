package com.jhcz.trade.account.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.jhcz.trade.account.biz.bean.UserInfo;
import com.jhcz.trade.account.biz.constants.Constants;
import com.jhcz.trade.account.biz.service.UserService;
import com.jhcz.trade.account.utils.ScriptHelper;
import com.jhcz.trade.account.utils.WebConstants;
import com.jhcz.trade.common.util.MD5Util;

import net.sf.json.JSONObject;


@Controller
@RequestMapping("/userLogin")
public class UserLoginController extends MultiActionController{
	
	@Autowired
	private UserService userService1;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping("/toLogin")
	public ModelAndView toLogin(){
		
		ModelAndView mav = new ModelAndView("/login_jsp");
		mav.addObject("step", "1");
		
		return mav;
		
	}
	
	/**
	 * 跳转注册页面
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response){
		
		Map result = new HashMap();
		ModelAndView mav = new ModelAndView("/login_jsp");

		try
		{
			HttpSession session = request.getSession();
			String loginId = request.getParameter("login_id");
			String ticket = StringFilter(request.getParameter("ticket"));
			int errortimes;
			try {
				errortimes = new Integer(
						(String) session.getAttribute(WebConstants.SESSION_LOGIN_PWD_ERROR_TIMES));
			} catch (Exception e) {
				errortimes = 0;
			}
			String sticket = (String)session.getAttribute("ticket");
//				String ip = IPHelper.getIpAddr(getRequest());//IP
			session.removeAttribute("ticket");
			if (errortimes >= 3 && (StringUtils.isEmpty(ticket) || !ticket.equalsIgnoreCase(sticket)))
			{
				result = setReMap(-10, "验证码不正确");
			}
			else if (StringUtils.isEmpty(loginId))
			{
				result = setReMap(-1, "请输入登录账号");
			}
			else
			{
				// 根据 LOGINID 取账号信息
				long start = System.currentTimeMillis();
				UserInfo user = userService1.getUserBaseInfoByLoginId(loginId);
				long end = System.currentTimeMillis();
				logger.info("登录查询用户：" + (end - start) + "毫秒");
				if (user != null)
				{
					//屏蔽机构用户登陆--陈智--2016-1-29版本
					//判断用户是不是机构用户并且是使用手机号码登陆
					if (isMoblie(loginId)&&"0".equals(user.getLoginByMobile()))
					{
						result = setReMap(-2, "用户不能使用电话号码登陆！");
						return null;
					}
					String custno = user.getCustNo() == null ?"" :user.getCustNo();
					//密码验证
					String pwd = request.getParameter("password");
					if (StringUtils.isEmpty(pwd))
					{
						result = setReMap(-2, "请输入密码");
					}
					else if (Constants.USER_INFO_STATE_ACTIVATE.equalsIgnoreCase(user.getState()))
					{
						result = setReMap(2, "账户未激活！！");
						return null;
					}
					else
					{
						start = System.currentTimeMillis();
						Map loginresult = userService1.userLogin(user,loginId, pwd, "0");
						end = System.currentTimeMillis();
						logger.info("登录验证客户密码：" + ( end - start) + "毫秒");
						if (loginresult != null)
						{
							String loginState = (String)loginresult.get("loginState");
							
							if ("0".equalsIgnoreCase(loginState))
							{
								Subject subject = SecurityUtils.getSubject();
								UsernamePasswordToken token = new UsernamePasswordToken(loginId, MD5Util.MD5(pwd));
//								Session sessions = subject.getSession();
//								sessions.setAttribute(Constants.SESSION_USER, user);
								token.setRememberMe(true);
								subject.login(token);
								
								session.removeAttribute(WebConstants.SESSION_LOGIN_PWD_ERROR_TIMES);
								
								session.setAttribute(WebConstants.LOGIN_USER, user);
								result = setReMap(0, "登录成功");
								
								mav = new ModelAndView("/index_jsp");
								mav.addObject("userInfo", result);
								return mav;
							}
							else if ("1".equalsIgnoreCase(loginState))
							{
								result = setReMap(1, "账户已锁定");
							}
							else if ("2".equalsIgnoreCase(loginState))
							{
								result = setReMap(2, "账户未激活！");
							}
							else if ("3".equalsIgnoreCase(loginState))
							{
								result = setReMap(3, "账户已注销！");
								
							}
							else if ("4".equalsIgnoreCase(loginState))
							{
								session.setAttribute("pwdErrorCount", (Integer)loginresult.get("error_times"));
								result = setReMap(4, "账号或密码错误！");
							}
							else
							{
								result = setReMap(-1, "账号或密码错误！");
							}
						}
						else
						{
							result = setReMap(-1, "账号或密码错误！");
						}
					}
				}
				else
				{
					result = setReMap(-1, "账号或密码错误！");
				}
			}
		}
		catch (Exception e)
		{
			logger.error("", e);
			result = setReMap(-1000, "登录失败!");
		}
//		WriteCallbackToPage(result, callback);
		
		mav.addObject("result",result);
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
		params.put("password", password);
		params.put("custType", Constants.CUST_TYPE_01);
		
		try {
			userService1.register(params);
			
			//添加用户登录密码
			params.put("pwdType", Constants.PD_TYPE_LOGIN);
			//入参不为空时设置
			/*if(!"".equals(password)){
				pwddata.set("pwd_value", SecurityHelper.getMD5of32Str(password + salt));
				//			pwddata.set("pwd_value", SecurityUtil.encode(password + salt));
				pwddata.set("pwd_salt", salt);
			}*/
			params.put("errorInputCount", 0);
			userService1.savePassword(params);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		ModelAndView mav = new ModelAndView("/user/userRegister_jsp");
		mav.addObject("step", "3");
		return mav;
	}
	
	// 过滤特殊字符  
	private String StringFilter(String str) throws PatternSyntaxException
	{
		if(StringUtils.isBlank(str)){
			return "";
		}
		// 只允许字母和数字       
		// String   regEx  =  "[^a-zA-Z0-9]";                     
		// 清除掉所有特殊字符  
		String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}
	
	protected Map setReMap(int errNo, String errInfo)
	{
		Map reMap = new HashMap();
		reMap.put("error_no", errNo);
		reMap.put("error_info", errInfo);
		return reMap;
	}
	
	public boolean isMoblie(String str)
    {
	    if (StringUtils.isEmpty(str))
	    {
	      return false;
	    }
	    Pattern pattern = Pattern.compile("^(13|14|15|17|18)[0-9]{9}$");
	    Matcher matcher = pattern.matcher(str);
	
	    return matcher.matches();
   }
	
	/**
	 * 管理后台账户登出
	 * @param req
	 * @param resp
	 * @return
	 */
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest req, HttpServletResponse resp) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		ModelAndView mav =new ModelAndView();
		mav.setViewName("login_jsp");
		return mav;
	}
	
}
