package com.wanwan.springboot.controller;

import cn.hutool.core.util.RandomUtil;
import com.wanwan.springboot.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author 玩玩
 * @description
 * @since 2024/8/15  20:00
 */
@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${spring.mail.nickname}")
    private String nickname;
    @GetMapping("/code")
    public String getCode(@RequestParam("email") String email) {

        // 创建一个邮件
        SimpleMailMessage message = new SimpleMailMessage();

        // 设置发件人
        message.setFrom(nickname + '<' + sender + '>');

        // 设置收件人
        message.setTo(email);

        // 设置邮件主题
        message.setSubject("欢迎访问" + nickname);

        //生成六位随机数
        String code = RandomUtil.randomNumbers(6);


        //将验证码存入redis，有效期为5分钟
        // redisTemplate.opsForValue().set("email_code_" + email, code, 300000, TimeUnit.MILLISECONDS);
        RedisUtil.put("email_code:" + email, code, 1,TimeUnit.MINUTES);

        String content = "【验证码】您的验证码为：" + code + " 。 验证码六十秒内有效，逾期作废。\n\n\n" +
                "------------------------------\n\n\n";

        message.setText(content);

        // 发送邮件
        javaMailSender.send(message);

        return "发送成功";

    }


}
