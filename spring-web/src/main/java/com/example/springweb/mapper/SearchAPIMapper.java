package com.example.springweb.mapper;

import com.example.springweb.dao.*;
import org.apache.ibatis.annotations.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SearchAPIMapper {
    @Select("select * from class_table where className = #{name}")
    @Results({
            @Result(property = "name",column = "className"),
            @Result(property = "type",column = "type"),
            @Result(property="packageName",column="packageName"),
            @Result(property = "methods",column = "methods"),
            @Result(property = "metNum",column = "metNum")
    })
    ClassName getClassName(String name);

    @Select("select * from apijava_table where apiNameForSearch = #{name}")
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
    ArrayList<JavaSwiftAPI> getJavaListAPI(String name);

    @Select("select * from apiswift_table where apiNameForSearch = #{name}")
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
    ArrayList<JavaSwiftAPI> getSwiftListAPI(String name);

}

