package com.wanwan.springboot.config.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.exceptions.*;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wanwan.springboot.common.Constants;
import com.wanwan.springboot.common.enums.ResultCodeEnum;
import com.wanwan.springboot.annotation.AuthAccess;
import com.wanwan.springboot.pojo.po.User;
import com.wanwan.springboot.exception.ServiceException;
import com.wanwan.springboot.service.IUserService;
import com.wanwan.springboot.utils.RedisUtil;
import com.wanwan.springboot.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author：玩玩
 * @since：2024/2/2 11:44
 * @description:
 */
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }else {
            HandlerMethod h = (HandlerMethod) handler;
            AuthAccess authAccess = h.getMethodAnnotation(AuthAccess.class);
            if (authAccess != null) {
                return true;
            }
        }

        // 判断token是否为空
        if (StrUtil.isBlank(token)){
            throw new ServiceException(ResultCodeEnum.TOKEN_EMPTY_ERROR);
        }
        // 验证token
        try {
            DecodedJWT decodedJWT = JWTUtils.getToken(token);
            String userId = decodedJWT.getClaim("userId").asString();
            User user;
            if(RedisUtil.hasKey(Constants.USER_ID+userId)){
                user = RedisUtil.get(Constants.USER_ID+userId,User.class);
            }else{
                user = userService.getById(userId);
                RedisUtil.put(Constants.USER_ID+userId,user,24,TimeUnit.HOURS);
            }
            // 根据token中的userid查询数据库
            if (user == null){
                throw new ServiceException(ResultCodeEnum.USER_NO_EXIT_ERROR);
            }
            return true;
        }catch (SignatureVerificationException e) {
            e.printStackTrace();
            throw new ServiceException(ResultCodeEnum.TOKEN_SIGNATURE_ERROR);
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            throw new ServiceException(ResultCodeEnum.TOKEN_EXPIRED_ERROR);
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            throw new ServiceException(ResultCodeEnum.TOKEN_ALGORITHM_ERROR);
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            throw new ServiceException(ResultCodeEnum.TOKEN_INVALID_ERROR);
        }


    }


}
