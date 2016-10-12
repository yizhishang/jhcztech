package webpart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jhcz.base.config.SysConfig;
import com.jhcz.base.service.ServiceLocator;
import com.jhcz.base.util.MapHelper;
import com.jhcz.plat.service.ArticleService;
import com.jhcz.plat.template.Context;
import com.jhcz.plat.template.FMWebpartParser;

public class ArticleListWebpart extends FMWebpartParser
{
    
    @Override
    public Map<String, Object> getModel(Context context, Map<String, Object> webpartProp)
    {
        int catalogId = MapHelper.getInt(webpartProp, "catalogId");//栏目ID
        int rows = MapHelper.getInt(webpartProp, "rows");//栏目ID
        rows = rows == 0 ? SysConfig.getRowOfPage() : rows;
        ArticleService articleService = (ArticleService) ServiceLocator.getService(ArticleService.class);
        List<Object> articleList = articleService.findPublishArticleById(catalogId, rows);
        
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("catalogId", catalogId);
        dataMap.put("articleList", articleList);
        return dataMap;
    }
}
