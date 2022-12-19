package com.ch1ppy.springboot_back_exam.utils.exception;


import com.ch1ppy.springboot_back_exam.utils.result.ResponseCode;

/**
 * @author MrCh1ppy
 */
public class DataException extends BaseException{
	public DataException(String message) {
		super(message);
	}

	@Override
	ResponseCode getCode() {
		return ResponseCode.DATA_ERROR;
	}
}
