package com.yizhishang.plat.web.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yizhishang.base.config.SysConfig;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.util.RequestHelper;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.domain.EnumItem;
import com.yizhishang.plat.domain.Result;
import com.yizhishang.plat.service.EnumService;
import com.yizhishang.plat.service.EnumTypeService;

/**
 * 描述: 数据字典管理
 * 版权: Copyright (c) 2015
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2015-10-20
 * 创建时间: 下午1:12:42
 */
@Controller
@RequestMapping("/admin/enumAdmin")
public class EnumAction extends BaseAction
{
    
    @Resource
    EnumService enumService;
    
    @Resource
    EnumTypeService enumTypeService;
    
    /**
    * 描述: addItem添加具体枚举值
    * 作者: 袁永君
    * 创建日期: 2015-10-20
    * 创建时间: 下午5:32:26
    * @return
    */
    @RequestMapping("/addItem.action")
    public @ResponseBody
    Result addItem()
    {
        
        DynaModel data = new DynaModel();
        Result result = new Result();
        
        data.set("is_system", "0");
        data.set("siteno", getSiteNo());
        
        //类型名称
        String enum_type = getStrParameter("enum_type");
        //名称
        String item_name = getStrParameter("form.item_name");
        //具体值
        String item_value = getStrParameter("form.item_value");
        
        try
        {
            enum_type = URLDecoder.decode(enum_type, "utf-8");
            item_name = URLDecoder.decode(item_name, "utf-8");
            item_value = URLDecoder.decode(item_value, "utf-8");
            data.set("enum_type", enum_type);
            data.set("item_name", item_name);
            data.set("item_value", item_value);
            if (StringHelper.isEmpty(enum_type) || StringHelper.isEmpty(item_name) || StringHelper.isEmpty(item_value))
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            MESSAGE = "添加失败";
            addLog("添加数据字典类别目录", MESSAGE);
            result.setErrorNo(-1);
            result.setErrorInfo(MESSAGE);
            return result;
        }
        
        boolean b = enumService.isExitDictItem(enum_type, item_name);
        if (b)
        {
            MESSAGE = "添加数据字典类别目录失败!";
            result.setErrorNo(-1);
        }
        else
        {
            enumService.addItem(data);
            MESSAGE = "添加成功";
            addLog("添加数据字典类别目录!", MESSAGE);
        }
        result.setErrorInfo(MESSAGE);
        return result;
        
    }
    
    @RequestMapping("/addType.action")
    public @ResponseBody
    Result addType()
    {
        DynaModel data = new DynaModel();
        
        String siteno = getSiteNo();
        data.set("siteno", siteno);
        
        //类型名称
        String enum_name = getStrParameter("form.enum_name");
        //类型值
        String enum_value = getStrParameter("form.enum_value");
        
        try
        {
            enum_name = URLDecoder.decode(enum_name, "utf-8");
            enum_value = URLDecoder.decode(enum_value, "utf-8");
            data.set("enum_name", enum_name);
            data.set("enum_value", enum_value);
        }
        catch (UnsupportedEncodingException e)
        {
            MESSAGE = "添加失败";
            addLog("添加数据字典类别目录", MESSAGE);
            result.setErrorNo(-1);
            result.setErrorInfo(MESSAGE);
            return result;
        }
        
        boolean b = enumTypeService.isExitDictItem(enum_name, enum_value, siteno);
        if (b)
        {
            MESSAGE = "数据字典中已经存在相同的数据项！";
            result.setErrorNo(-1);
        }
        else
        {
            enumTypeService.addItem(data);
            MESSAGE = "添加成功";
            addLog("添加数组字典类型", MESSAGE);
        }
        result.setErrorInfo(MESSAGE);
        return result;
        
    }
    
    @RequestMapping("/doAddItem.action")
    public String doAddItem(Model model)
    {
        String type = getStrParameter("type");
        model.addAttribute("enum_type", type);
        return "/WEB-INF/views/enum/add_enum_item.jsp";
    }
    
    @RequestMapping("/doAddType.action")
    public String doAddType()
    {
        return "/WEB-INF/views/enum/add_enum_type.jsp";
    }
    
    @Override
    @RequestMapping("/doDefault.action")
    public ModelAndView doDefault()
    {
        mv.setViewName("/WEB-INF/views/enum/default.jsp");
        return mv;
    }
    
    @SuppressWarnings("finally")
    @RequestMapping("/delete.action")
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public @ResponseBody
    Result doDelete() throws Exception
    {
        try
        {
            int[] idArray = getIntArrayParameter("id");
            for (int i = 0; i < idArray.length; i++)
            {
                enumService.delItem(idArray[i]);
                MESSAGE = "删除数据字典";
                addLog(MESSAGE, "删除数据字典[id=" + idArray[i] + "]");
                MESSAGE += "成功";
            }
        }
        catch (Exception e)
        {
            MESSAGE = "删除数据字典失败";
            result.setErrorNo(-1);
            throw new Exception();
        }
        finally
        {
            result.setErrorInfo(MESSAGE);
            return result;
        }
    }
    
