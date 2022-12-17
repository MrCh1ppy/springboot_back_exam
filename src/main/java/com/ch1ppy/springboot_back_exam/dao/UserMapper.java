package com.ch1ppy.springboot_back_exam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ch1ppy.springboot_back_exam.pojo.po.ProjectUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author MrCh1ppy
 * 数据操作层
 * baseMapper提供了一些最基础的SQL实现，一般简单的查找与修改，删除，插入都使用默认实现，复杂的查询自己写SQL
 */
@Mapper
public interface UserMapper extends BaseMapper<ProjectUser> {
}
