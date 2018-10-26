package com.magicli.ioc.service;

import com.magicli.ioc.domain.Account;

/**
 * Created by gaonl on 2018/10/26.
 */
public interface AccountService {
    public void transfer(Account from, Account to, int money);

    public Account get(Integer accountId);
}
