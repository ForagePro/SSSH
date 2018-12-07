package com.jie.service.impl;

import com.jie.dao.ReceiveaddressDao;
import com.jie.domain.Receiveaddress;
import com.jie.domain.User;
import com.jie.service.ReceiveaddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ReceiveaddressServiceImpl implements ReceiveaddressService {
    @Autowired
    private ReceiveaddressDao receiveaddressDao;

    public void setReceiveaddressDao(ReceiveaddressDao receiveaddressDao) {
        this.receiveaddressDao = receiveaddressDao;
    }

    @Override
    public void toAdd(Receiveaddress receiveaddress) {
        receiveaddressDao.toAdd(receiveaddress);
    }

    @Override
    public void toUpdate(Receiveaddress receiveaddress) {
        receiveaddressDao.toUpdate(receiveaddress);
    }

    @Override
    public List<Receiveaddress> toShow(int uId) {
        List<Receiveaddress>list=receiveaddressDao.toShow(uId);
        return list;
    }

    @Override
    public void toDelete(int id) {
        receiveaddressDao.toDelete(id);
    }

    @Override
    public void toUpdateStatus(int id, int status) {
        receiveaddressDao.toUpdateStatus(id,status);
    }

    @Override
    public User toGetUser(HttpServletRequest request) {
        User user=receiveaddressDao.toGetUser(request);
        return user;
    }

    @Override
    public Receiveaddress toFind(int id) {
        Receiveaddress receiveaddress=receiveaddressDao.toFind(id);
        return receiveaddress;
    }
}
