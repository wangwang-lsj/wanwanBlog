package com.wanwan.springboot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author 玩玩
 * @description
 * @since 2024/5/7  23:14
 */
@Slf4j
@Component
@Aspect
public class TestAop {
    @Pointcut(value = "execution(* com.wanwan.springboot.service.impl.UserServiceImpl.login(..))")
    public void loginPointCut() {

    }
    @Pointcut(value = "execution(* com.wanwan.springboot.controller.UserController.login(..))")
    public void loginPointCut1() {

    }
    @Around(value = "loginPointCut()")
    public Object loginAround(ProceedingJoinPoint pjp){
        log.info("环绕通知的目标方法名："+pjp.getSignature().getName());
        log.info("登陆业务前置通知");
        try {
            Object obj = pjp.proceed();
            log.info("登陆业务后置通知");
            return obj;
        } catch (Throwable e) {
            log.info("登陆业务异常通知");
            e.printStackTrace();
        }
        return null;

    }

}
