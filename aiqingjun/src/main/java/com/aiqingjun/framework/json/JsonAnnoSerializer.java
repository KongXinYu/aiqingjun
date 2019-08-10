package com.aiqingjun.framework.json;

import com.aiqingjun.common.annotations.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON注解序列化
 *
 * @author: WuZhenYu
 * @create: 2019-07
 */
public class JsonAnnoSerializer {

    ObjectMapper mapper = new ObjectMapper();
    JsonAnnoFilter jsonAnnoFilter = new JsonAnnoFilter();

    /**
     * @param clazz 需要设置规则的class
     * @param include 转换时包含哪些字段
     * @param exclude 转换时过滤哪些字段
     */
    public void filter(Class<?> clazz, String include, String exclude) {
        if (clazz == null) {
            return;
        }
        if (include != null && include.length() > 0) {
            jsonAnnoFilter.include(clazz, include.split(","));
        }
        if (exclude != null && exclude.length() > 0) {
            jsonAnnoFilter.filter(clazz, exclude.split(","));
        }
        mapper.addMixIn(clazz, jsonAnnoFilter.getClass());
    }

    public void filter(Class<?> clazz, JSON json) {
        filter(clazz, json.include(), json.exclude());
    }

    public String toJson(Object obj) throws JsonProcessingException {
        mapper.setFilterProvider(jsonAnnoFilter);
        return mapper.writeValueAsString(obj);
    }
}
