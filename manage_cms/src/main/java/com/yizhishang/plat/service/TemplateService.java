package com.yizhishang.plat.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.yizhishang.base.config.Configuration;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.jdbc.DataRow;
import com.yizhishang.base.jdbc.session.Session;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.DateHelper;
import com.yizhishang.base.util.FileHelper;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.domain.Article;
import com.yizhishang.plat.domain.Catalog;
import com.yizhishang.plat.domain.Template;
import com.yizhishang.plat.system.Application;
import com.yizhishang.plat.template.Context;
import com.yizhishang.plat.template.TemplateParser;
import com.yizhishang.plat.template.TemplatePreview;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 
 * 版本:	 1.0
 * 创建日期: 2010-1-18
 * 创建时间: 下午02:47:26
 */
@Component
public class TemplateService extends BaseService
{
    
    private static Logger logger = LoggerFactory.getLogger(TemplateService.class);
    
    @Resource
    private ArticleService articleService;
    
    @Resource
    private CatalogService catalogService;
    
    @Resource
    PublishQueueService publishQueueService;
    
    /**
    * 添加模板
    */
    public int addTemplate(Template template)
    {
        try
        {
            String id = getSeqValue("T_TEMPLATE");
            template.setId(Integer.parseInt(id));
            DataRow data = new DataRow();
            data.putAll(template.toMap());
            
            getJdbcTemplate().insert("T_TEMPLATE", data);
            return new Integer(id).intValue();
        }
        catch (Exception ex)
        {
            logger.error("", ex);
            return 0;
        }
    }
    
    /**
     * @描述：发布任务到发布队列
     * @作者：袁永君
     * @时间：2010-04-02 21:01:22
     * @param id
     * @param flag A:发布文章 T:发布模板 C:发布栏目 CR:发布栏目及其所有子栏目(递归发布)
     * @param loginSiteNo 用户所登陆的站点
     * @param uid 当前后台登录用户的UID
     */
    public void addToPublishQueue(int id, String flag, String loginSiteNo, String uid)
    {
        flag = flag.toUpperCase();//将flag转化为大写
        
        DataRow data = new DataRow();
        data.put("cmd_str", flag + ":" + id);
        if ("A".equals(flag))//发布文章
        {
            data.put("show_info", "发布文章[articleId=" + id + "]");
        }
        else if ("T".equals(flag)) //发布模板
        {
            data.put("show_info", "发布模板[catalogId=" + id + "]");
        }
        else if ("C".equals(flag))//发布栏目
        {
            data.put("show_info", "发布栏目[catalogId=" + id + "]");
        }
        else if ("CR".equals(flag)) //发布栏目及其所有子栏目(递归发布)
        {
            data.put("show_info", "发布栏目[catalogId=" + id + "]及其所有子栏目");
        }
        else
        {
            return;
        }
        
        data.set("state", 0);
        data.set("siteno", loginSiteNo);
        data.set("create_by", uid);
        data.set("create_date", DateHelper.formatDate(new Date()));
        data.set("machine_id", Configuration.getString("system.machineId"));
        
        publishQueueService.add(data);
    }
    
    /**
     * 根据栏目ID，查找有效的详细细览模板，若当前栏目找不到，则需要往父目录继续寻找
     * @param catalogId
     * @return
     */
    public List<Object> cycFindDetailedTemplateByCatalogId(int catalogId, int type)
    {
        
        List<Object> dataList = findUsableTemplate(catalogId, type);
        if (dataList != null && dataList.size() > 0) //找到了，直接返回
        {
            return dataList;
        }
        else
        //没找到，往父目录中寻找
        {
            if (type == 1)//首页模板不允许递归查询
            {
                return null;
            }
            Catalog catalog = catalogService.getParent(catalogId);
            if (catalog == null) //已经找到了最顶层，还是没有找到，直接返回空
            {
                return null;
            }
            else
            {
                return cycFindDetailedTemplateByCatalogId(catalog.getId(), type);
            }
        }
    }
    
    public void deleteTemplate(int id)
    {
        try
        {
            getJdbcTemplate().delete("T_TEMPLATE", "id", new Integer(id));
        }
        catch (Exception ex)
        {
            logger.error("", ex);
        }
    }
    
    public void editTemplate(Template template)
    {
        try
        {
            DataRow data = new DataRow();
            data.putAll(template.toMap());
            getJdbcTemplate().update("T_TEMPLATE", data, "id", new Integer(template.getId()));
        }
        catch (Exception ex)
        {
            logger.error("", ex);
        }
        
    }
    
