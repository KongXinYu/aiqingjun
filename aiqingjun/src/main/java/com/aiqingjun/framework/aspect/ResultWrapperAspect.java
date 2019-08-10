package com.aiqingjun.framework.aspect;

import com.aiqingjun.common.annotations.JSON;
import com.aiqingjun.common.annotations.JSONS;
import com.aiqingjun.common.constants.Constants;
import com.aiqingjun.framework.json.JsonAnnoSerializer;
import com.aiqingjun.framework.result.Result;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 对请求结果进行封装
 * @author: WuZhenYu
 * @create: 2019-07
 */
@Aspect
@Component
@Slf4j
public class ResultWrapperAspect {

    private static final String ARRARY_JSON_PREFIX = "[{";

    private static final String JSON_PREFIX = "{";

    @Pointcut("execution(* com.aiqingjun.controller..*(..)) " +
            "&& ( @annotation(org.springframework.web.bind.annotation.ResponseBody)" +
            "|| @target(org.springframework.web.bind.annotation.RestController) )")
    public void controllerAround() {}

    @Around("controllerAround()")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        log.info("---------wrapper Result start--------");

        // 获取目标方法
        Signature signature = point.getSignature();
        Method method = null;
        if (signature instanceof MethodSignature) {
            method = ((MethodSignature) signature).getMethod();
        }

        // 获取执行的结果
        Object value = point.proceed();
        value = filterFiled(method, value);
        if (value instanceof String) {
            String valueStr = (String)value;

            if (valueStr.startsWith(ARRARY_JSON_PREFIX) ) {
                value = JSONArray.parseArray(valueStr, Map.class);
            } else if (valueStr.startsWith(JSON_PREFIX)) {
                value = JSONObject.parseObject(valueStr, Map.class);
            }
        }

        Result result = new Result(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG, value);
        log.info("result: {}",result.toString());
        log.info("---------wrapper Result end--------");
        return result;
    }

    private Object filterFiled(Method method, Object data) throws JsonProcessingException {
        boolean needFilter = false;
        JsonAnnoSerializer jsonAnnoSerializer = new JsonAnnoSerializer();
        JSON json = method.getAnnotation(JSON.class);
        if (json != null) {
            needFilter = true;
            jsonAnnoSerializer.filter(json.type(), json);
        }
        JSONS jsons = method.getAnnotation(JSONS.class);
        if (jsons != null) {
            needFilter = true;
            JSON[] jsonArr = jsons.value();
            for (JSON j : jsonArr) {
                jsonAnnoSerializer.filter(j.type(), json);
            }
        }
        if (needFilter && !ObjectUtils.isEmpty(data)) {
            data = jsonAnnoSerializer.toJson(data);
        }
        return data;
    }

}
