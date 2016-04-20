package com.jhcz.trade.include.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

/**
 * @类名: IncludeController
 * @包名 com.jhcz.trade.include.controller
 * @描述: 页面包含控制器类
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-4-1 下午1:50:50
 * @版本 V1.0
 */
@Controller
@RequestMapping("/include.do")
public class IncludeController {
	
	/**
	  * @方法名: findPageHeader
	  * @描述: 表头查找
	  * @参数 @param request
	  * @参数 @param response
	  * @参数 @param model
	  * @参数 @return   
	  * @返回类型: ModelAndView 
	  * @throws
	  * @时间:2016-4-1下午3:13:08
	  * @作者: zhonghang
	 */
	@ResponseBody
	@RequestMapping(params="method=findPageHeader")
	public ModelAndView findPageHeader(HttpServletRequest request, HttpServletResponse response,Model model){
		Map<String,Object> map = new HashMap<String, Object>();
		List<Object> dataList = new ArrayList<Object>();
		for (int i=0;i<3;i++) {
			Map<String,String> dataMap = new HashMap<String, String>();
			dataMap.put("catalog_name", "首页_"+i);
			dataList.add(dataMap);
		}
		map.put("data", dataList);
		ModelAndView mav = new ModelAndView(new MappingJacksonJsonView(),map);
		return mav;
	}
}
