package com.jhcz.plat.template;

import java.io.StringWriter;
import java.util.Map;

import org.apache.log4j.Logger;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 描述:
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2009-12-25
 * 创建时间: 15:02:36
 */
public final class FMTemplateGenerator
{
    
    private static Logger logger = Logger.getLogger(FMTemplateGenerator.class);
    
    /**
    * 解析模板字串内容
    * @param model       模型数据
    * @param templateStr 需要解析的模板字串
    * @return 解析后的结果
    */
    public static String parseTemplate(Map<String, Object> model, String templateStr)
    {
        String result = "";
        try
        {
            Configuration cfg = new Configuration();
            StringTemplateLoader templateLoader = new StringTemplateLoader();
            templateLoader.AddTemplate("view", templateStr);
            cfg.setTemplateLoader(templateLoader);
            String encoding = com.jhcz.base.config.Configuration.getString("system.encoding", "UTF-8");
            cfg.setDefaultEncoding(encoding);
            
            Template template = cfg.getTemplate("view");
            StringWriter writer = new StringWriter();
            template.process(model, writer);
            result = writer.toString();
        }
        catch (Exception ex)
        {
            logger.error("ErrorMode:" + model);
            logger.error("", ex);
        }
        
        return result;
    }
}
