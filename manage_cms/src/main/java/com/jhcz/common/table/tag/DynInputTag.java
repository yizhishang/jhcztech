package com.jhcz.common.table.tag;

import java.util.Date;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import com.jhcz.base.jdbc.DataRow;
import com.jhcz.base.util.DateHelper;
import com.jhcz.base.util.StringHelper;
import com.jhcz.base.util.ToolKit;
import com.jhcz.common.table.consts.Consts;
import com.jhcz.common.table.service.TableColumnService;

public class DynInputTag extends CommonTag
{
    
    private static final long serialVersionUID = -4495264469438501769L;
    
    private DataRow item = null;
    
    private DataRow colitem = null;
    
    private String colname = "";
    
    private String curuser = "";
    
    private final TableColumnService colservice = new TableColumnService();
    
    @Override
    public int doEndTag()
    {
        id = ToolKit.nullTrans(id, name);
        onChange = ToolKit.nullTrans(onChange, "");
        colname = ToolKit.nullTrans(colname, "");
        if (item == null)
        {
            item = new DataRow();
        }
        String defaultvalue = ToolKit.nullTrans(colitem.getString("default_value"), "");
        value = ToolKit.nullTrans(item.getString(colname), defaultvalue);
        String is_sys = colitem.getString("is_sys");
        if ("Y".equals(is_sys))
        {
            String sys_type = colitem.getString("sys_type");
            if (Consts.sys_col_time.equals(sys_type))
            {
                value = DateHelper.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
            }
            else if (Consts.sys_col_userid.equals(sys_type))
            {
                value = curuser;
            }
            else if (Consts.sys_col_time_once.equals(sys_type))
            {
                if (StringHelper.isEmpty(value))
                {
                    value = DateHelper.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
                }
            }
            else if (Consts.sys_col_userid_once.equals(sys_type))
            {
                if (StringHelper.isEmpty(value))
                {
                    value = curuser;
                }
            }
        }
        try
        {
            JspWriter out = pageContext.getOut();
            String htmlcontent = "";
            String input_type = colitem.getString("input_type");
            if (Consts.input_type_input.equals(input_type))
            {
                htmlcontent = makeInputTextTag();
            }
            else if (Consts.input_type_select.equals(input_type))
            {
                htmlcontent = makeSelectTag();
            }
            else if (Consts.input_type_radio.equals(input_type))
            {
                htmlcontent = makeRadioTag("radio");
            }
            else if (Consts.input_type_checkbox.equals(input_type))
            {
                htmlcontent = makeRadioTag("checkbox");
            }
            else if (Consts.input_type_textarea.equals(input_type))
            {
                htmlcontent = makeTextAreaTag();
            }
            else if (Consts.input_type_file.equals(input_type))
            {
                htmlcontent = makeInputFileTag();
            }
            else if (Consts.input_type_html_editor.equals(input_type))
            {
                htmlcontent = makeHtmlEditorTag();
            }
            else if (Consts.input_type_open_select_radio.equals(input_type))
            {
                htmlcontent = makeOpenRadioTag("radio");
            }
            else if (Consts.input_type_open_select_checkbox.equals(input_type))
            {
                htmlcontent = makeOpenRadioTag("checkbox");
            }
            out.print(htmlcontent);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
    
    /**
     * 生成readonly属性
     */
    private String makeReadonly()
    {
        String str = "";
        String isreadonly = colitem.getString("is_readonly");
        if ("Y".equals(isreadonly))
        {
            str = " readonly='readonly' ";
        }
        return str;
    }
    
    /**
     * 生成js校验字符串
     */
    private String makeVerificationStr()
    {
        String str = "";
        String benull = colitem.getString("be_null");
        String titlevar = colitem.getString("name_ch");
        String is_special_format = colitem.getString("is_special_format");
        String special_format = colitem.getString("special_format");
        if ("N".equals(benull))
        {
            str += " title_val='" + titlevar + "' isnotempty='Y' ";
        }
        if ("Y".equals(is_special_format))
        {
            str += makeSpecialStr(special_format);
        }
        return str;
    }
    
    /**
     * 生成特殊格式的校验字符串
     */
    private String makeSpecialStr(String special_format)
    {
        String str = "";
        if (Consts.special_format_number.equals(special_format))
        {
            str = " isnumber='Y' ";
        }
        else if (Consts.special_format_time.equals(special_format))
        {
            str = " isdatetime='Y' ";
        }
        return str;
    }
    
    /**
     * 生成html编辑框
     * @return
     */
    private String makeHtmlEditorTag()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("<input name='" + name + "' type='hidden' id='" + id + "' value='" + value + "' />\n");
        sb.append("<IFRAME ID='eWebEditor_" + id + "' name='eWebEditor_" + id + "' " + makeVerificationStr() + makeReadonly()
                + " SRC='editor/ewebeditor/ewebeditor.htm?id=" + id + "&style=standard650' FRAMEBORDER='0' SCROLLING='no' WIDTH='99%' HEIGHT='500'></IFRAME>");
        return sb.toString();
    }
    
    /**
     * 生成文件上传框
     * @return
     */
    private String makeInputFileTag()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("<input type='text' name='" + name + "' id='" + id + "' class='input02' style='width:300px;' readonly  " + makeVerificationStr() + " value='"
                + value + "'/>\n");
        if (StringHelper.isEmpty(makeReadonly()))
        {
            sb.append("<input type='button' onClick=\"openUploadFileDialog($('#" + id + "'))\" value='上传文件' class='bt02'/>\n");
            sb.append("<input type='button'onClick=\"openDeleteFileDialog($('#" + id + "'))\" value='删除' class='bt01'/>");
        }
        return sb.toString();
    }
    
