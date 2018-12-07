package com.jie.service.impl;

import com.jie.dao.ShopcartDao;
import com.jie.domain.Shopcart;
import com.jie.service.ShopcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ShopcartServiceImpl implements ShopcartService {
    @Autowired
    private ShopcartDao shopcartDao;

    public void setShopcartDao(ShopcartDao shopcartDao) {
        this.shopcartDao = shopcartDao;
    }

    @Override
    public String toAdd(int cId,Shopcart shopcart, HttpServletRequest request) {
        String msg=shopcartDao.toAdd(cId,shopcart,request);
        return msg;
    }

    @Override
    public List<Shopcart> toShow(HttpServletRequest request) {
        List<Shopcart>list=shopcartDao.toShow(request);
        return list;
    }

    @Override
    public void toDelete(int id) {
        shopcartDao.toDelete(id);
    }

    @Override
    public void toUpdateStatus(int status) {
        shopcartDao.toUpdateStatus(status);
    }

    @Override
    public void toUpdate(Shopcart shopcart) {
        shopcartDao.toUpdate(shopcart);
    }

    @Override
    public void toDeleteAll(HttpServletRequest request) {
        shopcartDao.toDeleteAll(request);
    }
}
