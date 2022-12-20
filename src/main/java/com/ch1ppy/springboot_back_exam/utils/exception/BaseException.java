package com.ch1ppy.springboot_back_exam.utils.exception;


import com.ch1ppy.springboot_back_exam.utils.result.ResponseCode;

/**
 * 基础错误类型，当作基类，实际不使用
 * 需要创建一个新的异常类型需要在result.ResponseCode 中添加一个新的错误码，然后创建一个类，参考BaseException与DataException
 *
 * @author MrCh1ppy
 */
public abstract class BaseException extends RuntimeException{
	protected BaseException(String message) {
		super(message);
	}
	/**
	 * Gets code.
	 *
	 * @return the code
	 */
	abstract ResponseCode getCode();
}
