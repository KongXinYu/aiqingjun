package com.aiqingjun.common.utils.bean;

import org.springframework.beans.BeansException;

/**
 * bean工具类
 *
 * @author: WuZhenYu
 * @create: 2019-08
 */
public class BeanUtils extends org.springframework.beans.BeanUtils{

    /**
     * Bean属性复制
     * @param dest 源对象
     * @param src 目标对象
     */
    public static void copyBeanProp (Object src, Object dest) {
        try {
            copyProperties(src, dest);
        } catch (BeansException e) {
            e.printStackTrace();
        }
    }
}
