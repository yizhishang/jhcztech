package com.yizhishang.common.table.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.jdbc.DBPage;
import com.yizhishang.base.util.DateHelper;
import com.yizhishang.base.util.ExcelHelper;
import com.yizhishang.base.util.RequestHelper;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.base.util.ToolKit;
import com.yizhishang.common.table.consts.Consts;
import com.yizhishang.common.table.service.CommonService;
import com.yizhishang.common.table.service.TableColumnService;
import com.yizhishang.plat.domain.Result;
import com.yizhishang.plat.web.action.BaseAction;

@Controller
@RequestMapping("/admin/commonAdmin")
public class CommonAction extends BaseAction
{
    
    @Resource
    CommonService commonService;
    
    private final HashMap<String, Object> dataMap = new HashMap<String, Object>();
    
    private final String FORWARD_ADD_PAGE = "forward_add_page";
    
    private final String FORWARD_EDIT_PAGE = "forward_edit_page";
    
    private final String FORWARD_VIEW_PAGE = "forward_view_page";
    
    private final String FORWARD_LIST_PAGE = "forward_list_page";
    
    private final String FORWARD_IMPORTEXCEL_PAGE = "forward_import_excel_page";
    
    private final String FORWARD_IMPORTEXCEL_CONFIG_PAGE = "forward_import_excel_config_page";
    
    private final String FORWARD_EXPORTEXCEL_PAGE = "forward_export_excel_page";
    
    private final String FORWARD_EDIT_ACTION = "forward_edit_action";
    
    private final String FORWARD_LIST_ACTION = "forward_list_action";
    
    protected static boolean needPostBack = true;
    
    /**
     * 判断权限
     * @param function
     */
    protected boolean checkFunctionAva(String function)
    {
        // 此处留作action的权限判断用
        return true;
    }
    
    /**
     * 跳转到权限错误页面
     * if(checkFunctionAva("xxx"){return returnNotAva();}
     */
    public String returnNotAva()
    {
        return "";
    }
    
    /**
     * 获取action操作的表名称(小写)
     */
    public String getTablenameLowerCase()
    {
        String table_name = getTablename().toLowerCase();
        return table_name;
    }
    
    /**
     * 获取需要随id生成的排序字段(小写)
     */
    public String getOrderNoByIdLowerCase()
    {
        String orderno = getOrderNoById().toLowerCase();
        return orderno;
    }
    
    /**
     * 获取排序字段(小写)
     */
    public String getOrderByLowerCase()
    {
        String orderby = getOrderBy().toLowerCase();
        return orderby;
    }
    
    /**
     * 获取营业部id对应的字段
     */
    public String getBranchIdColNameByLowerCase()
    {
        String orderby = getBranchIdColName().toLowerCase();
        return orderby;
    }
    
    /**
     * 获取action操作的表名称
     */
    public String getTablename()
    {
        return "";
    }
    
    /**
     * 获取需要随id生成的排序字段
     */
    public String getOrderNoById()
    {
        return "";
    }
    
    /**
     * 获取需要随id生成的排序字段
     */
    public String getBranchIdColName()
    {
        return "";
    }
    
    /**
     * 获取排序字段
     */
    public String getOrderBy()
    {
        return "";
    }
    
    /**
     * 获取排序形式(正序倒序)
     * 默认正序
     */
    public String getOrderBySort()
    {
        return "asc";
    }
    
    public String getFORWARD_ADD_PAGE()
    {
        return FORWARD_ADD_PAGE;
    }
    
    public String getFORWARD_EDIT_PAGE()
    {
        return FORWARD_EDIT_PAGE;
    }
    
    public String getFORWARD_VIEW_PAGE()
    {
        return FORWARD_VIEW_PAGE;
    }
    
    public String getFORWARD_LIST_PAGE()
    {
        return FORWARD_LIST_PAGE;
    }
    
    public String getFORWARD_IMPORTEXCEL_PAGE()
    {
        return FORWARD_IMPORTEXCEL_PAGE;
    }
    
