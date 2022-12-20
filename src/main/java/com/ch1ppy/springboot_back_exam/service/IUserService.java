package com.ch1ppy.springboot_back_exam.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ch1ppy.springboot_back_exam.pojo.bo.req.UserAddReq;
import com.ch1ppy.springboot_back_exam.pojo.bo.req.UserPageQueryReq;
import com.ch1ppy.springboot_back_exam.pojo.bo.req.UserUpdateReq;
import com.ch1ppy.springboot_back_exam.pojo.vo.UserVo;

import java.util.Optional;

/**
 * The interface User service.
 *
 * @author MrCh1ppy
 */
public interface IUserService {

	/**
	 * 添加数据的例子
	 *
	 * @param req the req
	 * @return 一般根据前端要求 ，返回或者不返回插入数据的id
	 */
	Optional<Integer> add(UserAddReq req);

	/**
	 * 更新数据的例子
	 *
	 * @param req the req
	 * @return the boolean
	 */
	boolean update(UserUpdateReq req);

	/**
	 * 真删数据的例子
	 *
	 * @param id the id
	 * @return the boolean
	 */
	boolean realDelete(Integer id);

	/**
	 * 假删数据的例子
	 *
	 * @param id the id
	 * @return the boolean
	 */
	boolean fakeDelete(Integer id);

	/**
	 * 数据查询例子
	 *
	 * @param req the req
	 * @return the page
	 */
	Page<UserVo> selectUserInPage(UserPageQueryReq req);
}
