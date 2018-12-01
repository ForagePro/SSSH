package com.jie.controller;

import com.alibaba.fastjson.JSON;
import com.jie.domain.Breed;
import com.jie.service.BreedService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/breed")
public class BreedController {
    @Resource
    private BreedService breedService;

    //验证品类名
    @RequestMapping("/testBreedName.do")
    @ResponseBody
    public String testBreedName(String name){
        boolean flag=breedService.testBreedName(name);
        return JSON.toJSONString(flag);
    }

    //添加品类
    @RequestMapping("/addBreed.do")
    @ResponseBody
    public String addBreed(Breed breed){
        breedService.addBreed(breed);
        return JSON.toJSONString("true");
    }

    //获取品类名称
    @RequestMapping("/getBreedName.do")
    @ResponseBody
    public String getBreedName(){
        List<Breed> list=breedService.getBreedName();
        return JSON.toJSONString(list);
    }

    @RequestMapping("/select.do")
    @ResponseBody
    public String select(Breed breed, HttpServletRequest request){
        int num=Integer.parseInt(request.getParameter("num"));
        int page=Integer.parseInt(request.getParameter("page"));
        String keyword=request.getParameter("keyword");
        List<Breed> list=breedService.select(breed,num,page,keyword);
        return JSON.toJSONString(list);
    }

    //修改状态
    @RequestMapping("/updateStatus.do")
    @ResponseBody
    public String updateStatus(int id,int status){
        breedService.updateStatus(id,status);
        return JSON.toJSONString("true");
    }

    //根据id获取breed
    @RequestMapping("/getBreedById.do")
    @ResponseBody
    public String getBreedById(int id){
        Breed breed=breedService.getBreedById(id);
        return JSON.toJSONString(breed);
    }

    //修改breed
    @RequestMapping("/editBreed.do")
    @ResponseBody
    public String editBreed(Breed breed){
        breedService.editBreed(breed);
        return JSON.toJSONString("true");
    }

    //删除
    @RequestMapping("/delete.do")
    @ResponseBody
    public String delete(int id){
        breedService.delete(id);
        return JSON.toJSONString("true");
    }
    public void setBreedService(BreedService breedService) {
        this.breedService = breedService;
    }
}
