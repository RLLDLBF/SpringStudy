package com.example.springweb.controller;

import com.example.springweb.dao.ASetOfMap;
import com.example.springweb.dao.ClassName;
import com.example.springweb.dao.JavaSwiftAPI;
import com.example.springweb.service.MapService;
import com.example.springweb.service.SearchService;
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
import java.util.ArrayList;

@Controller
public class MapAPIController {
    private static ClassName className;
    private static ArrayList<JavaSwiftAPI> javaListAPI, swiftListAPI;
    private static ASetOfMap swiftMapJava;
    private static ArrayList<ASetOfMap> javaMapSwift;
    private static JavaSwiftAPI theAPI;

    public MapAPIController() {
        super();
        className = new ClassName();
        theAPI = new JavaSwiftAPI();
        javaListAPI = new ArrayList<JavaSwiftAPI>();
        swiftListAPI = new ArrayList<JavaSwiftAPI>();
        swiftMapJava = new ASetOfMap();
        javaMapSwift = new ArrayList<ASetOfMap>();
    }

    @Autowired
    SearchService searchService;

    @Autowired
    MapService mapService;
    public final static Logger logger = LoggerFactory.getLogger(MapAPIController.class);

    @GetMapping("mapresult")
    public ModelAndView mapResult(ModelAndView modelAndView) {
        modelAndView.setViewName("mapresult");
        return modelAndView;
    }

    @GetMapping("/javamapswiftresult")/*点击查看具体的api的映射*/
    public String getTheJavaMapSwift(int id, Model model) {
     //   System.out.println("***直接跳转了~！！！！！去查找对应映射");
        long startTime=System.currentTimeMillis();   //获取开始时间
        javaMapSwift = mapService.getMapSwiftId(javaListAPI.get(id).getApiId());
        swiftListAPI.clear();
        for (int i = 0; i < javaMapSwift.size(); i++) {
            int mapId = javaMapSwift.get(i).getSwiftId();
            JavaSwiftAPI theAPI = new JavaSwiftAPI();
            theAPI = mapService.getMapSwiftAPI(mapId);
            swiftListAPI.add(theAPI);
        }
        model.addAttribute("JavaAPI", javaListAPI.get(id).getFullName());
        if (swiftListAPI.isEmpty()) {
            JavaSwiftAPI theAPI = new JavaSwiftAPI();
            theAPI.setFullName("该方法没有Swift映射！");
            swiftListAPI.add(theAPI);
        }
        model.addAttribute("swiftList", swiftListAPI);
        long endTime=System.currentTimeMillis();
        System.out.println("【多个同名API：查找映射】运行时间： "+(endTime-startTime)+"ms");
        return "javaMapSwift";/*查找方法*/
    }

    @GetMapping("/mapmethodresult")/*查找映射的具体方法*/
    public String getTheMapMethod(int id,Model model,String name){
    //    System.out.println("***直接跳转了~！！！！判断List的值还在不在！！！");
     //   System.out.println("要查找的是第"+id+"个API！！！！");
        long startTime=System.currentTimeMillis();   //获取开始时间
        if(id==-1){
            for(int theId=0;theId<javaListAPI.size();theId++){//找准Java是哪一个API//有可能找到多个Java API
                if(javaListAPI.get(theId).getFullName().equals(name)){
                    model.addAttribute("fullName",javaListAPI.get(theId).getFullName());
                    model.addAttribute("packageName",javaListAPI.get(theId).getPackageName());
                    model.addAttribute("apiType","Java");
                    model.addAttribute("Signature",javaListAPI.get(theId).getApiSignature());
                    model.addAttribute("description",javaListAPI.get(theId).getDescription());
                    model.addAttribute("codeSample",javaListAPI.get(theId).getCodeSample());
                    model.addAttribute("returnParam",javaListAPI.get(theId).getReturnParam());
                    model.addAttribute("formParameter",javaListAPI.get(theId).getFormParameter());
                }
            }
            long endTime=System.currentTimeMillis();
            System.out.println("【Java映射Swift：API映射的具体方法】运行时间： "+(endTime-startTime)+"ms");
            return "methodresult";
        }
        else{
            model.addAttribute("fullName",swiftListAPI.get(id).getFullName());
            model.addAttribute("packageName",swiftListAPI.get(id).getPackageName());
            model.addAttribute("apiType","Swift");
            model.addAttribute("Signature",swiftListAPI.get(id).getApiSignature());
            model.addAttribute("description",swiftListAPI.get(id).getDescription());
            model.addAttribute("codeSample",swiftListAPI.get(id).getCodeSample());
            model.addAttribute("returnParam",swiftListAPI.get(id).getReturnParam());
            model.addAttribute("formParameter",swiftListAPI.get(id).getFormParameter());
            long endTime=System.currentTimeMillis();
            System.out.println("【Java映射Swift：API映射的具体方法】运行时间： "+(endTime-startTime)+"ms");
            return "methodresult";
        }
    }