    /**
     * 生成文本框
     * @return
     */
    private String makeInputTextTag()
    {
        StringBuffer sb = new StringBuffer();
        String is_special_format = colitem.getString("is_special_format");
        String special_format = colitem.getString("special_format");
        if ("N".equals(is_special_format))
        {
            sb.append("<input type='text' id='" + id + "' name='" + name + "' value='" + value + "' " + makeVerificationStr() + makeReadonly() + "");
            if (!StringHelper.isEmpty(css))
                sb.append(" class='" + css + "'");
            if (!StringHelper.isEmpty(style))
                sb.append(" style='" + style + "'");
            if (!StringHelper.isEmpty(onChange))
                sb.append(" onChange='" + onChange + "'");
            sb.append(">");
        }
        else
        {
            // 时间格式
            if (Consts.special_format_time.equals(special_format))
            {
                sb.append("<input type='text' id='" + id + "' name='" + name + "' value='" + value + "' " + makeVerificationStr() + "");
                if (!StringHelper.isEmpty(css))
                    sb.append(" class='" + css + "'");
                if (!StringHelper.isEmpty(style))
                    sb.append(" style='" + style + "'");
                if (!StringHelper.isEmpty(onChange))
                    sb.append(" onChange='" + onChange + "'");
                sb.append(" readonly='true' ");
                if (StringHelper.isEmpty(makeReadonly()))
                {
                    sb.append(" onFocus=\"WdatePicker({el:'" + id + "',dateFmt:'yyyy-MM-dd HH:mm:ss'})\">");
                }
            }
            else
            {
                sb.append("<input type='text' id='" + id + "' name='" + name + "' value='" + value + "' " + makeVerificationStr() + makeReadonly() + "");
                if (!StringHelper.isEmpty(css))
                    sb.append(" class='" + css + "'");
                if (!StringHelper.isEmpty(style))
                    sb.append(" style='" + style + "'");
                if (!StringHelper.isEmpty(onChange))
                    sb.append(" onChange='" + onChange + "'");
                sb.append(">");
            }
        }
        return sb.toString();
    }
    
    /**
     * 生成文本域
     * @return
     */
    private String makeTextAreaTag()
    {
        StringBuffer sb = new StringBuffer();
        String is_single = colitem.getString("is_single");
        sb.append("<textarea id='" + id + "' name='" + name + "' " + makeVerificationStr() + makeReadonly() + "");
        if ("Y".equals(is_single))
        {
            sb.append(" cols='80'");
        }
        else
        {
            sb.append(" cols='20'");
        }
        sb.append(" rows='8'");
        if (!StringHelper.isEmpty(css))
            sb.append(" class='" + css + "'");
        if (!StringHelper.isEmpty(style))
            sb.append(" style='" + style + "'");
        if (!StringHelper.isEmpty(onChange))
            sb.append(" onChange='" + onChange + "'");
        sb.append(">" + value + "</textarea>");
        return sb.toString();
    }
    
