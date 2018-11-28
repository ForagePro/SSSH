package com.jie.controller;

import com.alibaba.fastjson.JSON;
import com.jie.domain.User;
import com.jie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("/toLogin")
    @ResponseBody
    public String toLogin(String phone, String password, HttpServletRequest request) {

        List list = userService.toLogin(phone, password,request);
        return JSON.toJSONString(list);
    }

    @RequestMapping("/toRegister")
    @ResponseBody
    public String toRegister(User user) {

        String msg=userService.toRegister(user);
        return JSON.toJSONString(msg);
    }
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
