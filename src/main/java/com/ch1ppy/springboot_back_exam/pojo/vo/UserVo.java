package com.ch1ppy.springboot_back_exam.pojo.vo;

import com.ch1ppy.springboot_back_exam.pojo.po.ProjectUser;
import lombok.Data;
import org.springframework.beans.BeanUtils;

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

	private Boolean isDelete;

	public static UserVo from(ProjectUser user) {
		UserVo vo = new UserVo();
		/*因为字段类型一致所以可以直接复制，等价于：
		vo.setId(user.getId());
		vo.setUserName(user.getUserName());
		vo.setPasswordMd5(user.getPasswordMd5());*/
		BeanUtils.copyProperties(user, vo);
		return vo;
	}
}
