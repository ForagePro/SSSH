package com.jie.dao;

import com.jie.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserDao {
    //用户登录
    public List<Map> toLogin(String phone, String password, HttpServletRequest request);
    //用户注册
    public String toRegister(User user);

}
