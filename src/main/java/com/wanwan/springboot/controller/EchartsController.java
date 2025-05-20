package com.wanwan.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.wanwan.springboot.common.Constants;
import com.wanwan.springboot.common.Result;
import com.wanwan.springboot.annotation.AuthAccess;
import com.wanwan.springboot.pojo.po.User;
import com.wanwan.springboot.service.IArticleService;
import com.wanwan.springboot.service.ICommentService;
import com.wanwan.springboot.service.IMessageService;
import com.wanwan.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author：玩玩
 * @since：2024/2/15 15:24
 * @description:
 */
@RestController
@RequestMapping("/api/echarts")
public class EchartsController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IArticleService articleService;
    @Autowired
    private ICommentService commentService;
    @Autowired
    private IMessageService leaveWordService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @AuthAccess
    @GetMapping("/statistics")
    public Result queryStatistics() {
        Map<String, Object> map = new HashMap<>();
        map.put("userCount",userService.count());
        map.put("articleCount",articleService.count());
        map.put("commentCount",commentService.count());
        map.put("leaveWordCount",leaveWordService.count());

        return Result.success(map);
    }
    @AuthAccess
    @GetMapping("/example")
    public Result example() {
        Map<String, Object> map = new HashMap<>();
        map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        map.put("y", CollUtil.newArrayList(150, 230, 224, 218, 135, 147, 260));
        return Result.success(map);
    }

    @AuthAccess
    @GetMapping("/members")
    public Result members() {
        String jsonStr = stringRedisTemplate.opsForValue().get(Constants.ECHARTS_DATA_KEY);
        List<User> list;
        if (StrUtil.isBlank(jsonStr)) {
            list = userService.list();
            stringRedisTemplate.opsForValue().set(Constants.ECHARTS_DATA_KEY, JSONUtil.toJsonStr(list));
        } else {
            list = JSONUtil.toBean(jsonStr, new TypeReference<List<User>>() {
            }, true);
        }
        int q1 = 0;
        int q2 = 0;
        int q3 = 0;
        int q4 = 0;
        for (User user : list) {
            Date createTime = user.getCreateTime();
            Quarter quarter = DateUtil.quarterEnum(createTime);
            switch (quarter) {
                case Q1:
                    q1 += 1;
                    break;
                case Q2:
                    q2 += 1;
                    break;
                case Q3:
                    q3 += 1;
                    break;
                case Q4:
                    q4 += 1;
                    break;
                default:
                    break;
            }
        }
        return Result.success(CollUtil.newArrayList(q1, q2, q3, q4));
    }

}
