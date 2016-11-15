package com.yizhishang.business.domain;

import com.yizhishang.base.domain.DynaModel;

/**
 * 描        述: 预约信息
 * 版权所有: Copyright (c) 2012
 * 公        司: 285206405@qq.com
 * 作        者: 袁永君
 * 版        本: 1.0
 * 创建日期: 2015-11-14
 * 创建时间: 下午02:15:31
 */
public class PlanningOrder extends DynaModel
{

    public int getId()
    {
        return getInt("order_id");
    }

    public void setId(int id)
    {
        set("order_id", id);
    }

    public int getClientId()
    {
        return getInt("client_id");
    }

    public void setClientId(int id)
    {
        set("client_id", id);
    }

    public String getName()
    {
        return getString("name");
    }

    public void setName(String name)
    {
        set("name", name);
    }

    public int getType()
    {
        return getInt("type");
    }

    public void setType(int type)
    {
        set("type", type);
    }

    public String getProvince()
    {
        return getString("province");
    }

    public void setProvince(String province)
    {
        set("province", province);
    }

    public String getCity()
    {
        return getString("city");
    }

    public void setCity(String city)
    {
        set("city", city);
    }

    public String getPhone()
    {
        return getString("phone");
    }

    public void setPhone(String phone)
    {
        set("phone", phone);
    }

    public String getMobile()
    {
        return getString("mobile");
    }

    public void setMobile(String mobile)
    {
        set("mobile", mobile);
    }

    public String getEmail()
    {
        return getString("email");
    }

    public void setEmail(String email)
    {
        set("email", email);
    }

    public String getMessage()
    {
        return getString("message");
    }

    public void setMessage(String message)
    {
        set("message", message);
    }

    public String getCreateDate()
    {
        return getString("create_date");
    }

    public void setCreateDate(String createDate)
    {
        set("create_Date", createDate);
    }

    public String getUpdateDate()
    {
        return getString("update_date");
    }

    public void setUpdateDate(String updateDate)
    {
        set("update_Date", updateDate);
    }

    public String getUpdateBy()
    {
        return getString("update_by");
    }

    public void setUpdateBy(String updateBy)
    {
        set("update_by", updateBy);
    }

    public String getSiteNo()
    {
        return getString("siteno");
    }

    public void setSiteNo(String siteNo)
    {
        set("siteno", siteNo);
    }

}
