package com.jie.controller;

import com.alibaba.fastjson.JSON;
import com.jie.domain.Areas;
import com.jie.domain.Cities;
import com.jie.service.ThreeLevelLinkageService;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/threeLevelLinkage")
public class ThreeLevelLinkageController {
    @Resource
    private ThreeLevelLinkageService threeLevelLinkageService;
    //获取省份
    @RequestMapping("/getProvinces.do")
    @ResponseBody
    public String getProvinces(HttpServletRequest request){
        if(threeLevelLinkageService.getProvinces()!=null){
            return JSON.toJSONString(threeLevelLinkageService.getProvinces());
        }
        return JSON.toJSONString("false");
    }

    @RequestMapping("/getCity.do")
    @ResponseBody
    public String getCity(String provinceid){
        List<Cities> list=threeLevelLinkageService.getCity(provinceid);
        if(list!=null){
            return JSON.toJSONString(list);
        }
        return JSON.toJSONString("false");
    }

    @RequestMapping("/getArea.do")
    @ResponseBody
    public String getArea(String cityid){
        List<Areas> list=threeLevelLinkageService.getArea(cityid);
        if(list!=null){
            return JSON.toJSONString(list);
        }
        return JSON.toJSONString("false");
    }
    public void setThreeLevelLinkageService(ThreeLevelLinkageService threeLevelLinkageService) {
        this.threeLevelLinkageService = threeLevelLinkageService;
    }
}
