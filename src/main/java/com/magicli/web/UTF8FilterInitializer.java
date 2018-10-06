package com.magicli.web;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

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
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");

        FilterRegistration.Dynamic dynamic = servletContext.addFilter("SpringCharacterEncodingFilter", filter);
        dynamic.addMappingForUrlPatterns(null, false, "/*");
    }
}