    /**
    * 
    * @描述：批量更新模板
    * @作者：袁永君
    * @时间：2011-3-3 下午05:15:22
    * @param dataList
    * @throws Exception
    */
    public void editTemplateByList(List<Object> dataList) throws Exception
    {
        Session session = null;
        try
        {
            session = getSession();
            int id = 0;
            String name = "";
            int type = 0;
            int catalogId = 0;
            //			String newId = "";
            session.beginTrans();
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
            {
                DataRow dataRow = (DataRow) iter.next();
                id = dataRow.getInt("id");
                name = dataRow.getString("name");
                type = dataRow.getInt("type");
                catalogId = dataRow.getInt("catalog_id");
                //判断数据有效性
                if (id == 0 || name.trim().length() == 0 || type == 0 || catalogId == 0)
                {
                    throw new Exception("请检查导入的xml文件是否正确！");
                }
                if (this.getJdbcTemplate().queryInt("SELECT ID FROM T_TEMPLATE WHERE ID = ?", new Object[] { new Integer(id) }) == 0)
                {
                    //					newId = this.getSeqValue("T_TEMPLATE");
                    dataRow.set("id", id);//这里还是使用xml中的ID,
                    session.insert("T_TEMPLATE", dataRow);
                }
                else
                {
                    session.update("T_TEMPLATE", dataRow, "id", dataRow.getString("id"));
                }
            }
            session.commitTrans();
        }
        catch (Exception ex)
        {
            if (session != null)
                session.rollbackTrans();
            throw new Exception(ex);
        }
        finally
        {
            if (session != null)
            {
                session.close();
                session = null;
            }
        }
    }
    
    public DBPage findTemplate(int curPage, int numPerPage, int catalogId, String keyword, int state, String startDate, String endDate, String siteNo)
    {
        DBPage page = null;
        ArrayList<Object> argList = new ArrayList<Object>();
        StringBuffer strBuf = new StringBuffer("SELECT * FROM T_TEMPLATE WHERE 1 = 1");
        if (catalogId > 0)
        {
            strBuf.append(" AND CATALOG_ID = ?");
            argList.add(new Integer(catalogId));
        }
        if (StringHelper.isNotEmpty(keyword))
        {
            strBuf.append(" AND NAME LIKE ?");
            argList.add("%" + keyword + "%");
        }
        if (state > -1)
        {
            strBuf.append(" AND STATE LIKE ?");
            argList.add("%" + state + "%");
        }
        if (StringHelper.isNotEmpty(startDate))
        {
            strBuf.append(" AND CREATE_DATE >= ?");
            argList.add(startDate);
        }
        
        if (StringHelper.isNotEmpty(endDate))
        {
            strBuf.append("  AND CREATE_DATE  <= ?");
            argList.add(endDate);
        }
        if (StringHelper.isNotEmpty(siteNo))
        {
            strBuf.append(" AND SITENO = ?");
            argList.add(siteNo);
        }
        strBuf.append(" ORDER BY ID DESC");
        page = getJdbcTemplate().queryPage(strBuf.toString(), argList.toArray(), curPage, numPerPage);
        if (page != null)
        {
            List<Object> dataList = page.getData();
            ArrayList<Object> newDataList = new ArrayList<Object>();
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
            {
                DataRow data = (DataRow) iter.next();
                Template template = new Template();
                template.fromMap(data);
                newDataList.add(template);
            }
            page.setData(newDataList);
        }
        return page;
    }
    
    /**
    * 
    * @描述：查询栏目下的所有模板
    * @作者：袁永君
    * @时间：2010-07-01 12:43:36
    * @param catalogId
    * @return
    */
    public List<Object> findTemplate(int catalogId, String siteNo)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        
        String sql = "SELECT * FROM T_TEMPLATE WHERE CATALOG_ID = ? ";
        argList.add(new Integer(catalogId));
        if (StringHelper.isNotEmpty(siteNo))
        {
            sql += " AND SITENO = ?";
            argList.add(siteNo);
            
        }
        sql += "  AND STATE = 1 ORDER BY CREATE_DATE DESC";
        
