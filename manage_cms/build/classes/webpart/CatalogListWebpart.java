package webpart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jhcz.base.service.ServiceLocator;
import com.jhcz.base.util.MapHelper;
import com.jhcz.base.util.StringHelper;
import com.jhcz.plat.domain.Catalog;
import com.jhcz.plat.service.ArticleService;
import com.jhcz.plat.service.CatalogService;
import com.jhcz.plat.system.SysLibrary;
import com.jhcz.plat.template.Context;
import com.jhcz.plat.template.FMWebpartParser;

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
        
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("catalogId", catalogId);
        
        boolean showArticleAsCatalog = MapHelper.getBoolean(webpartProp, "showArticleAsCatalog");//是否像栏目那样展示文章--初使用：产品中心
        CatalogService catalogService = (CatalogService) ServiceLocator.getService(CatalogService.class);
        boolean showBrotherCatalog = MapHelper.getBoolean(webpartProp, "showBrotherCatalog");//展示同级的兄弟栏目--初使用：产品中心
        if (showBrotherCatalog)
        {
            Catalog parentCatalog = catalogService.getParent(catalogId);
            catalogId = parentCatalog.getId();
        }
        
        List<Catalog> catalogList = catalogService.findChildrenById(catalogId);
        catalogList = IterList(catalogList, showArticleAsCatalog);
        
        dataMap.put("catalogList", catalogList);
        
        return dataMap;
    }
    
    /**
     * 描述: IterList 迭代处理数据
     * 作者: 袁永君
     * 创建日期: 2016-3-7
     * 创建时间: 上午11:19:12
     * @param catalogList
     * @return
     */
    public List<Catalog> IterList(List<Catalog> catalogList, boolean showArticleAsCatalog)
    {
        for (Catalog catalog : catalogList)
        {
            /**
             * 处理栏目路径
             */
            String linkUrl = catalog.getLinkUrl();
            if (StringHelper.isNotEmpty(linkUrl))
            {
                catalog.setPublishPath(linkUrl);
            }
            else
            {
                String path = SysLibrary.getCatalogUrlPath(catalog.getId());
                catalog.setPublishPath(path);
            }
            
            int catalogId = catalog.getId();
            ArticleService articleService = (ArticleService) ServiceLocator.getService(ArticleService.class);
            
        }
        return catalogList;
    }
}
