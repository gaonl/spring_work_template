package com.magicli.ioc.service;

import com.magicli.ioc.dao.AccountDao;
import com.magicli.ioc.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by gaonl on 2018/10/26.
 */
@Service("accountService")
@Qualifier("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired()
    @Qualifier("accountDao")
    private AccountDao accountDao;

    @Override
    @Transactional("transactionManager")
    public void transfer(Account from, Account to, int money) {
        from.setMoney(from.getMoney() - money);
        to.setMoney(to.getMoney() + money);

        accountDao.update(from);
        accountDao.update(to);
        if(money <= 0){
            throw new RuntimeException("money must greater than 0.");
        }

    }

    @Override
    public Account get(Integer accountId) {
        return accountDao.get(accountId);
    }
}
