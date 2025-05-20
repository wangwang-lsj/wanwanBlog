package com.wanwan.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wanwan.springboot.common.Constants;
import com.wanwan.springboot.common.Result;
import com.wanwan.springboot.pojo.po.Dict;
import com.wanwan.springboot.mapper.DictMapper;
import com.wanwan.springboot.mapper.MenuMapper;
import com.wanwan.springboot.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import com.wanwan.springboot.service.IMenuService;
import com.wanwan.springboot.pojo.po.Menu;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author wanwan
 * @since 2024-02-15
 */
@RestController
@RequestMapping("/api")
public class MenuController {
    @Resource
    private IMenuService menuService;

    /**
     * 根据菜单名名获取菜单(搜索菜单)
     * @param name
     * @return List<Menu>
     */
    @GetMapping("/menus")
    public Result queryByName(@RequestParam(defaultValue = "") String name) {
        return Result.success(menuService.listMenu(name));
    }

    /**
     * 获取所有菜单图标
     * @return List<Dict>
     */
    @GetMapping("/menus/icons")
    public Result queryIcons() {

        return Result.success(menuService.listIcon());
    }
    /**
     * 新增菜单
     * @param menu
     * @return Boolean
     */
    // 新增或者更新
    @PostMapping("/menus")
    public Result create(@RequestBody Menu menu) {
        return Result.success(menuService.saveMenu(menu));
    }
    /**
     * 更改菜单
     * @param menu
     * @return Boolean
     */
    // 新增或者更新
    @PutMapping("/menus")
    public Result modify(@RequestBody Menu menu) {
        return Result.success(menuService.updateMenu(menu));
    }
    /**
     * 删除菜单
     * @param id
     * @return Boolean
     */
    @DeleteMapping("/menus/{id}")
    public Result deleteById(@PathVariable Integer id) {

        return Result.success(menuService.removeById(id));
    }

    /**
     * 删除菜单
     * @param ids
     * @return Boolean
     */
    @DeleteMapping("/menus")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(menuService.removeByIds(ids));
    }
}