    /**
     * 生成单选或多选框
     * @return
     */
    private String makeRadioTag(String tagname)
    {
        List<Object> list = colservice.getOptionBeans(colitem); // 获得列表
        StringBuffer sb = new StringBuffer();
        for (int i = 0; list != null && i < list.size(); i++)
        {
            DataRow tempBean = (DataRow) list.get(i);
            String tempValue = tempBean.getString("optionvalue");
            String tempText = tempBean.getString("optiontext");
            if ("radio".equals(tagname) && value.equals(tempValue))
            {
                sb.append("<input type='" + tagname + "' id='" + id + "_" + i + "' name='" + name + "' " + makeVerificationStr() + makeReadonly()
                        + " onclick=\"changeRadioTxt('" + tempText + "','" + colitem.getString("select_text_column")
                        + "')\" style='vertical-align:-3px;' value='" + tempValue + "' checked='checked'><span onclick=\"$('#" + id + "_" + i + "').click()\">"
                        + tempText + "</span>\n");
            }
            else if ("checkbox".equals(tagname) && ("," + value + ",").indexOf("," + tempValue + ",") != -1)
            {
                sb.append("<input type='" + tagname + "' id='" + id + "_" + i + "' name='" + name + "' " + makeVerificationStr() + makeReadonly()
                        + " onclick=\"changeRadioTxt('" + tempText + "','" + colitem.getString("select_text_column")
                        + "')\" style='vertical-align:-3px;' value='" + tempValue + "' checked='checked'><span onclick=\"$('#" + id + "_" + i + "').click()\">"
                        + tempText + "</span>\n");
            }
            else
            {
                sb.append("<input type='" + tagname + "' id='" + id + "_" + i + "' name='" + name + "' " + makeVerificationStr() + makeReadonly()
                        + " onclick=\"changeRadioTxt('" + tempText + "','" + colitem.getString("select_text_column")
                        + "')\" style='vertical-align:-3px;' value='" + tempValue + "'><span onclick=\"$('#" + id + "_" + i + "').click()\">" + tempText
                        + "</span>\n");
            }
        }
        return sb.toString();
    }
    
    /**
     * 生成单选或多选弹出页面
     * @return
     */
    private String makeOpenRadioTag(String tagname)
    {
        List<Object> list = colservice.getOptionBeans(colitem); // 获得列表
        String select_text_column = colitem.getString("select_text_column");
        String colid = colitem.getString("id");
        String txtValue = "";
        StringBuffer sb = new StringBuffer();
        sb.append("<input name=\"" + name + "\" id=\"" + id + "\" value=\"" + value + "\" " + makeVerificationStr() + " type=\"hidden\">");
        if (!StringHelper.isEmpty(value))
        {
            String tvalue = value;
            if (!value.startsWith(","))
            {
                tvalue = "," + tvalue;
            }
            if (!value.endsWith(","))
            {
                tvalue = tvalue + ",";
            }
            for (int i = 0; list != null && i < list.size(); i++)
            {
                DataRow tempBean = (DataRow) list.get(i);
                String tempValue = tempBean.getString("optionvalue");
                String tempText = tempBean.getString("optiontext");
                if (tvalue.indexOf("," + tempValue + ",") != -1)
                {
                    txtValue += "," + tempText;
                }
            }
            if (txtValue.startsWith(","))
            {
                txtValue = txtValue.substring(1);
            }
        }
        sb.append("<input name=\"show_temp_" + name + "\" id=\"show_temp_" + id + "\" value=\"" + txtValue
                + "\" type=\"text\" readonly=\"readonly\" disabled=\"disabled\">");
        if (StringHelper.isEmpty(makeReadonly()))
        {
            sb.append("<img alt=\"点击选择\" align=\"middle\" src=\"/admin/images/lookup.gif\" onclick=\"openSelectPage('" + id + "','" + select_text_column
                    + "','" + colid + "')\"/>");
        }
        return sb.toString();
    }
    
    /**
     * 生成下拉框
     * @return
     */
    private String makeSelectTag()
    {
        List<Object> list = colservice.getOptionBeans(colitem); // 获得列表
        StringBuffer sb = new StringBuffer();
        sb.append("<select name='" + name + "' id='" + id + "' " + makeVerificationStr() + makeReadonly() + "");
        if (!StringHelper.isEmpty(css))
            sb.append(" class='" + css + "'");
        if (!StringHelper.isEmpty(style))
            sb.append(" style='" + style + "'");
        if (!StringHelper.isEmpty(onChange))
            sb.append(" onChange='" + onChange + "';changeTxt(this.id,'" + colitem.getString("select_text_column") + "')");
        sb.append(">\n");
        sb.append("<option value=''>------</option>\n");
        for (int i = 0; list != null && i < list.size(); i++)
        {
            DataRow tempBean = (DataRow) list.get(i);
            String tempValue = tempBean.getString("optionvalue");
            String tempText = tempBean.getString("optiontext");
            if (value.equals(tempValue))
            {
                sb.append("<option value='" + tempValue + "' selected='selected'>" + tempText + "</option>\n");
            }
            else
            {
                sb.append("<option value='" + tempValue + "'>" + tempText + "</option>\n");
            }
        }
        sb.append("</select>");
        return sb.toString();
    }
    
    public DataRow getItem()
    {
        return item;
    }
    
    public void setItem(DataRow item)
    {
        this.item = item;
    }
    
    public DataRow getColitem()
    {
        return colitem;
    }
    
    public void setColitem(DataRow colitem)
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
    
    public String getCuruser()
    {
        return curuser;
    }
    
    public void setCuruser(String curuser)
    {
        this.curuser = curuser;
    }
}
