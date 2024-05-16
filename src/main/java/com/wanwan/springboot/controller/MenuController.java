package com.wanwan.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wanwan.springboot.common.Constants;
import com.wanwan.springboot.common.Result;
import com.wanwan.springboot.entity.Dict;
import com.wanwan.springboot.mapper.DictMapper;
import com.wanwan.springboot.mapper.MenuMapper;
import com.wanwan.springboot.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import com.wanwan.springboot.service.IMenuService;
import com.wanwan.springboot.entity.Menu;

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
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private DictMapper dictMapper;
    @Resource
    private UserServiceImpl userService;

    /**
     * 分页获取菜单列表(弃用）
     * @param pageNum
     * @param pageSize
     * @param name
     * @return Page
     */
    @GetMapping("/menus/page")
    public Result page(@RequestParam Integer pageNum,
                       @RequestParam Integer pageSize,
                       @RequestParam(defaultValue = "") String name
    ) {
        return Result.success();
    }

    /**
     * 获取所有菜单图标
     * @return List<Dict>
     */
    @GetMapping("/menus/icons")
    public Result getIcons() {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<Dict>();
        queryWrapper.eq("type", Constants.DICT_TYPE_ICON);
        return Result.success(dictMapper.selectList(queryWrapper));
    }

    /**
     * 根据菜单名名获取菜单(搜索菜单)
     * @param name
     * @return List<Menu>
     */
    @GetMapping("/menus")
    public Result getByName(@RequestParam(defaultValue = "") String name) {
        return Result.success(menuService.listMenu(name));
    }

    /**
     * 通过id获取菜单
     * @param id
     * @return Menu
     */
    @GetMapping("/menus/{id}")
    public Result getById(@PathVariable Integer id) {
        return Result.success(menuService.getById(id));
    }

    /**
     * 新增或更改菜单
     * @param menu
     * @return Boolean
     */
    // 新增或者更新
    @PostMapping("/menus")
    public Result saveOrUpdate(@RequestBody Menu menu) {
        return Result.success(menuService.saveOrUpdate(menu));
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

