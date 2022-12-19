package com.ch1ppy.springboot_back_exam.utils.exception;

import com.ch1ppy.springboot_back_exam.utils.result.ResponseCode;
import com.ch1ppy.springboot_back_exam.utils.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author MrCh1ppy
 */
@RestControllerAdvice
@Slf4j
public class GlobeExceptionHandler {
	private String getErrorPosition(Exception e){
		if(e.getStackTrace().length>0){
			final StackTraceElement element = e.getStackTrace()[0];
			String fileName = element.getFileName() == null ? "error file not found" : element.getFileName();
			int lineNumber = element.getLineNumber();
			return fileName+":"+lineNumber;
		}
		return "";
	}

	private void loggerOutPut(String exceptionName,String exceptionPosition){
		String text = "/////ERROR/////" + "\nlocation"+exceptionPosition+"\nname"+exceptionName;
		log.error(text);
	}

	private Result<String> defaultHandler(Exception e, String msg, ResponseCode responseCode){
		String position = getErrorPosition(e);
		e.printStackTrace();
		loggerOutPut(msg,position);
		return Result.error(responseCode,msg);
	}

	@ExceptionHandler(value = Exception.class)
	Result<String> exceptionHandler(Exception e){
		String errorMsg = e.getMessage() != null ? e.getMessage() : "未知错误";
		return defaultHandler(e,errorMsg,ResponseCode.DEFAULT_ERROR);
	}

	@ExceptionHandler(value = DefaultException.class)
	Result<String> baseExceptionHandler(BaseException e){
		return defaultHandler(e,e.getMessage(),ResponseCode.DEFAULT_ERROR);
	}

	@ExceptionHandler(value = DataException.class)
	Result<String> dataExceptionHandler(DataException e){
		return defaultHandler(e,e.getMessage(),ResponseCode.DATA_ERROR);
	}
}
