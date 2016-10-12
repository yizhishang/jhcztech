package webpart;

import java.util.HashMap;
import java.util.Map;

import com.jhcz.base.service.ServiceLocator;
import com.jhcz.base.util.MapHelper;
import com.jhcz.plat.domain.Article;
import com.jhcz.plat.service.ArticleService;
import com.jhcz.plat.template.Context;
import com.jhcz.plat.template.FMWebpartParser;

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
        ArticleService articleService = (ArticleService) ServiceLocator.getService(ArticleService.class);
        Article article = articleService.findArticleById(articleId);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("article", article);
        return dataMap;
    }
    
}
