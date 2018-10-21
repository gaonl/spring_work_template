package com.magicli.aop.baseinner;

/**
 * Created by gaonl on 2018/10/15.
 */
public class TargetImpl implements Target {
    @Override
    public String saveUser(String userName) {
        String msg = "-->invoke saveUser("+userName+")";
        System.out.println(msg);
        return "SUCCESS";
    }
}
