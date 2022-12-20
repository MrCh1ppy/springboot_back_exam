package com.ch1ppy.springboot_back_exam.pojo.bo.req;

import lombok.Data;

/**
 * @author MrCh1ppy
 */
@Data
public class UserAddReq {
	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 使用摘要算法处理后的密码，切忌使用密码原本存
	 */
	private String passwordMd5;

	/**
	 * 一般使用假删除，通过修改一个flag位代表是否删除，0代表否，1代表是
	 */
	private Boolean isDelete;
}
