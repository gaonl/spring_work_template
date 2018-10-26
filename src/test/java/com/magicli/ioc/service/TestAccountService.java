package com.magicli.ioc.service;

import com.magicli.ioc.dao.AccountDao;
import com.magicli.ioc.domain.Account;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by gaonl on 2018/9/28.
 */
@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations = {"classpath:application-context.xml"}) //加载配置文件
//@ContextConfiguration(classes = ApplicationContextConfig.class) //加载配置java类
public class TestAccountService {

    @Autowired
    @Qualifier("accountService") //首先匹配有指定Qualifier 的bean,如果没有匹配到，在匹配bean id 为userDao的bean
    private AccountService accountService;

    @Autowired
    @Qualifier("accountDao") //首先匹配有指定Qualifier 的bean,如果没有匹配到，在匹配bean id 为userDao的bean
    private AccountDao accountDao;

    @Test
    public void testTransfer() {
        Account accountFrom = accountService.get(1);
        Account accountTo = accountService.get(2);

        accountFrom.setMoney(1000);
        accountTo.setMoney(1000);

        accountDao.update(accountFrom);
        accountDao.update(accountTo);

        Assert.assertEquals(accountFrom.getMoney().intValue(), 1000);
        Assert.assertEquals(accountTo.getMoney().intValue(), 1000);

        accountService.transfer(accountFrom, accountTo, 100);

        accountFrom = accountService.get(1);
        accountTo = accountService.get(2);
        Assert.assertEquals(accountFrom.getMoney().intValue(), 900);
        Assert.assertEquals(accountTo.getMoney().intValue(), 1100);

        try {
            accountService.transfer(accountFrom, accountTo, -100);
            Assert.assertTrue(false);
        } catch (Exception e) {
        }

        accountFrom = accountService.get(1);
        accountTo = accountService.get(2);
        Assert.assertEquals(accountFrom.getMoney().intValue(), 900);
        Assert.assertEquals(accountTo.getMoney().intValue(), 1100);
    }
}