    /**
    * @描述：
    * @作者：袁永君
    * @时间：2016-3-26 下午08:52:07
    * @return
    */
    @ResponseBody
    @RequestMapping("/deleteType.action")
    public Result doDeleteType()
    {
    	Result result = new Result();
        //String[] idArray = getStrArrayParameter("id");
        String enumValue = RequestHelper.getString(getRequest(), "id");
        
        //删除类别下的所有数据字典
        enumService.delItemByType(enumValue, getSiteNo());
        addLog("删除数据字典类型", "删除[type=" + enumValue + "]下的所有数据字典");
        
        enumTypeService.delItemByVal(enumValue, getSiteNo());
        addLog("删除数据字典类型", "删除数据字典类型[id=" + enumValue + "]");
        
        result.setErrorInfo("操作成功");
        return result;
    }
    
    @RequestMapping("/doEditItem.action")
    public String doEditItem(Model model)
    {
        String id = getStrParameter("id");
        EnumItem data = enumService.findItemByCode(id);
        if (data != null)
        {
            model.addAttribute("form", data);
        }
        return "/WEB-INF/views/enum/edit_enum_item.jsp";
    }
    
    public String doEditType()
    {
        if (isPostBack())
        {
            normalize(form);
            DynaModel data = new DynaModel();
            data.putAll(form);
            
            enumTypeService.updateItem(data);
            return MESSAGE;
        }
        else
        {
            String id = getStrParameter("id");
            DynaModel data = enumTypeService.findItemByVal(id);
            if (data != null)
                form.putAll(data);
            return "edit_type";
        }
    }
    
    /**
    * 显示左侧的页面
    * @return
    */
    @RequestMapping("/doLeft.action")
    public String doLeft()
    {
        return "/WEB-INF/views/enum/left.jsp";
    }
    
    @RequestMapping("/list.action")
    public String doList(Model model)
    {
        int curPage = this.getIntParameter("page");
        String type = getStrParameter("type");
        curPage = (curPage <= 0) ? 1 : curPage;
        type = StringHelper.trim(type);
        
        //String siteno = getSiteNo();
        String siteno = "";
        DBPage<EnumItem> page = enumService.getEnumItemByType(curPage, SysConfig.getRowOfPage(), type, siteno);
        dataMap.put("page", page);
        
        List<DynaModel> typeList = enumService.getEnumTypeList(siteno);
        dataMap.put("typeList", typeList);
        model.addAttribute("data", dataMap);
        return "/WEB-INF/views/enum/list_enum_item.jsp";
    }
    
    @RequestMapping(value = "/showTree.action", produces = "text/xml;charset=GBK")
    public @ResponseBody
    String doShowTree(HttpServletResponse response)
    {
        StringBuffer buffer = new StringBuffer();
        
        response.setContentType("text/xml;charset=GBK");
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setDateHeader("expires", -10);
        
        buffer.append("<?xml version=\"1.0\" encoding=\"GBK\"?>\n");
        buffer.append("<tree>\n");
        
        //String siteno = getLoginSiteNo();
        String siteno = "";
        List<DynaModel> enumList = enumService.getEnumTypeList(siteno);
        for (Iterator<DynaModel> iter = enumList.iterator(); iter.hasNext();)
        {
            DynaModel dataRow = (DynaModel) iter.next();
            buffer.append("   <tree text=\"" + dataRow.getString("enum_name") + "\" target=\"roleRightFrame\" action=\"list.action?type="
                    + dataRow.getString("enum_value") + "\" value=\"" + dataRow.getString("enum_value") + "\" src=\"\"  oncontextmenu=\"true\" />\n");
        }
        
        buffer.append("</tree>");
        return buffer.toString();
    }
    
    @RequestMapping("/editItem.action")
    public @ResponseBody
    Result editItem()
    {
        DynaModel data = new DynaModel();
        Result result = new Result();
        
        //类型名称
        String enum_type = getStrParameter("form.enum_type");
        //名称
        String item_name = getStrParameter("form.item_name");
        //具体值
        String item_value = getStrParameter("form.item_value");
        
        String item_code = getStrParameter("form.item_code");
        String orderline = getStrParameter("form.orderline");
        int is_system = getIntParameter("form.is_system");
        String siteno = getStrParameter("form.siteno");
        try
        {
            enum_type = URLDecoder.decode(enum_type, "utf-8");
            item_name = URLDecoder.decode(item_name, "utf-8");
            item_value = URLDecoder.decode(item_value, "utf-8");
            item_code = URLDecoder.decode(item_code, "utf-8");
            orderline = URLDecoder.decode(orderline, "utf-8");
            siteno = URLDecoder.decode(siteno, "utf-8");
            data.set("enum_type", enum_type);
            data.set("item_name", item_name);
            data.set("item_value", item_value);
            data.set("item_code", item_code);
            data.set("orderline", orderline);
            data.set("is_system", is_system);
            data.set("siteno", siteno);
            if (data.size() == 0)
            {
                throw new Exception();
            }
        }
        catch (Exception e)
        {
            MESSAGE = "编辑失败";
            addLog("编辑数据字典枚举值", MESSAGE);
            result.setErrorNo(-1);
            result.setErrorInfo(MESSAGE);
            return result;
        }
        try
        {
            enumService.updateItem(data);
            MESSAGE = "编辑成功";
            addLog("编辑数据字典枚举值!", MESSAGE);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            MESSAGE = "编辑失败";
            addLog("编辑数据字典枚举值", MESSAGE);
            result.setErrorNo(-1);
            result.setErrorInfo(e.getMessage());
            return result;
        }
        result.setErrorInfo(MESSAGE);
        return result;
        
    }
}
