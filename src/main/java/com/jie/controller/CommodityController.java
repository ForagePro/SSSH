package com.jie.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jie.domain.Breed;
import com.jie.domain.Commodity;
import com.jie.service.BreedService;
import com.jie.service.CommodityService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/commodity")
public class CommodityController {
    @Resource
    private BreedService breedService;

    public void setBreedService(BreedService breedService) {
        this.breedService = breedService;
    }

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

    @RequestMapping("/addCommodity.do")
    @ResponseBody
    public String addCommodity(HttpServletRequest request,Commodity commodity, MultipartFile file){
        String path=request.getServletContext().getRealPath("forward/images/");
//        System.out.println(path+"==============>"+myFile.getOriginalFilename());
        //创建file对象，指定存储路径
        File descFile=new File(path+file.getOriginalFilename());
        try{
            FileUtils.copyInputStreamToFile(file.getInputStream(),descFile);
        }catch (IOException e){
            e.printStackTrace();
        }
        commodity.setImgPath("/forward/images/"+file.getOriginalFilename());
        System.out.println(file.getOriginalFilename());
        int b_id=Integer.parseInt(request.getParameter("bId"));
        Breed breed=breedService.getBreedById(b_id);
        commodity.setBreed(breed);
        commodityService.addCommodity(commodity);
        return JSON.toJSONString("true");
    }

    @RequestMapping("/select.do")
    @ResponseBody
    public String select(int bId,int status,String keyword,int num,int page){
        List<Commodity> list=commodityService.select(bId,status,keyword,num,page);
        return JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
    }

    @RequestMapping("/updateStatus.do")
    @ResponseBody
    public String updateStatus(int id,int status){
        commodityService.updateStatus(id,status);
        return JSON.toJSONString("true");
    }

    @RequestMapping("/getCommodity.do")
    @ResponseBody
    public String getCommodity(int id){
        Commodity commodity=commodityService.getCommodity(id);
        if(commodity!=null){
            return JSON.toJSONString(commodity);
        }
        return JSON.toJSONString("false");
    }

    @RequestMapping("/editCommodity.do")
    @ResponseBody
    public String editCommodity(HttpServletRequest request,Commodity commodity, MultipartFile file){
        if(file!=null) {
            String path = request.getServletContext().getRealPath("forward/images/");
//        System.out.println(path+"==============>"+myFile.getOriginalFilename());
            //创建file对象，指定存储路径
            File descFile = new File(path + file.getOriginalFilename());
            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(), descFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            commodity.setImgPath("/forward/images/" + file.getOriginalFilename());
        }
        int b_id=Integer.parseInt(request.getParameter("bId"));
        Breed breed=new Breed();
        breed.setId(b_id);
        commodity.setBreed(breed);
        commodityService.editCommodity(commodity);
        return JSON.toJSONString("true");
    }

    @RequestMapping("/delete.do")
    @ResponseBody
    public String delete(int id){
        commodityService.delete(id);
        return JSON.toJSONString("true");
    }
}
