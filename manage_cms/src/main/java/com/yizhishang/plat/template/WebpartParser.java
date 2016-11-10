package com.yizhishang.plat.template;

import java.util.Map;

/**
 * 描述:
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2009-12-24
 * 创建时间: 16:00:57
 */
public interface WebpartParser
{
    
    /**
     * 对部件进行解析，得到对应部件的HTML
     * @param context     解析上下文参数
     * @param webpartProp 部件的属性
     * @param viewStr     部件的视图字串
     * @return
     */
    public String parse(Context context, Map<String, Object> webpartProp, String viewStr);
}
