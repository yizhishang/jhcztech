package com.yizhishang.plat.template;

import java.util.Map;

/**
 * 描述:  模板预览
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2012-4-11
 * 创建时间: 下午02:35:02
 */
public class TemplatePreview
{
    
    private int templateId = 0;
    
    private int catalogId = 0;
    
    private int articleId = 0;
    
    private Map<String, Object> variables = null;
    
    /**
    * 0:根据文章ID预览模板
    * 1:根据栏目ID预览模板
    * 2:根据模板ID预览模板
    */
    private int type = 0;
    
    public TemplatePreview(int templateId)
    {
        this.templateId = templateId;
        type = 2;
    }

    public TemplatePreview(int catalogId, int articleId)
    {
        this.catalogId = catalogId;
        this.articleId = articleId;
        if (catalogId > 0)
        {
            type = 1;
        }
        else
        {
            type = 0;
        }
    }
    
    public int getArticleId()
    {
        return articleId;
    }
    
    public int getCatalogId()
    {
        return catalogId;
    }
    
    public int getTemplateId()
    {
        return templateId;
    }

    public int getType()
    {
        return type;
    }
    
    public Map<String, Object> getVariables()
    {
        return variables;
    }
    
    public void setArticleId(int articleId)
    {
        this.articleId = articleId;
    }
    
    public void setCatalogId(int catalogId)
    {
        this.catalogId = catalogId;
    }
    
    public void setVariables(Map<String, Object> variables)
    {
        this.variables = variables;
    }

}
