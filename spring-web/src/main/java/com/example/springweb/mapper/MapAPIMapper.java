package com.example.springweb.mapper;

import com.example.springweb.dao.ASetOfMap;
import com.example.springweb.dao.HelloUser;
import com.example.springweb.dao.JavaSwiftAPI;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface MapAPIMapper {
    @Select("select * from apimap_table where APIjavaID = #{id}")
    @Results({
            @Result(property = "swiftId",column = "APIswiftID"),
            @Result(property = "javaId",column = "APIjavaID")

    })
    ArrayList<ASetOfMap> getMaps(int id);

    @Select("select * from apimap_table where APIswiftID = #{id}")
    @Results({
            @Result(property = "swiftId",column = "APIswiftID"),
            @Result(property = "javaId",column = "APIjavaID")
    })
    ASetOfMap getTheMap(int id);

    @Select("select * from apiswift_table where apiId = #{id}")
    @Results({
            @Result(property = "apiId",column = "apiId"),
            @Result(property = "apiNameForSearch",column = "apiNameForSearch"),
            @Result(property = "fullName",column = "fullName"),
            @Result(property = "packageName",column = "packageName"),
            @Result(property = "className",column = "className"),
            @Result(property = "methodName",column = "methodName"),
            @Result(property = "apiSignature",column = "apiSignature"),
            @Result(property = "description",column = "description"),
            @Result(property = "formParameter",column = "formParameter"),
            @Result(property = "returnParam",column = "returnParam"),
            @Result(property = "codeSample",column = "codeSample")
    })
    JavaSwiftAPI getMapSwiftAPI(int id);

    @Select("select * from apijava_table where apiId = #{id}")
    @Results({
            @Result(property = "apiId",column = "apiId"),
            @Result(property = "apiNameForSearch",column = "apiNameForSearch"),
            @Result(property = "fullName",column = "fullName"),
            @Result(property = "packageName",column = "packageName"),
            @Result(property = "className",column = "className"),
            @Result(property = "methodName",column = "methodName"),
            @Result(property = "apiSignature",column = "apiSignature"),
            @Result(property = "description",column = "description"),
            @Result(property = "formParameter",column = "formParameter"),
            @Result(property = "returnParam",column = "returnParam"),
            @Result(property = "codeSample",column = "codeSample")
    })
    JavaSwiftAPI getMapJavaAPI(int id);
}