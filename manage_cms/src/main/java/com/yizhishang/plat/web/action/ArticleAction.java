package com.yizhishang.plat.web.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yizhishang.base.config.Configuration;
import com.yizhishang.base.config.SysConfig;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.jdbc.DataRow;
import com.yizhishang.base.util.BeanHelper;
import com.yizhishang.base.util.CharHelper;
import com.yizhishang.base.util.DateHelper;
import com.yizhishang.base.util.FileHelper;
import com.yizhishang.base.util.RequestHelper;
import com.yizhishang.base.util.ResponseHelper;
import com.yizhishang.base.util.ScriptHelper;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.CatalogTreeManage;
import com.yizhishang.plat.Constants;
import com.yizhishang.plat.domain.Article;
import com.yizhishang.plat.domain.ArticleKeyword;
import com.yizhishang.plat.domain.Catalog;
import com.yizhishang.plat.domain.Result;
import com.yizhishang.plat.domain.Review;
import com.yizhishang.plat.service.ArticleKeywordService;
import com.yizhishang.plat.service.ArticleService;
import com.yizhishang.plat.service.Article_SourceService;
import com.yizhishang.plat.service.AttachService;
import com.yizhishang.plat.service.CatalogService;
import com.yizhishang.plat.service.CommentService;
import com.yizhishang.plat.service.CustomFieldService;
import com.yizhishang.plat.service.EnumService;
import com.yizhishang.plat.service.TemplateService;
import com.yizhishang.plat.system.Application;
import com.yizhishang.plat.system.SysLibrary;
import com.yizhishang.plat.template.TemplatePreview;
import com.yizhishang.plat.web.form.DynaForm;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-18
 * 创建时间: 16:11:19
 */
@Controller
@RequestMapping("/admin/articleAdmin")
public class ArticleAction extends BaseAction
{
    
    private static Logger logger = LoggerFactory.getLogger(ArticleAction.class);
    
    private static final int SPLIT_NUM = 500;//自动截取的字符串长度
    
    private DynaForm extFieldform = new DynaForm();
    
    public static long START_DATE = 0;
    
    @Resource
    ArticleService articleService;
    
    @Resource
    AttachService attachService;
    
    @Resource
    Article_SourceService article_SourceService;
    
    @Resource
    ArticleKeywordService articleKeywordService;
    
    @Resource
    CatalogService catalogService;
    
    @Resource
    TemplateService templateService;
    
    @Autowired
    CustomFieldService customFieldService;
    
    @Autowired
    CommentService commentService;
    
    @Autowired
    EnumService enumService;
    
    @Override
    @ResponseBody
    @RequestMapping("/add.action")
    public Result add(HttpServletRequest request, HttpServletResponse reponse)
    {
        form = normalize(request);
        Article article = new Article();
        BeanHelper.mapToBean(form, article);
        
        extFieldform = normalize(request, "extFieldform");
        DataRow extFieldData = new DataRow();
        extFieldData.putAll(extFieldform);
        
        //需要复制文章的栏目ID
        String[] chooseCopyCatalogs = RequestHelper.getStringArray(request, "chooseCopyCatalog");
        
        if (getStrParameter("replace").equalsIgnoreCase("true"))
        {
            article.setContent(replaceKeyword(article.getContent()));
        }
        
        //获得用户登陆的站点
        String siteno = getLoginSiteNo();
        article.setUrl("");
        //article.setIsHot(0);
        article.setIsNew(0);
        //article.setIsHead(0);
        if (article.getState() == 3)
        {
            article.setPublishBy(getUID());
            if (StringHelper.isEmpty(article.getCreateDate()))
            {
                article.setPublishDate(DateHelper.formatDate(new Date()));
            }
            else
            {
                article.setPublishDate(article.getCreateDate());
            }
        }
        else
        {
            article.setPublishBy("");
            article.setPublishDate("");
        }
        
        article.setSiteNo(siteno);
        article.setReview(0);
        article.setHits(0);
        article.setInFlag(0);
        article.setUserRight("");
        article.setRoleRight("");
        article.setCreateBy(getUID());
        if (StringHelper.isEmpty(article.getCreateDate()))
        {
            article.setCreateDate(DateHelper.formatDate(new Date()));
        }
        article.setModifiedBy(getUID());
        article.setModifiedDate(DateHelper.formatDate(new Date()));
        
        /**
        * 如果摘要为空，自动提取内容前500个字符为摘要
        */
        if (StringHelper.isEmpty(article.getBrief()))
        {
            String content = article.getContent();
            content = StringHelper.clearHtml(content);//清除内容中的样式
            String brief = "";
            brief = StringHelper.truncate(content, SPLIT_NUM, false);
            article.setBrief(brief);
        }
        
        int articleId = articleService.addArticle(article);
        addLog("添加文章", "添加文章[id=" + article.getId() + ",title=" + article.getTitle() + "]", article.getCatalogId());
        //发布文章
        int isPublish = Configuration.getInt("system.isPublish", 1);//是否启动发布队列
        int isPublishArticle = Configuration.getInt("system.isPublishArticle", 1);//是否需要静态化文章
        if (1 == isPublish && 1 == isPublishArticle && articleId > 0 && article.getState() == 3)
        {
            //把要发布的文章添加入发布队列                
            addToPublishQueue(articleId, "A");
        }
        
        /**
        * 保存附件 285206405@qq.com add by 2011-11-09
        */
        String attach_url = getStrParameter("attach_url");
        String attach_savename = getStrParameter("attach_savename");
        String attach_realname = getStrParameter("attach_realname");
        if (articleId > 0 && StringHelper.isNotEmpty(attach_url) && StringHelper.isNotEmpty(attach_savename) && StringHelper.isNotEmpty(attach_realname))
        {
            DataRow fileUploadData = new DataRow();
            fileUploadData.set("source", "0");
            fileUploadData.set("source_id", articleId);
            fileUploadData.set("save_filename", attach_savename);
            fileUploadData.set("real_filaname", attach_realname);
            fileUploadData.set("url", attach_url);
            fileUploadData.set("create_by", getUID());
            fileUploadData.set("create_date", DateHelper.formatDate(new Date()));
            fileUploadData.set("down_count", "0");
            
            AttachService attachService = new AttachService();
            attachService.addAttach(fileUploadData);
            
        }
        
        /**
        * 插入文章扩展字段的值 285206405@qq.com add by 2010-5-16
        */
        if (articleId > 0 && extFieldData != null && extFieldData.size() > 0)
        {
            extFieldData.set("article_id", articleId);
            customFieldService.addExtendFieldContent(extFieldData);
        }
        
        //复制文章
        if (chooseCopyCatalogs != null && chooseCopyCatalogs.length > 0)
        {
            article.setState(0);
            for (int i = 0; i < chooseCopyCatalogs.length; i++)
            {
                String chooseCopyCatalog = chooseCopyCatalogs[i];
                article.setCatalogId(new Integer(chooseCopyCatalog).intValue());
                article.setCatalogNo(catalogService.findCatalogById(Integer.parseInt(chooseCopyCatalogs[i])).getCatalogNo());
                articleId = articleService.addArticle(article);
            }
        }
        String successPage = getStrParameter("successPage");
        if (StringHelper.isNotEmpty(successPage))
        {
            successPage += "&id=" + article.getId();
        }
        
        form.set("id", article.getId());
        String am = article.getState() == 3 ? "发布" : "添加";
        addLog(am + "文章", am + "文章成功！");
        
        return super.add(request, reponse);
    }
    
