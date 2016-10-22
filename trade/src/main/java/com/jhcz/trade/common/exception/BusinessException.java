package com.jhcz.trade.common.exception;
/**
 * 
 * 
 * @Description：业务异常类
 * @author 邱文豪
 * @created 2016年3月29日 下午3:19:03
 */
public class BusinessException extends RuntimeException {

	private String exceptionCode;
	private String exceptionMessage;

	public BusinessException(String exceptionCode, String exceptionMessage) {
		super("业务异常 " + exceptionMessage);

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

	private static final long serialVersionUID = 4424495805166382805L;
}
