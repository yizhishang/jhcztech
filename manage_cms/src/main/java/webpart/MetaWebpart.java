package webpart;

import java.util.HashMap;
import java.util.Map;

import com.yizhishang.base.domain.DynaModel;
import com.yizhishang.base.util.MapHelper;
import com.yizhishang.base.util.StringHelper;
import com.yizhishang.plat.domain.Catalog;
import com.yizhishang.plat.template.Context;
import com.yizhishang.plat.template.FMWebpartParser;

/**
 * 描述:  网站头部
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-14
 * 创建时间: 下午03:16:20
 */
public class MetaWebpart extends FMWebpartParser
{

    /**
     * @描述：
     * @作者：袁永君
     * @时间：2015-11-14 下午03:50:49
     * @param catalogId
     * @param siteNo
     * @return
     */
    private DynaModel getCatalogSeo(int catalogId, String siteNo)
    {
        DynaModel dataRow = seoService.findSeoByCatalogid(catalogId, siteNo);
        if (dataRow != null)
        {
            return dataRow;
        }
        else
        {
            Catalog catalog = catalogService.getParent(catalogId);
            if (catalog == null) //已经找到了最顶层，还是没有找到，直接返回空
            {
                return null;
            }
            else
            {
                return getCatalogSeo(catalog.getId(), siteNo);
            }
        }
    }
    
    @Override
    public Map<String, Object> getModel(Context context, Map<String, Object> webpartProp)
    {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        int catalogId = MapHelper.getInt(webpartProp, "catalogId");//栏目ID
        String siteNo = MapHelper.getString(webpartProp, "siteNo");//站点编号，默认为主站点
        if (StringHelper.isEmpty(siteNo))
        {
            siteNo = "main";
        }
        DynaModel seoData = getCatalogSeo(catalogId, siteNo);
        seoData = (seoData == null) ? new DynaModel() : seoData;
        dataMap.put("data", seoData);
        dataMap.put("catalogId", catalogId);
        return dataMap;
    }
}