    /**
    * 添加文章
    * @return
    */
    @RequestMapping("/doAdd.action")
    public ModelAndView doAdd(HttpServletRequest request, HttpServletResponse response)
    {
        int catalogId = getIntParameter("catalogId");
        form.set("catalogId", catalogId);
        
        //查询栏目信息
        Catalog catalog = catalogService.findCatalogById(catalogId);
        if (catalog == null)
        {
            ScriptHelper.alert(response, "栏目信息不存在，请勿非法操作！", "close");
            return null;
        }
        
        //取文章来源
        List<Object> sourceList = article_SourceService.findArticle_SourceBySiteNo(getSiteNo());
        form.set("sourceList", sourceList);
        
        //查询数据字典，取行业类别 285206405@qq.com add
        List<Object> enumDataList = enumService.getEnumListByType("INDUSTRY_TYPE");
        form.set("industryList", enumDataList);
        
        //取栏目级别
        DBPage page = enumService.getEnumItemByType(1, 20, "COLUMN_LEVEL", getSiteNo());
        List<Object> column_level = null;
        if (page != null)
        {
            column_level = page.getData();
            page = null;
        }
        form.set("column_level", column_level);
        
        mv.addObject("form", form);
        mv.setViewName("/WEB-INF/views/article/add_article.jsp");
        return mv;
        
    }
    
    /**
     * 描述: 作者管理
     * 作者: 袁永君
     * 创建日期: 2016-2-25
     * 创建时间: 上午9:55:17
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajaxAuthorManage.action")
    public Result doAjaxAuthorManage(HttpServletResponse response)
    {
        String type = RequestHelper.getString(getRequest(), "type");
        
        JSONArray jsonArr = new JSONArray();
        JSONObject jsonObj = new JSONObject();
        
        if ("add".equals(type))//添加文章作者
        {
            
            String author = RequestHelper.getString(getRequest(), "author");
            if (CharHelper.isUtf8Url(author))
            {
                author = CharHelper.Utf8URLdecode(author);
            }
            else
            {
                try
                {
                    author = URLDecoder.decode(author, "utf-8");
                }
                catch (UnsupportedEncodingException e)
                {
                    e.printStackTrace();
                }
            }
            
            if (!articleService.isAuthorExist(author))//判断作者是否存在
            {
                //获得用户登陆的站点
                String siteno = getLoginSiteNo();
                DataRow data = new DataRow();
                data.set("name", author);
                data.set("create_date", DateHelper.formatDate(new Date()));
                data.set("modify_date", DateHelper.formatDate(new Date()));
                data.set("createby", getUID());
                data.set("siteno", siteno);
                
                articleService.addAuthor(data);
                
                jsonObj.put("error_code", "0");//错误码
                jsonObj.put("error_msg", "添加文章作者成功！");//错误消息
            }
            else
            {
                jsonObj.put("error_code", "1");//错误码
                jsonObj.put("error_msg", "文章作者[" + author + "]已经存在！");//错误消息
            }
            jsonArr.add(jsonObj);
        }
        else if ("query".equals(type))//根据站点编号查询所有作者
        {
            DBPage page = articleService.findAuthor(1, 100, getLoginSiteNo(), "");
            if (page != null && page.getData() != null)
            {
                for (Iterator<Object> iter = page.getData().iterator(); iter.hasNext();)
                {
                    DataRow authoerData = (DataRow) iter.next();
                    jsonObj = new JSONObject();
                    jsonObj.put("author", authoerData.getString("name"));
                    jsonArr.add(jsonObj);
                }
            }
        }
        else if ("del".equals(type))
        {
            String ids = RequestHelper.getString(getRequest(), "ids");
            String[] idArray = StringHelper.split(ids, "|");
            for (int i = 0; i < idArray.length; i++)
            {
                articleService.delAuthor(idArray[i]);
            }
            jsonObj.put("error_code", "0");//错误码
            jsonObj.put("error_msg", "删除文章作者成功！");//错误消息
            jsonArr.add(jsonObj);
        }
        
        //        PrintWriter writer;
        //        try
        //        {
        //            writer = response.getWriter();
        //            writer.print(jsonArr.toString());
        //            writer.flush();
        //            writer.close();
        //        }
        //        catch (IOException e)
        //        {
        //            logger.error(e.getMessage());
        //        }
        Result result = new Result();
        result.setObj(jsonArr);
        return result;
    }
    
    /**
    * 查询评论
    * @return
    */
    public String doAjaxComment()
    {
        int articleId = RequestHelper.getInt(getRequest(), "articleId");
        int curPage = this.getIntParameter("page");
        curPage = (curPage <= 0) ? 1 : curPage;
        
        Review review = new Review();
        review.setElemId(articleId);
        DBPage page = commentService.getPageData(curPage, SysConfig.getRowOfPage(), 0, 0, 0, null, 0, null);
        dataMap.put("page", page);
        return "read_comment";
    }
    
