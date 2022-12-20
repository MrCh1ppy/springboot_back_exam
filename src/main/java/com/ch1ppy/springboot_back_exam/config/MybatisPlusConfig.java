package com.ch1ppy.springboot_back_exam.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Mybatis plus config.
 *
 * @author MrCh1ppy
 */
@Configuration
public class MybatisPlusConfig {
	/**
	 *添加乐观锁插件
	 *
	 * @return the mybatis plus interceptor
	 */
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		//乐观锁
		interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
		//分页插件
		PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
		paginationInnerInterceptor.setDbType(DbType.MYSQL);
		paginationInnerInterceptor.setOverflow(true);
		paginationInnerInterceptor.setOptimizeJoin(true);
		interceptor.addInnerInterceptor(paginationInnerInterceptor);
		return interceptor;
	}
}
