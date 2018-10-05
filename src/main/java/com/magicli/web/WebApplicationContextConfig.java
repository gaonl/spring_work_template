package com.magicli.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
* Created by gaonl on 2018/10/3.
* 功能相当于application-mvc.xml的配置
*/
@Configuration
@EnableWebMvc
@ComponentScan("com.magicli.web")
public class WebApplicationContextConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();

        freeMarkerViewResolver.setPrefix("");
        freeMarkerViewResolver.setSuffix(".ftl");
        freeMarkerViewResolver.setViewClass(FreeMarkerView.class);
        freeMarkerViewResolver.setContentType("text/html; charset=utf-8");
//        freeMarkerViewResolver.setCache(true);
//        freeMarkerViewResolver.setRequestContextAttribute("request");

        return freeMarkerViewResolver;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();

        freeMarkerConfigurer.setTemplateLoaderPath("classpath:/freemarker/");
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");

        return freeMarkerConfigurer;
    }

    @Override //相当于配置了<mvc:default-servlet-handler/>  让默认的servlet去处理静态资源
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
