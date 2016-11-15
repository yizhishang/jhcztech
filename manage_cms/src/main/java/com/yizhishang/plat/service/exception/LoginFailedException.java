package com.yizhishang.plat.service.exception;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-30
 * 创建时间: 11:36:16
 */
public class LoginFailedException extends BaseException
{

    private static final long serialVersionUID = 1L;

    public LoginFailedException()
    {
        super();
    }

    public LoginFailedException(String message)
    {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public LoginFailedException(Throwable cause)
    {
        super(cause);
    }
}
