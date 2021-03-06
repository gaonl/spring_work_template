package com.magicli.web;

import com.magicli.ApplicationContextConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * Created by gaonl on 2018/10/3.
 */
//只支持Servlet3.0之后的Servlet容器，作用相当于web.xml中的配置
//Spring实现了javax.servlet.ServletContainerInitializer的实现类，该类会加载并调用实现了WebApplicationInitializer接口的类（非abstract，如果是abstract就没用了）并执行初始化servlets,filters等等
//AbstractAnnotationConfigDispatcherServletInitializer就是实现了WebApplicationInitializer接口
public abstract class MyWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 子容器的class配置，就是web层相关的配置
     * 功能相当于加载spring应用的子容器
     * <servlet>
     *      <servlet-name>applicationMvc</servlet-name>
     *      <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
     *      <init-param>
     *              <param-name>contextConfigLocation</param-name>
     *              <param-value>classpath:application-mvc.xml</param-value>
     *      </init-param>
     *      <load-on-startup>1</load-on-startup>
     *      <multipart-config>
     *              <location>F:\my\my_projects\spring_word_template_files\tmp</location>
     *              <max-file-size>2097152</max-file-size>
     *              <max-request-size>4194304</max-request-size>
     *      </multipart-config>
     * </servlet>
     * <servlet-mapping>
     *      <servlet-name>applicationMvc</servlet-name>
     *      <url-pattern>/</url-pattern>
     * </servlet-mapping>
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebApplicationContextConfig.class};
    }

    /**
     * 父容器的class配置，就是业务层相关的配置
     * 功能相当于配置spring应用的父容器
     * <context-param>
     *      <param-name>contextConfigLocation</param-name>
     *      <param-value>classpath:application-context.xml</param-value>
     * </context-param>
     * <listener>
     *      <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
     * </listener>
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ApplicationContextConfig.class};
    }

    /**
     * 配置servlet3.0的文件上传，相当于上述的<multipart-config>配置
     * @param registration
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(new MultipartConfigElement("F:\\my\\my_projects\\spring_word_template_files\\tmp",2097152,4194304,0));
    }
}
