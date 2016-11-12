package com.yizhishang.base.domain;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-23
 * 创建时间: 15:52:00
 */
public class Right_Url extends DynaModel
{
    public String getRight_url()
    {
        return getString("right_url");
    }

     public void setRight_url(String right_url)
    {
        set("right_url", right_url);
    }


     public String getFunction_code_list()
    {
        return getString("function_code_list");
    }

     public void setFunction_code_list(String function_code_list)
    {
        set("function_code_list", function_code_list);
    }

    public void setSiteno(String siteno)
    {
        set("siteno", siteno);
    }

    public String getSiteno()
    {
        return getString("siteno");
    }
}
