package com.magicli.web;

import com.magicli.ApplicationContextConfig;
import com.magicli.web.WebApplicationContextConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by gaonl on 2018/10/3.
 */
//只支持Servlet3.0之后的Servlet容器，作用相当于web.xml中的配置
//Spring实现了javax.servlet.ServletContainerInitializer的实现类，该类会加载并调用实现了WebApplicationInitializer接口的类（非abstract，如果是abstract就没用了）并执行初始化servlets,filters等等
//AbstractAnnotationConfigDispatcherServletInitializer就是实现了WebApplicationInitializer接口

//添加utf-8编码过滤器
public abstract class UTF8FilterInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic dynamic = servletContext.addFilter("utf8Filter", org.springframework.web.filter.CharacterEncodingFilter.class);
        dynamic.addMappingForUrlPatterns(null, false, "/*");
    }
}
