package com.aiqingjun.framework.json;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

import java.util.*;

/**
 * JSON注解过滤
 *
 * @author: WuZhenYu
 * @create: 2019-07
 */
@JsonFilter("jsonAnnoFilter")
public class JsonAnnoFilter extends FilterProvider {

    private Map<Class<?>, Set<String>> includeMap = new HashMap<>();
    private Map<Class<?>, Set<String>> excludeMap = new HashMap<>();

    public void include(Class<?> clazz, String[] fileds) {
        addToMap(includeMap, clazz, fileds);
    }

    public void filter(Class<?> clazz, String[] fileds) {
        addToMap(excludeMap, clazz, fileds);
    }

    public void addToMap(Map<Class<?>, Set<String>> map, Class<?> clazz, String[] fileds) {
        Set<String> set = map.get(clazz);
        if (set == null) {
            set = new HashSet<>();
        }
        set.addAll(Arrays.asList(fileds));
        map.put(clazz, set);
    }

    @Override
    public PropertyFilter findPropertyFilter(Object filterId, Object valueToFilter) {
        return new SimpleBeanPropertyFilter() {
            @Override
            public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
                if (isInclude(pojo.getClass(), writer.getName())) {
                    writer.serializeAsField(pojo, jgen, provider);
                } else {
                    writer.serializeAsOmittedField(pojo, jgen, provider);
                }
            }
        };
    }

    private boolean isInclude(Class<?> key, String filed) {
        if (Map.class.isAssignableFrom(key)) {
            key = Map.class;
        }
        Set<String> include = includeMap.get(key);
        Set<String> exclude = excludeMap.get(key);

        if (include != null && include.contains(filed)){
            return true;
        } else  if (exclude != null && !exclude.contains(filed)) {
            return true;
        } else if (include == null && exclude == null) {
            return true;
        }
        return false;
    }

    @Override
    public BeanPropertyFilter findFilter(Object o) {
        return null;
    }
}
