package com.magicli.beans;

import com.magicli.ioc.dao.UserDaoImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;

import java.beans.PropertyDescriptor;

/**
 * Created by gaonl on 2018/10/14.
 */
/**
 * bean name[userDao] postProcessBeforeInstantiation, clazz: com.magicli.ioc.dao.UserDaoImpl
 * bean name[userDao] postProcessAfterInstantiation, clazz: com.magicli.ioc.dao.UserDaoImpl
 * bean name[userDao] postProcessPropertyValues, clazz: com.magicli.ioc.dao.UserDaoImpl
 * bean name[userDao] postProcessBeforeInitialization, clazz: com.magicli.ioc.dao.UserDaoImpl [代理增减前]
 * bean name[userDao] postProcessAfterInitialization, clazz: com.sun.proxy.$Proxy25           [代理增减后]
 * --->setBeanName: liveCycleBean
 * --->setBeanFactoryorg.springframework.beans.factory.support.DefaultListableBeanFactory@7fc4780b: defining bean
 * --->setApplicationContext: org.springframework.context.support.GenericApplicationContext@f8c1ddd: startup date
 * --->afterPropertiesSet
 * --->myInit
 * --->destroy
 * --->myDestroy LiveCycleBean with datasource: learn_spring
 */
public class AllBeanPostProcesser implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        if (doSystemoutBeanName(s)) {
            System.out.println("bean name[" + s + "] postProcessBeforeInitialization, clazz: " + o.getClass().getName());
        }
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        if (doSystemoutBeanName(s)) {
            System.out.println("*******bean name[" + s + "] postProcessAfterInitialization, clazz: " + o.getClass().getName());
        }
        return o;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (doSystemoutBeanName(beanName)) {
            System.out.println("bean name[" + beanName + "] postProcessBeforeInstantiation, clazz: " + beanClass.getName());
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (doSystemoutBeanName(beanName)) {
            System.out.println("bean name[" + beanName + "] postProcessAfterInstantiation, clazz: " + bean.getClass().getName());
        }
        return true;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        if (doSystemoutBeanName(beanName)) {
            System.out.println("bean name[" + beanName + "] postProcessPropertyValues, clazz: " + bean.getClass().getName());
        }
        return pvs;
    }

//    private boolean doSystemoutBean(Object bean) {
//        return bean instanceof UserDaoImpl;
//    }
//
//    private boolean doSystemoutClass(Class<?> clazz) {
//        return clazz == UserDaoImpl.class;
//    }
    private boolean doSystemoutBeanName(String beanName) {
        return "userDao".equals(beanName);
    }
}
