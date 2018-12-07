package com.jie.dao;

import com.jie.domain.Dispatch;

import java.util.List;

public interface DispatchDao {
    //添加配送费
    public void addDispatch(Dispatch dispatch);
    //获取配送费列表
    public List<Dispatch> getList(String province,String city,String area,String way,int page,int num);
    //获取
    public Dispatch getDispatch(int id);
    //修改
    public void update(Dispatch dispatch);
    //验证目的地
    public Dispatch testDest(String destination);
    //匹配运费
    public Dispatch toPay(int id,String code);
}
