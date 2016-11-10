package webpart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yizhishang.base.config.SysConfig;
import com.yizhishang.base.jdbc.DataRow;
import com.yizhishang.base.service.ServiceLocator;
import com.yizhishang.base.util.MapHelper;
import com.yizhishang.plat.service.ArticleService;
import com.yizhishang.plat.template.Context;
import com.yizhishang.plat.template.FMWebpartParser;

public class ArticleListWebpart extends FMWebpartParser
{
    
    @Override
    public Map<String, Object> getModel(Context context, Map<String, Object> webpartProp)
    {
        int catalogId = MapHelper.getInt(webpartProp, "catalogId");//栏目ID
        int rows = MapHelper.getInt(webpartProp, "rows");//栏目ID
        rows = rows == 0 ? SysConfig.getRowOfPage() : rows;
        ArticleService articleService = (ArticleService) ServiceLocator.getService(ArticleService.class);

        int sortType = MapHelper.getInt(webpartProp, "sortType");//排序方式
        List<DataRow> articleList = articleService.findPublishArticleById(catalogId, rows, sortType);
        
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("catalogId", catalogId);
        dataMap.put("articleList", articleList);
        return dataMap;
    }
}
