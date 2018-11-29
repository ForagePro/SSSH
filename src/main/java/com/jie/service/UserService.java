package com.jie.service;

import com.jie.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {
    public List<Map> toLogin(String username, String password, HttpServletRequest request);

    public String toRegister(User user);
    //根据用户名查询用户
    public User findUser(String username);
    //更新用户信息
    public void updateUserImg(User user);
    //更新用户信息
    public void updateUser(User user);
    //验证原始密码是否正确
    public String originCode(String username);
    //更新密码
    public void updatePwd(User user);
}
