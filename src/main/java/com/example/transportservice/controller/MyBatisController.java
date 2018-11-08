package com.example.transportservice.controller;

import com.example.transportservice.bean.car;
import com.example.transportservice.dao.carDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@EnableAutoConfiguration
public class MyBatisController {

    @Autowired
    carDao dao;

    @RequestMapping("getcar")
    public String getcar() {
        for (com.example.transportservice.bean.car car:dao.getAll()){
            System.out.println(car.getCarid()+":"+car.getMoney());
        }
        return "wewewe";
    }
    @RequestMapping("getid")
    public String getid(int id){

        car c=dao.getmoneybycarid(id);

        return c.getCarid()+":"+c.getMoney();
    }
    @RequestMapping(value = "setmoney")
    public String setmorey(@RequestParam(value = "id") int id,@RequestParam(value = "money")  int money){
        System.out.println("id"+id);
        dao.setmoney(id,money);
        return "";
    }

    @RequestMapping("getList")
    public List<car> getList(){
        return dao.getlist();
    }

}
