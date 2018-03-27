package com.app.demo.service.impl;

import com.app.demo.dao.UserDao;
import com.app.demo.model.User;
import com.app.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> queryUser() {
        return userDao.queryUser();
    }
}
