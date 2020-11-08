package com.example.springweb.controller;

import com.example.springweb.dao.HelloUser;
import com.example.springweb.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
    private static List<HelloUser> userList;
    public HelloController(){
        super();
        userList=new ArrayList<HelloUser>();
    }
    @Autowired
    HelloService helloService;
    public final static Logger logger = LoggerFactory.getLogger(HelloController.class);


    @RequestMapping(value = {"/register"})
    public String registerpage() {
        return "register";
    }

 //  @ResponseBody
 /*  @PostMapping(value="/login")
   public String login(@RequestParam(value = "loginname",required = false)String loginname, @RequestParam(value = "password",required = false)String password, Model model){
      logger.info("登录名"+loginname+"密码"+password);
     userList=helloService.getUserList();
     for(HelloUser user: userList){
            if(user.getName().equals(loginname)&&user.getPassword().equals(password)){
                model.addAttribute("user",user);
                return "redirect:hello";
            }
     }
        return "redirect:login";
    }
*/
  /*  @RequestMapping("/login")
    @ResponseBody
    public String home(HttpServletRequest request,Model model){
        String name=request.getParameter("loginname");
        String password=request.getParameter("passwd");
        logger.info("登录名"+name+"密码"+password);
        userList=helloService.getUserList();
        for(HelloUser user: userList){
              if(user.getName().equals(name)&&user.getPassword().equals(password)){
                  model.addAttribute("user",user);
                   return "hello";
          }
        }
        return "登录错误";
    }*/
 /* @RequestMapping(value = "dologin",method = RequestMethod.POST)
  public String login(HttpServletRequest request,
                      @RequestParam("loginname")String loginname,
                      @RequestParam("password")String password,Model model){
      userList=helloService.getUserList();
      logger.info("登录名"+loginname+"密码"+password);
      for(HelloUser user: userList){
                if(user.getName().equals(loginname)&&user.getPassword().equals(password)){
                   model.addAttribute("user",user);
                 return "redirect:/success";
                }
      }
          request.setAttribute("wrongMsg","用户名密码错误");
          request.getSession().setAttribute("wrongMsg","用户名密码错误");
          return "redirect:/login";
  }*/


}
