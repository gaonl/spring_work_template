package com.magicli.aop.baseinner;

import com.magicli.aop.DaoInvokeCounterAspectAnnotation;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by gaonl on 2018/10/15.
 */
public class Test {
    public static void main(String[] args){
//        test1();
//        test2();
        test3();
    }

    public static void test1(){
        System.out.println("----------test1------------");
        Target target = new TargetImpl();
        BeforeAdvice beforeAdvice = new BeforeAdvice();
        ProxyFactory pf = new ProxyFactory();

        //也可以使用基于@AspectJ注解的类来描述、创建代理
//        AspectJProxyFactory pf = new AspectJProxyFactory();
//        pf.addAspect(DaoInvokeCounterAspectAnnotation.class);

        pf.setTarget(target);
        pf.addAdvice(beforeAdvice);
//        pf.addAdvisor();
        pf.setInterfaces(Target.class);
        pf.setOptimize(true);

        Target proxied = (Target)pf.getProxy();

        System.out.println("-->" + proxied.saveUser("wskj"));
    }

    public static void test2(){
        System.out.println("----------test2------------");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:aop/aop-context.xml");
        Target target = (Target)context.getBean("proxiedTarget");
        MyCloseable myCloseable = (MyCloseable)target;
        myCloseable.close();
        System.out.println(myCloseable.isClose());
        System.out.println("-->" + target.saveUser("wskj"));
    }

    public static void test3(){
        System.out.println("----------test2------------");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:aop/aop-context.xml");
        Target target = (Target)context.getBean("target");
        System.out.println("-->" + target.saveUser("wskj"));
    }
}
