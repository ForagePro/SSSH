package com.jie.dao;

import com.jie.domain.Orderdetails;
import com.jie.domain.Shoplist;

import java.util.List;

public interface OrderDao {
    //获取订单
    public List<Orderdetails> getOrderAll(int type,String keyword,int status,int num,int page);
    //获取指定订单
    public Orderdetails getOrder(int id);
    public void updateStatus(String code);
    public void updateStatusByCode(String code,int status);
    public void updateCode(int id,String code,String name);

    public List<Orderdetails> getOrderByUId(int id);
}
