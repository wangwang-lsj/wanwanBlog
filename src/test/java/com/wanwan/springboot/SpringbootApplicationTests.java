package com.wanwan.springboot;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanwan.springboot.common.Constants;
import com.wanwan.springboot.common.enums.ResultCodeEnum;
import com.wanwan.springboot.pojo.po.Menu;
import com.wanwan.springboot.pojo.po.TestModel;
import com.wanwan.springboot.mapper.*;
import com.wanwan.springboot.service.impl.MessageServiceImpl;
import com.wanwan.springboot.service.impl.MenuServiceImpl;
import com.wanwan.springboot.utils.IpUtil;
import com.wanwan.springboot.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootTest
class SpringbootApplicationTests {
    @Value("${spring.datasource.url}")
    private String url;
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private MessageServiceImpl leaveWordService;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JavaMailSender javaMailSender;

    @Test
    void contextLoads() {
        System.out.println(url);
    }
    @Autowired
    private MenuMapper menuMapper;
    @Test
    void contextLoads1() {
        System.out.println(menuMapper.selectMenu());
    }
    @Autowired
    private MenuServiceImpl menuService;
    private QueryWrapper<Menu> queryWrapper;
    @Test
    void contextLoads2() {
        queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort_num");
        System.out.println(menuService.list(queryWrapper));
    }
    @Test
    void contextLoads3() {
        System.out.println(ResultCodeEnum.SUCCESS.code);
    }
    @Test
    void testInsert(){
        TestModel test = new TestModel();
        test.setName("罗书江");
        test.setAge("22");
        testMapper.insert(test);

    }
    @Test
    void testUpdate(){
        TestModel test = new TestModel();
        test.setId(1706754049);
        test.setName("罗书江");
        test.setAge("12");
        testMapper.updateById(test);
    }
    @Test
    void testSelect(){
        System.out.println(userMapper.selectUserAllByUN("wanwan","admin"));
    }
    @Test
    void testUpdateCommentLike(){
        commentMapper.updateCommentLikeById(69,-1);
    }
    @Test
    void testRedisTemplate(){
        redisTemplate.opsForValue().set("name","罗书江");
    }
    @Test
    void testStringRedisTemplate(){
        Object obj = stringRedisTemplate.opsForValue().get("USER_KEY");
        log.info("obj:"+obj.toString());
        log.info("list:"+JSON.parseArray(obj.toString()));
        // stringRedisTemplate.opsForValue().set("name","罗书江");
    }
    @Test
    void testSRTMap() throws JsonProcessingException {
        Map<String,Object> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        // map.put("records", objectMapper.writeValueAsString(leaveWordMapper.selectList(new QueryWrapper<>())));
        // map.put("total", String.valueOf(leaveWordService.count()));
        // RedisUtil.hPutAll(Constants.LEAVEWORD_LEAVEWORDS,map);

        // map.put("records", leaveWordMapper.selectList(new QueryWrapper<>()).toString());
        // map.put("total", String.valueOf(leaveWordService.count()));
        // RedisUtil.hPutAll("test",map);

        // map.put("records", leaveWordMapper.selectList(new QueryWrapper<>()));
        // map.put("total", leaveWordService.count());
        // RedisUtil.put("test",map);

        // map = RedisUtil.hGetAll(Constants.LEAVEWORD_LEAVEWORDS,new TypeReference<Object>(){});
        // Object records = map.get("records");
        // Object total = map.get("total");
        // Long total1 = JSON.parseObject(total.toString(),Long.class);
        // List<Message> leaveWords = JSON.parseArray(records.toString(),Message.class);
        // for (Message leaveWord : leaveWords){
        //     System.out.println(leaveWord.getContent());
        // }

        // HashMap<String, Object> hashMap = RedisUtil.get("test", new TypeReference<HashMap<String, Object>>() {});
        // Object records = hashMap.get("records");
        // for (Message record : records){
        //
        // }
        // RedisUtil.expire(Constants.LEAVEWORD_LEAVEWORDS,24, TimeUnit.HOURS);
        // RedisUtil.hPutAll(Constants.LEAVEWORD_LEAVEWORDS,map);
        Long num = RedisUtil.deletePrefix(Constants.LEAVEWORD);
        log.info(String.valueOf(num));

    }
    @Test
    public void testDeleteByMap(){
        Map<String,Object> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(12);
        list.add(21);
        map.put("age",list);
        // testMapper.deleteBa(map);
    }
    @Test
    public void testGetIpInfo(){
        // Map<String,String> ipInfo = IpUtil.getIp2region("223.146.223.145");
        Map<String,String> ipInfo = IpUtil.getIp2region("120.229.85.160");
        System.out.println(ipInfo.get("nation")+ipInfo.get("province")+ipInfo.get("city")+ipInfo.get("isp"));
    }
}
