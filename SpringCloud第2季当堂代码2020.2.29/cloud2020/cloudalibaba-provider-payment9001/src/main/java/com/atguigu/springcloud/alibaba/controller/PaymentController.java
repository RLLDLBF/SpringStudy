package com.atguigu.springcloud.alibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @auther zzyy
 * @create 2020-02-23 14:13
 */
@RestController
@Slf4j
public class PaymentController
{
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id)
    {
        log.info("*****返回端口号：");

        return "nacos registry, serverPort: "+ serverPort+"\t id"+id;
    }

    @GetMapping(value = "/payment1/nacos")
    public String getPayment1()
    {
        return "nacos registry";
    }

    @GetMapping(value = "/payment2/nacos")
    public String getPayment2()
    {
        log.info("*****返回端口号：");
        return "nacos registry, serverPort: "+ serverPort;
    }

    @RequestMapping(value="/hello/{id}",method= RequestMethod.GET)
    public String sayHello(@PathVariable("id") Integer id){
        return "id:"+id;
    }
}
