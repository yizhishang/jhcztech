package com.jhcz.trade.framework.freemark;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @类名: FreemarkController
 * @包名 com.jhcz.trade.freemark
 * @描述: TODO
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-4-14 上午10:52:04
 * @版本 V1.0
 */
@Controller
@RequestMapping("/freemark")
public class FreemarkController {
	
	@RequestMapping(params = "method=freemarkList")
	public ModelAndView freemarkList(){
		System.out.println("freemark测试");
		ModelAndView mav = new ModelAndView("/memberIndex/index");
		mav.addObject("title", "Spring MVC And Freemarker");  
		mav.addObject("content", " Hello world ， test my first spring mvc ! "); 
		//ModelAndView mav = new ModelAndView("/freemark/freemark_html");
		//ModelAndView mav = new ModelAndView("login_jsp");
		return mav;
	}
	
}
