package com.ch1ppy.springboot_back_exam.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ch1ppy.springboot_back_exam.dao.UserMapper;
import com.ch1ppy.springboot_back_exam.pojo.bo.req.UserAddReq;
import com.ch1ppy.springboot_back_exam.pojo.bo.req.UserPageQueryReq;
import com.ch1ppy.springboot_back_exam.pojo.bo.req.UserUpdateReq;
import com.ch1ppy.springboot_back_exam.pojo.po.ProjectUser;
import com.ch1ppy.springboot_back_exam.pojo.vo.UserVo;
import com.ch1ppy.springboot_back_exam.service.IUserService;
import com.ch1ppy.springboot_back_exam.utils.exception.DataException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
		user.setIsDelete(Boolean.FALSE);
		int flag = userMapper.updateById(user);
		return flag == 1;
	}

	@Override
	public IPage<UserVo> selectUserInPage(UserPageQueryReq req) {
		//这部分是分页查询，查询出了结果
		IPage<ProjectUser> page = new LambdaQueryChainWrapper<>(userMapper)
				//第一个参数是代表条件生效的状态，即，当getIsDelete不为空时，后面两个参数相同
				.eq(req.getIsDelete() != null, ProjectUser::getIsDelete, req.getIsDelete())
				.like(StringUtils.hasText(req.getUsername()), ProjectUser::getUserName, req.getUsername())
				.page(req.page());
		//但是需要获取的是VO，还需二次加工,我们的思路是把page内部的数据加工之后再塞回去
		//对page的列表中的数据进行修改,如果转化内部比较复杂的，比如说需要远程调用或者查数据库的，推荐使用并行流
		//等价于遍历getRecords()之后的list，然后对每个元素调用UserVo的from方法，然后收集到一个list中
		List<UserVo> vos = page.getRecords().stream()
				.map(UserVo::from)
				.collect(Collectors.toList());
		//创建一个空页码,注意泛型
		IPage<UserVo> resPage = Page.of(0, 0);
		//因为泛型不一致，所以需要先将泛型冲突的list变为空list，emptyList与任意泛型list不冲突
		page.setRecords(Collections.emptyList());
		//将属性按照名字复制
		BeanUtils.copyProperties(page, resPage);
		//将数据压入
		resPage.setRecords(vos);
		//看上去操作很复杂，但是实际上对于数据的转化只局限在显示的那几个数据上，没有遍历整个数据集进行转化，所以反而快
		return resPage;
	}

}
