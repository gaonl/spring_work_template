package com.magicli.aop.baseinner;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by gaonl on 2018/10/15.
 */
public class AfterAdvice implements AfterReturningAdvice{
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.print("after invoke method: " + method.getName());
        System.out.print(" with return (");
        System.out.print(returnValue);
        System.out.println(")");
    }
}
