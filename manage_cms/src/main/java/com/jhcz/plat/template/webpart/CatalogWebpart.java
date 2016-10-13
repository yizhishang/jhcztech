package com.jhcz.plat.template.webpart;

import java.util.HashMap;
import java.util.Map;

import com.jhcz.base.service.ServiceLocator;
import com.jhcz.base.util.MapHelper;
import com.jhcz.plat.domain.Catalog;
import com.jhcz.plat.service.CatalogService;
import com.jhcz.plat.template.Context;
import com.jhcz.plat.template.FMWebpartParser;

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
        CatalogService catalogService = (CatalogService) ServiceLocator.getService(CatalogService.class);
        Catalog catalog = catalogService.findCatalogById(catalogId);
        
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("catalog", catalog);
        return dataMap;
    }
    
}
