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

    @Override
    public void addCommodity(Commodity commodity) {
        commodityDao.addCommodity(commodity);
    }

    @Override
    public List<Commodity> select(int bId, int status, String keyword, int num, int page) {
        return commodityDao.select(bId,status,keyword,num,page);
    }

    @Override
    public void updateStatus(int id, int status) {
        commodityDao.updateStatus(id,status);
    }

    @Override
    public Commodity getCommodity(int id) {
        return commodityDao.getCommodity(id);
    }

    @Override
    public void editCommodity(Commodity commodity) {
        commodityDao.editCommodity(commodity);
    }

    @Override
    public void delete(int id) {
        commodityDao.delete(id);
    }
}
