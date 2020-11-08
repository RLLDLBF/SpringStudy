package com.example.springweb.controller;

import com.example.springweb.dao.ASetOfMap;
import com.example.springweb.dao.ClassName;
import com.example.springweb.dao.HelloUser;
import com.example.springweb.dao.JavaSwiftAPI;
import com.example.springweb.service.ManageService;
import com.example.springweb.service.MapService;
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
public class ManageAPIController {
    JavaSwiftAPI theAddApi;
    String theApiType;
    public ManageAPIController() {
        super();
        theAddApi = new JavaSwiftAPI();
    }

    @Autowired
    ManageService manageService;
    public final static Logger logger = LoggerFactory.getLogger(ManageAPIController.class);

    @GetMapping("addedapi")
    public ModelAndView addedTheApi(ModelAndView modelAndView) {
        modelAndView.setViewName("addedapi");
        return modelAndView;
    }

    @GetMapping("apimapadd")
    public ModelAndView apiMapAdd(ModelAndView modelAndView) {
        modelAndView.setViewName("apimapadd");
        return modelAndView;
    }

    @RequestMapping(value = "/addedapi", method = RequestMethod.POST)
    public String addTheAPI(HttpServletRequest request, Model model, HttpServletResponse response) throws IOException {
        long startTime=System.currentTimeMillis();
        long endTime;
        theAddApi.setFullName(request.getParameter("fullName"));
        theAddApi.setApiNameForSearch(request.getParameter("apiNameForSearch"));
        theAddApi.setApiSignature(request.getParameter("apiSignature"));
        theAddApi.setClassName(request.getParameter("className"));
        theAddApi.setPackageName(request.getParameter("packageName"));
        theAddApi.setDescription(request.getParameter("description"));
        theAddApi.setCodeSample(request.getParameter("codeSample"));
        theAddApi.setMethodName(request.getParameter("methodName"));
        theAddApi.setFormParameter(request.getParameter("formParameter"));
        theAddApi.setReturnParam(request.getParameter("returnParam"));
        theApiType=request.getParameter("apiType");
        if(theApiType.equals("Java")){
            manageService.insertJavaAPI(theAddApi);

        }//添加至Java表单
        else if(theApiType.equals("Swift")){
            manageService.insertSwiftAPI(theAddApi);
        }//添加至Swift表单
     //   endTime=System.currentTimeMillis();
     //   System.out.println("【一条API添加成功！添加成功弹窗跳出前】运行时间： "+(endTime-startTime)+"ms");
        response.setContentType("text/html;charset=gbk");
        PrintWriter out = response.getWriter();
        out.print("<script language=\"javascript\">alert('API信息添加成功！');</script>");
        model.addAttribute("fullName",theAddApi.getFullName());
        model.addAttribute("packageName",theAddApi.getPackageName());
        model.addAttribute("apiType",theApiType);
        model.addAttribute("Signature",theAddApi.getApiSignature());
        model.addAttribute("description",theAddApi.getDescription());
        model.addAttribute("codeSample",theAddApi.getCodeSample());
        model.addAttribute("returnParam",theAddApi.getReturnParam());
        model.addAttribute("formParameter",theAddApi.getFormParameter());
        endTime=System.currentTimeMillis();
        System.out.println("【一条API添加成功！页面跳转至API信息】运行时间： "+(endTime-startTime)+"ms");
        return "addedapi";
    }
}
