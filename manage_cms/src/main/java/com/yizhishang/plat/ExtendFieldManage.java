package com.yizhishang.plat;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.plat.service.CustomFieldService;

import java.util.*;

/**
 * 描述:
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2010-05-17
 * 创建时间: 14:24:44
 */
public class ExtendFieldManage
{

    private static Set<Object> fieldData = new TreeSet<Object>();

    private static ExtendFieldManage fieldManage = new ExtendFieldManage();

    static {
        initFieldDataMap();
    }

    private ExtendFieldManage()
    {

    }

    public static ExtendFieldManage getInstance()
    {
        return fieldManage;
    }

    private static void initFieldDataMap()
    {
        try {
            Set<Object> fieldSet = new HashSet<Object>();
            //查询已经被使用的字段
            CustomFieldService fieldService = new CustomFieldService();
            List<DynaModel> fieldList = fieldService.findExtendFieldInfo(0);
            for (Iterator<DynaModel> iter = fieldList.iterator(); iter.hasNext(); ) {
                DynaModel data = (DynaModel) iter.next();
                fieldSet.add(new Integer(data.getInt("id")));
            }

            Integer field = new Integer(0);
            for (int i = 0; i < 50; i++) {
                field = new Integer(i);
                if (fieldSet.contains(field)) {
                    continue;
                }
                fieldData.add(field);
            }
        } catch (Exception ex) {

        }
    }

    public void addField(Integer field)
    {
        fieldData.add(field);
    }

    public Set<Object> getFieldData()
    {
        return fieldData;
    }

    public void removeField(Integer field)
    {
        fieldData.remove(field);
    }
}
