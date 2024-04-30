package com.wanwan.springboot;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wanwan.springboot.common.enums.ResultCodeEnum;
import com.wanwan.springboot.entity.Menu;
import com.wanwan.springboot.entity.TestModel;
import com.wanwan.springboot.mapper.MenuMapper;
import com.wanwan.springboot.mapper.TestMapper;
import com.wanwan.springboot.mapper.UserMapper;
import com.wanwan.springboot.service.impl.MenuServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootApplicationTests {
    @Value("${spring.datasource.url}")
    private String url;
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        System.out.println(url);
    }
    @Autowired
    private MenuMapper menuMapper;
    @Test
    void contextLoads1() {
        System.out.println(menuMapper.selectAllBySortNum());
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
        System.out.println(userMapper.getOneAll("wanwan","admin"));
    }
}