    public String getFORWARD_IMPORTEXCEL_CONFIG_PAGE()
    {
        return FORWARD_IMPORTEXCEL_CONFIG_PAGE;
    }
    
    public String getFORWARD_EXPORTEXCEL_PAGE()
    {
        return FORWARD_EXPORTEXCEL_PAGE;
    }
    
    public String getFORWARD_EDIT_ACTION()
    {
        return FORWARD_EDIT_ACTION;
    }
    
    public String getFORWARD_LIST_ACTION()
    {
        return FORWARD_LIST_ACTION;
    }
    
    @SuppressWarnings("rawtypes")
    protected List<DynaModel> getParams()
    {
        Enumeration ema = getRequest().getParameterNames();
        List<DynaModel> plist = new ArrayList<DynaModel>();
        while (ema.hasMoreElements())
        {
            String pname = (String) ema.nextElement();
            if (pname.startsWith("search_name_") || pname.startsWith("eq_search_name_"))
            {
                String pvalue = RequestHelper.getString(getRequest(), pname, "");
                String type = "like";
                if (pname.startsWith("eq_"))
                {
                    type = "eq";
                }
                DynaModel item = new DynaModel();
                if (pname.startsWith("search_name_"))
                {
                    pname = pname.replaceFirst("search_name_", "");
                }
                else if (pname.startsWith("eq_search_name_"))
                {
                    pname = pname.replaceFirst("eq_search_name_", "");
                }
                if (pname.endsWith("_begin"))
                {
                    type = ">=";
                    pname = pname.substring(0, pname.length() - "_begin".length());
                }
                else if (pname.endsWith("_end"))
                {
                    type = "<=";
                    pname = pname.substring(0, pname.length() - "_end".length());
                }
                item.set("pname", pname);
                item.set("pvalue", pvalue);
                item.set("type", type);
                plist.add(item);
            }
        }
        return plist;
    }
    
    private List<DynaModel> getExportParam(String paramstr)
    {
        List<DynaModel> plist = new ArrayList<DynaModel>();
        if (!StringHelper.isEmpty(paramstr))
        {
            String[] items = paramstr.split("\\&");
            for (int i = 0; i < items.length; i++)
            {
                String item = items[i];
                String[] single = item.split("\\=");
                if (single.length > 1)
                {
                    String pname = single[0];
                    if (pname.startsWith("search_name_") || pname.startsWith("eq_search_name_"))
                    {
                        String pvalue = single[1];
                        String type = "like";
                        if (pname.startsWith("eq_"))
                        {
                            type = "eq";
                        }
                        DynaModel dr = new DynaModel();
                        if (pname.startsWith("search_name_"))
                        {
                            pname = pname.replaceFirst("search_name_", "");
                        }
                        else if (pname.startsWith("eq_search_name_"))
                        {
                            pname = pname.replaceFirst("eq_search_name_", "");
                        }
                        if (pname.endsWith("_begin"))
                        {
                            type = ">=";
                            pname = pname.substring(0, pname.length() - "_begin".length());
                        }
                        else if (pname.endsWith("_end"))
                        {
                            type = "<=";
                            pname = pname.substring(0, pname.length() - "_end".length());
                        }
                        dr.set("pname", pname);
                        dr.set("pvalue", pvalue);
                        dr.set("type", type);
                        plist.add(dr);
                    }
                }
            }
        }
        return plist;
    }
    
    protected List<DynaModel> addBranchParam(List<DynaModel> param)
    {
        String branchid = getBranchNo();
        String branchcol = getBranchIdColNameByLowerCase();
        if (!StringHelper.isEmpty(branchcol) && !StringHelper.isEmpty(branchid) && !(isAdministratorsRole() || isSystemAdmin()))
        {
            DynaModel item = new DynaModel();
            item.set("pname", branchcol);
            item.set("pvalue", branchid);
            item.set("type", "eq");
            param.add(item);
        }
        return param;
    }
    
