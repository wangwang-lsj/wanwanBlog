package com.wanwan.springboot.service.impl;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanwan.springboot.annotation.RedisCache;
import com.wanwan.springboot.annotation.RedisUpdate;
import com.wanwan.springboot.common.Constants;
import com.wanwan.springboot.mapper.MessageMapper;
import com.wanwan.springboot.pojo.po.Message;
import com.wanwan.springboot.service.IMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanwan.springboot.utils.IpUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {
    @Resource
    private MessageMapper messageMapper;
    @Override
    public Map<String,Object> pageMessage(Integer pageNum, Integer pageSize, String nickName, Integer enable) {
        Map<String,Object> map = new HashMap<>();
        map.put("records", messageMapper.selectMessagePageByCondition(new Page<>(pageNum,pageSize),nickName,enable));
        map.put("total", count());
        return map;
    }

    // 太不合理了，后面改
    @RedisCache(key = Constants.LEAVEWORD_LEAVEWORD,isMap = true,isPage = true)
    @Override
    public Map<String, Object> pageAbleMessage(Integer pageNum, Integer pageSize, String nickName, Integer enable) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        Map<String,Object> map = new HashMap<>();
        queryWrapper.eq("enable", enable);
        map.put("records", messageMapper.selectMessagePageByCondition(new Page<>(pageNum,pageSize),nickName,enable));
        map.put("total", count(queryWrapper));
        return map;
    }

    @RedisUpdate(keys = {Constants.LEAVEWORD},isPrefix = true)
    @Override
    public boolean saveMessage(Message message) {
        return save(message);
    }

    @RedisUpdate(keys = {Constants.LEAVEWORD},isPrefix = true)
    @Override
    public boolean reply(Message message) {
        return saveOrUpdate(message);
    }

    @RedisUpdate(keys = {Constants.LEAVEWORD},isPrefix = true)
    @Override
    public boolean removeMessage(Integer id) {
        return removeById(id);
    }

    @RedisUpdate(keys = {Constants.LEAVEWORD},isPrefix = true)
    @Override
    public boolean removeMessages(List<Integer> ids) {
        return removeByIds(ids);
    }

    @RedisUpdate(keys = {Constants.LEAVEWORD},isPrefix = true)
    @Override
    public boolean updateShow(Message message) {
        UpdateWrapper<Message> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", message.getId());
        updateWrapper.set("enable", message.getEnable());
        return update(updateWrapper);
    }
    public Message prepareMessageForSave(Message message,HttpServletRequest request) {
        String uaStr = request.getHeader("User-Agent");
        Map<String,String> ipInfo = IpUtil.getIp2region(IpUtil.getIp(request));
        UserAgent ua = UserAgentUtil.parse(uaStr);
        message.setIp(IpUtil.getIp(request));
        if (ipInfo != null) {
            message.setNation(ipInfo.get("nation"));
            message.setProvince(ipInfo.get("province"));
            message.setCity(ipInfo.get("city"));
            message.setIsp(ipInfo.get("isp"));
        }
        message.setBrowser(ua.getBrowser().toString());
        message.setBrowserVersion(ua.getVersion());
        message.setEngine(ua.getEngine().toString());
        message.setEngineVersion(ua.getEngineVersion());
        message.setOs(ua.getOs().toString());
        message.setPlatform(ua.getPlatform().toString());
        message.setIsMobile(ua.isMobile());
        return message;
    }
}
