package com.jhcz.plat.web.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jhcz.base.config.SysConfig;
import com.jhcz.base.util.StringHelper;
import com.jhcz.base.util.security.SecurityHelper;
import com.jhcz.plat.Constants;
import com.jhcz.plat.domain.Result;
import com.jhcz.plat.domain.User;
import com.jhcz.plat.domain.UserPasswordLog;
import com.jhcz.plat.service.LogService;
import com.jhcz.plat.service.UserPasswordLogService;
import com.jhcz.plat.service.UserService;
import com.jhcz.plat.system.SysLibrary;
import com.jhcz.plat.web.form.DynaForm;


/**
 * 描述: ModifyPwdAction.java
 * 版权: Copyright (c) 2015
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2015-11-25
 * 创建时间: 下午9:59:39
 */
@Controller
@RequestMapping("/admin/ModifyPwdAdmin")
public class ModifyPwdAction extends BaseAction
{
    
    @Resource
    LogService logService;
    
    @Resource
    UserPasswordLogService userPasswordLogService;
    
    @Resource
    UserService userService;
    
    @RequestMapping("/doDefault.action")
    public ModelAndView doDefault()
	{
        ModelAndView mv = new ModelAndView("/WEB-INF/views/user/modi_password.jsp");
        if (!SysLibrary.isLogin(getSession()))
        {
            mv.setViewName("/loginAdmin/login.action");
        }
        return mv;
    }
    
    @RequestMapping("/modifyPwd.action")
    @ResponseBody
    public Result modifyPwd(HttpServletRequest request)
    {
        DynaForm form = normalize(request);
        String oldPassword = form.getString("oldPassword");
        String newPassword = form.getString("password");
        int userId = getUserId();
        
        User user = userService.findUserById(userId);
        String password = user.getPassword();
        Result result = new Result();
        String errorInfo = "";
        if (!password.equals(SecurityHelper.getMD5of32Str(oldPassword)))
        {
            errorInfo = "您的旧密码输入不正确";
        }
        
        if (oldPassword.equals(newPassword))
        {
            errorInfo = "对不起，您输入的新密码与旧密码相同，请重新输入！";
        }
        if (StringHelper.isNotEmpty(errorInfo))
        {
            result.setErrorNo(-1);
            result.setErrorInfo(errorInfo);
            return result;
        }
        
        newPassword = SecurityHelper.getMD5of32Str(newPassword);
        User newUser = new User();
        newUser.setPassword(newPassword);
        newUser.setId(userId);
        userService.updateUser(newUser);
        
        if (SysConfig.getInt("system.errorPwdNum") > 0)
        {
            //清除该用户当日密码输错次数
            logService.clearErrorPwdNum(userId);
        }
        
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.POPUP_MODIFYPWD_MESSAGE);
        
        //增加用户密码修改记录
        UserPasswordLog log = new UserPasswordLog();
        log.setCreateBy(user.getUid());
        log.setPassword(newPassword);
        log.setDescription("修改密码成功");
        userPasswordLogService.addLog(log);
        
        addLog("修改密码", "修改密码成功");
        result.setErrorInfo("修改密码成功");
        //获得成功后返回的页面
        return result;
    }
}
