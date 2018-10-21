package com.magicli.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * Created by gaonl on 2018/9/29.
 */

/**
 * bean name[userDao] postProcessBeforeInstantiation, clazz: com.magicli.ioc.dao.UserDaoImpl
 * bean name[userDao] postProcessAfterInstantiation, clazz: com.magicli.ioc.dao.UserDaoImpl
 * bean name[userDao] postProcessPropertyValues, clazz: com.magicli.ioc.dao.UserDaoImpl
 * bean name[userDao] postProcessBeforeInitialization, clazz: com.magicli.ioc.dao.UserDaoImpl  [代理增减前]
 * bean name[userDao] postProcessAfterInitialization, clazz: com.sun.proxy.$Proxy25            [代理增减后]
 * --->setBeanName: liveCycleBean
 * --->setBeanFactoryorg.springframework.beans.factory.support.DefaultListableBeanFactory@7fc4780b: defining bean
 * --->setApplicationContext: org.springframework.context.support.GenericApplicationContext@f8c1ddd: startup date
 * --->afterPropertiesSet
 * --->myInit
 * --->destroy
 * --->myDestroy LiveCycleBean with datasource: learn_spring
 */
public class LiveCycleBean implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    @Value("${datasource.name}")
    private String dataSourceName;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("--->setApplicationContext: " + applicationContext);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("--->setBeanFactory" + beanFactory);
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("--->setBeanName: " + s);
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("--->destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("--->afterPropertiesSet");
    }

    public void myInit() {
        System.out.println("--->myInit");
    }

    public void myDestroy() {
        System.out.println("--->myDestroy LiveCycleBean with datasource: " + dataSourceName);
    }
}
