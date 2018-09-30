package com.magicli.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by gaonl on 2018/9/29.
 */

/**
 * setBeanName
 * setBeanFactory
 * setApplicationContext
 * afterPropertiesSet
 * myInit
 * bean name[dataSource] postProcessBeforeInitialization
 * bean name[dataSource] postProcessAfterInitialization
 * bean name[jdbcTemplate] postProcessBeforeInitialization
 * bean name[jdbcTemplate] postProcessAfterInitialization
 * bean name[userDao] postProcessBeforeInitialization
 * bean name[userDao] postProcessAfterInitialization
 * bean name[org.springframework.context.event.internalEventListenerProcessor] postProcessBeforeInitialization
 * bean name[org.springframework.context.event.internalEventListenerProcessor] postProcessAfterInitialization
 * bean name[org.springframework.context.event.internalEventListenerFactory] postProcessBeforeInitialization
 * bean name[org.springframework.context.event.internalEventListenerFactory] postProcessAfterInitialization
 * bean name[daoInvokerCounter] postProcessBeforeInitialization
 * bean name[daoInvokerCounter] postProcessAfterInitialization
 * bean name[com.magicli.ioc.dao.TestUserDao] postProcessBeforeInitialization
 * bean name[com.magicli.ioc.dao.TestUserDao] postProcessAfterInitialization
 * destroy
 * myDestroy
 */
public class LiveCycleBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, BeanPostProcessor, InitializingBean, DisposableBean {

    @Value("${datasource.name}")
    private String dataSourceName;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("setBeanName");
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
//        System.out.println("bean name[" + s + "] postProcessBeforeInitialization");
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
//        System.out.println("bean name[" + s + "] postProcessAfterInitialization");
        return o;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
    }

    public void myInit() {
        System.out.println("myInit");
    }

    public void myDestroy() {
        System.out.println("myDestroy LiveCycleBean with datasource: " + dataSourceName);
    }
}
