package com.jhcz.base.jdbc.exception;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-4
 * 创建时间: 21:34:26
 */
public class JdbcException extends RuntimeException
{
    
    private static final long serialVersionUID = 1L;

    public JdbcException()
    {
        super();
    }


    public JdbcException(String message)
    {
        super(message);
    }


    public JdbcException(String message, Throwable cause)
    {
        super(message, cause);
    }


    public JdbcException(Throwable cause)
    {
        super(cause);
    }
}