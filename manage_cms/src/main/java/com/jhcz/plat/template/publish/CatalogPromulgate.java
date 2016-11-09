package com.jhcz.plat.template.publish;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jhcz.base.config.Configuration;
import com.jhcz.base.service.ServiceLocator;
import com.jhcz.base.util.FileHelper;
import com.jhcz.base.util.ReflectHelper;
import com.jhcz.base.util.StringHelper;
import com.jhcz.plat.domain.Catalog;
import com.jhcz.plat.domain.Template;
import com.jhcz.plat.service.CatalogService;
import com.jhcz.plat.service.TemplateService;
import com.jhcz.plat.system.SysLibrary;
import com.jhcz.plat.template.Context;
import com.jhcz.plat.template.PeculiarTemplate;
import com.jhcz.plat.template.TemplateParser;

/**
 * 描述:
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2010-1-6
 * 创建时间: 15:56:39
 */
public class CatalogPromulgate extends BasePromulgate
{
    
    private static Logger logger = LoggerFactory.getLogger(CatalogPromulgate.class);
    
    private static CatalogService catalogService = (CatalogService) ServiceLocator.getService(CatalogService.class);
    
    private static TemplateService templateService = (TemplateService) ServiceLocator.getService(TemplateService.class);
    
    /**
    * 栏目的ID
    */
    private int catalogId = 0;
    
    private int queueId = 0;
    
    public CatalogPromulgate(int queueId, int catalogId)
    {
        this.queueId = queueId;
        this.catalogId = catalogId;
    }
    
