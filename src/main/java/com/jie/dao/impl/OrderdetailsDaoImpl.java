package com.jie.dao.impl;

import com.jie.dao.OrderdetailsDao;
import com.jie.domain.*;
import org.aspectj.apache.bcel.classfile.Code;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class OrderdetailsDaoImpl implements OrderdetailsDao {
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

    public String getOrderIdByUUId(int i) {

        int machineId = i;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数   10、
            hashCodeV = - hashCodeV;
        }          // 0 代表前面补充0
        //      13、         // 4 代表长度为4        14、         // d 代表参数为正数型   15、
        String code= machineId+String.format("%015d", hashCodeV);
        //System.out.println(code);
        return code;
    }

    @Override
    public String toAddOne(Orderdetails orderdetails,int cId,int num,HttpServletRequest request) {
        Commodity commodity=hibernateTemplate.get(Commodity.class,cId);
        String username=(String)request.getSession().getAttribute("USER_SESSION_KEY");
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<User>list=session.createQuery("from User where username=?").setString(0,username).list();
        User user=list.get(0);
        String code=getOrderIdByUUId(1);
        double sum=num*(commodity.getPrice()-commodity.getDownprice());
        double d= Double.parseDouble(String.format("%.1f",sum));
        String sql="insert into orderdetails (o_code,u_id,sum) values ('"+ code +"',"+user.getId()+","+d+")";
        jdbcTemplate.update(sql);
        Session session1=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Orderdetails>list2 =session1.createQuery("from Orderdetails where oCode=?").setString(0,code).list();
        Orderdetails orderdetails1=list2.get(0);
        String sql1="insert into shoplist (c_id,num,o_code,price,od_id) values ("+cId+","+num+","+code+","+commodity.getPrice()+","+orderdetails1.getId()+")";
        jdbcTemplate.update(sql1);
        String sql2="insert into ordertime (o_code) values ('"+code+"')";
        jdbcTemplate.update(sql2);
        Session session2=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Ordertime>list3=session2.createQuery("from Ordertime where oCode=?").setString(0,code).list();
        Ordertime ordertime=list3.get(0);
        String sql3="update orderdetails set od_id="+orderdetails1.getId()+",ot_id="+ordertime.getId()+" where o_code='"+code+"'";
        jdbcTemplate.update(sql3);
        return code;
    }

    @Override
    public String toAddList(Orderdetails orderdetails,HttpServletRequest request) {
        String username=(String)request.getSession().getAttribute("USER_SESSION_KEY");
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<User>list=session.createQuery("select u from User u where username=?").setString(0,username).list();
        User user=list.get(0);
        Session session1=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Shopcart>list1=session1.createQuery("from Shopcart where u_id=?").setInteger(0,user.getId()).list();
        double sum=orderdetails.getSum();
        String code=getOrderIdByUUId(3);
        String sql="insert into orderdetails (o_code,u_id,sum) values ('"+ code +"',"+user.getId()+","+sum+")";
        jdbcTemplate.update(sql);
        Session session2=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Orderdetails>list2 =session2.createQuery("from Orderdetails where oCode=?").setString(0,code).list();
        Orderdetails orderdetails1=list2.get(0);
        for (int i=0;i<list1.size();i++){
            String sql1="insert into shoplist (c_id,num,o_code,price,od_id) values ("+list1.get(i).getCommodity().getId()+","+list1.get(i).getNum()+","+code+","+list1.get(i).getCommodity().getPrice()+","+orderdetails1.getId()+")";
            jdbcTemplate.update(sql1);
        }
        for (int i=0;i<list1.size();i++){
            hibernateTemplate.delete(list1.get(i));
        }
        String sql2="insert into ordertime (o_code) values ('"+code+"')";
        jdbcTemplate.update(sql2);
        Session session3=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Ordertime>list3=session3.createQuery("from Ordertime where oCode=?").setString(0,code).list();
        Ordertime ordertime=list3.get(0);
        String sql3="update orderdetails set od_id="+orderdetails1.getId()+",ot_id="+ordertime.getId()+" where o_code='"+code+"'";
        jdbcTemplate.update(sql3);
        return code;
    }

    @Override
    public List<Orderdetails> toShow(int status,HttpServletRequest request) {
        String username=(String)request.getSession().getAttribute("USER_SESSION_KEY");
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<User>list=session.createQuery("select u from User u where username=?").setString(0,username).list();
        User user=list.get(0);
        List<Orderdetails>list1=new ArrayList<>();
        if (status < 0) {
            list1=session.createQuery("from Orderdetails where uid=?").setInteger(0,user.getId()).list();
        }else {
            list1=session.createQuery("from Orderdetails where uid=? and status=?").setInteger(0,user.getId()).setInteger(1,status).list();
        }
        return list1;
    }

    @Override
    public void toUpdateStatus(int id, int status) {

    }

    @Override
    public void toDelete(int id) {
        Orderdetails orderdetails=hibernateTemplate.get(Orderdetails.class,id);
        hibernateTemplate.delete(orderdetails);
    }

    @Override
    public Orderdetails toShowOne(String code) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Orderdetails>list =session.createQuery("from Orderdetails where oCode=?").setString(0,code).list();
        Orderdetails orderdetails=list.get(0);
        return orderdetails;
    }

    @Override
    public void toUpdate(Orderdetails orderdetails) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Orderdetails>list =session.createQuery("from Orderdetails where oCode=?").setString(0,orderdetails.getoCode()).list();
        Orderdetails orderdetails1=list.get(0);
        orderdetails1.setSum(orderdetails.getSum());
        orderdetails1.setStatus(2);
//        if (orderdetails.getBuymessage().length()!=0){
//            orderdetails1.setBuymessage(orderdetails.getBuymessage());
//        }else {
//            orderdetails.setBuymessage("暂无留言!");
//        }
        hibernateTemplate.update(orderdetails1);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        List<Ordertime> list1=session.createQuery("from Ordertime where oCode=?").setString(0,orderdetails.getoCode()).list();
        Ordertime ordertime=list1.get(0);
        ordertime.setStatus(2);
        ordertime.setPayTime(timestamp);
        hibernateTemplate.update(ordertime);
    }
}
