package com.wanwan.springboot.service;

import com.wanwan.springboot.pojo.po.Message;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
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
public interface IMessageService extends IService<Message> {


    Map<String,Object> pageMessage(Integer pageNum, Integer pageSize, String nickName, Integer enable);
    Map<String,Object> pageAbleMessage(Integer pageNum, Integer pageSize, String nickName, Integer enable);


    boolean saveMessage(Message message);

    boolean reply(Message message);

    boolean removeMessage(Integer id);

    boolean removeMessages(List<Integer> ids);

    boolean updateShow(Message message);

    Message prepareMessageForSave(Message message, HttpServletRequest request);
}
