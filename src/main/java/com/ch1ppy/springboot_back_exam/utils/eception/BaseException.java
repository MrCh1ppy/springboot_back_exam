package com.ch1ppy.springboot_back_exam.utils.eception;


import com.ch1ppy.springboot_back_exam.utils.result.ResponseCode;

/**
 * The interface Exception interface.
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
