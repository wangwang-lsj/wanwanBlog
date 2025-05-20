package com.wanwan.springboot.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanwan.springboot.common.Result;
import com.wanwan.springboot.annotation.AuthAccess;
import org.springframework.web.bind.annotation.*;

import com.wanwan.springboot.service.ICategoryService;
import com.wanwan.springboot.pojo.po.Category;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wanwan
 * @since 2024-03-07
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Resource
    private ICategoryService categoryService;

    @AuthAccess
    @GetMapping()
    public Result queryAll() {
        return Result.success(categoryService.list());
    }

    // @GetMapping("/{id}")
    // public Result queryById(@PathVariable Integer id) {
    //     return Result.success(categoryService.getById(id));
    // }

    @GetMapping ("/page")
    public Result queryPage(@RequestParam Integer pageNum,
                       @RequestParam Integer pageSize,
                       @RequestParam String name
                       ) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotBlank(name)) {
            queryWrapper.eq("name", name);
        }
        return Result.success(categoryService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }
    @PostMapping
    public Result create(@RequestBody Category category){
        return Result.success(categoryService.saveCategory(category));
    }
    // 新增或者更新
    @PutMapping
    public Result modify(@RequestBody Category category) {
        return Result.success(categoryService.updateCategory(category));
    }

    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        categoryService.removeById(id);
        return Result.success();
    }

    @DeleteMapping()
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        categoryService.removeByIds(ids);
        return Result.success();
    }



}

