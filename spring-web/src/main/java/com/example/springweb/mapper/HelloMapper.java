package com.example.springweb.mapper;

import com.example.springweb.dao.HelloUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HelloMapper {
    @Select("select * from user ")
    @Results({
            @Result(property = "id", column = "User_id"),
            @Result(property = "name", column = "User_name"),
            @Result(property = "password", column = "User_password")
    })
    List<HelloUser> findAll();

    @Insert("insert into user(User_id,User_name,User_password) values(#{id},#{name},#{password})")
    void insert(HelloUser helloUser);


    @Select("select * from user where User_name = #{name} and User_password= #{password}")
    @Results({
            @Result(property = "id",column = "User_id"),
            @Result(property = "name",column = "User_name"),
            @Result(property="password",column="User_password")
    })
    //public String login(@Param("username")String username,@Param("password")String password);
   HelloUser getOne(String id);

    @Update("update user set User_name = #{name}, User_password = #{password} where User_id = #{id}")
    void updateByID(HelloUser helloUser);//UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值

    @Delete("delete from user where User_id = #{id}")
    void deleteByID(String id);//DELETE FROM 表名称 WHERE 列名称 = 值
}
