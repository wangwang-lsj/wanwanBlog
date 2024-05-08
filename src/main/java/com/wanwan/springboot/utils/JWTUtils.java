package com.wanwan.springboot.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wanwan.springboot.entity.User;
import com.wanwan.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author：玩玩
 * @since：2024/2/2 11:00
 * @description:
 */
@Component
public class JWTUtils {
    private static String SIGNATURE;
    private static int EXPIRE;
    private static IUserService staticUserService;
    @Resource
    private IUserService userService;
    @Value("${jwt.signature}")
    public void setSIGNATURE(String SIGNATURE) {
        JWTUtils.SIGNATURE = SIGNATURE;
    }

    @Value("${jwt.expire}")
    public void setEXPIRE(int EXPIRE) {
        JWTUtils.EXPIRE = EXPIRE;
    }
    @PostConstruct
    public void setUserService(){
        staticUserService = userService;
    }

    /**
     * 生成token
     * @param map
     * @return
     */
    public static String genToken(Map<String,String> map){

        JWTCreator.Builder builder = JWT.create();
        //签收者
        builder.withAudience("wanwan");
        //主题
        builder.withSubject("token");
        //设置payload
        map.forEach(builder::withClaim);
        //24小时候token过期
        builder.withExpiresAt(DateUtil.offsetHour(new Date(),EXPIRE));
        return builder.sign(Algorithm.HMAC256(SIGNATURE));
    }

    /**
     * 验证token
     * @param token
     */
    public static void verify(String token){
        JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
    }

    /**
     * 获取token中payload
     * @param token
     * @return
     */
    public static DecodedJWT getToken(String token){
        return JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
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
