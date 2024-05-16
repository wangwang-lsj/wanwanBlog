package com.wanwan.springboot.service;

import com.wanwan.springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wanwan.springboot.entity.dto.UserDTO;
import com.wanwan.springboot.entity.dto.UserPasswordDTO;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wanwan
 * @since 2024-01-22
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDto);

    User register(UserDTO userDto);

    void updatePassword(UserPasswordDTO userPasswordDTO);

    Map<String,Object> pageUserByCondition(Integer pageNum, Integer pageSize, String username, String nickname, String address, String phone, String email);
}
