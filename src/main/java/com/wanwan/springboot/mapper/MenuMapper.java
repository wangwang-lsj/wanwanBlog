package com.wanwan.springboot.mapper;

import com.wanwan.springboot.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wanwan
 * @since 2024-02-15
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    @Select("SELECT id,name,path,icon,description,sort_num,pid,page_path FROM menu ORDER BY sort_num ASC")
    List<Menu> selectMenu();

}
