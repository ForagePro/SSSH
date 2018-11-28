package com.jie.service.impl;

import com.jie.dao.UserDao;
import com.jie.domain.User;
import com.jie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public List<Map> toLogin(String phone, String password, HttpServletRequest request) {
       List list=userDao.toLogin(phone, password,request);
       return list;
    }

    @Override
    public String toRegister(User user) {
        String msg=userDao.toRegister(user);
        return msg;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
