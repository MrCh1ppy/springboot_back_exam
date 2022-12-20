package com.ch1ppy.springboot_back_exam.pojo.bo.req;

import lombok.Data;

/**
 * @author MrCh1ppy
 */
@Data
public class UserPageQueryReq implements PageQueryReq {
	int pageSize;
	int pageNum;
	String username;
	Boolean isDelete;
}