        List<Object> dataList = getJdbcTemplate().query(sql, argList.toArray());
        ArrayList<Object> newDataList = new ArrayList<Object>();
        if (dataList != null && dataList.size() > 0)
        {
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
            {
                DataRow data = (DataRow) iter.next();
                Template template = new Template();
                template.fromMap(data);
                newDataList.add(template);
            }
        }
        return newDataList;
    }
    
    public Template findTemplateById(int id)
    {
        return findTemplateById(id, "");
    }
    
    public Template findTemplateById(int id, String siteNo)
    {
        try
        {
            ArrayList<Object> argList = new ArrayList<Object>();
            String sql = "SELECT * FROM T_TEMPLATE WHERE ID = ? ";
            argList.add(new Integer(id));
            if (StringHelper.isNotEmpty(siteNo))
            {
                sql += " AND SITENO = ?";
                argList.add(siteNo);
            }
            DataRow data = getJdbcTemplate().queryMap(sql, argList.toArray());
            
            Template template = null;
            if (data != null)
            {
                template = new Template();
                template.fromMap(data);
            }
            
            return template;
        }
        catch (Exception ex)
        {
            logger.error("", ex);
            return null;
        }
    }
    
    /**
    * 
    * @描述：查询当前栏目和所有子栏目模板
    * @作者：袁永君
    * @时间：2011-3-3 下午01:34:09
    * @param route
    * @return
    */
    public List<Template> findTemplateByRoute(int route)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        String sql = "SELECT * FROM T_TEMPLATE WHERE CATALOG_ID IN (SELECT CATALOG_ID FROM T_CATALOG WHERE ROUTE LIKE ? AND STATE = 1) ORDER BY ID ASC";
        argList.add("%" + route + "%");
        List<Object> dataList = getJdbcTemplate().query(sql, argList.toArray());
        ArrayList<Template> newDataList = new ArrayList<Template>();
        if (dataList != null && dataList.size() > 0)
        {
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
            {
                DataRow data = (DataRow) iter.next();
                Template template = new Template();
                template.fromMap(data);
                newDataList.add(template);
            }
        }
        return newDataList;
    }
    
    public List<Object> findUsableTemplate(int catalogId, int type)
    {
        return findUsableTemplate(catalogId, type, "");
    }
    
    /**
    * 查找某栏目下可用的某类型的模板,若有多个，则按建立时间的降序进行排序
    * @param catalogId 栏目ID
    * @param type      模板类型  1:首页模板 2:信息列表 3:信息细览 4：其它模版
    * @param siteNo 站点编号
    * @return
    */
    public List<Object> findUsableTemplate(int catalogId, int type, String siteNo)
    {
        ArrayList<Object> argList = new ArrayList<Object>();
        argList.add(new Integer(catalogId));
        argList.add(new Integer(type));
        
        String sql = "select * from T_TEMPLATE where catalog_id=? and type=? and state=1";
        if (StringHelper.isNotEmpty(siteNo))
        {
            sql += " and siteno = ?";
            argList.add(siteNo);
            
        }
        sql += " order by create_date desc";
        
        List<Object> dataList = getJdbcTemplate().query(sql, argList.toArray());
        ArrayList<Object> newDataList = new ArrayList<Object>();
        if (dataList != null && dataList.size() > 0)
        {
            for (Iterator<Object> iter = dataList.iterator(); iter.hasNext();)
            {
                DataRow data = (DataRow) iter.next();
                Template template = new Template();
                template.fromMap(data);
                newDataList.add(template);
            }
        }
        return newDataList;
    }
    
    /**
     * @描述：根据栏目ID查询栏目下模板信息
     * @作者：袁永君
     * @时间：2012-4-11 下午07:22:59
     * @param catalogId
     * @param type
     * @return
     * @throws Exception
     */
    public Template getTemplateByCid(int catalogId, int type) throws Exception
    {
        //判断本栏目的模板继承模式
        Catalog catalog = catalogService.findCatalogById(catalogId);
        int inheritMode = catalog.getInheritMode();
        
        List<Object> dataList = null;
        if (inheritMode == 1 || inheritMode == 3) //需要在本目录和父目录中查找信息细览模板
        {
            dataList = cycFindDetailedTemplateByCatalogId(catalogId, type);
        }
        else
        //只能在本目录中查找模板
        {
            dataList = findUsableTemplate(catalogId, type);
        }
        
        if (dataList != null && dataList.size() > 0)
        {
            
            //可能有多个模板，但只取最新的模板
            return (Template) dataList.get(0);
        }
        else
        {
            return null;
        }
    }
    
    /**
     * @描述：读取ssl包含的内容
     * @作者：袁永君
     * @时间：2011-11-23 下午05:10:50
     * @param content
     * @return
     */
    public String parseSslHtml(String content)
    {
        String encoding = Configuration.getString("system.encoding", "GBK");
        /**
        * 上传文件保存的地址，从配置文件中取，可以将附件生成在工程外
        *  add by 20131015
        */
        String fileSaveAddr = Configuration.getString("system.fileSaveAddr");
        try
        {
            StringBuffer buffer = new StringBuffer();
            String patternStr = "<!--#include([^>]*?)-->";
            Pattern pattern = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
            
            Matcher matcher = null;
            matcher = pattern.matcher(content);
            
            int begin = 0;
            int start = 0;
            int end = 0;
            while (matcher.find())
            {
                start = matcher.start();
                end = matcher.end();
                buffer.append(content.substring(begin, start));
                
                //替换ssl包含规则为ssl的包含规则
                String path = matcher.group(1).trim();
                if (path.startsWith("virtual"))
                {
                    path = path.substring("virtual=\"".length(), path.length() - 1);
                }
                
                if (StringHelper.isNotEmpty(fileSaveAddr))
                {
                    path = fileSaveAddr + path;
                }
                else
                {
                    path = Application.getRootPath() + path;
                }
                path = FileHelper.normalize(path);
                buffer.append(FileHelper.readFileToString(path, encoding));
                
                //移动计数指针
                begin = end;
            }
            //logger.info("===================:" + begin);
            buffer.append(content.substring(begin));
            //logger.info(buffer.toString().indexOf("virtual=") + "");
            if (begin > 0 && buffer.toString().indexOf("virtual=") > 0)
            {
                return parseSslHtml(buffer.toString());
            }
            else
            {
                return buffer.toString();
            }
            
        }
        catch (Exception ex)
        {
            logger.error("", ex);
            return content;
        }
    }
    
    /**
    * 解析模板内容
    * @param type 0:根据文章ID预览模板
    *             1:根据栏目ID预览模板
    *             2:根据模板ID预览模板
    * @return
    */
    public String parseTemplate(TemplatePreview templatePreview) throws Exception
    {
        int catalogId = templatePreview.getCatalogId();
        int templateId = templatePreview.getTemplateId();
        int articleId = templatePreview.getArticleId();
        String result = "";
        Template template = null;
        if (templatePreview.getType() == 0)//预览文章
        {
            //通过文章ID获取栏目ID
            Article article = articleService.findArticleById(articleId);
            if (article == null)
            {
                throw new Exception("文章不存在，不能预览此文章");
            }
            catalogId = article.getCatalogId();
            templatePreview.setCatalogId(catalogId);
            //查找信息细览模板
            template = getTemplateByCid(catalogId, 3);
        }
        else if (templatePreview.getType() == 1)
        {
            //先查询当前栏目有没有首页模板，没有查询当前栏目有没有信息列表
            template = getTemplateByCid(catalogId, 1);
            if (template == null)
            {
                template = getTemplateByCid(catalogId, 2);
            }
            
        }
        else if (templatePreview.getType() == 2)
        {
            template = findTemplateById(templateId);
        }
        
        if (template == null)
        {
            throw new Exception("找不到模板，不能预览此模板");
        }
        
        String content = template.getContent();
        if (StringHelper.isEmpty(content))
        {
            result = "模板内容为空，不能发布此模板[templateId=" + templateId + "]";
            return result;
        }
        
        Context context = new Context();
        context.setCatalogId(catalogId);
        context.setArticleId(articleId);
        context.addVariable("isPreview", "1");//是否预览模式
        
        Map<String, Object> variables = templatePreview.getVariables();
        if (variables != null)
        {
            for (Iterator<String> iter = variables.keySet().iterator(); iter.hasNext();)
            {
                String key = iter.next();
                Object value = variables.get(key);
                context.addVariable(key, String.valueOf(value));
            }
        }
        
        TemplateParser parser = new TemplateParser(context);
        result = parser.parse(content);
        
        //替换ssl包含内容
        result = parseSslHtml(result);
        
        return result;
    }
    
    /**
     * @描述：发布栏目下的模板信息
     * @作者：袁永君
     * @时间：2011-6-27 下午08:43:59
     * @param catalogNo
     * @param siteNo
     * @param flag
     */
    //    public void publishAdCatalog(String catalogNo, String siteNo, String flag)
    //    {
    //        Catalog catalog = catalogService.findCatalogByNo(catalogNo, siteNo);
    //
    //        if (catalog != null)
    //        {
    //            addToPublishQueue(catalog.getId(), flag, siteNo);
    //        }
    //    }
    
}
