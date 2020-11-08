package com.example.springweb.mapper;

import com.example.springweb.dao.ASetOfMap;
import com.example.springweb.dao.JavaSwiftAPI;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface AddApiMapMapper {

    @Select("select * from apijava_table where fullName= #{name}")
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
    JavaSwiftAPI getTheJavaAPI(String name);

    @Select("select * from apiswift_table where fullName= #{name}")
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
    JavaSwiftAPI getTheSwiftAPI(String name);

    @Insert("insert into apimap_table(APIjavaID,APIswiftID) values(#{APIjavaID},#{APIswiftID}) ")
    void insertApiMap(@Param("APIjavaID") int APIjavaID,@Param("APIswiftID") int APIswiftID);


    @Select("select * from apimap_table where APIjavaID = #{APIjavaID} and APIswiftID = #{APIswiftID}")
    @Results({
            @Result(property = "javaId",column = "APIjavaID"),
            @Result(property = "swiftId",column = "APIswiftID")
    })
    ASetOfMap getTheMap(@Param("APIjavaID")int APIjavaID,@Param("APIswiftID")int APIswiftID);


}
