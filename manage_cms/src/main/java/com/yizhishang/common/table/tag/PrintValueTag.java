package com.yizhishang.common.table.tag;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspWriter;

import com.google.common.collect.Maps;
import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.base.util.ToolKit;
import com.yizhishang.common.table.consts.Consts;

public class PrintValueTag extends CommonTag
{
    
    private static final long serialVersionUID = -5587142182111678087L;
    
    private Map<String, Object> item = null;
    
    private Map<String, Object> colitem = null;
    
    private String is_pk = "";
    
    private String colname = "";
    
    private String input_type = "";
    
    private String select_value = "";
    
    private String select_text = "";
    
    private String select_table = "";
    
    private String select_condition = "";
    
    private final String pics = ".jpg,.gif,.png,.bmp";
    
    @Override
    public int doEndTag()
    {
        colname = ToolKit.nullTrans(colname, "");
        is_pk = ToolKit.nullTrans(is_pk, "N");
        if (colitem == null)
        {
            colitem = Maps.newHashMap();
        }
        input_type = ToolKit.o2s(colitem.get("input_type"), Consts.input_type_input);
        String reuslt = ToolKit.o2s(item.get(colname), "");
        // 当不是作为主键值输出时，输入框类型是与其他表关联时，自动将展示文本展示到列表中
        if (!"Y".equals(is_pk))
        {
            if (input_type.equals(Consts.input_type_checkbox) || input_type.equals(Consts.input_type_radio) || input_type.equals(Consts.input_type_select))
            {
                reuslt = getOptionText(reuslt, input_type);
            }
            else if (input_type.equals(Consts.input_type_file) && !StringHelper.isEmpty(reuslt))
            {
                if (reuslt.lastIndexOf(".") > 0)
                {
                    String fix = reuslt.substring(reuslt.lastIndexOf("."));
                    if (pics.indexOf(fix.toLowerCase()) != -1)
                    { // 是图片,展示略缩图
                        reuslt = getPicHtml(reuslt);
                    }
                }
            }
        }
        try
        {
            JspWriter out = pageContext.getOut();
            out.print(reuslt);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
    
    /**
     * 展示图片略缩图
     * @return
     */
    public String getPicHtml(String colvalue)
    {
        String result = "<img src='" + colvalue + "' style='width: 100px;;height: 30px;'>";
        return result;
    }
    
    /**
     * 获得和值对应的文本
     * @return
     */
    public String getOptionText(String colvalue, String input_type)
    {
        String result = colvalue;
        select_value = ToolKit.nullTrans(colitem.get("select_value").toString(), "");
        select_text = ToolKit.nullTrans(colitem.get("select_text").toString(), select_value);
        select_table = ToolKit.nullTrans(colitem.get("select_table").toString(), "");
        select_condition = ToolKit.nullTrans(colitem.get("select_condition").toString(), "");
        // 找出配置的字段的值列表
        if (!"".equals(select_table) && !"".equals(select_value))
        {
            if (!Consts.input_type_checkbox.equals(input_type))
            {
                String sql = "select distinct " + select_text + " as optiontext from " + select_table + " where " + select_value + "=? " + select_condition;
                ArrayList<Object> argList = new ArrayList<Object>();
                argList.add(colvalue);
                BaseService bservice = new BaseService();
                List<DynaModel> list = bservice.getJdbcTemplateUtil().queryForList(sql, DynaModel.class, argList.toArray());
                if (list != null && list.size() > 0)
                {
                    DynaModel dr = (DynaModel) list.get(0);
                    result = dr.getString("optiontext");
                }
            }
            else
            {
                if (StringHelper.isEmpty(colvalue))
                {
                    colvalue = "''";
                }
                String sql = "select distinct " + select_text + " as optiontext from " + select_table + " where " + select_value + " in (" + colvalue + ") "
                        + select_condition;
                BaseService bservice = new BaseService();
                String[] texts = bservice.getJdbcTemplateUtil().queryStringArray(sql);
                if (texts != null && texts.length > 0)
                {
                    result = "";
                }
                for (int i = 0; texts != null && i < texts.length; i++)
                {
                    result += "," + texts[i];
                }
                if (!StringHelper.isEmpty(result) && result.startsWith(","))
                {
                    result = result.substring(1);
                }
            }
        }
        return result;
    }
    
    public String getInput_type()
    {
        return input_type;
    }
    
    public void setInput_type(String inputType)
    {
        input_type = inputType;
    }
    
    public String getSelect_value()
    {
        return select_value;
    }
    
    public void setSelect_value(String selectValue)
    {
        select_value = selectValue;
    }
    
    public String getSelect_text()
    {
        return select_text;
    }
    
    public void setSelect_text(String selectText)
    {
        select_text = selectText;
    }
    
    public String getSelect_table()
    {
        return select_table;
    }
    
    public void setSelect_table(String selectTable)
    {
        select_table = selectTable;
    }
    
    public String getSelect_condition()
    {
        return select_condition;
    }
    
    public void setSelect_condition(String selectCondition)
    {
        select_condition = selectCondition;
    }
    
    public Map<String, Object> getItem()
    {
        return item;
    }
    
    public void setItem(Map<String, Object> item)
    {
        this.item = item;
    }
    
    public String getColname()
    {
        return colname;
    }
    
    public void setColname(String colname)
    {
        this.colname = colname;
    }
    
    public Map<String, Object> getColitem()
    {
        return colitem;
    }
    
    public void setColitem(Map<String, Object> colitem)
    {
        this.colitem = colitem;
    }
    
    public String getIs_pk()
    {
        return is_pk;
    }
    
    public void setIs_pk(String isPk)
    {
        is_pk = isPk;
    }
    
}
