package com.example.springweb.service;

import com.example.springweb.dao.HelloUser;
import com.example.springweb.dao.JavaSwiftAPI;
import com.example.springweb.mapper.ManageMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class ManageService {
    @Resource
    private ManageMapper manageMapper;

    public void insertJavaAPI(JavaSwiftAPI javaAPI){
        manageMapper.insertJavaApi(javaAPI);
    }

    public void insertSwiftAPI(JavaSwiftAPI swiftAPI){
        manageMapper.insertSwiftApi(swiftAPI);
    }



}
