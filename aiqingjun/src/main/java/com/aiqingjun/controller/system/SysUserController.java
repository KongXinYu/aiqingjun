package com.aiqingjun.controller.system;

import com.aiqingjun.common.annotations.JSON;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @GetMapping("/getUser")
    @JSON(type = Map.class, include = "age")
    public Object getUser() {
        Map<String, String> user = Maps.newHashMap();
        user.put("age", "1");
        user.put("name", "kongxinyu");
        user.put("total", "666");
        user.put("phone", "123456");
        return user;
    }

    @GetMapping("/getUser1")
    @JSON(type = Map.class, exclude = "age")
    public Object getUser1() {
        Map<String, String> user = Maps.newHashMap();
        user.put("age", "1");
        user.put("name", "kongxinyu");
        user.put("total", "666");
        user.put("phone", "123456");
        return user;
    }
}
