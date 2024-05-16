package com.wanwan.springboot.mapper;

import com.wanwan.springboot.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wanwan
 * @since 2024-02-15
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select id from role where flag = #{flag}")
    Integer selectRoleIdByFlag(@Param("flag") String role);
}
