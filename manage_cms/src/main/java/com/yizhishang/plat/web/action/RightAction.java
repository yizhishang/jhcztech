package com.yizhishang.plat.web.action;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yizhishang.base.util.ScriptHelper;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-26
 * 创建时间: 12:09:15
 */
@Controller
@RequestMapping("/admin/rightAdmin")
public class RightAction extends BaseAction
{
    
    /**
     * 缺省的操作(function=""时调用)
     * @return
     */
    @Override
    @RequestMapping("/default.action")
    public ModelAndView doDefault()
    {
        //获得相关数据并放主dataMap中
        //袁永君 2009年09月15日 18:58:00 修改，验证用户密码强度，如果不符合要求则跳转到密码修改页面
    	HttpSession session = getSession();
    	Integer pwstr=(Integer)session.getAttribute("pwstr");
    	session.removeAttribute("pwstr");
    	if(pwstr!=null&&pwstr.intValue()<3){
            ScriptHelper.alert(getResponse(), "您的密码安全级别未达到要求，请及时修改密码！", "user.action?function=modiPassword&siteno=main");
            return null;
    	}
        mv.setViewName("/WEB-INF/views/right.jsp");
        return mv;
    }
}
