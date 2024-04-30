package com.wanwan.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanwan.springboot.entity.LeaveWord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wanwan
 * @since 2024-03-19
 */
public interface ILeaveWordService extends IService<LeaveWord> {


    List<LeaveWord> getByPage(Page<LeaveWord> page,String nickName,Integer enable);
}
