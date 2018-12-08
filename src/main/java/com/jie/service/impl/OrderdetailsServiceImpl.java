package com.jie.service.impl;

import com.jie.dao.OrderdetailsDao;
import com.jie.domain.Orderdetails;
import com.jie.domain.Ordertime;
import com.jie.domain.Shoplist;
import com.jie.service.OrderdetailsService;
import jdk.nashorn.internal.scripts.JD;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class OrderdetailsServiceImpl implements OrderdetailsService {

    @Autowired
    private OrderdetailsDao orderdetailsDao;
    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setOrderdetailsDao(OrderdetailsDao orderdetailsDao) {
        this.orderdetailsDao = orderdetailsDao;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String toAddOne(Orderdetails orderdetails, int cId, int num, HttpServletRequest request) {
        String code=orderdetailsDao.toAddOne(orderdetails,cId,num,request);
        return code;
    }

    @Override
    public String toAddList(Orderdetails orderdetails, HttpServletRequest request) {
        String code=orderdetailsDao.toAddList(orderdetails,request);
        return code;
    }

    @Override
    public List<Orderdetails> toShow(int status,HttpServletRequest request) {
        List<Orderdetails>list=orderdetailsDao.toShow(status,request);
        return list;
    }

    @Override
    public void toUpdateStatus(int id, int status) {

    }

    @Override
    public void toDelete(int id) {
        orderdetailsDao.toDelete(id);
    }

    @Override
    public Orderdetails toShowOne(String code) {
        Orderdetails orderdetails=orderdetailsDao.toShowOne(code);
        return orderdetails;
    }

    @Override
    public void toUpdate(Orderdetails orderdetails) {
        orderdetailsDao.toUpdate(orderdetails);
    }
}
