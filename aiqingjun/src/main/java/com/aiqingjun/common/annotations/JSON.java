package com.aiqingjun.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 过滤返回的JSON数据
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JSON {
    /**
     * 需要过滤的类
     * @return
     */
    Class<?> type();
    /**
     * 保留的字段,（逗号分隔）
     * @return
     */
    String include() default "";
    /**
     * 排除的字段,（逗号分隔）
     * @return
     */
    String exclude() default "";
}
