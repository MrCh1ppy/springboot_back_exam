package com.ch1ppy.springboot_back_exam.service;

import com.ch1ppy.springboot_back_exam.pojo.bo.req.UserAddReq;
import com.ch1ppy.springboot_back_exam.pojo.bo.req.UserUpdateReq;

import java.util.Optional;

/**
 * The interface User service.
 *
 * @author MrCh1ppy
 */
public interface IUserService {

	/**
	 * Add optional.
	 *
	 * @param req the req
	 * @return the optional
	 */
	Optional<Integer> add(UserAddReq req);

	/**
	 * Update boolean.
	 *
	 * @param req the req
	 * @return the boolean
	 */
	boolean update(UserUpdateReq req);

	/**
	 * Real delete boolean.
	 *
	 * @param id the id
	 * @return the boolean
	 */
	boolean realDelete(Integer id);

	/**
	 * Fake delete boolean.
	 *
	 * @param id the id
	 * @return the boolean
	 */
	boolean fakeDelete(Integer id);
}
