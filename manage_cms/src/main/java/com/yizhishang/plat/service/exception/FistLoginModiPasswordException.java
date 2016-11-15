package com.yizhishang.plat.service.exception;

/**
 * 描述:
 * 版权:	 Copyright (c) 2009
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2011-1-19
 * 创建时间: 下午01:25:47
 */
public class FistLoginModiPasswordException extends BaseException
{

    private static final long serialVersionUID = 1L;

    public FistLoginModiPasswordException()
    {
        super();
    }

    public FistLoginModiPasswordException(String message)
    {
        super(message);
    }

    public FistLoginModiPasswordException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public FistLoginModiPasswordException(Throwable cause)
    {
        super(cause);
    }
}
