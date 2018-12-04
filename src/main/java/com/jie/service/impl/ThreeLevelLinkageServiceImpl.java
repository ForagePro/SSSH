package com.jie.service.impl;

import com.jie.dao.ThreeLevelLinkageDao;
import com.jie.domain.Areas;
import com.jie.domain.Cities;
import com.jie.domain.Provinces;
import com.jie.service.ThreeLevelLinkageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ThreeLevelLinkageServiceImpl implements ThreeLevelLinkageService {
    @Resource
    private ThreeLevelLinkageDao threeLevelLinkageDao;
    @Override
    public List<Provinces> getProvinces() {
        return threeLevelLinkageDao.getProvinces();
    }

    @Override
    public List<Cities> getCity(String provinceid) {
        return threeLevelLinkageDao.getCity(provinceid);
    }

    @Override
    public List<Areas> getArea(String cityid) {
        return threeLevelLinkageDao.getArea(cityid);
    }

    public void setThreeLevelLinkageDao(ThreeLevelLinkageDao threeLevelLinkageDao) {
        this.threeLevelLinkageDao = threeLevelLinkageDao;
    }
}