    @GetMapping("/mapjavamethodresult")/*查找映射的具体方法*/
    public String getMapJavaTheMethod(int id,Model model){
    //    System.out.println("要查找的是第"+id+"个API！！！！");
        long startTime=System.currentTimeMillis();   //获取开始时间
        if(id==1){
            model.addAttribute("fullName",javaListAPI.get(0).getFullName());
            model.addAttribute("packageName",javaListAPI.get(0).getPackageName());
            model.addAttribute("apiType","Java");
            model.addAttribute("Signature",javaListAPI.get(0).getApiSignature());
            model.addAttribute("description",javaListAPI.get(0).getDescription());
            model.addAttribute("codeSample",javaListAPI.get(0).getCodeSample());
            model.addAttribute("returnParam",javaListAPI.get(0).getReturnParam());
            model.addAttribute("formParameter",javaListAPI.get(0).getFormParameter());
            long endTime=System.currentTimeMillis();
            System.out.println("【Swift映射Java：API映射的具体方法】运行时间： "+(endTime-startTime)+"ms");
            return "methodresult";
        }
        else {
            model.addAttribute("fullName",swiftListAPI.get(0).getFullName());
            model.addAttribute("packageName",swiftListAPI.get(0).getPackageName());
            model.addAttribute("apiType","Swift");
            model.addAttribute("Signature",swiftListAPI.get(0).getApiSignature());
            model.addAttribute("description",swiftListAPI.get(0).getDescription());
            model.addAttribute("codeSample",swiftListAPI.get(0).getCodeSample());
            model.addAttribute("returnParam",swiftListAPI.get(0).getReturnParam());
            model.addAttribute("formParameter",swiftListAPI.get(0).getFormParameter());
            long endTime=System.currentTimeMillis();
            System.out.println("【Swift映射Java：API映射的具体方法】运行时间： "+(endTime-startTime)+"ms");
            return "methodresult";
        }
    }/*swift map java只有一个映射*/

