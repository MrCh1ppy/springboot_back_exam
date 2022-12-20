package com.ch1ppy.springboot_back_exam.pojo.bo.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author MrCh1ppy
 */
@Data
public class UserPageQueryReq implements PageQueryReq {
	@NotNull
	int pageSize;
	@NotNull
	int pageNum;
	String username;
	Boolean isDelete;
}
