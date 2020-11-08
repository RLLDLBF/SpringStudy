package com.example.springweb.mapper;

import com.example.springweb.dao.HelloUser;
import com.example.springweb.dao.JavaSwiftAPI;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManageMapper {

    @Insert("insert into apijava_table(apiId,apiNameForSearch,fullName,packageName,className,methodName,apiSignature,description,formParameter,returnParam,codeSample) values(#{apiId},#{apiNameForSearch},#{fullName},#{packageName},#{className},#{methodName},#{apiSignature},#{description},#{formParameter},#{returnParam},#{codeSample})")
    void insertJavaApi(JavaSwiftAPI javaSwiftAPI);


    @Insert("insert into apiswift_table(apiId,apiNameForSearch,fullName,packageName,className,methodName,apiSignature,description,formParameter,returnParam,codeSample) values(#{apiId},#{apiNameForSearch},#{fullName},#{packageName},#{className},#{methodName},#{apiSignature},#{description},#{formParameter},#{returnParam},#{codeSample})")
    void insertSwiftApi(JavaSwiftAPI javaSwiftAPI);
}
