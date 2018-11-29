package com.jie.dao.impl;

import com.jie.dao.ConservatorDao;
import com.jie.domain.Conservator;
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
public class ConservatorDaoImpl implements ConservatorDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Override
    public List<Map> toLogin(String account, String password, HttpServletRequest request,String code) {
        String code1="1000";
        String msg="账号不存在";
        Map<String,String>map=new HashMap<>();
        List<Map>list=new ArrayList<>();
        Session session= hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Conservator>uList=session.createQuery("select new Conservator(account,password) from Conservator").list();
        for (int i=0;i<uList.size();i++){
            if (uList.get(i).getAccount().equals(account)){
                if (uList.get(i).getPassword().equals(password)){
                    HttpSession session1=request.getSession();
                    String valid = (String)session1.getAttribute("code");
                    if (code.equalsIgnoreCase(valid)){
                        session1.setAttribute("USER_SESSION_KEY",account);
                        code1="1002";
                        msg="登录成功！";
                    }else {
                        code1="1003";
                        msg="验证码错误！";
                    }
                }else {
                    code1="1001";
                    msg="密码错误！";
                }
            }
        }
        map.put(code1,msg);
        list.add(map);
        return list;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}
