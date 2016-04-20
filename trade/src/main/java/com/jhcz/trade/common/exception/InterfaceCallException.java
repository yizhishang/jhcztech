package com.jhcz.trade.common.exception;

/**
 * @类名: InterfaceCallException
 * @包名 com.jhcz.trade.common.exception
 * @描述: 接口调用异常
 * @版权: Copyright (c) 2016 
 * @公司:深圳市金恒创智信息技术有限公司
 * @作者 zhonghang
 * @时间 2016-4-5 上午9:16:23
 * @版本 V1.0
 */
public class InterfaceCallException extends RuntimeException{
	private String exceptionCode;
	private String exceptionMessage;
	
	
	public InterfaceCallException(String exceptionCode,String exceptionMessage){
		super("系统接口调用异常，异常信息为: " + exceptionMessage);
		this.exceptionCode = exceptionCode;
		this.exceptionMessage = exceptionMessage;
	}
	
	
	
	public String getExceptionCode() {
		return exceptionCode;
	}
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
}
