package com.jie.dao.impl;

import com.jie.dao.ShoplistDao;
import com.jie.domain.Shoplist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ShoplistDaoImpl implements ShoplistDao {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void toUpdate(Shoplist shoplist) {
        Shoplist shoplist1=hibernateTemplate.get(Shoplist.class,shoplist.getId());
        shoplist1.setNum(shoplist.getNum());
        hibernateTemplate.update(shoplist1);
    }
}
