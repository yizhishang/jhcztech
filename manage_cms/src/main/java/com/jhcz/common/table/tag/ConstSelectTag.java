package com.jhcz.common.table.tag;

import javax.servlet.jsp.JspWriter;

import com.jhcz.base.util.StringHelper;
import com.jhcz.base.util.ToolKit;
import com.jhcz.common.table.consts.Consts;

/**
 * 专门用于配置页面动态生成下拉框
 */
public class ConstSelectTag extends CommonTag
{
    
    private static final long serialVersionUID = -4495264469438501769L;
    
    private String prefix = ""; // 前缀
    
    public int doEndTag()
    {
        onChange = ToolKit.nullTrans(onChange, "");
        value = ToolKit.nullTrans(value, "");
        prefix = ToolKit.nullTrans(prefix, "input_type_");
        // 获取字段配置信息
        String optionStr = Consts.getInputTypeOptions(prefix, value);
        try
        {
            JspWriter out = pageContext.getOut();
            StringBuffer sb = new StringBuffer();
            sb.append("<select name='" + name + "' id='" + id + "'");
            if (!StringHelper.isEmpty(css))
                sb.append(" class='" + css + "'");
            if (!StringHelper.isEmpty(style))
                sb.append(" style='" + style + "'");
            if (!StringHelper.isEmpty(onChange))
                sb.append(" onChange='" + onChange + "'");
            sb.append(">\n");
            sb.append(optionStr);
            sb.append("</select>");
            out.print(sb.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }
    
    public String getPrefix()
    {
        return prefix;
    }
    
    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }
}
