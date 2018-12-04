package com.jie.service.impl;

import com.jie.dao.RoundsowDao;
import com.jie.domain.Roundsow;
import com.jie.service.RoundsowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoundsowServiceImpl implements RoundsowService {

    @Autowired
    private RoundsowDao roundsowDao;

    public void setRoundsowDao(RoundsowDao roundsowDao) {
        this.roundsowDao = roundsowDao;
    }

    @Override
    public void toAdd(Roundsow roundsow) {
        roundsowDao.toAdd(roundsow);
    }

    @Override
    public List<Roundsow> toShow(int pageNo, int pageSize) {
        List<Roundsow>list=roundsowDao.toShow(pageNo,pageSize);
        return list;
    }

    @Override
    public String toUpdataStatus(int status, int id) {
        String msg=roundsowDao.toUpdataStatus(status,id);
        return msg;
    }

    @Override
    public List<Roundsow> toFind() {
        List<Roundsow>list=roundsowDao.toFind();
        return list;
    }

    @Override
    public List<Roundsow> toQuery(int status) {
        List<Roundsow>list=roundsowDao.toQuery(status);
        return list;
    }

    @Override
    public void toDelete(int id) {
        roundsowDao.toDelete(id);
    }
}