    protected void beforeDefault()
    {
    }
    
    protected void afterDefault()
    {
    }
    
    @Override
    @RequestMapping("/doDefault.action")
    public ModelAndView doDefault()
    {
        beforeDefault();
        // 获取配置信息
        String table_name = getTablenameLowerCase();
        String orderby = getOrderByLowerCase();
        String orderbysort = getOrderBySort();
        CommonService service = new CommonService();
        List<DynaModel> cols = service.getListColumns(table_name);
        List<DynaModel> searchcols = service.getSearchColumns(table_name);
        // 获取配置的table的信息
        DynaModel tableInfo = service.getTableInfo(table_name);
        // 获取数据
        int curPage = this.getIntParameter("page");
        curPage = (curPage <= 0) ? 1 : curPage;
        List<DynaModel> params = getParams();
        params = addBranchParam(params);
        DBPage<DynaModel> page = service.getPageData(table_name, orderby, orderbysort, curPage, 20, params);
        dataMap.put("page", page);
        dataMap.put("cols", cols);
        dataMap.put("searchcols", searchcols);
        dataMap.put("tableinfo", tableInfo);
        String uid = getUID();
        dataMap.put("uid", uid);
        afterDefault();
        mv.setViewName("/WEB-INF/views/common/list.jsp");
        return mv;
    }
    
    protected void beforeAdd()
    {
    }
    
    protected void afterAdd()
    {
    }
    
