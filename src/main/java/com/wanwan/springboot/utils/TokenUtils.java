package com.wanwan.springboot.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.wanwan.springboot.entity.User;
import com.wanwan.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author：玩玩
 * @since：2024/2/2 11:00
 * @description:
 */
@Component
public class TokenUtils {
    private static IUserService staticUserService;
    @Resource
    private IUserService userService;

    @PostConstruct
    public void setUserService(){
        staticUserService = userService;
    }

    /**
     * 生成token
     * @param userId
     * @param sign
     * @return
     */
    public static String genToken(String userId, String sign){
        return  JWT.create()
                //签收者
                .withAudience(userId)
                //主题
                .withSubject("token")
                //24小时候token过期
                .withExpiresAt(DateUtil.offsetHour(new Date(),24))
                //以password作为token的密钥
                .sign(Algorithm.HMAC256(sign));
    }

    /**
     * 获取当前登录的用户信息
     * @return
     */
    public static User getCurrentUser(){
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if(StrUtil.isNotBlank(token)) {
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserService.getById(Integer.valueOf(userId));
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }
}
