package com.wanwan.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanwan.springboot.entity.LeaveWord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wanwan
 * @since 2024-03-19
 */
@Mapper
public interface LeaveWordMapper extends BaseMapper<LeaveWord> {

    List<LeaveWord> getByPage(@Param("page") Page<LeaveWord> page,@Param("nickName") String nickName,@Param("enable")Integer enable);
}
