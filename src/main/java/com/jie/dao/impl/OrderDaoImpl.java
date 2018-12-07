package com.jie.dao.impl;

import com.jie.dao.OrderDao;
import com.jie.domain.Orderdetails;
import com.jie.domain.Ordertime;
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
        List<Ordertime> list=session.createQuery("from Ordertime where code=?").setParameter(0,code).list();
        if(list!=null&&list.size()>0){
            Ordertime ordertime=list.get(0);
            ordertime.setStatus(6);
            ordertime.setSretTime(timestamp);
            hibernateTemplate.update(ordertime);
        }
    }

    @Override
    public void updateStatusByCode(String code, int status) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Ordertime> list=session.createQuery("from Ordertime where code=?").setParameter(0,code).list();
        if(list!=null&&list.size()>0){
            Ordertime ordertime=list.get(0);
            ordertime.setStatus(status);
            hibernateTemplate.update(ordertime);
        }
    }

    @Override
    public void updateCode(int id,String code,String name) {
        Ordertime ordertime=hibernateTemplate.get(Ordertime.class,id);
        Date date=new Date();
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.format(date);
        Timestamp timestamp=new Timestamp(date.getTime());
        ordertime.setStatus(3);
        ordertime.setCode(code);
        ordertime.setName(name);
        ordertime.setSendTime(timestamp);
        hibernateTemplate.update(ordertime);

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

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}
