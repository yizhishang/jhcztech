package com.yizhishang.plat.export;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yizhishang.base.util.FileHelper;
import com.yizhishang.base.util.XMLHelper;
import com.yizhishang.plat.domain.Template;
import com.yizhishang.plat.service.TemplateService;

/**
 * 描述:  
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2011-3-2
 * 创建时间: 下午03:53:33
 */
public class TemplateXmlManage
{
    
    private static Logger logger = LoggerFactory.getLogger(TemplateXmlManage.class);
    
    private String directory = "";
    
    public TemplateXmlManage(String directory)
    {
        this.directory = directory;
    }
    
    /**
    *
    * @描述：创建xml
    * @作者：袁永君
    * @时间：2011-3-2 下午04:50:27
    * @param catalogId
    * @return
    */
    public boolean handleXml(int catalogId)
    {
        
        TemplateService templateService = new TemplateService();
        List<Template> list = templateService.findTemplateByRoute(catalogId);
        
        if (!publishXML(list))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
    * @描述：根据模板ID创建XML
    * @作者：袁永君
    * @时间：2011-3-3 下午01:37:12
    * @param templates
    * @return
    */
    public boolean handleXml(int[] templates)
    {
        TemplateService templateService = new TemplateService();

        ArrayList<Template> list = new ArrayList<Template>();
        for (int i = 0; i < templates.length; i++)
        {
            Template template = templateService.findTemplateById(templates[i]);
            if (template == null)
            {
                continue;
            }
            list.add(template);
        }
        
        if (!publishXML(list))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
    * 
    * @描述：发布xml内容
    * @作者：袁永君
    * @时间：2011-3-2 下午04:46:44
    * @param templateList
    * @return
    */
    public boolean publishXML(List<Template> templateList)
    {
        boolean result = false;
        Iterator<Template> iter = templateList.iterator();
        while (iter.hasNext())
        {
            Template template = iter.next();
            
            if (!saveToXmlFile(template, "GB2312"))
            {
                result &= false;
            }
            
        }
        return result;
    }
    
    /**
    * 
    * @描述：把xml数据保存到文件系统指定的目录下面
    * @作者：袁永君
    * @时间：2011-3-2 下午04:45:11
    * @param templateXml
    * @param xmlEncoding
    * @return
    */
    public boolean saveToXmlFile(Template template, String xmlEncoding)
    {
        Document document = toDocument(template);
        if (document != null)
        {
            String path = directory + "/" + template.getId() + ".xml";
            path = FileHelper.normalize(path);
            if (!new File(path).exists())
            {
                FileHelper.createNewFile(path);
            }
            
            XMLHelper.writeToXml(path, document);
        }
        return false;
    }
    
    /**
    * 
    * @描述：根据当前状态组织一个文档
    * @作者：袁永君
    * @时间：2011-3-3 上午10:14:07
    * @param dataRow
    * @return
    */
    private Document toDocument(Template template)
    {
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("data");
        Element templateElem = root.addElement("template");
        
        Map<String, Object> dataMap = template.toMap();
        for (Iterator<String> iter = dataMap.keySet().iterator(); iter.hasNext();)
        {
            String key = iter.next();
            Object obj = dataMap.get(key);
            
            if (obj == null)
                continue;
            
            Element element = templateElem.addElement(key);
            if (obj instanceof String)
            {
                String value = (String) obj;
                try
                {
                    value = java.net.URLEncoder.encode(value, "UTF-8");
                }
                catch (UnsupportedEncodingException e)
                {
                    logger.error("", e);
                }
                element.addCDATA(value);
            }
            else
            {
                element.setText(String.valueOf(obj));
            }
        }
        return doc;
    }
}
