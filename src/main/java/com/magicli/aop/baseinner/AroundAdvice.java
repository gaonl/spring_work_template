package com.magicli.aop.baseinner;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Created by gaonl on 2018/10/15.
 */
public class AroundAdvice implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object[] arguments = invocation.getArguments();
        Object[] newArguments = new Object[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            newArguments[i] = arguments[i] + "*";
        }


        Object target = invocation.getThis();
        System.out.println("before invoke around: " + invocation.getMethod().getName());
        Object object = invocation.getMethod().invoke(target, newArguments);
        System.out.println("after invoke around: " + invocation.getMethod().getName());

        return object + "*";
    }
}
