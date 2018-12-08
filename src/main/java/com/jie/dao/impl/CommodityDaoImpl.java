package com.jie.dao.impl;

import com.jie.dao.CommodityDao;
import com.jie.domain.Breed;
import com.jie.domain.Commodity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CommodityDaoImpl implements CommodityDao {
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
    public List<Commodity> toShow(int status) {
        List<Commodity>list=null;
        if (status<0){
            list=hibernateTemplate.loadAll(Commodity.class);
        }else {
            Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
            list=session.createQuery("from Commodity where status=?").setInteger(0,status).list();
        }
        return list;
    }

    @Override
    public Commodity toFind(int id) {
        Commodity commodity=hibernateTemplate.get(Commodity.class,id);
        return commodity;
    }

    public void addCommodity(Commodity commodity) {
        //Breed breed=hibernateTemplate.get(Breed.class,commodity.getBreed().getId());
       // commodity.setBreed(breed);
        hibernateTemplate.save(commodity);
    }

    @Override
    public List<Commodity> select(int bId, int status, String keyword, int num, int page) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List list=new ArrayList();
        StringBuilder hql=new StringBuilder("from Commodity where 1=1 ");
        if(bId!=0){
            list.add(bId);
            hql.append("and bId=? ");
        }
        if(status!=-1){
            list.add(status);
            hql.append("and status=? ");
        }
        if(!keyword.equals("")){
            hql.append("and name like '%"+keyword+"%' ");
        }
        hql.append("order by sort asc,createTime desc ");
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
    public void updateStatus(int id, int status) {
        Commodity commodity=hibernateTemplate.get(Commodity.class,id);
        commodity.setStatus(status);
        hibernateTemplate.update(commodity);
    }

    @Override
    public Commodity getCommodity(int id) {
        return hibernateTemplate.get(Commodity.class,id);
    }

    @Override
    public void editCommodity(Commodity commodity) {
        Breed breed=hibernateTemplate.get(Breed.class,commodity.getBreed().getId());
        Commodity commodity1=hibernateTemplate.get(Commodity.class,commodity.getId());
        commodity1.setName(commodity.getName());
        commodity1.setPrice(commodity.getPrice());
        commodity1.setStatus(commodity.getStatus());
        if(commodity.getImgPath()!=null){
            commodity1.setImgPath(commodity.getImgPath());
        }
        commodity1.setDownprice(commodity.getDownprice());
        commodity1.setMinsaleweight(commodity.getMinsaleweight());
        commodity1.setStockbalance(commodity.getStockbalance());
        commodity1.setDetails(commodity.getDetails());
        commodity1.setSort(commodity.getSort());
        commodity1.setUnit(commodity.getUnit());
        commodity1.setBreed(breed);
        hibernateTemplate.update(commodity1);
    }

    @Override
    public void delete(int id) {
        Commodity commodity=hibernateTemplate.get(Commodity.class,id);
        hibernateTemplate.delete(commodity);
    }
}