    /**
    * 
    * @描述：
    * @作者：袁永君
    * @时间：2011-3-21 下午07:37:05
    * @return
    */
    @RequestMapping("/ajaxExtendFieldInfo.action")
    public ModelAndView doAjaxExtendFieldInfo()
    {
        int catalogId = RequestHelper.getInt(getRequest(), "catalogId");
        int articleId = RequestHelper.getInt(getRequest(), "articleId");
        Catalog catalog = catalogService.findCatalogById(catalogId);
        
        if (catalogId > 0)
        {
            //查询当前栏目中是否有自定义字段 285206405@qq.com add by 2010-5-15
            List<Object> extendFieldList = null;
            if (catalog.getInheritField() == 1)//判断当前栏目是否继承父栏目的文档自定义字段
            {
                extendFieldList = customFieldService.cycFindExtendFieldInfo(catalogId);
            }
            else
            {
                extendFieldList = customFieldService.findExtendFieldInfo(catalogId);
            }
            
            if (extendFieldList != null && extendFieldList.size() > 0)
            {
                //将自定义字段组成需要查询的字段
                StringBuffer field_codes = new StringBuffer();
                for (Iterator<Object> iter = extendFieldList.iterator(); iter.hasNext();)
                {
                    DataRow fieldData = (DataRow) iter.next();
                    String code = fieldData.getString("code");
                    
                    field_codes.append(code);
                    if (iter.hasNext())
                        field_codes.append(",");
                }
                
                DataRow fieldContentData = null;
                if (articleId > 0 && StringHelper.isNotEmpty(field_codes.toString()))
                {
                    fieldContentData = customFieldService.findExtendFieldContent(articleId, field_codes.toString());
                    
                    if (fieldContentData != null)
                    {
                        for (Iterator<Object> iter = extendFieldList.iterator(); iter.hasNext();)
                        {
                            DataRow fieldData = (DataRow) iter.next();
                            String code = fieldData.getString("code");
                            
                            if (fieldContentData != null)
                            {
                                String value = fieldContentData.getString(code.toLowerCase());
                                fieldData.set("default_value", value);
                            }
                        }
                    }
                }
                dataMap.put("extendFieldList", extendFieldList);
            }
        }
        ModelAndView mv = new ModelAndView("/WEB-INF/views/article/extendField.jsp");
        mv.addObject("data", dataMap);
        return mv;
    }
    
    /**
     * 描述: 异步查询自定义字段
     * 作者: 袁永君
     * 创建日期: 2016-2-25
     * 创建时间: 上午10:47:02
     * @return
     */
    @RequestMapping("/ajaxReadCatalogTree.action")
    public ModelAndView doAjaxReadCatalogTree()
    {
        //String siteNo = RequestHelper.getString(getRequest(), "siteNo");
        String siteNo = getSiteNo();
        //		if (StringHelper.isEmpty(siteNo))
        //		{
        //			return NONE;
        //		}
        //		int roleId = RequestHelper.getInt(getRequest(), "roleId");
        //		if (roleId == 0)
        //		{
        //			return NONE;
        //		}
        //		
        //		RoleService service = (RoleService) getService(RoleService.class);
        //		//查找角色信息
        //		Role role = service.findRoleById(roleId, "");
        //		if (role != null)
        //		{
        //			BeanHelper.beanToMap(role, form);
        //		}
        //		
        
        DataRow catalogRole = SysLibrary.getUserCatalogRight(getSession());
        
        List<DataRow> dataList = catalogService.findCatalogTrue(0, siteNo, SysLibrary.isSystemAdmin(getSession()), catalogRole);
        dataMap.put("dataList", dataList);
        
        //		SiteService siteService = (SiteService) getService(SiteService.class);
        //		List siteList = siteService.getAllSite();
        //		dataMap.put("siteList", siteList);
        
        ModelAndView mv = new ModelAndView("/WEB-INF/views/article/read_catalog_tree.jsp");
        mv.addObject("data", dataMap);
        return mv;
    }
    
    /**
    * 
    * 描述:  查看权限栏目树
    * 作者:	 285206405@qq.com
    * 创建日期: 2010-1-3
    * 创建时间: 下午11:47:04
    * @return String
    * @throws
    */
    public String doAjaxShowCatalogRoleRight()
    {
        DataRow catalogRole = SysLibrary.getUserCatalogRight(getSession());
        
        //取所有子栏目信息
        List<Object> childrenCatalogs = catalogService.findWholeCatalogById(1, getSiteNo());
        CatalogTreeManage treeManage = new CatalogTreeManage();
        String html = treeManage.getChildrenTreeHtml(childrenCatalogs, SysLibrary.isSystemAdmin(getSession()), catalogRole);
        ResponseHelper.print(response, html);
        return NONE;
    }
    
