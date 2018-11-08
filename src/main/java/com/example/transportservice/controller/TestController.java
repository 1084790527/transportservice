package com.example.transportservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(value = "/")
    public String test(){
        return "test";
    }

    @RequestMapping(value = "test1")
    @ResponseBody
    public String test1(){
        return "{\"RESULT\":\"S\",\"ERRMSG\":\"成功\",\"pm2.5\":8,\"co2\":5919,\"LightIntensity\":1711,\"humidity\":44,\"temperature\":28}";
    }


}
