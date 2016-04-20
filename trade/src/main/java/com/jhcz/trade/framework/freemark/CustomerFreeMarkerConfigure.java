package com.jhcz.trade.framework.freemark;

import java.util.List;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.cache.TemplateLoader;

/**
 * @类名: CustomerFreeMarkerConfigure
 * @包名 com.jhcz.trade.freemark
 * @描述: 自定义的FreeMarker配置文件
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-4-16 下午4:06:41
 * @版本 V1.0
 */
public class CustomerFreeMarkerConfigure extends FreeMarkerConfigurer{
	
	 @Override
	    protected TemplateLoader getAggregateTemplateLoader( List<TemplateLoader> templateLoaders) {
		  //HtmlTemplateLoader 这个类用来自定义html模板  
		 /*	for (TemplateLoader templateLoader : templateLoaders) {
		 		System.out.println("定义html模板");
		 	}*/
	        return new HtmlTemplateLoader(super.getAggregateTemplateLoader(templateLoaders));
	    }
}
