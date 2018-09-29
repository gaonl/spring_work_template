package com.magicli.ioc.dao;

import com.magicli.ioc.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

/**
* Created by gaonl on 2018/9/28.
*/
@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations={"classpath:application-context.xml"}) //加载配置文件
public class TestUserDao {

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Test
    public void testSave(){
        User user = new User();
        user.setName("user_" + UUID.randomUUID().toString());
        User userSaved =userDao.save(user);

        User userFindById = userDao.getById(user.getId());
        List<User> userFindByName = userDao.getByName(user.getName());

        Assert.assertEquals(userFindById,userSaved);
        Assert.assertEquals(userFindByName.get(0),userSaved);
    }
}
