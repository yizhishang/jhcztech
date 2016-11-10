package com.yizhishang.plat.template;

import java.util.Map;

import com.yizhishang.base.util.SpringContextHolder;
import com.yizhishang.plat.service.ArticleService;
import com.yizhishang.plat.service.CatalogService;
import com.yizhishang.plat.service.SeoService;

/**
 * 描述:
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2009-12-25
 * 创建时间: 14:01:37
 */
public abstract class FMWebpartParser implements WebpartParser
{
	protected static CatalogService catalogService = (CatalogService) SpringContextHolder.getBean("catalogService");
	
	protected static ArticleService articleService = (ArticleService) SpringContextHolder.getBean("articleService");
	
	protected static SeoService seoService = (SeoService) SpringContextHolder.getBean("seoService");
	
    /**
     * 使用FreeMarket解析器解析视图
     * @param model   模型数据
     * @param viewStr 视图字串
     * @return
     */
    public String doParse(Map<String, Object> model, String viewStr)
    {
        return FMTemplateGenerator.parseTemplate(model, viewStr);
    }

    public abstract Map<String, Object> getModel(Context context, Map<String, Object> webpartProp);

    /**
     * 对部件进行解析，得到对应部件的HTML
     * @param context     解析上下文参数
     * @param webpartProp 部件的属性
     * @param viewStr     模板中的部件视图字串
     * @return
     */
    @Override
    public String parse(Context context, Map<String, Object> webpartProp, String viewStr)
    {
        //获得处理后的模型数据
        Map<String, Object> model = getModel(context, webpartProp);
        model.put("prop", webpartProp);
        return doParse(model, viewStr);
    }
}