    @RequestMapping(value = "/mapresult", method = RequestMethod.POST)
    public String mapResult(HttpServletRequest request, Model model, HttpServletResponse response) throws IOException {
        long startTime=System.currentTimeMillis();   //获取开始时间
        String searchText = request.getParameter("searchText");
        String inputClassName = "NULL";
        String inputMethodName = "NULL";
        if (searchText.contains("_")) {
            String splitText[] = searchText.split("_");
            inputClassName = splitText[0];
            inputMethodName = splitText[1];
        } else {
            response.setContentType("text/html;charset=gbk");
            PrintWriter out = response.getWriter();
            out.print("<script language=\"javascript\">alert('请按照要求格式输入！eg.类名_方法名');window.location.href='/maphomepage'</script>");
            long endTime=System.currentTimeMillis();
            System.out.println("【查找映射格式错误】运行时间： "+(endTime-startTime)+"ms");
            return "maphomepage";/*先弹出对话框提示，再回到查询页面*/
        }
       // logger.info("输入内容：" + searchText + "类名：" + inputClassName + "方法名" + inputMethodName);
        className = searchService.getClassName(inputClassName);/*!!!查找类！！！*/
        /*完全没有这个类*/
        if (className == null) {
            response.setContentType("text/html;charset=gbk");
            PrintWriter out = response.getWriter();
            out.print("<script language=\"javascript\">alert('未查找到这个类！*数据库暂未收录，或请检查输入格式(注意大小写)*');window.location.href='/maphomepage'</script>");
            long endTime=System.currentTimeMillis();
            System.out.println("【未查找到所输入的API】运行时间： "+(endTime-startTime)+"ms");
            return "maphomepage";/*先弹出对话框提示，再回到查询页面*/
        }
        /*有这个类*/
        else {
            if (className.getType().equals("Java")) {
                javaListAPI = searchService.getJavaAPI(searchText);/*重新赋值*/
        //        System.out.println(searchText);
                if (javaListAPI.isEmpty()) {
                    response.setContentType("text/html;charset=gbk");
                    PrintWriter out = response.getWriter();
                //    System.out.println("有类没方法！！！！");
                    out.print("<script language=\"javascript\">alert('该类中没有此方法！*数据库暂未收录，或请检查输入格式(注意大小写)&请直接输入类名查看其方法');window.location.href='/maphomepage'</script>");
                    long endTime=System.currentTimeMillis();
                    System.out.println("【未查找到所输入的API】运行时间： "+(endTime-startTime)+"ms");
                    return "maphomepage";/*先弹出对话框提示，再回到查询页面*/
                } else {
                //    System.out.println("返回JavaAPI！！！");
                    if (javaListAPI.size() == 1) {/*只有一个同名方法*/
                        int id = javaListAPI.get(0).getApiId();
           //             System.out.println("找到了Java类_方法" + id);
                        javaMapSwift = mapService.getMapSwiftId(id);
                        swiftListAPI.clear();
                        for (int i = 0; i < javaMapSwift.size(); i++) {
                            int mapId = javaMapSwift.get(i).getSwiftId();
                            JavaSwiftAPI theAPI = new JavaSwiftAPI();
                            theAPI = mapService.getMapSwiftAPI(mapId);
                            swiftListAPI.add(theAPI);
                        }
                        model.addAttribute("JavaAPI", javaListAPI.get(0).getFullName());
                        if (swiftListAPI.isEmpty()) {
                            JavaSwiftAPI theAPI = new JavaSwiftAPI();
                            theAPI.setFullName("该方法没有Swift映射！");
                            swiftListAPI.add(theAPI);
                        }
                        model.addAttribute("swiftList", swiftListAPI);
                        long endTime=System.currentTimeMillis();
                        System.out.println("【找到映射：给出具体的映射】运行时间： "+(endTime-startTime)+"ms");
                        return "javaMapSwift";/*查找方法*/
                    } else {
                        model.addAttribute("mapmethodsList", javaListAPI);/*给出同名方法列表*/
                        long endTime=System.currentTimeMillis();
                        System.out.println("【找到多个同名API：给出API表】运行时间： "+(endTime-startTime)+"ms");
                        return "mapmethods";/*点击类名查看方法*/
                    }
                }
            }
            /*是swift类去swift表里查*/
            else if (className.getType().equals("Swift")) {
          //      System.out.println("是Swift类!!");
                swiftListAPI = searchService.getSwiftAPI(searchText);/*重新赋值*/
         //       System.out.println(searchText);
                if (swiftListAPI.isEmpty()) {
                    response.setContentType("text/html;charset=gbk");
                    PrintWriter out = response.getWriter();
                    out.print("<script language=\"javascript\">alert('该类中没有此方法！*数据库暂未收录，或请检查输入格式(注意大小写)&请直接输入类名查看其方法');window.location.href='/maphomepage'</script>");
                    long endTime=System.currentTimeMillis();
                    System.out.println("【未查找到所输入的API】运行时间： "+(endTime-startTime)+"ms");
                    return "maphomepage";/*先弹出对话框提示，再回到查询页面*/
                } else {
          //          System.out.println("返回SwiftAPI！！！");
                    if (swiftListAPI.size() == 1) {/*只有一个同名方法*/
                        int id = swiftListAPI.get(0).getApiId();
                    //    System.out.println("找到了Swift类_方法" + id);
                        swiftMapJava = mapService.getMapJavaId(id);
                        javaListAPI.clear();
                        if (swiftMapJava==null) {
                            theAPI.setFullName("该方法没有Swift映射！");
                            javaListAPI.add(theAPI);
                        }
                        else{
                        int mapId = swiftMapJava.getJavaId();
                        JavaSwiftAPI theAPI = new JavaSwiftAPI();
                        theAPI = mapService.getMapJavaAPI(mapId);
                        javaListAPI.add(theAPI);
                        }
                        model.addAttribute("JavaAPI", javaListAPI.get(0).getFullName());
                        model.addAttribute("SwiftAPI", swiftListAPI.get(0).getFullName());
                        long endTime=System.currentTimeMillis();
                        System.out.println("【找到映射：给出具体的映射】运行时间： "+(endTime-startTime)+"ms");
                        return "swiftMapJava";/*查找方法*/
                    } else {
                        model.addAttribute("mapmethodsList", swiftListAPI);/*给出同名方法列表*/
                        long endTime=System.currentTimeMillis();
                        System.out.println("【找到多个同名API：给出API表】运行时间： "+(endTime-startTime)+"ms");
                        return "mapmethods";/*点击类名查看方法*/
                    }
                }
            }
            return "maphomepage";
        }
    }
}