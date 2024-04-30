package com.wanwan.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanwan.springboot.entity.LeaveWord;
import com.wanwan.springboot.mapper.LeaveWordMapper;
import com.wanwan.springboot.service.ILeaveWordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wanwan
 * @since 2024-03-19
 */
@Service
public class LeaveWordServiceImpl extends ServiceImpl<LeaveWordMapper, LeaveWord> implements ILeaveWordService {
    @Resource
    private LeaveWordMapper leaveWordMapper;

    @Override
    public List<LeaveWord> getByPage(Page<LeaveWord> page,String nickName,Integer enable) {
        return leaveWordMapper.getByPage(page,nickName,enable);
    }
}
