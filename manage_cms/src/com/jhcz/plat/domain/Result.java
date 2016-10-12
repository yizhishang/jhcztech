package com.jhcz.plat.domain;

import java.io.Serializable;

/**
 * 描述: 结果集对象Result.java
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-1-5
 * 创建时间: 上午3:32:03
 */
public class Result implements Serializable
{
    
    private static final long serialVersionUID = 1L;
    
    private int errorNo;
    
    private String errorInfo;
    
    private Object obj;
    
    public Result()
    {
        this.errorNo = 0;
    }
    
    public Result(int ErrorNo)
    {
        this.errorNo = -1;
    }
    
    public Result(int errorNo, String errorInfo)
    {
        this.errorNo = errorNo;
        this.errorInfo = errorInfo;
    }
    
    public int getErrorNo()
    {
        return errorNo;
    }
    
    public void setErrorNo(int errorNo)
    {
        this.errorNo = errorNo;
    }
    
    public String getErrorInfo()
    {
        return errorInfo;
    }
    
    public void setErrorInfo(String errorInfo)
    {
        this.errorInfo = errorInfo;
    }
    
    public Object getObj()
    {
        return obj;
    }
    
    public void setObj(Object obj)
    {
        this.obj = obj;
    }
    
    @Override
    public String toString()
    {
        return "Result [errorNo=" + errorNo + ", errorInfo=" + errorInfo + ", obj=" + obj + "]";
    }
    
}
