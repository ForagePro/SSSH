package com.jie.dao.impl;

import com.jie.dao.ShopcartDao;
import com.jie.domain.Commodity;
import com.jie.domain.Shopcart;
import com.jie.domain.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@Repository
public class ShopcartDaoImpl implements ShopcartDao {
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
    public String toAdd(int cId,Shopcart shopcart, HttpServletRequest request) {
        String username=(String)request.getSession().getAttribute("USER_SESSION_KEY");
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<User>list=session.createQuery("select u from User u where username=?").setString(0,username).list();
        User user=list.get(0);
        List<Shopcart>list1=session.createQuery("select s from Shopcart s where uId=? and status=0").setInteger(0,user.getId()).list();
        String msg="";
        if (list.size()==0){
            Commodity commodity=hibernateTemplate.get(Commodity.class,cId);
            shopcart.setCommodity(commodity);
            shopcart.setuId(user.getId());
            hibernateTemplate.save(shopcart);
            msg="添加购物车成功！";
        }
        for (int i=0;i<list1.size();i++){
            if (list1.get(i).getCommodity().getId()==cId){
                System.out.println("已在购物车");
                msg="已在购物车";
                return msg;
            }
        }
        Commodity commodity=hibernateTemplate.get(Commodity.class,cId);
        shopcart.setCommodity(commodity);
        shopcart.setuId(user.getId());
        hibernateTemplate.save(shopcart);
        msg="添加购物车成功！";
        return msg;
    }

    @Override
    public List<Shopcart> toShow(HttpServletRequest request) {
        String username=(String)request.getSession().getAttribute("USER_SESSION_KEY");
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<User>list=session.createQuery("select u from User u where username=?").setString(0,username).list();
        User user=list.get(0);
        int uId=user.getId();
        Session session1=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Shopcart>list1=session1.createQuery("select s from Shopcart s where uId=? and status=0").setInteger(0,uId).list();
        return list1;
    }

    @Override
    public void toDelete(int id) {
        Shopcart shopcart=hibernateTemplate.get(Shopcart.class,id);
        hibernateTemplate.delete(shopcart);
    }

    @Override
    public void toUpdateStatus(int status) {

    }

    @Override
    public void toUpdate(Shopcart shopcart) {
        Shopcart shopcart1=hibernateTemplate.get(Shopcart.class,shopcart.getId());
        shopcart1.setNum(shopcart.getNum());
        hibernateTemplate.update(shopcart1);
    }

    @Override
    public void toDeleteAll(HttpServletRequest request) {
        String username=(String)request.getSession().getAttribute("USER_SESSION_KEY");
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<User>list=session.createQuery("select u from User u where username=?").setString(0,username).list();
        User user=list.get(0);
        int uId=user.getId();
        Session session1=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Shopcart>list1=session1.createQuery("select s from Shopcart where uId=?").setInteger(0,uId).list();
//        for (Shopcart shopcart:list1){
//            hibernateTemplate.delete(shopcart);
//        }
        for (int i=0;i<list1.size();i++){
            hibernateTemplate.delete(list1.get(i));
        }
    }
}
