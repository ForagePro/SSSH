package com.jie.dao;

import com.jie.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserDao {
    //用户登录
    public List<Map> toLogin(String username, String password, HttpServletRequest request);
    //用户注册
    public String toRegister(User user);

    //查找指定用户
    public User findUser(String username);
    //更新用户图片
    public void updateUserImg(User user);
    //更新用户信息
    public void updateUser(User user);
    //验证原始密码是否正确
    public String originCode(String username);
    //更新密码
    public void updatePwd(User user);
    //获取所有的存在用户
    public List<User> toShowAll(int pageNo,int pageSize);

    //删除用户
    public void toDelete(int id);

    //修改用户状态
    public void toUpdateStatus(int status,int id);

    //查询用户
    public List<User> toFind(String datemin,String datemax,int pageNo,int pageSize);


    //搜索用户
    public List<User> toFindUser(String keywords,int pageNo,int pageSize);
}
