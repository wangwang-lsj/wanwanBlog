package com.wanwan.springboot.aop;

import com.alibaba.fastjson.TypeReference;
import com.wanwan.springboot.annotation.RedisCache;
import com.wanwan.springboot.annotation.RedisUpdate;
import com.wanwan.springboot.service.ISlideshowService;
import com.wanwan.springboot.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author 玩玩
 * @description
 * @since 2024/5/8  18:04
 */
@Aspect
@Component
@Slf4j
public class RedisCacheAOP {
    // @Resource
    // ISlideshowService slideshowService;

    // @Pointcut("@annotation(com.wanwan.springboot.annotation.RedisCache)")
    // public void redisCachePointCut(){
    //
    // }
    // @Pointcut("@annotation(com.wanwan.springboot.annotation.RedisUpdate)")
    // public void redisUpdatePointCut(){
    //
    // }
    // @Around("redisCachePointCut() && @annotation(redisCache)")
    // public Object doAround(ProceedingJoinPoint pjp, RedisCache redisCache){
    //     String key = redisCache.key();
    //     TimeUnit timeUnit = redisCache.TIME_UNIT();
    //     int expire = redisCache.expire();
    //     Class<?> clazz = redisCache.clazz();
    //     boolean isArray = redisCache.isArray();
    //     boolean isMap = redisCache.isMap();
    //     boolean isPage = redisCache.isPage();
    //     boolean isId = redisCache.isId();
    //     if(isPage){
    //         key = key +"_"+ pjp.getArgs()[0]+"_"+pjp.getArgs()[1];
    //         log.info(key);
    //     }
    //     if(isId){
    //         key = key+pjp.getArgs()[0];
    //     }
    //     Object result = null;
    //     try {
    //         if(RedisUtil.hasKey(key)){
    //             log.info("==================缓存命中==============");
    //             // return RedisUtil.get(key,);
    //             if(isArray){
    //                 return RedisUtil.getArray(key,clazz);
    //             }else if(isMap){
    //                 return RedisUtil.get(key,new TypeReference<HashMap<String,Object>>(){});
    //                 // return RedisUtil.hGetAll(key,new TypeReference<Object>(){});
    //             }else {
    //                 return RedisUtil.get(key,clazz);
    //             }
    //         }else {
    //             log.info("==================缓存未命中==============");
    //             result = pjp.proceed();
    //             // 结果放入缓存
    //             RedisUtil.put(key,result,expire,timeUnit);
    //         }
    //     } catch (Throwable e) {
    //         log.error("程序异常："+e.getMessage());
    //         throw new RuntimeException(e);
    //     }
    //     return result;
    // }

    // @After("redisUpdatePointCut() && @annotation(redisUpdate)")
    // public void doAfter(RedisUpdate redisUpdate){
    //     log.info("=====================清除缓存=================");
    //     String[] keys = redisUpdate.keys();
    //     boolean isPrefix = redisUpdate.isPrefix();
    //     if(isPrefix){
    //         RedisUtil.deletePrefix(keys[0]);
    //     }else {
    //         for (String key: keys) {
    //             RedisUtil.delete(key);
    //         }
    //     }
    // }
    // @Around("sliderPointCut()")
    // public Object sliderAround(ProceedingJoinPoint pjp){
    //     if(pjp.getSignature().getName().equals("sliderList")){
    //         Object obj = null;
    //         try {
    //             obj = pjp.proceed();
    //         } catch (Throwable e) {
    //             e.printStackTrace();
    //         }
    //         return obj;
    //     }
    //     Class<?> returnType = ((MethodSignature) pjp.getSignature()).getReturnType();
    //     log.info("方法返回类型："+ returnType.getSimpleName());
    //     switch (returnType.getSimpleName()){
    //         case "List":
    //             log.info("缓存业务名："+pjp.getSignature().getName());
    //             if(RedisUtil.hasKey("home:slider")){
    //                 log.info("home:slider缓存命中");
    //                 return RedisUtil.get("home:slider",new TypeReference<List<Slideshow>>(){});
    //             }
    //             try {
    //                 log.info("home:slider缓存未命中，从数据库获取");
    //                 Object obj = pjp.proceed();
    //                 RedisUtil.put("home:slider",obj);
    //                 log.info("home:slider缓存已更新");
    //                 return obj;
    //             } catch (Throwable e) {
    //                 e.printStackTrace();
    //             }
    //             return null;
    //         case "Boolean":
    //
    //             try {
    //                 Object obj = pjp.proceed();
    //                 log.info("清除缓存业务名："+pjp.getSignature().getName());
    //                 RedisUtil.delete("home:slider");
    //                 log.info("home:slider已清除,然后重新读取");
    //                 RedisUtil.put("home:slider",slideshowService.listEnableSlider());
    //                 return obj;
    //             } catch (Throwable e) {
    //                 e.printStackTrace();
    //             }
    //             return null;
    //         default:
    //             return null;
    //
    //     }
    // }
    // @Around("homeArticlesPointCut()")
    // public Object homeArticlesAround(ProceedingJoinPoint pjp){
    //     log.info("缓存业务名："+pjp.getSignature().getName());
    //     if(RedisUtil.hasKey("home:articles")){
    //         log.info("home:articles缓存命中");
    //         return RedisUtil.get("home:articles",new TypeReference<List<Article>>(){});
    //     }
    //     try {
    //         log.info("home:articles缓存未命中，从数据库获取");
    //         Object obj = pjp.proceed();
    //         RedisUtil.put("home:articles",obj);
    //         log.info("home:articles缓存已更新");
    //         return obj;
    //     } catch (Throwable e) {
    //         e.printStackTrace();
    //     }
    //     return null;
    // }

}
