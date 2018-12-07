package com.jie.dao.impl;

import com.jie.dao.CommodityDao;
import com.jie.domain.Commodity;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

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
            list=session.createQuery("select c from Commodity c where status=?").setInteger(0,status).list();
        }
        return list;
    }

    @Override
    public Commodity toFind(int id) {
        Commodity commodity=hibernateTemplate.get(Commodity.class,id);
        return commodity;
    }


}
