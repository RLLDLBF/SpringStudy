package com.example.springweb.service;

import com.example.springweb.dao.ClassName;
import com.example.springweb.dao.HelloUser;
import com.example.springweb.dao.JavaSwiftAPI;
import com.example.springweb.mapper.HelloMapper;
import com.example.springweb.mapper.SearchAPIMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchService {
    @Resource
    private SearchAPIMapper searchAPIMapper;


    public ClassName getClassName(String name){
        ClassName classNameResult=new ClassName();
        classNameResult = searchAPIMapper.getClassName(name);
    //    if (classNameResult!=null)
      //  {
         //   System.out.println("getClass!:"+classNameResult.getName()+" "+classNameResult.getType()+" "+classNameResult.getPackageName()+" "+classNameResult.getMethods());
        //}
        return classNameResult;
    }

    public ArrayList<JavaSwiftAPI> getJavaAPI(String apiNameForSearch){
        ArrayList<JavaSwiftAPI> javaListAPI=searchAPIMapper.getJavaListAPI(apiNameForSearch);
        return javaListAPI;
    }

    public ArrayList<JavaSwiftAPI> getSwiftAPI(String apiNameForSearch){
        ArrayList<JavaSwiftAPI> swiftListAPI=searchAPIMapper.getSwiftListAPI(apiNameForSearch);
        return swiftListAPI;
    }


}
