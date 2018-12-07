package com.jie.dao;

import com.jie.domain.Shopcart;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ShopcartDao {
    //加入购物车
    public String toAdd(int cId,Shopcart shopcart, HttpServletRequest request);
    //查询购物车
    public List<Shopcart> toShow(HttpServletRequest request);
    //删除
    public void toDelete(int id);
    //改变购买状态
    public void toUpdateStatus(int status);
    //修改购物车
    public void toUpdate(Shopcart shopcart);
    //清空购物车
    public void toDeleteAll(HttpServletRequest request);
}
