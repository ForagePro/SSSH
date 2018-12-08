package com.jie.dao.impl;

import com.jie.dao.ReceiveaddressDao;
import com.jie.domain.Receiveaddress;
import com.jie.domain.User;
import com.jie.service.UserService;
import com.jie.service.impl.UserServiceImpl;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Repository
public class ReceiveaddressDaoImpl implements ReceiveaddressDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void toAdd(Receiveaddress receiveaddress) {

        hibernateTemplate.save(receiveaddress);
    }

    @Override
    public void toUpdate(Receiveaddress receiveaddress) {
        hibernateTemplate.update(receiveaddress);
    }

    @Override
    public List<Receiveaddress> toShow(int uId) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Receiveaddress>list=session.createQuery("select re from Receiveaddress re where uId=?").setInteger(0,uId).list();
        return list;
    }

    @Override
    public User toGetUser(HttpServletRequest request) {
        String username=(String)request.getSession().getAttribute("USER_SESSION_KEY");
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<User>list=session.createQuery("select u from User u where username=?").setString(0,username).list();
        User user=new User();
        if (list.size()!=0){
            user=list.get(0);
        }
        return user;
    }

    @Override
    public void toUpdateStatus(int id, int status) {
        Receiveaddress receiveaddress=hibernateTemplate.get(Receiveaddress.class,id);
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Receiveaddress>list=session.createQuery("select re from Receiveaddress re where uId=? and status=1").setInteger(0,receiveaddress.getuId()).list();
        if (list.size()>=1){
            for (int i=0;i<list.size();i++){
                list.get(i).setStatus(0);
                hibernateTemplate.update(list.get(i));
            }
        }

        receiveaddress.setStatus(status);
        hibernateTemplate.update(receiveaddress);
    }

    @Override
    public Receiveaddress toFind(int id) {
        Receiveaddress receiveaddress=hibernateTemplate.load(Receiveaddress.class,id);
        return receiveaddress;
    }

    @Override
    public void toDelete(int id) {
        Receiveaddress receiveaddress=hibernateTemplate.get(Receiveaddress.class,id);
//        if (receiveaddress.getStatus()==0){
//            hibernateTemplate.delete(id);
//        }else {
//
//        }
        hibernateTemplate.delete(receiveaddress);
    }
}
