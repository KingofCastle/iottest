package com.xinguang.iot.annotation;

import java.lang.annotation.*;

/**
 * Created by chuantou on 2016/12/22.
 */
@Target(ElementType.METHOD)  //注明注解中有方法
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireSign {

}
