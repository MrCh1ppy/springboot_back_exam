package com.ch1ppy.springboot_back_exam.utils.exception;


import com.ch1ppy.springboot_back_exam.utils.result.ResponseCode;

/**
 * @author MrCh1ppy
 */
public class DefaultException extends BaseException{


	public DefaultException(String message) {
		super(message);
	}

	@Override
	ResponseCode getCode() {
		return ResponseCode.DEFAULT_ERROR;
	}
}
