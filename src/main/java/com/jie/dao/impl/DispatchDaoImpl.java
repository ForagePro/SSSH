package com.jie.dao.impl;

import com.jie.dao.DispatchDao;
import com.jie.domain.Dispatch;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DispatchDaoImpl implements DispatchDao {
    @Resource
    private HibernateTemplate hibernateTemplate;
    @Override
    public void addDispatch(Dispatch dispatch) {
        hibernateTemplate.save(dispatch);
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
}
