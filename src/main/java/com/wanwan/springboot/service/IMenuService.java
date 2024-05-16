package com.wanwan.springboot.service;

import com.wanwan.springboot.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wanwan
 * @since 2024-02-15
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> listMenu(String name);
}
