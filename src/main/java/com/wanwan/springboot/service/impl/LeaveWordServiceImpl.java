package com.wanwan.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanwan.springboot.annotation.RedisCache;
import com.wanwan.springboot.annotation.RedisUpdate;
import com.wanwan.springboot.common.Constants;
import com.wanwan.springboot.entity.LeaveWord;
import com.wanwan.springboot.mapper.LeaveWordMapper;
import com.wanwan.springboot.service.ILeaveWordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String,Object> pageLeaveWord(Integer pageNum,Integer pageSize, String nickName, Integer enable) {
        Map<String,Object> map = new HashMap<>();
        map.put("records", leaveWordMapper.selectLWPageByCondition(new Page<>(pageNum,pageSize),nickName,enable));
        map.put("total", count());
        return map;
    }

    // 太不合理了，后面改
    @RedisCache(key = Constants.LEAVEWORD_LEAVEWORD,isMap = true,isPage = true)
    @Override
    public Map<String, Object> pageAbleLeaveWord(Integer pageNum,Integer pageSize, String nickName, Integer enable) {
        QueryWrapper<LeaveWord> queryWrapper = new QueryWrapper<>();
        Map<String,Object> map = new HashMap<>();
        queryWrapper.eq("enable", enable);
        map.put("records", leaveWordMapper.selectLWPageByCondition(new Page<>(pageNum,pageSize),nickName,enable));
        map.put("total", count(queryWrapper));
        return map;
    }

    @RedisUpdate(keys = {Constants.LEAVEWORD},isPrefix = true)
    @Override
    public boolean saveLeaveWord(LeaveWord leaveWord) {
        return save(leaveWord);
    }

    @RedisUpdate(keys = {Constants.LEAVEWORD},isPrefix = true)
    @Override
    public boolean reply(LeaveWord leaveWord) {
        return saveOrUpdate(leaveWord);
    }

    @RedisUpdate(keys = {Constants.LEAVEWORD},isPrefix = true)
    @Override
    public boolean removeLeaveWord(Integer id) {
        return removeById(id);
    }

    @RedisUpdate(keys = {Constants.LEAVEWORD},isPrefix = true)
    @Override
    public boolean removeLeaveWords(List<Integer> ids) {
        return removeByIds(ids);
    }

    @RedisUpdate(keys = {Constants.LEAVEWORD},isPrefix = true)
    @Override
    public boolean updateShow(LeaveWord leaveWord) {
        UpdateWrapper<LeaveWord> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", leaveWord.getId());
        updateWrapper.set("enable", leaveWord.getEnable());
        return update(updateWrapper);
    }
}
