package com.yizhishang.common.table.tag;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.base.util.ToolKit;
import com.yizhishang.common.table.consts.Consts;
import com.yizhishang.common.table.service.TableColumnService;

import javax.servlet.jsp.JspWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynSearchInputTag extends com.yizhishang.common.table.tag.CommonTag
{

    private static final long serialVersionUID = -4495264469438501769L;

    private final TableColumnService colservice = new TableColumnService();

    private Map<String, Object> colitem = null;

    private String colname = "";

    private Map<String, Object> param = null;

    @Override
    public int doEndTag()
    {
        onChange = ToolKit.nullTrans(onChange, "");
        colname = ToolKit.nullTrans(colname, "");
        if (param == null) {
            param = new HashMap<String, Object>();
        }
        try {
            JspWriter out = pageContext.getOut();
            String htmlcontent = "";
            String input_type = ToolKit.o2s(colitem.get("input_type"));
            if (Consts.input_type_select.equals(input_type)) {
                htmlcontent = makeSelectTag();
            } else if (Consts.input_type_radio.equals(input_type)) {
                htmlcontent = makeSelectTag();
            } else if (Consts.input_type_checkbox.equals(input_type)) {
                htmlcontent = makeSelectTag();
            } else if (Consts.input_type_open_select_radio.equals(input_type)) {
                htmlcontent = makeSelectTag();
            } else if (Consts.input_type_open_select_checkbox.equals(input_type)) {
                htmlcontent = makeSelectTag();
            } else {
                htmlcontent = makeInputTextTag();
            }
            out.print(htmlcontent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    /**
     * 生成文本框
     *
     * @return
     */
    private String makeInputTextTag()
    {
        StringBuffer sb = new StringBuffer();
        String name_ch = ToolKit.o2s(colitem.get("name_ch"));
        ;
        String name_en = ToolKit.o2s(colitem.get("name_en"));
        ;
        value = ToolKit.nullTrans((String) param.get("search_name_" + name_en), "");
        String special_format = ToolKit.o2s(colitem.get("special_format"));
        String is_special_format = ToolKit.o2s(colitem.get("is_special_format"));
        if ("N".equals(is_special_format)) {
            sb.append(name_ch + "：<input type=\"text\" name=\"search_name_" + name_en + "\" value=\"" + value + "\" " +
                    "class=\"input01\" ");
            if (!StringHelper.isEmpty(css))
                sb.append(" class='" + css + "'");
            if (!StringHelper.isEmpty(style))
                sb.append(" style='" + style + "'");
            if (!StringHelper.isEmpty(onChange))
                sb.append(" onChange='" + onChange + "'");
            sb.append("/>&nbsp;&nbsp;&nbsp;");
        } else {
            // 时间格式
            if (Consts.special_format_time.equals(special_format)) {
                sb.append("起始" + name_ch + "：<input type=\"text\" id=\"search_name_" + name_en + "_begin\" " +
                        "name=\"search_name_" + name_en + "_begin\" value=\"" + ToolKit.nullTrans((String) param.get
                        ("search_name_" + name_en + "_begin"), "") + "\" class=\"input01\" ");
                if (!StringHelper.isEmpty(css))
                    sb.append(" class='" + css + "'");
                if (!StringHelper.isEmpty(style))
                    sb.append(" style='" + style + "'");
                if (!StringHelper.isEmpty(onChange))
                    sb.append(" onChange='" + onChange + "'");
                sb.append(" readonly='readonly' onFocus=\"WdatePicker({el:'search_name_" + name_en + "_begin'," +
                        "dateFmt:'yyyy-MM-dd HH:mm:ss'})\">&nbsp;&nbsp;");
                sb.append("终止" + name_ch + "：<input type=\"text\" id=\"search_name_" + name_en + "_end\" " +
                        "name=\"search_name_" + name_en + "_end\" value=\"" + ToolKit.nullTrans((String) param.get
                        ("search_name_" + name_en + "_end"), "") + "\" class=\"input01\" ");
                if (!StringHelper.isEmpty(css))
                    sb.append(" class='" + css + "'");
                if (!StringHelper.isEmpty(style))
                    sb.append(" style='" + style + "'");
                if (!StringHelper.isEmpty(onChange))
                    sb.append(" onChange='" + onChange + "'");
                sb.append(" readonly='true' onFocus=\"WdatePicker({el:'search_name_" + name_en + "_end'," +
                        "dateFmt:'yyyy-MM-dd HH:mm:ss'})\">&nbsp;&nbsp;");
            } else {
                sb.append(name_ch + "：<input type=\"text\" id=\"search_name_" + name_en + "\" name=\"search_name_" +
                        name_en + "\" value=\"" + value + "\" class=\"input01\" ");
                if (!StringHelper.isEmpty(css))
                    sb.append(" class='" + css + "'");
                if (!StringHelper.isEmpty(style))
                    sb.append(" style='" + style + "'");
                if (!StringHelper.isEmpty(onChange))
                    sb.append(" onChange='" + onChange + "'");
                sb.append("/>&nbsp;&nbsp;&nbsp;");
            }
        }
        return sb.toString();
    }

    /**
     * 生成下拉框
     *
     * @return
     */
    private String makeSelectTag()
    {
        String name_ch = ToolKit.o2s(colitem.get("name_ch"));
        ;
        String name_en = ToolKit.o2s(colitem.get("name_en"));
        ;
        value = ToolKit.nullTrans((String) param.get("eq_search_name_" + name_en), "");
        DynaModel bean = new DynaModel();
        bean.putAll(colitem);
        List<DynaModel> list = colservice.getOptionBeans(bean); // 获得列表
        StringBuffer sb = new StringBuffer();
        sb.append(name_ch + "：<select name=\"eq_search_name_" + name_en + "\" id='" + id + "'  ");
        if (!StringHelper.isEmpty(css))
            sb.append(" class='" + css + "'");
        if (!StringHelper.isEmpty(style))
            sb.append(" style='width:104px;" + style + "'");
        else
            sb.append(" style='width:104px;'");
        if (!StringHelper.isEmpty(onChange))
            sb.append(" onChange='" + onChange + "'");
        sb.append(">\n");
        sb.append("<option value=''>------</option>\n");
        for (int i = 0; list != null && i < list.size(); i++) {
            DynaModel tempBean = (DynaModel) list.get(i);
            String tempValue = tempBean.getString("optionvalue");
            String tempText = tempBean.getString("optiontext");
            if (value.equals(tempValue)) {
                sb.append("<option value='" + tempValue + "' selected='selected'>" + tempText + "</option>\n");
            } else {
                sb.append("<option value='" + tempValue + "'>" + tempText + "</option>\n");
            }
        }
        sb.append("</select>");
        return sb.toString();
    }

    public Map<String, Object> getColitem()
    {
        return colitem;
    }

    public void setColitem(Map<String, Object> colitem)
    {
        this.colitem = colitem;
    }

    public String getColname()
    {
        return colname;
    }

    public void setColname(String colname)
    {
        this.colname = colname;
    }

    @Override
    public String getValue()
    {
        return value;
    }

    @Override
    public void setValue(String value)
    {
        this.value = value;
    }

    public Map<String, Object> getParam()
    {
        return param;
    }

    public void setParam(Map<String, Object> param)
    {
        this.param = param;
    }
}
