package com.aiqingjun.framework.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 自定义基础配置类
 *
 * @author: WuZhenYu
 * @create: 2019-08
 */
@ConfigurationProperties(prefix = "base")
@Component
@Getter
@Setter
public class BaseConfig {
    private String path;

}
