package com.jie.controller;

import com.jie.domain.Shoplist;
import com.jie.service.ShoplistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/shoplist")
public class ShoplistController {
    @Autowired
    private ShoplistService shoplistService;

    public void setShoplistService(ShoplistService shoplistService) {
        this.shoplistService = shoplistService;
    }

    @RequestMapping("/toUpdate")
    @ResponseBody
    public void toUpdate(Shoplist shoplist){
        shoplistService.toUpdate(shoplist);
    }

}
