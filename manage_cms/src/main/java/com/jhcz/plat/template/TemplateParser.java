package com.jhcz.plat.template;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jhcz.base.config.Configuration;
import com.jhcz.plat.system.Application;
import org.apache.log4j.Logger;

import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.service.ServiceLocator;
import com.jhcz.base.util.MapHelper;
import com.jhcz.base.util.ReflectHelper;
import com.jhcz.base.util.StringHelper;
import com.jhcz.plat.service.TemplateVarService;

/**
 * 描述:
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2009-12-23
 * 创建时间: 11:08:34
 */
public final class TemplateParser
{

    private final Logger logger = Logger.getLogger(TemplateParser.class);


    /**
     * 模板解析上下文
     */
    private Context context = null;

    public TemplateParser(Context context)
    {
        this.context = context;
    }

    /**
     * 分析属性字串到Map对象中
     * @param propStr 需要分解的属性字串
     * @return 分解后的Map对象
     */
    private Map<String, Object> decodePropStr(String propStr)
    {

        HashMap<String, Object> propMap = new HashMap<String, Object>();
        Pattern pattern = Pattern.compile("([a-z]*?)=\"([^\"]*?)\"", Pattern.CASE_INSENSITIVE);
        Matcher matcher = null;

        //开始正则表达式匹配
        matcher = pattern.matcher(propStr);
        while (matcher.find())
        {
            String name = matcher.group(1);
            String value = matcher.group(2);
            propMap.put(name, value);
        }

        return propMap;
    }

    /**
     * 获得部件的缺省视图，该文件应该和类文件放在同样的目录,名称为"部件名.view"
     * @param webpartName
     * @return
     */
    private String getDefaultView(String webpartName)
    {
        StringBuffer buffer = new StringBuffer();
        InputStream inStream = null;
        try
        {
            String path = Configuration.getString("publish.viewPath") + webpartName + ".view";
            inStream = TemplateParser.class.getResourceAsStream(path);
            if (inStream != null)
            {

                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
                String lineStr = "";
                while ((lineStr = reader.readLine()) != null)
                {
                    buffer.append(lineStr + "\r\n");
                }
            }
        }
        catch (Exception ex)
        {
            logger.error("", ex);
        }
        finally
        {
            if (inStream != null)
            {
                try
                {
                    inStream.close();
                }
                catch (Exception ex)
                {
                }
            }
        }

        return buffer.toString();
    }

    /**
     * 分析Webpart的内容
     * @param webpartProp
     * @param viewStr
     * @return
     */
    private String getWebpartContent(String webpartName, Map<String, Object> webpartProp, String viewStr)
    {
        //部件名称的第一个字符若是小写要改为大写
        webpartName = webpartName.substring(0, 1).toUpperCase() + webpartName.substring(1);

        //若部件视图字串为空，则寻找缺省部件视图。缺省部件视图在webpart类同目录的路径中
        viewStr = (StringHelper.isEmpty(viewStr)) ? getDefaultView(webpartName) : viewStr;

        //生成部件解析类的实例
        WebpartParser parser = null;
        String clazz = Configuration.getString("publish.viewPath").replace("/",".") + webpartName;
        parser = (WebpartParser) ReflectHelper.objectForName(clazz);
        if (parser == null)
        {
            throw new RuntimeException("无法实例化具体的部件处理类[" + clazz + "]，不能解析此部件！");
        }


        return parser.parse(context, webpartProp, viewStr);
    }

    /**
     * 对模板进行解析
     * @param template 需要解析的模板字串
     * @return 解析的模板内容
     */
    public String parse(String template)
    {
        if (StringHelper.isEmpty(template))
        {
            //throw new RuntimeException("模板内容为空!");
            context.addErrMsg("模版内容为空!");
            return "";
        }

        Map<String, Object> templateVars = new HashMap<String, Object>();

        //从数据库中提取模板变量
        TemplateVarService varService = (TemplateVarService) ServiceLocator.getService(TemplateVarService.class);
        List<Object> dataList = varService.findAllUsableItem();
        if (dataList != null && dataList.size() > 0)
        {
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
            {
                DataRow var = (DataRow) iter.next();
                String name = var.getString("item_name").trim();
                String value = var.getString("item_value").trim();
                templateVars.put(name, value);
            }
        }

        //加入上下文变量，上下文变量以@开头
        templateVars.put("@catalogId", Integer.valueOf(context.getCatalogId()));
        templateVars.put("@articleId", Integer.valueOf(context.getArticleId()));
        templateVars.put("@ctxPath", Application.getContextPath());

        //获取设置的系统变量
        Map<String, Object> varsMap = context.getAllVariable();
        for (Iterator<String> iter = varsMap.keySet().iterator(); iter.hasNext();)
        {
            String name = iter.next();
            String value = (String) varsMap.get(name);
            templateVars.put("@" + name, value);
        }

        //开始处理模板
        return parseTemplate(templateVars, template);
    }

