package com.magicli.aop.baseinner;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import java.lang.reflect.Method;

/**
 * 引介增强，就是在代理类中引入新的接口来扩展目标类，必须要要用cglib实现
 * Created by gaonl on 2018/10/15.
 */
public class IntroductionAdvice extends DelegatingIntroductionInterceptor implements MyCloseable {
    private boolean closed = false;

    /**
     * Subclasses may need to override this if they want to perform custom
     * behaviour in around advice. However, subclasses should invoke this
     * method, which handles introduced interfaces and forwarding to the target.
     */
    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object ths = mi.getThis();
        return super.invoke(mi);
    }

    @Override
    public void close() {
        closed = true;
        System.out.println("close by add introduction method ");
    }

    @Override
    public boolean isClose() {
        return closed;
    }
}
