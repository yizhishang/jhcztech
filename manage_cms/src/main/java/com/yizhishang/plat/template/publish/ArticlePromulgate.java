package com.yizhishang.plat.template.publish;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yizhishang.base.config.Configuration;
import com.yizhishang.base.util.FileHelper;
import com.yizhishang.base.util.SpringContextHolder;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.domain.Article;
import com.yizhishang.plat.domain.Catalog;
import com.yizhishang.plat.domain.Template;
import com.yizhishang.plat.service.ArticleService;
import com.yizhishang.plat.service.CatalogService;
import com.yizhishang.plat.service.TemplateService;
import com.yizhishang.plat.system.SysLibrary;
import com.yizhishang.plat.template.Context;
import com.yizhishang.plat.template.TemplateParser;

/**
 * 描述:
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2010-1-6
 * 创建时间: 15:56:52
 */
public class ArticlePromulgate extends BasePromulgate
{
    
    private static Logger logger = LoggerFactory.getLogger(ArticlePromulgate.class);
    
    private static ArticleService articleService = (ArticleService) SpringContextHolder.getBean("articleService");
    private static CatalogService catalogService = (CatalogService) SpringContextHolder.getBean("catalogService");
    private static TemplateService templateService = (TemplateService) SpringContextHolder.getBean("templateService");
    
    /**
    * 文章的ID
    */
    private int articleId = 0;
    
    private int queueId = 0;
    
    public ArticlePromulgate(int queueId, int articleId)
    {
        this.queueId = queueId;
        this.articleId = articleId;
    }
    
    /**
    * 根据栏目ID，查找有效的详细细览模板，若当前栏目找不到，则需要往父目录继续寻找
    *
    * @param catalogId
    * @return
    */
    private List<Object> cycFindDetailedTemplateByCatalogId(int catalogId, String siteNo)
    {
        List<Object> dataList = templateService.findUsableTemplate(catalogId, 3, siteNo);
        if (dataList != null && dataList.size() > 0) //找到了，直接返回
        {
            return dataList;
        }
        else
        //没找到，往父目录中寻找
        {
            Catalog catalog = catalogService.getParent(catalogId);
            if (catalog == null) //已经找到了最顶层，还是没有找到，直接返回空
            {
                return null;
            }
            else
            {
                return cycFindDetailedTemplateByCatalogId(catalog.getId(), siteNo);
            }
        }
    }
    
    public void publish()
    {
        
        Article article = articleService.findArticleById(articleId);
        if (article == null)
        {
            String description = "文章不存在，不能发布此文章[articleId=" + articleId + "]";
            logger.error(description);
            addPublishLogInfo(queueId, description);
            return;
        }
        
        //文章有 0：普通,1：链接(不开新页)，2: 下载(开新页)几种类型，只在类型为0时才需要发布,其它不需要发布
        //        if (article.getType() != 0)
        //        {
        //            return;
        //        }
        
        // modify by 2010-4-26 类型为1时，不需要发布，增加了对下载页面的发布
        if (article.getType() == 1)
        {
            return;
        }
        
        //得到文章所在栏目
        int catalogId = article.getCatalogId();
        //判断本栏目的模板继承模式
        Catalog catalog = catalogService.findCatalogById(catalogId);
        int inheritMode = catalog.getInheritMode();

        List<Object> dataList = null;
        if (inheritMode == 1 || inheritMode == 3) //需要在本目录和父目录中查找信息细览模板
        {
            dataList = cycFindDetailedTemplateByCatalogId(catalogId, catalog.getSiteNo());
        }
        else
        //只能在本目录中查找信息细览模板
        {
            dataList = templateService.findUsableTemplate(catalogId, 3);
        }
        
        if (dataList == null || dataList.size() == 0) //没有找到信息细览模板
        {
            String description = "没有找到用于发布该文章的信息细览模板，不能发布此文章[articleId=" + articleId + "]";
            logger.error(description);
            addPublishLogInfo(queueId, description);
            return;
        }
        
        //可能有多个模板，但只取最新的模板
        Template template = (Template) dataList.get(0);
        int templateId = template.getId();
        String content = template.getContent();
        if (StringHelper.isEmpty(content))
        {
            String description = "用于发布文章的信息细览模板[templateId=" + templateId + "]为空，不能发布此文章[articleId=" + articleId + "]";
            logger.error(description);
            addPublishLogInfo(queueId, description);
            return;
        }
        
        String encoding = template.getEncoding();//发布文件使用的编码
        if (StringHelper.isEmpty(encoding))
        {
            encoding = Configuration.getString("system.encoding");
        }
        
        //处理模板，获得解析后的内容
        Context context = new Context();
        context.setCatalogId(catalogId);
        context.setArticleId(articleId);
        
        TemplateParser parser = new TemplateParser(context);
        String result = parser.parse(content);
        
        String errMsg = context.getErrMsg();
        if (!StringHelper.isEmpty(errMsg)) //有错误产生,记录日志
        {
            String filePath = SysLibrary.getArticleUrlPath(articleId);
            errMsg = "文件[" + filePath + "]生成出错，错误信息为：\r\n" + errMsg;
            addPublishLogInfo(queueId, errMsg);
        }
        
        //两种情况重写文件(第一种:开发模式 第二种：正式模式且发布正常，没有错误)
        if (getRunMode() == 0 || StringHelper.isEmpty(errMsg))
        {
            //写入文件到磁盘中
            String filePath = SysLibrary.getArticleStorePath(articleId);
            FileHelper.createNewFile(filePath);
            FileHelper.writeToFile(filePath, result, encoding);
            
            //更新文件的相关资料
            filePath = SysLibrary.getArticleUrlPath(articleId);
            article = new Article();
            article.setId(articleId);
            article.setUrl(filePath);
            articleService.updateArticle(article);
        }
        
        //记录到发布文件表中
        //addPublishFileInfo(queueId, filePath);
    }
}
