package com.yizhishang.plat.template.webpart;

import com.yizhishang.base.util.MapHelper;
import com.yizhishang.plat.domain.Catalog;
import com.yizhishang.plat.template.Context;
import com.yizhishang.plat.template.FMWebpartParser;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述: 查询栏目信息
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-2-25
 * 创建时间: 下午2:53:44
 */
public class CatalogWebpart extends FMWebpartParser
{

    @Override
    public Map<String, Object> getModel(Context context, Map<String, Object> webpartProp)
    {
        int catalogId = MapHelper.getInt(webpartProp, "catalogId");//栏目ID
        Catalog catalog = catalogService.findCatalogById(catalogId);

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("catalog", catalog);
        return dataMap;
    }

}