    public String doAjaxShowCatalogs(HttpServletResponse response)
    {
        int catalogId = getIntParameter("catalogId", 0);
        List<Catalog> childrenList = catalogService.findChildrenById(catalogId, "main");
        StringBuffer strBuf = new StringBuffer("");
        for (Iterator<Catalog> iter = childrenList.iterator(); iter.hasNext();)
        {
            Catalog catalog = iter.next();
            String name = catalog.getName();
            name = StringHelper.replace(name, "\"", "&quot;");
            name = StringHelper.replace(name, "\'", "&quot;");
            
            strBuf.append("<li>");
            strBuf.append("<input type=\"checkbox\">");
            strBuf.append("<label class=\"DetailTagText\">").append(name).append("</label>");
            strBuf.append("<ul id=\"ul_").append(catalog.getId()).append("\"></ul>");
            strBuf.append("</li>");
        }
        
        response.reset();
        response.setContentType("text/xml;charset=UTF-8");
        try
        {
            java.io.PrintWriter os = response.getWriter();
            os.write(strBuf.toString());
            os.close();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        return NONE;
    }
    
    /**
    * 描述：生成树形菜单所需的xml
    * 作者：袁永君
    * 时间：Aug 27, 2009 10:49:52 AM
    * @return
    */
    public String doAjaxShowChildsCatalogsToCT()
    {
        Catalog rootCatalog = catalogService.findRootCatalog(getLoginSiteNo());
        
        String curCatalogId = RequestHelper.getString(getRequest(), "catalogId");//父栏目id
        
        int catalogId = rootCatalog.getId();//获取根栏目id
        boolean isRootCatalog = true;//当前是否为根栏目
        if (StringHelper.isNotEmpty(curCatalogId))//curCatalogId不为空时设置当前栏目不是根栏目
        {
            catalogId = Integer.parseInt(curCatalogId);
            isRootCatalog = false;
        }
        //通过栏目id获取子栏目
        List<Catalog> childrenList = catalogService.findChildrenById(catalogId, getLoginSiteNo());
        StringBuffer strBuf = new StringBuffer();//xml字串
        strBuf.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        strBuf.append("<nodes>");
        if (isRootCatalog)
        {
            strBuf.append("<node id=\"root\" text=\"栏目\">");
        }
        for (Iterator<Catalog> iter = childrenList.iterator(); iter.hasNext();)
        {
            Catalog catalog = iter.next();
            String name = catalog.getName();
            name = StringHelper.replace(name, "\"", "&quot;");
            name = StringHelper.replace(name, "\'", "&quot;");
            
            strBuf.append("<node id=\"").append(catalog.getId()).append("\" ").append("text=\"").append(name).append("\" ");
            if (catalogService.hasChildren(catalog.getId()))//是否有子栏目
            {
                String url = getRequest().getContextPath() + "/admin/CGWSArticle.action?function=AjaxShowChildsCatalogsToCT&catalogId=" + catalog.getId();
                strBuf.append("XMLData=\"").append(StringHelper.encodeHtml(url)).append("\" ");//动态调用所需url
            }
            strBuf.append("></node>");
        }
        if (isRootCatalog)
        {
            strBuf.append("</node>");
        }
        strBuf.append("</nodes>");
        response.reset();
        response.setContentType("text/xml;charset=UTF-8");
        try
        {
            java.io.PrintWriter os = response.getWriter();
            os.write(strBuf.toString());
            os.close();
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
        }
        return NONE;
    }
    
    @RequestMapping("/doBar.action")
    public ModelAndView doBar()
    {
        mv.setViewName("/WEB-INF/views/article/bar.jsp");
        return mv;
    }
    
    /**
    * 取消头条
    * @return
    */
    @ResponseBody
    @RequestMapping("/cancelHead.action")
    public Result doCancelHead()
    {
        Result result = new Result();
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++)
        {
            Article article = new Article();
            article.setId(idArray[i]);
            article.setIsHead(0); //取消最新
            articleService.updateArticle(article);
            addLog("取消头条", "取消头条[id=" + idArray[i] + "]");
        }
        
        result.setErrorInfo("操作成功！");
        return result;
    }
    
