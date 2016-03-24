package com.jhcz.cms.exception;


/**
 * 描述: 业务异常
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-2-29
 * 创建时间: 上午10:40:30
 */
public class BusinessException extends Exception
{
	
	private static final long serialVersionUID = 1L;

	public BusinessException()
	{
		super();
	}
	
	public BusinessException(String message)
    {
        super(message);
    }
	
	public BusinessException(String message1, String message2)
	{
		super(message1);
	}

    public BusinessException(String message, Throwable cause)
    {
        super(message, cause);
    }


    public BusinessException(Throwable cause)
    {
        super(cause);
    }
}
