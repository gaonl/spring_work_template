package com.magicli.aop;

import com.magicli.ioc.domain.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by gaonl on 2018/9/29.
 */
public class DaoInvokeCounter {
    public void before(JoinPoint joinPoint) {
        Object param = joinPoint.getArgs()[0];
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method [" + methodName + "] before with user: " + param);
    }

    public void after(JoinPoint joinPoint) {
        //不管方法执行成功还是失败，都会执行的
        Object param = joinPoint.getArgs()[0];
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method [" + methodName + "] after with user: " + param);
    }

    public void afterReturn(JoinPoint joinPoint, Object result) {
        Object param = joinPoint.getArgs()[0];
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method [" + methodName + "] afterReturn with user: " + param + " and result: " + result);
    }

    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        Object param = joinPoint.getArgs()[0];
        String methodName = joinPoint.getSignature().getName();
        System.out.println("The method [" + methodName + "] afterThrowing with user: " + param + " and throw: " + e);
    }

    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        String methodName = proceedingJoinPoint.getSignature().getName();
        try {
            //前置通知
            Object param = proceedingJoinPoint.getArgs()[0];
            System.out.println("The method [" + methodName + "] around(start) with param " + param);
            //执行目标方法
            result = proceedingJoinPoint.proceed();
            //执行目标方法(调用带参数的proceed方法可以更改参数，也可以对result进行返回值处理)
            //User user = (User) param;
            //user.setName("change___" + user.getName());
            //result = proceedingJoinPoint.proceed(new Object[]{user});
            //返回通知
            System.out.println("The method [" + methodName + "] around(end) with result " + result);
        } catch (Throwable e) {
            //异常通知
            System.out.println("The method [" + methodName + "] around(exception):" + e);
            throw new RuntimeException(e);
        }
        //后置通知
        System.out.println("The method [" + methodName + "] around(finally)");
        return result;
    }


    /**
     * 不知道为啥，这种获取参数的方法一直报错
     * @param proceedingJoinPoint
     *  arg-names="id"  这个一直不行
     * @return
     */
//    public void countBeforeInvokeDaoMethod(Integer id) {
//        System.out.println("countBeforeInvokeDaoMethod with id:" + id);
//    }
}
