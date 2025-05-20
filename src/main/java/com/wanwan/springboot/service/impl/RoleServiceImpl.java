package com.wanwan.springboot.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.wanwan.springboot.pojo.po.Menu;
import com.wanwan.springboot.pojo.po.Role;
import com.wanwan.springboot.pojo.po.RoleMenu;
import com.wanwan.springboot.mapper.RoleMapper;
import com.wanwan.springboot.mapper.RoleMenuMapper;
import com.wanwan.springboot.service.IMenuService;
import com.wanwan.springboot.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wanwan
 * @since 2024-02-15
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private IMenuService menuService;
    @Transactional
    @Override
    public void updateRoleMenu(Integer roleId, List<Integer> menuIds) {
        //先删除当前角色id所有的绑定关系
        // QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        // queryWrapper.eq("role_id",roleId);
        // roleMenuMapper.delete(queryWrapper);
        roleMenuMapper.deleteRMByRoleId(roleId);
        // 再把前端传过来的菜单id数组绑定到当前这个角色id上去
        List<Integer> menuIdsCopy = CollUtil.newArrayList(menuIds);
        for (Integer menuId : menuIds){
            Menu menu = menuService.getById(menuId);
            if(menu.getPid() != null && !menuIdsCopy.contains(menu.getPid())){
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menu.getPid());
                roleMenuMapper.insert(roleMenu);
                menuIdsCopy.add(menu.getPid());
            }
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuMapper.insert(roleMenu);
        }
    }

    @Override
    public boolean saveRole(Role role) {
        return save(role);
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.updateById(role);
    }

    @Override
    public List<Integer> listRoleMenu(Integer roleId) {
        return roleMenuMapper.selectRMByRoleId(roleId);
    }
}
