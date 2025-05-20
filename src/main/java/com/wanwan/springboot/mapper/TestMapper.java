package com.wanwan.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wanwan.springboot.pojo.po.TestModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author：玩玩
 * @since：2024/3/9 20:12
 * @description:
 */
@Mapper
public interface TestMapper extends BaseMapper<TestModel> {

}
