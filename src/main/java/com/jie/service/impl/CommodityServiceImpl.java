package com.jie.service.impl;

import com.jie.dao.CommodityDao;
import com.jie.domain.Commodity;
import com.jie.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityDao commodityDao;

    public void setCommodityDao(CommodityDao commodityDao) {
        this.commodityDao = commodityDao;
    }

    @Override
    public List<Commodity> toShow(int status) {
        List<Commodity>list=commodityDao.toShow(status);
        return list;
    }

    @Override
    public Commodity toFind(int id) {
        Commodity commodity=commodityDao.toFind(id);
        return commodity;
    }

}
