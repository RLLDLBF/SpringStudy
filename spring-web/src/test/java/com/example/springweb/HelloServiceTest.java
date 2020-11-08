package com.example.springweb;

import com.example.springweb.dao.HelloUser;
import com.example.springweb.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloServiceTest {
    @Autowired
    HelloService helloService;

    @Test
    public void getUsers() {
        List<HelloUser> users = helloService.getUserList();
        assertFalse("User not null", users == null);
        assertNotNull(users);
        assertNotEquals(users.size(), 0);
        assertEquals(users.get(0).getName(), "lgy");
    }

    @Test
    public void testInsert() throws Exception{
        //helloService.InsertUser(new HelloUser("4","zyy","dsb"));
       // assertEquals(helloService.getUserList().size(),3);
        Map<String,String> params=new HashMap<>();
        params.put("id","4");
        params.put("name","niha");
        params.put("password","yes");
        helloService.InsertUser(params);
        assertEquals(helloService.getUserList().size(),4);
    }

    @Test
    public void testGetOne() throws Exception{
        HelloUser helloUser = helloService.getOne("9");
        assertEquals(helloUser.getName(),null);
    }

    @Test
    public void testUpdate() throws Exception{
       // helloService.UpdateByID(new HelloUser("3","ooo","bbbb"));
       // assertEquals(helloService.getOne("3").getName(),"ooo");
        Map<String,String> params=new HashMap<>();
        params.put("id","4");
        params.put("name","yyy");
        helloService.UpdateByID(params);
        assertEquals(helloService.getOne("4").getName(),"yyy");
    }

    @Test
    public void testDelete() throws Exception{
        helloService.DeleteByID("4");
        assertEquals(helloService.getOne("4").getName(),null);
    }
}
