package com.wanwan.springboot.service.impl;

import com.wanwan.springboot.pojo.po.Category;
import com.wanwan.springboot.mapper.CategoryMapper;
import com.wanwan.springboot.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wanwan
 * @since 2024-03-07
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    @Override
    public boolean saveCategory(Category category) {
        return save(category);
    }

    @Override
    public int updateCategory(Category category) {
        return categoryMapper.updateById(category);
    }
}
