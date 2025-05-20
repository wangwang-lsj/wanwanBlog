package com.wanwan.springboot.service;

import com.wanwan.springboot.pojo.po.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wanwan
 * @since 2024-03-07
 */
public interface ICategoryService extends IService<Category> {

    boolean saveCategory(Category category);

    int updateCategory(Category category);
}
