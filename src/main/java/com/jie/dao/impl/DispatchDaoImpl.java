package com.jie.dao.impl;

import com.jie.dao.DispatchDao;
import com.jie.domain.Dispatch;
import com.jie.domain.Orderdetails;
import com.jie.domain.Receiveaddress;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DispatchDaoImpl implements DispatchDao {
    @Resource
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public void addDispatch(Dispatch dispatch) {
        hibernateTemplate.save(dispatch);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Dispatch> getList(String province,String city,String area,String way,int page,int num) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List list=new ArrayList();
        StringBuilder hql=new StringBuilder("from Dispatch where 1=1 ");
        if(!province.equals("选择省")){
            hql.append("and destination like '%"+province+"%' ");
        }
        if(!city.equals("选择市")){
            hql.append("and destination like '%"+city+"%' ");
        }
        if(!area.equals("选择县区")){
            hql.append("and destination like '%"+area+"%' ");
        }
        if(!way.equals("配送方式")){
            list.add(way);
            hql.append("and way=? ");
        }
        hql.append("order by createTime desc ");
        if(list.size()>0){
            Query query=session.createQuery(hql.toString()).setFirstResult((page-1)*num).setMaxResults(num);
            for(int i=0;i<list.size();i++){
                query.setParameter(i,list.get(i));
            }
            List<Dispatch> list1=query.list();
            return list1;
        }
        return session.createQuery(hql.toString()).list();
    }

    @Override
    public Dispatch getDispatch(int id) {
        return hibernateTemplate.get(Dispatch.class,id);
    }

    @Override
    public void update(Dispatch dispatch) {
        Dispatch dispatch1=hibernateTemplate.get(Dispatch.class,dispatch.getId());
        dispatch1.setWay(dispatch.getWay());
        dispatch1.setCost(dispatch.getCost());
        dispatch1.setContent(dispatch.getContent());
        hibernateTemplate.update(dispatch1);
    }

    @Override
    public Dispatch testDest(String destination) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Dispatch> list=session.createQuery("from Dispatch where destination=?").setParameter(0,destination).list();
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public Dispatch toPay(int id,String code) {
        Session session1=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Orderdetails>list2 =session1.createQuery("from Orderdetails where oCode=?").setString(0,code).list();
        Orderdetails orderdetails=list2.get(0);
        Receiveaddress receiveaddress=hibernateTemplate.get(Receiveaddress.class,id);
        String city=receiveaddress.getCity();
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Dispatch>list=session.createQuery("from Dispatch where destination like ?").setString(0,"%"+city+"%").list();
        Dispatch dispatch=null;
        if (list.size()==0){
            dispatch=new Dispatch();
            dispatch.setWay("快递");
            BigDecimal b= BigDecimal.valueOf(16.0);
            dispatch.setCost(b);
            orderdetails.setdId(16.0);
//            double sum= Double.parseDouble(String.format("%.2f", 16.0+orderdetails.getSum()));
//            orderdetails.setSum(sum);
        }else {
            dispatch=list.get(0);
            double did=dispatch.getCost().doubleValue();
            orderdetails.setdId(did);
//            double sum= Double.parseDouble(String.format("%.2f", did+orderdetails.getSum()));
//            orderdetails.setSum(did+orderdetails.getSum());
        }
        orderdetails.setReceiveaddress(receiveaddress);
        hibernateTemplate.update(orderdetails);
//        String sql="update orderdetails set r_id="+id+" where o_code='"+code+"'";
//        jdbcTemplate.update(sql);
        return dispatch;
    }
}
