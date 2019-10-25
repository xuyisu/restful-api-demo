package com.yisu.common.validate.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName FwValidate
 * @Author xuyisu
 * @Description 参数验证
 * @Date 2019/10/25
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FwValidate {

}
