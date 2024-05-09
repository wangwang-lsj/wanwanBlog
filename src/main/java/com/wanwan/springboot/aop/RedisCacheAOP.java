package com.wanwan.springboot.aop;

import com.alibaba.fastjson.TypeReference;
import com.wanwan.springboot.entity.Article;
import com.wanwan.springboot.entity.Slideshow;
import com.wanwan.springboot.service.ISlideshowService;
import com.wanwan.springboot.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 玩玩
 * @description
 * @since 2024/5/8  18:04
 */
@Aspect
@Component
@Slf4j
public class RedisCacheAOP {
    @Resource
    ISlideshowService slideshowService;

    @Pointcut(value = "execution(* com.wanwan.springboot.service.impl.SlideshowServiceImpl.*(..))")
    public void sliderPointCut(){

    }
    @Pointcut(value = "execution(* com.wanwan.springboot.service.impl.ArticleServiceImpl.getHomeArticle())")
    public void homeArticlesPointCut(){

    }
    @Around("sliderPointCut()")
    public Object sliderAround(ProceedingJoinPoint pjp){
        if(pjp.getSignature().getName().equals("sliderList")){
            Object obj = null;
            try {
                obj = pjp.proceed();
            } catch (Throwable e) {
                e.printStackTrace();
            }
            return obj;
        }
        Class<?> returnType = ((MethodSignature) pjp.getSignature()).getReturnType();
        log.info("方法返回类型："+ returnType.getSimpleName());
        switch (returnType.getSimpleName()){
            case "List":
                log.info("缓存业务名："+pjp.getSignature().getName());
                if(RedisUtil.hasKey("home:slider")){
                    log.info("home:slider缓存命中");
                    return RedisUtil.get("home:slider",new TypeReference<List<Slideshow>>(){});
                }
                try {
                    log.info("home:slider缓存未命中，从数据库获取");
                    Object obj = pjp.proceed();
                    RedisUtil.put("home:slider",obj);
                    log.info("home:slider缓存已更新");
                    return obj;
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                return null;
            case "Boolean":

                try {
                    Object obj = pjp.proceed();
                    log.info("清除缓存业务名："+pjp.getSignature().getName());
                    RedisUtil.delete("home:slider");
                    log.info("home:slider已清除,然后重新读取");
                    RedisUtil.put("home:slider",slideshowService.enableSliderList());
                    return obj;
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                return null;
            default:
                return null;

        }
    }
    @Around("homeArticlesPointCut()")
    public Object homeArticlesAround(ProceedingJoinPoint pjp){
        log.info("缓存业务名："+pjp.getSignature().getName());
        if(RedisUtil.hasKey("home:articles")){
            log.info("home:articles缓存命中");
            return RedisUtil.get("home:articles",new TypeReference<List<Article>>(){});
        }
        try {
            log.info("home:articles缓存未命中，从数据库获取");
            Object obj = pjp.proceed();
            RedisUtil.put("home:articles",obj);
            log.info("home:articles缓存已更新");
            return obj;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

}
