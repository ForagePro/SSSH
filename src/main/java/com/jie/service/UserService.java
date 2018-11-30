package com.jie.service;

import com.jie.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {
    public List<Map> toLogin(String username, String password, HttpServletRequest request);

    public String toRegister(User user);

    public List<User> toFind(String datemin,String datemax,int pageNo,int pageSize);

    public List<User> toShowAll(int pageNo,int pageSize);

    public void toUpdateStatus(int status,int id);

    public List<User> toFindUser(String keywords,int pageNo,int pageSize);

    public void toDelete(int id);

}
