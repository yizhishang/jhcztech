package com.jhcz.cms.controller;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jhcz.cms.aspect.MethodLog;
import com.jhcz.cms.base.Constants;
import com.jhcz.cms.base.Result;
import com.jhcz.cms.base.util.StringHelper;
import com.jhcz.cms.model.Site;
import com.jhcz.cms.model.User;
import com.jhcz.cms.service.SiteService;

@Controller
public class LoginController extends BaseController
{
	@Resource
    SiteService siteService;
	
	@RequestMapping("/login.action")
	@MethodLog(remark="进入登录页面")
    public ModelAndView doLogin()
    {
        List<Site> list = siteService.getAllSite();
        ModelAndView mv = new ModelAndView("/login");
        mv.addObject("list", list);
        return mv;
    }
	
	@ResponseBody
	@MethodLog(remark="登录校验")
    @RequestMapping(value = "/loginValidate.action", method = RequestMethod.POST)
    public Result loginValidate(@ModelAttribute("loginForm")
    LoginForm loginForm, String ticket, HttpServletRequest request)
    {
        Result result = new Result(-1);
        String message = "";
        String uid = "";
        String siteno = "";
        try
        {
            //验证验证码
            String ticketInSession = (String) getSession().getAttribute(Constants.TICKET);
            if (StringHelper.isEmpty(ticket) || !ticket.equalsIgnoreCase(ticketInSession))
            {
                throw new LoginFailedException("验证码不对");
            }
            
            //获得用户的账号和密码，并对账号密码进行验证；
            uid = loginForm.getName();
            String password = loginForm.getPassword();
            siteno = request.getParameter("siteno");
            User user = userService.login(uid, password);
            
            HttpSession session = getSession();
            
            //判断密码是否过期
            int pwdValidity = SysConfig.getInt("system.pwdValidity");
            if (!"admin".equals(uid) && pwdValidity > 0)
            {
                //String popupModifyPwdMsg = logService.isPasswordValidity(pwdValidity, uid);
                String popupModifyPwdMsg = passwordLogService.isPasswordValidity(pwdValidity, uid);
                if (popupModifyPwdMsg == null)
                {
                    UserPasswordLog passwordLog = new UserPasswordLog();
                    passwordLog.setCreateBy(user.getUid());
                    passwordLog.setDescription("用户登陆");
                    passwordLog.setPassword(user.getPassword());
                    passwordLogService.addLog(passwordLog);
                }
                if (StringHelper.isNotEmpty(popupModifyPwdMsg))
                {
                    session.setAttribute(Constants.POPUP_MODIFYPWD_MESSAGE, popupModifyPwdMsg);
                }
            }
            
            HashSet<String> siteRights = roleService.findUserSiteRights(user.getId());
            //验证用户登陆的站点是否合法
            if (!"all".equals(user.getSiteNo()) && !(siteRights != null && siteRights.contains(siteno)))
            {
                throw new LoginFailedException("登陆的站点无此用户");
            }
            
            //将用户信息保存到session
            saveUserDataSession(session, user, siteno);
            session.setAttribute(Constants.SESSION_FIRSTLOGIN_IP, IPHelper.getIpAddr(request));//增加请求ip地址到session
        }
        catch (PasswordErrorException ex)
        {
            if (StringHelper.isNotEmpty(uid) && StringHelper.isNotEmpty(siteno))
            {
                addLog(uid, siteno, "系统登录", ex.getMessage());
            }
            
            int errorPwdNum = SysConfig.getInt("system.errorPwdNum");//Configuration.getInt("system.errorPwdNum");//当日允许输入错密码次数
            if (!"admin".equals(uid) && errorPwdNum > 0)
            {
                int userErrorPwdNum = logService.getNowErrorPwdNum(uid);
                if (userErrorPwdNum >= errorPwdNum)
                {
                    userService.closeUser(uid);
                    LoggerUtil.error(this.getClass(), "您已经连续" + errorPwdNum + "次输错密码，您的帐户已被关闭，请与系统管理员联系！");
                }
                else
                {
                    LoggerUtil.error(this.getClass(),
                            StringHelper.toScript("您输入的密码不正确！\n您当日已输错" + userErrorPwdNum + "次密码，请检查后谨慎输入！\n如果当日对同一用户名输错" + errorPwdNum + "次密码，系统将自动关闭该用户。"));
                }
            }
            else
            {
                LoggerUtil.error(this.getClass(), ex.getMessage());
            }
            message = ex.getMessage();
        }
        catch (FistLoginModiPasswordException ex)
        {
            //第一次登录，需要修改密码				
            HttpSession session = request.getSession();
            session.setAttribute(Constants.SESSION_FIRSTLOGIN_UID, uid);
            session.setAttribute(Constants.SESSION_FIRSTLOGIN_SITENO, siteno);
            
            dataMap.put(Constants.POPUP_MODIFYPWD_WINDOW, "1");
            message = ex.getMessage();
            
        }
        catch (LoginFailedException ex)
        {
            LoggerUtil.error(this.getClass(), ex.getMessage());
            message = ex.getMessage();
        }
        
        if (StringHelper.isNotEmpty(message))
        {
            result.setErrorInfo(message);
        }
        else
        {
            result.setErrorNo(0);
        }
        
        return result;
    }
}
