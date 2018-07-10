package cn.wolfcode.wms.util;

/**
 *@author WangZhe
 *@time	  2018年6月20日下午9:56:11
 *@mail   wzzst310@163.com
 *@desp
 */
public class LogicException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public LogicException() {
		super();
	}
	public LogicException(String message) {
		super(message);
	}
	public LogicException(String message,Throwable cause) {
		super(message, cause);
	}
}