    /**
    * 根据栏目ID，查找有效的信息列表模板，若当前栏目找不到，则需要往父目录继续寻找
    * @param catalogId
    * @return
    */
    private List<Object> cycFindListTemplateByCatalogId(int catalogId, String siteNo)
    {
        
        List<Object> dataList = templateService.findUsableTemplate(catalogId, 2, siteNo);
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
                return cycFindListTemplateByCatalogId(catalog.getId(), siteNo);
            }
        }
    }
    
    /**
    * 根据栏目ID，查找该栏目下有效的首页模板
    * @param catalogId
    * @return
    */
    private List<Object> findMainTemplateByCatalogId(int catalogId)
    {
        return findMainTemplateByCatalogId(catalogId, "");
    }
    
    /**
    * 根据栏目ID和站点编号，查找该栏目下有效的首页模板
    * @param catalogId
    * @param siteNo
    * @return
    */
    private List<Object> findMainTemplateByCatalogId(int catalogId, String siteNo)
    {
        return templateService.findUsableTemplate(catalogId, 1, siteNo);
    }
    
    /**
    * 根据栏目ID，查找有效的其它模板
    * @param catalogId
    * @return
    */
    private List<Object> findOtherTemplateByCatalogId(int catalogId)
    {
        return templateService.findUsableTemplate(catalogId, 4);
    }
    
    public void publish()
    {
        
        Catalog catalog = catalogService.findCatalogById(catalogId);
        if (catalog == null)
        {
            String description = "目录不存在，不能发布此目录[catalogId=" + catalogId + "]";
            logger.error(description);
            addPublishLogInfo(queueId, description);
            return;
        }
        
        /**
        * 判断是否为特殊模板
        * 特殊模板需要实现PeculiarTemplate接口来处理
        *  add by 2010-03-29
        */
        if (catalog.getType() == 2 && StringHelper.isNotEmpty(catalog.getPublishPath()))
        {
            String className = catalog.getPublishPath();
            PeculiarTemplate peculiarTemplate = (PeculiarTemplate) ReflectHelper.objectForName(className);
            if (peculiarTemplate != null && peculiarTemplate instanceof PeculiarTemplate)
            {
                peculiarTemplate.run(catalog);
                return;
            }
            else
            {
                String description = "虚拟发布栏目的实现类不存在，不能发布此目录[catalogId=" + catalogId + "]";
                logger.error(description);
                addPublishLogInfo(queueId, description);
                return;
            }
        }
        
        /**
        * 第一步：查看该栏下是否有首页模板，若有，则发布。
        * 第二步：若没有发布首页模板，则再查看该栏目下及其上级父目录中是否有信息列表模板，若有，则发布
        * 第三步：查看该栏目下是否有其它模板，若有，则发布所有其它模板
        */
        List<Object> dataList = null;
        String tempSite = catalog.getSiteNo();
        if (tempSite.indexOf("|") > 0)//如果有子站时，发布根栏目
        {
            /**
            * 每个子站共用一个根栏目，对根栏目进行发步时，需要查询每个子站是否在根栏目中建立了模板
            * 由于根栏目没有父节点，所以不需要递归查找父栏目的信息列表
            *  add by 2011-03-18
            */
            String[] sites = StringHelper.split(tempSite, "|");
            for (int i = 0; i < sites.length; i++)
            {
                dataList = findMainTemplateByCatalogId(catalogId, sites[i]);
                if (dataList != null && dataList.size() > 0) //找到了首页模板，发布之
                {
                    //可能有多个模板，只取最新的模板
                    Template template = (Template) dataList.get(0);
                    publishMainTemplate(catalogId, template, sites[i]);
                }
                else
                {
                    dataList = templateService.findUsableTemplate(catalogId, 2, sites[i]);
                    if (dataList != null && dataList.size() > 0) //找到了信息列表模板，发布之
                    {
                        //可能有多个模板，但只取最新的模板
                        Template template = (Template) dataList.get(0);
                        publishListTemplate(catalogId, template, sites[i]);
                    }
                }
            }
        }
        else
        {
            dataList = findMainTemplateByCatalogId(catalogId); //查找该栏目下的首页模板
            if (dataList != null && dataList.size() > 0) //找到了首页模板，发布之
            {
                //可能有多个模板，只取最新的模板
                Template template = (Template) dataList.get(0);
                publishMainTemplate(catalogId, template);
            }
            else
            //没有找到，再看是否有信息列表模板
            {
                int inheritMode = catalog.getInheritMode();
                if (inheritMode == 2 || inheritMode == 3) //需要在本目录和父目录中查找信息列表模板
                {
                    dataList = cycFindListTemplateByCatalogId(catalogId, catalog.getSiteNo()); //查找该栏目下的信息列表模板
                }
                else
                //只能在本目录中查找信息列表模板
                {
                    dataList = templateService.findUsableTemplate(catalogId, 2);
                }
                
                if (dataList != null && dataList.size() > 0) //找到了信息列表模板，发布之
                {
                    //可能有多个模板，但只取最新的模板
                    Template template = (Template) dataList.get(0);
                    publishListTemplate(catalogId, template);
                }
            }
        }
        
        //发布该栏目下的其它模板
        dataList = findOtherTemplateByCatalogId(catalogId);
        if (dataList != null && dataList.size() > 0)
        {
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
            {
                Template template = (Template) iter.next();
                int templateId = template.getId();
                TemplatePromulgate promulgate = new TemplatePromulgate(queueId, templateId);
                promulgate.publish();
            }
        }
    }
    
    /**
    * 发布信息列表模板
    *
    * @param catalogId
    * @param template
    */
    private void publishListTemplate(int catalogId, Template template)
    {
        publishListTemplate(catalogId, template, "");
    }
    
    /**
    * 发布信息列表模板
    *
    * @param catalogId
    * @param template
    * @param siteNo
    */
    private void publishListTemplate(int catalogId, Template template, String siteNo)
    {
        int templateId = template.getId();
        String content = template.getContent();
        if (StringHelper.isEmpty(content))
        {
            String description = "用于发布栏目的信息列表模板[templateId=" + templateId + "]为空，不能发布此栏目[catalogId=" + catalogId + "]";
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
        
        TemplateParser parser = new TemplateParser(context);
        String result = parser.parse(content);
        
        String errMsg = context.getErrMsg();
        if (!StringHelper.isEmpty(errMsg)) //有错误产生,记录日志
        {
            String filePath = SysLibrary.getCatalogUrlPath(catalogId);
            errMsg = "文件[" + filePath + "]生成出错，错误信息为：\r\n" + errMsg;
            addPublishLogInfo(queueId, errMsg);
        }
        
        //两种情况重写文件(第一种:开发模式 第二种：正式模式且发布正常，没有错误)
        if (getRunMode() == 0 || StringHelper.isEmpty(errMsg))
        {
            //写入解析后的内容到文件
            String filePath = SysLibrary.getCatalogStorePath(catalogId);
            FileHelper.createNewFile(filePath);
            FileHelper.writeToFile(filePath, result, encoding);
        }
        
        //记录到发布文件表中
        //filePath = SysLibrary.getCatalogUrlPath(catalogId);
        //addPublishFileInfo(queueId, filePath);
    }
    
    /**
    * 发布首页模板
    *
    * @param catalogId
    * @param template
    */
    private void publishMainTemplate(int catalogId, Template template)
    {
        publishMainTemplate(catalogId, template, "");
    }
    
    /**
    * 发布首页模板
    *
    * @param catalogId
    * @param template
    */
    private void publishMainTemplate(int catalogId, Template template, String siteNo)
    {
        int templateId = template.getId();
        String content = template.getContent();
        if (StringHelper.isEmpty(content))
        {
            String description = "用于发布文章的首页模板[templateId=" + templateId + "]为空，不能发布此栏目[catalogId=" + catalogId + "]";
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
        
        TemplateParser parser = new TemplateParser(context);
        String result = parser.parse(content);
        
        String errMsg = context.getErrMsg();
        if (!StringHelper.isEmpty(errMsg)) //有错误产生,记录日志
        {
            String filePath = SysLibrary.getCatalogUrlPath(catalogId);
            errMsg = "文件[" + filePath + "]生成出错，错误信息为：\r\n" + errMsg;
            addPublishLogInfo(queueId, errMsg);
        }
        
        //两种情况重写文件(第一种:开发模式 第二种：正式模式且发布正常，没有错误)
        if (getRunMode() == 0 || StringHelper.isEmpty(errMsg))
        {
            //写入解析后的内容到文件
            String filePath = SysLibrary.getCatalogStorePath(catalogId, siteNo);
            FileHelper.createNewFile(filePath);
            FileHelper.writeToFile(filePath, result, encoding);
        }
        
        //记录到发布文件表中
        //filePath = SysLibrary.getCatalogUrlPath(catalogId);
        //addPublishFileInfo(queueId, filePath);
    }

}
