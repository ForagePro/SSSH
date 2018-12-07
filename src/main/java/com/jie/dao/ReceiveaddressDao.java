package com.jie.dao;

import com.jie.domain.Receiveaddress;
import com.jie.domain.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ReceiveaddressDao {
    //添加地址
    public void toAdd(Receiveaddress receiveaddress);
    //修改地址
    public void toUpdate(Receiveaddress receiveaddress);
    //显示地址
    public List<Receiveaddress>toShow(int uId);
    //删除地址
    public void toDelete(int id);
    //通过用户名获取用户信息
    public User toGetUser(HttpServletRequest request);
    //修改默认地址
    public void toUpdateStatus(int id,int status);
    //获取信息
    public Receiveaddress toFind(int id);
}
