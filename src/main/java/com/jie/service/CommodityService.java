package com.jie.service;

import com.jie.domain.Commodity;

import java.util.List;

public interface CommodityService {
    //获取商品
    public List<Commodity> toShow(int status);
    //查找商品
    public Commodity toFind(int id);

    //保存
    public void addCommodity(Commodity commodity);
    //多条件查询
    public List<Commodity> select(int bId, int status, String keyword, int num, int page);
    //修改状态
    public void updateStatus(int id,int status);
    //获取指定Commodity
    public Commodity getCommodity(int id);
    //修改
    public void editCommodity(Commodity commodity);
    //删除
    public void delete(int id);
}
