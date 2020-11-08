package com.example.springweb.controller;

import com.example.springweb.dao.ClassName;
import com.example.springweb.dao.JavaSwiftAPI;
import com.example.springweb.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Controller
public class SearchAPIController {
    private static ClassName className;
    private static ArrayList<JavaSwiftAPI> javaListAPI,swiftListAPI;
    private static JavaSwiftAPI theAPI;
    public SearchAPIController(){
        super();
        className=new ClassName();
        theAPI =new JavaSwiftAPI();
    }
    @Autowired
    SearchService searchService;
    public final static Logger logger = LoggerFactory.getLogger(SearchAPIController.class);

    @GetMapping("/methodresult")
    public String getTheMethod(int id,Model model){
        long startTime=System.currentTimeMillis();
    //    System.out.println(javaListAPI.size()+"!!!!!");
   //     System.out.println("要查找的是第"+id+"个API！！！！");
        model.addAttribute("fullName",javaListAPI.get(id).getFullName());
        model.addAttribute("packageName",javaListAPI.get(id).getPackageName());
        model.addAttribute("apiType","Java");
        model.addAttribute("Signature",javaListAPI.get(id).getApiSignature());
        model.addAttribute("description",javaListAPI.get(id).getDescription());
        model.addAttribute("codeSample",javaListAPI.get(id).getCodeSample());
        model.addAttribute("returnParam",javaListAPI.get(id).getReturnParam());
        model.addAttribute("formParameter",javaListAPI.get(id).getFormParameter());
        long endTime=System.currentTimeMillis();
        System.out.println("【点击API名称查询具体API】运行时间： "+(endTime-startTime)+"ms");
        return "methodresult";
    }
  /*  @RequestMapping(value = "/methodresult",method = RequestMethod.POST)
    public String getTheMethod(HttpServletRequest request, Model model, HttpServletResponse response){
        System.out.println("??????????开始查找具体方法??????????");
        System.out.println(javaListAPI.size()+"!!!!!");
        String theApiName=request.getParameter("");
        model.addAttribute("fullName",javaListAPI.get(0).getFullName());
        model.addAttribute("packageName",javaListAPI.get(0).getPackageName());
        model.addAttribute("apiType","Java");
        model.addAttribute("Signature",javaListAPI.get(0).getApiSignature());
        model.addAttribute("description",javaListAPI.get(0).getDescription());
        model.addAttribute("codeSample",javaListAPI.get(0).getCodeSample());
        model.addAttribute("returnParam",javaListAPI.get(0).getReturnParam());
        model.addAttribute("formParameter",javaListAPI.get(0).getFormParameter());
        return "methodresult";
    }*/

