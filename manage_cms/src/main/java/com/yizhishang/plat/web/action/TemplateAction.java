package com.yizhishang.plat.web.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yizhishang.base.config.Configuration;
import com.yizhishang.base.config.SysConfig;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.util.BeanHelper;
import com.yizhishang.base.util.DateHelper;
import com.yizhishang.base.util.FileHelper;
import com.yizhishang.base.util.RequestHelper;
import com.yizhishang.base.util.ResponseHelper;
import com.yizhishang.base.util.ScriptHelper;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.base.util.ThrowableHelper;
import com.yizhishang.base.util.zip.ZipHelper;
import com.yizhishang.plat.domain.Catalog;
import com.yizhishang.plat.domain.Result;
import com.yizhishang.plat.domain.Template;
import com.yizhishang.plat.export.TemplateXmlManage;
import com.yizhishang.plat.imp.TemplateImpManage;
import com.yizhishang.plat.service.CatalogService;
import com.yizhishang.plat.service.TemplateService;
import com.yizhishang.plat.system.Application;
import com.yizhishang.plat.template.TemplatePreview;
import com.yizhishang.plat.web.form.DynaForm;

/**
 * 描述: 网站模板管理
 * 版权: Copyright (c) 2016
 * 公司: 思迪科技 yuanyj@yizhishang.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-2-23
 * 创建时间: 下午2:27:37
 */
@Controller
@RequestMapping("/admin/templateAdmin")
public class TemplateAction extends BaseAction
{
    
    private static Logger logger = LoggerFactory.getLogger(TemplateAction.class);
    
    @Resource
    CatalogService catalogService;
    
    @Resource
    TemplateService templateService;
    
    @Override
    @ResponseBody
    @RequestMapping("/add.action")
    public Result add(HttpServletRequest request, HttpServletResponse response)
    {
        DynaForm form = normalize(request);
        Template template = new Template();
        BeanHelper.mapToBean(form, template);
        
        String siteNo = getSiteNo();
        template.setState(1);
        template.setSiteNo(siteNo);
        template.setCreateBy(getUID());
        template.setCreateDate(DateHelper.formatDate(new Date()));
        template.setModifiedBy(getUID());
        template.setModifiedDate(DateHelper.formatDate(new Date()));
        
        templateService.addTemplate(template);
        
        addLog("添加模板", "添加模板[name=" + template.getName() + "]");
        result.setErrorNo(0);
        result.setErrorInfo("添加模板[" + template.getName() + "]成功！");
        return result;
    }
    
    /**
    * 描述:  添加模板
    * 作者:	 袁永君
    * 创建日期: 2015-11-18
    * 创建时间: 下午01:28:01
    * @return String
    * @throws
    */
    @Override
    @RequestMapping("/doAdd.action")
    public ModelAndView doAdd()
    {
        return new ModelAndView("/WEB-INF/views/template/add_template.jsp");
    }
    
