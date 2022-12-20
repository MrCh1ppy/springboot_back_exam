package com.ch1ppy.springboot_back_exam.pojo.bo.req;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 分页请求接口
 * 一般来说分页请求需要实现这个接口，实现了这个
 * 你需要请求类里面加入pageSize与pageNum属性，并设置了@Data，直接实现他们
 * 因为lombok会提供Getter的实现，所以会直接实现这个接口
 *
 * @author MrCh1ppy
 */
public interface PageQueryReq {
	/**
	 * 获取页大小
	 *
	 * @return the int
	 */
	int getPageSize();

	/**
	 * 获取页码.
	 *
	 * @return the int
	 */
	int getPageNum();

	/**
	 * java多态的一点小trick，一个接口如果实现了上面两者就可以直接使用他们创建一个Page
	 *
	 * @return the page
	 */
	default <T> IPage<T> page() {
		return new Page<>(getPageNum(), getPageSize());
	}
}