    @GetMapping("/result")
    public ModelAndView searchResult(ModelAndView modelAndView){
        modelAndView.setViewName("classresult");
        return modelAndView;
    }
    @RequestMapping(value = "/result",method = RequestMethod.POST)
    public String searchResult(HttpServletRequest request, Model model, HttpServletResponse response) throws IOException {
        String searchText=request.getParameter("searchText");
        long startTime=System.currentTimeMillis();
        String inputClassName="NULL";
        String inputMethodName="NULL";
        if(searchText.contains("_")){
            String splitText[]=searchText.split("_");
            inputClassName=splitText[0];
            inputMethodName=splitText[1];
        }
        else{
            inputClassName=searchText;
        }
   //     logger.info("输入内容："+searchText+"类名："+inputClassName+"方法名"+inputMethodName);
        className=searchService.getClassName(inputClassName);/*!!!查找类！！！*/
        /*完全没有这个类*/
        if(className==null){
        //    System.out.println("未查找到这个类！数据库暂未收录，或请检查输入格式！");
            response.setContentType("text/html;charset=gbk");
            PrintWriter out= response.getWriter();
            out.print("<script language=\"javascript\">alert('未找到这个类！*数据库暂未收录，或请检查输入格式(注意大小写)*');window.location.href='/homepage'</script>");
            long endTime=System.currentTimeMillis();
            System.out.println("【未查找到类】运行时间： "+(endTime-startTime)+"ms");
            return "homepage";/*先弹出对话框提示，再回到查询页面*/
        }
        /*有这个类*/
        else{
            /*1.只查找类*/
            if(inputMethodName.equals("NULL")){
                className.methodsTeach();
               // System.out.println("输入的className是："+className.getName());
                String[] temp=className.getEachMethod();
                int tempCount=className.getMetNum();
                model.addAttribute("name",className.getName());
                model.addAttribute("type",className.getType());
                model.addAttribute("packageName",className.getPackageName());
                model.addAttribute("metNum",className.getMetNum());
                model.addAttribute("methods",className.getMethods());
                long endTime=System.currentTimeMillis();
                System.out.println("【查询的是类】运行时间： "+(endTime-startTime)+"ms");
                return "classresult";/*查找类*/
            }
            /*也查找方法*/
            else{
             //   System.out.println("也查找方法！！！"+className.getType());
                if(className.getType().equals("Java")){
                //    System.out.println("是Java类!!");
                    javaListAPI=searchService.getJavaAPI(searchText);/*重新赋值*/
                    //System.out.println(searchText);
                    if(javaListAPI.isEmpty()){
                    //    System.out.println("有类没方法！！！！");
                        response.setContentType("text/html;charset=gbk");
                        PrintWriter out= response.getWriter();
                        out.print("<script language=\"javascript\">alert('该类中没有此方法！*数据库暂未收录，或请检查输入格式(注意大小写)&请直接输入类名查看其方法');window.location.href='/homepage'</script>");
                        long endTime=System.currentTimeMillis();
                        System.out.println("【有这个类但没有所查找的方法】运行时间： "+(endTime-startTime)+"ms");
                        return "homepage";/*先弹出对话框提示，再回到查询页面*/
                    }
                    else {
                      //  System.out.println("返回JavaAPI！！！");
                        if(javaListAPI.size()==1){/*只有一个同名方法*/
                            model.addAttribute("fullName",javaListAPI.get(0).getFullName());
                            model.addAttribute("packageName",javaListAPI.get(0).getPackageName());
                            model.addAttribute("apiType","Java");
                       //     System.out.println("找到了Java类_方法");
                            model.addAttribute("Signature",javaListAPI.get(0).getApiSignature());
                            model.addAttribute("description",javaListAPI.get(0).getDescription());
                            model.addAttribute("codeSample",javaListAPI.get(0).getCodeSample());
                            model.addAttribute("returnParam",javaListAPI.get(0).getReturnParam());
                            model.addAttribute("formParameter",javaListAPI.get(0).getFormParameter());
                            long endTime=System.currentTimeMillis();
                            System.out.println("【显示具体的API方法】运行时间： "+(endTime-startTime)+"ms");
                            return "methodresult";/*查找方法*/
                        }
                        else{
                            model.addAttribute("methodsList",javaListAPI);/*给出同名方法列表*/
                            long endTime=System.currentTimeMillis();
                            System.out.println("【查找到多个同名方法】运行时间： "+(endTime-startTime)+"ms");
                            return"methods";
                        }
                    }
                }
                /*是swift类去swift表里查*/
                else if(className.getType().equals("Swift")){
             //       System.out.println("是Swift类!!");
                    swiftListAPI=searchService.getSwiftAPI(searchText);/*重新赋值*/
                 //   System.out.println(searchText);
                    if(swiftListAPI.isEmpty()){
                        response.setContentType("text/html;charset=gbk");
                        PrintWriter out= response.getWriter();
                        out.print("<script language=\"javascript\">alert('查找到此类，但该类中没有此方法！*请直接输入类名查看其方法*');window.location.href='/homepage'</script>");
                        long endTime=System.currentTimeMillis();
                        System.out.println("【有这个类但没有所查找的方法】运行时间： "+(endTime-startTime)+"ms");
                        return "homepage";/*先弹出对话框提示，再回到查询页面*/
                    }
                    else {
                        if(swiftListAPI.size()==1){
                            /*只有一个同名方法*/
                            model.addAttribute("fullName",swiftListAPI.get(0).getFullName());
                            model.addAttribute("packageName",swiftListAPI.get(0).getPackageName());
                            model.addAttribute("apiType","Java");
                            model.addAttribute("Signature",swiftListAPI.get(0).getApiSignature());
                            model.addAttribute("description",swiftListAPI.get(0).getDescription());
                            model.addAttribute("codeSample",swiftListAPI.get(0).getCodeSample());
                            model.addAttribute("returnParam",swiftListAPI.get(0).getReturnParam());
                            model.addAttribute("formParameter",swiftListAPI.get(0).getFormParameter());
                            long endTime=System.currentTimeMillis();
                            System.out.println("【查询并显示具体的方法】运行时间： "+(endTime-startTime)+"ms");
                            return "methodresult";/*查找方法*/
                        }
                        else{
                            model.addAttribute("methodsList",swiftListAPI);/*给出同名方法列表*/
                            long endTime=System.currentTimeMillis();
                            System.out.println("【查找到多个同名方法】运行时间： "+(endTime-startTime)+"ms");
                            return"methods";
                        }
                    }
                }
            }
        }
        return "classresult";
    }
}
