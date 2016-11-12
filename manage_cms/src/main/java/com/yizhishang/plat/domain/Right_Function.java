package com.yizhishang.plat.domain;

import com.yizhishang.base.domain.DynaModel;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-11-23
 * 创建时间: 15:49:02
 */
public class Right_Function extends DynaModel
{
	
    public String getName()
    {
        return getString("name");
    }

    public void setName(String name)
    {
        set("name", name);
    }

    public void setEname(String ename)
    {
        set("ename", ename);
    }

    public String getEname()
    {
        return getString("ename");
    }


    public void setFunction_code(String function_code)
    {
        set("function_code", function_code);
    }

    public String getFunction_code()
    {
        return getString("function_code");
    }

    public void setModule_code(String module_code)
    {
        set("module_code", module_code);
    }

    public String getModule_code()
    {
        return getString("module_code");
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
