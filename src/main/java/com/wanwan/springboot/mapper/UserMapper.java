package com.wanwan.springboot.mapper;

import com.wanwan.springboot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanwan.springboot.entity.dto.UserPasswordDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wanwan
 * @since 2024-01-22
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Update("update user set password = #{newPassword} where username = #{username} and password = #{password}")
    int updatePassword(UserPasswordDTO userPasswordDTO);

    @Select("select user.*,role.flag as role from user left join role on user.role_id=role.id where username = #{username} and password = #{password}")
    User getOneAll(@Param("username") String username,@Param("password") String password);
}
