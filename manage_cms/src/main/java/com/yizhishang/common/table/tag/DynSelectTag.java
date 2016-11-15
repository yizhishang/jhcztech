package com.yizhishang.common.table.tag;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.service.BaseService;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.base.util.ToolKit;
import com.yizhishang.common.table.service.TableColumnService;

import javax.servlet.jsp.JspWriter;
import java.util.List;

public class DynSelectTag extends CommonTag
{

    private static final long serialVersionUID = -4495264469438501769L;

    private boolean showBlank = true; // 是否展示为空的行

    private String colId = "0"; // 字段配置信息id

    @Override
    public int doEndTag()
    {
        id = ToolKit.nullTrans(id, name);
        onChange = ToolKit.nullTrans(onChange, "");
        colId = ToolKit.nullTrans(colId, "0");
        value = ToolKit.nullTrans(value, "");
        // 获取字段配置信息
        TableColumnService colService = new TableColumnService();
        DynaModel bean = colService.load(Integer.parseInt(colId));

        List<DynaModel> list = getOptionBeans(bean); // 获得列表

        try {
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
            if (showBlank) {
                sb.append("<option value=''>------</option>\n");
            }
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
            out.print(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    /**
     * 获得值和对应的文本的列表
     *
     * @return
     */
    public List<DynaModel> getOptionBeans(DynaModel bean)
    {
        String select_value = bean.getString("select_value");
        String select_text = bean.getString("select_text");
        String select_table = bean.getString("select_table");
        String select_condition = bean.getString("select_condition");
        // 找出配置的字段的值列表
        String sql = "select distinct " + select_text + " as optiontext," + select_value + " as optionvalue from " +
                select_table + " where 1=1 " + select_condition;
        BaseService bservice = new BaseService();
        List<DynaModel> list = bservice.getJdbcTemplateUtil().queryForList(sql, DynaModel.class);
        return list;
    }

    public boolean isShowBlank()
    {
        return showBlank;
    }

    public void setShowBlank(boolean showBlank)
    {
        this.showBlank = showBlank;
    }

    public String getColId()
    {
        return colId;
    }

    public void setColId(String colId)
    {
        this.colId = colId;
    }
}
