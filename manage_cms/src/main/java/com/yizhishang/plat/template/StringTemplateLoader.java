package com.yizhishang.plat.template;/*
 * Copyright (c) 2009 Your Corporation. All Rights Reserved.
 */

import freemarker.cache.TemplateLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 描述:
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2009-12-25
 * 创建时间: 15:11:27
 */
public class StringTemplateLoader implements TemplateLoader
{

    private static final String DEFAULT_TEMPLATE_KEY = "_default_template_key";

    private final Map<String, String> templates = new HashMap<String, String>();

    public StringTemplateLoader()
    {

    }

    public StringTemplateLoader(String defaultTemplate)
    {
        if (defaultTemplate != null && !defaultTemplate.equals("")) {
            templates.put(DEFAULT_TEMPLATE_KEY, defaultTemplate);
        }
    }

    public void AddTemplate(String name, String template)
    {
        if (name == null || template == null || name.equals("") || template.equals("")) {
            return;
        }
        if (!templates.containsKey(name)) {
            templates.put(name, template);
        }
    }

    @Override
    public void closeTemplateSource(Object templateSource) throws IOException
    {
    }

    @Override
    public Object findTemplateSource(String name) throws IOException
    {
        if (name == null || name.equals("")) {
            name = DEFAULT_TEMPLATE_KEY;
        }
        return templates.get(name);
    }

    @Override
    public long getLastModified(Object templateSource)
    {
        return 0;
    }

    @Override
    public Reader getReader(Object templateSource, String encoding) throws IOException
    {
        return new StringReader((String) templateSource);
    }
}
