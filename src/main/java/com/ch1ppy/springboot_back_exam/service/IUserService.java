package com.ch1ppy.springboot_back_exam.service;

import com.ch1ppy.springboot_back_exam.pojo.bo.req.UserAddReq;

import java.util.Optional;

/**
 * @author MrCh1ppy
 */
public interface IUserService {

	Optional<Integer> addUser(UserAddReq req);
}
