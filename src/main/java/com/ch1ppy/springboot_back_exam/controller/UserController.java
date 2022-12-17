package com.ch1ppy.springboot_back_exam.controller;

import com.ch1ppy.springboot_back_exam.pojo.bo.req.UserAddReq;
import com.ch1ppy.springboot_back_exam.service.IUserService;
import com.ch1ppy.springboot_back_exam.utils.result.ResponseCode;
import com.ch1ppy.springboot_back_exam.utils.result.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;


/**
 * @author MrCh1ppy
 * 控制器，是前端实际请求的地方
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/exam/api/user")
public class UserController {
	private final IUserService userService;
	@PostMapping("/add")
	public Result<String> addUser(@Valid @RequestBody UserAddReq req){
		Optional<Integer> idOpt = userService.addUser(req);
		if (!idOpt.isPresent()){
			return Result.ok(null);
		}
		return Result.fail(ResponseCode.DEFAULT_ERROR,"插入用户失败");
	}

}
