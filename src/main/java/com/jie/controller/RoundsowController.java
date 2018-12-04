package com.jie.controller;

import com.alibaba.fastjson.JSON;
import com.jie.domain.Roundsow;
import com.jie.service.RoundsowService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/round")
public class RoundsowController {
    @Autowired
    private RoundsowService roundsowService;


    //上传图片
    @RequestMapping("/toAdd")
    @ResponseBody
    public String toAdd(  HttpServletRequest request,Roundsow roundsow,MultipartFile myFile){
        String path=request.getServletContext().getRealPath("forward/images/");
//        System.out.println(path+"==============>"+myFile.getOriginalFilename());
        //创建file对象，指定存储路径
        File descFile=new File(path+myFile.getOriginalFilename());
        try{
            FileUtils.copyInputStreamToFile(myFile.getInputStream(),descFile);
        }catch (IOException e){
            e.printStackTrace();
        }
        roundsow.setImgPath("/forward/images/"+myFile.getOriginalFilename());
        roundsowService.toAdd(roundsow);
        return JSON.toJSONString("添加成功");
    }

    @RequestMapping("/toShow")
    @ResponseBody
    public String toShow(int pageNo,int pageSize){
        List<Roundsow>list= roundsowService.toShow(pageNo,pageSize);
        return JSON.toJSONString(list);
    }

    @RequestMapping("/toUpdateStatus")
    @ResponseBody
    public String toUpdateStatus(int status,int id){
        String msg=roundsowService.toUpdataStatus(status, id);
        return JSON.toJSONString(msg);
    }

    @RequestMapping("/toDelete")
    @ResponseBody
    public void toDelete(int id){
        roundsowService.toDelete(id);
    }

    @RequestMapping("/toFind")
    @ResponseBody
    public String toFind(){
        List<Roundsow>list=roundsowService.toFind();
        return JSON.toJSONString(list);
    }

    @RequestMapping("/toQuery")
    @ResponseBody
    public String toQuery(int status){
        List<Roundsow>list=roundsowService.toQuery(status);
        return JSON.toJSONString(list);
    }
    public void setRoundsowService(RoundsowService roundsowService) {
        this.roundsowService = roundsowService;
    }
}
