package com.magicli.aop.baseinner;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by gaonl on 2018/10/15.
 */
public class BeforeAdvice implements MethodBeforeAdvice{
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.print("before invoke method: " + method.getName());
        System.out.print("(");
        for(Object obj : objects){
            System.out.print(obj+",");
        }
        System.out.println(")");

        //无需调用，会自己调用了（按道理不应该这样啊）
//        method.invoke(o,objects);
    }
}
