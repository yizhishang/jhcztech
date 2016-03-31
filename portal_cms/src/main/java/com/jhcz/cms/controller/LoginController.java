package com.jhcz.cms.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jhcz.cms.aspect.MethodLog;
import com.jhcz.cms.base.Result;
import com.jhcz.cms.model.Site;
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
    public Result loginValidate(String ticket, HttpServletRequest request)
    {
        Result result = new Result(-1);
        
        return result;
    }
}
