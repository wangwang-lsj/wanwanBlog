package com.wanwan.springboot.annotation;

import java.lang.annotation.*;

/**
 * @author：玩玩
 * @since：2024/3/3 17:13
 * @description:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthAccess {
}
