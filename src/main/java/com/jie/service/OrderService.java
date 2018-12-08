package com.jie.service;

import com.jie.domain.Orderdetails;

import java.util.List;

public interface OrderService {
    //获取订单
    public List<Orderdetails> getOrderAll(int type, String keyword, int status, int num, int page);
    //获取指定订单
    public Orderdetails getOrder(int id);
    public void updateStatus(String code);
    public void updateStatusByCode(String code,int status);
    public void updateCode(int t_id,int o_id,String code,String name);

    public List<Orderdetails> getOrderByUId(int id);
    public List<Orderdetails> getOrderByUser(int id,int status);
    public Orderdetails getOrderByCode(String code);
    public void receiptStatus(String code);
    public void closeOrder(String code,int status);
}
