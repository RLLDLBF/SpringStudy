package com.example.springweb.service;

import com.example.springweb.dao.ASetOfMap;
import com.example.springweb.dao.ClassName;
import com.example.springweb.dao.HelloUser;
import com.example.springweb.dao.JavaSwiftAPI;
import com.example.springweb.mapper.HelloMapper;
import com.example.springweb.mapper.MapAPIMapper;
import com.example.springweb.mapper.SearchAPIMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MapService {
    @Resource
    private MapAPIMapper mapAPIMapper;


    public ArrayList<ASetOfMap> getMapSwiftId(int id){
        ArrayList<ASetOfMap>maps=new ArrayList<ASetOfMap>();
        maps = mapAPIMapper.getMaps(id);
        return maps;  //Java一对多
    }
    public ASetOfMap getMapJavaId(int id){
      ASetOfMap theMap=new ASetOfMap();
      theMap = mapAPIMapper.getTheMap(id);
      return theMap;  //Swift一对一
    }
    public JavaSwiftAPI getMapSwiftAPI(int id){
        JavaSwiftAPI theAPI=new JavaSwiftAPI();
        theAPI=mapAPIMapper.getMapSwiftAPI(id);
        return theAPI;
    }
    public JavaSwiftAPI getMapJavaAPI(int id){
        JavaSwiftAPI theAPI=new JavaSwiftAPI();
        theAPI=mapAPIMapper.getMapJavaAPI(id);
        return theAPI;
    }
}
