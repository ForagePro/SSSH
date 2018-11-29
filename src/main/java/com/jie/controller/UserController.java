package com.jie.controller;

import com.alibaba.fastjson.JSON;
import com.jie.domain.User;
import com.jie.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping("/toLogin")
    @ResponseBody
    public String toLogin(String username, String password, HttpServletRequest request) {
        List list = userService.toLogin(username, password,request);
        System.out.println("");
        return JSON.toJSONString(list);

    }

    @RequestMapping("/toRegister")
    @ResponseBody
    public String toRegister(User user) {

        String msg=userService.toRegister(user);
        return JSON.toJSONString(msg);
    }

    //查询用户信息
    @RequestMapping("/findUser.do")
    @ResponseBody
    public String findUser(HttpServletRequest request){
        String username=(String)request.getSession().getAttribute("USER_SESSION_KEY");
        User user=userService.findUser(username);
        if(user!=null){
            List<User> list=new ArrayList<>();
            list.add(user);
            return JSON.toJSONString(list);
        }
        return JSON.toJSONString("false");
    }

    //更新用户图片信息
    @RequestMapping("/updateUserImg.do")
    @ResponseBody
    public String updateUserImg(MultipartFile myFile,HttpServletRequest request){
        String path=request.getServletContext().getRealPath("forward/images/");
        //创建file对象，指定存储路径
        File descFile=new File(path+myFile.getOriginalFilename());
        try{
            FileUtils.copyInputStreamToFile(myFile.getInputStream(),descFile);
        }catch (IOException e){
            e.printStackTrace();
        }
        String username=(String)request.getSession().getAttribute("USER_SESSION_KEY");
        User user=new User();
        user.setUsername(username);
        user.setImgPath(myFile.getOriginalFilename());
        userService.updateUserImg(user);
        return JSON.toJSONString("OK");
    }

    //更新用户信息
    @RequestMapping("/updateUser.do")
    @ResponseBody
    public String updateUser(User user){
        userService.updateUser(user);
        return JSON.toJSONString("OK");
    }

    //验证原始密码
    @RequestMapping("/originCode.do")
    @ResponseBody
    public String originCode(String password,HttpServletRequest request){
        String username=(String)request.getSession().getAttribute("USER_SESSION_KEY");
        String pwd=userService.originCode(username);
        if(!pwd.equals("")){
            if(pwd.equals(password)){
                return JSON.toJSONString("true");
            }
        }
        return JSON.toJSONString("false");
    }

    //更新密码
    @RequestMapping("/updatePwd.do")
    @ResponseBody
    public String updatePwd(User user,HttpServletRequest request){
        String username=(String)request.getSession().getAttribute("USER_SESSION_KEY");
        user.setUsername(username);
        userService.updatePwd(user);
        return JSON.toJSONString("true");
    }
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
