package com.jie.service;

import com.jie.domain.Commodity;

import java.util.List;

public interface CommodityService {
    //获取商品
    public List<Commodity> toShow(int status);
    //查找商品
    public Commodity toFind(int id);

}
