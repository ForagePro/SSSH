package com.jie.service.impl;

import com.jie.dao.DispatchDao;
import com.jie.domain.Dispatch;
import com.jie.service.DispatchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DispatchServiceImpl implements DispatchService {
    @Resource
    private DispatchDao dispatchDao;
    @Override
    public void addDispatch(Dispatch dispatch) {
        dispatchDao.addDispatch(dispatch);
    }

    @Override
    public List<Dispatch> getList(String province, String city, String area,String way, int page, int num) {
        return dispatchDao.getList(province,city,area,way,page,num);
    }

    @Override
    public Dispatch getDispatch(int id) {
        return dispatchDao.getDispatch(id);
    }

    @Override
    public void update(Dispatch dispatch) {
        dispatchDao.update(dispatch);
    }

    @Override
    public Dispatch testDest(String destination) {
        return dispatchDao.testDest(destination);
    }

    public void setDispatchDao(DispatchDao dispatchDao) {
        this.dispatchDao = dispatchDao;
    }

    @Override
    public Dispatch toPay(int id,String code) {
        Dispatch dispatch=dispatchDao.toPay(id,code);
        return dispatch;
    }
}
