package com.ch1ppy.springboot_back_exam.pojo.vo;

import lombok.Data;

/**
 * @author MrCh1ppy
 */
@Data
public class UserVo {
	/**
	 * 主键，基本上使用自增，分布式情况下考虑雪花算法
	 */
	private Integer id;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 使用摘要算法处理后的密码，切忌使用密码原本存
	 */
	private String passwordMd5;
}
