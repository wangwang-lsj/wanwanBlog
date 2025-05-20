package com.wanwan.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanwan.springboot.pojo.po.Message;
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
public interface MessageMapper extends BaseMapper<Message> {

    List<Message> selectMessagePageByCondition(@Param("page") Page<Message> page, @Param("nickName") String nickName, @Param("enable")Integer enable);
}
