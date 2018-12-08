package com.jie.controller;


import com.alibaba.fastjson.JSON;
import com.jie.domain.Receiveaddress;
import com.jie.domain.User;
import com.jie.service.ReceiveaddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/read")
@Controller
public class ReceiveaddressController {
    @Autowired
    private ReceiveaddressService receiveaddressService;

    public void setReceiveaddressService(ReceiveaddressService receiveaddressService) {
        this.receiveaddressService = receiveaddressService;
    }
    @RequestMapping("/toAdd")
    @ResponseBody
    public void toAdd(Receiveaddress receiveaddress,HttpServletRequest request){
        User user=receiveaddressService.toGetUser(request);
        receiveaddress.setuId(user.getId());
        receiveaddressService.toAdd(receiveaddress);
    }
    @RequestMapping("toShow")
    @ResponseBody
    public String toShow(HttpServletRequest request){
        User user=receiveaddressService.toGetUser(request);
        int uId=user.getId();
        List<Receiveaddress>list=receiveaddressService.toShow(uId);
        return JSON.toJSONString(list);
    }
    @RequestMapping("/toUpdateStatus")
    @ResponseBody
    public String toUpdateStatus(int id,int status){
        receiveaddressService.toUpdateStatus(id,status);
        return JSON.toJSONString("true");
    }
    @RequestMapping("/toFind")
    @ResponseBody
    public String toFind(int id){
        Receiveaddress receiveaddress=receiveaddressService.toFind(id);
        return JSON.toJSONString(receiveaddress);
    }
    @RequestMapping("/toUpdate")
    @ResponseBody
    public void toUpdate(Receiveaddress receiveaddress,HttpServletRequest request){
        User user=receiveaddressService.toGetUser(request);
        receiveaddress.setuId(user.getId());
        receiveaddressService.toUpdate(receiveaddress);
    }
    @RequestMapping("/toDelete")
    @ResponseBody
    public void toDelete(int id){
        receiveaddressService.toDelete(id);
    }

    @RequestMapping("/toGetUser")
    @ResponseBody
    public String toGetUser(HttpServletRequest request){
        User user=receiveaddressService.toGetUser(request);
        return JSON.toJSONString(user);
    }

}
