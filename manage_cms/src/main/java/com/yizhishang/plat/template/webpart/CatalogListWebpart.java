package com.yizhishang.plat.template.webpart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yizhishang.base.service.ServiceLocator;
import com.yizhishang.base.util.MapHelper;
import com.yizhishang.plat.domain.Catalog;
import com.yizhishang.plat.service.CatalogService;
import com.yizhishang.plat.template.Context;
import com.yizhishang.plat.template.FMWebpartParser;

/**
 * 描述: 获取一级子栏目列表信息
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-2-25
 * 创建时间: 下午1:56:27
 */
public class CatalogListWebpart extends FMWebpartParser
{
    
    @Override
    public Map<String, Object> getModel(Context context, Map<String, Object> webpartProp)
    {
        int catalogId = MapHelper.getInt(webpartProp, "catalogId");//栏目ID
        CatalogService catalogService = (CatalogService) ServiceLocator.getService(CatalogService.class);
        List<Catalog> catalogList = catalogService.findChildrenById(catalogId);
        
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("catalogList", catalogList);
        dataMap.put("catalogId", catalogId);
        return dataMap;
    }
}
