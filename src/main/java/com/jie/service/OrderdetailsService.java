package com.jie.service;

import com.jie.domain.Orderdetails;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderdetailsService {
    //立即购买
    public String toAddOne(Orderdetails orderdetails, int cId, int num, HttpServletRequest request);
    //购物车购买
    public String toAddList(Orderdetails orderdetails,HttpServletRequest request);
    //显示订单
    public List<Orderdetails> toShow(int status,HttpServletRequest request);
    //修改状态
    public void toUpdateStatus(int id,int status);
    //删除订单
    public void toDelete(int id);
    //付款页面订单
    public Orderdetails toShowOne(String code);

    public void toUpdate(Orderdetails orderdetails);
}
