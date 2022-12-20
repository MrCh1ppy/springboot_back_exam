package com.ch1ppy.springboot_back_exam.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author MrCh1ppy
 * 添加的 @Data注解来自lombok，会自动生成getter与setter
 * Po类，一般与数据库中的表直接绑定，一般来说字段名称尽量与数据库的属性一致
 *数据库中的蛇形会直接转化为java的小驼峰
 */
@Data
@AllArgsConstructor
@TableName("t_user")
public class ProjectUser {
	/**
	 * 主键，基本上使用自增，分布式情况下考虑雪花算法
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;

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
	@Version
	private Integer version;
}

