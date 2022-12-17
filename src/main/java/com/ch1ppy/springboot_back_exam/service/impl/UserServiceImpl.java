package com.ch1ppy.springboot_back_exam.service.impl;

import com.ch1ppy.springboot_back_exam.dao.UserMapper;
import com.ch1ppy.springboot_back_exam.pojo.bo.req.UserAddReq;
import com.ch1ppy.springboot_back_exam.pojo.po.ProjectUser;
import com.ch1ppy.springboot_back_exam.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author MrCh1ppy
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
	private final UserMapper userMapper;

	@Override
	public Optional<Integer> addUser(UserAddReq req) {
		ProjectUser user = new ProjectUser(
				null,
				req.getUserName(),
				req.getPasswordMd5(),
				req.getIsDelete()
		);
		//mybatis plus插入时，如果插入成功会把成功插入的主键id放到entity中。这也是作为判别其是否插入成功的方法
		userMapper.insert(user);
		if (user.getId()==null){
			return Optional.empty();
		}
		return Optional.of(user.getId());
	}
}
