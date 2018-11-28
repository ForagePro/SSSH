package com.jie.service;

import com.jie.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {
    public List<Map> toLogin(String username, String password, HttpServletRequest request);

    public String toRegister(User user);
}