    @Override
    @ResponseBody
    @RequestMapping("/add.action")
    public Result add(HttpServletRequest request, HttpServletResponse response)
    {
        beforeAdd();
        String table_name = getTablenameLowerCase();
        String orderno = getOrderNoByIdLowerCase();
        //        String forwardStr = "";
        CommonService service = new CommonService();
        // 获取配置的table的信息
        DynaModel tableInfo = service.getTableInfo(table_name);
        String pkcol = tableInfo.getString("pk_column").toLowerCase();
        
        DynaModel bean = ToolKit.getCmsFormParams(getRequest());
        if (!StringHelper.isEmpty(orderno) && !StringHelper.isEmpty(bean.getString(orderno)))
        {
            orderno = "";
        }
        // 对系统取值字段设值
        List<DynaModel> syscols = service.getSysColumns(table_name);
        for (int i = 0; syscols != null && i < syscols.size(); i++)
        {
            DynaModel syscol = (DynaModel) syscols.get(i);
            if (Consts.sys_col_time.equals(syscol.getString("sys_type")) || Consts.sys_col_time_once.equals(syscol.getString("sys_type")))
            {
                bean.set(syscol.getString("name_en"), DateHelper.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
            }
            else if (Consts.sys_col_userid.equals(syscol.getString("sys_type")) || Consts.sys_col_userid_once.equals(syscol.getString("sys_type")))
            {
                bean.set(syscol.getString("name_en"), getUID());
            }
        }
        service.add(table_name, pkcol, orderno, bean);
        addLog("增加" + table_name, "保存成功");
        return super.add(request, response);
    }
    
    @Override
    @RequestMapping("/doAdd.action")
    public ModelAndView doAdd()
    {
        beforeAdd();
        String table_name = getTablenameLowerCase();
        //        String orderno = getOrderNoByIdLowerCase();
        // 获取配置的table的信息
        DynaModel tableInfo = commonService.getTableInfo(table_name);
        //        String pkcol = tableInfo.getString("pk_column").toLowerCase();
        List<DynaModel> cols = commonService.getInfoColumns(table_name);
        dataMap.put("cols", cols);
        dataMap.put("tableinfo", tableInfo);
        String uid = getUID();
        dataMap.put("uid", uid);
        afterAdd();
        
        mv.addObject("data", dataMap);
        mv.setViewName("/WEB-INF/views/common/add.jsp");
        return mv;
    }
    
    protected void beforeEdit()
    {
    }
    
    protected void afterEdit()
    {
    }
    
    @Override
    @ResponseBody
    @RequestMapping("/edit.action")
    public Result edit(HttpServletRequest request, HttpServletResponse response)
    {
        beforeEdit();
        String table_name = getTablenameLowerCase();
        // 获取配置的table的信息
        DynaModel tableInfo = commonService.getTableInfo(table_name);
        String pkcol = tableInfo.getString("pk_column").toLowerCase();
        //        String pkvalue = RequestHelper.getString(getRequest(), "pkcol", "0");
        //        String forwardStr = "";
        DynaModel bean = ToolKit.getCmsFormParams(getRequest());
        List<DynaModel> infocols = commonService.getInfoColumns(table_name);
        for (int i = 0; infocols != null && i < infocols.size(); i++)
        {
            DynaModel infocol = (DynaModel) infocols.get(i);
            if (!bean.containsKey(infocol.getString("name_en")))
            {
                bean.set(infocol.getString("name_en"), "");
            }
        }
        // 对系统取值字段设值
        List<DynaModel> syscols = commonService.getSysColumns(table_name);
        for (int i = 0; syscols != null && i < syscols.size(); i++)
        {
            DynaModel syscol = (DynaModel) syscols.get(i);
            if (Consts.sys_col_time.equals(syscol.getString("sys_type")))
            {
                bean.set(syscol.getString("name_en"), DateHelper.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
            }
            else if (Consts.sys_col_userid.equals(syscol.getString("sys_type")))
            {
                bean.set(syscol.getString("name_en"), getUID());
            }
        }
        commonService.update(table_name, pkcol, bean);
        addLog("编辑" + table_name, "保存成功");
        
        return super.edit(request, response);
    }
    
    @RequestMapping("/doEdit.action")
    public ModelAndView doEdit()
    {
        beforeEdit();
        String table_name = getTablenameLowerCase();
        CommonService service = new CommonService();
        // 获取配置的table的信息
        DynaModel tableInfo = service.getTableInfo(table_name);
        String pkcol = tableInfo.getString("pk_column").toLowerCase();
        String pkvalue = RequestHelper.getString(getRequest(), "pkcol", "0");
        List<DynaModel> cols = service.getInfoColumns(table_name);
        DynaModel bean = service.loadBean(table_name, pkcol, pkvalue);
        dataMap.put("cols", cols);
        dataMap.put("pkcol", pkcol);
        dataMap.put("pkvalue", pkvalue);
        dataMap.put("bean", bean);
        dataMap.put("tableinfo", tableInfo);
        String uid = getUID();
        dataMap.put("uid", uid);
        afterEdit();
        
        mv.addObject("data", dataMap);
        mv.setViewName("/WEB-INF/views/common/edit.jsp");
        return mv;
    }
    
    protected void beforeDelete()
    {
    }
    
    protected void afterDelete()
    {
    }
    
    @Override
    @ResponseBody
    @RequestMapping("/delete.action")
    public Result delete(HttpServletRequest request, HttpServletResponse response)
    {
        beforeDelete();
        String table_name = getTablenameLowerCase();
        CommonService service = new CommonService();
        // 获取配置的table的信息
        DynaModel tableInfo = service.getTableInfo(table_name);
        String pkcol = tableInfo.getString("pk_column").toLowerCase();
        String[] idArray = getStrArrayParameter("pkcol");
        for (int i = 0; i < idArray.length; i++)
        {
            service.delete(table_name, pkcol, idArray[i]);
            addLog("删除" + table_name + "记录", "删除信息[" + pkcol + "=" + idArray[i] + "]");
        }
        afterDelete();
        return super.delete(request, response);
    }
    
    protected void beforeImportExcel()
    {
    }
    
    protected void afterImportExcel()
    {
    }
    
    @RequestMapping("/doImportExcel.action")
    public ModelAndView doImportExcel(HttpServletRequest request)
    {
        beforeImportExcel();
        @SuppressWarnings("unused")
        String forwardStr = "";
        if (isPostBack())
        {
            String excel_path = RequestHelper.getString(getRequest(), "excel_path", "");
            try
            {
                String real_path = request.getSession().getServletContext().getRealPath(excel_path);
                DynaModel titles = ToolKit.ftExcelTitle(new File(real_path));
                dataMap.put("excel_titles", titles);
                dataMap.put("excel_path", excel_path);
                needPostBack = false;
                return doImportExcelConfig(request);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                addLog("读取上传的excel失败", e.getMessage());
                forwardStr = getFORWARD_IMPORTEXCEL_PAGE();
            }
        }
        else
        {
            CommonService service = new CommonService();
            String table_name = getTablenameLowerCase();
            DynaModel tableInfo = service.getTableInfo(table_name);
            dataMap.put("tableinfo", tableInfo);
            forwardStr = getFORWARD_IMPORTEXCEL_PAGE();
        }
        afterImportExcel();
        
        mv.addObject("data", dataMap);
        mv.setViewName("/WEB-INF/views/common/importexcel.jsp");
        return mv;
    }
    
    protected void beforeImportExcelConfig()
    {
    }
    
    protected void afterImportExcelConfig()
    {
    }
    
    @ResponseBody
    @RequestMapping("/importExcelConfig.action")
    public Result importExcelConfig(HttpServletRequest request)
    {
        Result result = new Result();
        beforeImportExcelConfig();
        //        String forwardStr = "";
        needPostBack = true;
        String excel_path = RequestHelper.getString(request, "excel_path", "");
        @SuppressWarnings("rawtypes")
        Enumeration ema = request.getParameterNames();
        DynaModel param = new DynaModel();
        List<String> fields = new ArrayList<String>();
        while (ema.hasMoreElements())
        { // 组装配置
            String pname = (String) ema.nextElement();
            if (!StringHelper.isEmpty(pname) && pname.startsWith("title."))
            {
                String colname_request = pname.substring("title.".length());
                String exceltitle = RequestHelper.getString(getRequest(), pname, "");
                String colname = RequestHelper.getString(getRequest(), colname_request, "");
                if (!"".equals(colname))
                {
                    param.set(exceltitle, colname);
                }
            }
        }
        try
        {
            String table_name = getTablenameLowerCase();
            String orderno = getOrderNoByIdLowerCase();
            DynaModel tableInfo = commonService.getTableInfo(table_name);
            String pkcol = tableInfo.getString("pk_column").toLowerCase();
            String real_path = request.getSession().getServletContext().getRealPath(excel_path);
            DynaModel titles = ToolKit.ftExcelTitle(new File(real_path));
            int title_num = 0;
            boolean title_flag = true;
            while (title_flag)
            { // 
                String pkey = titles.getString("" + title_num);
                if (StringHelper.isEmpty(pkey))
                {
                    title_flag = false;
                }
                else
                {
                    String colname = param.getString(pkey);
                    fields.add(colname);
                    title_num++;
                }
            }
            List<DynaModel> list = ToolKit.readExcel(new File(real_path), fields.toArray(), true, null);
            //            List<DynaModel> dbInList = new ArrayList<DynaModel>();
            for (int n = 0; list != null && n < list.size(); n++)
            {
                DynaModel data = (DynaModel) list.get(n);
                DynaModel tempBean = new DynaModel();
                // 对系统取值字段设值
                List<DynaModel> allcols = commonService.getAllColumns(table_name);
                for (int i = 0; allcols != null && i < allcols.size(); i++)
                {
                    DynaModel colbean = (DynaModel) allcols.get(i);
                    String name_en = colbean.getString("name_en");
                    String default_value = colbean.getString("default_value");
                    String is_sys = colbean.getString("is_sys");
                    if ("Y".equals(is_sys))
                    {
                        if (Consts.sys_col_time.equals(colbean.getString("sys_type")) || Consts.sys_col_time_once.equals(colbean.getString("sys_type")))
                        {
                            tempBean.set(colbean.getString("name_en"), DateHelper.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
                        }
                        else if (Consts.sys_col_userid.equals(colbean.getString("sys_type"))
                                || Consts.sys_col_userid_once.equals(colbean.getString("sys_type")))
                        {
                            tempBean.set(colbean.getString("name_en"), getUID());
                        }
                    }
                    else
                    {
                        String import_value = data.getString(name_en);
                        if (StringHelper.isEmpty(import_value))
                        {
                            import_value = default_value;
                        }
                        else
                        {
                            String input_type = colbean.getString("input_type");
                            if (Consts.input_type_open_select_checkbox.equals(input_type) || Consts.input_type_open_select_radio.equals(input_type)
                                    || Consts.input_type_checkbox.equals(input_type) || Consts.input_type_radio.equals(input_type)
                                    || Consts.input_type_select.equals(input_type))
                            { // 配置型
                                TableColumnService tcservice = new TableColumnService();
                                import_value = tcservice.getImportOptionValue(colbean, import_value);
                            }
                        }
                        tempBean.set(name_en, import_value);
                    }
                }
                if (!StringHelper.isEmpty(orderno) && !StringHelper.isEmpty(data.getString(orderno)))
                {
                    orderno = "";
                }
                commonService.add(table_name, pkcol, orderno, tempBean);
            }
        }
        catch (Exception e)
        {
            // 此处缺少将未成功的数据导出的功能
            addLog("读取上传的excel", e.getMessage());
            needPostBack = false;
            
            result.setErrorNo(-1);
            result.setErrorInfo(e.getMessage());
            return result;
        }
        addLog("读取上传的excel", "成功");
        return result;
    }
    
    @SuppressWarnings("rawtypes")
    @RequestMapping("/doImportExcelConfig.action")
    public ModelAndView doImportExcelConfig(HttpServletRequest request)
    {
        beforeImportExcelConfig();
        needPostBack = true;
        String table_name = getTablenameLowerCase();
        List cols = commonService.getAllColumns(table_name);
        
        // 获取配置的table的信息
        DynaModel tableInfo = commonService.getTableInfo(table_name);
        dataMap.put("tableinfo", tableInfo);
        dataMap.put("table_cols", cols);
        
        afterImportExcelConfig();
        
        String forwardStr = getFORWARD_IMPORTEXCEL_CONFIG_PAGE();
        mv.setViewName(forwardStr);
        mv.addObject("data", dataMap);
        return mv;
    }
    
    protected void beforeExportExcel()
    {
    }
    
    protected void afterExportExcel()
    {
    }
    
    public String doExportExcel()
    {
        beforeExportExcel();
        String forwardStr = "";
        CommonService service = new CommonService();
        String table_name = getTablenameLowerCase();
        DynaModel tableInfo = service.getTableInfo(table_name);
        if (isPostBack() && needPostBack)
        {
            needPostBack = true;
            List<DynaModel> list = ftQryList();
            try
            {
                String call_name = tableInfo.getString("name_ch") + "导出.xls";
                List<DynaModel> cols = service.getAllColumns(table_name);
                String[] colnames = new String[cols.size()];
                for (int i = 0; cols != null && i < cols.size(); i++)
                {
                    DynaModel col = (DynaModel) cols.get(i);
                    if (col != null)
                    {
                        String colname = ((DynaModel) cols.get(i)).getString("name_en");
                        colnames[i] = colname;
                    }
                    else
                    {
                        colnames[i] = "";
                    }
                }
                //              String charset = "gb2312";
                //              call_name = URLEncoder.encode(call_name, charset);
                getResponse().setHeader("Content-disposition", "attachment;filename=" + new String(call_name.getBytes("gb2312"), "ISO8859-1"));
                //getResponse().setContentType("application/octet-stream;charset="+charset);    //utf-8格式浏览器才能正确识别
                HSSFWorkbook hfbook = ExcelHelper.createExcel(list, colnames, colnames, null, false);
                OutputStream writer = getResponse().getOutputStream();
                hfbook.write(writer);
                writer.flush();
                return null;
            }
            catch (IOException e)
            {
                needPostBack = false;
                e.printStackTrace();
                addLog("导出excel失败", e.getMessage());
                forwardStr = doExportExcel();
            }
        }
        else
        {
            needPostBack = true;
            dataMap.put("tableinfo", tableInfo);
            forwardStr = getFORWARD_EXPORTEXCEL_PAGE();
        }
        afterExportExcel();
        return forwardStr;
    }
    
    protected List<DynaModel> ftQryList()
    {
        String export_type = RequestHelper.getString(getRequest(), "export_type", "all");
        CommonService service = new CommonService();
        String table_name = getTablenameLowerCase();
        String orderby = getOrderByLowerCase();
        String orderbysort = getOrderBySort();
        List<DynaModel> list = new ArrayList<DynaModel>();
        if ("all".equals(export_type))
        {
            List<DynaModel> params = new ArrayList<DynaModel>();
            params = addBranchParam(params);
            list = service.getListData(table_name, orderby, orderbysort, params);
        }
        else if ("page".equals(export_type))
        {
            String ids = RequestHelper.getString(getRequest(), "pageids", "");
            if (!StringHelper.isEmpty(ids))
            {
                DynaModel tableInfo = service.getTableInfo(table_name);
                List<DynaModel> paramList = new ArrayList<DynaModel>();
                DynaModel item = new DynaModel();
                item.set("pname", tableInfo.getString("pk_column"));
                item.set("pvalue", ids);
                item.set("type", "in");
                list = service.getListData(table_name, orderby, orderbysort, paramList);
            }
        }
        else if ("search".equals(export_type))
        {
            String qryparam = RequestHelper.getString(getRequest(), "qryparam", "");
            List<DynaModel> params = getExportParam(qryparam);
            params = addBranchParam(params);
            list = service.getListData(table_name, orderby, orderbysort, params);
        }
        else if ("selected".equals(export_type))
        {
            String ids = RequestHelper.getString(getRequest(), "selected", "");
            if (!StringHelper.isEmpty(ids))
            {
                DynaModel tableInfo = service.getTableInfo(table_name);
                List<DynaModel> paramList = new ArrayList<DynaModel>();
                DynaModel item = new DynaModel();
                item.set("pname", tableInfo.getString("pk_column"));
                item.set("pvalue", ids);
                item.set("type", "in");
                list = service.getListData(table_name, orderby, orderbysort, paramList);
            }
        }
        return list;
    }
    
    public String doSelectPageVal()
    {
        int colid = getIntParameter("colid", 0);
        String id = getStrParameter("id", "");
        String txtid = getStrParameter("txtid", "");
        String selected = getStrParameter("selected", "");
        TableColumnService service = new TableColumnService();
        DynaModel colBean = service.load(colid);
        String input_type = colBean.getString("input_type");
        if (Consts.input_type_open_select_checkbox.equals(input_type))
        {
            input_type = "checkbox";
        }
        else if (Consts.input_type_open_select_radio.equals(input_type))
        {
            input_type = "radio";
        }
        else
        {
            input_type = "";
        }
        List<DynaModel> list = service.getOptionBeans(colBean);
        this.setAttribute("id", id);
        this.setAttribute("txtid", txtid);
        this.setAttribute("list", list);
        this.setAttribute("input_type", input_type);
        this.setAttribute("selected", selected);
        return "forward_select_val_page";
    }
    
    /**
     * 判断是否是管理员,并将值设置到作用域
     */
    protected void isCkSystem()
    {
        dataMap.put("branchIdColName", getBranchIdColNameByLowerCase());
        if (isSystemAdmin() || isAdministratorsRole())//管理员可以看到所有营业部信息
        {
            dataMap.put("isSystemAdmin", "1");
        }
        else
        {
            dataMap.put("userBranchNo", getBranchNo());
            //BranchService branchService =new BranchService();
            //String  userBranchName= branchService.findBranchName(getBranchNo());
            //form.put("userBranchName", userBranchName);
        }
    }
}
