package com.jie.service.impl;

import com.jie.dao.ConservatorDao;
import com.jie.service.ConservatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class ConservatorServiceImpl implements ConservatorService {
    @Autowired
    private ConservatorDao conservatorDao;
    @Override
    public List<Map> toLogin(String account, String password, HttpServletRequest request, String code) {
        List<Map>list=conservatorDao.toLogin(account,password,request,code);
        return list;
    }

    public void setConservatorDao(ConservatorDao conservatorDao) {
        this.conservatorDao = conservatorDao;
    }
}
