package com.java.spring.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface ShController {

    /**
     * 给controller起别名
     * @return
     */
    String value() default "";
}
