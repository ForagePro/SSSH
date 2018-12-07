package com.jie.controller;


import com.alibaba.fastjson.JSON;
import com.jie.domain.Shopcart;
import com.jie.service.ShopcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/shopcart")
public class ShopcartController {
    @Autowired
    private ShopcartService shopcartService;

    public void setShopcartService(ShopcartService shopcartService) {
        this.shopcartService = shopcartService;
    }

    @RequestMapping("/toAdd")
    @ResponseBody
    public String toAdd(int cId,Shopcart shopcart, HttpServletRequest request){
        String msg=shopcartService.toAdd(cId,shopcart,request);
        return JSON.toJSONString(msg);
    }

    @RequestMapping("/toShow")
    @ResponseBody
    public String toShow(HttpServletRequest request){
        List<Shopcart>list=shopcartService.toShow(request);
        return JSON.toJSONString(list);
    }
    @RequestMapping("/toUpdate")
    @ResponseBody
    public void toUpdate(Shopcart shopcart){
        shopcartService.toUpdate(shopcart);
    }

    @RequestMapping("/toDeleteAll")
    @ResponseBody
    public void toDeleteAll(HttpServletRequest request){
        //shopcartService.toDeleteAll(request);
    }

}
