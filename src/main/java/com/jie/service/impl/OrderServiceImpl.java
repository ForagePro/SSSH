package com.jie.service.impl;

import com.jie.dao.OrderDao;
import com.jie.domain.Orderdetails;
import com.jie.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Override
    public List<Orderdetails> getOrderAll(int type, String keyword, int status, int num, int page) {
        return orderDao.getOrderAll(type,keyword,status,num,page);
    }

    @Override
    public Orderdetails getOrder(int id) {
        return orderDao.getOrder(id);
    }

    @Override
    public void updateStatus(String code) {
        orderDao.updateStatus(code);
    }

    @Override
    public void updateStatusByCode(String code, int status) {
        orderDao.updateStatusByCode(code,status);
    }

    @Override
    public void updateCode(int t_id,int o_id,String code,String name) {
        orderDao.updateCode(t_id,o_id,code,name);
    }

    @Override
    public List<Orderdetails> getOrderByUId(int id) {
        return orderDao.getOrderByUId(id);
    }

    @Override
    public List<Orderdetails> getOrderByUser(int id, int status) {
        return orderDao.getOrderByUser(id,status);
    }

    @Override
    public Orderdetails getOrderByCode(String code) {
        return orderDao.getOrderByCode(code);
    }

    @Override
    public void receiptStatus(String code) {
        orderDao.receiptStatus(code);
    }

    @Override
    public void closeOrder(String code,int status) {
        orderDao.closeOrder(code,status);
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
