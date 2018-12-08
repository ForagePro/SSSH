package com.jie.dao.impl;

import com.jie.dao.OrderDao;
import com.jie.domain.Commodity;
import com.jie.domain.Orderdetails;
import com.jie.domain.Ordertime;
import com.jie.domain.Shoplist;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.SessionScope;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Resource
    private HibernateTemplate hibernateTemplate;
    @Override
    public List<Orderdetails> getOrderAll(int type, String keyword, int status, int num, int page) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        StringBuilder hql=new StringBuilder("from Orderdetails where 1=1 ");
        List list=new ArrayList();
        if(type==1){
            hql=hql.append("and receiveaddress.name like '%"+keyword+"%' ");
        }else if(type==2){
            list.add(keyword);
            hql=hql.append("and receiveaddress.phone=? ");
        }else if(type==3){
            list.add(keyword);
            hql=hql.append("and oCode=? ");
        }
        if(status!=0){
            list.add(status);
            hql=hql.append("and ordertime.status=? ");
        }
        hql=hql.append("order by createTime desc");
        if(list.size()>0){
            Query query=session.createQuery(hql.toString()).setFirstResult((page-1)*num).setMaxResults(num);
            for(int i=0;i<list.size();i++){
                query.setParameter(i,list.get(i));
            }
            return query.list();
        }
        return session.createQuery(hql.toString()).list();
    }

    @Override
    public Orderdetails getOrder(int id) {
        return hibernateTemplate.get(Orderdetails.class,id);
    }

    @Override
    public void updateStatus(String code) {
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.format(date);
        Timestamp timestamp=new Timestamp(date.getTime());
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Orderdetails> list=session.createQuery("from Orderdetails where oCode=?").setParameter(0,code).list();
        if(list!=null&&list.size()>0){
            Orderdetails orderdetails=list.get(0);
            orderdetails.setStatus(6);
            orderdetails.getOrdertime().setStatus(6);
            orderdetails.getOrdertime().setSretTime(timestamp);
            hibernateTemplate.update(orderdetails);
        }
    }

    @Override
    public void updateStatusByCode(String code, int status) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Orderdetails> list=session.createQuery("from Orderdetails where oCode=?").setParameter(0,code).list();
        if(list!=null&&list.size()>0){
            Orderdetails orderdetails=list.get(0);
            orderdetails.setStatus(status);
            orderdetails.getOrdertime().setStatus(status);
            hibernateTemplate.update(orderdetails);
        }
    }

    @Override
    public void updateCode(int t_id,int o_id,String code,String name) {
        Ordertime ordertime=hibernateTemplate.get(Ordertime.class,t_id);
        Orderdetails orderdetails=hibernateTemplate.get(Orderdetails.class,o_id);
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.format(date);
        Timestamp timestamp=new Timestamp(date.getTime());
        orderdetails.setStatus(3);
        ordertime.setStatus(3);
        ordertime.setCode(code);
        ordertime.setName(name);
        ordertime.setSendTime(timestamp);
         Iterator<Shoplist> iterator=orderdetails.getSet().iterator();
         while (iterator.hasNext()){
             Shoplist shoplist=iterator.next();
             int num=shoplist.getNum();
             Commodity commodity =shoplist.getCommodity();
             double stock=commodity.getStockbalance();
             stock=stock-num;
             commodity.setStockbalance(stock);
             shoplist.setCommodity(commodity);
             orderdetails.getSet().add(shoplist);
         }
        orderdetails.setOrdertime(ordertime);
        hibernateTemplate.update(orderdetails);

    }

    @Override
    public List<Orderdetails> getOrderByUId(int id) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Orderdetails> list=session.createQuery("select o from Orderdetails o where u_id=?").setParameter(0,id).list();
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public List<Orderdetails> getOrderByUser(int id,int status) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Orderdetails> list=session.createQuery("select o from Orderdetails o where u_id=? and status=?").setParameter(0,id).setParameter(1,status).list();
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public Orderdetails getOrderByCode(String code) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Orderdetails> list=session.createQuery("from Orderdetails where oCode=?").setParameter(0,code).list();
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public void receiptStatus(String code) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Orderdetails> list=session.createQuery("from Orderdetails where oCode=?").setParameter(0,code).list();
        if(list!=null&&list.size()>0){
            Date date=new Date();
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormat.format(date);
            Timestamp timestamp=new Timestamp(date.getTime());
            Orderdetails orderdetails=list.get(0);
            orderdetails.setStatus(4);
            orderdetails.getOrdertime().setRecTime(timestamp);
            orderdetails.getOrdertime().setStatus(4);
            hibernateTemplate.update(orderdetails);
        }
    }

    @Override
    public void closeOrder(String code,int status) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Orderdetails> list=session.createQuery("from Orderdetails where oCode=?").setParameter(0,code).list();
        if(list!=null&&list.size()>0){
            Date date=new Date();
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormat.format(date);
            Timestamp timestamp=new Timestamp(date.getTime());
            Orderdetails orderdetails=list.get(0);
            orderdetails.setStatus(status);
            orderdetails.getOrdertime().setStatus(status);
            if(status==2){
                orderdetails.getOrdertime().setPayTime(timestamp);
            }else if(status==3){
                orderdetails.getOrdertime().setSendTime(timestamp);
            }else if(status==4){
                orderdetails.getOrdertime().setRecTime(timestamp);
            }else if(status==5){
                orderdetails.getOrdertime().setRetTime(timestamp);
            }else if(status==6){
                orderdetails.getOrdertime().setSretTime(timestamp);
            }else if(status==7){
                orderdetails.getOrdertime().setCloseTime(timestamp);
            }
            hibernateTemplate.update(orderdetails);
        }
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}
