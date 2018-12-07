package com.jie.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jie.domain.Commodity;
import com.jie.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.support.ObjectNameManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    public void setCommodityService(CommodityService commodityService) {
        this.commodityService = commodityService;
    }

    @RequestMapping("/toShow")
    @ResponseBody
    public String toShow(int status) {
        List<Commodity> list = commodityService.toShow(status);
        return JSON.toJSONString(list);
    }

    @RequestMapping("/toFind")
    @ResponseBody
    public String toFind(int id) {
        Commodity commodity = commodityService.toFind(id);
        return JSON.toJSONString(commodity);
    }



}
