package com.jhcz.common.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.util.RequestHelper;
import com.jhcz.common.consts.Consts;
import com.jhcz.common.service.TableColumnService;
import com.jhcz.common.service.TableService;
import com.jhcz.plat.domain.Result;
import com.jhcz.plat.web.action.BaseAction;

@Controller
@RequestMapping("/admin/TableColumnAdmin")
public class TableColumnAction extends BaseAction
{
    
    private final HashMap<String, Object> dataMap = new HashMap<String, Object>();
    
    @Resource
    TableService tableService;
    
    @Resource
    TableColumnService tableColumnService;
    
    @Override
    @RequestMapping("/doDefault.action")
    public ModelAndView doDefault()
    {
        int table_id = this.getIntParameter("table_id", 0);
        String name_en = getStrParameter("name_en", "").toLowerCase();
        String name_ch = getStrParameter("name_ch", "").toLowerCase();
        DataRow tableBean = tableService.load(table_id);
        List<Object> list = tableColumnService.getListData(table_id, name_en, name_ch);
        dataMap.put("tableBean", tableBean);
        dataMap.put("list", list);
        
        mv.addObject("data", dataMap);
        mv.setViewName("/WEB-INF/views/tableconfig/tablecolumn/list.jsp");
        return mv;
    }
    
    @ResponseBody
    @RequestMapping("/doImportCols.action")
    public Result doImportCols()
    {
        int table_id = this.getIntParameter("table_id", 0);
        DataRow tableBean = tableService.load(table_id);
        tableColumnService.importCols(tableBean);
        return new Result(0, "操作成功");
    }
    
    @Override
    @ResponseBody
    @RequestMapping("/edit.action")
    public Result edit(HttpServletRequest request, HttpServletResponse response)
    {
        //        DynaForm form = normalize(request);
        //        DataRow[] beans = TableColumnBean.getTableColumnBeanArray(form);
        DataRow[] beans = normalizeList(request);
        for (DataRow dataRow : beans)
        {
            dataRow.remove("pageUrl");
        }
        tableColumnService.update(beans);
        return super.edit(request, response);
    }
    
    @RequestMapping("/configSelect.action")
    public ModelAndView doConfigSelect()
    {
        int temp_id = getIntParameter("temp_id", 0);
        List<Object> enumList = tableColumnService.getEnumTypeList();
        List<Object> cols = tableColumnService.getTableCols(temp_id);
        DataRow colBean = tableColumnService.load(temp_id);
        String input_type = colBean.getString("input_type");
        if (Consts.input_type_open_select_checkbox.equals(input_type) || Consts.input_type_open_select_radio.equals(input_type)
                || Consts.input_type_checkbox.equals(input_type) || Consts.input_type_radio.equals(input_type) || Consts.input_type_select.equals(input_type))
        { // 不是可配置的输入框不陪你玩儿
            String select_table = colBean.getString("select_table");
            String select_text_column = colBean.getString("select_text_column");
            if ("t_enum_value".equals(select_table))
            { // 数据字典
                String select_condition = colBean.getString("select_condition");
                String selected = tableColumnService.getEnumTypeSelected(select_condition);
                dataMap.put("select_type", "sjzd");
                dataMap.put("selected", selected);
            }
            else if ("t_b_dictionary".equals(select_table))
            { // 手动配置
                dataMap.put("select_type", "sdsr");
                List<Object> table_cols = tableColumnService.getSdsrList(colBean.getString("id"));
                dataMap.put("dic_cols", table_cols);
            }
            else
            { // 直接表关联
                String select_condition = colBean.getString("select_condition");
                dataMap.put("select_type", "zdy");
                dataMap.put("selected_table", select_table);
                dataMap.put("select_value", colBean.getString("select_value"));
                dataMap.put("select_text", colBean.getString("select_text"));
                dataMap.put("select_condition", select_condition);
                List<Object> table_cols = tableColumnService.getTableCols(select_table);
                dataMap.put("table_cols", table_cols);
            }
            dataMap.put("select_text_column", select_text_column);
        }
        dataMap.put("list", enumList);
        dataMap.put("cols", cols);
        dataMap.put("temp_id", temp_id);
        
        mv.addObject("data", dataMap);
        mv.setViewName("/WEB-INF/views/tableconfig/tablecolumn/select_config.jsp");
        return mv;
    }
    
    @RequestMapping("/doSelectPageVal.action")
    public ModelAndView doSelectPageVal()
    {
        int colid = getIntParameter("colid", 0);
        String id = getStrParameter("id", "");
        String txtid = getStrParameter("txtid", "");
        String selected = getStrParameter("selected", "");
        DataRow colBean = tableColumnService.load(colid);
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
        List<Object> list = tableColumnService.getOptionBeans(colBean);
        this.setAttribute("id", id);
        this.setAttribute("txtid", txtid);
        this.setAttribute("list", list);
        this.setAttribute("input_type", input_type);
        this.setAttribute("selected", selected);
        
        mv.setViewName("/WEB-INF/views/common/select.jsp");
        return mv;
    }
    
    @RequestMapping("/doAjaxSelectTable.action")
    public ModelAndView doAjaxSelectTable()
    {
        String table_name_en = RequestHelper.getString(getRequest(), "table_name", "");
        String select_value = RequestHelper.getString(getRequest(), "select_value", "");
        String select_text = RequestHelper.getString(getRequest(), "select_text", "");
        List<Object> cols = tableColumnService.getTableCols(table_name_en);
        dataMap.put("list", cols);
        dataMap.put("select_value", select_value);
        dataMap.put("select_text", select_text);
        
        mv.addObject("data", dataMap);
        mv.setViewName("/WEB-INF/views/tableconfig/tablecolumn/select_table_config.jsp");
        return mv;
    }
    
    public String doAjaxUpdateManualOption()
    {
        String temp_id = RequestHelper.getString(getRequest(), "temp_id", "");
        String item_value = RequestHelper.getString(getRequest(), "item_value", "");
        String item_text = "";
        try
        {
            item_text = URLDecoder.decode(RequestHelper.getString(getRequest(), "item_text", ""), "utf-8");
        }
        catch (UnsupportedEncodingException e1)
        {
            e1.printStackTrace();
        }
        String reusltStr = "suc";
        if (!"".equals(temp_id))
        {
            String[] values = item_value.split("\\,");
            String[] texts = item_text.split("\\,");
            if (values.length == texts.length || values.length + 1 == texts.length)
            {
                tableColumnService.updateSdsr(temp_id, values, texts);
            }
            else
            {
                reusltStr = "error:wrong index!";
            }
        }
        PrintWriter writer = null;
        try
        {
            writer = getResponse().getWriter();
            writer.print(reusltStr);
            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
