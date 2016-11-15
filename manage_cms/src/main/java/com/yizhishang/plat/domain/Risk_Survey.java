package com.yizhishang.plat.domain;

import com.yizhishang.base.domain.DynaModel;

/**
 * 描述:
 * 版权:	 Copyright (c) 2007
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-4-1
 * 创建时间: 16:22:00
 */
public class Risk_Survey extends DynaModel
{

    public int getId()
    {
        return getInt("survey_id");
    }

    public void setId(int id)
    {
        set("survey_id", id);
    }

    public String getClientId()
    {
        return getString("client_id");
    }

    public void setClientId(String client_id)
    {
        set("client_id", client_id);
    }

    public int getBranchNum()
    {
        return getInt("branch_num");
    }

    public void setBranchNum(int branch_num)
    {
        set("branch_num", branch_num);
    }

    public String getField1()
    {
        return getString("field1");
    }

    public void setField1(String field1)
    {
        set("field1", field1);
    }

    public String getField2()
    {
        return getString("field2");
    }

    public void setField2(String field2)
    {
        set("field2", field2);
    }

    public String getField3()
    {
        return getString("field3");
    }

    public void setField3(String field3)
    {
        set("field3", field3);
    }

    public String getField4()
    {
        return getString("field4");
    }

    public void setField4(String field4)
    {
        set("field4", field4);
    }

    public String getField5()
    {
        return getString("field5");
    }

    public void setField5(String field5)
    {
        set("field5", field5);
    }

    public String getField6()
    {
        return getString("field6");
    }

    public void setField6(String field6)
    {
        set("field6", field6);
    }

    public String getField7()
    {
        return getString("field7");
    }

    public void setField7(String field7)
    {
        set("field7", field7);
    }

    public String getField8()
    {
        return getString("field8");
    }

    public void setField8(String field8)
    {
        set("field8", field8);
    }

    public String getField9()
    {
        return getString("field9");
    }

    public void setField9(String field9)
    {
        set("field9", field9);
    }

    public String getField10()
    {
        return getString("field10");
    }

    public void setField10(String field10)
    {
        set("field10", field10);
    }

    public String getResults()
    {
        return getString("results");
    }

    public void setResults(String results)
    {
        set("results", results);
    }

    public String getClientName()
    {
        return getString("client_name");
    }

    public void setClientName(String client_name)
    {
        set("client_name", client_name);
    }

    public String getClientType()
    {
        return getString("clienttype");
    }

    public void setClientType(String clienttype)
    {
        set("clienttype", clienttype);
    }

    public String getCreateBy()
    {
        return getString("create_by");
    }

    public void setCreateBy(String UID)
    {
        set("create_by", UID);
    }

    public String getCreateDate()
    {
        return getString("create_date");
    }

    public void setCreateDate(String date)
    {
        set("create_date", date);
    }

    public String getModifiedBy()
    {
        return getString("modified_by");
    }

    public void setModifiedBy(String UID)
    {
        set("modified_by", UID);
    }

    public String getModifiedDate()
    {
        return getString("modified_date");
    }

    public void setModifiedDate(String date)
    {
        set("modified_date", date);
    }

    public String getSiteNo()
    {
        return getString("siteno");
    }

    public void setSiteNo(String siteno)
    {
        set("siteno", siteno);
    }
}
