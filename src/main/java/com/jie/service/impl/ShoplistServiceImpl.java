package com.jie.service.impl;

import com.jie.dao.ShoplistDao;
import com.jie.domain.Shoplist;
import com.jie.service.ShoplistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoplistServiceImpl implements ShoplistService {
    @Autowired
    private ShoplistDao shoplistDao;

    public void setShoplistDao(ShoplistDao shoplistDao) {
        this.shoplistDao = shoplistDao;
    }

    @Override
    public void toUpdate(Shoplist shoplist) {
        shoplistDao.toUpdate(shoplist);
    }
}
