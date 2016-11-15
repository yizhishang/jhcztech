package com.yizhishang.plat.service.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 描述:
 * 版权:	 Copyright (c) 2015
 * 公司:	 285206405@qq.com
 * 作者:	 袁永君
 * 版本:	 1.0
 * 创建日期: 2015-12-24
 * 创建时间: 16:01:38
 */
public class BaseException extends Exception
{

    private static final long serialVersionUID = 1L;

    public BaseException()
    {
        super();
    }

    public BaseException(String message)
    {
        super(message);
    }

    public BaseException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public BaseException(Throwable cause)
    {
        super(cause);
    }

    @Override
    public void printStackTrace()
    {
        printStackTrace(System.err);
    }

    @Override
    public void printStackTrace(PrintStream outStream)
    {
        printStackTrace(new PrintWriter(outStream));
    }

    @Override
    public void printStackTrace(PrintWriter writer)
    {
        super.printStackTrace(writer);

        if (getCause() != null) {
            getCause().printStackTrace(writer);
        }
        writer.flush();
    }
}
