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
    public List<Map> toLogin(String username, String password, HttpServletRequest request) {
       List list=userDao.toLogin(username, password,request);
       return list;
    }

    @Override
    public String toRegister(User user) {
        String msg=userDao.toRegister(user);
        return msg;
    }

    @Override
    public User findUser(String username) {
        return userDao.findUser(username);
    }

    @Override
    public void updateUserImg(User user) {
        userDao.updateUserImg(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    //验证原始密码是否正确
    @Override
    public String originCode(String username) {
        return userDao.originCode(username);
    }

    @Override
    public void updatePwd(User user) {
        userDao.updatePwd(user);
    }

    @Override
    public List<User> toFind(String datemin,String datemax,int pageNo,int pageSize) {
        List<User>list=userDao.toFind(datemin,datemax,pageNo,pageSize);
        return list;
    }

    @Override
    public List<User> toShowAll(int pageNo,int pageSize) {
        List<User>list=userDao.toShowAll(pageNo,pageSize);
        return list;
    }

    @Override
    public List<User> toFindUser(String keywords,int pageNo,int pageSize) {
        List<User>list=userDao.toFindUser(keywords,pageNo,pageSize);
        return list;
    }

    @Override
    public void toDelete(int id) {
        userDao.toDelete(id);
    }

    @Override
    public void toUpdateStatus(int status, int id) {
        userDao.toUpdateStatus(status,id);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
