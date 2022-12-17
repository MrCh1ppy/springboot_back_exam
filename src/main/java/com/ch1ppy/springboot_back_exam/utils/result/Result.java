package com.ch1ppy.springboot_back_exam.utils.result;

import lombok.Getter;

/**
 * @author MrCh1ppy
 */
@Getter
public final class Result<T> {
	private final T data;
	private final String msg;
	private final int code;

	public Result(T data, String msg, int code) {
		this.data = data;
		this.msg = msg;
		this.code = code;
	}


	/**
	 * get
	 *
	 * @param code 返回码
	 * @param data 数据
	 * @param msg 信息
	 * @return {@link Result}
	 * @see Result
	 * @see T
	 * 一般不使用，作为基本方法
	 */
	private static <T>Result<T> get(ResponseCode code, T data, String msg){
		return new Result<>(data, msg, code.getCode());
	}

	/**
	 * ok
	 *返回成功的数据
	 * @param data 数据
	 * @return {@link Result}
	 * @see Result
	 * @see T
	 */
	public static <T>Result<T> ok(T data){
		return get(ResponseCode.SUCCESS,data,"success");
	}

	/**
	 * ok
	 * 返回成功的数据
	 * @param data data
	 * @param msg msg
	 * @return {@link Result}
	 * @see Result
	 * @see T
	 */
	public static <T>Result<T> ok(T data,String msg){
		return get(ResponseCode.SUCCESS,data,msg);
	}

	/**
	 * fail
	 *
	 * @param code 返回码
	 * @param msg 附带消息
	 * @return {@link Result}
	 * @see Result
	 * 返回失败的数据
	 */
	public static Result<String> error(ResponseCode code,String msg){
		return get(code,null,msg);
	}


	/**
	 * fail
	 *基础错误类型
	 * @param code code
	 * @param msg msg
	 * @return {@link Result}
	 * @see Result
	 * @see String
	 */
	public static <T>Result<T> fail(ResponseCode code, String msg){
		return get(code,null,msg);
	}
}
