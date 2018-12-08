package com.jie.controller;

import com.alibaba.fastjson.JSON;
import com.jie.domain.Orderdetails;
import com.jie.service.OrderdetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/orderdetails")
public class OrderdetailsController {
    @Autowired
    private OrderdetailsService orderdetailsService;

    public void setOrderdetailsService(OrderdetailsService orderdetailsService) {
        this.orderdetailsService = orderdetailsService;
    }

    @RequestMapping("/toAddOne")
    @ResponseBody
    public String toAddOne(Orderdetails orderdetails, int cId, int num, HttpServletRequest request){
        String code=orderdetailsService.toAddOne(orderdetails,cId,num,request);
        return JSON.toJSONString(code);
    }

    @RequestMapping("/toAddList")
    @ResponseBody
    public String toAddList(Orderdetails orderdetails,HttpServletRequest request){
        String code=orderdetailsService.toAddList(orderdetails,request);
        return JSON.toJSONString(code);
    }

    @RequestMapping("/toShowOne")
    @ResponseBody
    public String toShowOne(String code){
        Orderdetails orderdetails=orderdetailsService.toShowOne(code);
        return JSON.toJSONString(orderdetails);
    }

    @RequestMapping("/toUpdate")
    @ResponseBody
    public void toUpdate(Orderdetails orderdetails){
        orderdetailsService.toUpdate(orderdetails);
    }

    @RequestMapping("toShow")
    @ResponseBody
    public String toShow(int status,HttpServletRequest request){
        List<Orderdetails>list=orderdetailsService.toShow(status,request);
        return JSON.toJSONString(list);
    }
    @RequestMapping("toDelete")
    @ResponseBody
    public void toDelete(int id){
        orderdetailsService.toDelete(id);
    }

}
