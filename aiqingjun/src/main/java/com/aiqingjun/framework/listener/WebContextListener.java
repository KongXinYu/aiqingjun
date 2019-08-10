package com.aiqingjun.framework.listener;

import com.aiqingjun.framework.config.BaseConfig;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;

/**
 * web容器初始化监听
 *
 * @author: WuZhenYu
 * @create: 2019-08
 */
@WebListener
public class WebContextListener extends org.springframework.web.context.ContextLoaderListener {

    @Override
    public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {

        servletContext.setAttribute("ctx", new BaseConfig().getPath());
        return super.initWebApplicationContext(servletContext);
    }
}
