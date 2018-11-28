package com.jie.dao.impl;

import com.jie.dao.UserDao;
import com.jie.domain.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public List<Map> toLogin(String username, String password, HttpServletRequest request) {
        String code="1000";
        String msg="账号不存在";
        Map<String,String>map=new HashMap<>();
        List<Map>list=new ArrayList<>();
        Session session= hibernateTemplate.getSessionFactory().getCurrentSession();
        List<User>uList=session.createQuery("select new User(username,password) from User").list();
        for (int i=0;i<uList.size();i++){
            if (uList.get(i).getUsername().equals(username)){
                if (uList.get(i).getPassword().equals(password)){
                    HttpSession session1=request.getSession();
                    session1.setAttribute("USER_SESSION_KEY",username);
                    code="1002";
                    msg="登录成功！";
                }else {
                    code="1001";
                    msg="密码错误！";
                }
            }
        }
        map.put(code,msg);
        list.add(map);
        return list;
    }

    @Override
    public String toRegister(User user) {
        Session session= hibernateTemplate.getSessionFactory().getCurrentSession();
        List<String> list=session.createQuery("select username from User").list();
        String msg="注册成功";
        if (list.size()==0){
            hibernateTemplate.save(user);
        }else {
            for (int i=0;i<list.size();i++){
                if (list.get(i).equals(user.getUsername())){
                    msg="用户名存在";
                    return msg;

                }
            }
        }
        System.out.println("123");
        hibernateTemplate.save(user);
        msg="注册成功";
        return msg;
    }
}