    /**
    * 取消推荐
    * @return
    */
    @ResponseBody
    @RequestMapping("/cancelHot.action")
    public Result doCancelHot()
    {
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++)
        {
            Article article = new Article();
            article.setId(idArray[i]);
            article.setIsHot(0); //取消推荐
            articleService.updateArticle(article);
            addLog("取消推荐", "取消推荐[id=" + idArray[i] + "]");
        }
        Result result = new Result();
        result.setErrorInfo("操作成功！");
        return result;
    }
    
    /**
    * 取消最新
    * @return
    */
    @ResponseBody
    @RequestMapping("/cancelNew.action")
    public Result doCancelNew()
    {
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++)
        {
            Article article = new Article();
            article.setId(idArray[i]);
            article.setIsNew(0); //取消最新
            articleService.updateArticle(article);
            addLog("取消最新", "取消最新[id=" + idArray[i] + "]");
            
        }
        Result result = new Result();
        result.setErrorInfo("操作成功！");
        return result;
    }
    
    /**
    * 描述：复制文章
    * 作者：袁永君
    * 时间：Aug 27, 2009 10:49:52 AM
    *
    * @return
    */
    public String doCopyArticle()
    {
        if (isPostBack())
        {
            String catalogs = RequestHelper.getString(getRequest(), "catalogs");
            //int isPublish = Configuration.getInt("system.isPublish", 1);//是否启动发布队列
            //int isPublish = Configuration.getInt("isPublish");//是否发布文章
            int isPublish = this.getIntParameter("isPublish");
            int isPublishArticle = Configuration.getInt("system.isPublishArticle", 1);//是否需要静态化文章
            
            int copyOrRemove = getIntParameter("copyOrRemove");//复制或转移
            
            int[] idArray = getIntArrayParameter("id");
            String[] arrCatalogs = new String[0];
            if (StringHelper.isNotEmpty(catalogs))
            {
                arrCatalogs = catalogs.split(",");
            }
            for (int i = 0; i < idArray.length; i++)
            {
                Article article = articleService.findArticleById(idArray[i]);
                for (int j = 0; j < arrCatalogs.length; j++)
                {
                    article.setCatalogId(Integer.parseInt(arrCatalogs[j]));
                    article.setCatalogNo(catalogService.findCatalogById(Integer.parseInt(arrCatalogs[j])).getCatalogNo());
                    article.setPublishBy(getUID());
                    article.setCreateDate(DateHelper.formatDate(new Date()));
                    if (1 == isPublish)
                    {
                        article.setPublishDate(DateHelper.formatDate(new Date()));
                        article.setState(3); //设为已经发布
                    }
                    else
                    {
                        article.setPublishDate(DateHelper.formatDate(new Date()));
                        article.setState(0); //设为未发布
                    }
                    int articleId = articleService.addArticle(article);
                    
                    //发布文章
                    if (articleId > 0 && 1 == isPublish && 1 == isPublishArticle)
                    {
                        //把要发布的文章添加入发布队列
                        addToPublishQueue(article.getId(), "A");
                    }
                }
                addLog("拷贝文章", "拷贝文章[id=" + idArray[i] + "]到栏目［id=" + catalogs + "］");
                if (1 == copyOrRemove)
                {
                    String url = articleService.findUrlById(idArray[i]);
                    if (!StringHelper.isEmpty(url)) //若已经发布，则需要删除已经发布的文件
                    {
                        FileHelper.deleteFile(Application.getRootPath() + url);
                    }
                    
                    articleService.deleteArticle(idArray[i]);
                    addLog("删除文章", "删除文章[id=" + idArray[i] + "]");
                }
            }
            
            String successPage = getStrParameter("successPage");
            ScriptHelper.redirect(response, successPage);
            return NONE;
        }
        else
        {
            return "copy_article";
        }
    }
    
    /**
    * 缺省的操作(function=""时调用)
    * 转向栏目的首页页面
    * @return
    */
    @Override
    @RequestMapping("/doDefault.action")
    public ModelAndView doDefault()
    {
        mv.setViewName("/WEB-INF/views/article/default.jsp");
        return mv;
    }
    
    /**
    * 删除文章
    * @return
    */
    @Override
    @ResponseBody
    @RequestMapping("/delete.action")
    public Result delete(HttpServletRequest request, HttpServletResponse response)
    {
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++)
        {
            int articleId = idArray[i];
            String url = articleService.findUrlById(articleId);
            if (!StringHelper.isEmpty(url)) //若已经发布，则需要删除已经发布的文件
            {
                FileHelper.deleteFile(Application.getRootPath() + url);
            }
            
            articleService.deleteArticle(articleId);
            addLog("删除文章", "删除文章[id=" + articleId + "]");
        }
        return super.delete(request, response);
    }
    
    /**
    * 描述:  根据文章ID删除文章
    * 作者:	 285206405@qq.com
    * 创建日期: 2010-1-10
    * 创建时间: 下午11:34:20
    * @return String
    * @throws
    */
    public String doDeleteById()
    {
        if (isPostBack())
        {
            normalize(form);
            Article article = new Article();
            BeanHelper.mapToBean(form, article);
            
            articleService.deleteArticle(article.getId());
            
            String url = article.getUrl();
            if (!StringHelper.isEmpty(url)) //若已经发布，则需要删除已经发布的文件
            {
                FileHelper.deleteFile(Application.getRootPath() + url);
            }
            addLog("删除文章", "删除文章[id=" + article.getId() + "]成功！");
            
            return "article_message";
        }
        return NONE;
    }
    
    /**
    * 编辑文章
    * @return
    */
    @Override
    @RequestMapping("/doEdit.action")
    public ModelAndView doEdit(HttpServletResponse response)
    {
        int id = getIntParameter("id");
        Article article = articleService.findArticleById(id);
        if (article != null)
        {
            BeanHelper.beanToMap(article, form);
            
            int catalogId = article.getCatalogId();
            form.set("catalogId", catalogId);
            
            List<Object> sourceList = article_SourceService.findArticle_SourceBySiteNo(getSiteNo());
            form.set("sourceList", sourceList);
            
            //查询数据字典，取行业类别 285206405@qq.com add
            List<Object> enumDataList = enumService.getEnumListByType("INDUSTRY_TYPE");
            form.set("industryList", enumDataList);
            
            //取栏目级别
            DBPage page = enumService.getEnumItemByType(1, 20, "COLUMN_LEVEL", getSiteNo());
            List<Object> column_level = null;
            if (page != null)
            {
                column_level = page.getData();
                page = null;
            }
            form.set("column_level", column_level);
            
            AttachService serivce = new AttachService();
            DataRow attachData = serivce.getAttachBySourceId(0, id);
            if (attachData != null)
            {
                dataMap.put("attach", attachData);
            }
            
        }
        else
        {
            ScriptHelper.alert(response, "文章不存在或已经被删除！", "close");
            return null;
        }
        
        mv.addObject("data", dataMap);
        mv.addObject("form", form);
        mv.setViewName("/WEB-INF/views/article/edit_article.jsp");
        return mv;
    }
    
    /**
    * 显示左侧的页面
    * @return
    */
    @RequestMapping("/doLeft.action")
    public ModelAndView doLeft()
    {
        String isPublish = Configuration.getString("system.isPublish", "1");//是否启动发布队列
        String isPublishArticle = Configuration.getString("system.isPublishArticle", "1");//是否需要静态化文章
        
        dataMap.put("isPublish", isPublish);
        dataMap.put("isPublishArticle", isPublishArticle);
        
        ModelAndView mv = new ModelAndView();
        mv.addObject("data", dataMap);
        mv.setViewName("/WEB-INF/views/article/left.jsp");
        return mv;
    }
    
    /**
    * 显示某该栏目的文章
    * @return
    */
    @RequestMapping("/doList.action")
    public ModelAndView doList()
    {
        int catalogId = getIntParameter("catalogId");
        
        String title = getStrParameter("title");
        String author = getStrParameter("author");
        int state = getIntParameter("state", -1);
        String startDate = getStrParameter("startDate");//开始时间
        String endDate = getStrParameter("endDate");//结束时间
        
        String siteno = getLoginSiteNo();
        
        int curPage = this.getIntParameter("page");
        curPage = (curPage <= 0) ? 1 : curPage;
        
        Catalog catalog = catalogService.findCatalogById(catalogId, siteno);
        if (catalog != null)
        {
            dataMap.put("catalogName", catalog.getName());
        }
        DBPage page = articleService.getPageData(curPage, SysConfig.getRowOfPage(), siteno, catalogId, title, state, author, startDate, endDate);
        dataMap.put("page", page);
        dataMap.put("isEndCode", new Boolean(articleService.isEndCode(catalogId)));
        
        mv.addObject("data", dataMap);
        mv.setViewName("/WEB-INF/views/article/list_article.jsp");
        return mv;
    }
    
    /**
    * 
    * @描述：
    * @作者：袁永君
    * @时间：2010-9-19 下午01:04:38
    * @return
    */
    @RequestMapping("/message.action")
    public String doMessage()
    {
        String type = RequestHelper.getString(getRequest(), "type");
        
        if ("virtualCatalog".equals(type))//虚拟栏目
        {
            int catalogId = RequestHelper.getInt(getRequest(), "catalogId");
            
            Catalog catalog = catalogService.findCatalogById(catalogId);
            if (catalog == null || "".equals(catalog.getRoute()))
            {
                //ScriptHelper.alert(response, "栏目不存在，请勿非法操作", "close");
                return NONE;
            }
            String route = catalog.getRoute();
            String[] routes = route.split("\\|");
            String parentCatalogs = "";
            for (int i = 0; i < routes.length; i++)
            {
                Catalog paramCatalog = catalogService.findCatalogById(Integer.parseInt(routes[i]));
                if (paramCatalog != null)
                {
                    parentCatalogs += paramCatalog.getName();
                    if (i < routes.length - 1)
                    {
                        parentCatalogs += "->";
                    }
                }
            }
            
            dataMap.put("message", "对不起，该栏目属于虚拟栏目只读类型，请到<a href='article.action?function=list&menuNo=&catalogId=" + catalogId + "'>源栏目</a>（" + parentCatalogs
                    + "）中管理文章！");
            return ERROR;
        }
        
        return NONE;
    }
    
    /**
    * 
    * @描述：预览栏目或文章
    * @作者：袁永君
    * @时间：2012-4-11 下午01:52:35
    * @return
    */
    @ResponseBody
    @RequestMapping(value = "/doPreview.action", produces = "text/html;charset=UTF-8;")
    public void doPreview(HttpServletResponse response)
    {
        int catalogId = getIntParameter("catalogId");//栏目ID
        int articleId = getIntParameter("articleId");//文章ID
        
        try
        {
            TemplatePreview templatePreview = new TemplatePreview(catalogId, articleId);
            
            String content = templateService.parseTemplate(templatePreview);
            
            String encoding = Configuration.getString("system.encoding", "GBK");
            response.setContentType("text/html;charset=" + encoding);
            response.getWriter().print(content);
            
        }
        catch (Exception ex)
        {
            logger.error("", ex);
        }
    }
    
    /**
    * 发布文章
    * @return
    */
    @ResponseBody
    @RequestMapping("/publish.action")
    public Result doPublish()
    {
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++)
        {
            //285206405@qq.com modify by 20160215 增加查询当前ID的文章状态，只有发布时间为空，才将当前时间赋予发布时间
            Article article = articleService.findArticleById(idArray[i]);
            if (article == null)
            {
                continue;
            }
            //修改状态
            Article upArticle = new Article();
            upArticle.setPublishBy(getUID());
            if (StringHelper.isEmpty(article.getPublishDate()))
            {
                upArticle.setPublishDate(DateHelper.formatDate(new Date()));
            }
            upArticle.setModifiedBy(getUID());
            upArticle.setModifiedDate(DateHelper.formatDate(new Date()));
            upArticle.setId(idArray[i]);
            upArticle.setState(Constants.ARTICLE_STATE_PUBLISHED); //设为已经发布
            articleService.updateArticle(upArticle);
            
            int isPublish = Configuration.getInt("system.isPublish", 1);//是否启动发布队列
            int isPublishArticle = Configuration.getInt("system.isPublishArticle", 1);//是否需要静态化文章
            if (1 == isPublish && 1 == isPublishArticle)
            {
                //把要发布的文章添加入发布队列
                addToPublishQueue(upArticle.getId(), "A");
            }
            
            //记录发布日志
            addLog("发布文章", "发布文章[id=" + idArray[i] + "]", article.getCatalogId());
            upArticle = null;
            article = null;
        }
        result.setErrorNo(0);
        result.setErrorInfo("操作成功");
        return result;
    }
    
    /**
    * @描述：重新发布栏目指定数量的文章,只对已发布的文章生效
    * @作者：袁永君
    * @时间：2010-05-18 18:46:49
    * @return
    */
    public String doPublishArticleRecursio()
    {
        int isPublish = Configuration.getInt("system.isPublish", 1);//是否启动发布队列
        int isPublishArticle = Configuration.getInt("system.isPublishArticle", 1);//是否需要静态化文章
        if (1 == isPublish && 1 == isPublishArticle)
        {
            
            int catalogId = getIntParameter("catalogId");
            int rows = getIntParameter("rows", 200);//发布文章的数据量
            
            List<Object> catalogList = articleService.findUnionArtilceByCatalog(catalogId);
            List<DataRow> articleList = null;
            DataRow articleData = null;
            for (Iterator<Object> catalogIter = catalogList.iterator(); catalogIter.hasNext();)
            {
                DataRow catalog = (DataRow) catalogIter.next();
                articleList = articleService.findPublishArticleById(catalog.getInt("catalog_id"), rows, 1);
                for (int i = 0; i < articleList.size(); i++)
                {
                    articleData = (DataRow) articleList.get(i);
                    //把要发布的文章添加入发布队列
                    addToPublishQueue(articleData.getInt("article_id"), "A");
                    
                    //记录发布日志
                    addLog("发布文章", "发布文章[id=" + articleData.getInt("article_id") + "]", catalogId);
                }
                
            }
        }
        return NONE;
    }
    
    /**
    * 发布某栏目
    * @return
    */
    @ResponseBody
    @RequestMapping("/publishCatalog.action")
    public Result doPublishCatalog(HttpServletResponse response)
    {
        Result result = new Result();
        int isPublish = Configuration.getInt("system.isPublish", 1);//是否启动发布队列
        //判断发布引擎是否启动
        if (1 == isPublish)
        {
            int catalogId = getIntParameter("catalogId");
            
            //把要发布的栏目添加入发布队列
            addToPublishQueue(catalogId, "C");
            
            //判断该栏目下是否有需要附带发布的栏目
            //CatalogService catalogService = (CatalogService) getService(CatalogService.class);
            List<Object> attachList = catalogService.findAttachCatalog(catalogId, getSiteNo());
            for (Iterator<Object> iter = attachList.iterator(); iter.hasNext();)
            {
                DataRow data = (DataRow) iter.next();
                addToPublishQueue(data.getInt("attach_catalog_id"), "C");
            }
            result.setErrorInfo("栏目[catalogId=" + catalogId + "]发布已经添加到发布队列中");
        }
        return result;
    }
    
    /**
    * 发布本栏目及其子栏目
    * @return
    */
    @ResponseBody
    @RequestMapping("/publishCatalogRecursion.action")
    public Result doPublishCatalogRecursion(HttpServletResponse response)
    {
        int isPublish = Configuration.getInt("system.isPublish", 1);//是否启动发布队列
        Result result = new Result();
        //判断发布引擎是否启动
        if (1 == isPublish)
        {
            int catalogId = getIntParameter("catalogId");
            
            //把要发布的栏目添加入发布队列
            addToPublishQueue(catalogId, "CR");
            
            ScriptHelper.alert(response, "栏目[catalogId=" + catalogId + "]及其子栏目发布已经添加到发布队列中");
            result.setErrorInfo("栏目[catalogId=" + catalogId + "]及其子栏目发布已经添加到发布队列中");
        }
        return result;
    }
    
    /**
    * 驳回文章
    * @return
    */
    @ResponseBody
    @RequestMapping("/reject.action")
    public Result doReject()
    {
    	Result result = new Result();
    	int[] idArray = getIntArrayParameter("id");
    	for (int i = 0; i < idArray.length; i++)
    	{
    		int articleId = idArray[i];
    		
    		String url = articleService.findUrlById(articleId);
    		if (!StringHelper.isEmpty(url)) //若已经生成文件，则需要删除已经发布的文件
    		{
    			FileHelper.deleteFile(Application.getRootPath() + url);
    		}
    		
    		Article article = new Article();
    		article.setId(articleId);
    		article.setState(0); //设为未发布
    		article.setUrl(""); //清空生成的文件URL
    		articleService.updateArticle(article);
    		
    		addLog("驳回文章", "驳回文章[id=" + idArray[i] + "]", article.getCatalogId());
    	}
    	result.setErrorInfo("操作成功!");
    	return result;
    }
    
    /**
    * 设为头条
    * @return
    */
    @ResponseBody
    @RequestMapping("/setHead.action")
    public Result doSetHead()
    {
        Result result = new Result();
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++)
        {
            Article article = new Article();
            article.setId(idArray[i]);
            article.setIsHead(1); //设为头条
            articleService.updateArticle(article);
            addLog("设为头条", "设为头条[id=" + idArray[i] + "]");
        }
        result.setErrorInfo("操作成功！");
        return result;
    }
    
    /**
    * 设为推荐
    * @return
    */
    @ResponseBody
    @RequestMapping("/setHot.action")
    public Result doSetHot()
    {
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++)
        {
            Article article = new Article();
            article.setId(idArray[i]);
            article.setIsHot(1); //设为推荐
            articleService.updateArticle(article);
            addLog("设为推荐", "设为推荐[id=" + idArray[i] + "]");
        }
        Result result = new Result();
        result.setErrorInfo("操作成功！");
        return result;
    }
    
    /**
    * 设为最新
    * @return
    */
    @ResponseBody
    @RequestMapping("/setNew.action")
    public Result doSetNew()
    {
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++)
        {
            Article article = new Article();
            article.setId(idArray[i]);
            article.setIsNew(1); //设为最新
            articleService.updateArticle(article);
            addLog("设为最新", "设为最新[id=" + idArray[i] + "]");
        }
        Result result = new Result();
        result.setErrorInfo("操作成功！");
        return result;
    }
    
    /**
     * 描述: 显示作者
     * 作者: 袁永君
     * 创建日期: 2016-2-25
     * 创建时间: 上午10:46:30
     * @return
     */
    @RequestMapping("/showAuthor.action")
    public ModelAndView doShowAuthor()
    {
        String keyword = getStrParameter("keyword");
        int curPage = this.getIntParameter("page");
        curPage = (curPage <= 0) ? 1 : curPage;
        keyword = StringHelper.trim(keyword);
        DBPage page = articleService.findAuthor(curPage, 15, getSiteNo(), keyword);
        dataMap.put("page", page);
        ModelAndView mv = new ModelAndView();
        mv.addObject("data", dataMap);
        mv.setViewName("/WEB-INF/views/article/show_author.jsp");
        return mv;
    }
    
    /**
    * 得到子目录的xml文档
    * @return
    */
    @ResponseBody
    @RequestMapping(value = "/showCatalog.action", produces = "text/xml;charset=GBK")
    public String doShowCatalog(HttpServletRequest request, HttpServletResponse response)
    {
        int catalogId = getIntParameter("catalogId", 0);
        StringBuffer buffer = new StringBuffer();
        
        response.setContentType("text/xml;charset=GBK");
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setDateHeader("expires", -10);
        
        buffer.append("<?xml version=\"1.0\" encoding=\"GBK\"?>\n");
        buffer.append("<tree>\n");
        
        String menuNo = getStrParameter("menuNo");
        //首先获得用户登陆的站点
        String siteno = getLoginSiteNo();
        if (catalogId <= 0) //显示根目录
        {
            //Catalog rootCatalog = catalogService.findRootCatalog(getSiteNo());
            //查找根栏目
            Catalog rootCatalog = catalogService.findRootCatalog(siteno);
            buffer.append("   <tree text=\"" + rootCatalog.getName()
                    + "\" target=\"articleRightFrame\" action=\"javascript:void(0);\" src=\"showCatalog.action?root&amp;menuNo=" + menuNo + "&amp;catalogId="
                    + rootCatalog.getId() + "\"  value=\"" + rootCatalog.getId() + "\" oncontextmenu=\"true\" />\n");
        }
        else
        {
            HttpSession session = request.getSession();
            DataRow rights = SysLibrary.getUserCatalogRight(session);
            
            //查询子栏目
            List<Catalog> childrenList = catalogService.findChildrenById(catalogId, siteno);
            
            for (Iterator<Catalog> iter = childrenList.iterator(); iter.hasNext();)
            {
                Catalog catalog = iter.next();
                
                if (catalog.getState() == 0)
                {
                    continue;
                }
                
                //判断是否拥有访问该栏目权限
                if (!SysLibrary.isSystemAdmin(session) && !rights.containsKey(String.valueOf(catalog.getId())))
                {
                    continue;
                }
                
                String name = catalog.getName();
                name = StringHelper.replace(name, "\"", "&quot;");
                name = StringHelper.replace(name, "\'", "&quot;");
                
                String url = "javascript:void(0)";
                if (catalog.getChildrennum() == 0)
                {
                    if (catalog.getAttribute() > 0 && catalog.getAttribute() == 1)
                    {
                        //url = "javascript:alert('对不起，该栏目属于虚拟栏目只读类型，请到源栏目（" + catalog.getSourceId() + "）中管理文章！')";
                        url = "message.action?type=virtualCatalog&amp;catalogId=" + catalog.getSourceId();
                    }
                    else
                    {
                        url = "doList.action?menuNo=" + menuNo + "&amp;catalogId=" + catalog.getId();
                    }
                }
                
                String src = "";
                if (catalog.getChildrennum() > 0)
                {
                    src = "showCatalog.action?menuNo=" + menuNo + "&amp;catalogId=" + catalog.getId();
                }
                
                buffer.append("   <tree text=\"" + name + "\" target=\"articleRightFrame\" action=\"" + url + "\" src=\"" + src + "\" value=\""
                        + catalog.getId() + "\" oncontextmenu=\"true\" />\n");
            }
        }
        
        buffer.append("</tree>");
        return buffer.toString();
    }
    
    @RequestMapping("/doShowCatalogs.action")
    public ModelAndView doShowCatalogs()
    {
        List<Catalog> childrenList = catalogService.findChildrenById(1, "main");
        if (childrenList != null)
        {
            setAttribute("childrenList", childrenList);
        }
        
        mv.setViewName("/WEB-INF/views/article/show_catalogs.jsp");
        return mv;
    }
    
    @Override
    @ResponseBody
    @RequestMapping("/edit.action")
    public Result edit(HttpServletRequest request, HttpServletResponse reponse)
    {
        form = normalize(request);
        Article article = new Article();
        BeanHelper.mapToBean(form, article);
        
        extFieldform = normalize(request, "extFieldform");
        DataRow extFieldData = new DataRow();
        extFieldData.putAll(extFieldform);
        
        int isPublish = Configuration.getInt("system.isPublish", 1);//是否启动发布队列
        int isPublishArticle = Configuration.getInt("system.isPublishArticle", 1);//是否需要静态化文章
        
        if (getStrParameter("replace").equalsIgnoreCase("true"))
        {
            article.setContent(replaceKeyword(article.getContent()));
        }
        
        //需要复制文章的栏目ID
        String[] chooseCopyCatalogs = RequestHelper.getStringArray(getRequest(), "chooseCopyCatalog");
        
        article.setModifiedBy(getUID());
        article.setModifiedDate(DateHelper.formatDate(new Date()));
        
        /**
        * 如果摘要为空，自动提取内容前500个字符为摘要
        */
        if (StringHelper.isEmpty(article.getBrief()))
        {
            String content = article.getContent();
            content = StringHelper.clearHtml(content);//清除内容中的样式
            String brief = "";
            brief = StringHelper.truncate(content, SPLIT_NUM, false);
            article.setBrief(brief);
        }
        
        String date = form.getString("publish_date");
        if (article.getState() == 3)
        {
            if (StringHelper.isNotEmpty(date))
            {
                article.setPublishDate(date);
            }
            else
            {
                article.setPublishDate(DateHelper.formatDate(new Date()));
            }
            
            if (1 == isPublish && 1 == isPublishArticle)
            {
                
                //把要发布的文章添加入发布队列                
                addToPublishQueue(article.getId(), "A");
            }
            
            //记录发布日志
            addLog("发布文章", "发布文章[id=" + article.getId() + "]", article.getCatalogId());
            
        }
        
        articleService.updateArticle(article);
        
        /**
        * 保存附件
        */
        String attach_url = getStrParameter("attach_url");
        String attach_savename = getStrParameter("attach_savename");
        String attach_realname = getStrParameter("attach_realname");
        
        DataRow fileUploadData = new DataRow();
        fileUploadData.set("source", "0");
        fileUploadData.set("source_id", article.getId());
        fileUploadData.set("save_filename", attach_savename);
        fileUploadData.set("real_filaname", attach_realname);
        fileUploadData.set("url", attach_url);
        fileUploadData.set("create_by", getUID());
        fileUploadData.set("create_date", DateHelper.formatDate(new Date()));
        fileUploadData.set("down_count", "0");
        
        attachService.editAttach(0, article.getId(), fileUploadData);
        
        /**
        * 插入文章扩展字段的值
        */
        if (extFieldData != null && extFieldData.size() > 0)
        {
            extFieldData.set("article_id", article.getId());
            //判断扩展信息的值是否存在
            if (customFieldService.isExistsExtendFieldById(article.getId()))
            {
                customFieldService.editExtendFieldContent(extFieldData);
            }
            else
            {
                customFieldService.addExtendFieldContent(extFieldData);
            }
        }
        addLog("编辑文章", "编辑文章[id=" + article.getId() + "]", article.getCatalogId());
        
        //复制文章
        if (chooseCopyCatalogs != null && chooseCopyCatalogs.length > 0)
        {
            Article copyArticle = articleService.findArticleById(article.getId());
            copyArticle.setState(0);
            for (int i = 0; i < chooseCopyCatalogs.length; i++)
            {
                String chooseCopyCatalog = chooseCopyCatalogs[i];
                copyArticle.setCatalogId(new Integer(chooseCopyCatalog).intValue());
                copyArticle.setCatalogNo(catalogService.findCatalogById(Integer.parseInt(chooseCopyCatalogs[i])).getCatalogNo());
            }
        }
        
        addLog("编辑文章", "修改文章成功！");
        if (RequestHelper.getInt(getRequest(), "isPreview") == 1)//预览文章
        {
            ScriptHelper.redirect(reponse, "/WEB-INF/views/article/messages.jsp");
            return null;
        }
        
        return super.edit(request, reponse);
    }
    
    private String replaceKeyword(String content)
    {
        List<ArticleKeyword> dataList = articleKeywordService.findAll();
        if (dataList != null && dataList.size() > 0)
        {
            for (Iterator<ArticleKeyword> iter = dataList.iterator(); iter.hasNext();)
            {
                ArticleKeyword articleKeyword = iter.next();
                String name = articleKeyword.getName();
                String url = articleKeyword.getUrl();
                content = StringHelper.replace(content, name, "<a target='_blank' href='" + url + "'>" + name + "</a>");
            }
        }
        return content;
    }
}
