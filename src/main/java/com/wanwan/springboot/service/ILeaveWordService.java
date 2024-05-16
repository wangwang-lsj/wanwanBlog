package com.wanwan.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanwan.springboot.entity.LeaveWord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wanwan
 * @since 2024-03-19
 */
public interface ILeaveWordService extends IService<LeaveWord> {


    Map<String,Object> pageLeaveWord(Integer pageNum,Integer pageSize,String nickName, Integer enable);
    Map<String,Object> pageAbleLeaveWord(Integer pageNum,Integer pageSize, String nickName, Integer enable);


    boolean saveLeaveWord(LeaveWord leaveWord);

    boolean reply(LeaveWord leaveWord);

    boolean removeLeaveWord(Integer id);

    boolean removeLeaveWords(List<Integer> ids);

    boolean updateShow(LeaveWord leaveWord);
}
