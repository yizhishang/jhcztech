package webpart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jhcz.base.util.MapHelper;
import com.jhcz.business.other.service.AdManageService;
import com.jhcz.plat.template.Context;
import com.jhcz.plat.template.FMWebpartParser;

/**
 * 描述: 广告管理
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-3-8
 * 创建时间: 上午10:36:44
 */
public class AdWebpart extends FMWebpartParser
{
    
    @Override
    public Map<String, Object> getModel(Context context, Map<String, Object> webpartProp)
    {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        int groupNo = MapHelper.getInt(webpartProp, "groupNo");//栏目ID
        AdManageService service = new AdManageService();
        List<Object> adList = service.findAdsByGroupNo(groupNo);
        dataMap.put("groupNo", groupNo);
        dataMap.put("adList", adList);
        return dataMap;
    }
}
