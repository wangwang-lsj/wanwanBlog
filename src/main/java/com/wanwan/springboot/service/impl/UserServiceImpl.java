package com.wanwan.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wanwan.springboot.common.Constants;
import com.wanwan.springboot.common.enums.ResultCodeEnum;
import com.wanwan.springboot.entity.Menu;
import com.wanwan.springboot.entity.User;
import com.wanwan.springboot.entity.dto.UserDTO;
import com.wanwan.springboot.entity.dto.UserPasswordDTO;
import com.wanwan.springboot.exception.ServiceException;
import com.wanwan.springboot.mapper.RoleMapper;
import com.wanwan.springboot.mapper.RoleMenuMapper;
import com.wanwan.springboot.mapper.UserMapper;
import com.wanwan.springboot.service.IMenuService;
import com.wanwan.springboot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanwan.springboot.utils.JWTUtils;
import com.wanwan.springboot.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wanwan
 * @since 2024-01-22
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private static final Log LOG = Log.get();
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private IMenuService menuService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public UserDTO login(UserDTO userDTO) {
        log.info("登录业务执行");
        User one = getUserInfo(userDTO);
        if(one != null){
            // 刷新上次登陆时间
            one.setRecentlyLanded(DateUtil.now());
            updateById(one);

            BeanUtil.copyProperties(one,userDTO,true);
            Map<String,String> map = new HashMap<>();
            map.put("userId",one.getId().toString());
            String token = JWTUtils.genToken(map);
            userDTO.setToken(token);
            userDTO.setPassword(null);
            String role = one.getRole();
            List<Menu> roleMenus = getRoleMenus(role);
            userDTO.setMenus(roleMenus);
            return userDTO;
        }else{
            throw new ServiceException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }
    }

    @Override
    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if(one == null){
            one = new User();
            BeanUtil.copyProperties(userDTO, one ,true);
            save(one);
            stringRedisTemplate.delete(Constants.USER_KEY);
        }else {
            throw new ServiceException(ResultCodeEnum.USER_EXIT_ERROR);
        }
        return one;
    }

    @Override
    public Map<String, Object> findByPageOrSearch(Integer pageNum, Integer pageSize, String username, String nickname, String address, String phone, String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StrUtil.isNotBlank(username)||StrUtil.isNotBlank(nickname)||StrUtil.isNotBlank(address)||StrUtil.isNotBlank(phone)||StrUtil.isNotBlank(email)){
            RedisUtil.delete(Constants.USER_KEY);
        }
        if(!"".equals(username)){
            queryWrapper.like("username",username);
        }
        if(!"".equals(nickname)){
            queryWrapper.like("nickname",nickname);
        }
        if(!"".equals(address)){
            queryWrapper.like("address",address);
        }
        if(!"".equals(phone)){
            queryWrapper.like("phone",phone);
        }
        if(!"".equals(email)){
            queryWrapper.like("email",email);
        }
        queryWrapper.orderByDesc("id");
        List<User> list;
        if(RedisUtil.hasKey(Constants.USER_KEY)){
             list = RedisUtil.get(Constants.USER_KEY,new TypeReference<List<User>>(){});
        }else {
            list = list(queryWrapper);
            RedisUtil.put(Constants.USER_KEY,list,24, TimeUnit.HOURS);
        }
        Map<String,Object> dataMap = new HashMap<>();
        // codeUseList：处理后的所有符合条件的数据（list）
        // 组装返回结果对象 list：当前页数据列表 total：数据总数
        dataMap.put("records", list.stream().skip((long) (pageNum - 1) * pageSize)
                .limit(pageSize).collect(Collectors.toList()));
        dataMap.put("total", list.size());
        return dataMap;
    }

    @Override
    public void updatePassword(UserPasswordDTO userPasswordDTO) {
        int update = userMapper.updatePassword(userPasswordDTO);
        if (update < 1) {
            throw new ServiceException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }
    }

    public User getUserInfo(UserDTO userDTO){
        User one = null;
        try {
            one = userMapper.getOneAll(userDTO.getUsername(),userDTO.getPassword());
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(ResultCodeEnum.SYSTEM_ERROR);
        }
        return one;
    }

    /**
     * 获取当前角色的菜单列表
     * @param role
     * @return
     */
    public List<Menu> getRoleMenus(String role){
        Integer roleId = roleMapper.selectByFlag(role);

        List<Integer> menuIds = roleMenuMapper.selectByRoleId(roleId);
        // 查出所有菜单
        List<Menu> menus = menuService.selectMenus("");
        List<Menu> roleMenus = new ArrayList<>();
        // 筛选当前用户菜单
        for(Menu menu: menus){

            if(menuIds.contains(menu.getId())){
                roleMenus.add(menu);
            }
            List<Menu> children = menu.getChildren();
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }
        return roleMenus;
    }
}
