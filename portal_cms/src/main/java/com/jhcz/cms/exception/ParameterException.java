package com.jhcz.cms.exception;


/**
 * 描述: 运行异常
 * 版权: Copyright (c) 2016
 * 公司: 285206405@qq.com
 * 作者: 袁永君
 * 版本: 1.0
 * 创建日期: 2016-2-29
 * 创建时间: 上午10:40:43
 */
public class ParameterException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public ParameterException()
	{
		super();
	}
	
	public ParameterException(String msg)
	{
		super(msg);
	}
	
	public ParameterException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public ParameterException(Throwable cause)
	{
		super(cause);
	}
}
