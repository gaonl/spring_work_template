package com.magicli.ioc.dao;

import com.magicli.ioc.domain.Account;

/**
 * Created by gaonl on 2018/10/26.
 */
public interface AccountDao {
    public void update(Account account);

    public Account get(Integer accountId);
}
