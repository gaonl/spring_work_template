package com.magicli.ioc.dao;

import com.magicli.ioc.domain.User;

import java.util.List;

/**
 * Created by gaonl on 2018/9/28.
 */
public interface UserDao {
    public User save(User user);

    public User getById(Integer id);

    public List<User> getByName(String name);
}
