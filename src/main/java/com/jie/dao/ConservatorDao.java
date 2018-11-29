package com.jie.dao;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

//管理员
public interface ConservatorDao {
    //登录
    public List<Map> toLogin(String account, String password, HttpServletRequest request,String code);
}
