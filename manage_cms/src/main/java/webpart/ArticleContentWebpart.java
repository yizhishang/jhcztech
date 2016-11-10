package webpart;

import java.util.HashMap;
import java.util.Map;

import com.yizhishang.base.jdbc.DataRow;
import com.yizhishang.base.service.ServiceLocator;
import com.yizhishang.base.util.MapHelper;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.domain.Article;
import com.yizhishang.plat.service.ArticleService;
import com.yizhishang.plat.template.Context;
import com.yizhishang.plat.template.FMWebpartParser;

/**
 * 描述: ArticleContentWebpart.java
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-3-8
 * 创建时间: 下午7:06:24
 */
public class ArticleContentWebpart extends FMWebpartParser
{
    
    @Override
    public Map<String, Object> getModel(Context context, Map<String, Object> webpartProp)
    {
        int articleId = MapHelper.getInt(webpartProp, "articleId");
        String catalogId = MapHelper.getString(webpartProp, "catalogId");
        ArticleService articleService = (ArticleService) ServiceLocator.getService(ArticleService.class);
        Article article = articleService.findArticleById(articleId);
        
        Map<String, Object> dataMap = new HashMap<String, Object>();
        if (StringHelper.isNotEmpty(catalogId))
		{
        	DataRow row = articleService.findUpAndDown("" + articleId, catalogId);
        	dataMap.put("row", row);
		}
        
        dataMap.put("article", article);
        return dataMap;
    }
    
}