    /**
    * @描述：导出模板
    * @作者：袁永君
    * @时间：2011-3-3 下午01:27:03
    * @return
    */
    @ResponseBody
    @RequestMapping("/dataExport.action")
    public void doDataExport(HttpServletRequest request, HttpServletResponse response)
    {
        int catalogId = RequestHelper.getInt(request, "catalogId", 1);
        int[] idArray = getIntArrayParameter("id");
        
        /**
        * 上传文件保存的地址，从配置文件中取，可以将附件生成在工程外
        * 袁永君 add by 20131015
        */
        String fileSaveAddr = Configuration.getString("system.fileSaveAddr");
        
        String directory = "";
        String savePath = "";
        if (StringHelper.isNotEmpty(fileSaveAddr))
        {
            directory = fileSaveAddr + "/temp/expxml/template/";
        }
        else
        {
            directory = Application.getRootPath() + "/temp/expxml/template/";
        }
        
        if (StringHelper.isNotEmpty(fileSaveAddr))
        {
            savePath = fileSaveAddr + "/temp/expxml/zip/" + catalogId + ".zip";
        }
        else
        {
            savePath = Application.getRootPath() + "/temp/expxml/zip/" + catalogId + ".zip";
        }
        if (new File(directory).exists())
        {
            FileHelper.deleteDirectory(directory);
        }
        
        TemplateXmlManage templateXmlManage = new TemplateXmlManage(directory);
        boolean result = false;
        if (idArray != null && idArray.length > 0)
        {
            result = templateXmlManage.handleXml(idArray);
        }
        else
        {
            result = templateXmlManage.handleXml(catalogId);
        }
        
        if (result)
        {
            if (!new File(savePath).exists())
            {
                FileHelper.createNewFile(savePath);
            }
            ZipHelper.compress(directory, savePath);
            
            //下载
            OutputStream out = null;
            BufferedInputStream in = null;
            try
            {
                response.setContentType("application/zip");
                response.setHeader("Content-Disposition", "attachment; filename=\"" + catalogId + ".zip\"");
                
                out = response.getOutputStream();
                in = new BufferedInputStream(new FileInputStream(savePath));
                int line;
                while ((line = in.read()) != -1)
                {
                    out.write(line);
                    
                }
                out.close();
                in.close();
            }
            catch (Exception ex)
            {
                logger.error("", ex);
            }
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
        return new ModelAndView("/WEB-INF/views/template/default.jsp");
    }
    
    /**
    * 作者:	 袁永君
    * 创建日期: 2015-11-18
    * 创建时间: 下午04:14:59
    * @return String
    * @throws
    */
    @ResponseBody
    @RequestMapping("/doDelete.action")
    public Result doDelete(HttpServletResponse reponse)
    {
        int[] idArray = getIntArrayParameter("id");
        for (int i = 0; i < idArray.length; i++)
        {
            templateService.deleteTemplate(idArray[i]);
            addLog("删除模板", "删除模板[id=" + idArray[i] + "]");
        }
        result.setErrorNo(0);
        result.setErrorInfo("删除模板 成功！");
        return result;
    }
    
    /**
     * 描述: 修改模板
     * 作者: 袁永君
     * 创建日期: 2015-12-8
     * 创建时间: 下午9:21:23
     * @param response
     * @return
     */
    @RequestMapping("/doEdit.action")
    public ModelAndView doEdit(HttpServletRequest request, HttpServletResponse response)
    {
        int id = getIntParameter("id");
        Template template = templateService.findTemplateById(id, getSiteNo());
        if (template != null)
        {
            DynaForm form = normalize(request);
            BeanHelper.beanToMap(template, form);
            ModelAndView mv = new ModelAndView("/WEB-INF/views/template/edit_template.jsp");
            mv.addObject("form", form);
            return mv;
        }
        else
        {
            ScriptHelper.alert(response, "模板不存在或已经被删除！", "close");
            return null;
        }
    }
    
    /**
     * 描述: 编辑模板内容
     * 作者: 袁永君
     * 创建日期: 2015-12-8
     * 创建时间: 下午9:21:48
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/doEditContent.action")
    public ModelAndView doEditContent(HttpServletRequest request, HttpServletResponse response)
    {
        int id = getIntParameter("id");
        Template template = templateService.findTemplateById(id, getSiteNo());
        if (template != null)
        {
            DynaForm form = normalize(request);
            BeanHelper.beanToMap(template, form);
            ModelAndView mv = new ModelAndView("/WEB-INF/views/template/edit_template_content.jsp");
            mv.addObject("form", form);
            return mv;
        }
        else
        {
            ScriptHelper.alert(response, "模板不存在或已经被删除！", "close");
            return null;
        }
    }
    
    /**
    * 描述:  更新状态  
    * 作者:	 袁永君
    * 创建日期: 2015-11-19
    * 创建时间: 下午02:52:12
    * @return String
    * @throws
    */
    @Override
    @ResponseBody
    @RequestMapping("/editState.action")
    public Result doEditState(HttpServletRequest request, HttpServletResponse reponse)
    {
        int[] idArray = getIntArrayParameter("id");
        int state = getIntParameter("state");
        
        Template template = null;
        for (int i = 0; i < idArray.length; i++)
        {
            template = new Template();
            template.setId(idArray[i]);
            template.setState(state);
            templateService.editTemplate(template);
            addLog("更新模板状态", "更新模板状态[id=" + idArray[i] + "]");
        }
        result.setErrorNo(0);
        result.setErrorInfo("更新模板状态成功！");
        return result;
    }
    
    /**
    * 
    * @描述：导出模板
    * @作者：袁永君
    * @时间：2011-1-13 下午04:23:09
    * @return
    * @throws IOException
    */
    @ResponseBody
    @RequestMapping("/doExportHtml.action")
    public void doExportHtml(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        int id = RequestHelper.getInt(request, "form.id");
        if (id > 0)
        {
            Template template = templateService.findTemplateById(id, getSiteNo());
            
            if (template != null)
            {
                String name = template.getName();
                String content = template.getContent();
                
                name = URLEncoder.encode(name, "UTF-8");
                response.addHeader("Content-Disposition", "attachment; filename=" + name + ".html");
                response.setContentType("application/html");
                //response.setCharacterEncoding("UTF-8");
                
                response.getWriter().write(content.toString());
                response.flushBuffer();
            }
        }
    }
    
    /**
    * 
    * @描述：导入模板
    * @作者：袁永君
    * @时间：2011-3-3 下午02:41:04
    * @return
    */
    @RequestMapping("/doImportTemplate.action")
    public ModelAndView doImportTemplate()
    {
        return new ModelAndView("/WEB-INF/views/template/uploadTemplate.jsp");
    }
    
    /**
    * 显示左侧的页面
    * @return
    */
    @RequestMapping("/doLeft.action")
    public ModelAndView doLeft()
    {
        return new ModelAndView("/WEB-INF/views/template/left.jsp");
    }
    
    @RequestMapping("/doList.action")
    public ModelAndView doList()
    {
        int catalogId = getIntParameter("catalogId");
        
        String keyword = getStrParameter("keyword");
        int templateState = getIntParameter("templateState", -1);
        String startDate = getStrParameter("startDate");
        String endDate = getStrParameter("endDate");
        
        int curPage = this.getIntParameter("page");
        curPage = (curPage <= 0) ? 1 : curPage;
        
        DBPage page = templateService.findTemplate(curPage, SysConfig.getRowOfPage(), catalogId, keyword, templateState, startDate, endDate, getSiteNo());
        dataMap.put("page", page);
        ModelAndView mv = new ModelAndView("/WEB-INF/views/template/list_template.jsp");
        mv.addObject("data", dataMap);
        mv.addObject("catalogId", "catalogId");
        mv.addObject("keyword", "keyword");
        mv.addObject("templateState", templateState);
        mv.addObject("startDate", "startDate");
        mv.addObject("endDate", "endDate");
        return mv;
    }
    
    /**
     * 描述: 预览模板
     * 作者: 袁永君
     * 创建日期: 2015-12-8
     * 创建时间: 下午9:19:41
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/doPreview.action", produces = "text/html;charset=UTF-8;")
    public void doPreview(HttpServletResponse response)
    {
        try
        {
            int templateId = getIntParameter("id");
            
            TemplatePreview templatePreview = new TemplatePreview(templateId);
            String content = templateService.parseTemplate(templatePreview);
            
            String encoding = Configuration.getString("system.encoding", "UTF-8");
            response.setContentType("text/html;charset=" + encoding);
            
            response.getWriter().print(content);
        }
        catch (Exception ex)
        {
            logger.error("", ex);
        }
    }
    
    /**
    * 发布模板
    * @return
    */
    @RequestMapping("/doPublish.action")
    public void doPublish(HttpServletResponse response)
    {
        int templateId = getIntParameter("id");
        
        //把要发布的模板添加入发布队列        
        templateService.addToPublishQueue(templateId, "T", getLoginSiteNo(), getUID());
        
        ScriptHelper.alert(response, "模板[templateId=" + templateId + "]发布已经添加到发布队列中");
    }
    
    /**
    * 得到子目录的xml文档
    * @return
    */
    @ResponseBody
    @RequestMapping(value = "/doShowChild.action", produces = "text/xml;charset=GBK;")
    public void doShowChild(HttpServletRequest request, HttpServletResponse response)
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
            
            String url = "doList.action?menuNo=" + menuNo + "&amp;catalogId=" + rootCatalog.getId();
            buffer.append("   <tree text=\"" + rootCatalog.getName() + "\" target=\"catalogRightFrame\" action=\"" + url
                    + "\" src=\"doShowChild.action?root&amp;menuNo=" + menuNo + "&amp;catalogId=" + rootCatalog.getId() + "\"  value=\"" + rootCatalog.getId()
                    + "\" oncontextmenu=\"true\" />\n");
        }
        else
        {
            //查询子栏目
            //List childrenList = catalogService.findChildrenById(catalogId);
            List<Catalog> childrenList = catalogService.findChildrenById(catalogId, siteno);
            
            for (Iterator<Catalog> iter = childrenList.iterator(); iter.hasNext();)
            {
                Catalog catalog = iter.next();
                String name = catalog.getName();
                name = StringHelper.replace(name, "\"", "&quot;");
                name = StringHelper.replace(name, "\'", "&quot;");
                
                String url = "doList.action?menuNo=" + menuNo + "&amp;catalogId=" + catalog.getId();
                
                String src = "";
                if (catalog.getChildrennum() > 0)
                {
                    src = "doShowChild.action?menuNo=" + menuNo + "&amp;catalogId=" + catalog.getId();
                }
                
                buffer.append("   <tree text=\"" + name + "\" target=\"catalogRightFrame\" action=\"" + url + "\" src=\"" + src + "\" value=\""
                        + catalog.getId() + "\" oncontextmenu=\"true\" />\n");
            }
        }
        
