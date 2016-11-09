package com.jhcz.plat.domain;

import com.jhcz.base.domain.DynaModel;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-18
 * 创建时间: 10:36:01
 */
public class User extends DynaModel
{
    public static int STATE_CLOSE = 0;
    public static int STATE_OPEN = 1;

    public int getId()
    {
        return getInt("id");
    }

    public void setId(int id)
    {
        set("id", id);
    }

    public String getUid()
    {
        return getString("uid2");
    }

    public void setUid(String uid)
    {
        set("uid2", uid);
    }

    public String getSiteNo()
    {
        return getString("siteno");
    }

    public void setSiteNo(String siteNo)
    {
        set("siteno", siteNo);
    }

    public String getName()
    {
        return getString("name");
    }

    public void setName(String name)
    {
        set("name", name);
    }

    public String getPassword()
    {
        return getString("password");
    }

    public void setPassword(String password)
    {
        set("password", password);
    }

    public int getState()
    {
        return getInt("state");
    }

    public void setState(int state)
    {
        set("state", state);
    }

    public int getIsSystem()
    {
        return getInt("is_system");
    }

    public void setIsSystem(int system)
    {
        set("is_system", system);
    }

    public int getLoginTimes()
    {
        return getInt("login_times");
    }

    public void setLoginTimes(int loginTimes)
    {
        set("login_times", loginTimes);
    }

    public String getLastTime()
    {
        return getString("last_time");
    }

    public void setLastTime(String lastTime)
    {
        set("last_time", lastTime);
    }

    public String getEmail()
    {
        return getString("email");
    }

    public void setEmail(String eMail)
    {
        set("email", eMail);
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

    public String getCreateBy()
    {
        return getString("create_by");
    }

    public void setCreateBy(String createBy)
    {
        set("create_by", createBy);
    }

    public String getCreateDate()
    {
        return getString("create_date");
    }

    public void setCreateDate(String createDate)
    {
        set("create_date", createDate);
    }

    public String getModifiedBy()
    {
        return getString("modified_by");
    }

    public void setModifiedBy(String modifiedBy)
    {
        set("modified_by", modifiedBy);
    }

    public String getModifiedDate()
    {
        return getString("modified_date");
    }

    public void setModifiedDate(String modifiedDate)
    {
        set("modified_date", modifiedDate);
    }

    public String getBranchNo()
    {
        return getString("branchno");
    }

    public void setBranchNo(String branchNo)
    {
        set("branchno", branchNo);
    }

    public String getSiteName()
    {
        return getString("sitename");
    }

    public void setSiteName(String siteName)
    {
        set("sitename", siteName);
    }
}
