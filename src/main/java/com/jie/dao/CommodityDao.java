package com.jie.dao;

import com.jie.domain.Commodity;

import java.util.List;

public interface CommodityDao {
    //获取商品
    public List<Commodity> toShow(int status);
    //查找商品
    public Commodity toFind(int id);

}
