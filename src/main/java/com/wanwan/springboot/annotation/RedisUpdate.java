package com.wanwan.springboot.annotation;

import java.lang.annotation.*;

/**
 * @author 玩玩
 * @description
 * @since 2024/5/14  18:41
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RedisUpdate {
    String[] keys() default {};
    boolean isPrefix() default false;
}
