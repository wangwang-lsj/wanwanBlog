package com.wanwan.springboot.service;

import com.wanwan.springboot.pojo.po.Role;
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
public interface IRoleService extends IService<Role> {

    void updateRoleMenu(Integer roleId, List<Integer> menuIds);

    List<Integer> listRoleMenu(Integer roleId);
    boolean saveRole(Role role);

    int updateRole(Role role);

}
