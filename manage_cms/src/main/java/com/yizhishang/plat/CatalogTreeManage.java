package com.yizhishang.plat;

import com.yizhishang.base.domain.DynaModel;

import java.util.Iterator;
import java.util.List;

/**
 * 描述:
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2010-06-03
 * 创建时间: 20:24:20
 */
public class CatalogTreeManage
{

    private boolean isCheckParent = true;

    private String type = "checkbox";//类型

    public CatalogTreeManage()
    {

    }

    public CatalogTreeManage(boolean isCheckParent)
    {
        this.isCheckParent = isCheckParent;
    }

    public CatalogTreeManage(String type, boolean isCheckParent)
    {
        this.type = type;
        this.isCheckParent = isCheckParent;
    }

    private String getCatalogFunction(DynaModel data)
    {
        String catalogId = data.getString("catalog_id");
        String route = data.getString("route");
        String disabledInfo = "";
        if (isCheckParent) {
            int childrenNum = data.getInt("childrennum");
            if (childrenNum > 0) {
                disabledInfo = "disabled=\"disabled\"";
            }
        }

        StringBuffer buffer = new StringBuffer();
        if ("radio".equals(type)) {
            buffer.append("<input type=\"radio\" name=\"id_fun\" " + disabledInfo + " id=\"id_fun\" value=\"" +
                    catalogId + "\" route=\"" + route + "\"/><label id=\"lab_" + catalogId + "\">" + data.getString
                    ("name") + "</label>");
        } else {
            buffer.append("<input type=\"checkbox\" name=\"id_fun\" " + disabledInfo + " id=\"id_fun\" value=\"" +
                    catalogId + "\" route=\"" + route + "\"/><label id=\"lab_" + catalogId + "\">" + data.getString
                    ("name") + "</label>");
        }

        return buffer.toString();
    }

    /**
     * @param wholeCatalogs
     * @param isCheckParent 是否禁止选择父节点 true:禁止使用父节点（缺省）
     * @return
     * @描述：
     * @作者：袁永君
     * @时间：2010-06-03 20:36:07
     */
    @SuppressWarnings("unchecked")
    public String getCatalogTreeHtml(DynaModel wholeCatalogs, boolean isSystemAdmin, DynaModel catalogRole)
    {
        String html = "";
        if (wholeCatalogs != null) {
            List<DynaModel> children = (List<DynaModel>) wholeCatalogs.getObject("children");
            html = getChildrenTreeHtml(children, isSystemAdmin, catalogRole);
        }
        return html;
    }

    /**
     * 描述：获取栏目树状html代码
     * 作者：袁永君
     * 时间：2010-1-12 下午03:11:50
     *
     * @param list
     * @param catalogRole
     * @return
     */
    @SuppressWarnings("unchecked")
    public String getChildrenTreeHtml(List<DynaModel> list)
    {
        StringBuffer buffer = new StringBuffer();
        if (list != null) {
            buffer.append("<ul>");
            for (Iterator<DynaModel> iter = list.iterator(); iter.hasNext(); ) {
                buffer.append("<li>");
                DynaModel data = (DynaModel) iter.next();
                buffer.append(getCatalogFunction(data));
                buffer.append(getChildrenTreeHtml((List<DynaModel>) data.getObject("children")));
                buffer.append("</li>");
            }
            buffer.append("</ul>");
        }
        return buffer.toString();
    }

    /**
     * 描述：获取栏目树状html代码
     * 作者：袁永君
     * 时间：2010-1-12 下午03:11:50
     *
     * @param list
     * @param catalogRole
     * @return
     */
    @SuppressWarnings("unchecked")
    public String getChildrenTreeHtml(List<DynaModel> list, boolean isSystemAdmin, DynaModel catalogRole)
    {
        String catalogId = "";
        StringBuffer buffer = new StringBuffer();
        if (list != null) {
            buffer.append("<ul>");
            for (Iterator<DynaModel> iter = list.iterator(); iter.hasNext(); ) {
                DynaModel data = (DynaModel) iter.next();
                catalogId = data.getString("catalog_id");
                if (isSystemAdmin || (catalogRole != null && catalogRole.containsKey(catalogId))) {
                    buffer.append("<li>");
                    buffer.append(getCatalogFunction(data));
                    buffer.append(getChildrenTreeHtml((List<DynaModel>) data.getObject("children"), isSystemAdmin,
                            catalogRole));
                    buffer.append("</li>");
                } else {
                    continue;
                }
            }
            buffer.append("</ul>");
        }
        return buffer.toString();
    }
}
