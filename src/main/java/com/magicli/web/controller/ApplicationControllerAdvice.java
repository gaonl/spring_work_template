package com.magicli.web.controller;

import com.magicli.web.controller.exceptions.DuplicateDomainException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by gaonl on 2018/10/6.
 */
@ControllerAdvice
public class ApplicationControllerAdvice {
    /**
     * 当不管哪个controller抛出DuplicateDomainException异常时，调用这个方法
     * 不知道能不能带请求参数，算了，不试了
     * @return
     */
    @ExceptionHandler(DuplicateDomainException.class)
    public String allThrowableHere(){
        return "error/500";
    }
}
