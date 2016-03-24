package com.jhcz.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jhcz.cms.aspect.MethodLog;
import com.jhcz.cms.model.User;
import com.jhcz.cms.service.UserService;

@Controller
public class HelloController
{
	@Autowired
	UserService userService;
	
	@RequestMapping("/greeting.action")
	@MethodLog(remark="欢迎")
	public ModelAndView greeting()
	{
		User user = userService.getUserById(1);
		ModelAndView mv = new ModelAndView("/test/hello1");
		System.out.println(user);
		mv.addObject("user", user);
		return mv;
	}
}
