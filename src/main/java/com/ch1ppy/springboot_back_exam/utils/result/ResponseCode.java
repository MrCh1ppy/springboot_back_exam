package com.ch1ppy.springboot_back_exam.utils.result;

/**
 * The enum Response code.
 *
 * @author MrCh1ppy
 */
public enum ResponseCode {
	/**
	 * 默认成功码
	 */
	SUCCESS(200),
	/**
	 * 服务器内部错误
	 */
	DEFAULT_ERROR(500),
	/**
	 * 数据异常
	 */
	DATA_ERROR(501)
	;

	/**
	 * The Code.
	 */
	final int code;

	ResponseCode(int code) {
		this.code = code;
	}

	/**
	 * Gets code.
	 *
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
}