        buffer.append("</tree>");
        ResponseHelper.print(response, buffer.toString());
    }
    
    /**
    * @描述：
    * @作者：袁永君
    * @时间：2011-1-13 下午07:21:47
    * @return
    * @throws Exception
    */
    @RequestMapping("/doUploadHtml.action")
    public void doUploadHtml(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        if (isPostBack())
        {
            String id = RequestHelper.getString(request, "form.id");//模板ID
            String uploadPath = RequestHelper.getString(request, "uploadHtmlPath");
            /**
            * 上传文件保存的地址，从配置文件中取，可以将附件生成在工程外
            * 袁永君 add by 20131015
            */
            String fileSaveAddr = Configuration.getString("system.fileSaveAddr");
            if (StringHelper.isNotEmpty(fileSaveAddr))
            {
                uploadPath = fileSaveAddr + uploadPath;
            }
            else
            {
                uploadPath = Application.getRootPath() + uploadPath;
            }
            uploadPath = FileHelper.normalize(uploadPath);
            
            if (StringHelper.isEmpty(id) || uploadPath.indexOf(".html") <= 0)
            {
                FileHelper.deleteFile(uploadPath);
                return;
            }
            
            String content = FileHelper.readFileToString(uploadPath);
            FileHelper.deleteFile(uploadPath);
            response.getWriter().write(content);
            
        }
        return;
    }
    
    @Override
    @ResponseBody
    @RequestMapping("/edit.action")
    public Result edit(HttpServletRequest request, HttpServletResponse response)
    {
        DynaForm form = normalize(request);
        Template template = new Template();
        BeanHelper.mapToBean(form, template);
        
        template.setModifiedBy(getUID());
        template.setModifiedDate(DateHelper.formatDate(new Date()));
        templateService.editTemplate(template);
        
        addLog("编辑模板", "编辑模板[id=" + template.getId() + "]");
        result.setErrorNo(0);
        result.setErrorInfo("编辑模板成功[" + template.getName() + "]！");
        return result;
    }
    
    /**
     * 描述: 上传模板
     * 作者: 袁永君
     * 创建日期: 2015-12-7
     * 创建时间: 下午9:33:28
     * @return
     */
    @ResponseBody
    @RequestMapping("/importTemplate.action")
    public void importTemplate(HttpServletRequest request, HttpServletResponse response, Model model)
    {
        //上传的zip文件
        String uploadPath = RequestHelper.getString(request, "uploadPath");//zip文件上传路径
        /**
        * 上传文件保存的地址，从配置文件中取，可以将附件生成在工程外
        * 袁永君 add by 20131015
        */
        String fileSaveAddr = Configuration.getString("system.fileSaveAddr");
        if (StringHelper.isNotEmpty(fileSaveAddr))
        {
            uploadPath = fileSaveAddr + uploadPath;
        }
        else
        {
            uploadPath = Application.getRootPath() + uploadPath;
        }
        uploadPath = FileHelper.normalize(uploadPath);
        try
        {
            if (!new File(uploadPath).exists())
            {
                throw new Exception("上传的zip文件不存在！");
            }
            
            if (!FileHelper.getExtension(uploadPath).equalsIgnoreCase("ZIP"))
            {
                throw new Exception("数据包必须是zip格式！");
            }
            
            //解压的临时地址
            String tempPath = "";
            /**
            * 上传文件保存的地址，从配置文件中取，可以将附件生成在工程外
            * 袁永君 add by 20131015
            */
            if (StringHelper.isNotEmpty(fileSaveAddr))
            {
                tempPath = fileSaveAddr + "/temp/expxml/temp/";
            }
            else
            {
                tempPath = Application.getRootPath() + "/temp/expxml/temp/";
            }
            tempPath = FileHelper.normalize(tempPath);
            if (new File(tempPath).exists())
            {
                FileHelper.deleteDirectory(tempPath);
            }
            
            //将zip解压
            ZipHelper.decompress(uploadPath, tempPath);
            
            TemplateImpManage impManage = new TemplateImpManage(tempPath);
            impManage.impTemplate();
            
        }
        catch (Exception ex)
        {
            logger.error("", ex);
            //addActionError(ThrowableHelper.toString(ex));
            //return "uploadImplate";
            setAttribute("throwMessage", ThrowableHelper.toString(ex));
            dataMap.put("message", ThrowableHelper.toString(ex));
            model.addAttribute("actionErrors", dataMap);
            model.addAttribute("error", dataMap);
            ScriptHelper.redirect(response, MESSAGE_JSP);
        }
        finally
        {
            //删除上传的zip文件
            if (new File(uploadPath).exists() && FileHelper.getExtension(uploadPath).equalsIgnoreCase("ZIP"))
            {
                FileHelper.deleteFile(uploadPath);
            }
        }
        ScriptHelper.alert(response, "上传模板成功", "close");
    }
}
