package com.jie.dao.impl;

import com.jie.dao.ThreeLevelLinkageDao;
import com.jie.domain.Areas;
import com.jie.domain.Cities;
import com.jie.domain.Provinces;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ThreeLevelLinkageDapImpl implements ThreeLevelLinkageDao {
    @Resource
    private HibernateTemplate hibernateTemplate;
    @Override
    public List<Provinces> getProvinces() {
        List<Provinces> list=hibernateTemplate.loadAll(Provinces.class);
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public List<Cities> getCity(String provinceid) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Cities> list=session.createQuery("from Cities where provinceid=?").setParameter(0,provinceid).list();
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    @Override
    public List<Areas> getArea(String cityid) {
        Session session=hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Areas> list=session.createQuery("from Areas where cityid=?").setParameter(0,cityid).list();
        if(list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }
}
