package com.ch1ppy.springboot_back_exam.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ch1ppy.springboot_back_exam.pojo.bo.req.UserAddReq;
import com.ch1ppy.springboot_back_exam.pojo.bo.req.UserPageQueryReq;
import com.ch1ppy.springboot_back_exam.pojo.bo.req.UserUpdateReq;
import com.ch1ppy.springboot_back_exam.pojo.vo.UserVo;
import com.ch1ppy.springboot_back_exam.service.IUserService;
import com.ch1ppy.springboot_back_exam.utils.result.ResponseCode;
import com.ch1ppy.springboot_back_exam.utils.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;


/**
 * @author MrCh1ppy
 * 控制器，是前端实际请求的地方
 * get与post的选择：初学者记住，需要一个参数的基本都使用Get，然后把参数放在url上面，否则使用post，构造一个类使用requestbody请求
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/exam/api/user")
@Api
public class UserController {
	private final IUserService userService;

	@PostMapping("/add")
	@ApiOperation("添加用户")
	public Result<String> add(@Valid @RequestBody UserAddReq req){
		Optional<Integer> idOpt = userService.add(req);
		if (!idOpt.isPresent()){
			return Result.fail(ResponseCode.DEFAULT_ERROR,"插入用户失败");
		}
		return Result.ok(null);
	}

	@PostMapping("/update")
	@ApiOperation("更新用户")
	public Result<String> update(@Valid @RequestBody UserUpdateReq req){
		boolean flag = userService.update(req);
		if (!flag){
			return Result.fail(ResponseCode.DEFAULT_ERROR,"更新用户失败");
		}
		return Result.ok(null);
	}

	@GetMapping("/delete/true/{id}")
	@ApiOperation("删除用户")
	public Result<String>deleteTrue(@NotNull @PathVariable("id")Integer id){
		boolean flag = userService.realDelete(id);
		if (!flag){
			return Result.fail(ResponseCode.DEFAULT_ERROR,"删除用户失败");
		}
		return Result.ok(null);
	}

	@GetMapping("/delete/fake/{id}")
	@ApiOperation("假删用户")
	public Result<String> deleteFake(@NotNull @PathVariable("id") Integer id) {
		boolean flag = userService.fakeDelete(id);
		if (!flag) {
			return Result.fail(ResponseCode.DEFAULT_ERROR, "删除用户失败");
		}
		return Result.ok(null);
	}

	@PostMapping("/query/page")
	@ApiOperation("分页查询用户")
	public Result<IPage<UserVo>> selectUserInPage(@Valid @RequestBody UserPageQueryReq req) {
		IPage<UserVo> page = userService.selectUserInPage(req);
		return Result.ok(page);
	}
}
