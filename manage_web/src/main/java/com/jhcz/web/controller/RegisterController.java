package com.jhcz.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhcz.base.domain.system.Result;
import com.jhcz.base.util.StringHelper;
import com.jhcz.web.aspect.MethodLog;
import com.jhcz.web.exception.BusinessException;
import com.jhcz.web.service.RegisterService;

@Controller
public class RegisterController extends BaseController
{
	
	@Resource
	RegisterService registerService;
	
	@ResponseBody
	@MethodLog(remark = "联系我们留言")
	@RequestMapping(value="/insertMessage.action",method=RequestMethod.GET)
	public Result insertMessage(HttpServletRequest request)
	{
		Result result = new Result();
		String id;
		try
		{
			String name = request.getParameter("name");
			if (StringHelper.isEmpty(name))
			{
				throw new BusinessException("用户名[name]不能为空！");
			}
			
			String mobileNo = request.getParameter("mobileNo");
			if (StringHelper.isEmpty(mobileNo))
			{
				throw new BusinessException("手机号[mobileNo]不能为空！");
			}
			
			String message = request.getParameter("message");
			if (StringHelper.isEmpty(message))
			{
				throw new BusinessException("留言信息[message]不能为空！");
			}
			
			try
			{
				name = URLDecoder.decode(name, "utf-8");
				message = URLDecoder.decode(message, "utf-8");
				message = StringHelper.textFmtToHtmlFmt(message);
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
				throw new BusinessException("留言失败!");
			}
			
			id = registerService.insertMessage(name, mobileNo, message);
			result.setErrorInfo("留言成功！ id=" + id);
		}
		catch (BusinessException e)
		{
			result.setErrorNo(-1);
			result.setErrorInfo(e.getMessage());
		}
		return result;
	}
}
