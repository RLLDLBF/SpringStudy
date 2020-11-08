package com.example.springweb.controller;

import com.example.springweb.dao.ASetOfMap;
import com.example.springweb.dao.JavaSwiftAPI;
import com.example.springweb.service.AddApiMapService;
import com.example.springweb.service.ManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class AddApiMapController {
    JavaSwiftAPI theAddJavaApi,theAddSwiftApi;
    String theJavaFullName,theSwiftFullName;
    ASetOfMap aSetOfMap;
    public AddApiMapController() {
        super();
        theAddJavaApi=new JavaSwiftAPI();
        theAddSwiftApi=new JavaSwiftAPI();
    }

    @Autowired
    AddApiMapService addApiMapService;
    public final static Logger logger = LoggerFactory.getLogger(AddApiMapController.class);


    @GetMapping("addedmap")
    public ModelAndView addTheApi(ModelAndView modelAndView) {
        modelAndView.setViewName("addedmap");
        return modelAndView;
    }

    @GetMapping("/addedmapresult")/*查找映射的具体方法*/
    public String getTheAddedMapResult(String type,Model model){
        if(type.equals("java")){
            model.addAttribute("fullName",theAddJavaApi.getFullName());
            model.addAttribute("packageName",theAddJavaApi.getPackageName());
            model.addAttribute("apiType","Java");
            model.addAttribute("Signature",theAddJavaApi.getApiSignature());
            model.addAttribute("description",theAddJavaApi.getDescription());
            model.addAttribute("codeSample",theAddJavaApi.getCodeSample());
            model.addAttribute("returnParam",theAddJavaApi.getReturnParam());
            model.addAttribute("formParameter",theAddJavaApi.getFormParameter());
            return "methodresult";
        }
        else if(type.equals("swift")){
            model.addAttribute("fullName",theAddSwiftApi.getFullName());
            model.addAttribute("packageName",theAddSwiftApi.getPackageName());
            model.addAttribute("apiType","Swift");
            model.addAttribute("Signature",theAddSwiftApi.getApiSignature());
            model.addAttribute("description",theAddSwiftApi.getDescription());
            model.addAttribute("codeSample",theAddSwiftApi.getCodeSample());
            model.addAttribute("returnParam",theAddSwiftApi.getReturnParam());
            model.addAttribute("formParameter",theAddSwiftApi.getFormParameter());
            return "methodresult";

        }
        return "methodresult";
    }

    @RequestMapping(value = "/addedmap", method = RequestMethod.POST)
    public String addTheAPI(HttpServletRequest request, Model model, HttpServletResponse response) throws IOException {
        long startTime=System.currentTimeMillis();
        long endTime;
        theJavaFullName=request.getParameter("javaFullName");
        theSwiftFullName=request.getParameter("swiftFullName");
        theAddJavaApi=addApiMapService.getTheJavaAPI(theJavaFullName);
        theAddSwiftApi=addApiMapService.getTheSwiftAPI(theSwiftFullName);
        if(theAddJavaApi==null){
            response.setContentType("text/html;charset=gbk");
            PrintWriter out = response.getWriter();
            out.print("<script language=\"javascript\">alert('数据库中没有该Java API！请先添加API，再添加映射！');</script>");
            endTime=System.currentTimeMillis();
            System.out.println("【添加API映射时，未找到JavaAPI】运行时间： "+(endTime-startTime)+"ms");
            return "management";
        }
        if(theAddSwiftApi==null){
            response.setContentType("text/html;charset=gbk");
            PrintWriter out = response.getWriter();
            out.print("<script language=\"javascript\">alert('数据库中没有该Swift API！请先添加API，再添加映射！');</script>");
            endTime=System.currentTimeMillis();
            System.out.println("【添加API映射时，未找到SwiftAPI】运行时间： "+(endTime-startTime)+"ms");
            return "management";
        }
        /*api都存在可以添加映射了*/
        aSetOfMap=addApiMapService.getTheMap(theAddJavaApi.getApiId(),theAddSwiftApi.getApiId());
        if(aSetOfMap!=null){
          //  endTime=System.currentTimeMillis();
        //    System.out.println("【API映射添加成功：未弹出对话框之前】运行时间： "+(endTime-startTime)+"ms");
            response.setContentType("text/html;charset=gbk");
            PrintWriter out = response.getWriter();
            out.print("<script language=\"javascript\">alert('该API映射已存在!');</script>");
        }
        else{
            addApiMapService.insertMap(theAddJavaApi.getApiId(),theAddSwiftApi.getApiId());
      //      endTime=System.currentTimeMillis();
     //       System.out.println("【API映射添加成功：未弹出对话框之前】运行时间： "+(endTime-startTime)+"ms");
            response.setContentType("text/html;charset=gbk");
            PrintWriter out = response.getWriter();
            out.print("<script language=\"javascript\">alert('API映射添加成功！');</script>");
        }
        model.addAttribute("JavaAPI",theAddJavaApi.getFullName());
        model.addAttribute("SwiftAPI",theAddSwiftApi.getFullName());
        endTime=System.currentTimeMillis();
        System.out.println("【API映射添加成功：跳转API映射页面】运行时间： "+(endTime-startTime)+"ms");
        return "addedmap";
    }

}
