package com.spzx.common.log.annotation;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PrintLog  {

    String title();

    boolean isPrintRequestParam() default true;
    boolean isPrintResponseData() default true;
}
