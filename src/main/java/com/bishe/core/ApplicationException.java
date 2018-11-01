package com.bishe.core;

import java.text.MessageFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
public class ApplicationException extends RuntimeException {

	/**
	 * Logger
	 */
	protected final Log log = LogFactory.getLog(ApplicationException.class);
	
	/**
	 * The default error code.
	 */
	public static final String UNKNOW_ERROR = "UNKNOW_ERROR";

	/**
	 * 兼容纯错误信息，不含error code,errorArgs的情况.
	 */
	private final String errorMessage;

	/**
	 * @see RuntimeException#RuntimeException()
	 */
	public ApplicationException() {
		super();
		errorMessage = null;
	}

	/**
	 * @see RuntimeException#RuntimeException(String)
	 */
	public ApplicationException(final String msgPattern, final Object... args) {
		this.errorMessage = MessageFormat.format(msgPattern, args);
	}

	/**
	 * @see RuntimeException#RuntimeException(Throwable)
	 */
	public ApplicationException(Throwable cause) {
		super(cause);
		this.errorMessage = null;
	}

	/**
	 * 获得出错信息.
	 * 
	 * @return 格式化后的错误信息
	 */
	public final String getMessage() {
		return errorMessage;
	}

}