    /**
     * 解析具体的模板内容
     * @param templateVars 需要全局替换的系统变量
     * @param template     具体模板内容
     * @return 处理后的模板内容
     */
    private String parseTemplate(Map<String, Object> templateVars, String template)
    {
        String result = "";

        //第一步：处理模板变量
        result = parseTemplateVars(templateVars, template);

        //第二步：处理模板中的部件标记
        result = parseWebpart(result);

        //增加转义符  modify by 20120317 
        result = StringHelper.replace(result, "&frasl;", "/");
        
        return result;
    }


    /**
     * 分析模板，替换模板中的系统变量标记
     * @param templateVars 系统变量数据
     * @param template     模板内容
     * @return
     */
    private String parseTemplateVars(Map<String, Object> templateVars, String template)
    {
        Pattern pattern = Pattern.compile("\\$\\[([\\s\\S]*?)\\]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = null;

        StringBuffer buffer = new StringBuffer();

        int begin = 0;
        int start = 0;
        int end = 0;

        //开始正则表达式匹配
        matcher = pattern.matcher(template);
        while (matcher.find())
        {
            //匹配到的webpart的内容的开始位置
            start = matcher.start();

            //匹配到的webpart的内容的结束位置
            end = matcher.end();

            //把非webpart内容的html代码加入buffer
            buffer.append(template.substring(begin, start));

            String label = matcher.group(1);
            label = label.trim();
            //替换特定的变量的值,若没有找到，则替换为空字串
            String value = MapHelper.getString(templateVars, label);
            buffer.append(value);

            //移动计数指针
            begin = end;
        }

        buffer.append(template.substring(begin));
        return buffer.toString();
    }

    /**
     * 分析模板,替换模板中的webpart部件标记
     * @param template 模板内容
     * @return
     */
    private String parseWebpart(String template)
    {
        String result = "";

        //第一步：处理第一种类型的部件
        result = parseWebpartType1(template);

        //第二步：处理第二种类型的部件
        result = parseWebpartType2(result);

        return result;
    }

    /**
     * 分析模板,替换模板中的webpart部件标记(，如:<web:listWebpart catalogId=1000 .... />)
     * @param template
     * @return
     */
    private String parseWebpartType1(String template)
    {
        Pattern pattern = Pattern.compile("<web:([a-z]*)([^>]*?)/>", Pattern.CASE_INSENSITIVE);
        Matcher matcher = null;

        StringBuffer buffer = new StringBuffer();

        int begin = 0;
        int start = 0;
        int end = 0;

        //开始正则表达式匹配
        matcher = pattern.matcher(template);
        while (matcher.find())
        {
            //匹配到的webpart的内容的开始位置
            start = matcher.start();

            //匹配到的webpart的内容的结束位置
            end = matcher.end();

            //把非webpart内容的html代码加入buffer
            buffer.append(template.substring(begin, start));

            String webpartName = matcher.group(1).trim();
            String propStr = matcher.group(2).trim();
            String webpartStr = matcher.group(0).trim();

            Map<String, Object> webpartProp = decodePropStr(propStr);

            //解析部件，获取部件的内容
            try
            {
                buffer.append(getWebpartContent(webpartName, webpartProp, ""));
            }
            catch (Exception ex)
            {
                String strMsg = "解析部件出错：[" + webpartStr + "]";
                logger.error(strMsg, ex);
                context.addErrMsg(strMsg);
            }

            //移动计数指针
            begin = end;
        }

        buffer.append(template.substring(begin));
        return buffer.toString();
    }

    /**
     * 分析模板,替换模板中的webpart部件标记(如:<web:listWebpart catalogId=1000 ...> ..... </web:listWebpart>)
     * @param template
     * @return
     */
    private String parseWebpartType2(String template)
    {
        Pattern pattern = Pattern.compile("<web:([a-z]*)([^/>]*?)>([\\s\\S]*?)</([a-z]*):([a-z]*)>", Pattern.CASE_INSENSITIVE);
        Matcher matcher = null;

        StringBuffer buffer = new StringBuffer();

        int begin = 0;
        int start = 0;
        int end = 0;

        //开始正则表达式匹配
        matcher = pattern.matcher(template);
        while (matcher.find())
        {
            //匹配到的webpart的内容的开始位置
            start = matcher.start();

            //匹配到的webpart的内容的结束位置
            end = matcher.end();

            //把非webpart内容的html代码加入buffer
            buffer.append(template.substring(begin, start));

            String webpartName = matcher.group(1).trim();
            String propStr = matcher.group(2).trim();
            String viewStr = matcher.group(3).trim();
            String webpartStr = matcher.group(0).trim();

            Map<String, Object> webpartProp = decodePropStr(propStr);

            //获取每一个部件替换后的内容
            try
            {
                buffer.append(getWebpartContent(webpartName, webpartProp, viewStr));
            }
            catch (Exception ex)
            {
                String strMsg = "解析部件出错：[" + webpartStr + "]";
                logger.error(strMsg, ex);
                context.addErrMsg(strMsg);
            }

            //移动计数指针
            begin = end;
        }

        buffer.append(template.substring(begin));
        return buffer.toString();
    }
}
