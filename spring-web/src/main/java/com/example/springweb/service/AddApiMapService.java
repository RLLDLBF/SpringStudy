package com.example.springweb.service;

import com.example.springweb.dao.ASetOfMap;
import com.example.springweb.dao.HelloUser;
import com.example.springweb.dao.JavaSwiftAPI;
import com.example.springweb.mapper.AddApiMapMapper;
import com.example.springweb.mapper.HelloMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AddApiMapService {
    @Resource
    private AddApiMapMapper addApiMapMapper;

    public JavaSwiftAPI getTheJavaAPI(String name){
        JavaSwiftAPI theJavaAPI=new JavaSwiftAPI();
        theJavaAPI=addApiMapMapper.getTheJavaAPI(name);
        return theJavaAPI;
    }

    public JavaSwiftAPI getTheSwiftAPI(String name){
        JavaSwiftAPI theSwiftAPI=new JavaSwiftAPI();
        theSwiftAPI=addApiMapMapper.getTheSwiftAPI(name);
        return theSwiftAPI;
    }

    public ASetOfMap getTheMap(int javaId,int swiftId){
        ASetOfMap aSetOfMap=new ASetOfMap();
        aSetOfMap=addApiMapMapper.getTheMap(javaId,swiftId);
        return aSetOfMap;
    }

    public void insertMap(int javaId,int swiftId){
        addApiMapMapper.insertApiMap(javaId,swiftId);
    }

}
