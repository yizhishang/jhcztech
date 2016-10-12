package com.jhcz.plat.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexAction
{
	@RequestMapping("/admin/index.action")
	public String doDefault()
	{
		return "/WEB-INF/views/main.jsp";
	}
}
