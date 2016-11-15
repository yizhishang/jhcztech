package com.yizhishang.plat.domain;

public class EnumItem
{

    private String enum_type;

    private String enum_value;

    private String enum_name;

    private String item_name;

    private String item_value;

    private int is_system;

    private int orderline;

    private String item_code;

    private String siteno;

    public String getEnum_type()
    {
        return enum_type;
    }

    public void setEnum_type(String enum_type)
    {
        this.enum_type = enum_type;
    }

    public String getEnum_value()
    {
        return enum_value;
    }

    public void setEnum_value(String enum_value)
    {
        this.enum_value = enum_value;
    }

    public String getEnum_name()
    {
        return enum_name;
    }

    public void setEnum_name(String enum_name)
    {
        this.enum_name = enum_name;
    }

    public String getItem_name()
    {
        return item_name;
    }

    public void setItem_name(String item_name)
    {
        this.item_name = item_name;
    }

    public String getItem_value()
    {
        return item_value;
    }

    public void setItem_value(String item_value)
    {
        this.item_value = item_value;
    }

    public int getIs_system()
    {
        return is_system;
    }

    public void setIs_system(int is_system)
    {
        this.is_system = is_system;
    }

    public int getOrderline()
    {
        return orderline;
    }

    public void setOrderline(int orderline)
    {
        this.orderline = orderline;
    }

    public String getItem_code()
    {
        return item_code;
    }

    public void setItem_code(String item_code)
    {
        this.item_code = item_code;
    }

    public String getSiteno()
    {
        return siteno;
    }

    public void setSiteno(String siteno)
    {
        this.siteno = siteno;
    }

    @Override
    public String toString()
    {
        return "EnumItem [enum_type=" + enum_type + ", enum_value=" + enum_value + ", enum_name=" + enum_name + ", " +
                "item_name=" + item_name + ", item_value=" + item_value + ", is_system=" + is_system + ", orderLine="
                + orderline + ", item_code=" + item_code + ", siteno=" + siteno + "]";
    }

}
