package com.wanwan.springboot.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 玩玩
 * @description
 * @since 2024/5/11  17:21
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RedisCache {
    //自定义key值（本文中未使用）
    String key() default "";
    //设置缓存时间
    int expire() default 24;
    //缓存时间类型
    TimeUnit TIME_UNIT() default TimeUnit.HOURS;
    // 缓存反序列化获取的对象
    Class<?> clazz() default Object.class;
    // //序列化后的对象是否是jsonarry 比如 List<Object>
    boolean isArray() default false;

    boolean isMap() default false;
    boolean isPage() default false;
    boolean isId() default false;
}
