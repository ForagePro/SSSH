package com.jie.dao.impl;

import com.jie.dao.UserDao;
import com.jie.domain.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

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
        List<User>uList=session.createQuery("select new User(username,password,status) from User").list();
        for (int i=0;i<uList.size();i++){
            if (uList.get(i).getUsername().equals(username)){
                if (uList.get(i).getPassword().equals(password)){
                    if (uList.get(i).getStatus()==0){
                        HttpSession session1=request.getSession();
                        session1.setAttribute("USER_SESSION_KEY",username);
                        code="1002";
                        msg="登录成功！";
                    }else if (uList.get(i).getStatus()==1){
                        code="1003";
                        msg="账号被禁用";
                    }else if (uList.get(i).getStatus()==2){
                        code="1004";
                        msg="账号即将被销毁";
                    }

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
        user.setImgPath("getAvatar.do.jpg");
        hibernateTemplate.save(user);
        msg="注册成功";
        return msg;
    }

    @Override
    public List<User> toFind(String datemin, String datemax, int pageNo, int pageSize) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<User>list=session.createQuery("select u from User u where registerTime between ? and ? or birthday between ? and ?").setString(0,datemin).setString(1,datemax).setString(2,datemin).setString(3,datemax).setFirstResult((pageNo-1)*pageSize).setMaxResults(pageNo*pageSize).list();
        return list;
    }

    @Override
    public List<User> toShowAll(int pageNo,int pageSize) {
//        List<User>list=hibernateTemplate.loadAll(User.class);
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<User>list=session.createQuery("from User").setFirstResult((pageNo-1)*pageSize).setMaxResults(pageNo*pageSize).list();
        return list;
    }

    @Override
    public void toUpdateStatus(int status,int id) {
        User user=hibernateTemplate.load(User.class,id);
        user.setStatus(status);
        hibernateTemplate.update(user);
    }

    @Override
    public void toDelete(int id) {
        User user=hibernateTemplate.get(User.class,id);
        hibernateTemplate.delete(user);
    }

    //通过用户名查找指定用户
    @Override
    public User findUser(String username) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<User> list=session.createQuery("from User where username=?").setString(0,username).list();
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
    //更新用户图片
    @Override
    public void updateUserImg(User user) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<User> list=session.createQuery("from User where username=?").setString(0,user.getUsername()).list();
        if(list!=null&&list.size()>0){
            User user1=list.get(0);
            user1.setImgPath(user.getImgPath());
            hibernateTemplate.update(user1);
        }

    }
    //更新用户信息
    @Override
    public void updateUser(User user) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        User user1=session.get(User.class,user.getId());
        user1.setName(user.getName());
        user1.setBirthday(user.getBirthday());
        user1.setEmail(user.getEmail());
        user1.setSex(user.getSex());
        hibernateTemplate.update(user1);
    }

    //验证原始密码是否正确
    @Override
    public String originCode(String username) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<User> list=session.createQuery("from User where username=?").setString(0,username).list();
        if(list!=null&&list.size()>0){
            return list.get(0).getPassword();
        }
        return "";
    }
    //更新密码
    @Override
    public void updatePwd(User user) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<User> list=session.createQuery("from User where username=?").setString(0,user.getUsername()).list();
        if(list!=null&&list.size()>0){
            User user1=list.get(0);
            user1.setPassword(user.getPassword());
            hibernateTemplate.update(user1);
        }
    }

    @Override
    public List<User> toFindUser(String keywords,int pageNo,int pageSize) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<User>list=session.createQuery("select u from User u where username like ? or phone like ? or email like ?").setString(0,"%"+keywords+"%").setString(1,"%"+keywords+"%").setString(2,"%"+keywords+"%").setFirstResult((pageNo-1)*pageSize).setMaxResults(pageNo*pageSize).list();
        return list;
    }
}
