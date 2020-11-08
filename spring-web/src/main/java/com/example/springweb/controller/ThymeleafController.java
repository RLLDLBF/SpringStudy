package com.example.springweb.controller;

import com.example.springweb.dao.HelloUser;
import com.example.springweb.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ThymeleafController {
    private static List<HelloUser> userList;
    public ThymeleafController(){
        super();
        userList=new ArrayList<HelloUser>();
    }

    @Autowired
    HelloService helloService;
    public final static Logger logger = LoggerFactory.getLogger(ThymeleafController.class);

    @RequestMapping("/thymeleaf")
    public String hello(Model model) {
        model.addAttribute("greeting", "lichenwei,12345");
        return "wrong";
    }
    @GetMapping("login")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @GetMapping("maphomepage")
    public ModelAndView homepage2search(ModelAndView modelAndView){
        modelAndView.setViewName("maphomepage");
        return modelAndView;
    }
   @GetMapping("management")
   public ModelAndView management(ModelAndView modelAndView){
       modelAndView.setViewName("management");
       return modelAndView;
   }
    @GetMapping("wrong")
    public ModelAndView wrong(ModelAndView modelAndView){
        modelAndView.setViewName("wrong");
        return modelAndView;
    }
  // @ResponseBody
   @RequestMapping(value = "/management",method = RequestMethod.POST)
    public String management(HttpServletRequest request,Model model, HttpServletResponse response) throws IOException {
        String name=request.getParameter("userName");
        String password=request.getParameter("password");
        logger.info("登录名"+name+"密码"+password);
        userList=helloService.getUserList();
        for(HelloUser user: userList){
            if(user.getName().equals(name)&&user.getPassword().equals(password)){
                model.addAttribute("user",user);
                return "redirect:/management";
            }
        }
       response.setContentType("text/html;charset=gbk");
       PrintWriter out = response.getWriter();
       out.print("<script language=\"javascript\">alert('登录错误!您没有管理权限！');window.location.href='/login'</script>");
       return "login";/*先弹出对话框提示，再回到查询页面*/
    }

    @RequestMapping(value={"","homepage"},method=RequestMethod.GET)
    public String homepage(){
    //    logger.info("hello logging" + helloService.getUserList());
        return "homepage";
    }
  /*  @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(HttpServletRequest request,Model model){
        String name=request.getParameter("userName");
        String password=request.getParameter("password");
        userList=helloService.getUserList();
        for(HelloUser user: userList){
            if(user.getName().equals(name)&&user.getPassword().equals(password)){
                model.addAttribute("user",user);
                return "homepage";
            }
        }
        return "wrong";
    }*/

}
