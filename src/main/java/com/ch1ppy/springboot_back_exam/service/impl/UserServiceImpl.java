package com.ch1ppy.springboot_back_exam.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.ch1ppy.springboot_back_exam.dao.UserMapper;
import com.ch1ppy.springboot_back_exam.pojo.bo.req.UserAddReq;
import com.ch1ppy.springboot_back_exam.pojo.bo.req.UserUpdateReq;
import com.ch1ppy.springboot_back_exam.pojo.po.ProjectUser;
import com.ch1ppy.springboot_back_exam.service.IUserService;
import com.ch1ppy.springboot_back_exam.utils.exception.DataException;
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
	public Optional<Integer> add(UserAddReq req) {
		//使用全参构造是一个比较好的习惯
		ProjectUser user = new ProjectUser(
				null,
				req.getUserName(),
				req.getPasswordMd5(),
				req.getIsDelete(),
				null
		);
		//mybatis plus插入时，如果插入成功会把成功插入的主键id放到entity中。这也是作为判别其是否插入成功的方法
		userMapper.insert(user);
		return Optional.ofNullable(user.getId());
	}

	@Override
	public boolean update(UserUpdateReq req) {
		ProjectUser user = new ProjectUser(
				req.getId(),
				req.getUserName(),
				req.getPasswordMd5(),
				req.getIsDelete(),
				null
		);
		//一般情况下， mybatis plus这种默认返回值会返回数据库中修改的条数
		int num = userMapper.updateById(user);
		return num == 1;
	}

	@Override
	public boolean realDelete(Integer id) {
		int num = userMapper.deleteById(id);
		return num==1;
	}

	@Override
	public boolean fakeDelete(Integer id) {
		//这个Query是MP提供的工具， 通过放入可以查询的Mapper进行查询，在单表查询的时候很好用
		//原理是在Po中定义时已经通过注解将实体类与数据库的表链接，对实体类的操作可以映射为SQL
		Optional<ProjectUser> userOpt = new LambdaQueryChainWrapper<>(userMapper)
				.eq(ProjectUser::getId, id)
				//寻找还未被删除的数据然后进行假删
				.eq(ProjectUser::getIsDelete, 0)
				//可能为null的数据都使用Optional进行包裹，防止万恶的NullPointerException
				.oneOpt();
		//如果为空，没查到则抛出异常，异常会被全局异常处理器(com.ch1ppy.springboot_back_exam.utils.exception)自动捕获，
		ProjectUser user = userOpt.orElseThrow(() -> new DataException("待删除数据不存在"));
		//通过修改来进行假删
		user.setIsDelete(1);
		int flag = userMapper.updateById(user);
		return flag==1;
	}

}
