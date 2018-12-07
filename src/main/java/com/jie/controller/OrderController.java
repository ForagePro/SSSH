package com.jie.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jie.domain.Orderdetails;
import com.jie.domain.User;
import com.jie.service.OrderService;
import com.jie.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Resource
    private UserService userService;
    @Resource
    private OrderService orderService;

    @RequestMapping("/getOrderAll.do")
    @ResponseBody
    public String getOrderAll(int type, String keyword, int status, int num, int page){
        List<Orderdetails> list=orderService.getOrderAll(type,keyword,status,num,page);
        return JSON.toJSONString(list);
    }

    @RequestMapping("/getOrder.do")
    @ResponseBody
    public String getOrder(int id){
        Orderdetails orderdetails=orderService.getOrder(id);
        if(orderdetails!=null){
            return JSON.toJSONString(orderdetails, SerializerFeature.DisableCircularReferenceDetect);
        }
        return JSON.toJSONString("false");
    }

    @RequestMapping("/updateStatus.do")
    @ResponseBody
    public String updateStatus(String code){
        String arr[]=code.split("：");
        orderService.updateStatus(arr[1]);
        return JSON.toJSONString("true");
    }

    @RequestMapping("/updateStatusByCode.do")
    @ResponseBody
    public String updateStatusByCode(String code,int status){
        String arr[]=code.split("：");
        orderService.updateStatusByCode(arr[1],status);
        return JSON.toJSONString("true");
    }

    @RequestMapping("/updateCode.do")
    @ResponseBody
    public String updateCode(int id,String code,String name){
        orderService.updateCode(id,code,name);
        return JSON.toJSONString("true");
    }

    @RequestMapping("/getOrderByUId.do")
    @ResponseBody
    public String getOrderByUId(HttpServletRequest request){
        String username=(String)request.getSession().getAttribute("USER_SESSION_KEY");
        User user=userService.findUser(username);
        List<Orderdetails> list=orderService.getOrderByUId(user.getId());
        if(list!=null){
            return JSON.toJSONString(list);
        }
        return JSON.toJSONString("false");
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
