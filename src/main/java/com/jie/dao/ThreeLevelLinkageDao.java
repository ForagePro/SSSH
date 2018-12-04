package com.jie.dao;

import com.jie.domain.Areas;
import com.jie.domain.Cities;
import com.jie.domain.Provinces;

import java.util.List;

public interface ThreeLevelLinkageDao {
    //获取省份
    public List<Provinces> getProvinces();
    //获取市
    public List<Cities> getCity(String provinceid);
    //获取县
    public List<Areas> getArea(String cityid);
}
