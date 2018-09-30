package com.magicli.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by gaonl on 2018/9/29.
 */
@Component//首先让spring管理这个bean
@Aspect
//声明这个bean为切面，并在配置中配置 @EnableAspectJAutoProxy或者<aop:aspectj-autoproxy/> 启用自动代理
public class DaoInvokeCounterAspectAnnotation {

    @Pointcut("execution(* com.magicli..UserDao.save(..)))")
    public void savePointCut() {
    }


    @Before(value = "savePointCut()")
    public void before(JoinPoint joinPoint) {
        Object param = joinPoint.getArgs()[0];
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[annotation]The method [" + methodName + "] before with user: " + param);
    }

    @After(value = "savePointCut()")
    public void after(JoinPoint joinPoint) {
        //不管方法执行成功还是失败，都会执行的
        Object param = joinPoint.getArgs()[0];
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[annotation]The method [" + methodName + "] after with user: " + param);
    }

    @AfterReturning(value = "savePointCut()", returning = "result")
    public void afterReturn(JoinPoint joinPoint, Object result) {
        Object param = joinPoint.getArgs()[0];
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[annotation]The method [" + methodName + "] afterReturn with user: " + param + " and result: " + result);
    }

    @AfterThrowing(value = "savePointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        Object param = joinPoint.getArgs()[0];
        String methodName = joinPoint.getSignature().getName();
        System.out.println("[annotation]The method [" + methodName + "] afterThrowing with user: " + param + " and throw: " + e);
    }

    @Around(value = "savePointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        String methodName = proceedingJoinPoint.getSignature().getName();
        try {
            //前置通知
            Object param = proceedingJoinPoint.getArgs()[0];
            System.out.println("[annotation]The method [" + methodName + "] around(start) with param " + param);
            //执行目标方法
            result = proceedingJoinPoint.proceed();
            //执行目标方法(调用带参数的proceed方法可以更改参数，也可以对result进行返回值处理)
            //User user = (User) param;
            //user.setName("change___" + user.getName());
            //result = proceedingJoinPoint.proceed(new Object[]{user});
            //返回通知
            System.out.println("[annotation]The method [" + methodName + "] around(end) with result " + result);
        } catch (Throwable e) {
            //异常通知
            System.out.println("[annotation]The method [" + methodName + "] around(exception):" + e);
            throw new RuntimeException(e);
        }
        //后置通知
        System.out.println("[annotation]The method [" + methodName + "] around(finally)");
        return result;
    }

}
