package com.jie.controller;

import com.alibaba.fastjson.JSON;
import com.jie.service.ConservatorService;
import com.jie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/conser")
public class ConservatorController {
    @Autowired
    private ConservatorService conservatorService;
    @RequestMapping("/toLogin")
    @ResponseBody
    public String toLogin(String account, String password, HttpServletRequest request,String code) {
        List<Map>list=conservatorService.toLogin(account,password,request,code);
        return JSON.toJSONString(list);
    }

    public void setConservatorService(ConservatorService conservatorService) {
        this.conservatorService = conservatorService;
    }
}
